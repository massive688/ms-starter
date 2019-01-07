package tp.ms.common.data.register;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;
import javax.sql.XADataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyNameAliases;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.github.pagehelper.PageInterceptor;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

import lombok.val;
import lombok.extern.slf4j.Slf4j;
import tp.ms.common.bean.utils.ObjectUtilms;
import tp.ms.common.data.mybatis.config.MsSessionTemplateHolder;
import tp.ms.common.data.mybatis.properties.DSourceProperties;
import tp.ms.common.data.mybatis.properties.MsXAProperties;
import tp.ms.common.data.mybatis.session.MsSqlSessionTemplate;
import tp.ms.common.data.source.config.MsDynamicDataSourceHolder;
import tp.ms.common.data.source.session.MsDynamicRoutingDataSource;

/**
 * 动态数据源注册
 * 实现 ImportBeanDefinitionRegistrar 实现数据源注册
 * 实现 EnvironmentAware 用于读取application.yml配置
 */
@Slf4j
@Order(Integer.MIN_VALUE)
public class MsDynamicSessionSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {


    /**
     * 配置上下文（也可以理解为配置文件的获取工具）
     */
    private Environment evn;

    /**
     * 别名
     */
    private final static ConfigurationPropertyNameAliases aliases = new ConfigurationPropertyNameAliases();

    /**
     * 由于部分数据源配置不同，所以在此处添加别名，避免切换数据源出现某些参数无法注入的情况
     */
    static {
        aliases.addAliases("url", new String[]{"jdbc-url"});
        aliases.addAliases("username", new String[]{"user"});
    }

    /**
     * 存储我们注册的SqlSessionFactory
     */
    private Map<String, SqlSessionFactory> customSqlSessionFactorys = new HashMap<String, SqlSessionFactory>();
    /**
     * 存储我们注册的数据源
     */
    private Map<String, DataSource> dataSources = new HashMap<String, DataSource>();

    /**
     * 参数绑定工具 springboot2.0新推出
     */
    private Binder binder;
    
	String sqlSessionFactoryKey = "Session";

	private AtomikosDataSourceBean defaultDataSource;
	
    /**
     * ImportBeanDefinitionRegistrar接口的实现方法，通过该方法可以按照自己的方式注册bean
     *
     * @param annotationMetadata
     * @param beanDefinitionRegistry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata,
    		BeanDefinitionRegistry beanDefinitionRegistry) {
		List<DSourceProperties> dssProps = binder.bind("ms.ds.source", Bindable.listOf(DSourceProperties.class)).get();
		MsXAProperties xaProp = binder.bind("ms.ds.xas", MsXAProperties.class).get();
		int index=0;
    	SqlSessionFactory defaultSqlSessionFactory = null, sqlSessionFactory;
		for(DSourceProperties dssProp : dssProps) {
	    	sqlSessionFactory = registerFactory(xaProp, dssProp, beanDefinitionRegistry, 0==index++);
	    	if(index == 1) {
	    		defaultSqlSessionFactory = sqlSessionFactory;
	    	}
	    	if(ObjectUtilms.isEqual(dssProp.getKey(), "default")) {
	    		defaultSqlSessionFactory = sqlSessionFactory;
	    	}
		}

    	//注册dataSource
        // bean定义类
        GenericBeanDefinition dataSource = new GenericBeanDefinition();
        // 设置bean的类型，此处DynamicRoutingDataSource是继承AbstractRoutingDataSource的实现类
        dataSource.setBeanClass(MsDynamicRoutingDataSource.class);
        dataSource.setPrimary(true);
        // 需要注入的参数
        MutablePropertyValues dataSourceMpv = dataSource.getPropertyValues();
        // 添加默认数据源，避免key不存在的情况没有数据源可用
        dataSourceMpv.add("defaultTargetDataSource", defaultDataSource);
        // 添加其他数据源
        dataSourceMpv.add("targetDataSources", dataSources);
        // 将该bean注册为datasource，不使用springboot自动生成的datasource
        beanDefinitionRegistry.registerBeanDefinition("dataSource", dataSource);
        log.info("注册dataSource成功，一共注册{}个数据源", dataSources.keySet().size());
        
    	//注册SqlSessionTemplate
        // bean定义类
        GenericBeanDefinition define = new GenericBeanDefinition();
        // 设置bean的类型，此处MsCustomerSqlSessionTemplate是继承SqlSessionTemplate的实现类
        define.setBeanClass(MsSqlSessionTemplate.class);
        define.getConstructorArgumentValues().addGenericArgumentValue(defaultSqlSessionFactory);
        // 需要注入的参数
        MutablePropertyValues mpv = define.getPropertyValues();
        // 添加其他数据源
        mpv.add("targetSqlSessionFactorys", customSqlSessionFactorys);
        // 将该bean注册为sqlSessionTemplate，不使用springboot自动生成的sqlSessionTemplate
        beanDefinitionRegistry.registerBeanDefinition("sqlSessionTemplate", define);
        log.info("注册SqlSesion成功，一共注册{}个会话模板", customSqlSessionFactorys.keySet().size());
    }


    
    private SqlSessionFactory registerFactory(MsXAProperties xaProp, DSourceProperties dssProp, BeanDefinitionRegistry beanDefinitionRegistry,boolean isPrimary) {
    	//注册DataSource
    	DataSource dataSource = registerDataSource(xaProp, dssProp,beanDefinitionRegistry, isPrimary);
    	//注册SqlSessionFactory
    	try {
			return registerSessionFactory(dssProp, dataSource, beanDefinitionRegistry, isPrimary);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }


	private SqlSessionFactory registerSessionFactory(DSourceProperties dssProp, DataSource dataSource,
			BeanDefinitionRegistry beanDefinitionRegistry, boolean isPrimary) throws Exception {
    	SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
    	sqlSessionFactory.setDataSource(dataSource);

	    if(isPrimary) {
	        // 定义默认sqlSessionFactory类 
			// bean定义类
			GenericBeanDefinition define = new GenericBeanDefinition();
		    // 设置bean的类型，此处DynamicRoutingDataSource是继承AbstractRoutingDataSource的实现类
	        define.setBeanClass(SqlSessionFactoryBean.class);
		    define.setPrimary(true);
		    // 需要注入的参数
		    MutablePropertyValues mpv = define.getPropertyValues();
		    mpv.add("dataSource", dataSource);
		    // 将该bean注册为datasource，不使用springboot自动生成的datasource
	        beanDefinitionRegistry.registerBeanDefinition("sqlSessionFactory", define);
	        log.info("注册默认sqlSessionFactory成功，注册默认{}会话", sqlSessionFactory);
	    }
	    
    	
		// bean定义类
		GenericBeanDefinition define = new GenericBeanDefinition();
	    // 设置bean的类型，此处DynamicRoutingDataSource是继承AbstractRoutingDataSource的实现类
        define.setBeanClass(SqlSessionFactoryBean.class);
	    define.setPrimary(false);
	    // 需要注入的参数
	    MutablePropertyValues mpv = define.getPropertyValues();
	    mpv.add("dataSource", dataSource);
	    // 将该bean注册为datasource，不使用springboot自动生成的datasource
	    beanDefinitionRegistry.registerBeanDefinition(dssProp.getKey()+"SqlSessionFactory", define);
	    
	    
	    //添加到holder中
	    MsSessionTemplateHolder.sessionKeys.add(dssProp.getKey()+sqlSessionFactoryKey);
	    
	    if(dataSource instanceof AtomikosDataSourceBean) {
	    	AtomikosDataSourceBean xaDataSource = (AtomikosDataSourceBean) dataSource;
	    	log.info(xaDataSource.getUniqueResourceName() + "数据源注入 sqlSqlSessionFactoryBean 成功.....");
	 	}
		 SqlSessionFactory sqlSessionFactoryObject = sqlSessionFactory.getObject();
		 sqlSessionFactoryObject.getConfiguration().addInterceptor(createPageInterceptor(dssProp.getDbType()));
    	//添加到工厂集合
    	customSqlSessionFactorys.put(dssProp.getKey()+sqlSessionFactoryKey, sqlSessionFactoryObject);

    	log.info(dssProp.getKey()+sqlSessionFactoryKey + " SqlSession注入Spring成功......");

    	return sqlSessionFactoryObject;
	}


	private DataSource registerDataSource(MsXAProperties xaProp, DSourceProperties dssProp, 
			BeanDefinitionRegistry beanDefinitionRegistry, boolean isPrimary) {
		String type = dssProp.getDbType(), key = dssProp.getKey()+"DataSource";
		XADataSource dataSource = null;
    	if(type.equals("mysql")) {
    		MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
    		mysqlXaDataSource.setUrl(dssProp.getUrl());
    		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
    		mysqlXaDataSource.setPassword(dssProp.getPassword());
    		mysqlXaDataSource.setUser(dssProp.getUsername());
    		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
    		dataSource = mysqlXaDataSource;
    	}else{
			@SuppressWarnings("resource")
			DruidXADataSource druidXA = new DruidXADataSource();
    		druidXA.setUrl(dssProp.getUrl());
    		druidXA.setDriverClassName(dssProp.getDriverClassName());
    		druidXA.setDbType(dssProp.getDbType());
    		druidXA.setPassword(dssProp.getPassword());
    		druidXA.setUsername(dssProp.getUsername());
    		druidXA.setSharePreparedStatements(true);
    		druidXA.setValidationQuery("SELECT 1");
    		dataSource = druidXA;
    	}
		/**
		 * 设置分布式-- 主数据源
		 * 
		 */
		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(dataSource);
		xaDataSource.setUniqueResourceName(dssProp.getKey()+"DataSource");
		Properties xaProperties = bindProp(xaProp);
		xaDataSource.setMinPoolSize(xaProp.getMinPoolSize());
		xaDataSource.setMaxPoolSize(xaProp.getMaxPoolSize());
		xaDataSource.setMaxLifetime(xaProp.getMaxLifetime());
		xaDataSource.setBorrowConnectionTimeout(xaProp.getBorrowConnectionTimeout());
		try {
			xaDataSource.setLoginTimeout(xaProp.getLoginTimeout());
		} catch (SQLException e) {
		}
		xaDataSource.setMaintenanceInterval(xaProp.getMaintenanceInterval());
		xaDataSource.setMaxIdleTime(xaProp.getMaxIdleTime());
		xaDataSource.setTestQuery(xaProp.getTestQuery());
		xaDataSource.setXaProperties(xaProperties);
		  
		// bean定义类
		GenericBeanDefinition define = new GenericBeanDefinition();
	    // 设置bean的类型，此处AtomikosDataSourceBean是继承XADataSource的实现类
	    define.setBeanClass(AtomikosDataSourceBean.class);
	    define.setPrimary(isPrimary);
	    // 需要注入的参数
	    MutablePropertyValues mpv = define.getPropertyValues();
	    mpv.add("xaDataSource", dataSource);
		mpv.add("minPoolSize", xaProp.getMinPoolSize());
		mpv.add("maxPoolSize", xaProp.getMaxPoolSize());
		mpv.add("maxLifetime", xaProp.getMaxLifetime());
		mpv.add("borrowConnectionTimeout", xaProp.getBorrowConnectionTimeout());
		mpv.add("loginTimeout", xaProp.getLoginTimeout());
		mpv.add("maintenanceInterval", xaProp.getMaintenanceInterval());
		mpv.add("maxIdleTime", xaProp.getMaxIdleTime());
		mpv.add("testQuery", xaProp.getTestQuery());
	    // 添加其他数据源
	    mpv.add("uniqueResourceName", xaDataSource.getUniqueResourceName());
	    mpv.add("xaProperties", xaProperties);
	    // 将该bean注册为datasource，不使用springboot自动生成的datasource
	    if(isPrimary) {
	    	defaultDataSource = xaDataSource;
	    }
    	if(ObjectUtilms.isEqual(dssProp.getKey(), "default")) {
	    	defaultDataSource = xaDataSource;
    	}
	    define.setPrimary(false);
	    // 将该bean注册为datasource，不使用springboot自动生成的datasource
	    beanDefinitionRegistry.registerBeanDefinition(key, define);
	    
	    dataSources.put(key, xaDataSource);
	    
	    MsDynamicDataSourceHolder.dataSourceKeys.add(key);
	    
		return xaDataSource;
	}


	private Properties bindProp(MsXAProperties druidProp) {
		Properties p = new Properties();
		p.putIfAbsent("minPoolSize", druidProp.getMinPoolSize());
		p.putIfAbsent("maxPoolSize", druidProp.getMaxPoolSize());
		p.putIfAbsent("maxLifetime", druidProp.getMaxLifetime());
		p.putIfAbsent("borrowConnectionTimeout", druidProp.getBorrowConnectionTimeout());
		p.putIfAbsent("loginTimeout", druidProp.getLoginTimeout());
		p.putIfAbsent("maintenanceInterval", druidProp.getMaintenanceInterval());
		p.putIfAbsent("maxIdleTime", druidProp.getMaxIdleTime());
		p.putIfAbsent("testQuery", druidProp.getTestQuery());
		return p;
	}

    /**
     * EnvironmentAware接口的实现方法，通过aware的方式注入，此处是environment对象
     *
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        this.evn = environment;
        // 绑定配置器
        binder = Binder.get(evn);
    }
    
    
    
    private PageInterceptor createPageInterceptor(String dbType) {
		val pageInterceptor = new PageInterceptor();
		Properties properties = new Properties();
//		properties.setProperty("offsetAsPageNum", "true");
//		properties.setProperty("rowBoundsWithCount", "true");
//		properties.setProperty("reasonable", "true");
		properties.setProperty("helperDialect", "jtds".equalsIgnoreCase(dbType)?"sqlserver":dbType);
		properties.setProperty("reasonable", "false");
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("params", "count=countSql");
//		switch (dbType) {
//		case JdbcUtils.MYSQL:
//			properties.setProperty("dialect","com.github.pagehelper.dialect.helper.MySqlDialect");
//			break;
//		case JdbcUtils.ORACLE:
//	        properties.setProperty("dialect","com.github.pagehelper.dialect.helper.OracleDialect");
//	        break;
//		case JdbcUtils.JTDS:
//			properties.setProperty("dialect","com.github.pagehelper.dialect.helper.SqlServerDialect");
//			break;
//		default:
//			properties.setProperty("dialect","com.github.pagehelper.dialect.helper.SqlServerDialect");
//			break;
//		}
		pageInterceptor.setProperties(properties);
		return pageInterceptor;
	}
}
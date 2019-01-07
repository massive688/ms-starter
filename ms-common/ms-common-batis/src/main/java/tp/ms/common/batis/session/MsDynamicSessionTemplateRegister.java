package tp.ms.common.batis.session;

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
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

import lombok.extern.slf4j.Slf4j;
import tp.ms.common.batis.properties.DSourceProperties;
import tp.ms.common.batis.properties.MsXAProperties;

/**
 * 动态数据源注册
 * 实现 ImportBeanDefinitionRegistrar 实现数据源注册
 * 实现 EnvironmentAware 用于读取application.yml配置
 */
@Slf4j
public class MsDynamicSessionTemplateRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {


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
     * 存储我们注册的数据源
     */
    private Map<String, SqlSessionFactory> customSqlSessionFactorys = new HashMap<String, SqlSessionFactory>();

    /**
     * 参数绑定工具 springboot2.0新推出
     */
    private Binder binder;
    
	String sqlSessionFactoryKey = "Session";
	
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
		}
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
        log.info("注册数据源成功，一共注册{}个数据源", customSqlSessionFactorys.keySet().size() + 1);
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
		// bean定义类
		GenericBeanDefinition define = new GenericBeanDefinition();
	    // 设置bean的类型，此处DynamicRoutingDataSource是继承AbstractRoutingDataSource的实现类
        define.setBeanClass(SqlSessionFactoryBean.class);
	    define.setPrimary(isPrimary);
	    // 需要注入的参数
	    MutablePropertyValues mpv = define.getPropertyValues();
	    mpv.add("dataSource", dataSource);
	    // 将该bean注册为datasource，不使用springboot自动生成的datasource
	    beanDefinitionRegistry.registerBeanDefinition(dssProp.getKey()+"SqlSessionFactory", define);
	    
	    //添加到holder中
	    MsSessionTemplateHolder.sessionKeys.add(dssProp.getKey()+sqlSessionFactoryKey);
	    
	    System.err.println(dssProp.getKey()+sqlSessionFactoryKey);
    	
	    SqlSessionFactory sqlSessionFactoryObject = sqlSessionFactory.getObject();
    	//添加到工厂集合
    	customSqlSessionFactorys.put(dssProp.getKey()+sqlSessionFactoryKey, sqlSessionFactoryObject);
    	
		return sqlSessionFactoryObject;
	}


	private DataSource registerDataSource(MsXAProperties xaProp, DSourceProperties dssProp, 
			BeanDefinitionRegistry beanDefinitionRegistry, boolean isPrimary) {
		String type = dssProp.getDbType();
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
//		xaDataSource.setMinPoolSize(dssProp.getMinPoolSize());
//		xaDataSource.setMaxPoolSize(dssProp.getMaxPoolSize());
//		xaDataSource.setMaxLifetime(dssProp.getMaxLifetime());
//		xaDataSource.setBorrowConnectionTimeout(dssProp.getBorrowConnectionTimeout());
//		try {
//			xaDataSource.setLoginTimeout(dssProp.getLoginTimeout());
//		} catch (SQLException e) {
//		}
//		xaDataSource.setMaintenanceInterval(dssProp.getMaintenanceInterval());
//		xaDataSource.setMaxIdleTime(dssProp.getMaxIdleTime());
//		xaDataSource.setTestQuery(dssProp.getTestQuery());
		xaDataSource.setXaProperties(xaProperties);
		  
		// bean定义类
		GenericBeanDefinition define = new GenericBeanDefinition();
	    // 设置bean的类型，此处DynamicRoutingDataSource是继承AbstractRoutingDataSource的实现类
	    define.setBeanClass(AtomikosDataSourceBean.class);
	    define.setPrimary(isPrimary);
	    // 需要注入的参数
	    MutablePropertyValues mpv = define.getPropertyValues();
	    mpv.add("xaDataSource", dataSource);
	    // 添加其他数据源
	    mpv.add("uniqueResourceName", xaDataSource.getUniqueResourceName());
	    mpv.add("xaProperties", xaProperties);
	    // 将该bean注册为datasource，不使用springboot自动生成的datasource
	    beanDefinitionRegistry.registerBeanDefinition(dssProp.getKey()+"DataSource", define);
		System.err.println(xaDataSource.getUniqueResourceName() + "数据 sqlSqlSessionFactoryBean 源注入成功.....");
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
        log.info("开始注册数据源");
        this.evn = environment;
        // 绑定配置器
        binder = Binder.get(evn);
    }
}
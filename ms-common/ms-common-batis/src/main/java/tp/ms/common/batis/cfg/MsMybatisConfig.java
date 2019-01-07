package tp.ms.common.batis.cfg;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.alibaba.druid.util.JdbcUtils;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.github.pagehelper.PageInterceptor;

import lombok.val;
import lombok.extern.slf4j.Slf4j;
import tp.ms.common.batis.properties.DSourceProperties;
import tp.ms.common.batis.properties.MsProperties;
//import tp.ms.common.data.TargetDataSource;

@Slf4j
//@Configuration
//@EnableConfigurationProperties(MsProperties.class)
//@EnableTransactionManagement
//@EnableAspectJAutoProxy(proxyTargetClass=true)
//@ComponentScan({
//	"tp.ms.common.batis.aspect",
//})
//@Import({
//	MsDynamicDataSourceRegister_Temp.class,
//	DruidStatViewServletFilterConfiguration.class
//	})
public class MsMybatisConfig implements EnvironmentAware {

	static final String MAPPERLOCATION = "classpath*:mappers/*/*.xml";

   /*
     * 配置上下文（也可以理解为配置文件的获取工具）
     */
	@Autowired
    private Environment evn;
	
	
//	@Bean
//	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
//		return new DataSourceTransactionManager(dataSource);
//	}
	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage(evn.getProperty("ms.dyn.myb.mapper-scan"));
//		mapperScannerConfigurer.setAnnotationClass(TargetDataSource.class);
		return mapperScannerConfigurer;
	}
	
	@SuppressWarnings("unused")
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource, MsProperties mds)
			throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		Map<String,Interceptor> interceptors = new HashMap<>(); 
//		interceptors.put(mds.getMaster().getDbType(), createPageInterceptor(mds.getMaster()));
//		for(val ds : mds.getSlave()) {
//			if(!interceptors.containsKey(ds.getDbType())) {
//				interceptors.put(ds.getDbType(), createPageInterceptor(ds));
//			}
//		}
		log.error("PageInterceptor configuration initialization interceptors end");
//        sessionFactoryBean.setPlugins(interceptors.values().toArray(new Interceptor[0]));
		
//		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//		sessionFactoryBean.setMapperLocations(resolver.getResources(MsMyBatisMapperScannerConfig.MAPPERLOCATION));
		sessionFactoryBean.setMapperLocations(mds.resolveMapperLocations());
		
		sessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);

		return sessionFactoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	   @Bean
	    public TransactionManager atomikosTransactionManager() {
	        val userTransactionManager = new UserTransactionManager();
	        userTransactionManager.setForceShutdown(true);
	        return userTransactionManager;
	    }
	   @Bean
	    public UserTransaction atomikosUserTransaction() {
	        val userTransaction = new UserTransactionImp();
	        try {
				userTransaction.setTransactionTimeout(90000);
			} catch (SystemException e) {
			}
	        return userTransaction;
	    }
	   @Bean
	    public PlatformTransactionManager springTransactionManager() {
	        val jta = new JtaTransactionManager();
	        jta.setTransactionManager(atomikosTransactionManager());
	        jta.setUserTransaction(atomikosUserTransaction());
	        jta.setAllowCustomIsolationLevels(true);
			return jta;
	    }
	
	
	@SuppressWarnings("unused")
	private Interceptor createPageInterceptor(DSourceProperties dSourceProperties) {
		val pageInterceptor = new PageInterceptor();
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum", "true");
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("reasonable", "true");
		switch (dSourceProperties.getDbType()) {
		case JdbcUtils.MYSQL:
			properties.setProperty("dialect","com.github.pagehelper.dialect.helper.MySqlDialect");
			break;
		case JdbcUtils.ORACLE:
	        properties.setProperty("dialect","com.github.pagehelper.dialect.helper.OracleDialect");
	        break;
		case JdbcUtils.JTDS:
			properties.setProperty("dialect","com.github.pagehelper.dialect.helper.SqlServerDialect");
			break;
		default:
			properties.setProperty("dialect","com.github.pagehelper.dialect.helper.SqlServerDialect");
			break;
		}
		pageInterceptor.setProperties(properties);
		return pageInterceptor;
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.evn = environment;
	}

	
	
	/*
	 * - 设置SqlSessionFactory；
	 * - 设置dao所在的package路径；
	 * - 关联注解在dao类上的Annotation名字；
	 */
//	@Bean(name="nc6302MapperScannerConfigurer")
//	public MapperScannerConfigurer nc6302MapperScannerConfigurer() {
//		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//		mapperScannerConfigurer.setSqlSessionFactoryBeanName("nc6302SqlSessionFactory");
//		mapperScannerConfigurer.setBasePackage(MybatisConfiguration.PACKAGE);
//		mapperScannerConfigurer.setAnnotationClass(Nc6302Repository.class);
//		return mapperScannerConfigurer;
//	}
//	/*
//	 * nc6302 数据源
//	 * @return
//	 */
//	@Bean(name = "nc6302DataSource")
//	@ConfigurationProperties(prefix = "spring.datasource.mssql10122624932nc6302")
//	public DataSource nc6302DataSource(MsProperties ds){
//		DruidDataSource datasource = DataSourceBuilder.create().type(DruidDataSource.class).build();
//		datasource.setUrl(ds.getUrl());
//		datasource.setUsername(ds.getUsername());
//		datasource.setPassword(ds.getPassword());
//		datasource.setDriverClassName(ds.getDriverClassName());
//        datasource.setInitialSize(ds.getInitialSize());
//        datasource.setMinIdle(ds.getMinIdle());
//        datasource.setMaxActive(ds.getMaxActive());
//        datasource.setMaxWait(ds.getMaxWait());
//        datasource.setMinEvictableIdleTimeMillis(ds.getMinEvictableIdleTimeMillis());
//        
//        datasource.setTimeBetweenEvictionRunsMillis(ds.getTimeBetweenEvictionRunsMillis());
//        datasource.setValidationQuery(ds.getValidationQuery());
//        datasource.setTestWhileIdle(ds.isTestWhileIdle());
//        datasource.setTestOnBorrow(ds.isTestOnBorrow());
//        datasource.setTestOnReturn(ds.isTestOnReturn());
//        try {
//            datasource.setFilters(ds.getFilters());
//        } catch (SQLException e) {
//            log.error("druid configuration initialization filter", e);
//        }
//        datasource.setConnectionProperties(ds.getConnectionProperties());
//		log.info("DataSource {} Initialization end。", datasource.getClass().getName());
//		return datasource;
//	}
//	@Bean(name = "nc6302TransactionManager")
//	public DataSourceTransactionManager nc6302TransactionManager() {
//		return new DataSourceTransactionManager(nc6302DataSource());
//	}
//	@Bean(name = "nc6302SqlSessionFactory")
//	@Qualifier
//	public SqlSessionFactory nc6302SqlSessionFactory(@Qualifier("nc6302DataSource") DataSource nc6302DataSource)
//			throws Exception {
//		final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
//		sessionFactoryBean.setDataSource(nc6302DataSource);
//		/*
//		 * 分页插件
//		 * https://www.cnblogs.com/onetwo/p/7371778.html
//		 */
//        PageHelper pageHelper = new PageHelper();
//        val pageInterceptor = new PageInterceptor();
//        Properties properties = new Properties();
////        pagehelper.dialect=
////        var mysqlDialect = new com.github.pagehelper.dialect.helper.MySqlDialect();
////        var mssqlDialect = new com.github.pagehelper.dialect.helper.SqlServerDialect();
////        var oracleDialect = new com.github.pagehelper.dialect.helper.OracleDialect();
////        properties.setProperty("dialect","com.github.pagehelper.dialect.helper.OracleDialect");
//        properties.setProperty("offsetAsPageNum","true");
//        properties.setProperty("rowBoundsWithCount","true");
//        properties.setProperty("reasonable","true");
//        properties.setProperty("dialect","com.github.pagehelper.dialect.helper.SqlServerDialect");
//        pageHelper.setProperties(properties);
//        pageInterceptor.setProperties(properties);
//        sessionFactoryBean.setPlugins(new Interceptor[]{ pageInterceptor});
//        
//		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//		sessionFactoryBean.setMapperLocations(resolver.getResources(MybatisConfiguration.MAPPERLOCATION));
////		sessionFactoryBean.setMapperLocations(resolver.getResources(MybatisConfiguration.MAPPERLOCATION));
//		sessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
//		return sessionFactoryBean.getObject();
//	}
   
    
    
}
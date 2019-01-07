package com.ms.workable.flow.api.conf;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author ms date: 2018/ desc: flowable配置----为放置生成的流程图中中文乱码
 */
@Configuration
@SuppressWarnings("unused")
public class WorkSpringFlowableProcessEngineConfiguration implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {

	@Autowired
	private PlatformTransactionManager transactionManager;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Autowired
	private DataSource dataSource;

//	@Qualifier("mprocessDataSource")
//	@Bean
//	public ProcessEngineFactoryBean processEngineConfiguration(
//			SpringProcessEngineConfiguration springProcessEngineConfiguration) {
//		val processEngineFactoryBean = new ProcessEngineFactoryBean();
//		springProcessEngineConfiguration.setDataSource(dataSource);
//		processEngineFactoryBean.setProcessEngineConfiguration(springProcessEngineConfiguration);
//		return processEngineFactoryBean;
//	}
	


	@Override
	public void configure(SpringProcessEngineConfiguration engineConfiguration) {
		engineConfiguration.setActivityFontName("宋体");
		engineConfiguration.setLabelFontName("宋体");
		engineConfiguration.setAnnotationFontName("宋体");
//		engineConfiguration.setDataSource(dataSource);
//		engineConfiguration.setSqlSessionFactory(sqlSessionFactory);
//		engineConfiguration.setTransactionManager(transactionManager);
		engineConfiguration.setDatabaseSchemaUpdate(SpringProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		engineConfiguration.setAsyncExecutorMessageQueueMode(false);
		engineConfiguration.setAsyncExecutorActivate(false);

//		engineConfiguration.setJobManager(jobManager());
//	        engineConfiguration.setIdGenerator(new DbIdGenerator());
	}
	
	
}

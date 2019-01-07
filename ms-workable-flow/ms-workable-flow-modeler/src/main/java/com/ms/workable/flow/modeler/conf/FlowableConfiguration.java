package com.ms.workable.flow.modeler.conf;

import javax.sql.DataSource;

import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author ms date: 2018/ desc: flowable配置----为放置生成的流程图中中文乱码
 */
@Configuration
@EnableAutoConfiguration
public class FlowableConfiguration implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {

	@Autowired
	DataSource dataSource;
	@Autowired
	private PlatformTransactionManager transactionManager;

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
		engineConfiguration.setDataSource(dataSource);
		engineConfiguration.setDatabaseSchemaUpdate(SpringProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		engineConfiguration.setAsyncExecutorMessageQueueMode(false);
		engineConfiguration.setAsyncExecutorActivate(false);
		engineConfiguration.setTransactionManager(transactionManager);
//		engineConfiguration.setJobManager(jobManager());
//	        engineConfiguration.setIdGenerator(new DbIdGenerator());
	}
	
	
}

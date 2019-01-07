package com.ms.workable.flow.modeler;

import org.flowable.ui.modeler.conf.ApplicationConfiguration;
import org.flowable.ui.modeler.conf.DatabaseConfiguration;
import org.flowable.ui.modeler.servlet.AppDispatcherServletConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
@Import({
    ApplicationConfiguration.class,
    AppDispatcherServletConfiguration.class,
    DatabaseConfiguration.class
})
@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)
public class SpringFlowableModelerApplication extends SpringBootServletInitializer {
	
	@Override protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringFlowableModelerApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringFlowableModelerApplication.class, args);
	}
}

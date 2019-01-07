package com.ms.workable.flow.api.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author ms date: 2018/ desc: flowable配置----为放置生成的流程图中中文乱码
 */
@Configuration
@Import({
	WorkSpringFlowableProcessEngineConfiguration.class,
//	WorkSpringAppFlowableEngineConfiguration.class,
//	WorkSpringCmmnFlowableEngineConfiguration.class,
//	WorkSpringDmnFlowableEngineConfiguration.class,
//	WorkSpringFormFlowableEngineConfiguration.class,
})
@ComponentScan({
	"com.ms.workable.flow.api",
})
public class WorkFlowableConfiguration {

	
}

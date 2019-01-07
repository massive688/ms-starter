package com.ms.workable.flow.modeler.processes;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProjectManagerTaskHandler implements TaskListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5773303672082612185L;

	@Override
	public void notify(DelegateTask delegateTask) {
		log.debug(ProcessesVariableKey.AUDITED_FORM_DATA_OBJECT);
		delegateTask.getVariable(EVENTNAME_ALL_EVENTS);
//		SpringContextHolder.getBean(requiredType);
		
	}

}

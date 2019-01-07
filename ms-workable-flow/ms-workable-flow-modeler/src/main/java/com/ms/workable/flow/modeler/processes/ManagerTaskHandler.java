package com.ms.workable.flow.modeler.processes;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
 
public class ManagerTaskHandler implements TaskListener {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = -6564372800548594301L;

	@Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.setAssignee("经理");
    }
 
}

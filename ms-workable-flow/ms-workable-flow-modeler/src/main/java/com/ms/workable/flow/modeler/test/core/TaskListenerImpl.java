package com.ms.workable.flow.modeler.test.core;
 
import java.util.List;
import java.util.Map;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngines;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.task.service.delegate.DelegateTask;
 
@SuppressWarnings("serial")
public class TaskListenerImpl implements TaskListener {
 
	/**用来指定任务的办理人*/
	@Override
	public void notify(DelegateTask delegateTask) {
		//指定个人任务的办理人，也可以指定组任务的办理人
		String executeInstanceId = delegateTask.getProcessInstanceId();
		
		String nextLevel="Nobody";
		
//		IntershipBeanImpl intershipbeanimpl = new IntershipBeanImpl();
//		//个人任务：通过类去查询数据库，将下一个任务的办理人查询获取，然后通过setAssignee()的方法指定任务的办理人
//		String assignee = intershipbeanimpl.findAssigneeByExecutionID("itoo_internship", executeInstanceId);	
		
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		
		//根据流程实例查询任务列表[
		List<Task> listtask = processEngine.getTaskService().createTaskQuery().executionId(executeInstanceId).list();
		
		if(listtask.size() > 0 && listtask!=null){
			
			String lastassignee = listtask.get(0).getAssignee();   //取得ID
			
			String prcessName = listtask.get(0).getProcessDefinitionId();   
			
			String[] processDefinitionid =  prcessName.split(":"); 
			String processDefId = processDefinitionid[0];          //就去intershipone
					
			List<HistoricTaskInstance> list = processEngine.getHistoryService().createHistoricTaskInstanceQuery().executionId(executeInstanceId).list();
			
			if(list.size()==1){
				//如果任务的个数为1的话，则为第一个角色
				
				String sql = "select * From tb_relation where intershipid='" + processDefId + "'";
				
				Dbhelper dbCourseCurrentadd = new Dbhelper();
				dbCourseCurrentadd.setSql(sql.toString());		 
				List<Map<String,Object>> listmap =  dbCourseCurrentadd.findItemById();
				if(listmap.size() > 0 && listmap!=null){
					 for(int i=0;i<listmap.size();i++){
						 Map<String, Object> m = listmap.get(i);
						 String Next_ASSIGNEE_ROLE_CHILD = (String) m.get("childCode");     //拿到第一个childcode
						 boolean IS_FIRTHROLE = true;                                      //假设它是第一个
							 for(int j=0;j<listmap.size();j++){
								 Map<String, Object> mnext = listmap.get(j);
								 String Next_ASSIGNEE_ROLE_FATHER = (String) mnext.get("fatherCode");      //循环fathercode是否与childcode
								 if(Next_ASSIGNEE_ROLE_CHILD.equals(Next_ASSIGNEE_ROLE_FATHER)){
									 IS_FIRTHROLE = false;                                                 //一定相等则不是
									 continue;
								 }						 
							 }
						 if(IS_FIRTHROLE == true){
							  nextLevel = (String) m.get("fatherCode");
							  break;
						 }
					 }
				}
			}else{
				//如果任务的个数大于1的话，则ID为RoleID
				String sql = "select * From tb_relation where intershipid='" + processDefId + "' and childCode='" + lastassignee + "'";
				
				Dbhelper dbCourseCurrentadd = new Dbhelper();
				 dbCourseCurrentadd.setSql(sql.toString());		 
				 List<Map<String,Object>> listmap =  dbCourseCurrentadd.findItemById();
				 
				 if(listmap.size() > 0 && listmap!=null){
					 Map<String, Object> m = listmap.get(0);   	
					 nextLevel = (String) m.get("fatherCode");
				 }
				 
			}
			
		}
	
		
		System.out.println(nextLevel);	
		
		delegateTask.setAssignee(nextLevel);
	}
 
}

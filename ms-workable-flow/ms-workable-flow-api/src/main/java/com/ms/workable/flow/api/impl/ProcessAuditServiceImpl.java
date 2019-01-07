//package com.ms.workable.flow.api.impl;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.List;
//
//import org.flowable.bpmn.model.BpmnModel;
//import org.flowable.bpmn.model.FlowElement;
//import org.flowable.bpmn.model.Process;
//import org.flowable.bpmn.model.SequenceFlow;
//import org.flowable.bpmn.model.UserTask;
//import org.flowable.engine.ProcessEngine;
//import org.flowable.engine.RepositoryService;
//import org.flowable.engine.RuntimeService;
//import org.flowable.engine.TaskService;
//import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
//import org.flowable.engine.impl.util.CommandContextUtil;
//import org.flowable.engine.runtime.ProcessInstance;
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.boot.util.LambdaSafe.Callback;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.ms.workable.flow.api.IProcessAuditService;
//import com.ms.workable.flow.api.process.ProcessesVariableKey;
//
//import tp.ms.base.rest.resource.logic.Callback;
//import tp.ms.base.rest.resource.vo.AuditInfoVo;
//import tp.ms.base.rest.resource.vo.IPendingAudit;
//
///**
// * <p>
// * 报销表 服务实现类
// * </p>
// *
// * @author stylefeng
// * @since 2017-12-04
// */
//@Service
//public class ProcessAuditServiceImpl implements IProcessAuditService {
//
//    @Autowired
//    private RuntimeService runtimeService;
//
//    @Autowired
//    private TaskService taskService;
//
//    @Autowired
//    private RepositoryService repositoryService;
//
//    @Autowired
//    private ProcessEngine processEngine;
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void startProcess(IPendingAudit auditData, AuditInfoVo info, Callback fn) {
//    	
//    	HashMap<String, Object> variables = new HashMap<>();
//    	variables.put(ProcessesVariableKey.AUDITED_FORM_DATA_OBJECT, auditData);
//    	ProcessInstance process = runtimeService.startProcessInstanceByKey(auditData.getBilltype(), variables);
//    	ExecutionEntity executionEntity = (ExecutionEntity) runtimeService.createProcessInstanceQuery()
//    			.processDefinitionId(process.getId()).singleResult();
//    	executionEntity.getActivityId();
//    	
//    	
//    	
//    	fn.invoke(process.getActivityId());
//    }
//
//    public Object getCurrentFlowElement(String processId) {
////    	ProcessDefinition process = repositoryService.getProcessDefinition(processId);
//    	
//    	BpmnModel bpmn = repositoryService.getBpmnModel(processId);
//    	Process process = bpmn.getProcesses().get(0);
//		Collection<FlowElement> flowElements = process.getFlowElements();
//		for (FlowElement flowElement : flowElements) {
//			if (flowElement instanceof UserTask) {
//				UserTask u = (UserTask) flowElement;
//				List<SequenceFlow> outgoingFlows = u.getOutgoingFlows();
//				System.err.println("outgoingFlows:" + outgoingFlows);
//			}
//			System.err.println(flowElement.getId() + "--->>>>"
//					+ flowElement.getName());
//		
//		}
//		CommandContextUtil.getModelEntityManager();
//		return CommandContextUtil.getTableDataManager();
//    }
//    
//
//}
//

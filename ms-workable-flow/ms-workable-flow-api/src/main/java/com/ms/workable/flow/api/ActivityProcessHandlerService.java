//package com.ms.workable.flow.api;
//
//import java.util.HashMap;
//
//import org.flowable.engine.ProcessEngine;
//import org.flowable.engine.RepositoryService;
//import org.flowable.engine.RuntimeService;
//import org.flowable.engine.TaskService;
//import org.flowable.engine.impl.persistence.entity.ExecutionEntity;
//import org.flowable.engine.runtime.ProcessInstance;
//import org.flowable.engine.runtime.ProcessInstanceQuery;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.ms.workable.flow.api.process.ProcessesVariableKey;
//
//import lombok.val;
//import tp.ms.base.rest.formula.mapper.FormulaValueDaoMapper;
//import tp.ms.base.rest.formula.vo.FormulaObject;
//import tp.ms.base.rest.resource.vo.AuditInfoVo;
//import tp.ms.base.rest.resource.vo.MajorAuditBaseVO;
//import tp.ms.common.bean.utils.SqlHelper;
//
//@Component
//public class ActivityProcessHandlerService {
//
//	@Autowired
//	private FormulaValueDaoMapper fvDao;
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
//    public String start(MajorAuditBaseVO vo, AuditInfoVo info) {
//    	String pkUser = fvDao.queryByFormula(FormulaObject.build()
//    	    	.setFieldCode("pk_user")
//    	    	.setTable(vo.getTable())
//    	    	.setWhere(SqlHelper.build()
//    	    			.appendConditional("userid", "10001TRA021C00000000000000014")
//    	    			.toString()));
//    	HashMap<String, Object> variables = new HashMap<>();
//    	variables.put(ProcessesVariableKey.AUDITED_FORM_DATA_OBJECT, vo);
//    	variables.put("pkUser", vo.getCreator());
//    	variables.put("projectManager", pkUser);
//    	
//    	ProcessInstance process = runtimeService.startProcessInstanceByKey(vo.getBilltype(), variables);
//    	ProcessInstanceQuery instance = runtimeService.createProcessInstanceQuery().deploymentId(vo.getBilltype());
////    	instance.
//    	
//    	ExecutionEntity executionEntity = (ExecutionEntity) runtimeService.createProcessInstanceQuery()
//    			.processDefinitionId(process.getId()).singleResult();
//    	
//		return null;
//    }
//}

package tp.ms.base.rest.process.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.common.engine.api.FlowableObjectNotFoundException;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;
import tp.ms.base.rest.ots.orgs.api.OrgsService;
import tp.ms.base.rest.ots.orgs.entity.MyAdreamOrgelements;
import tp.ms.base.rest.ots.staff.api.StaffService;
import tp.ms.base.rest.ots.staff.entity.MyAdreamStaff;
import tp.ms.base.rest.ots.staff.entity.MyAdreamStaffRelation;
import tp.ms.base.rest.ots.staff.entity.MyAdreamStaffRelationExample;
import tp.ms.base.rest.ots.staff.entity.MyAdreamStaffRelationExample.Criteria;
import tp.ms.base.rest.ots.staff.entity.MyAdreamStaffRelationMapper;
import tp.ms.base.rest.process.api.AuxiliaryService;
import tp.ms.base.rest.process.api.FlowProcessService;
import tp.ms.base.rest.process.api.HomeWaitingMatterService;
import tp.ms.base.rest.process.api.ProcessInformationService;
import tp.ms.base.rest.process.vo.MsWorkableFlowProcessInformation;
import tp.ms.base.rest.process.vo.MyAdreamHomeWaitingMatter;
import tp.ms.base.rest.process.vo.ProcessOperation;
import tp.ms.base.rest.resource.http.Pager;
import tp.ms.base.rest.resource.logic.AuditCall;
import tp.ms.base.rest.resource.vo.AbstractPolyVO;
import tp.ms.base.rest.resource.vo.BillStatus;
import tp.ms.base.rest.resource.vo.MajorAuditBaseVO;
import tp.ms.base.rest.resource.vo.MajorBaseVO;
import tp.ms.common.bean.exception.ADBusiRuntimeException;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.exception.ADServiceCodeException;
import tp.ms.common.bean.support.context.BeanHelperEnv;
import tp.ms.common.bean.support.context.MsEnvContextHolder;
import tp.ms.common.bean.utils.ADate;
import tp.ms.common.bean.utils.ObjectUtilms;
import tp.ms.common.bean.utils.ReplaceNull;
import tp.ms.common.bean.vo.VoStatus;

@Slf4j
@Service
public class FlowProcessServiceImpl implements FlowProcessService {
	

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Autowired
    private BeanHelperEnv beanHelperEnv;
	
//	@Autowired
//	private ManagementService managementService;
//	
	@Autowired
    private RepositoryService repositoryService;

	@Autowired
    private ProcessEngine processEngine;

	@Autowired
    private StaffService staffService;

	@Autowired
    private OrgsService orgsService;

	@Autowired
    private MyAdreamStaffRelationMapper staffRelationMapper;

	@Autowired
	HomeWaitingMatterService waitingMatterService;

	@Autowired
	AuxiliaryService auxiliaryService;
	
	

	//处理提交动作
	@Override
	public MajorAuditBaseVO startSubmit(MajorAuditBaseVO auditVO, AuditCall<?> auditCall) throws ADBusinessException {
		
		try {
			String pkUser = MsEnvContextHolder.getContext().user().getPkUser();
			String billtype = auditVO.getBilltype();
			HashMap<String, Object> variables;
			if(auditVO.getProcessId() == null) {
		        //启动流程
				variables = new HashMap<>();
		        variables.put("pkUser", pkUser);
		        auditCall.getDataMap(variables);
		        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(mergeObtainProcessId(billtype), variables);

		        auditVO.setProcessId(processInstance.getId());
			}
	        
//	        Process process = ProcessDefinitionUtil.getProcess(processInstance.getProcessDefinitionId());
	        
        	variables = new HashMap<String, Object>();
			variables.put("operation", ProcessOperation.PASS.getCode());
	        //启动流程成功后插入一条流程提交记录
			MsWorkableFlowProcessInformation processInformation = new MsWorkableFlowProcessInformation();
			processInformation.setIsCurrentNode("false");
			processInformation.setProcessId(auditVO.getProcessId());
			processInformation.setOperator(MsEnvContextHolder.getContext().user().getUserName());
			processInformation.setOperatorID(MsEnvContextHolder.getContext().user().getPkUser());

			Task task;int index=0;
	        while((task = hasTask(auditVO)) != null) {
	        	index++;
	        	taskService.complete(task.getId(), variables);
				processInformation.setApproveTime(ADate.now().toString());
				processInformation.setTaskId(task.getId());
				processInformation.setNodeName(task.getName());
				processInformation.setPrimaryKey(beanHelperEnv.generateid(MsEnvContextHolder.getContext().user().getPkOrg()));
	        	if(index == 1) {
	        		processInformation.setOperation(ProcessOperation.SUBMIT.getInfo());
					processInformation.setHandlingOpinions("提交");
	        	}else {
					processInformation.setOperation(ProcessOperation.PASS.getInfo());
					processInformation.setHandlingOpinions("通过");
					if(index > 2) {
						continue;
					}
	        	}
				auxiliaryService.handleAfterSubmitInformation(processInformation);
	        }
			//通过后判断实例是否存在
			ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(auditVO.getProcessId()).singleResult();
			if(instance == null) {
				//查询空，表示流程已经结束
				auditVO.setBillStatus(BillStatus.UNDERWAY.getCode());
			}else if(auditVO.getBillStatus() != BillStatus.APPROVING.getCode()) {
				//不为空表示还有节点在流传
				auditVO.setBillStatus(BillStatus.APPROVING.getCode());
				auxiliaryService.submitAfterAround(auditVO);
			}
			if(auditVO.getStatus() != VoStatus.NEW)
				auditVO.setStatus(VoStatus.UPDATED);
			auditCall.call(null);
		}catch(Exception e) {
			log.error(e.getMessage(), e);
			throw new ADBusiRuntimeException(e);
		}
		
		return auditVO;
	}

	public <A extends AbstractPolyVO> A submitProcess(A polyVO, AuditCall<A> AuditCall) throws ADBusinessException{
		MajorBaseVO majorVO = polyVO.getParent(); 
		if(majorVO instanceof MajorAuditBaseVO) {
			MajorAuditBaseVO auditVO = (MajorAuditBaseVO) majorVO;
			auditVO = startSubmit(auditVO, AuditCall);
			polyVO.setParent(majorVO);
			polyVO = AuditCall.call(polyVO);
		}
		return polyVO;
	}

	@Autowired
	ProcessInformationService processInformationService;
	
	@Override
	public <T extends MajorAuditBaseVO> T handleProcess(MsWorkableFlowProcessInformation info, T auditVO) throws ADBusinessException {
		try {			
			String taskId = info.getTaskId();
			Task task = taskService.createTaskQuery().processInstanceId(auditVO.getProcessId()).taskId(taskId).singleResult();
			ProcessOperation operation = ProcessOperation.obtainProcessOperation(Integer.parseInt(info.getOperation()));
			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("operation", operation.getCode());

			taskService.complete(taskId, variables);

			info.setOperatorID(MsEnvContextHolder.getContext().user().getPkUser());
			info.setStatus(VoStatus.NEW);
			info.setOperation(operation.getInfo());
			info.setIsCurrentNode(null);
			switch (operation) {
			case PASS:
				//通过
				int index = 0;
		        while((task = hasTask(auditVO)) != null) {
		        	index++;
	    			taskService.complete(task.getId(), variables);
		        	if(index > 1) {
		        		continue;
		        	}
		        	info.setApproveTime(ADate.now().toString());
		        	info.setTaskId(task.getId());
		        	info.setNodeName(task.getName());
	        		info.setHandlingOpinions("通过");
	    			info.setNodeName(task.getName());
					auxiliaryService.handleAfterSubmitInformation(info);
		        }
				ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(info.getProcessId()).singleResult();
				if(instance == null) {
					//查询空，表示流程已经结束
					auditVO.setBillStatus(BillStatus.UNDERWAY.getCode());
				}else if(auditVO.getBillStatus() != BillStatus.APPROVING.getCode()) {
					//不为空表示还有节点在流传
					auditVO.setBillStatus(BillStatus.APPROVING.getCode());
				}
				break;
			case REJECT:
				//驳回
				//不为空表示还有节点在流传
				if(auditVO.getBillStatus() != BillStatus.REJECT.getCode()) {
					//不为空表示还有节点在流传
					auditVO.setBillStatus(BillStatus.REJECT.getCode());
				}
				break;
			case TRANSFER:
				//转办
				break;
			case COMMUNICATE:
				//沟通
				break;
			case DISCARD:
				//废弃
				break;
			default:
				break;
			}

			if(operation != ProcessOperation.PASS) {
				auditVO.setStatus(VoStatus.UPDATED);
				
				info.setApproveTime(ADate.now().toString());
				info.setTaskId(task.getId());
				info.setNodeName(task.getName());
				info.setOperatorID(MsEnvContextHolder.getContext().user().getPkUser());
				info.setIsCurrentNode(null);
				info.setStatus(VoStatus.NEW);
				try {
					auxiliaryService.handleAfterSubmitInformation(info);
				} catch (ADBusinessException e) {
					throw new ADBusiRuntimeException(e);
				}
			}
			return auditVO;
//			MsWorkableFlowProcessInformation processInformation = info;
//			while( (task = hasTask(auditVO)) != null) {
//
//				processInformation.setApproveTime(ADate.now().toString());
//				processInformation.setOperator(MsEnvContextHolder.getContext().user().getUserName());
//				processInformation.setOperatorID(MsEnvContextHolder.getContext().user().getPkUser());
//				processInformation.setOperation(ProcessOperation.PASS.getInfo());
//				processInformation.setHandlingOpinions("通过");
//				processInformation.setProcessId(auditVO.getProcessId());
//				processInformation.setIsCurrentNode("false");
//				processInformation.setTaskId(task.getId());
//				processInformation.setNodeName(task.getName());
//				handleProcess(processInformation);
//			}
//
		} catch (Exception e) {
			if(e instanceof FlowableObjectNotFoundException) {
				log.error(e.getMessage(), e);
				throw new ADServiceCodeException("流程对象未找到！！");
			}else if(e instanceof ADBusinessException) {
				throw e;
			}else {
				throw new ADBusinessException(e);
			}
		}

		
	}

	@Override
	public Object jumpProcess() {
		return null;
	}

	@Override
	public Object obtainProcessPicture(String processId) throws ADBusinessException {
		

//		List<Task> procs = taskService.createTaskQuery().processDefinitionKey(mergeObtainProcessId("100001C6KOBBBX0000000000010004")).taskAssignee("100001FK4SQC0B00000000000106CU").list();
		
		
        ProcessInstance pi = runtimeService.createProcessInstanceQuery()
        		.processInstanceId(processId)
        		.singleResult();

        //流程走完的不显示图
        if (pi == null) {
            return null;
        }

        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();

        //使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
        String InstanceId = task.getProcessInstanceId();
        List<Execution> executions = runtimeService
                .createExecutionQuery()
                .processInstanceId(InstanceId)
                .list();

        //得到正在执行的Activity的Id
        List<String> activityIds = new ArrayList<>();
        List<String> flows = new ArrayList<>();
        for (Execution exe : executions) {
            List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
            activityIds.addAll(ids);
        }

        try {
	        //获取流程图
	        BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
	        ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
	        ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
	        InputStream in = diagramGenerator.generateDiagram(bpmnModel, "png", activityIds, flows, engconf.getActivityFontName(), engconf.getLabelFontName(), engconf.getAnnotationFontName(), engconf.getClassLoader(), 1.0, false);
	        OutputStream out = null;
	        byte[] buf = new byte[1024];
	        int legth = 0;
        	try {
           		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
           		out = response.getOutputStream();
            	while ((legth = in.read(buf)) != -1) {
        			out.write(buf, 0, legth);
        		}
           	} finally {
            	if (in != null) {
            		in.close();
            	}
            	if (out != null) {
            		out.close();
            	}
           }
       	}catch (Exception e) {
        	throw new ADBusinessException(e);
		}
		return null;
	}

	
	
	
	@SuppressWarnings("unused")
	@Override
	public Object obtainTask(Pager pager) throws ADBusinessException {
		
		PageInfo<MyAdreamHomeWaitingMatter> matters = waitingMatterService.queryProcessByPager(pager);
		if(ObjectUtilms.isNotEmpty(matters.getList())) {
			return new Pager(matters);
		}

		 TaskQuery taskQuery = taskService.createTaskQuery()
				.taskAssignee(MsEnvContextHolder.getContext().user().getPkUser())
				.orderByTaskCreateTime().desc();
		 List<Task> list = taskQuery.listPage(pager.getCurrent(), pager.getShowNum());
	
		 long count = taskQuery.count();
		 
		 
		String processDefinitionId = null, processInstanceId = null;
		List<String> instanceIds = new ArrayList<String>();
		List<MyAdreamHomeWaitingMatter> waitingMatters = new ArrayList<MyAdreamHomeWaitingMatter>();
		for(Task task: list) {
			log.info(task.getProcessInstanceId());
			MyAdreamHomeWaitingMatter waitingMatter = waitingMatterService.queryTaskByProcessId(task.getProcessInstanceId());
			if(waitingMatter == null)
				continue;
			//设置流程节点名称
			waitingMatter.setNodeName(task.getName());
			//设置流程任务ID
			waitingMatter.setTaskId(task.getId());
			waitingMatters.add(waitingMatter);
			instanceIds.add(task.getProcessInstanceId());
			log.info(task.getFormKey());
			log.info(task.getId());
			log.info(task.getExecutionId());
			log.info(task.getName());
			log.info(task.getAssignee());
			log.info(task.getParentTaskId());
			log.info(task.getProcessDefinitionId());
			processDefinitionId = task.getProcessDefinitionId();
			processInstanceId = task.getProcessInstanceId();
			log.info(task.getScopeDefinitionId());
			log.info(task.getScopeId());
			log.info(task.getScopeType());
			log.info(task.getSubScopeId());
			log.info(task.getTaskDefinitionId());
			log.info(task.getTaskDefinitionKey());
			log.info(task.getTenantId());
			log.info(task.getDescription());

			log.info(ReplaceNull.string(task.getProcessVariables()));
			log.info(ReplaceNull.string(task.getTaskLocalVariables()));
			

			ProcessInstance aa = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
//			ProcessInstance bb = runtimeService.createProcessInstanceQuery().processDefinitionId(processDefinitionId).singleResult();

			log.info(aa.getActivityId());
			log.info(aa.getDeploymentId());
			log.info(aa.getBusinessKey());
			log.info(aa.getDescription());
			log.info(ReplaceNull.string(aa.getProcessVariables()));
			log.info(aa.getDescription());
			log.info(aa.getDescription());
		}
		
//		if(ObjectUtilms.isEmptyCollection(waitingMatters)) {
//			throw new ADBusinessException("暂无需要代办的流程");
//		}
		
//		PageInfo<MyAdreamHomeWaitingMatter> result = new PageInfo<MyAdreamHomeWaitingMatter>(waitingMatters);
//		result.setTotal(count);
		
		pager.setTotal(count);
		pager.setData(waitingMatters);
		return pager;
	}

	@Override
	public Object obtainInformation(String processId) throws ADBusinessException {
//		Pager pager = new Pager(null, 1, 1000, 0, null, null);
		List<MsWorkableFlowProcessInformation> informations = processInformationService.queryByProcessId(processId);
//		informations = Arrays.copyOf(informations, informations.length+1);
		MsWorkableFlowProcessInformation information = new MsWorkableFlowProcessInformation();
		Task task = taskService.createTaskQuery().processInstanceId(processId).orderByTaskCreateTime().desc().active().singleResult();
		if(task != null) {
			//节点名称
			String nodeName = task.getName();
			//当前处理人
			String pkUser = task.getAssignee();
			MyAdreamStaff user = staffService.queryByKey(pkUser);
	        //得到岗位的关联信息
	        MyAdreamStaffRelationExample example = new MyAdreamStaffRelationExample();
	        Criteria criteria = example.createCriteria();
	        criteria.andPkUserEqualTo(pkUser)
	        .andIsMainEqualTo((short) 0);
	        List<MyAdreamStaffRelation> relations = staffRelationMapper.selectByExample(example);
	        if(ObjectUtilms.isNotEmpty(relations)) {
		        MyAdreamStaffRelation relation = relations.get(0);
		        
		        MyAdreamOrgelements postInfo = orgsService.queryByKey(relation.getPkPost());
		        //得到处理人岗位
		        String proposerPost = postInfo.getName();
		        information.setOperator(nodeName+":"+proposerPost+"("+user.getUserName()+")");
		        information.setIsCurrentNode("true");
		        information.setOperatorID(pkUser);
		        information.setProcessId(processId);
		        information.setTaskId(task.getId());
		        information.setNodeName(nodeName);
				informations.add(information);
	        }
		}
		return informations;
	}


	@Override
	public Task hasTask(MajorAuditBaseVO auditVO) {
        Task task = taskService.createTaskQuery()
                .processInstanceId(auditVO.getProcessId())
                .taskAssignee(MsEnvContextHolder.getContext().user().getPkUser())
                .singleResult();
		return task;
	}


	@Override
	public ProcessInstance hasProcess(MajorAuditBaseVO auditVO) {
		ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(auditVO.getProcessId()).singleResult();
		return instance;
	}


}

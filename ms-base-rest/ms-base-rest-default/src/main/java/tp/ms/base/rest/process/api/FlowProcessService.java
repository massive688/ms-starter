package tp.ms.base.rest.process.api;

import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;

import tp.ms.base.rest.process.vo.MsWorkableFlowProcessInformation;
import tp.ms.base.rest.resource.http.Pager;
import tp.ms.base.rest.resource.logic.AuditCall;
import tp.ms.base.rest.resource.vo.MajorAuditBaseVO;
import tp.ms.common.bean.exception.ADBusinessException;

public interface FlowProcessService {
	
	public final static String processIdPrefix = "process-";
	
	default String mergeObtainProcessId(String billtype) {return processIdPrefix + billtype;};
	
	
	//审批流程
	<T extends MajorAuditBaseVO> T handleProcess(MsWorkableFlowProcessInformation info, T auditVO) throws ADBusinessException;
	
	//流程跳转
	Object jumpProcess();
	
	//获取用户任务
	Object obtainTask(Pager pager) throws ADBusinessException;

	//获取流程图
	Object obtainProcessPicture(String processId) throws ADBusinessException;

	//获取流程已操作的信息记录
	Object obtainInformation(String processId) throws ADBusinessException;


	Task hasTask(MajorAuditBaseVO auditVO);

	ProcessInstance hasProcess(MajorAuditBaseVO auditVO);

	//启动流程
	MajorAuditBaseVO startSubmit(MajorAuditBaseVO auditVO, AuditCall<?> auditCall) throws ADBusinessException;



//	//启动流程后更新数据
//	Object postSubmitProcess(PolyMyAdreamActivityVO vo) throws ADBusinessException;
//
//	//启动流程后保存数据
//	Object putSubmitBill(PolyMyAdreamActivityVO vo) throws ADBusinessException;
//	
}

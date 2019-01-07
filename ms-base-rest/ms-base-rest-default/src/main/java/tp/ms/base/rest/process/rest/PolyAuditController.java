package tp.ms.base.rest.process.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import tp.ms.base.rest.process.api.PolyAuditService;
import tp.ms.base.rest.process.vo.MsWorkableFlowProcessInformation;
import tp.ms.base.rest.resource.PolyController;
import tp.ms.base.rest.resource.service.IBaseService;
import tp.ms.base.rest.resource.vo.AbstractPolyVO;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.exception.ADServiceCodeException;
import tp.ms.common.bean.result.ResultSupport;


public abstract class PolyAuditController<T extends AbstractPolyVO> extends PolyController<T> {


	@PutMapping("process-submit")
	public Object insertToExamined(@RequestBody T data) throws ADBusinessException {
		PolyAuditService<T> processService = getAuditService();
        //保存业务数据并提交流程
		return ResultSupport.ok(processService.putSubmitBill(data));
	}
	
	private PolyAuditService<T> getAuditService() throws ADServiceCodeException {
		IBaseService<T> service = getService();
		if(service instanceof PolyAuditService) {
			return (PolyAuditService<T>) service;
		}else {
			throw new ADServiceCodeException("Service 类型错误");
		}
	}

	@PostMapping("process-submit")
	public Object updateToExamined(@RequestBody T data) throws ADBusinessException {
		PolyAuditService<T> processService = getAuditService();
        //更新业务数据并提交流程
		return ResultSupport.ok(processService.postSubmitProcess(data));
	}

	@PostMapping("task-handler")
	public Object toProcessTaskExamined(@RequestBody MsWorkableFlowProcessInformation data) throws ADBusinessException {
		PolyAuditService<T> processService = getAuditService();
		return ResultSupport.ok(processService.handleProcess(data));
	}
	
	
	
}


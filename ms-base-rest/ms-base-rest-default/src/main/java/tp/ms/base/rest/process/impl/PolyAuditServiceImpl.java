package tp.ms.base.rest.process.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import tp.ms.base.rest.process.api.FlowProcessService;
import tp.ms.base.rest.process.api.PolyAuditService;
import tp.ms.base.rest.process.vo.MsWorkableFlowProcessInformation;
import tp.ms.base.rest.resource.logic.AuditCall;
import tp.ms.base.rest.resource.service.IMajorService;
import tp.ms.base.rest.resource.service.MajorAuditService;
import tp.ms.base.rest.resource.service.impl.PolyServiceImpl;
import tp.ms.base.rest.resource.vo.AbstractPolyVO;
import tp.ms.base.rest.resource.vo.MajorAuditBaseVO;
import tp.ms.base.rest.resource.vo.MajorBaseVO;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.vo.BaseExample;
import tp.ms.common.bean.vo.VoStatus;

@Slf4j
public abstract class PolyAuditServiceImpl<T extends AbstractPolyVO> extends PolyServiceImpl<T> implements PolyAuditService<T> {

	@Autowired
    private FlowProcessService flowService;

	private <A extends AbstractPolyVO> A submitBillProcess(A polyVO, AuditCall<A> AuditCall) throws ADBusinessException{
		MajorBaseVO majorVO = polyVO.getParent(); 
		if(majorVO instanceof MajorAuditBaseVO) {
			MajorAuditBaseVO auditVO = (MajorAuditBaseVO) majorVO;
			polyVO.setParent(flowService.startSubmit(auditVO, AuditCall));
		}
		return polyVO;
	}
	
	@Override
	public T putSubmitBill(T vo) throws ADBusinessException {
		try {
			submitBillProcess(vo, new AuditCall<T>(){
				@Override
				public T call(T object) {
					log.debug("call function");
					return object;
				}

				@Override
				public Map<String, Object> getDataMap(HashMap<String,Object> map) {
					return PolyAuditServiceImpl.this.getSubmitDataMap(vo, map);
				}
			});
			return super.insert(vo);
		} catch (Exception e) {
			if(e instanceof ADBusinessException) {
				throw e;
			} else {
				throw new ADBusinessException(e);
			}
		}
	}
	
	public abstract Map<String, Object> getSubmitDataMap(T vo, HashMap<String, Object> map);

	@Override
	public T postSubmitProcess(T vo) throws ADBusinessException {
		try {
			submitBillProcess(vo, new AuditCall<T>(){
				@Override
				public T call(T object) {
					log.debug("call function");
					return object;
				}
				@Override
				public Map<String, Object> getDataMap(HashMap<String,Object> map) {
					return PolyAuditServiceImpl.this.getSubmitDataMap(vo, map);
				}
			});
			return super.update(vo);
		} catch (Exception e) {
			if(e instanceof ADBusinessException) {
				throw e;
			}else {
				throw new ADBusinessException(e);
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object handleProcess(MsWorkableFlowProcessInformation processInformation) throws ADBusinessException {
		IMajorService<MajorBaseVO, BaseExample> majorService = getMajorService();
		if(majorService instanceof MajorAuditService) {
			MajorAuditService majorAuditService = (MajorAuditService) majorService;

			MajorAuditBaseVO auditVO = majorAuditService.queryByProcessId(processInformation.getProcessId());

			flowService.handleProcess(processInformation, auditVO);

			if(auditVO.getStatus() == VoStatus.UPDATED)
				return majorAuditService.update(auditVO);

			return auditVO;
		}else {
			throw new ADBusinessException("后端处理服务对象错误！！");
		}
	}


}

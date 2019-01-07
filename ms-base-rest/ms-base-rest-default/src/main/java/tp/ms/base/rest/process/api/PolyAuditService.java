package tp.ms.base.rest.process.api;

import tp.ms.base.rest.process.vo.MsWorkableFlowProcessInformation;
import tp.ms.base.rest.resource.service.IPolyService;
import tp.ms.base.rest.resource.vo.AbstractPolyVO;
import tp.ms.common.bean.exception.ADBusinessException;

public interface PolyAuditService<T extends AbstractPolyVO> extends IPolyService<T> {

	T putSubmitBill(T vo) throws ADBusinessException;

	T postSubmitProcess(T vo) throws ADBusinessException;

	Object handleProcess(MsWorkableFlowProcessInformation processInformation) throws ADBusinessException;

}

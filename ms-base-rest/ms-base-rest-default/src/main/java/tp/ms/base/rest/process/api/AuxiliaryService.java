package tp.ms.base.rest.process.api;

import tp.ms.base.rest.process.vo.MsWorkableFlowProcessInformation;
import tp.ms.base.rest.resource.vo.MajorAuditBaseVO;
import tp.ms.common.bean.exception.ADBusinessException;

public interface AuxiliaryService {

	
	<T extends MajorAuditBaseVO> T submitAfterAround(T myAdreamActivity) throws ADBusinessException;

	void handleAfterSubmitInformation(MsWorkableFlowProcessInformation info) throws ADBusinessException;


}

package tp.ms.base.rest.resource.service;

import tp.ms.base.rest.resource.vo.MajorAuditBaseVO;
import tp.ms.common.bean.vo.BaseExample;

public interface MajorAuditService<T extends MajorAuditBaseVO, E extends BaseExample> extends IMajorService<T, E> {

	MajorAuditBaseVO queryByProcessId(String processId);


}

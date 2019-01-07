package tp.ms.base.rest.resource.service.impl;

import tp.ms.base.rest.resource.service.IMajorService;
import tp.ms.base.rest.resource.vo.MajorBaseVO;
import tp.ms.common.bean.vo.BaseExample;

public abstract class MajorServiceImpl<T extends MajorBaseVO, E extends BaseExample> extends SingleServiceImpl<T, E> implements IMajorService<T, E> {

	
}

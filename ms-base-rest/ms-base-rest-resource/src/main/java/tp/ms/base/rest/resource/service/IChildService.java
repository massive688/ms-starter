package tp.ms.base.rest.resource.service;

import tp.ms.base.rest.resource.vo.ChildBaseVO;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.vo.BaseExample;

public interface IChildService<T extends ChildBaseVO, E extends BaseExample> extends ISingleService<T, E> {


	public T[] queryByParentKey(String parentKey) throws ADBusinessException;


}

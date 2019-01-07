package tp.ms.base.rest.resource.service.ace;

import tp.ms.base.rest.resource.http.Pager;
import tp.ms.base.rest.resource.vo.AbstractPolyVO;
import tp.ms.common.bean.exception.ADBusinessException;

public interface IQueryOperator<T> {

	T query(String key) throws ADBusinessException;

	T query(String key, Class<? extends AbstractPolyVO> queryClass) throws ADBusinessException;

	T[] query(Pager page) throws ADBusinessException;

	T[] query(Pager page, Class<? extends AbstractPolyVO> queryClass);

	
}

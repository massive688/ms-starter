package tp.ms.base.rest.resource.service;

import tp.ms.common.bean.exception.ADBusinessException;

public interface IBaseService<T> extends IQueryService<T> {

	public T insert(T vo) throws ADBusinessException;

	public T update(T vo) throws ADBusinessException;

	public T delete(T vo) throws ADBusinessException;
	
}

package tp.ms.base.rest.resource.service;

import tp.ms.base.rest.resource.http.Pager;
import tp.ms.common.bean.exception.ADBusinessException;


public interface IQueryService<T> {
	
	public T queryByKey(String key) throws ADBusinessException;

	public T[] query(Pager page) throws ADBusinessException;
}

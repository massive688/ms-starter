package tp.ms.base.rest.resource.service;

import java.util.Map;

import tp.ms.base.rest.resource.http.Pager;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.vo.BaseExample;
import tp.ms.common.bean.vo.BaseVO;
import tp.ms.common.data.mybatis.mapper.DaoMapper;

public interface ISingleService<T extends BaseVO, E extends BaseExample> extends IBaseService<T>{

	DaoMapper<T, E> getDao();

	public abstract BaseExample transformToExampleFromPagination(Pager page) throws ADBusinessException;

	public abstract Map<Class<BaseVO>, BaseExample> transformToExampleFromByPolyArray(T[] vos) throws ADBusinessException;

}

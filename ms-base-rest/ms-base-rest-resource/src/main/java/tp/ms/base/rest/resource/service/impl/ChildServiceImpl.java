package tp.ms.base.rest.resource.service.impl;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import tp.ms.base.rest.resource.service.IChildService;
import tp.ms.base.rest.resource.vo.ChildBaseVO;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.vo.BaseExample;

public abstract class ChildServiceImpl<T extends ChildBaseVO, E extends BaseExample> extends SingleServiceImpl<T, E> implements IChildService<T,E>{


	public abstract void setExampleParentKeyCondition(E example, String parentKey);

	@SuppressWarnings("unchecked")
	@Override
	public T[] queryByParentKey(String parentKey) throws ADBusinessException {

		Type type = getClass().getGenericSuperclass();
		//ParameterizedType参数化类型，即泛型  
		ParameterizedType p = (ParameterizedType)type;

		Class<E> c = (Class<E>) p.getActualTypeArguments()[1];
		Class<E> t = (Class<E>) p.getActualTypeArguments()[0];
		E example = null;
		try {
			example = c.newInstance();
			setExampleParentKeyCondition(example, parentKey);
		} catch (InstantiationException | IllegalAccessException e) {
		}
		
		List<T> res = getDao().selectByExample(example);
		T[] ts = (T[])Array.newInstance(t, res.size());
		return 	res.toArray(ts)	;
	}


}

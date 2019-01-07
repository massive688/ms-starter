package tp.ms.base.rest.resource.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.util.ReflectionUtils;

import lombok.extern.slf4j.Slf4j;
import tp.ms.base.rest.resource.service.MajorAuditService;
import tp.ms.base.rest.resource.vo.MajorAuditBaseVO;
import tp.ms.common.bean.vo.BaseExample;

@Slf4j
public abstract class MajorAuditServiceImpl<T extends MajorAuditBaseVO, E extends BaseExample> extends MajorServiceImpl<T, E> implements MajorAuditService<T, E> {



	@Override
	public T queryByProcessId(String processId) {

		Type type = getClass().getGenericSuperclass();
		//ParameterizedType参数化类型，即泛型  
		ParameterizedType p = (ParameterizedType)type;

		@SuppressWarnings("unchecked")
		Class<E> c = (Class<E>) p.getActualTypeArguments()[1];
//		Class<T> t = (Class<T>) p.getActualTypeArguments()[0];

		E example;
		try {
			example = c.newInstance();

			Method createCriteriaMethod = ReflectionUtils.findMethod(example.getClass(), "createCriteria");

			Object criteria = createCriteriaMethod.invoke(example, new Object[0]);
			
			Method andDrEqualToMethod = ReflectionUtils.findMethod(criteria.getClass(), "andDrEqualTo", Integer.class);
			Method andProcessIdEqualToMethod = ReflectionUtils.findMethod(criteria.getClass(), "andProcessIdEqualTo", String.class);
			andDrEqualToMethod.invoke(criteria, 0);
			andProcessIdEqualToMethod.invoke(criteria, processId);
			List<T> list = getDao().selectByExample(example);
			if(list.isEmpty())
				return null;
			return list.get(0);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			log.error(e.getMessage(), e);
			return null;
		}

	}

	
}

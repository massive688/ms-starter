package tp.ms.base.rest.resource.service.strengthen;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ResolvableType;

import tp.ms.base.rest.resource.http.Pager;
import tp.ms.base.rest.resource.service.IChildService;
import tp.ms.base.rest.resource.service.IPolyService;
import tp.ms.base.rest.resource.service.ace.IQueryOperator;
import tp.ms.base.rest.resource.vo.AbstractPolyVO;
import tp.ms.base.rest.resource.vo.ChildBaseVO;
import tp.ms.base.rest.resource.vo.MajorBaseVO;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.utils.ObjectUtilms;
import tp.ms.common.bean.utils.StringUtilms;
import tp.ms.common.bean.vo.BaseExample;
import tp.ms.common.bean.vo.IChildVO;

public class BillQueryOperator<T extends AbstractPolyVO> implements IQueryOperator<T> {


	IPolyService<T> polyService;
	static final Logger log = LoggerFactory.getLogger(BillUpdateBPTemplate.class);


	public BillQueryOperator(IPolyService<T> polyService) {
		this.polyService = polyService;
	}


	@Override
	public T query(String key) throws ADBusinessException {
		if(StringUtilms.isEmpty(key)) {
			throw new ADBusinessException("传入的参数有误或空，检查！！！");
		} 
		Class<? extends AbstractPolyVO> clazz = getPolyVOClassByTypeVariable();
		return query(key, clazz);
	}

	/**
	 * 通过泛型得到实际polyVO的类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Class<? extends AbstractPolyVO> getPolyVOClassByTypeVariable() {
		Type type = getClass().getGenericInterfaces()[0];
		//ParameterizedType参数化类型，即泛型  
		ParameterizedType p = (ParameterizedType)type;
		Type ct = p.getActualTypeArguments()[0]; 
		return (Class<? extends AbstractPolyVO>) resolveVariable((TypeVariable<?>) ct, ResolvableType.forClass(polyService.getClass())).resolve();
	}




	private ResolvableType resolveVariable(TypeVariable<?> typeVariable, ResolvableType contextType) {
		ResolvableType resolvedType;
		if (contextType.hasGenerics()) {
			resolvedType = ResolvableType.forType(typeVariable, contextType);
			if (resolvedType.resolve() != null) {
				return resolvedType;
			}
		}

		ResolvableType superType = contextType.getSuperType();
		if (superType != ResolvableType.NONE) {
			resolvedType = resolveVariable(typeVariable, superType);
			if (resolvedType.resolve() != null) {
				return resolvedType;
			}
		}
		for (ResolvableType ifc : contextType.getInterfaces()) {
			resolvedType = resolveVariable(typeVariable, ifc);
			if (resolvedType.resolve() != null) {
				return resolvedType;
			}
		}
		return ResolvableType.NONE;
	}


	@SuppressWarnings({ "unchecked" })
	@Override
	public T query(String key, Class<? extends AbstractPolyVO> queryClass) throws ADBusinessException {
		MajorBaseVO major = polyService.getMajorService().queryByKey(key);
		if(major == null) {
			return null;
		}
		T obj = null;
		try {
			obj = (T)queryClass.newInstance();
			
			obj.setParent(major);
			
			for(Class<ChildBaseVO> clz : obj.getChildrenClass()) {
				
				IChildService<? extends IChildVO, BaseExample> childService = polyService.getChildService(clz);
				
				ChildBaseVO[] childBaseVo = childService.queryByParentKey(major.getPrimaryKey());
				
				obj.setChildren(clz, childBaseVo);
			}
			
		} catch (InstantiationException | IllegalAccessException | ADBusinessException e) {
		}
		
		return obj;
	}


	@SuppressWarnings("unchecked")
	@Override
	public T[] query(Pager page) throws ADBusinessException {
		Class<? extends AbstractPolyVO> clazz = getPolyVOClassByTypeVariable();
		MajorBaseVO[] majors = polyService.getMajorService().query(page);
		if(ObjectUtilms.isNotEmpty(majors)) {
			List<T> result = new ArrayList<T>();
			for(int i=0; i < majors.length; i++) {
				try {
					
					MajorBaseVO major = majors[i];
					
					T obj = (T)clazz.newInstance();

					obj.setParent(major);
					
					for(Class<ChildBaseVO> clz : obj.getChildrenClass()) {
						
						IChildService<? extends IChildVO, BaseExample> childService = polyService.getChildService(clz);
						
						ChildBaseVO[] childBaseVo = childService.queryByParentKey(major.getPrimaryKey());
						
						obj.setChildren(clz, childBaseVo);
					}
					
					result.add(obj);
				} catch (InstantiationException | IllegalAccessException e) {
				}
			}
			return result.toArray((T[]) Array.newInstance(clazz, result.size()));
		}
		return (T[]) Array.newInstance(clazz, 0);
	}


	@Override
	public T[] query(Pager page, Class<? extends AbstractPolyVO> queryClass) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

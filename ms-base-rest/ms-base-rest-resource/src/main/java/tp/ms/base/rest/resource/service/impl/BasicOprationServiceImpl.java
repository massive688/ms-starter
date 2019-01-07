//package tp.ms.base.rest.resource.service.impl;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.util.ReflectionUtils;
//
//import com.alibaba.fastjson.JSON;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//
//import tp.ms.base.rest.resource.http.Pager;
//import tp.ms.base.rest.resource.service.BasicRefService;
//import tp.ms.base.rest.resource.vo.RefQueryConditionVO;
//import tp.ms.common.bean.http.Symbol;
//import tp.ms.common.bean.utils.ObjectUtilms;
//import tp.ms.common.bean.utils.ReplaceNull;
//import tp.ms.common.bean.utils.StringUtilms;
//import tp.ms.common.bean.vo.BaseExample;
//import tp.ms.common.bean.vo.BaseVO;
//import tp.ms.common.data.mybatis.mapper.DaoMapper;
//
//public abstract class BasicOprationServiceImpl<T extends BaseVO, E extends BaseExample> extends AbstractOprationService<T> implements BasicRefService {
//
//	
//	@Override
//	public Pager executeReference(Pager pager) {
//		PageHelper.startPage(pager.getCurrent(), pager.getShowNum());
//		PageInfo<T> data = new PageInfo<T>(getDao().selectByReference(obtainExampleIfNeed(pager.getCondition())));
//		return new Pager(data);
//	}
//
//	@SuppressWarnings("unchecked")
//	private E obtainExampleIfNeed(Object condition){
//		List<RefQueryConditionVO> cond = analysisCondition(condition);
//		if(ObjectUtilms.isEmpty(cond))
//			return null;
//		
//		Type type = getClass().getGenericSuperclass();
//		//ParameterizedType参数化类型，即泛型  
//		ParameterizedType p = (ParameterizedType)type;
//
//		Class<E> c = (Class<E>) p.getActualTypeArguments()[1];
////		Class<T> t = (Class<T>) p.getActualTypeArguments()[0];
//
//		E example = null;
//		try {
//			example = c.newInstance();
//
//			example = resolveCondition(example, cond);
//			
//		} catch (InstantiationException | IllegalAccessException e) {
//		}
//		
//		return example;
//	}
//
//
//	/**
//	 * 
//	 * @param example 传入查询封装条件
//	 * @param condition 前台出入的条件数组 
//	 * @return example 封装好的条件返回
//	 */
//	private E resolveCondition(E example, List<RefQueryConditionVO> condition) {
//		Method createCriteriaMethod = ReflectionUtils.findMethod(example.getClass(), "createCriteria");
//
//		try {
//		Object criteria = createCriteriaMethod.invoke(example, new Object[0]);
//		
//		for(RefQueryConditionVO cond : condition) {
//			
//				String methodName = mergeJointMethodName(cond);
//				
//				Method criteriaMethod = ReflectionUtils.findMethod(criteria.getClass(), methodName);
//				
//				if(criteriaMethod != null) {
//					
//					Class<?>[] parameterType = criteriaMethod.getParameterTypes();
//					
//					if(parameterType != null && parameterType.length > 0) {
//						Class<?> clazz = parameterType[0];
//						Object value = JSON.parseObject(ReplaceNull.string(cond.getValue()), clazz);
//						criteriaMethod.invoke(criteria, value);
//					}
//				}
//				
//			}
//			
//		} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//		}
//		return example;
//	}
//
//	/**
//	 * 拼接方法名
//	 * @param cond 传入前台解析后的查询定义
//	 * @return
//	 */
//	private String mergeJointMethodName(RefQueryConditionVO cond) {
//		StringBuilder result = new StringBuilder();
//		result.append("and")
//		.append(StringUtilms.translateUpperString(cond.getVariable(), ' '));
//		switch (cond.getSymbol()) {
//		case equal:
//			result.append("EqualTo");
//			break;
//		case like:
//			result.append("Like");
//			break;
//		case greater_than:
//			result.append("GreaterThan");
//			break;
//		case greater_than_equal:
//			result.append("GreaterThanOrEqualTo");
//			break;
//		case less_than:
//			result.append("LessThan");
//			break;
//		case less_than_equal:
//			result.append("LessThanOrEqualTo");
//			break;
//
//		default:
//			break;
//		}
//		return result.toString();
//	}
//
//	/**
//	 * 
//	 * @param condition 分解前台传入的条件
//	 * @return	返回封装好的条件对象组
//	 */
//	private List<RefQueryConditionVO> analysisCondition(Object condition) {
//		/**
//		 * ["pkUser,=,100025AA456009001",] 
//		 */
//		if(ObjectUtilms.isEmpty(condition))
//			return null;
//		String condiString = condition.toString();
//		try {
//			ArrayList<?> list = JSON.parseObject(condiString, ArrayList.class);
//			if(ObjectUtilms.isEmpty(list))
//				return null;
//			
//			List<RefQueryConditionVO> result = new ArrayList<RefQueryConditionVO>();
//			
//			for(Object o : list) {
//				if(o instanceof String ) {
//					String[] arr = o.toString().split(",");
//					if(arr.length != 3) {
//						continue;
//					}
//					RefQueryConditionVO cond = new RefQueryConditionVO();
//					cond.setVariable(arr[0]);
//					cond.setSymbol(JSON.parseObject(arr[1], Symbol.class));
//					cond.setValue(arr[2]);
//					result.add(cond);
//				}
//			}
//			
//			return result;
//			
//			
//		} catch (Exception e) {
//			return null;
//		}
//	}
//}

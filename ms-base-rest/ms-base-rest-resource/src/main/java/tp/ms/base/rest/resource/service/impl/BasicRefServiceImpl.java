package tp.ms.base.rest.resource.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tp.ms.base.rest.resource.http.Pager;
import tp.ms.base.rest.resource.service.BasicRefService;
import tp.ms.base.rest.resource.vo.RefQueryConditionVO;
import tp.ms.common.bean.exception.ADServerException;
import tp.ms.common.bean.http.Symbol;
import tp.ms.common.bean.utils.ObjectUtilms;
import tp.ms.common.bean.utils.StringUtilms;
import tp.ms.common.bean.vo.RefBaseVO;
import tp.ms.common.data.mybatis.mapper.RefMapper;

public abstract class BasicRefServiceImpl<T extends RefBaseVO> implements BasicRefService {

	public abstract RefMapper<T> getDao();

	List<Field> fields;
	
	@Override
	public Pager executeReference(Pager pager) throws ADServerException {
		fields = null;
		PageHelper.startPage(pager.getCurrent(), pager.getShowNum());
		PageInfo<T> data = new PageInfo<T>(getDao().selectByReferenceCondition(obtainConditionIfNeed(pager.getCondition())));
		return new Pager(data);
	}

	/**
	 * 
	 * @param condition 前台传入的条件对象字符串数组
	 * @return 返回拼接好的 AND 后部分查询条件
	 * @throws ADServerException
	 */
	private String obtainConditionIfNeed(Object condition) throws ADServerException {
		//首要分解condition条件对象
		List<RefQueryConditionVO> condVOs = analysisCondition(condition);
		StringBuffer conditionWhere = new StringBuffer();
		if(ObjectUtilms.isNotEmpty(condVOs)) {
			conditionWhere.append(" AND (");
			int cvIndex = 0;
			for(int i=0; i < condVOs.size(); i++) {
				RefQueryConditionVO condvo = condVOs.get(i);
				if(!containsField(condvo.getVariable())) {
					continue;
				}
				if(cvIndex > 0) {
					conditionWhere.append(" OR ");
				}
				cvIndex++;
				conditionWhere.append(mergeJointFieldNameValue(condvo));
			}
			//判断 条件是否有数据添加过
			if(cvIndex == 0) {
				//没有添加过数据则删除开头的 and 并返回空字符串
				conditionWhere.delete(0, conditionWhere.length());
			}else {
				conditionWhere.append(") ");
			}
		}
		return conditionWhere.toString();
	}
	
	//判断查询的字段是否在对应返回数据类型的实体类中
	private boolean containsField(String variable) {
		if(fields == null) {
			Type type = getClass().getGenericSuperclass();
			//ParameterizedType参数化类型，即泛型  
			ParameterizedType p = (ParameterizedType)type;
			@SuppressWarnings("unchecked")
			Class<T> t = (Class<T>) p.getActualTypeArguments()[0];
			fields = Arrays.asList(t.getDeclaredFields()); 
		}
		for(Field field : fields) {
			if(ObjectUtilms.isEqual(field.getName(), variable) 
					|| ObjectUtilms.isEqual(StringUtilms.translateLowerString(field.getName(), '_'), variable)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 拼接WHERE AND 后 查询条件 
	 * @param cond 传入前台解析后的查询定义
	 * @return
	 */
	private String mergeJointFieldNameValue(RefQueryConditionVO cond) {
		StringBuilder result = new StringBuilder();
		result.append("(")
		.append(StringUtilms.translateLowerString(cond.getVariable(), '_'));
		switch (cond.getSymbol()) {
		case equal:
			result.append(" = ");
			break;
		case like:
			result.append(" like ");
			break;
		case greater_than:
			result.append(" > ");
			break;
		case greater_than_equal:
			result.append(" >= ");
			break;
		case less_than:
			result.append(" < ");
			break;
		case less_than_equal:
			result.append(" <= ");
			break;

		default:
			break;
		}
		switch (cond.getSymbol()) {
		case like:
			result.append("'%"+cond.getValue()+"%'");
			break;
		case equal:
		case greater_than:
		case greater_than_equal:
		case less_than:
		case less_than_equal:
		default:
			result.append("'"+cond.getValue()+"'");
			break;
		}
		result.append(")");
		return result.toString();
	}


	/**
	 * 
	 * @param condition 分解前台传入的条件
	 * @return	返回封装好的条件对象组
	 * @throws ADServerException 
	 */
	private List<RefQueryConditionVO> analysisCondition(Object condition) throws ADServerException {
		/**
		 * ["pkUser,=,100025AA456009001",] 
		 */
		//空字符串后 "" 这样的字符串直接返回null
		if(ObjectUtilms.isEmpty(condition))
			return null;
		String condiString = condition.toString();

		ArrayList<?> list = JSON.parseObject(condiString, ArrayList.class);
		//[] 空数组字符串的方式直接返回
		if(ObjectUtilms.isEmpty(list))
			return null;
		
		List<RefQueryConditionVO> result = new ArrayList<RefQueryConditionVO>();
		
		for(Object o : list) {
			if(o instanceof String ) {
				String[] arr = o.toString().split(",");
				if(arr.length != 3 ) {
					continue;
				}
				/*
				 * 判断是否有查询的条件字段
				 * 查询的条件字段在billTemplate模块中定义 （定义的模板字段refQueryCondition）
				 * 字段信息是与返回的实体封装数据类中的字段相同。不是数据库中的表字段，类字段
				 * 后续会解析成表对应的字段
				 */
				if(StringUtilms.isEmpty(arr[0])) {
					throw new ADServerException("参照未配置查询字段信息，请联系维护");
				}
				RefQueryConditionVO cond = new RefQueryConditionVO();
				cond.setVariable(arr[0]);
				cond.setSymbol(JSON.parseObject(arr[1], Symbol.class));
				cond.setValue(arr[2]);
				result.add(cond);
			}
		}
		
		return result;
		
	}
}

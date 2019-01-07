package tp.ms.base.rest.refinfo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;

import lombok.val;
import tp.ms.base.rest.refinfo.api.BaseRefInfoService;
import tp.ms.base.rest.refinfo.mapper.MsBaseRefInfoMapper;
import tp.ms.base.rest.refinfo.vo.BaseRefInfoVO;
import tp.ms.base.rest.refinfo.vo.MyBaseRefInfo;
import tp.ms.base.rest.refinfo.vo.MyBaseRefInfoExample;
import tp.ms.base.rest.refinfo.vo.MyBaseRefInfoExample.Criteria;
import tp.ms.base.rest.refinfo.vo.RefInfoQueryVO;
import tp.ms.base.rest.resource.http.Pager;
import tp.ms.base.rest.resource.service.impl.SingleServiceImpl;
import tp.ms.base.rest.resource.vo.RefQueryConditionVO;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.exception.AExceptionUtils;
import tp.ms.common.bean.http.Symbol;
import tp.ms.common.bean.result.DataSourceChoose;
import tp.ms.common.bean.result.TranslateRef;
import tp.ms.common.bean.support.context.SpringContextHolder;
import tp.ms.common.bean.utils.ObjectUtilms;
import tp.ms.common.bean.utils.StringUtilms;
import tp.ms.common.bean.vo.BaseExample;
import tp.ms.common.bean.vo.BaseVO;
import tp.ms.common.data.mybatis.annotation.SqlSessionKey;
import tp.ms.common.data.mybatis.annotation.TargetSqlSession;
import tp.ms.common.data.mybatis.mapper.DaoMapper;

@Service
@TargetSqlSession(SqlSessionKey.CS6304)
public class BaseRefInfoServiceImpl extends SingleServiceImpl<MyBaseRefInfo, MyBaseRefInfoExample> implements BaseRefInfoService{

	@Autowired
	MsBaseRefInfoMapper dao;
	
	@Override
	public DaoMapper<MyBaseRefInfo, MyBaseRefInfoExample> getDao() {
		return dao;
	}

	@Override
	public Map<Class<BaseVO>, BaseExample> transformToExampleFromByPolyArray(MyBaseRefInfo[] vos)
			throws ADBusinessException {
		return null;
	}

	@Override
	public String getRefClass(String translate) {
		val example = new MyBaseRefInfoExample();
		MyBaseRefInfoExample.Criteria criteria = example.createCriteria();
		criteria.andRefClassEqualTo(translate);
		example.or(criteria);
		MyBaseRefInfoExample.Criteria criteria2 = example.createCriteria();
		criteria2.andRefCodeEqualTo(translate);
		example.or(criteria2);
		MyBaseRefInfoExample.Criteria criteria3 = example.createCriteria();
		criteria3.andRefNameEqualTo(translate);
		example.or(criteria3);
		List<MyBaseRefInfo> refs = dao.selectByExample(example);
		if(refs.isEmpty())
			AExceptionUtils.unSupported("translate ref Wrongful"); 
		return refs.get(0).getRefClass();
	}

	@Override
	public BaseExample transformToExampleFromPagination(Pager page)
			throws  ADBusinessException {
		return null;
	}

	@Override
	public void setQueryExample(MyBaseRefInfoExample example, String key) {
		Criteria criteria = example.createCriteria();
		criteria.andDrEqualTo((short) 0);
		if(key != null)
			criteria.andPkBaseRefInfoEqualTo(key);
	}

	@Override
	public List<BaseRefInfoVO> executeQueryByPager(String refid, Pager data) {
		String className = getRefClass(refid);
		Class<?> clz = null;
		try {
			clz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			AExceptionUtils.unSupported("translate ref class Wrongful");
		}
		TranslateRef translate = (TranslateRef) SpringContextHolder.getBean(clz);
		PageHelper.startPage(data.getCurrent(), data.getShowNum());
		RefInfoQueryVO queryVO = new RefInfoQueryVO();
		queryVO.setCondition(ObjectUtilms.isEmpty(data.getCondition()) ? translate.getWherePart() : translate.getWherePart() + obtainConditionIfNeed(translate, data.getCondition()));
		queryVO.setCode(translate.getCodeField());
		queryVO.setName(translate.getNameField());
		queryVO.setPk(translate.getPkField());
		queryVO.setTable(translate.getTable());
		if(translate instanceof DataSourceChoose) {
			DataSourceChoose dsChoose = (DataSourceChoose) translate;
			queryVO.setDsKey(dsChoose.getSessionKey());
		}
		
		return dao.executeByTranslateQuery(queryVO);
	}

	private String obtainConditionIfNeed(TranslateRef translate, Object condition) {
		List<RefQueryConditionVO> condVOs = analysisCondition(translate, condition);
		StringBuffer conditionWhere = new StringBuffer();
		if(ObjectUtilms.isNotEmpty(condVOs)) {
			conditionWhere.append(" AND (");
			for(int i=0; i < condVOs.size(); i++) {
				RefQueryConditionVO condvo = condVOs.get(i);
				if(i > 0) {
					conditionWhere.append(" OR ");
				}
				conditionWhere.append(mergeJointFieldNameValue(condvo));
			}
			conditionWhere.append(") ");
		}
		return conditionWhere.toString();
	}
	

	/**
	 * @param cond 传入前台解析后的查询定义
	 * @return
	 */
	private String mergeJointFieldNameValue(RefQueryConditionVO cond) {
		StringBuilder result = new StringBuilder();
		result.append("(")
		.append(cond.getVariable());
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
	 * @param condition2 
	 * @return	返回封装好的条件对象组
	 */
	private List<RefQueryConditionVO> analysisCondition(TranslateRef translate, Object condition) {
		/**
		 * ["pkUser,=,100025AA456009001",] 
		 */
		if(ObjectUtilms.isEmpty(condition))
			return null;
		String condiString = condition.toString();
		try {
			ArrayList<?> list = JSON.parseObject(condiString, ArrayList.class);
			if(ObjectUtilms.isEmpty(list))
				return null;
			
			List<RefQueryConditionVO> result = new ArrayList<RefQueryConditionVO>();
			
			for(Object o : list) {
				if(o instanceof String ) {
					String[] arr = o.toString().split(",");
					if(arr.length != 3) {
						continue;
					}
					if(StringUtilms.isEmpty(arr[0])) {
						RefQueryConditionVO cond = new RefQueryConditionVO();
						cond.setVariable(translate.getCodeField());
						cond.setSymbol(JSON.parseObject(arr[1], Symbol.class));
						cond.setValue(arr[2]);
						result.add(cond);
						RefQueryConditionVO cond2 = new RefQueryConditionVO();
						cond2.setVariable(translate.getNameField());
						cond2.setSymbol(JSON.parseObject(arr[1], Symbol.class));
						cond2.setValue(arr[2]);
						result.add(cond2);
						break;
					}else {
						RefQueryConditionVO cond = new RefQueryConditionVO();
						cond.setVariable(arr[0]);
						cond.setSymbol(JSON.parseObject(arr[1], Symbol.class));
						cond.setValue(arr[2]);
						result.add(cond);
					}
				}
			}
			
			return result;
			
			
		} catch (Exception e) {
			return null;
		}
	}
}

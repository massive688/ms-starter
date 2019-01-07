package tp.ms.base.rest.resource.service.impl;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;

import tp.ms.base.rest.resource.http.Pager;
import tp.ms.base.rest.resource.service.ISingleService;
import tp.ms.base.rest.resource.service.strengthen.SingleDeleteBPTemplate;
import tp.ms.base.rest.resource.service.strengthen.SingleInsertBPTemplate;
import tp.ms.base.rest.resource.service.strengthen.SingleTransferTool;
import tp.ms.base.rest.resource.service.strengthen.SingleUpdateBPTemplate;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.utils.ObjectUtilms;
import tp.ms.common.bean.vo.BaseExample;
import tp.ms.common.bean.vo.BaseVO;
import tp.ms.common.data.source.annotation.TransactionalKey;

public abstract class SingleServiceImpl<T extends BaseVO, E extends BaseExample> extends AbstractOprationService<T> implements ISingleService<T, E> {

	private static final Logger logger = LoggerFactory.getLogger(SingleServiceImpl.class);

	public abstract void setQueryExample(E example, String key);
	
	@Override
	@Transactional(value=TransactionalKey.JTA, rollbackFor= {Exception.class, ADBusinessException.class})
	public T insert(T vos) throws ADBusinessException {
		SingleInsertBPTemplate<T,E> opration = new SingleInsertBPTemplate<T,E>(this);
		/**
		 * 添加执行插入 前后规则或插件
		 */
		addBeforeRule(opration.getAroundProcesser());
		addAfterRule(opration.getAroundProcesser());
		return (T) opration.insert(vos);
	}



	@Override
	@Transactional(value=TransactionalKey.JTA, rollbackFor= {Exception.class, ADBusinessException.class})
	public T update(T vos) throws ADBusinessException {
		SingleTransferTool<T, E> transTool = new SingleTransferTool<T, E>(vos, this);
		// 补全前台VO
		T fullBills = transTool.getClientFullInfoBaseVO();
		// 获得修改前vo
		T originBills = transTool.getOriginBaseVO();
		SingleUpdateBPTemplate<T,E> opration = new SingleUpdateBPTemplate<T,E>(this);
		addBeforeRule(opration.getAroundProcesser());
		addAfterRule(opration.getAroundProcesser());
		return (T) opration.update(fullBills, originBills);
	}
	



	@SuppressWarnings("unchecked")
	@Override
	public T queryByKey(String key) throws ADBusinessException {
		logger.info("执行 queryById 数据插入!! param_value:{},  ||开始", key);
		Type type = getClass().getGenericSuperclass();
		//ParameterizedType参数化类型，即泛型  
		ParameterizedType p = (ParameterizedType)type;
		Class<E> c = (Class<E>) p.getActualTypeArguments()[1];
		E example = null;
		try {
			example = c.newInstance();
			setQueryExample(example, key);
		}catch (Exception e) {
			throw new ADBusinessException(e);
		}
		logger.info("执行 queryById 数据插入!! ||结束");
		List<T> list = getDao().selectByExample(example);
		if(ObjectUtilms.isNotEmpty(list))
			return list.get(0);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T[] query(Pager pager) throws ADBusinessException {
		logger.info("执行 search 数据插入!! param_class:{}, param_page_value:{},  ||开始", pager.getCondition(), JSON.toJSONString(pager));
		PageHelper.startPage(pager.getCurrent(), pager.getShowNum());
		Type type = getClass().getGenericSuperclass();
		//ParameterizedType参数化类型，即泛型  
		ParameterizedType p = (ParameterizedType)type;
		Class<E> t = (Class<E>) p.getActualTypeArguments()[0];
		Class<E> c = (Class<E>) p.getActualTypeArguments()[1];
		E example = null;
		try {
			example = c.newInstance();
			setQueryExample(example, null);
		}catch (Exception e) {
			throw new ADBusinessException(e);
		}
		logger.info("执行 search 数据插入!! param_class:{} ||结束", pager.getCondition());
		List<T> list = getDao().selectByExample(example);
		if(ObjectUtilms.isNotEmpty(list)) {
			T[] ts = (T[]) Array.newInstance(t, list.size());
			return list.toArray(ts);
		}
		return null;
	}


	@Override
	@Transactional(value=TransactionalKey.JTA, rollbackFor= {Exception.class, ADBusinessException.class})
	public T delete(T vos) throws ADBusinessException {
		logger.info("执行 search 数据插入!! param_class:{}, param_page_value:{},  ||开始", vos.getClass(), JSON.toJSONString(vos));
		SingleTransferTool<T, E> transTool = new SingleTransferTool<T, E>(vos, this);
		// 补全前台VO
		T fullBills = transTool.getClientFullInfoBaseVO();
		// 获得修改前vo
		T originBills = transTool.getOriginBaseVO();
		SingleDeleteBPTemplate<T,E> opration = new SingleDeleteBPTemplate<T,E>(this);
		addBeforeRule(opration.getAroundProcesser());
		addAfterRule(opration.getAroundProcesser());
		logger.info("执行 search 数据插入!! param_class:{} ||结束", vos.getClass());
		T returnVos = (T) opration.delete(fullBills, originBills);
		return returnVos;
	}


}

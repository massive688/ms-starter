package tp.ms.base.rest.resource.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import tp.ms.base.rest.resource.http.Pager;
import tp.ms.base.rest.resource.service.IPolyService;
import tp.ms.base.rest.resource.service.strengthen.BillDeleteBPTemplate;
import tp.ms.base.rest.resource.service.strengthen.BillInsertBPTemplate;
import tp.ms.base.rest.resource.service.strengthen.BillQueryTemplate;
import tp.ms.base.rest.resource.service.strengthen.BillTransferTool;
import tp.ms.base.rest.resource.service.strengthen.BillUpdateBPTemplate;
import tp.ms.base.rest.resource.vo.AbstractPolyVO;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.data.source.annotation.TransactionalKey;

@Slf4j
public abstract class PolyServiceImpl<T extends AbstractPolyVO> extends AbstractOprationService<T> implements IPolyService<T> {

	@Override
	@Transactional(value=TransactionalKey.JTA, rollbackFor= {Exception.class, ADBusinessException.class})
	public T insert(T vos) throws ADBusinessException {
		BillInsertBPTemplate<T> opration = new BillInsertBPTemplate<T>(this);
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
		BillTransferTool<T> transTool = new BillTransferTool<T>(vos, this);
		// 补全前台VO
		T fullBills = transTool.getClientFullInfoBill();
		// 获得修改前vo
		T originBills = transTool.getOriginBill();
		BillUpdateBPTemplate<T> opration = new BillUpdateBPTemplate<T>(this);
		addBeforeRule(opration.getAroundProcesser());
		addAfterRule(opration.getAroundProcesser());
		return (T) opration.update(fullBills, originBills);
	}
	



	@Override
	public T queryByKey(String key) throws ADBusinessException {
		log.info("执行 queryById 数据查询!! param_value:{},  ||开始", key);
		BillQueryTemplate<T> queryTemplate = new BillQueryTemplate<T>(this);
		log.info("执行 queryById 数据插入!! ||结束");
		return (T) queryTemplate.query(key);
	}
	
	@Override
	public T[] query(Pager page) throws ADBusinessException {
		log.info("执行 search 数据插入!! param_class:{}, param_page_value:{},  ||开始", page.getClass(), JSON.toJSONString(page));
		BillQueryTemplate<T> queryTemplate = new BillQueryTemplate<T>(this);
		log.info("执行 search 数据插入!! param_class:{} ||结束", page.getClass());
		return (T[]) queryTemplate.queryByPager(page);
	}


	@Override
	@Transactional(value=TransactionalKey.JTA, rollbackFor= {Exception.class, ADBusinessException.class})
	public T delete(T vos) throws ADBusinessException {
		log.info("执行 search 数据插入!! param_class:{}, param_page_value:{},  ||开始", vos.getClass(), JSON.toJSONString(vos));
		BillTransferTool<T> transTool = new BillTransferTool<T>(vos, this);
		// 补全前台VO
		T fullBills = transTool.getClientFullInfoBill();
		// 获得修改前vo
		T originBills = transTool.getOriginBill();
		BillDeleteBPTemplate<T> opration = new BillDeleteBPTemplate<T>(this);
		addBeforeRule(opration.getAroundProcesser());
		addAfterRule(opration.getAroundProcesser());
		log.info("执行 search 数据插入!! param_class:{} ||结束", vos.getClass());
		return (T) opration.delete(fullBills, originBills);
	}
	

}

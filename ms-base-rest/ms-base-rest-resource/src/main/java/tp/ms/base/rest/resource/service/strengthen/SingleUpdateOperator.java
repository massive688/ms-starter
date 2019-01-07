package tp.ms.base.rest.resource.service.strengthen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import tp.ms.base.rest.resource.service.ISingleService;
import tp.ms.base.rest.resource.service.ace.ICompareOperator;
import tp.ms.common.bean.utils.ObjectUtilms;
import tp.ms.common.bean.vo.BaseExample;
import tp.ms.common.bean.vo.BaseVO;

public class SingleUpdateOperator<T extends BaseVO, E extends BaseExample> implements ICompareOperator<T> {

	private static final Logger log = LoggerFactory.getLogger(SingleUpdateOperator.class);
	
	ISingleService<T,E> service;
	
	public SingleUpdateOperator(ISingleService<T,E> service) {
		this.service = service;
	}
	
	@Override
	public T operate(T returnVOs, T originBills) {
		log.info(JSON.toJSONString(returnVOs));
		if(ObjectUtilms.isNotEmpty(returnVOs)) {
			service.getDao().updateByPrimaryKey(returnVOs);
		}
		return returnVOs;
	}

}

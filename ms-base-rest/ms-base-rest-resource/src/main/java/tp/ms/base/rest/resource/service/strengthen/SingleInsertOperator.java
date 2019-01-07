package tp.ms.base.rest.resource.service.strengthen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import tp.ms.base.rest.resource.service.ISingleService;
import tp.ms.base.rest.resource.service.ace.IOperator;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.utils.ObjectUtilms;
import tp.ms.common.bean.vo.BaseExample;
import tp.ms.common.bean.vo.BaseVO;

public class SingleInsertOperator<T extends BaseVO, E extends BaseExample> implements IOperator<T> {

	private static final Logger log = LoggerFactory.getLogger(SingleInsertOperator.class);
	
	ISingleService<T,E> service;
	
	public SingleInsertOperator(ISingleService<T,E> service) {
		this.service = service;
	}

	@Override
	public T operate(T vos) throws ADBusinessException {
		log.info(JSON.toJSONString(vos));
		if(ObjectUtilms.isNotEmpty(vos)) {
			service.getDao().insert(vos);
		}
		return vos;
	}

	
}

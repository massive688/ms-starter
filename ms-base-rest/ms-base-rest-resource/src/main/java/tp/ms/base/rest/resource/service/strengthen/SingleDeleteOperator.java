package tp.ms.base.rest.resource.service.strengthen;

import tp.ms.base.rest.resource.service.ISingleService;
import tp.ms.base.rest.resource.service.ace.ICompareOperator;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.vo.BaseExample;
import tp.ms.common.bean.vo.BaseVO;

public class SingleDeleteOperator<T extends BaseVO, E extends BaseExample> implements ICompareOperator<T> {

	private ISingleService<T,E> service;

	public SingleDeleteOperator(ISingleService<T,E> service) {
		this.service = service;
	}

	@Override
	public T operate(T returnVOs, T originBills) throws ADBusinessException {
		returnVOs.setDr(1);
		service.getDao().updateByPrimaryKey(returnVOs);
		return returnVOs;
	}

}

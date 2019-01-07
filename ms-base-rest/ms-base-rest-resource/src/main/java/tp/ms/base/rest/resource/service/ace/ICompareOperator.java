package tp.ms.base.rest.resource.service.ace;

import tp.ms.common.bean.exception.ADBusinessException;

public interface ICompareOperator<E> {

	E operate(E returnVOs, E originBills) throws ADBusinessException;

}

package tp.ms.base.rest.resource.service.ace;

import tp.ms.common.bean.exception.ADBusinessException;

public interface IOperator<T> {

	 /*
	   * 针对业务对象进行处理
	   * 
	   * @param vos 要处理的业务对象
	   * @return 处理后的业务对象，此对象的内容可能已经改变，也可能没有改变
	 * @throws ADBusinessException  业务处理异常
	   */
	  T operate(T vos) throws ADBusinessException;
}

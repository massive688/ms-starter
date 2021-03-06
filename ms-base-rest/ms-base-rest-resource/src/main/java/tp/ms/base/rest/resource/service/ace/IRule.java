package tp.ms.base.rest.resource.service.ace;

import tp.ms.common.bean.exception.ADBusinessException;

public interface IRule<T> {

	 /*
	   * 对传入的对象进行业务处理
	   * 
	   * @param vo 传入的要处理的对象
	 * @throws ADBusinessException 业务处理异常
	   */
	  void process(T vo) throws ADBusinessException;
}

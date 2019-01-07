package tp.ms.base.rest.resource.service.ace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tp.ms.common.bean.exception.ADBusinessException;

/**
 * 更新操作
 * @author ms
 *
 * @param <T> 指定泛型的类型
 */
public class CompareOperatorTemplate<T> {

	private static final Logger log = LoggerFactory.getLogger(CompareOperatorTemplate.class);

	/**
	 * 业务处理实例
	 */
	private ICompareOperator<T> operaor;

	/**
	 * 简单规则处理器
	 */
	private CompareAroundProcesser<T> processer;

	/*
	 * 业务处理的模板类构造函数
	 */
	public CompareOperatorTemplate() {
		this.processer = new CompareAroundProcesser<T>();
	}

	public CompareOperatorTemplate(ICompareOperator<T> operator) {
		this.processer = new CompareAroundProcesser<T>();
		this.operaor = operator;
	}

	public T operate(T vos, T originBills) throws ADBusinessException {
		T returnVOs = vos;
		returnVOs = this.processer.before(returnVOs, originBills);
		log.info("业务处理前执行业务规则"); /*-=notranslate=-*/

		returnVOs = this.operaor.operate(returnVOs, originBills);
		log.info("业务处理"); /*-=notranslate=-*/

		returnVOs = this.processer.after(returnVOs, originBills);
		log.info("业务处理后执行业务规则"); /*-=notranslate=-*/

		return returnVOs;
	}

	public CompareAroundProcesser<T> getAroundProcesser() {
		return this.processer;
	}
}

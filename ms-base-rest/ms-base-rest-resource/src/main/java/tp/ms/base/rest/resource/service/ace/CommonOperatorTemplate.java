package tp.ms.base.rest.resource.service.ace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tp.ms.common.bean.exception.ADBusinessException;
/**
 * 新增操作
 * @author ms
 *
 * @param <T> 指定泛型的类型
 */
public class CommonOperatorTemplate<T> {
	
	private static final Logger log = LoggerFactory.getLogger(CommonOperatorTemplate.class);

	/**
	 * 业务处理实例
	 */
	private IOperator<T> operaor;

	/**
	 * 简单规则处理器
	 */
	private CommonAroundProcesser<T> processer;

	/*
	 * 业务处理的模板类构造函数
	 * 
	 */
	public CommonOperatorTemplate() {
		this.processer = new CommonAroundProcesser<T>();
	}

	public CommonOperatorTemplate(IOperator<T> operator) {
		this.processer = new CommonAroundProcesser<T>();
		this.operaor = operator;
	}

	public T operate(T vos) throws ADBusinessException {
		T returnVOs = vos;
		returnVOs = this.processer.before(vos);
		log.info("业务处理前执行业务规则"); /*-=notranslate=-*/
		if (this.operaor != null) {
			returnVOs = this.operaor.operate(returnVOs);
			log.info("业务处理"); /*-=notranslate=-*/
		}
		returnVOs = this.processer.after(returnVOs);
		log.info("业务处理后执行业务规则"); /*-=notranslate=-*/
		return returnVOs;
	}

	public CommonAroundProcesser<T> getAroundProcesser() {
		return this.processer;
	}

}

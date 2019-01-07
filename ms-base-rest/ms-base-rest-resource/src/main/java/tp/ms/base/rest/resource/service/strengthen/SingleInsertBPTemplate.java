package tp.ms.base.rest.resource.service.strengthen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tp.ms.base.rest.resource.service.ISingleService;
import tp.ms.base.rest.resource.service.ace.CommonAroundProcesser;
import tp.ms.base.rest.resource.service.ace.CommonOperatorTemplate;
import tp.ms.base.rest.resource.service.ace.IOperator;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.vo.BaseExample;
import tp.ms.common.bean.vo.BaseVO;

public class SingleInsertBPTemplate<T extends BaseVO, E extends BaseExample> {
	
	static final Logger log = LoggerFactory.getLogger(SingleInsertBPTemplate.class);

	/**
	 * 单据新增规则处理器
	 */
	private CommonOperatorTemplate<T> template;

	public SingleInsertBPTemplate(ISingleService<T, E> service) {
		IOperator<T> operator = new SingleInsertOperator<T,E>(service);
		this.template = new CommonOperatorTemplate<T>(operator);
	}

	public T insert(T vos) throws ADBusinessException {
		return this.template.operate(vos);
	}

	/**
	 * 获取单据新增规则处理器。调用者可以据此增加前、后规则
	 * 
	 * @return 单据新增规则处理器
	 */
	public CommonAroundProcesser<T> getAroundProcesser() {
		return this.template.getAroundProcesser();
	}


}

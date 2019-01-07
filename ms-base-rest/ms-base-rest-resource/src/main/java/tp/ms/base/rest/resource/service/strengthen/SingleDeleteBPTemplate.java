package tp.ms.base.rest.resource.service.strengthen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tp.ms.base.rest.resource.service.ISingleService;
import tp.ms.base.rest.resource.service.ace.CompareAroundProcesser;
import tp.ms.base.rest.resource.service.ace.CompareOperatorTemplate;
import tp.ms.base.rest.resource.service.ace.ICompareOperator;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.vo.BaseExample;
import tp.ms.common.bean.vo.BaseVO;

public class SingleDeleteBPTemplate<T extends BaseVO, E extends BaseExample> {


	static final Logger log = LoggerFactory.getLogger(SingleDeleteBPTemplate.class);

	/**
	 * 单据新增规则处理器
	 */
	private CompareOperatorTemplate<T> template;

	public SingleDeleteBPTemplate(ISingleService<T,E> service) {
		ICompareOperator<T> operator = new SingleDeleteOperator<T, E>(service);
		this.template = new CompareOperatorTemplate<T>(operator);
	}

	/**
	 * 获取单据新增规则处理器。调用者可以据此增加前、后规则
	 * 
	 * @return 单据新增规则处理器
	 */
	public CompareAroundProcesser<T> getAroundProcesser() {
		return this.template.getAroundProcesser();
	}

	public T delete(T fullBills, T originBills) throws ADBusinessException {
		return this.template.operate(fullBills, originBills);
	}
}

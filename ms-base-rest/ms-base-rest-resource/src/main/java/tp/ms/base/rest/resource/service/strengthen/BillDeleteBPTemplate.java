package tp.ms.base.rest.resource.service.strengthen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tp.ms.base.rest.resource.service.IPolyService;
import tp.ms.base.rest.resource.service.ace.CompareAroundProcesser;
import tp.ms.base.rest.resource.service.ace.CompareOperatorTemplate;
import tp.ms.base.rest.resource.service.ace.ICompareOperator;
import tp.ms.base.rest.resource.vo.AbstractPolyVO;
import tp.ms.common.bean.exception.ADBusinessException;

public class BillDeleteBPTemplate<T extends AbstractPolyVO> {


	static final Logger log = LoggerFactory.getLogger(BillDeleteBPTemplate.class);

	/**
	 * 单据新增规则处理器
	 */
	private CompareOperatorTemplate<T> template;

	public BillDeleteBPTemplate(IPolyService<T> polyService) {
		ICompareOperator<T> operator = new BillDeleteOperator<T>(polyService);
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

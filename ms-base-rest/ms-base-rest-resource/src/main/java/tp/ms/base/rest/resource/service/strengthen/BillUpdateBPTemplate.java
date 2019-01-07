package tp.ms.base.rest.resource.service.strengthen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tp.ms.base.rest.resource.service.IPolyService;
import tp.ms.base.rest.resource.service.ace.CompareAroundProcesser;
import tp.ms.base.rest.resource.service.ace.CompareOperatorTemplate;
import tp.ms.base.rest.resource.service.ace.ICompareOperator;
import tp.ms.base.rest.resource.service.impl.PolyServiceImpl;
import tp.ms.base.rest.resource.vo.AbstractPolyVO;
import tp.ms.common.bean.exception.ADBusinessException;

public class BillUpdateBPTemplate<E extends AbstractPolyVO> {

	IPolyService<E> polyService;
	static final Logger log = LoggerFactory.getLogger(BillUpdateBPTemplate.class);

	/**
	 * 单据新增规则处理器
	 */
	private CompareOperatorTemplate<E> template;

	public BillUpdateBPTemplate(PolyServiceImpl<E> polyService) {
		this.polyService = polyService;
		ICompareOperator<E> operator = new BillUpdateOperator<E>(polyService);
		this.template = new CompareOperatorTemplate<E>(operator);
	}

	/**
	 * 获取单据新增规则处理器。调用者可以据此增加前、后规则
	 * 
	 * @return 单据新增规则处理器
	 */
	public CompareAroundProcesser<E> getAroundProcesser() {
		return this.template.getAroundProcesser();
	}

	public E update(E fullBills, E originBills) throws ADBusinessException {
		return this.template.operate(fullBills, originBills);
	}
}

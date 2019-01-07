package tp.ms.base.rest.resource.service.strengthen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tp.ms.base.rest.resource.service.IPolyService;
import tp.ms.base.rest.resource.service.ace.CommonAroundProcesser;
import tp.ms.base.rest.resource.service.ace.CommonOperatorTemplate;
import tp.ms.base.rest.resource.service.ace.IOperator;
import tp.ms.base.rest.resource.vo.AbstractPolyVO;
import tp.ms.common.bean.exception.ADBusinessException;

public class BillInsertBPTemplate<T extends AbstractPolyVO> {
	
	static final Logger log = LoggerFactory.getLogger(BillInsertBPTemplate.class);

	/**
	 * 单据新增规则处理器
	 */
	private CommonOperatorTemplate<T> template;

	public BillInsertBPTemplate(IPolyService<T> polyService) {
		IOperator<T> operator = new BillInsertOperator<T>(polyService);
		this.template = new CommonOperatorTemplate<T>(operator);
	}

	public T insert(T bills) throws ADBusinessException {
		return this.template.operate(bills);
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

package tp.ms.base.rest.resource.service.strengthen;

import tp.ms.base.rest.resource.service.IChildService;
import tp.ms.base.rest.resource.service.IPolyService;
import tp.ms.base.rest.resource.service.ace.ICompareOperator;
import tp.ms.base.rest.resource.vo.AbstractPolyVO;
import tp.ms.base.rest.resource.vo.ChildBaseVO;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.vo.IBaseVO;

public class BillDeleteOperator<T extends AbstractPolyVO> implements ICompareOperator<T> {
	
	private IPolyService<T> polyService;

	public BillDeleteOperator(IPolyService<T> baseService) {
		this.polyService = baseService;
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public T operate(T returnVOs, T originBills) throws ADBusinessException {
		polyService.getMajorService().delete(returnVOs.getParent());
		IBaseVO[] cvos = returnVOs.getChildrenVO();
		for(int j=0; j<cvos.length; j++) {
			ChildBaseVO cvo = (ChildBaseVO) cvos[j];
			IChildService service = polyService.getChildService(cvo.getClass());
			service.delete(cvo);
		}
		return returnVOs;
	}

}

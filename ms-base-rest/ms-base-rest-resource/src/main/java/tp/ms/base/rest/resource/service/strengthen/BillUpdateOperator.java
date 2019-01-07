package tp.ms.base.rest.resource.service.strengthen;

import tp.ms.base.rest.resource.service.IChildService;
import tp.ms.base.rest.resource.service.IPolyService;
import tp.ms.base.rest.resource.service.ace.ICompareOperator;
import tp.ms.base.rest.resource.vo.AbstractPolyVO;
import tp.ms.base.rest.resource.vo.ChildBaseVO;
import tp.ms.base.rest.resource.vo.MajorBaseVO;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.utils.StringUtilms;
import tp.ms.common.bean.vo.IBaseVO;
import tp.ms.common.bean.vo.VoStatus;

public class BillUpdateOperator<E extends AbstractPolyVO> implements ICompareOperator<E> {

	IPolyService<E> polyService;
	
	public BillUpdateOperator(IPolyService<E> polyService) {
		this.polyService = polyService;
	}

	@Override
	public E operate(E returnVo, E originBill) throws ADBusinessException {
		if(StringUtilms.isEmpty(returnVo.getPrimaryKey())) {
			return polyService.insert(returnVo);
		}
		//处理主表数据
		MajorBaseVO parent = returnVo.getParent();
		if(parent == null)
			return returnVo;
		parent = handleParent(parent);
		returnVo.setParent(parent);
		//处理子表数据
		returnVo = handleChildes(returnVo);
		return returnVo;
	}

	private E handleChildes(E returnVo) throws ADBusinessException {
		IBaseVO[] childes = returnVo.getChildrenVO();
		childes = handleChild(childes, returnVo.getPrimaryKey());
		returnVo.setChildrenVO(childes);
		return returnVo;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private IBaseVO[] handleChild(IBaseVO[] childes, String masterKey) throws ADBusinessException {
		IBaseVO[] ces = childes;
		for(int i=0; i< childes.length; i++) {
			ChildBaseVO child = (ChildBaseVO) childes[i];
			IChildService childService = null;
			switch (child.getStatus()) {
			case VoStatus.UNCHANGED:
				break;
			case VoStatus.UPDATED:
				childService = polyService.getChildService(child.getClass());
				child = (ChildBaseVO) childService.update(child);
				break;
			case VoStatus.NEW:
				childService = polyService.getChildService(child.getClass());
				child.setParentKey(masterKey);
				child = (ChildBaseVO) childService.insert(child);
				break;
			case VoStatus.DELETED:
				childService = polyService.getChildService(child.getClass());
				child = (ChildBaseVO) childService.delete(child);
				break;

			default:
				break;
			}
			ces[i] = child;
		}
		return ces;
	}

	private MajorBaseVO handleParent(MajorBaseVO mbvo) throws ADBusinessException {
		switch (mbvo.getStatus()) {
		case VoStatus.UNCHANGED:
			return mbvo;
		case VoStatus.NEW:
			return this.polyService.getMajorService().insert(mbvo);
		default:
			break;
		}
		return this.polyService.getMajorService().update(mbvo);
		
	}

}

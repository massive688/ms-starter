package tp.ms.base.rest.resource.service.strengthen;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import tp.ms.base.rest.resource.service.IChildService;
import tp.ms.base.rest.resource.service.IPolyService;
import tp.ms.base.rest.resource.service.ace.IOperator;
import tp.ms.base.rest.resource.vo.AbstractPolyVO;
import tp.ms.base.rest.resource.vo.ChildBaseVO;
import tp.ms.base.rest.resource.vo.MajorBaseVO;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.result.ResultStatus;
import tp.ms.common.bean.utils.ObjectUtilms;
import tp.ms.common.bean.vo.IChildVO;
import tp.ms.common.bean.vo.VoStatus;

@Slf4j
public class BillInsertOperator<E extends AbstractPolyVO> implements IOperator<E> {

	IPolyService<E> polyService;
	
	public BillInsertOperator(IPolyService<E> polyService) {
		this.polyService = polyService;
	}

	@Override
	public E operate(E pvo) throws ADBusinessException {
		if(ObjectUtilms.isNotEmpty(pvo)) {
			if(ObjectUtilms.isEmpty(pvo.getChildrenVO())) {
				throw new ADBusinessException(ResultStatus.ERROR, "表体的子表数据不能为空");
			}
			MajorBaseVO parent = pvo.getParent();
			String id = null;
			if(parent.getStatus() == VoStatus.NEW) {
				id = polyService.getMajorService().insert(pvo.getParent()).getPrimaryKey();
			}
			if(id != null) {
				insertChilds(pvo.getChildrenVO(), id);
			}
		}else {
			log.info("poly is null {}", pvo);
			throw new ADBusinessException(ResultStatus.ERROR, "传入的数据错误【" + JSON.toJSONString(pvo) + "】");
		}
		return pvo;
	}

	private void insertChilds(IChildVO[] childrenVO, String parentId) throws ADBusinessException {
		if(ObjectUtilms.isNotEmpty(childrenVO)) {
			for(int i=0; i<childrenVO.length; i++) {
				ChildBaseVO child = (ChildBaseVO)childrenVO[i];
				if(child != null && child.getStatus()==VoStatus.NEW){
					child.setParentKey(parentId);
					insert(child);
				}
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String insert(ChildBaseVO vo) throws ADBusinessException {
		IChildService service = polyService.getChildService(vo.getClass());
		vo = (ChildBaseVO)service.insert(vo);
		return vo.getPrimaryKey();
	}
}

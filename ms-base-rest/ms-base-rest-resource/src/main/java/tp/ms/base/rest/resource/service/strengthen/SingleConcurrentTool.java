package tp.ms.base.rest.resource.service.strengthen;

import tp.ms.common.bean.exception.AExceptionUtils;
import tp.ms.common.bean.vo.IBaseVO;
import tp.ms.common.bean.vo.VoStatus;

public class SingleConcurrentTool {

	public void checkTS(IBaseVO vo, IBaseVO originBaseVO) {
		    String key = vo.getPrimaryKey();
		    if (key == null) {
		      return;
		    }
		    // 新增行，但是前台界面已经设置上pK
		    else if (vo.getStatus() == VoStatus.NEW) {
		      return;
		    }
		    IBaseVO originVO = originBaseVO;
		    if (originVO == null) {
		      this.throwUnSynchronizedException();
		    }
		    VOConcurrentTool bo = new VOConcurrentTool();
		    bo.checkTS(vo, originVO);
	}

	private void throwUnSynchronizedException() {
	    String message = "出现并发，请重新查询";
	    AExceptionUtils.wrappADBusinessException(message);
	}

}

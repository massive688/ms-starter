package tp.ms.base.rest.resource.service.strengthen;

import tp.ms.base.rest.resource.vo.IPolyVO;
import tp.ms.common.bean.exception.AExceptionUtils;
import tp.ms.common.bean.vo.IBaseVO;
import tp.ms.common.bean.vo.VoStatus;

/**
 * 一主多子单据实体的并发控制工具
 *
 * @since 6.0
 * @version 2008-8-2 下午11:04:33
 * @author 钟鸣
 */
public class BillConcurrentTool {

  /**
   * 通过比较两组单据实体的时间戳来判断是否存在并发
   *
   * @param bill 单据实体1
   * @param originBill 单据实体2
   */
  public void checkTS(IPolyVO bill, IPolyVO originBill) {
	  IBaseVO[] bvos = bill.getChildrenVO();
	  BillIndex index = new BillIndex(originBill);
	    IBaseVO parent = bill.getParent();
	    this.checkTS(parent, index);
	    for (IBaseVO vo : bvos) {
	        this.checkTS(vo, index);
	    }
  }

  /**
   * 对一主多子单据进行中间件加锁
   *
   * @param bill 单据实体
   */
  public void lockBill(IPolyVO bill) {
    IBaseVO header = bill.getParent();
    VOConcurrentTool bo = new VOConcurrentTool();
    bo.lock(header);
    IBaseVO[] itemIndex = bill.getChildrenVO();
    for (IBaseVO index : itemIndex) {
        bo.lock(index);
    }
  }

  private void checkTS(IBaseVO vo, BillIndex index) {
    String key = vo.getPrimaryKey();
    if (key == null) {
      return;
    }
    // 新增行，但是前台界面已经设置上pK
    else if (vo.getStatus() == VoStatus.NEW) {
      return;
    }
    IBaseVO originVO = index.get(key);
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
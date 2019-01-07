package tp.ms.base.rest.resource.service.strengthen;

import java.util.HashMap;
import java.util.Map;

import tp.ms.base.rest.resource.vo.IPolyVO;
import tp.ms.common.bean.vo.IBaseVO;

/**
 * 一主多子单据的各种索引结构。便于快速引用单据中的实体对象
 * 
 * @since 6.0
 * @version 2008-3-09 下午04:23:02
 * @author 钟鸣
 */
public class BillIndex {

  /**
   * 实体元数据、实体主键、实体的映射关系
   */
  private Map<String, IBaseVO> index = new HashMap<String, IBaseVO>();

  /**
   * 一主多子单据的各种索引结构构造函数
   * 
   * @param bill 一主多子单据
   */
  public BillIndex(IPolyVO bill) {
    this.init(bill);
  }

  /**
   * 根据实体元数据、实体主键获取实体
   * 
   * @param voMeta 实体元数据
   * @param key 实体主键
   * @return 实体
   */
  public IBaseVO get(String key) {
    if (this.index.containsKey(key)) {
      return this.index.get(key);
    }
    return null;
  }

  private void init(IPolyVO bill) {
	  index.put(bill.getPrimaryKey(), bill.getParent());
	  init(bill.getChildrenVO());
  }

  private void init(IBaseVO[] list) {
    if (list.length == 0) {
      return;
    }
    for (IBaseVO vo : list) {
      String key = vo.getPrimaryKey();
      index.put(key, vo);
    }
  }
}

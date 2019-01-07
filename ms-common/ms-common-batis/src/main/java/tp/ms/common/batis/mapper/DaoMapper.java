package tp.ms.common.batis.mapper;

import tp.ms.common.bean.vo.BaseExample;
import tp.ms.common.bean.vo.BaseVO;

public interface DaoMapper<T extends BaseVO, E extends BaseExample> extends SuperDaoMapper<T, E> {

}

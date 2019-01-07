package tp.ms.base.rest.resource.service;

import tp.ms.base.rest.resource.vo.MajorBaseVO;
import tp.ms.common.bean.vo.BaseExample;

public interface IMajorService<T extends MajorBaseVO, E extends BaseExample> extends ISingleService<T, E>, ICompareUpdateService<T>, ICommonInsertService<T> {

}

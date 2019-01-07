package tp.ms.base.rest.resource.service;


import tp.ms.base.rest.resource.vo.AbstractPolyVO;
import tp.ms.base.rest.resource.vo.ChildBaseVO;
import tp.ms.base.rest.resource.vo.MajorBaseVO;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.vo.BaseExample;

public interface IPolyService<T extends AbstractPolyVO> extends IBaseService<T>, ICompareUpdateService<T>, ICommonInsertService<T>  {

	public <M extends MajorBaseVO, E extends BaseExample> IMajorService<M, E> getMajorService() throws ADBusinessException;

	public <C extends ChildBaseVO, B extends BaseExample>IChildService<C, B> getChildService(Class<C> clz) throws ADBusinessException;


}

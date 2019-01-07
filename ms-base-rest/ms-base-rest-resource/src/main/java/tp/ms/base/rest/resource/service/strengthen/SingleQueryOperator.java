package tp.ms.base.rest.resource.service.strengthen;

import tp.ms.base.rest.resource.http.Pager;
import tp.ms.base.rest.resource.service.ISingleService;
import tp.ms.base.rest.resource.service.ace.IQueryOperator;
import tp.ms.base.rest.resource.vo.AbstractPolyVO;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.vo.BaseExample;
import tp.ms.common.bean.vo.BaseVO;

public class SingleQueryOperator<T extends BaseVO, E extends BaseExample> implements IQueryOperator<T> {

	ISingleService<T, E> singleService;
	public SingleQueryOperator(ISingleService<T, E> singleService) {
		this.singleService = singleService;
	}

	@Override
	public T query(String key) throws ADBusinessException {
		return singleService.queryByKey(key);
	}

	@Override
	public T query(String key, Class<? extends AbstractPolyVO> queryClass) throws ADBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T[] query(Pager page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T[] query(Pager page, Class<? extends AbstractPolyVO> queryClass) {
		// TODO Auto-generated method stub
		return null;
	}

}

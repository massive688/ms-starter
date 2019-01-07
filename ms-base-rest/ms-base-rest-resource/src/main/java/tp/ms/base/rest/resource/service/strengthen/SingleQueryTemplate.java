package tp.ms.base.rest.resource.service.strengthen;

import lombok.extern.slf4j.Slf4j;
import tp.ms.base.rest.resource.http.Pager;
import tp.ms.base.rest.resource.service.ISingleService;
import tp.ms.base.rest.resource.service.ace.IQueryOperator;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.vo.BaseExample;
import tp.ms.common.bean.vo.BaseVO;

@Slf4j
public class SingleQueryTemplate<T extends BaseVO, E extends BaseExample> {
	

	private IQueryOperator<T> template;

	ISingleService<T, E> singleService;
	public SingleQueryTemplate(ISingleService<T, E> singleService) {
		this.singleService = singleService;
		template = new SingleQueryOperator<T, E>(singleService);
	}


	public T query(String key) throws ADBusinessException {
		log.info(key);
		return template.query(key);
	}



	public T[] queryByPager(Pager page) throws ADBusinessException {
		return template.query(page);
	}


}



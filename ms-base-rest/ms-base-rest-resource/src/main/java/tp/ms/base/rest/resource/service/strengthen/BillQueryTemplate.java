package tp.ms.base.rest.resource.service.strengthen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tp.ms.base.rest.resource.http.Pager;
import tp.ms.base.rest.resource.service.IPolyService;
import tp.ms.base.rest.resource.service.ace.IQueryOperator;
import tp.ms.base.rest.resource.vo.AbstractPolyVO;
import tp.ms.common.bean.exception.ADBusinessException;

public class BillQueryTemplate<T extends AbstractPolyVO>  {

	IPolyService<T> polyService;
	static final Logger log = LoggerFactory.getLogger(BillQueryTemplate.class);

	private IQueryOperator<T> template;

	public BillQueryTemplate(IPolyService<T> polyService) {
		this.polyService = polyService;
		template = new BillQueryOperator<T>(polyService);
	}

	Class<? extends AbstractPolyVO> queryClass;
	public BillQueryTemplate(IPolyService<T> polyService, Class<? extends AbstractPolyVO> clazz) {
		this.polyService = polyService;
		template = new BillQueryOperator<T>(polyService);
		queryClass = clazz;
	}



	public T query(String key) throws ADBusinessException {
		if(queryClass == null)
			return template.query(key);
		else
			return template.query(key, this.queryClass);
	}



	public T[] queryByPager(Pager page) throws ADBusinessException {
		if(queryClass == null)
			return template.query(page);
		else
			return template.query(page, this.queryClass);
	}


}


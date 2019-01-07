package tp.ms.base.rest.resource.service;

import tp.ms.base.rest.resource.http.Pager;
import tp.ms.common.bean.exception.ADServerException;

public interface BasicRefService {
	
	Pager executeReference(Pager pager) throws ADServerException;
	
}

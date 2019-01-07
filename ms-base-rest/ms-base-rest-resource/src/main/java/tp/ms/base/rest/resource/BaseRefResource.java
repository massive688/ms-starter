package tp.ms.base.rest.resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import tp.ms.base.rest.resource.http.Pager;
import tp.ms.base.rest.resource.service.BasicRefService;
import tp.ms.common.bean.exception.ADServerException;
import tp.ms.common.bean.result.Result;
import tp.ms.common.bean.result.ResultSupport;

public abstract class BaseRefResource<T> extends BasisResource<T> {

	public abstract BasicRefService getRefService();
	
	@PostMapping("ref")
	public Result<Pager> refResource(@RequestBody Pager pager) throws ADServerException {
		BasicRefService refService = getRefService();
		return ResultSupport.ok(refService.executeReference(pager));
	}

}

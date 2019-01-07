package tp.ms.base.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tp.ms.base.rest.ots.orgs.api.OrgsService;
import tp.ms.base.rest.ots.orgs.entity.MyAdreamOrgelements;
import tp.ms.base.rest.resource.MajorController;
import tp.ms.base.rest.resource.service.IBaseService;

@RestController
@RequestMapping("/api/orgs")
public class OrgsApiResource extends MajorController<MyAdreamOrgelements> {

	@Autowired
	OrgsService service;
	
	@Override
	public IBaseService<MyAdreamOrgelements> getService() {
		return service;
	}

}

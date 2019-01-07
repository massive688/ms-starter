package tp.ms.base.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tp.ms.base.rest.resource.PolyController;
import tp.ms.base.rest.resource.service.IBaseService;
import tp.ms.base.rest.typecoded.api.BilltypeService;
import tp.ms.base.rest.typecoded.vo.PolyMsBaseBilltypeVO;

@RestController
@RequestMapping("/api/bill-type-poly")
public class BilltypePolyApiResource extends PolyController<PolyMsBaseBilltypeVO> {

	@Autowired
	BilltypeService service;
	
	@Override
	public IBaseService<PolyMsBaseBilltypeVO> getService() {
		return service;
	}

}

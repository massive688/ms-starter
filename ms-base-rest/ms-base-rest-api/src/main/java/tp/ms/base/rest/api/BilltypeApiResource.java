package tp.ms.base.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tp.ms.base.rest.resource.MajorController;
import tp.ms.base.rest.resource.service.IBaseService;
import tp.ms.base.rest.typecoded.api.BilltypeMajorService;
import tp.ms.base.rest.typecoded.vo.MsBaseBilltype;

@RestController
@RequestMapping("/api/bill-type-major")
public class BilltypeApiResource extends MajorController<MsBaseBilltype> {

	@Autowired
	BilltypeMajorService service;
	
	@Override
	public IBaseService<MsBaseBilltype> getService() {
		return service;
	}
//
//	@GetMapping("bt-link")
//	public void redirectUrl(HttpServletRequest req, HttpServletResponse response) {
//		req.
//	}
}

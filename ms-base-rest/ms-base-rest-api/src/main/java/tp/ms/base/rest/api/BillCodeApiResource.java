package tp.ms.base.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tp.ms.base.rest.resource.ChildController;
import tp.ms.base.rest.resource.service.IBaseService;
import tp.ms.base.rest.typecoded.api.BillCodeService;
import tp.ms.base.rest.typecoded.vo.MsBaseBillCodeRule;

@RestController
@RequestMapping("/api/bill-code")
public class BillCodeApiResource extends ChildController<MsBaseBillCodeRule> {

	@Autowired
	BillCodeService billCodeService;
	
	@Override
	public IBaseService<MsBaseBillCodeRule> getService() {
		return billCodeService;
	}

}

package tp.ms.base.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tp.ms.base.rest.resource.ChildController;
import tp.ms.base.rest.resource.service.IBaseService;
import tp.ms.base.rest.resource.service.IChildService;
import tp.ms.base.rest.typecoded.api.BillTemplateService;
import tp.ms.base.rest.typecoded.vo.MsBaseBillTemplate;
import tp.ms.base.rest.typecoded.vo.MsBaseBillTemplateExample;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.result.Result;
import tp.ms.common.bean.result.ResultSupport;

@RestController
@RequestMapping("/template/bill-columns")
public class BillTemplateApiResource extends ChildController<MsBaseBillTemplate> {

	@Autowired
	BillTemplateService service;
	
	@Override
	public IBaseService<MsBaseBillTemplate> getService() {
		return service;
	}
//
//	@GetMapping("bt-link")
//	public void redirectUrl(HttpServletRequest req, HttpServletResponse response) {
//		req.
//	}
	
	
	

	@Description("通过id获取对象的所有属性")
	@GetMapping
	public Result<MsBaseBillTemplate[]> findByBillTypeKey(@RequestParam("billKey")String key) throws ADBusinessException {
		if(service instanceof IChildService) {
			IChildService<MsBaseBillTemplate, MsBaseBillTemplateExample> childService = service;
			MsBaseBillTemplate[] columns = childService.queryByParentKey(key);
			return ResultSupport.ok(columns);
		}
		return ResultSupport.dataError(null);
	}
}

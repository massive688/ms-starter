package tp.ms.base.rest.generator.freemarker.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tp.ms.base.rest.generator.freemarker.service.TemplateService;
import tp.ms.base.rest.resource.BaseController;
import tp.ms.base.rest.resource.service.IBaseService;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.result.ResultSupport;

@RestController
@RequestMapping("/api/freemarker")
public class TablefreemarkerResource extends BaseController<Object>{

	@Autowired
	TemplateService templateService;

	@PostMapping("/create-table/{billKey}")
	public Object jdbctable(@PathVariable("billKey")String billKey) throws ADBusinessException {
		return ResultSupport.ok(templateService.excuteGeneratorStructure(billKey));
	}

	@Override
	public IBaseService<Object> getService() {
		// TODO Auto-generated method stub
		return null;
	}
}

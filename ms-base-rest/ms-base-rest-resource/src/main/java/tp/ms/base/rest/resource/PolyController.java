package tp.ms.base.rest.resource;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import tp.ms.base.rest.resource.http.Pager;
import tp.ms.base.rest.resource.vo.AbstractPolyVO;
import tp.ms.common.bean.exception.ADBusinessException;


public abstract class PolyController<T extends AbstractPolyVO> extends BaseController<T> {


	
	@PostMapping("search")
	public Object search(@Validated @RequestBody Pager page, @RequestHeader("signature")String signature, BindingResult bindingResult) throws ADBusinessException {
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors();
		}
		return getService().query(page);
	}
	

	
	
}


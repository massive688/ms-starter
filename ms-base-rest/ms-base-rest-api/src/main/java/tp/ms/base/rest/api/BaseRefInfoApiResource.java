package tp.ms.base.rest.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import tp.ms.base.rest.refinfo.api.BaseRefInfoService;
import tp.ms.base.rest.refinfo.vo.BaseRefInfoVO;
import tp.ms.base.rest.resource.BasisResource;
import tp.ms.base.rest.resource.http.Pager;
import tp.ms.common.bean.result.ResultSupport;

@RestController
@RequestMapping("api/ref-info")
public class BaseRefInfoApiResource extends BasisResource<BaseRefInfoVO> {

	@Autowired
	BaseRefInfoService service;
	
	@PostMapping("{refid}")
	public Object excuteRefInfoQuery(@PathVariable("refid") String refid, @Validated @RequestBody Pager data, BindingResult bindingResult) {
		List<BaseRefInfoVO> result = service.executeQueryByPager(refid, data);
		return ResultSupport.ok(new Pager(new PageInfo<BaseRefInfoVO>(result)));

	}
	

   
}

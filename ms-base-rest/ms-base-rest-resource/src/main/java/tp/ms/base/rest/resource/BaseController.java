package tp.ms.base.rest.resource;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Description;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.github.pagehelper.PageInfo;

import tp.ms.base.rest.resource.http.Pager;
import tp.ms.base.rest.resource.service.IBaseService;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.result.Result;
import tp.ms.common.bean.result.ResultSupport;

public abstract class BaseController<T> extends BasisResource<T> {
	
	private Logger log = LoggerFactory.getLogger(getClass());

	
	public abstract IBaseService<T> getService();
	
	
	@PostMapping("qry")
	public Object query(@RequestBody Pager pager) throws ADBusinessException {
		T[] res = getService().query(pager);
		PageInfo<T> result = new PageInfo<T>(Arrays.asList(res));
		return ResultSupport.ok(new Pager(result));
	}
	

	@Description("通过id获取对象的所有属性")
	@GetMapping("{id}")
	public Result<T> find(@PathVariable("id")String key) throws ADBusinessException {
		T t = getService().queryByKey(key);
		return ResultSupport.ok(t);
	}
    
    @PutMapping
	public Object create(@Validated @RequestBody T pvo) throws ADBusinessException {
		return ResultSupport.ok(getService().insert(pvo));
	}

	@DeleteMapping
	public Object delete(@RequestBody T pvo) throws ADBusinessException {
		return ResultSupport.ok(getService().delete(pvo));
	}

	@PostMapping
	public Object update(@RequestBody T pvo, HttpServletRequest request) throws ADBusinessException {
		log.info(pvo.toString());
		return ResultSupport.ok(getService().update(pvo));
	}
	
    
}

package tp.ms.base.rest.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import tp.ms.base.rest.formula.api.FormulaValueService;
import tp.ms.base.rest.formula.httparam.FormulaExecValue;
import tp.ms.base.rest.formula.httparam.TranslateValue;
import tp.ms.base.rest.resource.BasisResource;
import tp.ms.common.bean.exception.AExceptionUtils;
import tp.ms.common.bean.result.ResultSupport;

@RestController
@RequestMapping("api/translate-formula")
public class FormulaValueApiResource extends BasisResource<Object>{

	@Autowired
	FormulaValueService service;
	
	@PostMapping("translate-value")
	public Object excuteTranslate(@Validated @RequestBody TranslateValue data, BindingResult bindingResult) {

		//获取校验错误信息
		if(bindingResult.hasErrors()) {			
			//输出错误信息			
			List <ObjectError> allErrors = bindingResult.getAllErrors();
			List <String> errors = new ArrayList<>();
			for(ObjectError objectError:allErrors) {		
				//输出错误信息		
				System.out.println(objectError.getDefaultMessage());	
				errors.add(objectError.getDefaultMessage());
			}
			AExceptionUtils.unSupported(JSON.toJSONString(errors));
		}
		return ResultSupport.ok(service.execute(data));

			
	}
	
	@PostMapping("formula-exec")
	public Object excuteFormula(@Validated @RequestBody FormulaExecValue data, BindingResult bindingResult) {

		//获取校验错误信息
		if(bindingResult.hasErrors()) {			
			//输出错误信息			
			List <ObjectError> allErrors = bindingResult.getAllErrors();
			List <String> errors = new ArrayList<>();
			for(ObjectError objectError:allErrors) {		
				//输出错误信息		
				System.out.println(objectError.getDefaultMessage());	
				errors.add(objectError.getDefaultMessage());
			}
			AExceptionUtils.unSupported(JSON.toJSONString(errors));
		}
		return ResultSupport.ok(service.execute(data));

			
	}
	

   
}

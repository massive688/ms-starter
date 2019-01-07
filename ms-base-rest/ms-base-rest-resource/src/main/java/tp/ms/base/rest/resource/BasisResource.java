package tp.ms.base.rest.resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.extern.slf4j.Slf4j;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.exception.ADServerException;
import tp.ms.common.bean.result.Result;
import tp.ms.common.bean.result.ResultSupport;

@Slf4j
public class BasisResource<T> {

	/**    
     * 用于处理异常的    
     * @return    
     */      
    @ExceptionHandler({Exception.class})       
    public Result<String> exception(Exception e) { 
    	log.error(e.getMessage(), e);
    	if(e instanceof ADBusinessException)
    		return ResultSupport.exception(e);
    	if(e instanceof ADServerException)
    		return ResultSupport.serverError(e.getMessage());

		return ResultSupport.exception(e);
    } 
    

    
    @PutMapping("test")
	public Object createTest(@Validated @RequestBody T pvo, @Validated @ModelAttribute T pvo2) throws ADBusinessException {
		return ResultSupport.ok();
	}
    
}

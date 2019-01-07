package tp.ms.base.rest.resource;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.result.BaseResultCode;
import tp.ms.common.bean.result.Result;

@ControllerAdvice
public class BusinessExceptionControllerAdvice {
	private Logger logger = LoggerFactory.getLogger("ErrorFile");

	public Result<Object> initResp() {
		Result<Object> vo = new Result<>();
		vo.setCode(BaseResultCode.INTERNAL_SERVER_ERROR.getCode());
		vo.setMessage(BaseResultCode.INTERNAL_SERVER_ERROR.getMessage());
		return vo;
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(value = HttpStatus.OK)
	public Map<String, Object> handleAndReturnData(HttpServletRequest requset, HttpServletResponse response,
			Exception exception) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (exception instanceof ADBusinessException) {
			resultMap.put("msg", ((ADBusinessException) exception).getMessage());
			resultMap.put("error-code", ((ADBusinessException) exception).getCode());
		}
		Result<Object> vo = initResp();
		Object resp = vo.build();
		logger.error("Excpetion Handler || Error Response Body: [ {} ]", resp, exception);
		return resultMap;
	}
}
package tp.ms.common.bean.result;

import java.io.IOException;

import javax.servlet.ServletResponse;

import com.alibaba.fastjson.JSON;

import tp.ms.common.bean.exception.ADBusinessException;


public class ResultSupport {
    private ResultSupport() {
    }
    
    public static Result<String> ok() {
        return new Result<String>(BaseResultCode.OK);
    }

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<T>(BaseResultCode.OK);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> dataError(T data) {
        Result<T> result = new Result<T>(BaseResultCode.DATA_ERROR);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> exception(Exception e) {
        Result<T> result = new Result<T>(BaseResultCode.EXCEPTION);
        result.setMessage(e.getMessage());
        return result;
    }

    public static Result<String> ok(ServletResponse resp) throws ADBusinessException {
        return outputJson(ok(), resp);
    }

    public static Result<String> serverError() {
        return new Result<String>(BaseResultCode.INTERNAL_SERVER_ERROR);
    }
    
    public static Result<String> serverError(String errorMessage) {
        Result<String> result = new Result<String>(BaseResultCode.INTERNAL_SERVER_ERROR);
        result.setMessage(errorMessage);
        return result;
    }

    public static Result<String> serverError(String codeStr, String errorMessage) {
    	Result<String> result = null;
    	try {
    		ResultCode code = BaseResultCode.parseForCode(codeStr);
    		result = new Result<String>(code.getCode(), code.getMessage());
    	}catch (Exception e) {
    		result = serverError();
    	}
    	return result;
    }

    public static Result<String> serverError(ServletResponse resp) throws ADBusinessException {
        return outputJson(serverError(), resp);
    }

    public static Result<String> serverError(String errorMessage, ServletResponse resp) throws ADBusinessException {
        return outputJson(serverError(errorMessage), resp);
    }
    
    public static Result<String> forbidden() {
        return new Result<String>(BaseResultCode.FORBIDDEN);
    }

    public static Result<String> forbidden(ServletResponse resp) throws ADBusinessException {
        return outputJson(forbidden(), resp);
    }

    public static final <T> Result<T> outputJson(Result<T> obj, ServletResponse resp) throws ADBusinessException {
    	resp.setCharacterEncoding("UTF-8");
    	try {
        	resp.getWriter().write(JSON.toJSONString(obj));
			resp.flushBuffer();
		} catch (IOException e) {
			throw new ADBusinessException(e);
		}
        return obj;
    }

    public static final Result<String> outputJson(ResultCode resultCode, ServletResponse resp) throws ADBusinessException {
        return ResultSupport.outputJson(new Result<String>(resultCode), resp);
    }
}

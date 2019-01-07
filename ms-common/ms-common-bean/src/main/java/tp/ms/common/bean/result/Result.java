package tp.ms.common.bean.result;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import tp.ms.common.bean.utils.ObjectUtilms;
import tp.ms.common.bean.utils.StringUtilms;


public class Result<T> {
	
	public static void main(String[] args) {
		Result<String> result = new Result<String>(BaseResultCode.OK);
		String hm = "";
//		hm.put("key", "aaaaa");
		result.setData(hm);
//		result.setLocation("jslowl");
		System.out.println(JSON.toJSONString(result));
	}

//	@JSONField(ordinal=1)
    private Integer code;
//	@JSONField(ordinal=2)
    private String message;

	@JSONField(ordinal=3)//, serialzeFeatures= {SerializerFeature.WriteNonStringKeyAsString})
//    @JsonInclude(Include.NON_EMPTY)
    private T data;

	private Object parameter;
    /**
     * Only used when 202 Created
     */
	@JSONField(ordinal=4)
//    @JsonInclude(value=Include.NON_NULL) 
    private String location;

    public Result() {
        this(BaseResultCode.OK);
    }

    public Result(Integer code, String message) {
        this(code, message, null);
    }

    public Result(Integer string, String message, T data) {
        this.code = string;
        this.message = message;
        this.data = data;
    }

    public Result(ResultCode resultCode) {
        this(resultCode, null);
    }

    public Result(ResultCode resultCode, T data) {
        this(resultCode.getCode(), resultCode.getMessage(), data);
    }
    
    public void setResult(Integer code, String message){
    	this.code = code;
    	this.message = message;
    }
    
    public void setResult(ResultCode resultCode){
    	this.code = resultCode.getCode();
    	this.message = resultCode.getMessage();
    }
    public void setResult(ResultCode resultCode, T data){
    	this.code = resultCode.getCode();
    	this.message = resultCode.getMessage();
    	this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public Result<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
    	if(ObjectUtilms.isNotEmpty(data))
    		this.data = data;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public Result<T> setLocation(String location) {
    	if(!StringUtilms.isEmpty(location)) {
            this.location = location;
    	}
        return this;
    }

    
	public Object getParameter() {
		return parameter;
	}

	public void setParameter(Object parameter) {
		this.parameter = parameter;
	}

	public static Object create() {
		return new Result<>();
	}

	public String build() {
		return JSON.toJSONString(this);
	}
}

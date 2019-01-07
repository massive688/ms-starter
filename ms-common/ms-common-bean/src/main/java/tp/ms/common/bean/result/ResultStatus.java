package tp.ms.common.bean.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

@JSONType(ignores= {"eLogType"})
public class ResultStatus {
	public static final ResultStatus SUCCESS = new ResultStatus(20000,"成功");
	public static final ResultStatus FAILD = new ResultStatus(20004,"成功");
	public static final ResultStatus ERROR = new ResultStatus(20005,"成功");

	@JSONField(ordinal=1)
	private Integer code;
	@JSONField(ordinal=2)
	private Object msg;
	private ELogType eLogType;
	
	public ResultStatus() {
		
	}
	public ResultStatus(Integer c, Object m) {
	    code = c;
	    msg = m;
	    this.eLogType = ELogType.WARNING;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Object getMsg() {
		return msg;
	}
	public void setMsg(Object msg) {
		this.msg = msg;
	}
	public ELogType geteLogType() {
		return eLogType;
	}
	public void seteLogType(ELogType elt) {
		eLogType = elt;
	}
	public static enum ELogType {
		WARNING,
		ERROR
	}
	
}

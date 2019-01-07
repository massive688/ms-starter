package tp.ms.common.bean.exception;

import org.springframework.beans.BeanUtils;

import tp.ms.common.bean.result.Result;
import tp.ms.common.bean.result.ResultStatus;


public class ADServerException extends ADBusinessException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 685151875438350107L;

	private Integer code;
	ResultStatus resultStatus;
	public ADServerException() {
		this(ResultStatus.ERROR, "错误解释，快联系管理员");
	}

	public ADServerException(Exception e) {
		super(e);
		setCodeAndResultStatus(e.getMessage(), null);
	}
	public ADServerException(String message) {
		super(message);
		setCodeAndResultStatus(message, null);
	}
	
	private void setCodeAndResultStatus(String message, ResultStatus resultStatus) {
		ResultStatus newObj = new ResultStatus();
		BeanUtils.copyProperties(resultStatus==null?ResultStatus.ERROR:resultStatus, newObj);
		newObj.setMsg(message);
		setCodeAndResultStatus(newObj);
	}
	private void setCodeAndResultStatus(ResultStatus resultStatus) {
		setCode(resultStatus.getCode());
		this.resultStatus = resultStatus;
	}

	public ADServerException(ResultStatus resultStatus) {
		super(String.valueOf(resultStatus.getMsg()));
		setCodeAndResultStatus(resultStatus);
	}
	
	public ADServerException(ResultStatus resultStatus, String message) {
		super(message);
		setCodeAndResultStatus(message, resultStatus);
	}
	
	Result<?> result;
	public ADServerException(Result<?> result) {
		this.result = result;
	}


	public void setCode(Integer code) {
		this.code = code;
	}
	public Integer getCode() {
		return this.code;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}

}

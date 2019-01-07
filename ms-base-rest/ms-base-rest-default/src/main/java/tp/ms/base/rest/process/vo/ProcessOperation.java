package tp.ms.base.rest.process.vo;

import tp.ms.common.bean.utils.ObjectUtilms;

public enum ProcessOperation {
	
	SUBMIT(1,"提交"),
	PASS(2,"通过"),
	REJECT(3,"驳回"),
	TRANSFER(4,"转办"),
	COMMUNICATE(5,"沟通"),
	DISCARD(6,"废弃");
	
	Integer code;
	String info;
	ProcessOperation(Integer code, String info) {
		this.code = code;
		this.info = info;
	}

	public static ProcessOperation obtainProcessOperation(Integer code) {
		for(ProcessOperation status: ProcessOperation.values()) {
			if(ObjectUtilms.isEqual(status.code, code))
				return status;
		}
		return null;
	}
	public static ProcessOperation obtainProcessOperation(String info) {
		for(ProcessOperation status: ProcessOperation.values()) {
			if(ObjectUtilms.isEqual(status.info, info))
				return status;
		}
		return null;
	}
	
	 

	public Integer getCode() {
		if(this.code == null && this.info != null) {
			for(ProcessOperation status: ProcessOperation.values()) {
				if(ObjectUtilms.isEqual(status.info, this.info))
					return status.code;
			}
		}
		return this.code;
	}

	public String getInfo() {
		if(this.code != null && this.info == null) {
			for(ProcessOperation status: ProcessOperation.values()) {
				if(ObjectUtilms.isEqual(status.code, this.code))
					return status.info;
			}
		}
		return this.info;
	}
	
}

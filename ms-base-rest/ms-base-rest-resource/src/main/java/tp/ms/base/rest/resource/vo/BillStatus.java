package tp.ms.base.rest.resource.vo;

import tp.ms.common.bean.utils.ObjectUtilms;

public enum BillStatus {

	FREE(-1,"草稿"),
	SUBMIT(1,"提交"),
	APPROVING(2, "审核中..."),
	PASS(3, "通过"),
	REJECT(4, "驳回"),
	END(5, "结束"),
	DISCARD(6, "废弃"),
	UNDERWAY(7, "进行中");
	
	Integer code; 
	String name;
	
	BillStatus(Integer code, String name){
		this.code = code;
		this.name = name;
	}

	BillStatus(Integer code){
		this(code, null);
	}

	BillStatus(String name){
		this(null, name);
	}

	public Integer getCode() {
		if(this.code == null && this.name != null) {
			for(BillStatus status: BillStatus.values()) {
				if(ObjectUtilms.isEqual(status.name, this.name))
					return status.code;
			}
		}
		return this.code;
	}

	public String getName() {
		if(this.code != null && this.name == null) {
			for(BillStatus status: BillStatus.values()) {
				if(ObjectUtilms.isEqual(status.code, this.code))
					return status.name;
			}
		}
		return this.name;
	}
	
}

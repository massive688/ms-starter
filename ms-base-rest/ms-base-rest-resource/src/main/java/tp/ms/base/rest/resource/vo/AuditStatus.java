package tp.ms.base.rest.resource.vo;

public enum AuditStatus {

	//审批状态

	FREE(0, "自由"),
	EXAMINEDING(1, "待审核"),
    PASS(2, "审核通过"),
    UN_PASS(3, "未通过"),
	REJECT(4, "驳回"),
	DISCARDED(5, "废弃"),
	END(6, "结束");
    
    int code;
    String message;

    AuditStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String valueOf(Integer status) {
        if (status == null) {
            return "";
        } else {
            for (AuditStatus s : AuditStatus.values()) {
                if (s.getCode() == status) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
	
}

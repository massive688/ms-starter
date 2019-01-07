package tp.ms.base.rest.resource.vo;

public abstract class MajorAuditBaseVO extends MajorBaseVO implements IPendingAudit{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6646480743604694423L;
	
    protected String processId;

    protected Integer approveStatus;

    protected Integer billStatus;
    
    
    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId == null ? null : processId.trim();
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }


    public Integer getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(Integer billStatus) {
        this.billStatus = billStatus;
    }

}

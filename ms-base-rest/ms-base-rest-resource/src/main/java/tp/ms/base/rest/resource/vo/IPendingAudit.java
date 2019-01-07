package tp.ms.base.rest.resource.vo;

public interface IPendingAudit extends IAuditInfo {

	String getBilltype();
	
//	BigDecimal getConditionAmount();

	String getProcessId();

	Integer getApproveStatus();
	
	
}

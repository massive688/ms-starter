package tp.ms.base.rest.process.vo;

import java.io.Serializable;
import tp.ms.base.rest.resource.vo.ChildBaseVO;

public class MsWorkableFlowProcessInformation extends ChildBaseVO implements Serializable {
    private String pkProcessInformation;

    private String approveTime;

    private String nodeName;

    private String operator;

    private String operation;

    private String handlingOpinions;

    private String processId;

    private String isCurrentNode;

    private String taskId;

    private String operatorID;
    
    private String billtype;

    private static final long serialVersionUID = 1L;

    public String getPkProcessInformation() {
        return pkProcessInformation;
    }

    public void setPkProcessInformation(String pkProcessInformation) {
        this.pkProcessInformation = pkProcessInformation == null ? null : pkProcessInformation.trim();
    }

    public String getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(String approveTime) {
        this.approveTime = approveTime == null ? null : approveTime.trim();
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    public String getHandlingOpinions() {
        return handlingOpinions;
    }

    public void setHandlingOpinions(String handlingOpinions) {
        this.handlingOpinions = handlingOpinions == null ? null : handlingOpinions.trim();
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId == null ? null : processId.trim();
    }

    public String getIsCurrentNode() {
        return isCurrentNode;
    }

    public void setIsCurrentNode(String isCurrentNode) {
        this.isCurrentNode = isCurrentNode == null ? null : isCurrentNode.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(String operatorID) {
        this.operatorID = operatorID == null ? null : operatorID.trim();
    }	@Override
	public String getParentKey() {
		return "";
	}

	@Override
	public void setParentKey(String parentKey) {
	}

	@Override
	public String getTable() {
		return "ms_workable_flow_process_information";
	}

	@Override
	public void setPrimaryKey(String key) {
		this.pkProcessInformation = key;
	}

	@Override
	public String getPrimaryKey() {
		return this.pkProcessInformation;
	}
	
	public void setBilltype(String billtype) {
		this.billtype = billtype;
	}
	
	public String getBilltype() {
		return billtype;
	}
}

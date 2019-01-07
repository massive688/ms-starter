package tp.ms.base.rest.resource.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AuditInfoVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5567293663312045812L;
	
	String approveTime;
	
	String nodeName;
	
	String operation;

	String operator;
	
	String handlingOpinions;
	
}

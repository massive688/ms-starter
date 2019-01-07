package tp.ms.common.bean.vo;

import lombok.Data;

@Data
public abstract class Http {
	
	String refere;
	
	String servletPath;
	
	String contextPath;
	
	String host;
	
	String port;
	
	String protocol;
	
}

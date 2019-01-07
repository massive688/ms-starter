package tp.ms.common.bean.support.context;

import tp.ms.common.bean.vo.Business;
import tp.ms.common.bean.vo.Http;
import tp.ms.common.bean.vo.User;

public interface MsContext {

	User user();
	void setUser(User user);
	
	Http http();
	void setHttp(Http http);
	
	Business business();
	void setBusiness(Business business);
	
	String getPkOrg();
	
	String getPkGroup();
	
	String oUrl();
	void setOUrl(String url);
}

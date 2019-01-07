package tp.ms.common.bean.support.context;

import tp.ms.common.bean.vo.Business;
import tp.ms.common.bean.vo.Http;
import tp.ms.common.bean.vo.User;

public class MsContextImpl implements MsContext {

	private User user;
	private Http http;
	private Business business;
	
	@Override
	public User user() {
		return user;
	}

	@Override
	public Http http() {
		return http;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void setHttp(Http http) {
		this.http = http;
	}

	@Override
	public Business business() {
		return business;
	}

	@Override
	public void setBusiness(Business business) {
		this.business = business;
	}

	@Override
	public String getPkOrg() {
		return user().getPkOrg();
	}

	@Override
	public String getPkGroup() {
		return user().getPkGroup();
	}

	String oUrl;
	@Override
	public String oUrl() {
		return oUrl;
	}

	@Override
	public void setOUrl(String url) {
		this.oUrl = url;
	}



}

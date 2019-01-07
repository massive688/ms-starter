package tp.ms.cas.security.rest.jwt4csrf;

import tp.ms.base.rest.ots.staff.entity.MyAdreamStaff;

public interface AuthService {
	
	static final String REST_API_ROOT = "/jwt4sca";
	
	MyAdreamStaff register(MyAdreamStaff addedUser);
    String login(String username, String password);
    String refresh(String oldToken);
}
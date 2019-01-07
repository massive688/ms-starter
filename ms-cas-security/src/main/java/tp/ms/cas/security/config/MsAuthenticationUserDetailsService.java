package tp.ms.cas.security.config;

import org.jasig.cas.client.util.AssertionHolder;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MsAuthenticationUserDetailsService extends UserDetailsByNameServiceWrapper<CasAssertionAuthenticationToken> {

	public MsAuthenticationUserDetailsService(UserDetailsService userDetailsService) {
		super(userDetailsService);
	}

	@Override
	public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
		/*Map<String, Object> map = token.getAssertion().getPrincipal().getAttributes();*/
		AssertionHolder.setAssertion(token.getAssertion());
		return super.loadUserDetails(token);
	}

}

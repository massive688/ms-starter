package tp.ms.cas.security.config;

import java.util.Map;

import org.jasig.cas.client.util.AssertionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.val;
import tp.ms.base.rest.ots.staff.api.StaffService;
import tp.ms.cas.security.permission.entity.JwtUserFactory;

public class MsUserDetailsService implements UserDetailsService {

	@Autowired
	StaffService staffService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		Map<String,Object> result = AssertionHolder.getAssertion().getPrincipal().getAttributes();
		
		val vo = staffService.getStaff(username, result);

		return JwtUserFactory.create(vo, result);//;
	}

	
}

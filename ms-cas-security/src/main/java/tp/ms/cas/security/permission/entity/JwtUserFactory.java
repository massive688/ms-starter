package tp.ms.cas.security.permission.entity;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.Assert;

import lombok.val;
import tp.ms.base.rest.ots.staff.entity.MyAdreamStaff;
import tp.ms.common.bean.support.context.MsEnvContextHolder;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(MyAdreamStaff vo) {
        return create(vo, null);
    }
    
	public static JwtUser create(MyAdreamStaff vo, Map<String, Object> result) {
		MsEnvContextHolder.getContext().setUser(vo);
		
        return new JwtUser(
                vo.getPkUser(),
                vo.getUserCode(),
                "15830必须保密",
                vo.getUserName(),
                roles(vo.getRoles()),
                new Date(),
                result,
                vo
        );
    }

//    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
//        return authorities.stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }

	public static Collection<GrantedAuthority> roles(String rolestr) {
		val roles = rolestr.split(",");
		Map<String, GrantedAuthority> authorities = new HashMap<>(
				roles.length);
		for (String role : roles) {
			if(!authorities.containsKey(role)) {
				role = RoleEnum.valueOf(Integer.parseInt(role)).name();
				Assert.isTrue(!role.startsWith("ROLE_"), role
						+ " cannot start with ROLE_ (it is automatically added)");
				authorities.put(role, new SimpleGrantedAuthority("ROLE_" + role));
			}
		}
		return authorities.values();
	}

}

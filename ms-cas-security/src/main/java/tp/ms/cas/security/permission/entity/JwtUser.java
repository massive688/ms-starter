package tp.ms.cas.security.permission.entity;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tp.ms.base.rest.ots.staff.entity.MyAdreamStaff;

public class JwtUser extends User {
	tp.ms.common.bean.vo.User vo;
	public JwtUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public JwtUser(String id, String username, String password, String email,
			Collection<? extends GrantedAuthority> authorities, Date lastPasswordResetDate,
			Map<String, Object> serverResult) {
		this(id, password, authorities);
		this.id = id;
		this.r_user_name = username;
		this.email = email;
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.serverResult = serverResult;
	}

	public JwtUser(String pkUser, String username, String password, String email, Collection<GrantedAuthority> authorities,
			Date lastPasswordResetDate, Map<String, Object> result, MyAdreamStaff vo) {
		this(pkUser, username, password, email, authorities, lastPasswordResetDate, result);
		this.vo = vo;
	}
	
	public tp.ms.common.bean.vo.User getAfterAuthenticationUser(){
		return vo;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3135894853049092444L;

	private String id;
	@SuppressWarnings("unused")
	private String r_user_name;
	private String email;
	private Date lastPasswordResetDate;
	private Map<String, Object> serverResult;

	@JsonIgnore
	public String getId() {
		return id;
	}
	
	@JsonIgnore
	public Map<String, Object> getServerResult() {
		return serverResult;
	}

	public String getEmail() {
		return email;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}

	@JsonIgnore
	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}
}
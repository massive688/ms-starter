package tp.ms.cas.security.rest.jwt4csrf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import lombok.val;
import tp.ms.base.rest.ots.staff.entity.MyAdreamStaff;
import tp.ms.base.rest.ots.staff.entity.MyAdreamStaffExample;
import tp.ms.base.rest.ots.staff.entity.MyAdreamStaffMapper;
import tp.ms.cas.security.permission.entity.JwtUser;
import tp.ms.cas.security.permission.filter.JwtTokenUtil;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private MyAdreamStaffMapper mapper;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil,
            MyAdreamStaffMapper userRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.mapper = userRepository;
    }

    @Override
    public MyAdreamStaff register(MyAdreamStaff userToAdd) {
        final String username = userToAdd.getUserid();
        val example = new MyAdreamStaffExample();
		example.createCriteria().andUseridEqualTo(username);
        if(mapper.selectByExample(example)!=null) {
            return null;
        }
        userToAdd.setRoles("4,5");
        mapper.insert(userToAdd);
        return userToAdd;
    }

	@Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())){
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }
}
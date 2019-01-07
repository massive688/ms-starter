package tp.ms.cas.security.rest.jwt4csrf;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tp.ms.base.rest.ots.staff.entity.MyAdreamStaff;

/**
 * https://github.com/wpcfan/spring-boot-tut/tree/chap04
 * @author hadoop
 *
 */
@RestController
@RequestMapping(AuthService.REST_API_ROOT)
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @PostMapping("${jwt.route.authentication.path}")
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException{
        final String token = authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @GetMapping("${jwt.route.authentication.refresh}")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException{
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if(refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        }
    }

    @PostMapping("${jwt.route.authentication.register}")
    public MyAdreamStaff register(@RequestBody MyAdreamStaff addedUser) throws AuthenticationException{
        return authService.register(addedUser);
    }
}
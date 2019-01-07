package tp.ms.cas.security.config;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import tp.ms.cas.security.permission.entity.RoleEnum;
import tp.ms.cas.security.permission.filter.JwtAuthenticationTokenFilter;

@Configuration
@EnableWebSecurity
@AutoConfigureAfter({MsSecurityBeanConfiguration.class})
@ComponentScan({
	"tp.ms.cas.security.*"
})
public class MsWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private SingleSignOutFilter singleSignOutFilter;

    @Autowired
    private LogoutFilter logoutFilter;

    @Autowired
	private AccessDeniedHandler msAccessDeniedHandler;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
	

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	 //所有都需要认证才能访问
        //由于设置了验证filter访问为，/login/cas，所以必须通过验证，否则出现死循环
        http
                .authorizeRequests().antMatchers("/login/cas").permitAll()
                .and()
                .csrf().requireCsrfProtectionMatcher(new RequestMatcher() {
            		//放行这几种请求
            		private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
            		//放行rest请求，当然后面rest与web将会分开，到时这里可以删除
            		private RegexRequestMatcher unprotectedMatcher = new RegexRequestMatcher("^/rest/.*", null);

            		@Override
            		public boolean matches(HttpServletRequest request) {
            			if(allowedMethods.matcher(request.getMethod()).matches()){
            				return false;
            			}

            			String servletPath = request.getServletPath();
            			if (servletPath.contains("/druid")) {
            				return false;
            			}
            			return !unprotectedMatcher.matches(request);
            		}
            	})
                .and().csrf().disable().cors()
                //servletPath.contains("/connector"
                .and()
                .authorizeRequests().antMatchers("/index").hasAnyRole(RoleEnum.USER.toString())
                .and()
                .authorizeRequests().antMatchers("/connector/.*").hasAnyRole(RoleEnum.USER.toString())
                .and()
                .authorizeRequests().antMatchers("/abc/[\\d]*").hasAnyRole(RoleEnum.OUTSIDE_USER.toString(), RoleEnum.USER.toString())
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .logout().logoutSuccessUrl("/logout")
                .and()
                .addFilterBefore(singleSignOutFilter, CasAuthenticationFilter.class).addFilterBefore(logoutFilter, LogoutFilter.class)
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(msAccessDeniedHandler)   ;

//                
                /**
                
                // 自定义accessDecisionManager 方式动态加载权限
                .and()
                    .authorizeRequests()
                    // 自定义accessDecisionManager
                    .accessDecisionManager(accessDecisionManager())

                // 自定义ObjectPostProcessor 方式动态加载权限
                .and()
                	.authorizeRequests()
                    // 自定义FilterInvocationSecurityMetadataSource
                    .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    	@Override
                        public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                    		fsi.setSecurityMetadataSource(msSecurityMetadataSource(fsi.getSecurityMetadataSource()));
                                return fsi;
                            }
                        }) 
                 */        		
    }
    
    
	@Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

	
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        //设置cas认证提供
        return authenticationManager;
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
    	web.ignoring().antMatchers(
				"/v2/api-docs"
				,"/swagger-resources/configuration/ui"
				,"/swagger-resources"
				,"/swagger-resources/configuration/security"
//				,"/swagger-ui.html"
				,"/webjars/**"
				);
        super.configure(web);
    }
}
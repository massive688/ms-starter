package tp.ms.cas.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.web.CasAuthenticationFilter;

import tp.ms.cas.security.permission.filter.JwtAuthenticationTokenFilter;
import tp.ms.cas.security.permission.filter.UrlAuthenticationFailureHandler;
import tp.ms.cas.security.permission.filter.UrlAuthenticationSuccessHandler;


@Configuration
@AutoConfigureAfter({MsAuthenticationManagerConfiguration.class})
public class MsSecurityBeanConfiguration {


    //cas服务
	@Autowired
    private MsCasSecurityProperties casSecurityProperties; 

	@Autowired
	AuthenticationManager authenticationManager;
	
	
    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }
    
//    @Bean
//    public AccessDecisionManager accessDecisionManager() {
//        List<AccessDecisionVoter<? extends Object>> decisionVoters
//            = Arrays.asList(
//            new WebExpressionVoter(),
//            // new RoleVoter(),
//            new MsRoleBasedVoter(),
//            new AuthenticatedVoter());
//        return new UnanimousBased(decisionVoters);
//    }
//
//    @Bean
//    public AppFilterInvocationSecurityMetadataSource msSecurityMetadataSource(FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource) {
//        AppFilterInvocationSecurityMetadataSource securityMetadataSource = new AppFilterInvocationSecurityMetadataSource(filterInvocationSecurityMetadataSource);
//        return securityMetadataSource;
//    }

    @Bean
    public CasAuthenticationFilter casAuthenticationFilter(ServiceProperties sp)
            throws Exception {
        //cas认证过滤器，当触发本filter时，对ticket进行认证
        CasAuthenticationFilter filter = new CasAuthenticationFilter();
        filter.setServiceProperties(sp);
        filter.setAuthenticationManager(authenticationManager);
        filter.setAuthenticationSuccessHandler(new UrlAuthenticationSuccessHandler("/"));
        filter.setAuthenticationFailureHandler(new UrlAuthenticationFailureHandler(casSecurityProperties.getCasServerUrl(), casSecurityProperties.getAppServiceUrl()));
        return filter;
    }    

//  @Bean
//  public Controller rootController() {
//      return new ParameterizableViewController() {
//          @Override
//          protected ModelAndView handleRequestInternal(final HttpServletRequest request,
//                                                       final HttpServletResponse response) {
//              val queryString = request.getQueryString();
//              val url = request.getContextPath() + "/index"
//                  + (queryString != null ? '?' + queryString : "");
//              return new ModelAndView(new RedirectView(response.encodeURL(url)));
//          }
//
//      };
//  }
}

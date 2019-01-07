package tp.ms.cas.security.config;

import javax.servlet.http.HttpSessionEvent;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.validation.Cas30ServiceTicketValidator;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.StringUtils;

import lombok.val;
import tp.ms.cas.security.permission.filter.MsEnvContextPersistenceFilter;
import tp.ms.cas.security.permission.filter.UrlLogoutSuccessHandler;

@Configuration
@EnableConfigurationProperties(MsCasSecurityProperties.class)
public class MsCasCoreSecurityConfig {
	/**
	 * https://blog.csdn.net/u010475041/article/details/79592661
	 * @return
	 */
//    @Bean
//    public FilterRegistrationBean<HttpParamsFilter> httpParamsFilter() {
//        FilterRegistrationBean<HttpParamsFilter> filterRegistrationBean = new FilterRegistrationBean<HttpParamsFilter>();
//        filterRegistrationBean.setFilter(new HttpParamsFilter(getContextPath(casSecurityProperties.getAppServiceUrl())));
//        filterRegistrationBean.setOrder(-102);
//        filterRegistrationBean.addUrlPatterns("/*"); 
//        return filterRegistrationBean;
//    }

    public String getContextPath(String appServiceUrl) {
    	if(appServiceUrl.startsWith("https://")) {
    		appServiceUrl = appServiceUrl.substring("https://".length());
    	}else if(appServiceUrl.startsWith("http://")){
    		appServiceUrl = appServiceUrl.substring("http://".length());
    	}else {
    		throw new RuntimeException("cas app service url is invalid value");
    	}
    	if(appServiceUrl.indexOf("/") > -1) {
    		appServiceUrl = appServiceUrl.substring(appServiceUrl.indexOf("/"));
    		val serviceUrl = appServiceUrl.substring(1);
    		if(serviceUrl.indexOf("/") > -1) {
    			return "/"+serviceUrl.substring(0, serviceUrl.indexOf("/"));
    		}else {
    			return appServiceUrl;
    		}
    	}else {
    		return "/";
    	}
	}

//	@Bean
//    public FilterRegistrationBean<MsEnvContextBusinessFilter> msEnvContextBusinessFilter() {
//        FilterRegistrationBean<MsEnvContextBusinessFilter> filterRegistrationBean = new FilterRegistrationBean<MsEnvContextBusinessFilter>();
//        filterRegistrationBean.setFilter(new MsEnvContextBusinessFilter());
//        filterRegistrationBean.setOrder(-101);
//        filterRegistrationBean.addUrlPatterns("/*"); 
//        return filterRegistrationBean;
//    }

	@Bean
    public FilterRegistrationBean<MsEnvContextPersistenceFilter> msEnvContextPersistenceFilter() {
        FilterRegistrationBean<MsEnvContextPersistenceFilter> filterRegistrationBean = new FilterRegistrationBean<MsEnvContextPersistenceFilter>();
        filterRegistrationBean.setFilter(new MsEnvContextPersistenceFilter());
        filterRegistrationBean.setOrder(-102);
        filterRegistrationBean.addUrlPatterns("/*"); 
        return filterRegistrationBean;
    }
	

	/********************************************************************************/

    //cas服务
	@Autowired
    private MsCasSecurityProperties casSecurityProperties; 
	
    @Bean
    public ServiceProperties serviceProperties() {
        ServiceProperties serviceProperties = new ServiceProperties();
        //本机服务，访问/login/cas时进行校验登录
        serviceProperties.setService(casSecurityProperties.getAppServiceUrl() + "/login/cas");
        serviceProperties.setSendRenew(false);
        return serviceProperties;
    }

    @Bean
    @Primary
    public AuthenticationEntryPoint authenticationEntryPoint(
            ServiceProperties sP) {

        CasAuthenticationEntryPoint entryPoint
                = new CasAuthenticationEntryPoint();
        //cas登录服务
        entryPoint.setLoginUrl(casSecurityProperties.getCasServerUrl() + "/login");
        entryPoint.setServiceProperties(sP);
        return entryPoint;
    }

    @Bean
    public TicketValidator ticketValidator() {
        //指定cas校验器
        return new Cas30ServiceTicketValidator(
        		casSecurityProperties.getCasServerUrl());
    }

    //cas认证
    @Bean
    @Primary
    public CasAuthenticationProvider casAuthenticationProvider(AuthenticationUserDetailsService<CasAssertionAuthenticationToken> authenticationUserDetailsService) {

    	CasAuthenticationProvider provider = new CasAuthenticationProvider();
        provider.setServiceProperties(serviceProperties());
        provider.setTicketValidator(ticketValidator());
        provider.setAuthenticationUserDetailsService(authenticationUserDetailsService);
        //固定响应用户，在生产环境中需要额外设置用户映射
//      provider.setUserDetailsService(
//      s -> new User("auth-user", "123", true, true, true, true,
//              AuthorityUtils.createAuthorityList("ROLE_ADMIN")));
        provider.setKey("CAS_PROVIDER_LOCALHOST_8123");
        return provider;
    }

    @Bean
    @Primary
    public MsAuthenticationUserDetailsService authenticationUserDetailsService(UserDetailsService userDetailsService) {
    	return new MsAuthenticationUserDetailsService(userDetailsService);
    }
    @Bean
    @Primary
    public MsUserDetailsService userDetailsService() {
    	return new MsUserDetailsService();
    }

    @Bean
    public SecurityContextLogoutHandler securityContextLogoutHandler() {
        return new SecurityContextLogoutHandler();
    }

    @Bean
    public LogoutFilter logoutFilter() {
    	String logoutSuccessUrl = casSecurityProperties.getCasServerUrl() 
    			+ "/logout?service="
    			+ casSecurityProperties.getAppServiceUrl();

    	//退出成功后处理
    	UrlLogoutSuccessHandler urlLogoutSuccessHandler = new UrlLogoutSuccessHandler();
		if (StringUtils.hasText(logoutSuccessUrl)) {
			urlLogoutSuccessHandler.setDefaultTargetUrl(logoutSuccessUrl);
		}
        //退出后转发路径
//        LogoutFilter logoutFilter = new LogoutFilter(logoutSuccessUrl, securityContextLogoutHandler());
        LogoutFilter logoutFilter = new LogoutFilter(urlLogoutSuccessHandler, securityContextLogoutHandler());
        //cas退出
//        logoutFilter.setFilterProcessesUrl("/logout");
        return logoutFilter;
    }

    @Bean
    public SingleSignOutFilter singleSignOutFilter() {
        //单点退出
        SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
        singleSignOutFilter.setCasServerUrlPrefix(casSecurityProperties.getCasServerUrl());
        singleSignOutFilter.setIgnoreInitConfiguration(true);
        return singleSignOutFilter;
    }

    //设置退出监听
    @EventListener
    public SingleSignOutHttpSessionListener singleSignOutHttpSessionListener(
            HttpSessionEvent event) {
        return new SingleSignOutHttpSessionListener();
    }
    
    
}
package tp.ms.cas.security.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "cas.sec", ignoreUnknownFields = false)
public class MsCasSecurityProperties implements InitializingBean{

	@Autowired
	private Environment evn;
	
	String casServerUrl;
	
	String appServiceUrl;

	@Override
	public void afterPropertiesSet() throws Exception {
		casServerUrl = StringUtils.hasText(casServerUrl)?casServerUrl:"https://acc.m96.co:9443";
		if(!StringUtils.hasText(appServiceUrl)) {
			appServiceUrl = "http://localhost:";
			if(StringUtils.hasText(evn.getProperty("server.port") )) {
				appServiceUrl += evn.getProperty("server.port");
			}else {
				appServiceUrl += "8080";
			}
			if(StringUtils.hasText(evn.getProperty("server.servlet.context-path"))) {
				appServiceUrl += evn.getProperty("server.servlet.context-path");
			}
		}
	}
	
}

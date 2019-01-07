package tp.ms.base.rest.resource.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import lombok.extern.slf4j.Slf4j;
import tp.ms.base.rest.resource.converter.PolyJsonHttpMessageConverter;

/**
 * Hello world!
 *
 */

@Slf4j
@Configuration
@ComponentScan({
	"tp.ms.base.rest.resource.logic",
})
public class MsAppResourceConfig
{
	/**
	 * 配置消息转换器为fastjson
	 * @return
	 */
	@Bean 
	public HttpMessageConverter<Object> polyJsonHttpMessageConverter() {
		PolyJsonHttpMessageConverter fastConverter = new PolyJsonHttpMessageConverter();
		List<MediaType> supportedMediaTypes = new ArrayList<>();
		supportedMediaTypes.add(MediaType.APPLICATION_JSON);
		supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//		supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
//		supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
//		supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
//		supportedMediaTypes.add(MediaType.APPLICATION_PDF);
//		supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
//		supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
//		supportedMediaTypes.add(MediaType.APPLICATION_XML);
//		supportedMediaTypes.add(MediaType.IMAGE_GIF);
//		supportedMediaTypes.add(MediaType.IMAGE_JPEG);
//		supportedMediaTypes.add(MediaType.IMAGE_PNG);
//		supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
		supportedMediaTypes.add(MediaType.TEXT_HTML);
//		supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
		supportedMediaTypes.add(MediaType.TEXT_PLAIN);
//		supportedMediaTypes.add(MediaType.TEXT_XML);
		fastConverter.setSupportedMediaTypes(supportedMediaTypes);
		return fastConverter;
	}
	
	
	//如果没有使用默认值80 
	  @Value("${http.port:80}") 
	  Integer httpPort; 
	  
	  //正常启用的https端口 如443 
	  @Value("${server.port}") 
	  Integer httpsPort; 

	  @Bean
	  @ConditionalOnProperty(name="condition.http2https",havingValue="true", matchIfMissing=false) 
	  public TomcatServletWebServerFactory servletContainer() { 
	    TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() { 
	      @Override
	      protected void postProcessContext(Context context) { 
	        SecurityConstraint constraint = new SecurityConstraint(); 
	        constraint.setUserConstraint("CONFIDENTIAL"); 
	        SecurityCollection collection = new SecurityCollection(); 
	        collection.addPattern("/*"); 
	        constraint.addCollection(collection); 
	        context.addConstraint(constraint); 
	      } 
	    }; 
	    tomcat.addAdditionalTomcatConnectors(httpConnector()); 
	    return tomcat; 
	  } 
	  
	  @Bean
	  @ConditionalOnProperty(name="condition.http2https",havingValue="true", matchIfMissing=false) 
	  public Connector httpConnector() { 
	    log.info("启用http转https协议，http端口："+this.httpPort+"，https端口："+this.httpsPort); 
	    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol"); 
	    connector.setScheme("http"); 
	    //Connector监听的http的端口号 
	    connector.setPort(httpPort); 
	    connector.setSecure(false); 
	    //监听到http的端口号后转向到的https的端口号 
	    connector.setRedirectPort(httpsPort); 
	    return connector; 
	  }
}

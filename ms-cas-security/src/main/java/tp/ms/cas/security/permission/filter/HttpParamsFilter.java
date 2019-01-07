package tp.ms.cas.security.permission.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;

import lombok.val;
import lombok.extern.slf4j.Slf4j;


/**
 * 处理回跳地址
 * @author ms
 *
  |	处理的思路是，在登录之前记住访问地址及请求参数，在登录成功之后再取到这个地址然后回跳到对应的地址。
  |	首先我们需要写一个过滤器来获取我们的请求地址，并放到Session中。
 */
@Slf4j
public class HttpParamsFilter implements Filter {
    public static String REQUESTED_URL = "_CasRequestedUrl_";
    
    public static String oUrl;

    String appserverContext;
    public HttpParamsFilter(String appserverContext) {
    	this.appserverContext = appserverContext;
	}
    
    public final static String CACHE_ID = REQUESTED_URL + "k_redis_http_params_filter_01";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//                String headerName = (String) headerNames.nextElement();
//                String headerValues = request.getHeader(headerName);
//                log.info(headerName + " " + headerValues);
//        }
		if (needAddUri(request) && HttpMethod.GET.matches(request.getMethod())) {
//			MsMybatisRedisCache cache = new MsMybatisRedisCache(CACHE_ID);
//			Object oUrl = cache.getObject(REQUESTED_URL);
			val url = request.getRequestURL();
			if (oUrl == null) {
				val query = request.getQueryString();
				if (query != null)
					url.append("?").append(query);
				log.info("need login index before url {}", url);
//				cache.putObject(REQUESTED_URL, url);
				oUrl = url.toString();
			}
//			session.setAttribute(REQUESTED_URL, url);
		}
		chain.doFilter(request, response);
    }

    private boolean needAddUri(HttpServletRequest request) {
		return !("/login/cas".equals(request.getServletPath()) || this.appserverContext.equals(request.getServletPath())) || !matcherStaticFile(request);
	}

	private boolean matcherStaticFile(HttpServletRequest request) {
		String path = request.getServletPath();
		return path.endsWith(".css") 
				|| path.endsWith(".js") 
				|| path.endsWith(".ttf")
				|| path.endsWith(".png")
				|| path.endsWith(".jpg")
				|| path.endsWith(".svg") 
				|| path.endsWith(".ico") ;
	}

	@Override
    public void destroy() {

    }
    
 
}
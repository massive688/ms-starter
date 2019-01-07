package tp.ms.cas.security.permission.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.web.filter.GenericFilterBean;

import tp.ms.common.bean.support.context.MsEnvContextHolder;
import tp.ms.common.bean.vo.Business;

@WebFilter(urlPatterns="/*")
public class MsEnvContextBusinessFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Business business = MsEnvContextHolder.getContext().business();
		if(business == null) {
			business = new Business() {
			};
			MsEnvContextHolder.getContext().setBusiness(business);
		}
		business.setPkBilltype(loadBillTypeFromRequest(request));
		
		chain.doFilter(request, response);
	}

	private String loadBillTypeFromRequest(ServletRequest request) {
		String billType = null, sign = "bt";
		HttpServletRequestWrapper req;
		if(request instanceof HttpServletRequestWrapper) {
			req = (HttpServletRequestWrapper) request;
			billType = req.getParameter(sign);
			if(billType == null) {
				billType = req.getHeader(sign);
			}
			if(billType == null) {
				billType = (String) req.getAttribute(sign);
			}
			if(billType == null) {
				Cookie[] cooks = req.getCookies();
				for(Cookie cook : cooks) {
					if(sign.equalsIgnoreCase(cook.getName())) {
						billType = cook.getValue();
						break;
					}
				}
			}
		}
		return billType;
	}
}

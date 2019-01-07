package tp.ms.cas.security.permission.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.StringUtils;

import tp.ms.common.bean.support.context.MsEnvContextHolder;

public class UrlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    public UrlAuthenticationSuccessHandler() {
        super();
    }

    public UrlAuthenticationSuccessHandler(String defaultTargetUrl) {
        super(defaultTargetUrl);
    }

    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
        if (isAlwaysUseDefaultTargetUrl()) {
            return this.getDefaultTargetUrl();
        }

        // Check for the parameter and use that if available
        String targetUrl = null;

        if (this.getTargetUrlParameter() != null) {
            targetUrl = request.getParameter(this.getTargetUrlParameter());

            if (StringUtils.hasText(targetUrl)) {
                logger.debug("Found targetUrlParameter in request: " + targetUrl);

                return targetUrl;
            }
        }

        if (!StringUtils.hasText(targetUrl)) {
//        	MsMybatisRedisCache cache = new MsMybatisRedisCache(HttpParamsFilter.CACHE_ID);
//			Object oUrl = cache.getObject(HttpParamsFilter.REQUESTED_URL);
			String oUrl = MsEnvContextHolder.getContext().oUrl();
			if(oUrl != null)
				targetUrl = oUrl;
//            HttpSession session = request.getSession();
//            targetUrl = (String) session.getAttribute(HttpParamsFilter._REQUESTED_URL);
//            session.removeAttribute(HttpParamsFilter._REQUESTED_URL);
        }

        if (!StringUtils.hasText(targetUrl)) {
            targetUrl = this.getDefaultTargetUrl();
            logger.debug("Using default Url: " + targetUrl);
        }

        return targetUrl;
    }
}
package tp.ms.cas.security.config;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MsAccessDeniedHandlerImpl implements AccessDeniedHandler {

	public static String REQUESTED_URL = "CasRequestedUrl";

	public MsAccessDeniedHandlerImpl() {

	}

	private String errorPage;

	public String getAccessDeniedUrl() {
		return accessDeniedUrl;
	}

	/**
	 * 初始化访问无效跳转的地址
	 * 
	 * @param accessDeniedUrl
	 */
	public void setAccessDeniedUrl(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}

	public MsAccessDeniedHandlerImpl(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}

	private String accessDeniedUrl;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException reason)
			throws ServletException, IOException {

		boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

		log.debug(String.valueOf(isAjax));
		
		if (!response.isCommitted()) {
			if (errorPage != null) {
				// Put exception into request scope (perhaps of use to a view)
				request.setAttribute(WebAttributes.ACCESS_DENIED_403, reason);

				// Set the 403 status code.
				response.setStatus(HttpStatus.FORBIDDEN.value());

				// forward to error page.
				RequestDispatcher dispatcher = request.getRequestDispatcher(errorPage);
				dispatcher.forward(request, response);
			} else {
				response.sendError(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase());
			}
		}

		/**
		 * Map<String, Object> jsonObject = new HashMap<String, Object>();
		 * 
		 * 验证报错
		 * 
		 * if (reason instanceof MissingCsrfTokenException) { jsonObject.put("code",
		 * "4003"); jsonObject.put("msg", "AUTHORIZATION_FAILURE");
		 * 
		 * } else if (reason instanceof InvalidCsrfTokenException) {
		 * jsonObject.put("code", "4002"); CsrfToken csrfToken = (CsrfToken)
		 * request.getSession(false)
		 * .getAttribute(HttpSessionCsrfTokenRepository.class.getName().concat(".CSRF_TOKEN"));
		 * jsonObject.put("X-CSRF-TOKEN", csrfToken.getToken());
		 * 
		 * } else {
		 * 
		 * } System.out.println(request.getSession(false).getId()); // 如果是ajax请求你阿里斯顿 if
		 * (isAjax) {
		 * 
		 * // String jsonObject = "{\"message\":\"You are not privileged to request this
		 * // resource.\","+ //
		 * "\"access-denied\":true,\"cause\":\"AUTHORIZATION_FAILURE\"}"; String
		 * contentType = "application/json"; response.setContentType(contentType); //
		 * String jsonObject = "noright"; PrintWriter out = response.getWriter();
		 * out.print(JSON.toJSONString(jsonObject)); out.flush(); out.close(); return; }
		 * else { String path = request.getContextPath(); String basePath =
		 * request.getScheme() + "://" + request.getServerName() + ":" +
		 * request.getServerPort() + path + "/"; response.sendRedirect(basePath +
		 * accessDeniedUrl); }
		 */
	}

	/**
	 * The error page to use. Must begin with a "/" and is interpreted relative to
	 * the current context root.
	 *
	 * @param errorPage the dispatcher path to display
	 *
	 * @throws IllegalArgumentException if the argument doesn't comply with the
	 *                                  above limitations
	 */
	public void setErrorPage(String errorPage) {
		if ((errorPage != null) && !errorPage.startsWith("/")) {
			throw new IllegalArgumentException("errorPage must begin with '/'");
		}

		this.errorPage = errorPage;
	}
}
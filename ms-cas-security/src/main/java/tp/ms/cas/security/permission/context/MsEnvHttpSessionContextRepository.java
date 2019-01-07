package tp.ms.cas.security.permission.context;

import java.security.Principal;

import javax.servlet.AsyncContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.cas.authentication.CasAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.web.util.WebUtils;

import lombok.val;
import lombok.extern.slf4j.Slf4j;
import tp.ms.cas.security.permission.entity.JwtUser;
import tp.ms.common.bean.support.context.MsContext;
import tp.ms.common.bean.support.context.MsEnvContextHolder;
import tp.ms.common.bean.vo.User;

@Slf4j
@SuppressWarnings("unused")
public class MsEnvHttpSessionContextRepository {
	/*
	 * The default key under which the security context will be stored in the session.
	 */
	public static final String MS_ENV_SPRING_CONTEXT_KEY = "MS_ENV_SPRING_CONTEXT";

	protected final Log logger = LogFactory.getLog(this.getClass());

	/*
	 * SecurityContext instance used to check for equality with default (unauthenticated)
	 * content
	 */
	private final Object contextObject = SecurityContextHolder.createEmptyContext();
	private boolean allowSessionCreation = true;
	private boolean disableUrlRewriting = false;
	private boolean isServlet3 = ClassUtils.hasMethod(ServletRequest.class, "startAsync");
	private String msEnvSpringContextKey = MS_ENV_SPRING_CONTEXT_KEY;

	private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

	/*
	 * Gets the security context for the current request (if available) and returns it.
	 * <p>
	 * If the session is null, the context object is null or the context object stored in
	 * the session is not an instance of {@code SecurityContext}, a new context object
	 * will be generated and returned.
	 */
	public MsContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
		HttpServletRequest request = requestResponseHolder.getRequest();
		HttpServletResponse response = requestResponseHolder.getResponse();
		HttpSession httpSession = request.getSession(false);

		MsContext context = readMsContextFromSession(httpSession);
		
		if (context == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("No SecurityContext was available from the HttpSession: "
						+ httpSession + ". " + "A new one will be created.");
			}
			context = generateNewContext();
			setOUrl(request, context);
		}
		

		
		MsSaveToSessionResponseWrapper wrappedResponse = new MsSaveToSessionResponseWrapper(
				response, request, httpSession != null, context);
		requestResponseHolder.setResponse(wrappedResponse);

		if (isServlet3) {
			requestResponseHolder.setRequest(new Servlet3SaveToSessionRequestWrapper(
					request, wrappedResponse));
		}

		return context;
	}
	
	public void saveContext(MsContext context, HttpServletRequest request,
			HttpServletResponse response) {
		MsSaveToSessionResponseWrapper responseWrapper = WebUtils
				.getNativeResponse(response,
						MsSaveToSessionResponseWrapper.class);
		if (responseWrapper == null) {
			throw new IllegalStateException(
					"Cannot invoke saveContext on response "
							+ response
							+ ". You must use the HttpRequestResponseHolder.response after invoking loadContext");
		}
		// saveContext() might already be called by the response wrapper
		// if something in the chain called sendError() or sendRedirect(). This ensures we
		// only call it
		// once per request.
		if (!responseWrapper.isContextSaved()) {
			responseWrapper.saveContext(context);
		}
	}

	public boolean containsContext(HttpServletRequest request) {
		HttpSession session = request.getSession(false);

		if (session == null) {
			return false;
		}

		return session.getAttribute(msEnvSpringContextKey) != null;
	}

	/**
	 *
	 * @param httpSession the session obtained from the request.
	 */
	private MsContext readMsContextFromSession(HttpSession httpSession) {
		final boolean debug = logger.isDebugEnabled();

		if (httpSession == null) {
			if (debug) {
				logger.debug("No HttpSession currently exists");
			}

			return null;
		}

		// Session exists, so try to obtain a context from it.

		Object contextFromSession = httpSession.getAttribute(msEnvSpringContextKey);

		if (contextFromSession == null) {
			if (debug) {
				logger.debug("HttpSession returned null object for MS_ENV_SPRING_CONTEXT");
			}

			return null;
		}

		// We now have the security context object from the session.
		if (!(contextFromSession instanceof MsContext)) {
			if (logger.isWarnEnabled()) {
				logger.warn(msEnvSpringContextKey
						+ " did not contain a MsEnvSpringContextKey but contained: '"
						+ contextFromSession
						+ "'; are you improperly modifying the HttpSession directly "
						+ "(you should always use SecurityContextHolder) or using the HttpSession attribute "
						+ "reserved for this class?");
			}

			return null;
		}

		if (debug) {
			logger.debug("Obtained a valid SecurityContext from "
					+ msEnvSpringContextKey + ": '" + contextFromSession + "'");
		}

		// Everything OK. The only non-null return from this method.

		return (MsContext) contextFromSession;
	}

	/**
	 * By default, calls {@link SecurityContextHolder#createEmptyContext()} to obtain a
	 * new context (there should be no context present in the holder when this method is
	 * called). Using this approach the context creation strategy is decided by the
	 * {@link SecurityContextHolderStrategy} in use. The default implementations will
	 * return a new <tt>SecurityContextImpl</tt>.
	 *
	 * @return a new SecurityContext instance. Never null.
	 */
	protected MsContext generateNewContext() {
		return MsEnvContextHolder.createEmptyContext();
	}

	/*
	 * If set to true (the default), a session will be created (if required) to store the
	 * security context if it is determined that its contents are different from the
	 * default empty context value.
	 * <p>
	 * Note that setting this flag to false does not prevent this class from storing the
	 * security context. If your application (or another filter) creates a session, then
	 * the security context will still be stored for an authenticated user.
	 *
	 * @param allowSessionCreation
	 */
	public void setAllowSessionCreation(boolean allowSessionCreation) {
		this.allowSessionCreation = allowSessionCreation;
	}

	/*
	 * Allows the use of session identifiers in URLs to be disabled. Off by default.
	 *
	 * @param disableUrlRewriting set to <tt>true</tt> to disable URL encoding methods in
	 * the response wrapper and prevent the use of <tt>jsessionid</tt> parameters.
	 */
	public void setDisableUrlRewriting(boolean disableUrlRewriting) {
		this.disableUrlRewriting = disableUrlRewriting;
	}

	/*
	 * Allows the session attribute name to be customized for this repository instance.
	 *
	 * @param msEnvSpringContextKey the key under which the security context will be
	 * stored. Defaults to {@link #SPRING_SECURITY_CONTEXT_KEY}.
	 */
	public void setMsEnvSpringContextKey(String msEnvSpringContextKey) {
		Assert.hasText(msEnvSpringContextKey,
				"msEnvSpringContextKey cannot be empty");
		this.msEnvSpringContextKey = msEnvSpringContextKey;
	}

	// ~ Inner Classes
	// ==================================================================================================

	private static class Servlet3SaveToSessionRequestWrapper extends
			HttpServletRequestWrapper {
		private final MsSaveToSessionResponseWrapper response;

		public Servlet3SaveToSessionRequestWrapper(HttpServletRequest request,
				MsSaveToSessionResponseWrapper wrappedResponse) {
			super(request);
			this.response = wrappedResponse;
		}

		@Override
		public AsyncContext startAsync() {
//			response.disableSaveOnResponseCommitted();
			response.saveContext(MsEnvContextHolder.getContext());
			return super.startAsync();
		}

		@Override
		public AsyncContext startAsync(ServletRequest servletRequest,
				ServletResponse servletResponse) throws IllegalStateException {
//			response.disableSaveOnResponseCommitted();
			return super.startAsync(servletRequest, servletResponse);
		}
	}

	/*
	 * Wrapper that is applied to every request/response to update the
	 * <code>HttpSession<code> with
	 * the <code>SecurityContext</code> when a <code>sendError()</code> or
	 * <code>sendRedirect</code> happens. See SEC-398.
	 * <p>
	 * Stores the necessary state from the start of the request in order to make a
	 * decision about whether the security context has changed before saving it.
	 */
	final class MsSaveToSessionResponseWrapper extends MsSaveContextOnUpdateOrErrorResponseWrapper {

		private final HttpServletRequest request;
		private boolean httpSessionExistedAtStartOfRequest;
		private MsContext contextBeforeExecution;
		private Authentication authBeforeExecution;

		/*
		 * Takes the parameters required to call <code>saveContext()</code> successfully
		 * in addition to the request and the response object we are wrapping.
		 *
		 * @param request the request object (used to obtain the session, if one exists).
		 * @param httpSessionExistedAtStartOfRequest indicates whether there was a session
		 * in place before the filter chain executed. If this is true, and the session is
		 * found to be null, this indicates that it was invalidated during the request and
		 * a new session will now be created.
		 * @param context the context before the filter chain executed. The context will
		 * only be stored if it or its contents changed during the request.
		 */
		MsSaveToSessionResponseWrapper(HttpServletResponse response,
				HttpServletRequest request, boolean httpSessionExistedAtStartOfRequest,
				MsContext context) {
			super(response, httpSessionExistedAtStartOfRequest);
			this.request = request;
			this.httpSessionExistedAtStartOfRequest = httpSessionExistedAtStartOfRequest;
			this.contextBeforeExecution = context;
		}


		/*
		 * Stores the supplied security context in the session (if available) and if it
		 * has changed since it was set at the start of the request. If the
		 * AuthenticationTrustResolver identifies the current user as anonymous, then the
		 * context will not be stored.
		 *
		 * @param context the context object obtained from the SecurityContextHolder after
		 * the request has been processed by the filter chain.
		 * SecurityContextHolder.getContext() cannot be used to obtain the context as it
		 * has already been cleared by the time this method is called.
		 *
		 */
		protected void saveContext(MsContext context) {
			final User authentication = context.user();
			HttpSession httpSession = request.getSession(false);

			/*
			 * 保存MsContext到Session
			 * 
			 * 去掉不需要的
			 * 
			// See SEC-776
			if (authentication == null ) {
				if (logger.isDebugEnabled()) {
					logger.debug("SecurityContext is empty or contents are anonymous - context will not be stored in HttpSession.");
				}

				if (httpSession != null && authBeforeExecution != null) {
					// SEC-1587 A non-anonymous context may still be in the session
					// SEC-1735 remove if the contextBeforeExecution was not anonymous
					httpSession.removeAttribute(msEnvSpringContextKey);
				}
				return;
			}

			if (httpSession == null) {
				httpSession = createNewSessionIfAllowed(context);
			}
			*/
			
			// If HttpSession exists, store current SecurityContext but only if it has
			// actually changed in this thread (see SEC-37, SEC-1307, SEC-1528)
			if (httpSession != null) {
				// We may have a new session, so check also whether the context attribute
				// is set SEC-1561
				if (contextChanged(context)
						|| httpSession.getAttribute(msEnvSpringContextKey) == null) {
					httpSession.setAttribute(msEnvSpringContextKey, context);

					if (logger.isDebugEnabled()) {
						logger.debug("SecurityContext '" + context
								+ "' stored to HttpSession: '" + httpSession);
					}
				}
			}
		}

		private boolean contextChanged(MsContext context) {
			return context != contextBeforeExecution
//					|| context.user() != authBeforeExecution	///原有的在本案例中没有用的部分
					;
		}

		private HttpSession createNewSessionIfAllowed(MsContext context) {
			if (httpSessionExistedAtStartOfRequest) {
				if (logger.isDebugEnabled()) {
					logger.debug("HttpSession is now null, but was not null at start of request; "
							+ "session was invalidated, so do not create a new session");
				}

				return null;
			}

			if (!allowSessionCreation) {
				if (logger.isDebugEnabled()) {
					logger.debug("The HttpSession is currently null, and the "
							+ MsEnvHttpSessionContextRepository.class.getSimpleName()
							+ " is prohibited from creating an HttpSession "
							+ "(because the allowSessionCreation property is false) - SecurityContext thus not "
							+ "stored for next request");
				}

				return null;
			}
			// Generate a HttpSession only if we need to

			if (contextObject.equals(context)) {
				if (logger.isDebugEnabled()) {
					logger.debug("HttpSession is null, but SecurityContext has not changed from default empty context: ' "
							+ context
							+ "'; not creating HttpSession or storing SecurityContext");
				}

				return null;
			}

			if (logger.isDebugEnabled()) {
				logger.debug("HttpSession being created as SecurityContext is non-default");
			}

			try {
				return request.getSession(true);
			}
			catch (IllegalStateException e) {
				// Response must already be committed, therefore can't create a new
				// session
				logger.warn("Failed to create a session, as response has been committed. Unable to store"
						+ " SecurityContext.");
			}

			return null;
		}

	}

	/*
	 * Sets the {@link AuthenticationTrustResolver} to be used. The default is
	 * {@link AuthenticationTrustResolverImpl}.
	 *
	 * @param trustResolver the {@link AuthenticationTrustResolver} to use. Cannot be
	 * null.
	 */
	public void setTrustResolver(AuthenticationTrustResolver trustResolver) {
		Assert.notNull(trustResolver, "trustResolver cannot be null");
		this.trustResolver = trustResolver;
	}
	


	/*
	 * 设置 没有 登录的用户 获取登录前，也就是当前访问的地址
	 * ===及请求参数
	 *
	 * @param request
	 * @param context
	 */
	private void setOUrl(HttpServletRequest request, MsContext context) {
		if(isInterceptUrl(request)) {
			val url = request.getRequestURL();
			val query = request.getQueryString();
			if (query != null)
				url.append("?").append(query);
			if(matcherStaticFile(request)) {
				context.setOUrl(getRootPath(request));
			}else {
				context.setOUrl(url.toString());
			}
			log.info(" need saved url {} before login", url);
		}
	}
	private boolean isInterceptUrl(HttpServletRequest request) {
		String path = request.getServletPath();
		return path.endsWith(".css") 
				|| path.endsWith(".js") 
				|| path.endsWith(".ttf")
				|| path.endsWith(".png")
				|| path.endsWith(".jpg")
				|| path.endsWith(".svg") 
				|| path.endsWith(".ico") 
				|| !"/login/cas".equals(request.getServletPath());
	}

	private String getRootPath(HttpServletRequest request) {
		//客户端的IP及端口 信息
		val addr = request.getRemoteAddr();
		String host = request.getRemoteHost();
		int port = request.getRemotePort();
		//http
		val scheme = request.getScheme();
		//域名或访问的IP
		val sname = request.getServerName();
		//访问的端口
		val sport = request.getServerPort();
		//服务器地址
		val s = request.getLocalAddr();
		//服务器 机器名称
		val s1 = request.getLocalName();
		//服务端接收端口
		val s11 = request.getLocalPort();
		//应用名
		String context = request.getContextPath();
		//访问的应用Mapping
		String path = request.getServletPath();
		//访问的全路径
		val url = request.getRequestURL();
		return scheme + "://" + sname + ":" + sport + "/" + context;
	}

	private boolean matcherStaticFile(HttpServletRequest request) {
		val url = request.getRequestURL();
		String path = request.getServletPath();
		return path.endsWith(".css") 
				|| path.endsWith(".js") 
				|| path.endsWith(".ttf")
				|| path.endsWith(".png")
				|| path.endsWith(".jpg")
				|| path.endsWith(".svg") 
				|| path.endsWith(".ico") ;
	}
}

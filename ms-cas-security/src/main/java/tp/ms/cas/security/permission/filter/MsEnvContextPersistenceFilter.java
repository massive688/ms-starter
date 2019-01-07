package tp.ms.cas.security.permission.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.web.filter.GenericFilterBean;

import tp.ms.cas.security.permission.context.MsEnvHttpSessionContextRepository;
import tp.ms.common.bean.support.context.MsContext;
import tp.ms.common.bean.support.context.MsEnvContextHolder;

@WebFilter(urlPatterns="/*")
public class MsEnvContextPersistenceFilter extends GenericFilterBean {

	static final String FILTER_APPLIED = "__spring_msenv_scpf_applied";

	private MsEnvHttpSessionContextRepository repo;

	private boolean forceEagerSessionCreation = false;

	public MsEnvContextPersistenceFilter() {
		this(new MsEnvHttpSessionContextRepository());
	}

	public MsEnvContextPersistenceFilter(MsEnvHttpSessionContextRepository msEnvHttpSessionContextRepository) {
		this.repo = msEnvHttpSessionContextRepository;
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		if (request.getAttribute(FILTER_APPLIED) != null) {
			// ensure that filter is only applied once per request
			chain.doFilter(request, response);
			return;
		}
//		String body = HttpHelper.getBodyString(request);
//		logger.info(body);

		final boolean debug = logger.isDebugEnabled();

		request.setAttribute(FILTER_APPLIED, Boolean.TRUE);

		if (forceEagerSessionCreation) {
			HttpSession session = request.getSession();

			if (debug && session.isNew()) {
				logger.debug("Eagerly created session: " + session.getId());
			}
		}

		HttpRequestResponseHolder holder = new HttpRequestResponseHolder(request,
				response);
		MsContext contextBeforeChainExecution = repo.loadContext(holder);

		try {
			MsEnvContextHolder.setContext(contextBeforeChainExecution);

			chain.doFilter(holder.getRequest(), holder.getResponse());

		}
		finally {
			MsContext contextAfterChainExecution = MsEnvContextHolder
					.getContext();
			// Crucial removal of SecurityContextHolder contents - do this before anything
			// else.
			SecurityContextHolder.clearContext();
			repo.saveContext(contextAfterChainExecution, holder.getRequest(),
					holder.getResponse());
			request.removeAttribute(FILTER_APPLIED);

			if (debug) {
				logger.debug("SecurityContextHolder now cleared, as request processing completed");
			}
		}
	}

	public void setForceEagerSessionCreation(boolean forceEagerSessionCreation) {
		this.forceEagerSessionCreation = forceEagerSessionCreation;
	}
}

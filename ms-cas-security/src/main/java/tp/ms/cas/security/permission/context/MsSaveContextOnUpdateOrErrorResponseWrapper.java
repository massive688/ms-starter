/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tp.ms.cas.security.permission.context;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.SaveContextOnUpdateOrErrorResponseWrapper;

import tp.ms.common.bean.support.context.MsContext;
import tp.ms.common.bean.support.context.MsEnvContextHolder;

public abstract class MsSaveContextOnUpdateOrErrorResponseWrapper
		extends MsOnCommittedResponseWrapper {


	private boolean contextSaved = false;
	
	private final boolean disableUrlRewriting;

	/*
	 * @param response the response to be wrapped
	 * @param disableUrlRewriting turns the URL encoding methods into null operations,
	 * preventing the use of URL rewriting to add the session identifier as a URL
	 * parameter.
	 */
	public MsSaveContextOnUpdateOrErrorResponseWrapper(HttpServletResponse response,
			boolean disableUrlRewriting) {
		super(response);
		this.disableUrlRewriting = disableUrlRewriting;
	}

	/*
	 * Invoke this method to disable automatic saving of the {@link SecurityContext} when
	 * the {@link HttpServletResponse} is committed. This can be useful in the event that
	 * Async Web Requests are made which may no longer contain the {@link SecurityContext}
	 * on it.
	 */
	public void disableSaveOnResponseCommitted() {
		disableOnResponseCommitted();
	}

	/*
	 * Implements the logic for storing the security context.
	 *
	 * @param context the <tt>SecurityContext</tt> instance to store
	 */
	protected abstract void saveContext(MsContext context);

	/*
	 * Calls <code>saveContext()</code> with the current contents of the
	 * <tt>SecurityContextHolder</tt> as long as {@link #disableSaveOnResponseCommitted()
	 * ()} was not invoked.
	 */
	@Override
	protected void onResponseCommitted() {
		saveContext(MsEnvContextHolder.getContext());
		this.contextSaved = true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public final String encodeRedirectUrl(String url) {
		if (this.disableUrlRewriting) {
			return url;
		}
		return super.encodeRedirectUrl(url);
	}

	@Override
	public final String encodeRedirectURL(String url) {
		if (this.disableUrlRewriting) {
			return url;
		}
		return super.encodeRedirectURL(url);
	}

	@SuppressWarnings("deprecation")
	@Override
	public final String encodeUrl(String url) {
		if (this.disableUrlRewriting) {
			return url;
		}
		return super.encodeUrl(url);
	}

	@Override
	public final String encodeURL(String url) {
		if (this.disableUrlRewriting) {
			return url;
		}
		return super.encodeURL(url);
	}

	/*
	 * Tells if the response wrapper has called <code>saveContext()</code> because of this
	 * wrapper.
	 */
	public final boolean isContextSaved() {
		return this.contextSaved;
	}
}

/*
 * Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
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

package tp.ms.common.bean.support.context;

import org.springframework.util.Assert;

/**
 * A <code>ThreadLocal</code>-based implementation of
 * {@link MsContextHolderStrategy}.
 *
 * @author Ben Alex
 *
 * @see java.lang.ThreadLocal
 * @see org.springframework.security.core.context.web.MsContextPersistenceFilter
 */
final class MsThreadLocalEnvContextHolderStrategy {
	// ~ Static fields/initializers
	// =====================================================================================

	private static final ThreadLocal<MsContext> contextHolder = new ThreadLocal<>();

	// ~ Methods
	// ========================================================================================================

	public void clearContext() {
		contextHolder.remove();
	}

	public MsContext getContext() {
		MsContext ctx = contextHolder.get();

		if (ctx == null) {
			ctx = createEmptyContext();
			contextHolder.set(ctx);
		}

		return ctx;
	}

	public void setContext(MsContext context) {
		Assert.notNull(context, "Only non-null MsContext instances are permitted");
		contextHolder.set(context);
	}

	public MsContext createEmptyContext() {
		return new MsContextImpl();
	}
}

package tp.ms.common.bean.support.context;

import org.springframework.util.Assert;

public class MsEnvContextHolder {
	
	private static MsThreadLocalEnvContextHolderStrategy strategy;
	
	static {
		initialize();
	}
	
	public static void clearContext() {
		strategy.clearContext();
	}

	public static MsContext getContext() {
		return strategy.getContext();
	}

	public static void setContext(MsContext context) {
		Assert.notNull(context, "Only non-null SecurityContext instances are permitted");
		strategy.setContext(context);
	}

	public static MsContext createEmptyContext() {
		return strategy.createEmptyContext();
	}
	
	private static void initialize() {
		strategy = new MsThreadLocalEnvContextHolderStrategy();
	}
}

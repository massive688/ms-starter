package tp.ms.common.data.mybatis.properties;

import org.springframework.beans.factory.InitializingBean;

import lombok.Data;

@Data
public class MsXAProperties implements InitializingBean{

	private int minPoolSize;

	private int maxPoolSize;

	private int maxLifetime;

	private int borrowConnectionTimeout;

	private int loginTimeout;

	private int maintenanceInterval;

	private int maxIdleTime;

	private String testQuery;

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(this.testQuery);
	}
}

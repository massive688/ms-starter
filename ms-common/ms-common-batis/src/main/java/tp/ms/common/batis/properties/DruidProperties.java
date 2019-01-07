package tp.ms.common.batis.properties;

import org.springframework.beans.factory.InitializingBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class DruidProperties implements InitializingBean {

	String type;
	
	int initialSize;
	
	int minIdle;
	
	int maxActive;
	
	long maxWait;
	
	long timeBetweenEvictionRunsMillis;
	
	long minEvictableIdleTimeMillis;
	
	String validationQuery;
	
	boolean testWhileIdle;
	
	boolean testOnBorrow;
	
	boolean testOnReturn;
	
	String poolPreparedStatements;
	
	String maxPoolPreparedStatementPerConnectionSize;
	
	String filters;
	
	String connectionProperties;
	
	boolean logSlowSql;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("DruidDataSourceProperties toStringValue:{}", this.toString());
	}
	
	
}
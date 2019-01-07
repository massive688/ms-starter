package tp.ms.common.data.mybatis.properties;

import org.springframework.beans.factory.InitializingBean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class DSourceProperties implements InitializingBean {

	String driverClassName;
	
	String url;
	
	String username;
	
	String password;
	
	String dbType; //JdbcUtils
	
	String key;
	
	boolean primary = false;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("DruidDataSourceProperties toStringValue:{}", this.toString());
	}
	
	
}
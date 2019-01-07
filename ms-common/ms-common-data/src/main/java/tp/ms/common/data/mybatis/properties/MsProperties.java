package tp.ms.common.data.mybatis.properties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Data
@ConfigurationProperties( prefix="ms.ds")
public class MsProperties implements InitializingBean {

	@NestedConfigurationProperty
	List<DSourceProperties> source = new ArrayList<>();

	@NestedConfigurationProperty
	MsXAProperties xas = new MsXAProperties();
	
	@NestedConfigurationProperty
	DruidProperties druid = new DruidProperties();
	
	String mapperScan;
	
	String[] mapperLocation;
	
	@Override	
	public void afterPropertiesSet() throws Exception {
		if(mapperScan == null)
			mapperScan = "com.my.*,com.ms.*,com.ams.*";
		if(mapperLocation == null)
			mapperLocation = new String[] {"classpath*:*mappers/**/*.xml"};
		log.info("do conf {} datasource", source.size());
	}
	
	public Resource[] resolveMapperLocations() {
	    ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
	    List<Resource> resources = new ArrayList<Resource>();
	    if (this.mapperLocation != null) {
	      for (String mapperLocation : this.mapperLocation) {
	        try {
	          Resource[] mappers = resourceResolver.getResources(mapperLocation);
	          resources.addAll(Arrays.asList(mappers));
	        } catch (IOException e) {
	          // ignore
	        }
	      }
	    }
	    return resources.toArray(new Resource[resources.size()]);
	  }

	public ExecutorType getExecutorType() {
		// TODO Auto-generated method stub
		return null;
	}

	public Properties getConfigurationProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getConfigLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	public Configuration getConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTypeHandlersPackage() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTypeAliasesPackage() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isCheckConfigLocation() {
		// TODO Auto-generated method stub
		return false;
	}
}

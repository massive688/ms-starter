package tp.ms.common.bean.proploader;

public class PropertiesLoader extends AbstractConfigFileCompatiblePropertiesLoader {

	@Override
	public String[] locations() {
		return new String[]{
				"classpath:/application.yml",
		};
	}

}

/**

org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.ms.support.security.CasWebSecurityConfiguration,com.ms.support.security.SecurityConfiguration

org.springframework.boot.env.EnvironmentPostProcessor=\
com.ms.support.security.loader.CasPrope

*/
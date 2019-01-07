package tp.ms.common.batis.session;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.slf4j.Slf4j;
import tp.ms.common.batis.cfg.DruidStatViewServletFilterConfiguration;
import tp.ms.common.batis.properties.MsProperties;

@Slf4j
@Configuration
@EnableConfigurationProperties(value = {MsProperties.class })
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass=true)
@ComponentScan({
	"tp.ms.common.batis.aspect",
})
@Import({
	MsDynamicSessionTemplateRegister.class,
	DruidStatViewServletFilterConfiguration.class
	})
public class MsMapperScannerConfiguration {
	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer( ) {
		MsMapperScannerConfigurer msc = new MsMapperScannerConfigurer();
		msc.setSqlSessionTemplateBeanName("sqlSessionTemplate");
		msc.setBasePackage("com.mapper.*");
		log.info("registory mapper-scan-configurer");
		return msc;
	}
	
	
}

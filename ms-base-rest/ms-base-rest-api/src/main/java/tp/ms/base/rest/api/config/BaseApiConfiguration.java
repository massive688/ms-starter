package tp.ms.base.rest.api.config;

import javax.annotation.PostConstruct;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import tp.ms.base.rest.quartz.config.QuartzSchedulerConfigure;

@Configuration
@ComponentScan({
	"tp.ms.base.rest.formula.impl",
	"tp.ms.base.rest.formula.handle",
	"tp.ms.base.rest.refinfo.impl",
	"tp.ms.base.rest.quartz.impl",
	"tp.ms.base.rest.typecoded.impl",
	"tp.ms.base.rest.typecoded.translate",
	"tp.ms.base.rest.defaultacit.casynchro.impl",
	"tp.ms.base.rest.defaultacit.casynchro.translate",
	
	"tp.ms.base.rest.generator.freemarker.rest",
	"tp.ms.base.rest.generator.freemarker.service.impl",
	

	"tp.ms.base.rest.process.impl",
	
	"tp.ms.base.rest.runevn.supper",
	"tp.ms.base.rest.api",
})
@MapperScan({
	"tp.ms.base.rest.formula.mapper",
	"tp.ms.base.rest.typecoded.mapper",
	"tp.ms.base.rest.quartz.mapper",
	"tp.ms.base.rest.refinfo.mapper",
	"tp.ms.base.rest.generator.freemarker.mapper",
	"tp.ms.base.rest.defaultacit.casynchro.mapper",

	"tp.ms.base.rest.process.mapper",
})
@Import({QuartzSchedulerConfigure.class, ErrorPageConfig.class})
public class BaseApiConfiguration {
     
	
	 
	@PostConstruct
	public void main() {
		String tp = "                               __\r\n" + 
				"   /\\\\   __________  _______   \\ \\\r\n" + 
				"  (()·	|___	___| |	__  |   \\ \\\r\n" + 
				"   \\//      |  |     | |__| |    \\ \\\r\n" + 
				"    |       |  |     |  ____|    / /\r\n" + 
				"    |       |  |     | |	/ / \r\n" + 
				"  __|__     |__|     |_|       /_/\r\n"+
				"                                                         ";
		System.out.println(tp);
	}
				
}

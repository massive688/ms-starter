package tp.ms.base.rest.ots.orgs.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
	"tp.ms.base.rest.ots.orgs.impl",
	"tp.ms.base.rest.ots.orgs.translate",
	"tp.ms.base.rest.ots.staff.impl",
	"tp.ms.base.rest.ots.staff.translate",
})
@MapperScan({
	"tp.ms.base.rest.ots.orgs.entity",
	"tp.ms.base.rest.ots.staff.entity",
})
public class OtsConfiguration {

}

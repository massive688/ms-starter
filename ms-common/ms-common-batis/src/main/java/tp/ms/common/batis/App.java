package tp.ms.common.batis;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

import tp.ms.common.bean.support.context.SpringContextHolder;

@SpringBootApplication
public class App extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(App.class);
	}
	
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(App.class, args);
		SpringContextHolder.setApplicationContext(context);
	}
}

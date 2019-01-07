package tp.ms.common.data.mybatis.config;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;

import lombok.extern.slf4j.Slf4j;
import tp.ms.common.data.mybatis.properties.MsProperties;
import tp.ms.common.data.mybatis.session.MsMapperScannerConfigurer;
import tp.ms.common.data.register.MsDynamicSessionSourceRegister;

@Slf4j
@Configuration
@Import({MsDynamicSessionSourceRegister.class})
@EnableConfigurationProperties(value = {MsProperties.class })
@EnableAspectJAutoProxy(proxyTargetClass=true)
@ComponentScan({
	"tp.ms.common.data.mybatis.aspect",
})
public class MsMapperScannerConfiguration implements EnvironmentAware {

	@Autowired
    private Environment evn;
	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer( ) {
		String scan = evn.getProperty("ms.dyn.myb.mapper-scan");
		if(scan.startsWith("com.")) scan = "";
		MsMapperScannerConfigurer msc = new MsMapperScannerConfigurer();
		msc.setSqlSessionTemplateBeanName("sqlSessionTemplate");
		msc.setBasePackage("classpath*:com.*, classpath*:tp.*, classpath*:org.flowable.*,"+scan);
		log.info("registory mapper-scan-configurer");
		return msc;
	}
	
	  @Bean(name = "userTransaction")
	    public UserTransaction userTransaction() throws Throwable {
	        UserTransactionImp userTransactionImp = new UserTransactionImp();
	        userTransactionImp.setTransactionTimeout(10000);
	        return userTransactionImp;
	    }

	    @Bean(name = "atomikosTransactionManager", initMethod = "init", destroyMethod = "close")
	    public TransactionManager atomikosTransactionManager() throws Throwable {
	        UserTransactionManager userTransactionManager = new UserTransactionManager();
	        userTransactionManager.setForceShutdown(false);
	        return userTransactionManager;
	    }

	    @Bean(name = "jtaTransactionManager")
	    @DependsOn({ "userTransaction", "atomikosTransactionManager" })
	    public PlatformTransactionManager jtaTransactionManager() throws Throwable {
	        return new JtaTransactionManager(userTransaction(), atomikosTransactionManager());
	    }
	    
	    
	    @Bean(name = "transactionManager")
	    @Primary
	    public PlatformTransactionManager transactionManager(DataSource dataSource) throws Throwable {
	        return new DataSourceTransactionManager(dataSource);
	    }

		@Override
		public void setEnvironment(Environment environment) {
			this.evn = environment;
		}
	    
	
}

package tp.ms.common.data.jpa.config;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import tp.ms.common.data.register.MsDynamicSessionSourceRegister;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "jpaTransactionManager",
        basePackages = {MsJpaRepositorieConfiguration.PACKAGE}
)
@Import(MsDynamicSessionSourceRegister.class)
public class MsJpaRepositorieConfiguration {

	/**
	 * https://blog.csdn.net/tianyaleixiaowu/article/details/78905149
	 */
	
	static final String PACKAGE = "com.my.*,com.ms.*,com.ams.*";

	@Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private DataSource primaryDataSource;

    private Map<String, Object> getVendorProperties() {
        return jpaProperties.getHibernateProperties(new HibernateSettings());
    }
 
    /**
     * 设置实体类所在位置
    *
    * @param builder
    * @return
    */
   @Bean(name = "entityManagerFactory")
   @Primary
   public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {

       return builder
               //设置数据源
               .dataSource(primaryDataSource)
               //设置数据源属性
               .properties(getVendorProperties())
               //设置实体类所在位置.扫描所有带有 @Entity 注解的类
               .packages("com.itguang.springbootmultidatasource.domain")
               // Spring会将EntityManagerFactory注入到Repository之中.有了 EntityManagerFactory之后,
               // Repository就能用它来创建 EntityManager 了,然后Entity就可以针对数据库执行操作
               .persistenceUnit("primaryPersistenceUnit")
               .build();

   }
    /**
     * 事物管理器
     *
     * @param builder
     * @return
     */
    @Bean(name = "jpaTransactionManager")
    PlatformTransactionManager jpaTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactory(builder).getObject());
    }
	
}

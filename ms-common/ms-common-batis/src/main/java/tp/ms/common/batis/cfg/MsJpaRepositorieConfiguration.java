package tp.ms.common.batis.cfg;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager",
        basePackages = {MsJpaRepositorieConfiguration.PACKAGE})
@AutoConfigureAfter(EntityManagerFactoryBuilder.class)
public class MsJpaRepositorieConfiguration {

	static final String PACKAGE = "com.my.*,com.ms.*,com.ams.*";

	@Autowired
    private JpaProperties jpaProperties;

	@Autowired
    private DataSource dataSource;

	@Autowired
	EntityManagerFactoryBuilder builder;
	
//    @Bean
//    public EntityManager entityManager() {
//        return entityManagerFactory().getObject().createEntityManager();
//    }
	
   /*
     * 设置实体类所在位置
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        return builder
        		.dataSource(dataSource)
                .packages(MsJpaRepositorieConfiguration.PACKAGE)
                .persistenceUnit("defaultUnit")
                .properties(getVendorProperties())
                .build();
    }
 
    @Bean
    public JpaTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }
    
    private Map<String, Object> getVendorProperties() {
        return jpaProperties.getHibernateProperties(new HibernateSettings());
    }

//	@Autowired
//    DataSourceTransactionManager mapperTransactionManager;
	
   /*
     * 继承 TransactionManagementConfigurer
	 * Transaction 相关配置
	 * 因为有两个数据源，所有使用ChainedTransactionManager把两个DataSourceTransactionManager包括在一起。
	 */
//	@Override
//	public PlatformTransactionManager annotationDrivenTransactionManager() {
//		ChainedTransactionManager ctm = new ChainedTransactionManager(mapperTransactionManager, transactionManager());
//		return ctm;
//	}
    
}

package tp.ms.common.batis.cfg;


import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

//@Configuration
//@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactory", 
        transactionManagerRef = "transactionManager", 
        // 【1】这里写的是DAO层的路径 ，如果你的DAO放在 com.xx.DAO下面，则这里写成 com.xx.DAO
        basePackages = {"com.demo.defaultDataSource.repository"}  
)
public class JpaDataSourceConfiguration {
	
	@Autowired
    private JpaProperties jpaProperties;

    /**===========================================================================*/
	@Resource
//    @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;
 
    @Bean(name = "entityManagerPrimary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }
 
    private Map<String, Object> getVendorProperties() {
        return jpaProperties.getHibernateProperties(new HibernateSettings());
    }
 
    /**
     * 设置实体类所在位置
     */
    @Bean(name = "entityManagerFactoryPrimary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(primaryDataSource)
                .packages("com.example.demo.model.primary")
                .persistenceUnit("primaryPersistenceUnit")
                .properties(getVendorProperties())
                .build();
    }
 
    @Bean(name = "transactionManagerPrimary")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }
    
    /**===========================================================================*/
    
    @Primary
    @Bean(name = "amsflowEntityManagerFactory") 
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("amsflowDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.demo.defaultDataSource.domain") // 【3】这里是实体类的包路径
                .persistenceUnit("defaultUnit") // 这里写成唯一的就可以了，具体我也不太明白 ，希望有人告知
                  // 【4】
                .properties(jpaProperties.getHibernateProperties(new HibernateSettings())) 
                .build();
    }

    @Primary
    @Bean(name = "amsflowTransactionManager") 
    public PlatformTransactionManager transactionManager(
            @Qualifier("amsflowEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean(name = "nc6302EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean nc6302EntityManagerFactory(EntityManagerFactoryBuilder builder,
            @Qualifier("nc6302DataSource") DataSource dataSource , Environment environment) {
        
        LocalContainerEntityManagerFactoryBean nc6302 = builder
                .dataSource(dataSource)
                .packages("com.demo.nc6302DataSource.domain") // 【3】
                .persistenceUnit("nc6302")
                // 【4】
                .properties(jpaProperties.getHibernateProperties(new HibernateSettings())) 
                .build();
        return nc6302 ;
    }
    

    @Bean(name = "nc6302TransactionManager")
    public PlatformTransactionManager nc6302TransactionManager(
            @Qualifier("nc6302EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }


    @Bean(name = "cs6304EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean cs6304EntityManagerFactory(EntityManagerFactoryBuilder builder,
            @Qualifier("cs6304DataSource") DataSource dataSource , Environment environment) {
        
        LocalContainerEntityManagerFactoryBean cs6304 = builder
                .dataSource(dataSource)
                .packages("com.demo.cs6304DataSource.domain") // 【3】
                .persistenceUnit("cs6304")
                // 【4】
                .properties(jpaProperties.getHibernateProperties(new HibernateSettings())) 
                .build();
        return cs6304 ;
    }
    

    @Bean(name = "cs6304TransactionManager")
    public PlatformTransactionManager cs6304TransactionManager(
            @Qualifier("cs6304EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }


    @Bean(name = "mprocessEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mprocessEntityManagerFactory(EntityManagerFactoryBuilder builder,
            @Qualifier("mprocessDataSource") DataSource dataSource , Environment environment) {
        
        LocalContainerEntityManagerFactoryBean mprocess = builder
                .dataSource(dataSource)
                .packages("com.demo.mprocessDataSource.domain") // 【3】
                .persistenceUnit("mprocess")
                // 【4】
                .properties(jpaProperties.getHibernateProperties(new HibernateSettings())) 
                .build();
        return mprocess ;
    }
    

    @Bean(name = "mprocessTransactionManager")
    public PlatformTransactionManager mprocessTransactionManager(
            @Qualifier("mprocessEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}

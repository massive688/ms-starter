package tp.ms.common.batis.cfg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertyNameAliases;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

/**
 * 动态数据源注册
 * 实现 ImportBeanDefinitionRegistrar 实现数据源注册
 * 实现 EnvironmentAware 用于读取application.yml配置
 */
@Slf4j
public class MsDynamicDataSourceRegister_Temp implements ImportBeanDefinitionRegistrar, EnvironmentAware {


   /*
     * 配置上下文（也可以理解为配置文件的获取工具）
     */
    private Environment evn;

   /*
     * 别名
     */
    private final static ConfigurationPropertyNameAliases aliases = new ConfigurationPropertyNameAliases();

   /*
     * 由于部分数据源配置不同，所以在此处添加别名，避免切换数据源出现某些参数无法注入的情况
     */
    static {
        aliases.addAliases("url", new String[]{"jdbc-url"});
        aliases.addAliases("username", new String[]{"user"});
    }

   /*
     * 存储我们注册的数据源
     */
    private Map<String, DataSource> customDataSources = new HashMap<String, DataSource>();

   /*
     * 参数绑定工具 springboot2.0新推出
     */
    private Binder binder;

   /*
     * ImportBeanDefinitionRegistrar接口的实现方法，通过该方法可以按照自己的方式注册bean
     *
     * @param annotationMetadata
     * @param beanDefinitionRegistry
     */
    @SuppressWarnings("rawtypes")
	@Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        // 获取所有数据源配置
        Map config, defauleDataSourceProperties;
        defauleDataSourceProperties = binder.bind("ms.dyn.myb.master", Map.class).get();
       /**NEW ADD 获取数据源配置*****/
//        MsMybatisProperties mmp = 
//        		binder.bind("spring.dyn.myb", MsMybatisProperties.class).get();
        // 获取数据源类型
        String typeStr = evn.getProperty("ms.dyn.myb.master.type");
        // 获取数据源类型
        Class<? extends DataSource> clazz = getDataSourceType(typeStr);
        // 绑定默认数据源参数 也就是主数据源
        DataSource consumerDatasource, defaultDatasource = bind(clazz, defauleDataSourceProperties);
        MsDynamicDataSourceContextHolder.dataSourceKeys.add(evn.getProperty("ms.dyn.myb.master.key"));
        log.info("注册默认数据源成功");
        // 获取其他数据源配置
        List<Map> configs = binder.bind("ms.dyn.myb.ds", Bindable.listOf(Map.class)).get();
        // 遍历从数据源
        for (int i = 0; i < configs.size(); i++) {
            config = configs.get(i);
            clazz = getDataSourceType((String) config.get("type"));
            defauleDataSourceProperties = config;
            // 绑定参数
            consumerDatasource = bind(clazz, defauleDataSourceProperties);
            // 获取数据源的key，以便通过该key可以定位到数据源
            String key = config.get("key").toString();
            customDataSources.put(key, consumerDatasource);
            // 数据源上下文，用于管理数据源与记录已经注册的数据源key
            MsDynamicDataSourceContextHolder.dataSourceKeys.add(key);
            log.info("注册数据源{}成功", key);
        }
        // bean定义类
        GenericBeanDefinition define = new GenericBeanDefinition();
        // 设置bean的类型，此处DynamicRoutingDataSource是继承AbstractRoutingDataSource的实现类
        define.setBeanClass(MsDynamicRoutingDataSource.class);
        // 需要注入的参数
        MutablePropertyValues mpv = define.getPropertyValues();
        // 添加默认数据源，避免key不存在的情况没有数据源可用
        mpv.add("defaultTargetDataSource", defaultDatasource);
        // 添加其他数据源
        mpv.add("targetDataSources", customDataSources);
        // 将该bean注册为datasource，不使用springboot自动生成的datasource
        beanDefinitionRegistry.registerBeanDefinition("dataSource", define);
        log.info("注册数据源成功，一共注册{}个数据源", customDataSources.keySet().size() + 1);
    }

   /*
     * 通过字符串获取数据源class对象
     *
     * @param typeStr
     * @return
     */
	@SuppressWarnings("unchecked")
	Class<? extends DataSource> getDataSourceType(String typeStr) {
        Class<? extends DataSource> type;
        try {
            if (StringUtils.hasLength(typeStr)) {
                // 字符串不为空则通过反射获取class对象
                type = (Class<? extends DataSource>) Class.forName(typeStr);
            } else {
                // 默认为hikariCP数据源，与springboot默认数据源保持一致
                type = HikariDataSource.class;
            }
            return type;
        } catch (Exception e) {
            throw new IllegalArgumentException("can not resolve class with type: " + typeStr); //无法通过反射获取class对象的情况则抛出异常，该情况一般是写错了，所以此次抛出一个runtimeexception
        }
    }

   /*
     * 绑定参数，以下三个方法都是参考DataSourceBuilder的bind方法实现的，目的是尽量保证我们自己添加的数据源构造过程与springboot保持一致
     *
     * @param result
     * @param properties
     */
    @SuppressWarnings({ "unused", "rawtypes" })
	private void bind(DataSource result, Map properties) {
        ConfigurationPropertySource source = new MapConfigurationPropertySource(properties);
        Binder binder = new Binder(new ConfigurationPropertySource[]{source.withAliases(aliases)});
        // 将参数绑定到对象
        binder.bind(ConfigurationPropertyName.EMPTY, Bindable.ofInstance(result));
    }

    @SuppressWarnings("rawtypes")
	private <T extends DataSource> T bind(Class<T> clazz, Map properties) {
        ConfigurationPropertySource source = new MapConfigurationPropertySource(properties);
        Binder binder = new Binder(new ConfigurationPropertySource[]{source.withAliases(aliases)});
        // 通过类型绑定参数并获得实例对象
        return binder.bind(ConfigurationPropertyName.EMPTY, Bindable.of(clazz)).get();
    }

   /*
     * @param clazz
     * @param sourcePath 参数路径，对应配置文件中的值，如: spring.datasource
     * @param <T>
     * @return
     */
    @SuppressWarnings({ "unused", "rawtypes" })
	private <T extends DataSource> T bind(Class<T> clazz, String sourcePath) {
        Map properties = binder.bind(sourcePath, Map.class).get();
        return bind(clazz, properties);
    }

   /*
     * EnvironmentAware接口的实现方法，通过aware的方式注入，此处是environment对象
     *
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        log.info("开始注册数据源");
        this.evn = environment;
        // 绑定配置器
        binder = Binder.get(evn);
    }
    
    
   /**NEW ADD 遍历数据源 默认都用 DruidDataSource连接池 ****
    for (int i = 0; i < dsproperties.getDs().size(); i++) {
    	val ds = dsproperties.getDs().get(i);
		val datasource = DataSourceBuilder.create().type(DruidDataSource.class).build();
		
		datasource.setUrl(ds.getUrl());
		datasource.setUsername(ds.getUsername());
		datasource.setPassword(ds.getPassword());
		datasource.setDriverClassName(ds.getDriverClassName());
        datasource.setInitialSize(ds.getInitialSize());
        datasource.setMinIdle(ds.getMinIdle());
        datasource.setMaxActive(ds.getMaxActive());
        datasource.setMaxWait(ds.getMaxWait());
        datasource.setMinEvictableIdleTimeMillis(ds.getMinEvictableIdleTimeMillis());
        
        datasource.setTimeBetweenEvictionRunsMillis(ds.getTimeBetweenEvictionRunsMillis());
        datasource.setValidationQuery(ds.getValidationQuery());
        datasource.setTestWhileIdle(ds.isTestWhileIdle());
        datasource.setTestOnBorrow(ds.isTestOnBorrow());
        datasource.setTestOnReturn(ds.isTestOnReturn());
        try {
            datasource.setFilters(ds.getFilters());
        } catch (SQLException e) {
            log.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(ds.getConnectionProperties());
        if(ds.isPrimary()) {
        	if(defaultDatasource == null) {
        		defaultDatasource = datasource;
        	}else {
                log.error("same druid configuration DataSource is exist");
                throw new RuntimeException("主数据源已重复，多数据源只可以设置一个主数据源");
        	}
        }else {
        	customDataSources.put(ds.getId(), datasource);
        }
    }
    if(defaultDatasource == null)  
    	throw new RuntimeException("主数据源未设置");
    	*/
}

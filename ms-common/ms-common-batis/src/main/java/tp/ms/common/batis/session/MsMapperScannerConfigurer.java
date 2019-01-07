package tp.ms.common.batis.session;
import java.util.Properties;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;

import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.util.StringUtil;

public class MsMapperScannerConfigurer extends MapperScannerConfigurer {
	
    private MapperHelper mapperHelper = new MapperHelper();
    private String sqlSessionTemplateBeanName;

    @Override
    public void setMarkerInterface(Class<?> superClass) {
        super.setMarkerInterface(superClass);
        if (Marker.class.isAssignableFrom(superClass)) {
            mapperHelper.registerMapper(superClass);
        }
    }

    public MapperHelper getMapperHelper() {
        return mapperHelper;
    }

    public void setMapperHelper(MapperHelper mapperHelper) {
        this.mapperHelper = mapperHelper;
    }

    @Override
    public void setSqlSessionTemplateBeanName(String sqlSessionTemplateBeanName) {
        super.setSqlSessionTemplateBeanName(sqlSessionTemplateBeanName);
        this.sqlSessionTemplateBeanName = sqlSessionTemplateBeanName;
      }

    /**
     * 属性注入
     *
     * @param properties
     */
    public void setProperties(Properties properties) {
        mapperHelper.setProperties(properties);
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) {
        super.postProcessBeanDefinitionRegistry(registry);
        // 如果没有注册过接口，就注册默认的Mapper接口
        this.mapperHelper.ifEmptyRegisterDefaultInterface();
        String[] names = registry.getBeanDefinitionNames();
        GenericBeanDefinition definition;
        for (String name : names) {
            BeanDefinition beanDefinition = registry.getBeanDefinition(name);
            if (beanDefinition instanceof GenericBeanDefinition) {
                definition = (GenericBeanDefinition) beanDefinition;
                if (StringUtil.isNotEmpty(definition.getBeanClassName())
                        && definition.getBeanClassName().equals("org.mybatis.spring.mapper.MapperFactoryBean")) {
                    definition.setBeanClass(MsMapperFactoryBean.class);
                    definition.getPropertyValues().add("mapperHelper", this.mapperHelper);
                    definition.getPropertyValues().add("sqlSessionTemplateBeanName",
                            this.sqlSessionTemplateBeanName);
                    registry.registerBeanDefinition(name, beanDefinition);
                }
            }
        }
    }
}
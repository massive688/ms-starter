package tp.ms.common.data.mybatis.session;
import java.util.List;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.Configuration;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import lombok.Getter;
import lombok.Setter;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

@Getter
@Setter
public class MsMapperFactoryBean<T> extends org.mybatis.spring.mapper.MapperFactoryBean<T> implements ApplicationContextAware{
    private MapperHelper mapperHelper;
    private String sqlSessionTemplateBeanName;
    private ApplicationContext applicationContext;

    public MsMapperFactoryBean() {
        super();
    }

    public MsMapperFactoryBean(Class<T> mapperInterface) {
        super(mapperInterface);
    }

    public void setSqlSessionTemplateBeanName(String sqlSessionTemplateBeanName) {
        this.sqlSessionTemplateBeanName = sqlSessionTemplateBeanName;
      }

    public void setMapperHelper(MapperHelper mapperHelper) {
        this.mapperHelper = mapperHelper;
    }

    @Override
    protected void checkDaoConfig() {
    	MsSqlSessionTemplate customSqlSessionTemplate = (MsSqlSessionTemplate)this.applicationContext.getBean(this.sqlSessionTemplateBeanName);
        List<Configuration> configurations = customSqlSessionTemplate.getAllConfigurations();
        for (Configuration configuration : configurations) {
            if (isAddToConfig() && !configuration.hasMapper(getMapperInterface())) {
                try {
                    configuration.addMapper(getMapperInterface());
                } catch (Exception e) {
                    logger.error("Error while adding the mapper '" + getMapperInterface() + "' to configuration.", e);
                    throw new IllegalArgumentException(e);
                } finally {
                    ErrorContext.instance().reset();
                }
            }
            // 通用Mapper
            if (mapperHelper.isExtendCommonMapper(getObjectType())) {
                mapperHelper.processConfiguration(configuration, getObjectType());
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // TODO Auto-generated method stub
        this.applicationContext = applicationContext;
    }
    
}
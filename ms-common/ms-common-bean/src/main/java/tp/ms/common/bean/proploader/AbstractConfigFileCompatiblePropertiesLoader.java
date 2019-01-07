/* * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tp.ms.common.bean.proploader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.SpringFactoriesLoader;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

/**
 * This is used for backwards compatibility pre 6.3.0.
 *
 * @author Filip Hrisafov
 * 
 * 参照 原类
 * org.flowable.rest.app.properties.BackwardsCompatiblePropertiesLoader
 */
@Slf4j
public abstract class AbstractConfigFileCompatiblePropertiesLoader implements EnvironmentPostProcessor, Ordered {

    public static final int DEFAULT_ORDER = ConfigFileApplicationListener.DEFAULT_ORDER - 1;

//    private static final DefaultResourceLoader DEFAULT_RESOURCE_LOADER = new DefaultResourceLoader();
//    private static final PropertiesPropertySourceLoader PropertiesPropertySourceLoader = new PropertiesPropertySourceLoader();
//    private static final YamlPropertySourceLoader YamlPropertySourceLoader = new YamlPropertySourceLoader();
    

//    private static final String[] DEPRECATED_LOCATIONS = {"classpath:b.yml", "classpath:c.yml", "classpath:engine.properties" };

    private int order = DEFAULT_ORDER;

//	private final List<PropertySourceLoader> ;
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        ResourceLoader resourceLoader = application.getResourceLoader();
        if (resourceLoader == null) {
            resourceLoader = new DefaultResourceLoader();
        }
		
        MutablePropertySources mutablePropertySources = environment.getPropertySources();
       
        val propertySourceLoaders = SpringFactoriesLoader.loadFactories(
        		PropertySourceLoader.class, getClass().getClassLoader());
        
        for (val loader : propertySourceLoaders) {
        	val processedProfiles = loader.getFileExtensions();
        	val locations = locations();
        	if(locations == null) {
        		throw new IllegalArgumentException(" locations function cannot return null or empty");
        	}
            for (String location : locations) {
            	if (processedProfiles != null) {
        			// Try profile specific sections in files we've already processed
        			for (val processedProfile : processedProfiles) {
        				if (processedProfile != null && location.endsWith(processedProfile)) {
        	    			load(loader, location, resourceLoader, mutablePropertySources);
        				}
        			}
        		}
            }
        }
    }

    private void load(PropertySourceLoader loader, String location, ResourceLoader resourceLoader, MutablePropertySources mutablePropertySources) {
		Resource resource = resourceLoader.getResource(location);
    	if (resource == null || !resource.exists()) {
			if (log.isTraceEnabled()) {
				log.trace("Skipped missing config "
						+ location);
			}
			return;
		}
    	try {
			List<PropertySource<?>> propertySources = loader.load(resourceName(location), resource);
			for (val propertySource : propertySources) {
				if (mutablePropertySources.contains(propertySource.getName())) {
					mutablePropertySources.replace(propertySource.getName(), propertySource);
                } else {
                	mutablePropertySources.addLast(propertySource);
                }
			}
		} catch (IllegalArgumentException | FileNotFoundException | UnknownHostException ex) {
            if (log.isInfoEnabled()) {
            	log.info("Properties location [{}] not resolvable: {}", location, ex.getMessage());
            }
        } catch (IOException e) {
			log.error(e.getMessage(), e);
            throw new UncheckedIOException("Failed to creaty property source", e);
		}
    	
    }

	private String resourceName(String location) {
		return "applicationConfig: [" + location + "]";
	}

	public abstract String[] locations();

	@Override
    public int getOrder() {
        return order;
    }
}

/**
 * spring.factories 文件 配置Spring运行时接口实现
 * 
 * org.springframework.boot.env.EnvironmentPostProcessor=\
 * com.fw.common.conf.abstracts.properties.AbstractConfigFileCompatiblePropertiesLoader,\
 * com.fw.common.conf.abstracts.properties.AbstractConfigFileAppLoader
 */
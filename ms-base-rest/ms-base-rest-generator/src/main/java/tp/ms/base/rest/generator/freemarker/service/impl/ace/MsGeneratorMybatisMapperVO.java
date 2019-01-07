package tp.ms.base.rest.generator.freemarker.service.impl.ace;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.data.mybatis.mapper.DaoMapper;

@Slf4j
public class MsGeneratorMybatisMapperVO {
	
    public static void main( String[] args ) throws XMLParserException, Exception {
    	gen("generator");
        System.out.println( "generator Done!" );
    }
    
    @SuppressWarnings("deprecation")
	public static void gen(String project) throws Exception, XMLParserException {
        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(project + "/generatorConfig.xml");
//        File configFile = new File("/work/codebase/yibuyiliao/mybatis-gen/src/resource/platform.xml");
        try {
            gen(input);
        } finally {
            IOUtils.closeQuietly(input);
        }
        
        System.out.println("DaoGenerator> Done.");
    }

    private static void gen(InputStream input) throws IOException,
            XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;

		ConfigurationParser cp = new ConfigurationParser(warnings);
		org.mybatis.generator.config.Configuration config = cp.parseConfiguration(input);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);

		for (String warning : warnings) {
			log.warn(warning);
		}
    }

	@SuppressWarnings("deprecation")
	public static void excuteMybatisGenerator(File file) throws ADBusinessException {
		log.info("Mybatis mapper Dao Generator > Start.");
		InputStream input = null;
		try {
			input = new FileInputStream(file.getPath());
			gen(input);
		} catch (InvalidConfigurationException | IOException | XMLParserException | SQLException
				| InterruptedException e) {
			throw new ADBusinessException(e);
		} finally {
			if (input != null)
				IOUtils.closeQuietly(input);
		}
		log.info("Mybatis mapper Dao Generator > Done.");
	}
	
	
	public static File generatorMybatisResource(String extendClassName, String packageName, String tableName, String className,
			String configName) throws ADBusinessException {
		try {
			String templatePath = TemplateUtil.getRootTemplateResourcesPath();

			Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
			// 设置模板目录
			cfg.setDirectoryForTemplateLoading(new File(templatePath));
			// 设置默认编码格式
			cfg.setDefaultEncoding("UTF-8");
			Map<String, Object> product = new HashMap<>();
			TemplateUtil.loadCommonProperties(product);
			product.put("name", configName);
			product.put("packageName", packageName);
			product.put("extendClass", extendClassName);
			product.put("mapperBase", DaoMapper.class.getName());
			product.put("tableName", tableName);
			product.put("domainObjectName", className);
			// 从设置的目录中获得模板
			Template temp = cfg.getTemplate("generatorConfig.ftl");
			File targetFile = new File(
					templatePath.replaceAll("template", "generator") + File.separator + configName + ".xml");
			
			Writer out = new OutputStreamWriter(new FileOutputStream(targetFile), "UTF-8");
			// 合并模板和数据模型
			temp.process(product, out);
			// 关闭
			out.flush();
			out.close();

			excuteMybatisGenerator(targetFile);
			if(targetFile != null && targetFile.isFile()) {
				targetFile.delete();
			}
			return targetFile;
		} catch (Exception e) {
			throw new ADBusinessException(e);
		}
	}
}

package tp.ms.base.rest.generator.freemarker.service.impl.ace;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import tp.ms.common.bean.exception.ADBusinessException;

public class GeneratorConfigCodeRuleFactoriesResource {

	private String packageName;
	private String parentClassSuffix;
	private Object parentClass;

	public GeneratorConfigCodeRuleFactoriesResource(String packageName, String parentClassSuffix, String parentClass) {
		this.packageName = packageName;
		this.parentClassSuffix = parentClassSuffix;
		this.parentClass = parentClass;
	}

	public void excute() throws ADBusinessException {
		try {
			String rootPath = TemplateUtil.getRootTemplateResourcesPath();

			// 从设置的目录中获得模板
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
			// 设置模板目录
			cfg.setDirectoryForTemplateLoading(new File(rootPath));
			// 设置默认编码格式
			cfg.setDefaultEncoding("UTF-8");
			Map<String, Object> product = new HashMap<>();
			product.put("packageName", packageName);
			product.put("parentClass", parentClass);
			product.put("parentClassSuffix", parentClassSuffix);
			Template temp = cfg.getTemplate("AppConfig.ftl");
			File targetFile = new File(TemplateUtil.resolveTargetPath(packageName, "config", null));
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			Writer out = new OutputStreamWriter(
					new FileOutputStream(TemplateUtil.resolveTargetPath(packageName, "config", "AppConfiguration")),
					"UTF-8");
			// 合并模板和数据模型
			temp.process(product, out);
			out.flush();
			out.close();

			temp = cfg.getTemplate("BillCodeRule.ftl");
			targetFile = new File(TemplateUtil.resolveTargetPath(packageName, "rule", null));
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			out = new OutputStreamWriter(
					new FileOutputStream(TemplateUtil.resolveTargetPath(packageName, "rule", parentClassSuffix + "BillCodeRule")),
					"UTF-8");
			// 合并模板和数据模型
			temp.process(product, out);
			out.flush();
			out.close();
			// 从设置的目录中获得模板

			temp = cfg.getTemplate("factories.ftl");
			String target = TemplateUtil.class.getClassLoader().getResource("").getPath().replaceAll("target/classes",
					"src/main/resources/META-INF");
			targetFile = new File(target);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			
			out = new OutputStreamWriter(
					new FileOutputStream(target + "/spring.factories"),
					"UTF-8");
			// 合并模板和数据模型
			temp.process(product, out);
			out.flush();
			out.close();

		} catch (Exception e) {
			throw new ADBusinessException(e);
		}
	
	}

}

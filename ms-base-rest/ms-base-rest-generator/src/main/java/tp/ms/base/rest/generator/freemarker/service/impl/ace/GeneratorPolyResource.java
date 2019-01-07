package tp.ms.base.rest.generator.freemarker.service.impl.ace;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import tp.ms.common.bean.exception.ADBusinessException;

public class GeneratorPolyResource {
	
	private String module;
	private String parentClassSuffix;
	private String packageName;
	private List<String> childClassSuffixes;
	private String mapping;

	public GeneratorPolyResource(String packageName, String module, String parentClassSuffix,
			List<String> childClassSuffixes, String mapping) {
		this.packageName = packageName;
		this.module = module;
		this.parentClassSuffix = parentClassSuffix;
		this.childClassSuffixes = childClassSuffixes;
		this.mapping = mapping;
	}

	public void excute() throws ADBusinessException {

		try {
			String rootPath = TemplateUtil.getRootTemplateResourcesPath();
			String parentClass = module + parentClassSuffix;

			// 从设置的目录中获得模板
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
			// 设置模板目录
			cfg.setDirectoryForTemplateLoading(new File(rootPath));
			// 设置默认编码格式
			cfg.setDefaultEncoding("UTF-8");
			Map<String, Object> product = new HashMap<>();
			product.put("packageName", packageName);
			product.put("module", module);
			product.put("parentClass", parentClass);
			product.put("childClassSuffixes", childClassSuffixes);
			product.put("mapping", mapping);
			product.put("parentClassSuffix", parentClassSuffix);
			Template temp = cfg.getTemplate("PolyVO.ftl");
			File targetFile = new File(TemplateUtil.resolveTargetPath(packageName, "vo", null));
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			Writer out = new OutputStreamWriter(
					new FileOutputStream(TemplateUtil.resolveTargetPath(packageName, "vo", "Poly" + parentClass + "VO")),
					"UTF-8");
			// 合并模板和数据模型
			temp.process(product, out);
			out.flush();
			out.close();

			temp = cfg.getTemplate("PolyRest.ftl");
			targetFile = new File(TemplateUtil.resolveTargetPath(packageName, "rest.poly", null));
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			out = new OutputStreamWriter(
					new FileOutputStream(TemplateUtil.resolveTargetPath(packageName, "rest.poly", "Poly" + parentClassSuffix + "Resource")),
					"UTF-8");
			// 合并模板和数据模型
			temp.process(product, out);
			out.flush();
			out.close();
			// 从设置的目录中获得模板

			temp = cfg.getTemplate("PolyService.ftl");

			targetFile = new File(TemplateUtil.resolveTargetPath(packageName, "service.poly", null));
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			out = new OutputStreamWriter(
					new FileOutputStream(TemplateUtil.resolveTargetPath(packageName, "service.poly", parentClassSuffix + "Service")),
					"UTF-8");
			// 合并模板和数据模型
			temp.process(product, out);
			out.flush();
			out.close();

			// 从设置的目录中获得模板

			temp = cfg.getTemplate("PolyServiceImpl.ftl");
			targetFile = new File(TemplateUtil.resolveTargetPath(packageName, "service.poly.impl", null));
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			out = new OutputStreamWriter(
					new FileOutputStream(TemplateUtil.resolveTargetPath(packageName, "service.poly.impl", parentClassSuffix + "ServiceImpl")),
					"UTF-8");
			temp.process(product, out);
			out.flush();
			out.close();

		} catch (Exception e) {
			throw new ADBusinessException(e);
		}
	
		
	}

}

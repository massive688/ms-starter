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
import tp.ms.common.bean.utils.StringUtilms;

public class GeneratorAdditionalResource {

	private String parentClassSuffix;
	private String packageName;

	public GeneratorAdditionalResource(String packageName, String parentClassSuffix) {
		this.packageName = packageName;
		this.parentClassSuffix = parentClassSuffix;
	}

	public void excute() throws ADBusinessException {
		String mapping = StringUtilms.translateLowerString(parentClassSuffix, '-');
		try {
			String rootPath = TemplateUtil.getRootTemplateResourcesPath();
			String parentClass = parentClassSuffix;

			// 从设置的目录中获得模板
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
			// 设置模板目录
			cfg.setDirectoryForTemplateLoading(new File(rootPath));
			// 设置默认编码格式
			cfg.setDefaultEncoding("UTF-8");
			Map<String, Object> product = new HashMap<>();
			product.put("packageName", packageName);
			product.put("parentClass", parentClass);
			product.put("mapping", mapping);
			product.put("pkSuffix", parentClassSuffix);
			Template temp = cfg.getTemplate("IndividualizationMapper.ftl");
			File targetFile = new File(TemplateUtil.resolveTargetPath(packageName, "mapper.individualization", null));
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			Writer out = new OutputStreamWriter(
					new FileOutputStream(TemplateUtil.resolveTargetPath(packageName, "mapper.individualization", parentClassSuffix + "IndividualizationMapper")),
					"UTF-8");
			// 合并模板和数据模型
			temp.process(product, out);
			out.flush();
			out.close();

			temp = cfg.getTemplate("IndividualizationRest.ftl");
			targetFile = new File(TemplateUtil.resolveTargetPath(packageName, "rest.individualization", null));
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			out = new OutputStreamWriter(
					new FileOutputStream(TemplateUtil.resolveTargetPath(packageName, "rest.individualization", parentClassSuffix + "IndividualizationResource")),
					"UTF-8");
			// 合并模板和数据模型
			temp.process(product, out);
			out.flush();
			out.close();
			// 从设置的目录中获得模板

			temp = cfg.getTemplate("IndividualizationService.ftl");

			targetFile = new File(TemplateUtil.resolveTargetPath(packageName, "service.individualization", null));
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			out = new OutputStreamWriter(
					new FileOutputStream(TemplateUtil.resolveTargetPath(packageName, "service.individualization", parentClassSuffix + "IndividualizationService")),
					"UTF-8");
			// 合并模板和数据模型
			temp.process(product, out);
			out.flush();
			out.close();

			// 从设置的目录中获得模板

			temp = cfg.getTemplate("IndividualizationServiceImpl.ftl");
			targetFile = new File(TemplateUtil.resolveTargetPath(packageName, "service.individualization.impl", null));
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			out = new OutputStreamWriter(
					new FileOutputStream(TemplateUtil.resolveTargetPath(packageName, "service.individualization.impl", parentClassSuffix + "IndividualizationServiceImpl")),
					"UTF-8");
			temp.process(product, out);
			out.flush();
			out.close();

			

			// 从设置的目录中获得模板
			cfg = new Configuration(Configuration.VERSION_2_3_22);
			// 设置模板目录
			cfg.setDirectoryForTemplateLoading(new File(rootPath));
			// 设置默认编码格式
			cfg.setDefaultEncoding("UTF-8");
			product = new HashMap<>();
			product.put("packageName", packageName);
			product.put("parentClass", parentClass);
			product.put("mapping", mapping);
			product.put("pkSuffix", parentClassSuffix);
			temp = cfg.getTemplate("ReferenceMapper.ftl");
			targetFile = new File(TemplateUtil.resolveTargetPath(packageName, "mapper.reference", null));
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			out = new OutputStreamWriter(
					new FileOutputStream(TemplateUtil.resolveTargetPath(packageName, "mapper.reference", parentClassSuffix + "ReferenceMapper")),
					"UTF-8");
			// 合并模板和数据模型
			temp.process(product, out);
			out.flush();
			out.close();

			temp = cfg.getTemplate("ReferenceRest.ftl");
			targetFile = new File(TemplateUtil.resolveTargetPath(packageName, "rest.reference", null));
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			out = new OutputStreamWriter(
					new FileOutputStream(TemplateUtil.resolveTargetPath(packageName, "rest.reference", parentClassSuffix + "ReferenceResource")),
					"UTF-8");
			// 合并模板和数据模型
			temp.process(product, out);
			out.flush();
			out.close();
			// 从设置的目录中获得模板

			temp = cfg.getTemplate("ReferenceService.ftl");

			targetFile = new File(TemplateUtil.resolveTargetPath(packageName, "service.reference", null));
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			out = new OutputStreamWriter(
					new FileOutputStream(TemplateUtil.resolveTargetPath(packageName, "service.reference", parentClassSuffix + "ReferenceService")),
					"UTF-8");
			// 合并模板和数据模型
			temp.process(product, out);
			out.flush();
			out.close();

			// 从设置的目录中获得模板

			temp = cfg.getTemplate("ReferenceServiceImpl.ftl");
			targetFile = new File(TemplateUtil.resolveTargetPath(packageName, "service.reference.impl", null));
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			out = new OutputStreamWriter(
					new FileOutputStream(TemplateUtil.resolveTargetPath(packageName, "service.reference.impl", parentClassSuffix + "ReferenceServiceImpl")),
					"UTF-8");
			temp.process(product, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			throw new ADBusinessException(e);
		}
	
	}

}

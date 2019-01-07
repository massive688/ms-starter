package tp.ms.base.rest.generator.freemarker.service.impl.ace;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import tp.ms.base.rest.generator.freemarker.service.impl.TableTypeEnum;
import tp.ms.base.rest.typecoded.vo.MsBaseBillTemplate;
import tp.ms.base.rest.typecoded.vo.MsBaseBilltype;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.utils.ObjectUtilms;
import tp.ms.common.bean.utils.StringUtilms;

public class GeneratorChildResource {

	//billtype 模板信息
	//所有的子表字段
	//主表的类后缀
	final String parentClassSuffix;
	private final MsBaseBilltype majorVO;
	private final Map<String, List<MsBaseBillTemplate>> childFields;
	private TableTypeEnum isSingle;
	
	public GeneratorChildResource(MsBaseBilltype majorVO, Map<String, List<MsBaseBillTemplate>> childFields,
			String parentClassSuffix, TableTypeEnum isSingle) {
		this.majorVO = majorVO;
		this.childFields = childFields;
		this.parentClassSuffix = parentClassSuffix;
		this.isSingle = isSingle;
	}


	public List<String> excute() throws ADBusinessException {
		if (ObjectUtilms.isEmpty(childFields))
			return null;
		List<String> childClass = new ArrayList<String>();
		for( String key : childFields.keySet()) {
			List<MsBaseBillTemplate> templateFields = childFields.get(key);
			if(ObjectUtilms.isEmpty(templateFields))
				continue;
			String cc = generatorStructureResources(templateFields);
			childClass.add(cc);
			
		}
		return childClass;	
	}

	private String generatorStructureResources(List<MsBaseBillTemplate> templateFields) throws ADBusinessException {

		String abbreviation = templateFields.get(0).getColumnClassNameAbbreviation();
		String qualifiedName = templateFields.get(0).getColumnClassQualifiedName();
		String tableName = templateFields.get(0).getTableName();
		String className = qualifiedName.substring(qualifiedName.lastIndexOf(".") + 1);
		String packageName = majorVO.getPackageName();
		
		new CommonGeneratorTableAndMapperVO(majorVO, templateFields, TableTypeEnum.CHILD, parentClassSuffix, isSingle).excute();
		
		// 生成controller, service层
		String pkSuffix = StringUtilms.translateUpperString(abbreviation, '_');
		String mapping = StringUtilms.translateLowerString(pkSuffix, '-');

		//处理子表VO添加实现
		handlerChildVO(qualifiedName, tableName, pkSuffix);
		//生成子表rest, service层代码
		excuteGeneratorChildResource(className, packageName, mapping, pkSuffix);
		
		return pkSuffix;
	}

	private void excuteGeneratorChildResource(String className, String packageName, String mapping, String pkSuffix) throws ADBusinessException {
		// 生成controller service impl层
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
			product.put("className", className);
			product.put("mapping", mapping);
			product.put("pkSuffix", pkSuffix);
			product.put("parentClassSuffix", parentClassSuffix);
			product.put("isSingle", isSingle == TableTypeEnum.SINGLE);
			Template temp = cfg.getTemplate("ChildRest.ftl");
			File targetFile = new File(TemplateUtil.resolveTargetPath(packageName, "rest.child", null));
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			Writer out = new OutputStreamWriter(
					new FileOutputStream(TemplateUtil.resolveTargetPath(packageName, "rest.child", pkSuffix + "Resource")),
					"UTF-8");
			// 合并模板和数据模型
			temp.process(product, out);
			out.flush();
			out.close();

			// 从设置的目录中获得模板

			temp = cfg.getTemplate("ChildService.ftl");

			targetFile = new File(TemplateUtil.resolveTargetPath(packageName, "service.child", null));
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			out = new OutputStreamWriter(
					new FileOutputStream(TemplateUtil.resolveTargetPath(packageName, "service.child", pkSuffix + "Service")),
					"UTF-8");
			// 合并模板和数据模型
			temp.process(product, out);
			out.flush();
			out.close();

			// 从设置的目录中获得模板

			temp = cfg.getTemplate("ChildServiceImpl.ftl");
			targetFile = new File(TemplateUtil.resolveTargetPath(packageName, "service.child.impl", null));
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			out = new OutputStreamWriter(
					new FileOutputStream(TemplateUtil.resolveTargetPath(packageName, "service.child.impl", pkSuffix + "ServiceImpl")),
					"UTF-8");
			temp.process(product, out);
			out.flush();
			out.close();

		} catch (Exception e) {
			throw new ADBusinessException(e);
		}
	
	}

	private void handlerChildVO(String qualifiedName, String tableName, String pkSuffix) throws ADBusinessException {
		String append;
		if(isSingle == TableTypeEnum.SINGLE) {
			append = "	@Override\n" + 
					"	public String getParentKey() {\n" + 
					"		return \"\";\n" + 
					"	}\n" + 
					"\n" + 
					"	@Override\n" + 
					"	public void setParentKey(String parentKey) {\n" + 
					"	}\n" ;
		}else {
			append = "	@Override\n" + 
					"	public String getParentKey() {\n" + 
					"		return this.pk"+parentClassSuffix+";\n" + 
					"	}\n" + 
					"\n" + 
					"	@Override\n" + 
					"	public void setParentKey(String parentKey) {\n" + 
					"		this.pk"+parentClassSuffix+" = parentKey;\n" + 
					"	}\n" ;
		}
	  append += "\n" + 
				"	@Override\n" + 
				"	public String getTable() {\n" + 
				"		return \""+tableName+"\";\n" + 
				"	}\n" + 
				"\n" + 
				"	@Override\n" + 
				"	public void setPrimaryKey(String key) {\n" + 
				"		this.pk"+pkSuffix+" = key;\n" + 
				"	}\n" + 
				"\n" + 
				"	@Override\n" + 
				"	public String getPrimaryKey() {\n" + 
				"		return this.pk"+pkSuffix+";\n" + 
				"	}";
		
		String target = this.getClass().getClassLoader().getResource("").getPath().replaceAll("target/classes",
				"src/main/java");
		qualifiedName = qualifiedName.replace(".", File.separator);
		target += File.separator + qualifiedName+ ".java";
		BufferedReader orignFile = null;
		FileWriter fw = null;
		try {
			orignFile = new BufferedReader(new FileReader(target));
			StringBuffer newFile = new StringBuffer();
	        String tempString = null;
	        // 一次读入一行，直到读入null为文件结束

            while ((tempString = orignFile.readLine()) != null) {
                newFile.append(tempString);
        		newFile.append("\r\n");
            }
            newFile.insert(newFile.lastIndexOf("}") - 1, append);

            fw = new FileWriter(new File(target));
            fw.write(newFile.toString());
        }catch (Exception e) {
			throw new ADBusinessException(e);
		}finally {
			try {
				if(orignFile != null) 
					orignFile.close();
			} catch (IOException e) {
				throw new ADBusinessException(e);
			}
			if(fw != null) {
				try {
					fw.flush();
					fw.close();
				} catch (IOException e) {
					throw new ADBusinessException(e);
				}
			}
		}
	
	}
}

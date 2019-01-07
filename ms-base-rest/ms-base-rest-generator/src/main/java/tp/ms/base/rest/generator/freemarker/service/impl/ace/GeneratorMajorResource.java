package tp.ms.base.rest.generator.freemarker.service.impl.ace;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
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

public class GeneratorMajorResource {

	MsBaseBilltype majorVO;
	
	List<MsBaseBillTemplate> majorFields;
	
	TableTypeEnum ttenum;
	
	public GeneratorMajorResource(MsBaseBilltype majorVO, List<MsBaseBillTemplate> majorFields, TableTypeEnum ttenum) {
		this.majorVO = majorVO;
		this.majorFields = majorFields;
		this.ttenum = ttenum;
	}

	/**
	 * 
	 * @return 类缩写后缀
	 * @throws ADBusinessException
	 */
	public String excute() throws ADBusinessException {
		if (ObjectUtilms.isNotEmpty(majorFields))
			return generatorStructureResources(majorVO, majorFields, ttenum, null);
		return null;
	}
	
	private String generatorStructureResources(MsBaseBilltype majorVO, List<MsBaseBillTemplate> templateFields, TableTypeEnum ttenum, String parentClassSuffix)
			throws ADBusinessException {
		String abbreviation = templateFields.get(0).getColumnClassNameAbbreviation();
		String qualifiedName = templateFields.get(0).getColumnClassQualifiedName();
		String tableName = templateFields.get(0).getTableName();
		String className = qualifiedName.substring(qualifiedName.lastIndexOf(".") + 1);
		String packageName = majorVO.getPackageName();
		
		new CommonGeneratorTableAndMapperVO(majorVO, templateFields, ttenum, parentClassSuffix).excute();
		
		// 生成controller, service层
		String pkSuffix = StringUtilms.translateUpperString(abbreviation, '_');
		String mapping = StringUtilms.translateLowerString(pkSuffix, '-');

		//处理主表VO添加实现
		handleMajorVO(qualifiedName, tableName, pkSuffix, majorVO.getComponentType());
		//生成主表rest, service层代码
		productionResourceService(className, packageName, mapping, pkSuffix, majorVO.getComponentType());
		
		return pkSuffix;
		
	}

	private void productionResourceService(String className, String packageName, String mapping, String pkSuffix, Integer componentType) throws ADBusinessException {


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
			Template temp = cfg.getTemplate("MajorRest.ftl");
			File targetFile = new File(TemplateUtil.resolveTargetPath(packageName, "rest.major", null));
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			Writer out = new OutputStreamWriter(
					new FileOutputStream(TemplateUtil.resolveTargetPath(packageName, "rest.major", pkSuffix + "MajorResource")),
					"UTF-8");
			// 合并模板和数据模型
			temp.process(product, out);
			out.flush();
			out.close();

			// 从设置的目录中获得模板

			temp = cfg.getTemplate("MajorService.ftl");

			targetFile = new File(TemplateUtil.resolveTargetPath(packageName, "service.major", null));
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			out = new OutputStreamWriter(
					new FileOutputStream(TemplateUtil.resolveTargetPath(packageName, "service.major", pkSuffix + "MajorService")),
					"UTF-8");
			// 合并模板和数据模型
			temp.process(product, out);
			out.flush();
			out.close();

			// 从设置的目录中获得模板

			temp = cfg.getTemplate("MajorServiceImpl.ftl");
			targetFile = new File(TemplateUtil.resolveTargetPath(packageName, "service.major.impl", null));
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			out = new OutputStreamWriter(
					new FileOutputStream(TemplateUtil.resolveTargetPath(packageName, "service.major.impl", pkSuffix + "MajorServiceImpl")),
					"UTF-8");
			temp.process(product, out);
			out.flush();
			out.close();

		} catch (Exception e) {
			throw new ADBusinessException(e);
		}

	
	}

	private void handleMajorVO(String qualifiedName, String tableName, String pkSuffix, Integer componentType) throws ADBusinessException {

		String append = "	@Override\r\n" + 
				"	public String getTable() {\r\n" + 
				"		return \""+tableName+"\";\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	@Override\r\n" + 
				"	public void setPrimaryKey(String key) {\r\n" + 
				"		this.pk"+pkSuffix+" = key;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	@Override\r\n" + 
				"	public String getPrimaryKey() {\r\n" + 
				"		return this.pk"+pkSuffix+";\r\n" + 
				"	}\r\n";
		
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

package tp.ms.base.rest.generator.freemarker.service.impl.ace;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import tp.ms.base.rest.generator.freemarker.mapper.TemplateMapper;
import tp.ms.base.rest.generator.freemarker.service.impl.TableTypeEnum;
import tp.ms.base.rest.generator.generationcode.bean.FreemarkerConstant;
import tp.ms.base.rest.typecoded.mapper.MsBaseBillTemplateMapper;
import tp.ms.base.rest.typecoded.vo.MsBaseBillTemplate;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.support.context.SpringContextHolder;
import tp.ms.common.bean.utils.ADate;
import tp.ms.common.bean.utils.ObjectUtilms;
import tp.ms.common.bean.utils.StringUtilms;

@Slf4j
public class TemplateUtil {

	static TemplateMapper templatemapper = SpringContextHolder.getBean(TemplateMapper.class);

	public static boolean isExistTableName(String tableName) {
		Integer isExist = templatemapper.findTable(tableName);
		if (isExist == null) {
			return false;
		}
		return true;
	}


	static List<String> auditFields = Arrays.asList(new String[] {"approve_status","bill_status","process_id"});
	static List<String> majorFields = Arrays.asList(new String[] {"code","name","pk_corp","pk_group","creator","creationtime","modifier","modifiedtime","billtype","enabled"});
	static List<String> childFields = Arrays.asList(new String[] {"ts","dr"});
	
	public static String[] getColSqls(List<MsBaseBillTemplate> templateFields, TableTypeEnum ttenum, String tableName, String module, String parentClassSuffix) {
		String abbreviation = tableName.replaceAll(module+"_", "");
		List<String> dbCols = templatemapper.columns(tableName);
		String[] result = new String[2];
		StringBuffer beforeColSql = new StringBuffer();
		StringBuffer afterColSql = new StringBuffer();
		String pkPrefix = "pk_";
		if(parentClassSuffix != null) {
			String parentField = pkPrefix+StringUtilms.translateLowerString(parentClassSuffix, '_');
			afterColSql.append(parentField);
			afterColSql.append(",");
			beforeColSql.append(parentField);
			beforeColSql.append(",");
		}
		String pkField = pkPrefix + abbreviation;
		afterColSql.append(pkField);
		afterColSql.append(",");
		beforeColSql.append(pkField);
		beforeColSql.append(",");
		for(MsBaseBillTemplate templateField : templateFields) {
			if(ObjectUtilms.isEqual(templateField.getColumnClassNameAbbreviation(), abbreviation)){
				String name = StringUtilms.translateLowerString(templateField.getName(), '_');
				String prevGeneratorName = StringUtilms.translateLowerString(templateField.getPrevGeneratorName(), '_');
				if(auditFields.contains(templateField.getName())
						|| majorFields.contains(name)
						|| childFields.contains(name)
						|| prevGeneratorName == null
						|| !dbCols.contains(prevGeneratorName))
					continue;
				afterColSql.append(name);
				afterColSql.append(",");
				beforeColSql.append(prevGeneratorName == null ? name : prevGeneratorName);
				beforeColSql.append(",");
			}
		}
		switch (ttenum) {
		case AUDIT:
			for(String field : auditFields) {
				if(dbCols.contains(field)) {
					beforeColSql.append(field);
					beforeColSql.append(",");
					afterColSql.append(field);
					afterColSql.append(",");
				}
			}
		case MAJOR:
			for(String field : majorFields) {
				if(dbCols.contains(field)) {
					beforeColSql.append(field);
					beforeColSql.append(",");
					afterColSql.append(field);
					afterColSql.append(",");
				}
			}
		case CHILD:
			for(String field : childFields) {
				if(dbCols.contains(field)) {
					beforeColSql.append(field);
					beforeColSql.append(",");
					afterColSql.append(field);
					afterColSql.append(",");
				}
			}
			break;
		default:
			break;
		}
		if(afterColSql.lastIndexOf(",") > -1)
			result[0] = afterColSql.substring(0, afterColSql.lastIndexOf(","));
		if(beforeColSql.lastIndexOf(",") > -1)
			result[1] = beforeColSql.substring(0, beforeColSql.lastIndexOf(","));
		return result;
	}

	/**
	 * 保存给定的表数据到临时设定的表中
	 * @param tableName
	 * @param cols
	 */
	public static void saveDataToTemp(String tableName, String cols) {
		String sql = "SELECT "+cols+" INTO "+tableName+"_temp  FROM "+tableName+";";
		templatemapper.updateTableField(sql);
	}

	/**
	 * 执行删除给定的表
	 * @param tableName
	 * @return
	 */
	public static int excuteDROPSqlTo(String tableName) {
		String tableSql = " DROP TABLE "+tableName+" ;";
		return excuteSqlTo(tableSql);
	}

	/**
	 * 构建新表的结构SQL
	 * @param tableName
	 * @param abbreviation
	 * @param templateFields
	 * @param ttenum
	 * @param parentClassSuffix
	 * @param isSingle 
	 * @return
	 * @throws ADBusinessException 
	 */
	public static String newTableStructure(String tableName, String abbreviation,
			List<MsBaseBillTemplate> templateFields, TableTypeEnum ttenum, String parentClassSuffix, TableTypeEnum isSingle) throws ADBusinessException {
		log.info("构建表结构开始。。。");
		StringBuffer createSql = new StringBuffer();
		createSql.append("CREATE TABLE dbo.").append(tableName).append("\r\n").append("	(\r\n")
			.append("	pk_").append(abbreviation).append("  VARCHAR (30) NOT NULL,\r\n");


		if(ttenum == TableTypeEnum.CHILD && isSingle != TableTypeEnum.SINGLE ) {
			if(parentClassSuffix == null) {
				throw new ADBusinessException("主表字段缺少");
			}
			parentClassSuffix = StringUtilms.translateLowerString(parentClassSuffix, '_');
			createSql.append("	pk_").append(parentClassSuffix).append("  VARCHAR (30) NOT NULL,\r\n");
		}
		
		for (MsBaseBillTemplate templateField : templateFields) {
			String fieldName = templateField.getName();
			switch (ttenum) {
			case AUDIT:
				if (auditFields.contains(fieldName))
					break;
			case MAJOR:
				if (majorFields.contains(fieldName))
					break;
			case CHILD:
				if (childFields.contains(fieldName))
					break;
				createSql.append(structureFieldSql(templateField));
				break;
			default:
				break;
			}
		}
		switch (ttenum) {
		case AUDIT:
			createSql.append("	approve_status      SMALLINT DEFAULT (-1),\r\n")
					.append("	bill_status      SMALLINT DEFAULT (-1),\r\n")
					.append("	process_id   		VARCHAR (64),\r\n");
		case MAJOR:
			createSql.append("	code         VARCHAR (64) DEFAULT ('~'),\r\n")
					.append("	name         VARCHAR (64) DEFAULT ('~'),\r\n")
					.append("	pk_corp             VARCHAR (30),\r\n")
					.append("	pk_group            VARCHAR (30),\r\n")
					.append("	creator             VARCHAR (30),\r\n")
					.append("	creationtime        VARCHAR (19),\r\n")
					.append("	modifier            VARCHAR (30),\r\n")
					.append("	modifiedtime        VARCHAR (19),\r\n")
					.append("	billtype            VARCHAR (30),\r\n")
					.append("	enabled        		SMALLINT ,\r\n");
		case CHILD:
			createSql.append("	ts           CHAR (19) DEFAULT (CONVERT([char](19),getdate(),(20))),\r\n")
					.append("	dr           SMALLINT DEFAULT ((0)),\r\n");
			break;

		default:
			break;
		}
		createSql.append("	CONSTRAINT PK_").append(tableName).append(" PRIMARY KEY (pk_").append(abbreviation)
				.append(")\r\n").append("	)\r\n").append(";");
		log.info("构建表结构结束。。。");
		return createSql.toString();
	}

	private static String structureFieldSql(MsBaseBillTemplate templateField) {
		String fieldName = StringUtilms.translateLowerString(templateField.getName(), '_');
		String type = templateField.getType();
		Integer length = templateField.getMaxLength();
		String result;
		switch (type) {
		case "number":
			result = "	" + fieldName + "            INT ,\r\n";
			break;
		default:
			result = "	" + fieldName + "             VARCHAR (" + length + "),\r\n";
			break;
		}
		return result;
	}

	public static int excuteSqlTo(String tableSql) {
		return templatemapper.updateTableField(tableSql);
	}

	public static void backDataToNewTable(String tableName, String[] cols) {
		String newColSql = cols[0];
		String originalColSql = cols[1];
		String sql = "INSERT INTO "+tableName+" ("+newColSql+") SELECT "+originalColSql+" FROM "+tableName+"_temp;";
		templatemapper.updateTableField(sql);
		sql = " DROP TABLE "+tableName+"_temp ;";
		templatemapper.updateTableField(sql);
	}

	public static String getRootTemplateResourcesPath() {
		return TemplateUtil.class.getClassLoader().getResource("template").getPath().replaceAll("target/classes",
				"src/main/resources");
	}

	public static void loadCommonProperties(Map<String, Object> product) {
		product.put("driverClass", FreemarkerConstant.DRTVERCLASS);
		product.put("connUrl", FreemarkerConstant.JDBCURL);
		product.put("user", FreemarkerConstant.USERNAME);
		product.put("password", FreemarkerConstant.PASSWORD);
	}

	public static String resolveTargetPath(String packageName, String targetTypePath, String className) {
		String target = TemplateUtil.class.getClassLoader().getResource("").getPath().replaceAll("target/classes",
				"src/main/java");
		packageName = packageName.replace(".", File.separator);
		targetTypePath = targetTypePath.replace(".", File.separator);
		target += "com" + File.separator + packageName+ File.separator + targetTypePath
				+ File.separator;
		if (className != null)
			target += className + ".java";
		return target;
	}

	public static void backDataAfterBatchUpdateTemplateFields(List<MsBaseBillTemplate> templateFields) {
		MsBaseBillTemplateMapper billTemplateMapper = SpringContextHolder.getBean(MsBaseBillTemplateMapper.class);
		String now = ADate.now().toString();
		for(MsBaseBillTemplate templateField: templateFields) {
			templateField.setTs(now);
			templateField.setPrevGeneratorName(templateField.getName());
			billTemplateMapper.updateByPrimaryKey(templateField);
		}
	}
	
	
}

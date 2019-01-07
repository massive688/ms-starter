package tp.ms.base.rest.generator.freemarker.service.impl.ace;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import tp.ms.base.rest.generator.freemarker.service.impl.TableTypeEnum;
import tp.ms.base.rest.resource.vo.ChildBaseVO;
import tp.ms.base.rest.resource.vo.MajorAuditBaseVO;
import tp.ms.base.rest.resource.vo.MajorBaseVO;
import tp.ms.base.rest.typecoded.vo.MsBaseBillTemplate;
import tp.ms.base.rest.typecoded.vo.MsBaseBilltype;
import tp.ms.common.bean.exception.ADBusinessException;

@Slf4j
public class CommonGeneratorTableAndMapperVO {

	private MsBaseBilltype majorVO;
	private List<MsBaseBillTemplate> templateFields;
	private TableTypeEnum ttenum;
	private String parentClassSuffix;
	private TableTypeEnum isSingle;

	public CommonGeneratorTableAndMapperVO(MsBaseBilltype majorVO, List<MsBaseBillTemplate> templateFields,
			TableTypeEnum ttenum, String parentClassSuffix) {
		this.majorVO = majorVO;
		this.templateFields = templateFields;
		this.ttenum = ttenum;
		this.parentClassSuffix = parentClassSuffix;
	}

	public CommonGeneratorTableAndMapperVO(MsBaseBilltype majorVO, List<MsBaseBillTemplate> templateFields,
			TableTypeEnum ttenum, String parentClassSuffix, TableTypeEnum isSingle) {
		this.majorVO = majorVO;
		this.templateFields = templateFields;
		this.ttenum = ttenum;
		this.parentClassSuffix = parentClassSuffix;
		this.isSingle = isSingle;
	}

	public String excute() throws ADBusinessException {
		// 得到所属模块
		String module = majorVO.getModuleName();
		// 得到组件块
		String component = majorVO.getComponent();
		log.info(component);
		// 得到表缩写
		String abbreviation = templateFields.get(0).getColumnClassNameAbbreviation();
		// 得到表名 (PS:模块名+缩写)
		String tableName = module + "_" + abbreviation;
		// 得到全类名
		String qualifiedName = templateFields.get(0).getColumnClassQualifiedName();
		// 得到类名
		String className = qualifiedName.substring(qualifiedName.lastIndexOf(".") + 1);
		// 得到包路径
		String packageName = majorVO.getPackageName();
		
		boolean isExistTable = false;

		//修改后的字段[0] 修改前的字段[1]
		String[] cols = null; 
		
		//判断表是否已经存在
		if (TemplateUtil.isExistTableName(tableName)) {
			isExistTable = true;
			cols = TemplateUtil.getColSqls(templateFields, ttenum, tableName, module, parentClassSuffix);
			//存在就将数据保存到临时表中 然后生成新的表后将数据再返回插入
			TemplateUtil.saveDataToTemp(tableName, cols[0]);//select syscolumns.name from syscolumns where id=object_id('bill_type_ak_entityo')
			//执行删除当前已经存在的表
			TemplateUtil.excuteDROPSqlTo(tableName);
		}
		//构建新的表结构
		String tableSql = TemplateUtil.newTableStructure(tableName, abbreviation, templateFields, ttenum, parentClassSuffix, isSingle);
		//执行新的表结构创建新表
		TemplateUtil.excuteSqlTo(tableSql);
		if (isExistTable) {
			//是更新已存在的表后 将临时表的数据再反向插入新的表 因为更新表只会添加字段不可以删除字段
			TemplateUtil.backDataToNewTable(tableName, cols);
			TemplateUtil.backDataAfterBatchUpdateTemplateFields(templateFields);
		}
		String extendVOClass = null;
		switch (ttenum) {
		case AUDIT:
			extendVOClass = MajorAuditBaseVO.class.getName();
			break;
		case MAJOR:
			extendVOClass = MajorBaseVO.class.getName();
			break;
		case CHILD:
			extendVOClass = ChildBaseVO.class.getName();
			break;
		default:
			break;
		}
		// 执行生成mybatis 生成 mapper vo类及xml
		MsGeneratorMybatisMapperVO.generatorMybatisResource(extendVOClass, packageName, tableName, className,
				className + "gmConfig");
		//处理example 继承 BaseExample
		HandleHelper.example(qualifiedName);
		//处理mapper接口 DaoMap类添加泛型 并去掉11个默认生成的方法
		HandleHelper.daoMap(qualifiedName);
		return abbreviation;
	}

}

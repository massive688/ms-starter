package tp.ms.base.rest.generator.freemarker.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tp.ms.base.rest.generator.freemarker.service.TemplateService;
import tp.ms.base.rest.generator.freemarker.service.impl.ace.GeneratorAdditionalResource;
import tp.ms.base.rest.generator.freemarker.service.impl.ace.GeneratorChildResource;
import tp.ms.base.rest.generator.freemarker.service.impl.ace.GeneratorConfigCodeRuleFactoriesResource;
import tp.ms.base.rest.generator.freemarker.service.impl.ace.GeneratorMajorResource;
import tp.ms.base.rest.generator.freemarker.service.impl.ace.GeneratorPolyResource;
import tp.ms.base.rest.typecoded.api.BilltypeService;
import tp.ms.base.rest.typecoded.vo.MsBaseBillTemplate;
import tp.ms.base.rest.typecoded.vo.MsBaseBilltype;
import tp.ms.base.rest.typecoded.vo.PolyMsBaseBilltypeVO;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.utils.ObjectUtilms;
import tp.ms.common.bean.utils.StringUtilms;

@Slf4j
@Service("TemplateService")
public class TemplateServiceImpl implements TemplateService {
	@Autowired
	private BilltypeService billtypeService;

	/*
	 * 创建主子表的sql语句
	 * 
	 * 1、检索表及所有表所需的字段 2、解析组装结构 3、创建表 4、生成mybatis配置文件 5、生成mybatis Mapper接口 xml文件
	 * 实体vo类 BaseExample接口操作类 6、生成聚合类 7、生成rest类文件 8、生成聚合rest类文件 8、生成service 类文件
	 * 9、生成聚合service 类文件
	 * 
	 * @return
	 * @throws ADBusinessException
	 */
	@Override
//	@Transactional(value=TransactionalKey.JTA, rollbackFor= {Exception.class, ADBusinessException.class})
	public Object excuteGeneratorStructure(String billtypeId) throws ADBusinessException {
		PolyMsBaseBilltypeVO polyBilltypeVO = billtypeService.queryByKey(billtypeId);

		MsBaseBilltype majorVO = polyBilltypeVO.getParentVO();

		MsBaseBillTemplate[] templateFields = polyBilltypeVO.getChildren(MsBaseBillTemplate.class);
		
		String parentClass = null;
		
		String parentClassSuffix;
		// 得到包路径
		String packageName = majorVO.getPackageName();
		//主表字段
		List<MsBaseBillTemplate> majorFields;
		//子表所有字段
		Map<String, List<MsBaseBillTemplate>> childFields = null;
		//所有子表后缀名
		List<String> childClassSuffixes;

		//模块前缀名 使用在类前面的 MyAdreamActivity 中的 MyAdream
		String module;
		//URL Mapping映射的前缀名 使用在类前面的 通过ClassSuffix后缀 ‘-’ 转换得来
		String mapping;
		
		
		switch (majorVO.getComponentType()) {
		case 0:
			//单表头
			//生成主表资源得到 主表类后缀 (ms_base_bill_template表为列：类后缀 BillTemlate 表后缀 bill_template)
			new GeneratorMajorResource(majorVO, Arrays.asList(templateFields), TableTypeEnum.MAJOR).excute();
			break;
		case 2:
			//单表体
			//生成子表资源得到 主表类后缀集合 (ms_base_bill_template表为列：类后缀 BillTemlate 表后缀 bill_template)
			if(ObjectUtilms.isNotEmpty(templateFields)) {
				childFields = new HashMap<String, List<MsBaseBillTemplate>>();
				String key = templateFields[0].getColumnClassQualifiedName();
				childFields.put(key, Arrays.asList(templateFields));
			}
			childClassSuffixes = new GeneratorChildResource(majorVO,  childFields, null, TableTypeEnum.SINGLE).excute();
			break;
		case 1:
			//主子型
		case 3:
			//审核流程主子型
			majorFields = new ArrayList<MsBaseBillTemplate>();
			childFields = new HashMap<String, List<MsBaseBillTemplate>>();
			
			for (MsBaseBillTemplate templateField : templateFields) {
				if (ObjectUtilms.isEqual(templateField.getColumnModuleName(), "parent")) {
					if(parentClass == null) {
						parentClass = templateField.getColumnClassQualifiedName();
						parentClass = parentClass.substring(parentClass.lastIndexOf(".") + 1);
					}
					majorFields.add(templateField);
				} else {
					String key = templateField.getColumnClassQualifiedName();
					List<MsBaseBillTemplate> childrenFields;
					if(childFields.containsKey(key)) {
						childrenFields = childFields.get(key);
					}else {
						childrenFields = new ArrayList<MsBaseBillTemplate>();
						childFields.put(key, childrenFields);
					}
					childrenFields.add(templateField);
				}
			}
			
			if(majorFields.isEmpty()) {
				throw new ADBusinessException("缺少主表字段信息！！！");
			}
			
			TableTypeEnum ttenum = majorVO.getComponentType() == 3 ? TableTypeEnum.AUDIT : TableTypeEnum.MAJOR;
			
			//生成主表资源得到 主表类后缀 (ms_base_bill_template表为列：类后缀 BillTemlate 表后缀 bill_template)
			parentClassSuffix = new GeneratorMajorResource(majorVO, majorFields, ttenum).excute();
			//生成子表资源得到 主表类后缀集合 (ms_base_bill_template表为列：类后缀 BillTemlate 表后缀 bill_template)
			childClassSuffixes = new GeneratorChildResource(majorVO, childFields, parentClassSuffix, null).excute();
			
			mapping = StringUtilms.translateLowerString(parentClassSuffix, '-');
			module = StringUtilms.translateUpperString(majorVO.getModuleName(), '_');

			new GeneratorPolyResource(packageName, module, parentClassSuffix, childClassSuffixes, mapping).excute();

			new GeneratorAdditionalResource(packageName, parentClassSuffix).excute();

			new GeneratorConfigCodeRuleFactoriesResource(packageName, parentClassSuffix, module+parentClassSuffix).excute();
			break;
		default:
			break;
		}
		String msg = "成功生成资源文件";
		log.info(msg);
		return msg;

	}

}

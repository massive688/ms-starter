package tp.ms.base.rest.formula.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.val;
import tp.ms.base.rest.formula.api.FormulaValueService;
import tp.ms.base.rest.formula.httparam.FormulaExecValue;
import tp.ms.base.rest.formula.httparam.TranslateValue;
import tp.ms.base.rest.formula.mapper.FormulaValueDaoMapper;
import tp.ms.base.rest.formula.vo.FormulaObject;
import tp.ms.base.rest.refinfo.api.BaseRefInfoService;
import tp.ms.common.bean.exception.AExceptionUtils;
import tp.ms.common.bean.result.TranslateRef;
import tp.ms.common.bean.support.context.SpringContextHolder;
import tp.ms.common.data.mybatis.annotation.SqlSessionKey;
import tp.ms.common.data.mybatis.annotation.TargetSqlSession;

@Service
@TargetSqlSession(SqlSessionKey.CS6304)
public class FormulaValueServiceImpl implements FormulaValueService {

	@Autowired
	FormulaValueDaoMapper dao;
	@Autowired
	BaseRefInfoService refService;

	@Override
	public String execute(TranslateValue data) {
		String refClass = refService.getRefClass(data.getTranslate());
		Class<?> clz = null;
		try {
			clz = Class.forName(refClass);
		} catch (ClassNotFoundException e) {
			AExceptionUtils.unSupported("translate ref class Wrongful");
		}
		TranslateRef translate = (TranslateRef) SpringContextHolder.getBean(clz);
		if (translate == null)
			AExceptionUtils.unSupported("translate ref class not find in spring");
		FormulaObject param = new FormulaObject();
		param.setFieldCode(data.getShow() == 0 ? translate.getCodeField() : translate.getNameField());
		param.setTable(translate.getTable());
		param.setWhere(translate.getWherePart() + " and " + translate.getPkField() + "='" + data.getValue() + "'");
		return dao.queryByFormula(param);
	}

	@Override
	public String execute(FormulaExecValue data) {
		String refClass = refService.getRefClass(data.getTranslate());
		Class<?> clz = null;
		try {
			clz = Class.forName(refClass);
		} catch (ClassNotFoundException e) {
			AExceptionUtils.unSupported("translate ref class Wrongful");
		}
		TranslateRef translate = (TranslateRef) SpringContextHolder.getBean(clz);
		if (translate == null)
			AExceptionUtils.unSupported("translate ref class not find in spring");
		FormulaObject param = new FormulaObject();
		param.setTable(translate.getTable());
		/**
		 * 公式的形式为指定格式的字符串数组
		 *
		 * 函数,目标字段属性,数据库要查询的字段,当前字段value值 
		 * ['getVal,dept,dept']
		 */

		val formula = data.getFormula().split(",");
		val func = formula[0];
		val dbField = formula[2];
		if("getVal".equalsIgnoreCase(func)) {
			param.setFieldCode(dbField);
		}
		param.setWhere(translate.getWherePart() + " and " + translate.getPkField() + "='" + data.getValue() + "'");
		return dao.queryByFormula(param);
	}

}

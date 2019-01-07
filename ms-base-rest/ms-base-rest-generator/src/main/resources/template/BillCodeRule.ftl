package com.${packageName}.rule;

import com.${packageName}.vo.Poly${parentClass}VO;

import tp.ms.base.rest.resource.service.ace.IRule;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.support.context.BeanHelperEnv;
import tp.ms.common.bean.support.context.MsEnvContextHolder;
import tp.ms.common.bean.support.context.SpringContextHolder;
import tp.ms.common.bean.utils.ObjectUtilms;
import tp.ms.common.bean.utils.StringUtilms;
import tp.ms.common.bean.vo.Business;

public class ${parentClassSuffix}BillCodeRule implements IRule<Poly${parentClass}VO> {

	@Override
	public void process(Poly${parentClass}VO vo) throws ADBusinessException {
		BeanHelperEnv beanHelper = SpringContextHolder.getBean(BeanHelperEnv.class);
		Business bis = MsEnvContextHolder.getContext().business();
		String billtype;
		if(ObjectUtilms.isEmpty(bis) || StringUtilms.isEmpty(bis.getPkBilltype()))
			billtype = vo.getParent().getBilltype();
		else
			billtype = bis.getPkBilltype();
		vo.getParent().setCode(beanHelper.generateBatchCodes(billtype));
	}

}


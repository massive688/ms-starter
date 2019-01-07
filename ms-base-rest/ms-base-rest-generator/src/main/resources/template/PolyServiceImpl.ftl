package com.${packageName}.service.poly.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.${packageName}.rule.${parentClassSuffix}BillCodeRule;
<#list childClassSuffixes as childClass>
import com.${packageName}.service.child.${childClass}Service;
</#list>
import com.${packageName}.service.major.${parentClassSuffix}MajorService;
import com.${packageName}.service.poly.${parentClassSuffix}Service;
<#list childClassSuffixes as childClass>
import com.${packageName}.vo.${module}${childClass};
</#list>
import com.${packageName}.vo.Poly${parentClass}VO;
import com.${packageName}.vo.${parentClass};
import com.${packageName}.vo.${parentClass}Example;

import tp.ms.base.rest.resource.service.IChildService;
import tp.ms.base.rest.resource.service.IMajorService;
import tp.ms.base.rest.resource.service.ace.CommonAroundProcesser;
import tp.ms.base.rest.resource.service.impl.PolyServiceImpl;
import tp.ms.base.rest.resource.vo.ChildBaseVO;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.vo.BaseExample;


@Service
public class ${parentClassSuffix}ServiceImpl extends PolyServiceImpl<Poly${parentClass}VO> implements ${parentClassSuffix}Service {

	@Autowired
	${parentClassSuffix}MajorService majorService;
	
	@SuppressWarnings("unchecked")
	@Override
	public IMajorService<${parentClass}, ${parentClass}Example> getMajorService()
			throws ADBusinessException {
		return majorService;
	}


<#list childClassSuffixes as childClassSuffix>
	@Autowired
	${childClassSuffix}Service ${childClassSuffix}Service;
	
</#list>
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <C extends ChildBaseVO, B extends BaseExample> IChildService getChildService(Class<C> clz)
			throws ADBusinessException {
<#list childClassSuffixes as childClassSuffix>
		if(clz == ${module}${childClassSuffix}.class)
			return ${childClassSuffix}Service;
</#list>
		return null;
	}

   
	@Override
	public void addBeforeRule(CommonAroundProcesser<Poly${parentClass}VO> aroundProcesser) {
		aroundProcesser.addBeforeRule(new ${parentClassSuffix}BillCodeRule());
		super.addBeforeRule(aroundProcesser);
	}
	   
}



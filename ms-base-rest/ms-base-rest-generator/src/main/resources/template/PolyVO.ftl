package com.${packageName}.vo;

import tp.ms.base.rest.resource.vo.AbstractPolyVO;
import tp.ms.base.rest.resource.vo.IMajorVO;

public class Poly${parentClass}VO extends AbstractPolyVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void registerChildrenClass() {
		initChildrenClass(new Class[] {
<#list childClassSuffixes as childClassSuffix>
				${module}${childClassSuffix}.class, 
</#list>
			});
		
	}

	@Override
	public Class<? extends IMajorVO> getParentClass() {
		return ${parentClass}.class;
	}

	@Override
	public ${parentClass} getParentVO() {
		return (${parentClass})this.getParent();
	}

}


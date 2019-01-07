package com.${packageName}.service.child.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.${packageName}.mapper.${className}Mapper;
import com.${packageName}.service.child.${pkSuffix}Service;
import com.${packageName}.vo.${className};
import com.${packageName}.vo.${className}Example;
import com.${packageName}.vo.${className}Example.Criteria;

import tp.ms.base.rest.resource.http.Pager;
import tp.ms.base.rest.resource.service.impl.ChildServiceImpl;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.vo.BaseExample;
import tp.ms.common.bean.vo.BaseVO;
import tp.ms.common.data.mybatis.mapper.DaoMapper;

@Service
public class ${pkSuffix}ServiceImpl extends ChildServiceImpl<${className}, ${className}Example> implements ${pkSuffix}Service {

	@Autowired
	${className}Mapper dao;
	
	@Override
	public DaoMapper<${className}, ${className}Example> getDao() {
		return dao;
	}

	@Override
	public Map<Class<BaseVO>, BaseExample> transformToExampleFromByPolyArray(${className}[] vos)
			throws ADBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseExample transformToExampleFromPagination(Pager page) throws ADBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setExampleParentKeyCondition(${className}Example example, String parentKey) {
		<#if !isSingle>
		example.createCriteria().andDrEqualTo(0)
		.andPk${parentClassSuffix}EqualTo(parentKey);
		</#if>
	}

	@Override
	public void setQueryExample(${className}Example example, String key) {
		Criteria criteria = example.createCriteria();
		criteria.andDrEqualTo(0);
		if(key != null)
			criteria.andPk${pkSuffix}EqualTo(key);
	}

}

package com.${packageName}.service.individualization.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.${packageName}.mapper.individualization.${pkSuffix}IndividualizationMapper;
import com.${packageName}.service.individualization.${pkSuffix}IndividualizationService;

import tp.ms.base.rest.resource.http.Pager;
import tp.ms.common.bean.exception.ADBusinessException;


@Service
public class ${pkSuffix}IndividualizationServiceImpl implements ${pkSuffix}IndividualizationService {
	

	@Autowired
	${pkSuffix}IndividualizationMapper mapper;
	
	public Pager individuaList(Pager pager) throws ADBusinessException {
		PageHelper.startPage(pager.getCurrent(), pager.getShowNum());
		PageInfo<Object> objs = new PageInfo<Object>(mapper.getListInfos());
		return new Pager(objs);
	}

}

package com.${packageName}.service.reference.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.${packageName}.mapper.reference.${pkSuffix}ReferenceMapper;
import com.${packageName}.service.reference.${pkSuffix}ReferenceService;


@Service
public class ${pkSuffix}ReferenceServiceImpl implements ${pkSuffix}ReferenceService {

	@Autowired
	${pkSuffix}ReferenceMapper dao;
	

	

}

package com.${packageName}.rest.reference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.${packageName}.service.reference.${pkSuffix}ReferenceService;

import tp.ms.base.rest.resource.BasisResource;
import tp.ms.base.rest.resource.http.Pager;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.result.ResultSupport;

@RestController
@RequestMapping("/api/${mapping}-reference")
public class ${pkSuffix}ReferenceResource extends BasisResource<Object> {

	@Autowired
	${pkSuffix}ReferenceService service;
	
	
	@PostMapping("${mapping}-list")
	public Object queryReferenceList(Pager pager) throws ADBusinessException {
		return ResultSupport.ok();
	}
	
}



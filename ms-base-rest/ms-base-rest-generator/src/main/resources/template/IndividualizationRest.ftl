package com.${packageName}.rest.individualization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.${packageName}.service.individualization.${pkSuffix}IndividualizationService;

import tp.ms.base.rest.resource.BasisResource;
import tp.ms.base.rest.resource.http.Pager;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.result.ResultSupport;

@RestController
@RequestMapping("/api/${mapping}-individualization")
public class ${pkSuffix}IndividualizationResource extends BasisResource<Object> {

	@Autowired
	${pkSuffix}IndividualizationService service;
	
	
	@PostMapping("${mapping}-list")
	public Object queryIndividualizationList(Pager pager) throws ADBusinessException {
		return ResultSupport.ok(service.individuaList(pager));
	}
	
}


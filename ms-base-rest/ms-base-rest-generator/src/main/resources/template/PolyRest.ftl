package com.${packageName}.rest.poly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.${packageName}.service.poly.${parentClassSuffix}Service;
import com.${packageName}.vo.Poly${parentClass}VO;

import tp.ms.base.rest.resource.PolyController;
import tp.ms.base.rest.resource.service.IPolyService;

@RestController
@RequestMapping("/api/${mapping}-poly")
public class Poly${parentClassSuffix}Resource extends PolyController<Poly${parentClass}VO> {

	@Autowired
	${parentClassSuffix}Service service;
	
	@Override
	public IPolyService<Poly${parentClass}VO> getService() {
		return service;
	}

}


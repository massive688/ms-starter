package com.${packageName}.rest.major;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.${packageName}.service.major.${pkSuffix}MajorService;
import com.${packageName}.vo.${className};
import tp.ms.base.rest.resource.MajorController;
import tp.ms.base.rest.resource.service.IBaseService;

@RestController
@RequestMapping("/api/${mapping}-major")
public class ${pkSuffix}MajorResource extends MajorController<${className}> {

	@Autowired
	${pkSuffix}MajorService service;
	
	@Override
	public IBaseService<${className}> getService() {
		return service;
	}

}


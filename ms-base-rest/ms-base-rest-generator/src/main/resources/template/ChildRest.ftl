package com.${packageName}.rest.child;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.${packageName}.service.child.${pkSuffix}Service;
import com.${packageName}.vo.${className};

import tp.ms.base.rest.resource.ChildController;
import tp.ms.base.rest.resource.service.IBaseService;

@RestController
@RequestMapping("/api/${mapping}")
public class ${pkSuffix}Resource extends ChildController<${className}> {
	
	@Autowired
	${pkSuffix}Service service;
	
	@Override
	public IBaseService<${className}> getService() {
		return service;
	}

}



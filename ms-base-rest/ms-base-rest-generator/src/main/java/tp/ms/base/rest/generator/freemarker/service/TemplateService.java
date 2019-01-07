package tp.ms.base.rest.generator.freemarker.service;

import tp.ms.common.bean.exception.ADBusinessException;

public interface TemplateService {

	 


	Object excuteGeneratorStructure(String billKey) throws ADBusinessException;

	 

}

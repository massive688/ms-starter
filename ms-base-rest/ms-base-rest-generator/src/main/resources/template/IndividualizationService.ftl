package com.${packageName}.service.individualization;

import tp.ms.base.rest.resource.http.Pager;
import tp.ms.common.bean.exception.ADBusinessException;

public interface ${pkSuffix}IndividualizationService {

	public Pager individuaList(Pager pager) throws ADBusinessException;

}

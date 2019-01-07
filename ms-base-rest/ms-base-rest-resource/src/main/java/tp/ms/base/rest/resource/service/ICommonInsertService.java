package tp.ms.base.rest.resource.service;

import tp.ms.base.rest.resource.service.ace.CommonAroundProcesser;

public interface ICommonInsertService<T> {


	public void addBeforeRule(CommonAroundProcesser<T> aroundProcesser);
	
	public void addAfterRule(CommonAroundProcesser<T> aroundProcesser);
	
}

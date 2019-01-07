package tp.ms.base.rest.resource.service;

import tp.ms.base.rest.resource.service.ace.CompareAroundProcesser;

public interface ICompareUpdateService<T> {

	/**
	 * 
	 */
	public void addBeforeRule(CompareAroundProcesser<T> aroundProcesser);
	
	public void addAfterRule(CompareAroundProcesser<T> aroundProcesser);
	
}

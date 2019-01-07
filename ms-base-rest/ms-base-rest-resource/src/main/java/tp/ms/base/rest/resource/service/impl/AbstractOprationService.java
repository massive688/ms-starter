package tp.ms.base.rest.resource.service.impl;

import tp.ms.base.rest.resource.service.ICommonInsertService;
import tp.ms.base.rest.resource.service.ICompareUpdateService;
import tp.ms.base.rest.resource.service.ace.CommonAroundProcesser;
import tp.ms.base.rest.resource.service.ace.CompareAroundProcesser;

public abstract class AbstractOprationService<T>  implements ICompareUpdateService<T>, ICommonInsertService<T> {
	
	@Override
	public void addBeforeRule(CompareAroundProcesser<T> aroundProcesser) {
		aroundProcesser.addBeforeRule(null);
	}


	@Override
	public void addAfterRule(CompareAroundProcesser<T> aroundProcesser) {
		
	}



	@Override
	public void addBeforeRule(CommonAroundProcesser<T> aroundProcesser) {
	}


	@Override
	public void addAfterRule(CommonAroundProcesser<T> aroundProcesser) {
		
		
	}


}

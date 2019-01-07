package tp.ms.base.rest.resource.service.strengthen;

import tp.ms.common.bean.exception.ADBusinessException;

public class VersionConflictException extends ADBusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1820540643127585274L;
	private Object busiObject;
	public VersionConflictException()
	{
		
	}
	public VersionConflictException(Object busiObject) {
		super();
		this.busiObject = busiObject;
	}

	public Object getBusiObject() {
		return busiObject;
	}

	public void setBusiObject(Object busiObject) {
		this.busiObject = busiObject;
	}
	
}

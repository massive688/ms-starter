package tp.ms.base.rest.refinfo.vo;

import java.io.Serializable;

import tp.ms.common.bean.vo.BaseVO;

public class BaseRefInfoVO extends BaseVO implements Serializable {

	String id;
	
	String code;
	
	String name;
	
	String marker;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7801743413941877390L;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMarker() {
		return marker;
	}

	public void setMarker(String marker) {
		this.marker = marker;
	}

	@Override
	public String getTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPrimaryKey(String key) {
		this.id = key;
	}

	@Override
	public String getPrimaryKey() {
		return id;
	}
    
}
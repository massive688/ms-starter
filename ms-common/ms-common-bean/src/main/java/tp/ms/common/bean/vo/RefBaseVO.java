package tp.ms.common.bean.vo;

@SuppressWarnings("serial")
public abstract class RefBaseVO extends BaseVO  {

	String id;
	
	String code;
	
	String name;
	
	String marker;

	public abstract String getId();

	public void setId(String id) {
		this.id = id;
	}

	public abstract String getCode();

	public void setCode(String code) {
		this.code = code;
	}

	public abstract String getName();

	public void setName(String name) {
		this.name = name;
	}

	public abstract String getMarker();

	public void setMarker(String marker) {
		this.marker = marker;
	}
	
	
	
	
}

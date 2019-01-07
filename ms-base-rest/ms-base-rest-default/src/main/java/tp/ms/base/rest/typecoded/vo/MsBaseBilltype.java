package tp.ms.base.rest.typecoded.vo;

import java.io.Serializable;

import tp.ms.base.rest.resource.vo.MajorAuditBaseVO;

public class MsBaseBilltype extends MajorAuditBaseVO implements Serializable {

    private String pkBilltype;

    private String moduleName;

    private String packageName;

    private String component;

    private Integer componentType;

    private String uiPath;

    private static final long serialVersionUID = 1L;

    public String getPkBilltype() {
        return pkBilltype;
    }

    public void setPkBilltype(String pkBilltype) {
        this.pkBilltype = pkBilltype == null ? null : pkBilltype.trim();
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName == null ? null : packageName.trim();
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component == null ? null : component.trim();
    }

    public Integer getComponentType() {
        return componentType;
    }

    public void setComponentType(Integer componentType) {
        this.componentType = componentType;
    }

    public String getUiPath() {
        return uiPath;
    }

    public void setUiPath(String uiPath) {
        this.uiPath = uiPath == null ? null : uiPath.trim();
    }


    
	@Override
	public String getTable() {
		return "ms_base_billtype";
	}

	@Override
	public void setPrimaryKey(String key) {
		setBilltype(key);
		this.pkBilltype = key;
	}

	@Override
	public String getPrimaryKey() {
		return this.pkBilltype;
	}

}
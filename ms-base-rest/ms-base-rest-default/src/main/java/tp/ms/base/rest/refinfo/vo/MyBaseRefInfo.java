package tp.ms.base.rest.refinfo.vo;

import java.io.Serializable;

import tp.ms.common.bean.vo.BaseVO;

public class MyBaseRefInfo extends BaseVO implements Serializable {
    private String pkBaseRefInfo;

    private String refCode;

    private String refName;

    private String refClass;

    private Integer refType;

    private String modulename;

    private static final long serialVersionUID = 1L;

    public String getPkBaseRefInfo() {
        return pkBaseRefInfo;
    }

    public void setPkBaseRefInfo(String pkBaseRefInfo) {
        this.pkBaseRefInfo = pkBaseRefInfo == null ? null : pkBaseRefInfo.trim();
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode == null ? null : refCode.trim();
    }

    public String getRefName() {
        return refName;
    }

    public void setRefName(String refName) {
        this.refName = refName == null ? null : refName.trim();
    }

    public String getRefClass() {
        return refClass;
    }

    public void setRefClass(String refClass) {
        this.refClass = refClass == null ? null : refClass.trim();
    }

    public Integer getRefType() {
        return refType;
    }

    public void setRefType(Integer refType) {
        this.refType = refType;
    }

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename == null ? null : modulename.trim();
    }


	@Override
	public String getTable() {
		return "ms_base_ref_info";
	}

	@Override
	public void setPrimaryKey(String key) {
		this.pkBaseRefInfo = key;
	}

	@Override
	public String getPrimaryKey() {
		return pkBaseRefInfo;
	}
}
package tp.ms.base.rest.ots.staff.entity;

import java.io.Serializable;
import java.util.Map;

import tp.ms.common.bean.vo.User;


public class MyAdreamStaff extends User implements Serializable {
	
    private String roles;

    private String pkCorp;
    
    private String creator;

    private String creationtime;

    private String modifier;

    private String modifiedtime;

    private String billtype;

    private Integer enabled;

    private String ts;

    private Integer dr;

	private Map<String, Object> result;

    private static final long serialVersionUID = 1L;

    public MyAdreamStaff() {

	}
    public MyAdreamStaff(String username, String string) {

	}

	public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles == null ? null : roles.trim();
    }

    public String getPkCorp() {
        return pkCorp;
    }

    public void setPkCorp(String pkCorp) {
    	setPkOrg(pkCorp);
        this.pkCorp = pkCorp == null ? null : pkCorp.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(String creationtime) {
        this.creationtime = creationtime == null ? null : creationtime.trim();
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public String getModifiedtime() {
        return modifiedtime;
    }

    public void setModifiedtime(String modifiedtime) {
        this.modifiedtime = modifiedtime == null ? null : modifiedtime.trim();
    }

    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype == null ? null : billtype.trim();
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts == null ? null : ts.trim();
    }

    public Integer getDr() {
        return dr;
    }

    public void setDr(Integer dr) {
        this.dr = dr;
    }
    
	public void setServerResult(Map<String, Object> result) {
		this.result = result;
	}

	public Map<String, Object> getServerResult() {
		return result;
	}
}
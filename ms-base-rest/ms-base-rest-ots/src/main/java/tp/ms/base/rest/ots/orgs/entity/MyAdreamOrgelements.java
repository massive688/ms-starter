package tp.ms.base.rest.ots.orgs.entity;

import java.io.Serializable;

import tp.ms.base.rest.resource.vo.MajorBaseVO;

public class MyAdreamOrgelements extends MajorBaseVO implements Serializable {
	
    private String pkOrgelements;

    private String parentId;

    private Integer type;

    private static final long serialVersionUID = 1L;

    public String getPkOrgelements() {
        return pkOrgelements;
    }

    public void setPkOrgelements(String pkOrgelements) {
        this.pkOrgelements = pkOrgelements == null ? null : pkOrgelements.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

	@Override
	public String getTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPrimaryKey(String key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return null;
	}
    
}
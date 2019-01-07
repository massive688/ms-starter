package tp.ms.base.rest.resource.vo;

import tp.ms.common.bean.vo.BaseVO;

@SuppressWarnings("serial")
public abstract class MajorBaseVO extends BaseVO implements IMajorVO{
	

	//组织主键
	protected String pkCorp;

	//组织主键
	protected String pkOrg;

	//集团主键
	protected String pkGroup;

	//创建人
	protected String creator;

	//
	protected String creationtime;

	//修改人
	protected String modifier;

	protected String modifiedtime;

	protected String billtype;
	//数据是否启用权限 默认为 0
	protected Integer enabled;


	
	public Integer getEnabled() {
		return enabled;
	}
	
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getPkCorp() {
        return this.pkCorp;
    }

    public void setPkCorp(String pkCorp) {
    	setPkOrg(pkCorp);
        this.pkCorp = pkCorp == null ? null : pkCorp.trim();
    }

    public String getPkGroup() {
        return pkGroup;
    }

    public void setPkGroup(String pkGroup) {
        this.pkGroup = pkGroup == null ? null : pkGroup.trim();
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
		this.creationtime = creationtime;
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
    


    protected String getPkOrg() {
        return pkOrg;
    }

    protected void setPkOrg(String pkOrg) {
        this.pkOrg = pkOrg == null ? null : pkOrg.trim();
    }
    
    /**
     * 以下五个方法
     * 可以由子类间接实现，根据实际情况需求
     */
    @Override
    public String getPId() {
    	return null;
    }


    protected String code;
    
    protected String name;
    
	@Override
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getName() {
		return name;
	}

	
}

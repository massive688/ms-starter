package tp.ms.base.rest.resource.vo;

public interface IMajorVO extends IBDObject,IAuditInfo{
	
	void setCode(String code);
	
	void setName(String name);
	
	String getPkCorp();

    void setPkCorp(String pkCorp);

    String getPkGroup();

    void setPkGroup(String pkGroup);

    void setCreator(String creator);

	void setCreationtime(String creationtime);

    void setModifier(String modifier);

    void setModifiedtime(String modifiedtime);
    
    String getBilltype();

    void setBilltype(String billtype);
}

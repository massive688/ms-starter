package tp.ms.base.rest.typecoded.vo;

import tp.ms.base.rest.resource.vo.ChildBaseVO;

public class MsBaseBillCodeRule extends ChildBaseVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2115252607207389053L;
	
	//编码元素类型：0-常量，1-编码实体，2-时间类型，3-流水号
	public static final int TYPE_CONST = 0;
	public static final int TYPE_ENTITY = 1;
	public static final int TYPE_TIME = 2;
	public static final int TYPE_SN = 3;
	

	public static final int REF_NOT = 0;//不作为流水依据
	public static final int REF_YES = 1;//按年流水
	public static final int REF_MON = 2;//按月流水
	public static final int REF_DAY = 3;//按日流水
	
    private String pkBaseBillCodeRule;

    private Integer codelength;

    private Short elemtype;

    private String lastserialnumber;

    private Short isrefer = 0;

    private String codeprefix;

    private String constantfix;

    private String timeformat;

    private String lasttimevalue;

    private String isautofill;

	protected String billtype;
	
	protected String pkCorp;
	
    public String getPkBaseBillCodeRule() {
        return pkBaseBillCodeRule;
    }

    public void setPkBaseBillCodeRule(String pkBaseBillCodeRule) {
        this.pkBaseBillCodeRule = pkBaseBillCodeRule == null ? null : pkBaseBillCodeRule.trim();
    }

    public Integer getCodelength() {
        return codelength;
    }

    public void setCodelength(Integer codelength) {
        this.codelength = codelength;
    }

    public Short getElemtype() {
        return elemtype;
    }

    public void setElemtype(Short elemtype) {
        this.elemtype = elemtype;
    }

    public String getLastserialnumber() {
        return lastserialnumber;
    }

    public void setLastserialnumber(String lastserialnumber) {
        this.lastserialnumber = lastserialnumber == null ? null : lastserialnumber.trim();
    }

    public Short getIsrefer() {
        return isrefer;
    }

    public void setIsrefer(Short isrefer) {
        this.isrefer = isrefer;
    }

    public String getCodeprefix() {
        return codeprefix;
    }

    public void setCodeprefix(String codeprefix) {
        this.codeprefix = codeprefix == null ? null : codeprefix.trim();
    }

    public String getConstantfix() {
        return constantfix;
    }

    public void setConstantfix(String constantfix) {
        this.constantfix = constantfix == null ? null : constantfix.trim();
    }

    public String getTimeformat() {
        return timeformat;
    }

    public void setTimeformat(String timeformat) {
        this.timeformat = timeformat == null ? null : timeformat.trim();
    }

    public String getLasttimevalue() {
        return lasttimevalue;
    }

    public void setLasttimevalue(String lasttimevalue) {
        this.lasttimevalue = lasttimevalue == null ? null : lasttimevalue.trim();
    }

    public boolean isAutofill() {
        return isautofill=="Y"?true:false;
    }

    public void setIsautofill(String isautofill) {
        this.isautofill = isautofill == null ? null : isautofill.trim();
    }
    
    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype == null ? null : billtype.trim();
    }
    
	public String getPkCorp() {
		return pkCorp;
	}

	public void setPkCorp(String pkCorp) {
        this.pkCorp = pkCorp == null ? null : pkCorp.trim();
	}

	@Override
	public String getParentKey() {
		return billtype;
	}

	@Override
	public void setParentKey(String parentKey) {
		this.billtype = parentKey;
	}


	@Override
	public void setPrimaryKey(String key) {
		this.pkBaseBillCodeRule = key;
	}

	@Override
	public String getPrimaryKey() {
		return this.pkBaseBillCodeRule;
	}

	@Override
	public String getTable() {
		return "ms_base_bill_code_rule";
	}



}
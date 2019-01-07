package tp.ms.base.rest.typecoded.vo;

import tp.ms.base.rest.resource.vo.ChildBaseVO;

public class MyBillcodereturn extends ChildBaseVO {
   /*
	 * 
	 */
	private static final long serialVersionUID = -3663633612827610786L;

	private String pkBillcoderulebase;

    private String pkBillcodereturn;

    private String markstr;

    private String rtsns;

    public String getPkBillcoderulebase() {
        return pkBillcoderulebase;
    }

    public void setPkBillcoderulebase(String pkBillcoderulebase) {
        this.pkBillcoderulebase = pkBillcoderulebase == null ? null : pkBillcoderulebase.trim();
    }

    public String getPkBillcodereturn() {
        return pkBillcodereturn;
    }

    public void setPkBillcodereturn(String pkBillcodereturn) {
        this.pkBillcodereturn = pkBillcodereturn == null ? null : pkBillcodereturn.trim();
    }

    public String getMarkstr() {
        return markstr;
    }

    public void setMarkstr(String markstr) {
        this.markstr = markstr == null ? null : markstr.trim();
    }

    public String getRtsns() {
        return rtsns;
    }

    public void setRtsns(String rtsns) {
        this.rtsns = rtsns == null ? null : rtsns.trim();
    }

	@Override
	public String getParentKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParentKey(String parentKey) {
		// TODO Auto-generated method stub
		
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
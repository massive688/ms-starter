package tp.ms.common.bean.vo;

import javax.validation.ValidationException;

@SuppressWarnings("serial")
public abstract class BaseVO extends CircularlyBaseVO implements IBaseVO {
	
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

    
    public Object clone() {
//    	JSONObject object = JSONObject.fromObject(this);
//		byte[] bytes = JSON.toJSONBytes(this, SerializerFeature.BeanToArray, SerializerFeature.IgnoreNonFieldGetter);
//		return JSONObject.toBean(object);
    	Object returnObject = null;
    	//利用对象的序列化和反序列化实现对象的复制
//    	try {
//        	ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        	ObjectOutputStream oos = new ObjectOutputStream(baos);
//			oos.writeObject(this);
//	    	ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
//	    	ObjectInputStream ois = new ObjectInputStream(bais);
//	    	returnObject = ois.readObject();
//		} catch (IOException | ClassNotFoundException e) {
//			e.printStackTrace();
//		}
    	try {
    		returnObject = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
    	return returnObject;
    }

	@Override
	public void validate() throws ValidationException {
	}
	
	@Override
	public <DaoMapper> Class<? extends DaoMapper> getDaoMapperClass() {
		return null;
	}

}

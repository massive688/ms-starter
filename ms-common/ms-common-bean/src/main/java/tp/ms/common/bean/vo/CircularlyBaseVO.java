package tp.ms.common.bean.vo;

import java.io.Serializable;

import javax.validation.ValidationException;

import com.alibaba.fastjson.annotation.JSONType;

@SuppressWarnings("serial")
@JSONType()
public abstract class CircularlyBaseVO implements Cloneable,Serializable{


	// 对象状态 0无操作 1更新 2新增 3删除
	private byte status = VoStatus.UNCHANGED;

	protected String ts;

	//删除标识
	protected Integer dr;

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}


	public abstract void setPrimaryKey(String key) ;

	public abstract String getPrimaryKey() ;

	/**
	 * 验证对象各属性之间的数据逻辑正确性。
	 * 
	 * 创建日期：(2001-2-15 11:47:35)
	 * 
	 * @exception nc.vo.pub.ValidationException
	 *                如果验证失败，抛出 ValidationException，对错误进行解释。
	 */
	public abstract void validate() throws ValidationException;
}

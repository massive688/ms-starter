package tp.ms.common.bean.vo;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public interface IBaseVO extends Serializable, Cloneable {

	/**
	 * 得到主键
	 * 
	 * @return String
	 */
	public String getPrimaryKey();

	/**
	 * 获得VO的状态
	 * 
	 * @return int
	 */
	public byte getStatus();

	/**
	 * 设置VO的状态
	 * 
	 * @param status 转入要设置的状态
	 */
	public void setStatus(byte status);

	/**
	 * 克隆当前VO，只是克隆可序列化的属性 对于javatype是object的，这里只是拷贝一个引用而已，如果需要深度克隆，请重载 此方法
	 * 
	 * @return ISmartVO
	 */
	public Object clone();

	@JSONField(serialize = false)
	public abstract String getTable();

	@JSONField(serialize = false)
	public abstract <DaoMapper> Class<? extends DaoMapper> getDaoMapperClass();
	
	public String getTs();
}

package tp.ms.base.rest.resource.vo;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import tp.ms.common.bean.vo.IBaseVO;
import tp.ms.common.bean.vo.IChildVO;

public interface IPolyVO {
	/**
	 * 克隆当前的单据
	 * 
	 * @return 返回克隆后的对象
	 */
	Object clone();

	/**
	 * 根据子实体的类型获得得到子实体数据，主要应用场景为多子表的时候
	 * 
	 * @param clazz
	 *            子实体的类型
	 * @return 子实体数据
	 */
	<Child extends IChildVO> Child[] getChildren(Class<Child> clazz);

//	IChildVO[] getChildren(Class<? extends IChildVO> clazz);
	
	/**
	 * 获取一主多子单据的主实体
	 * 
	 * @return 一主多子单据的主实体
	 */
	IBaseVO getParent();

	/**
	 * 获得一主多子单据的主键
	 * 
	 * @return 一主多子单据的主键
	 */
	String getPrimaryKey();

	/**
	 * 获取初始化子实体的类型
	 * 
	 * @param clazz
	 *            子实体的类型數組
	 * @param children
	 *            子实体数据
	 */
	  
	List<Class<ChildBaseVO>> getChildrenClass();

	/**
	 * 根据子实体的类型设置子实体数据
	 * 
	 * @param clazz
	 *            子实体的类型
	 * @param children
	 *            子实体数据
	 */
	void setChildren(Class<? extends IChildVO> clazz, IChildVO[] children);

	/**
	 * 获得子表VO对象数组。
	 * 
	 * @return 子表VO对象数组
	 */
	public IChildVO[] getChildrenVO();
	/**
	 * 设置主实体
	 * 
	 * @param parent
	 *            主实体
	 */
	void setParent(MajorBaseVO parent);

	/**
	 * 注册子表实体类型
	 */
	void registerChildrenClass();

	/**
	 * 得到父类的类型
	 * 
	 * @return
	 */

	@JSONField(serialize=false)
	Class<? extends IMajorVO> getParentClass();

	/**
	 * 设置子表VO对象数组。
	 * 
	 * @param children
	 *            子表VO对象数组
	 */
	void setChildrenVO(IBaseVO[] vos);



}

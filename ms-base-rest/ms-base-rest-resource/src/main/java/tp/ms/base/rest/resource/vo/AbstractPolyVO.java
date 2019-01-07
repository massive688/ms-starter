package tp.ms.base.rest.resource.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.ExceptionUtils;

import com.alibaba.fastjson.annotation.JSONField;

import tp.ms.common.bean.utils.ObjectUtilms;
import tp.ms.common.bean.vo.CircularlyBaseVO;
import tp.ms.common.bean.vo.IBaseVO;
import tp.ms.common.bean.vo.IChildVO;

public abstract class AbstractPolyVO extends PolyValueObject implements Cloneable, IPolyVO {

	public AbstractPolyVO() {
		registerChildrenClass();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1440745556510678802L;
	/**
	 * 多表体VO的表体
	 */
	@JSONField(serialize=false)
	IChildVO[][] children;
	/**
	 * 多表体VO的表体
	 */
	IChildVO[] allChildren;

	Map<String, IBaseVO[]> childes = new HashMap<String, IBaseVO[]>();

	Map<String, Class<? extends IChildVO>> childClass = new HashMap<String, Class<? extends IChildVO>>();
	/**
	 * 主表
	 */
	private MajorBaseVO parent;

	@Override
	public Object clone() {
		AbstractPolyVO bill = null;
		try {
			bill = (AbstractPolyVO) super.clone();
		} catch (CloneNotSupportedException ex) {
			ExceptionUtils.handleThrowable(ex);
			return null;
		}
		if (this.parent != null) {
			MajorBaseVO parentVO = (MajorBaseVO) this.parent.clone();
			bill.setParent(parentVO);
		}
		bill.children = this.clone(this.children);
		return bill;
	}

	private IChildVO[][] clone(IChildVO[][] vos) {
		int size = vos.length;
		IChildVO[][] newChildren = new IChildVO[size][];

		for (int i = 0; i < size; i++) {
			IChildVO[] oldBodyVOs = vos[i];
			IChildVO[] newBodyVOs = this.clone(oldBodyVOs);
			if (newBodyVOs == null) {
				continue;
			}
			newChildren[i] = newBodyVOs;
		}
		return newChildren;

	}

	private IChildVO[] clone(IChildVO[] vos) {
		if ((vos == null) || (vos.length == 0)) {
			// 保持和原始状态一样，为NULL或者空对象
			return vos;
		}
		int length = vos.length;
		Class<? extends IChildVO> voClass = vos[0].getClass();
		IChildVO[] newVOs = ObjectUtilms.constructArray(voClass, length);
		for (int i = 0; i < length; i++) {
			newVOs[i] = (IChildVO) vos[i].clone();
		}
		return newVOs;

	}

	
	@SuppressWarnings("unchecked")
	@Override
	public <Child extends IChildVO> Child[] getChildren(Class<Child> clazz) {
		int index = getChildrenClassIndex(clazz);
		return (Child[]) this.children[index];
	}

	@Override
	public MajorBaseVO getParent() {
		return this.parent;
	}

	@JSONField(serialize=false)
	@Override
	public String getPrimaryKey() {
		return this.parent.getPrimaryKey();
	}

	@Override
	public void setChildren(Class<? extends IChildVO> clazz, IChildVO[] children) {
		int index = getChildrenClassIndex(clazz);
		this.children[index] = children;
		childes.put(clazz.getName(), children);
	}

	@Override
	public void setParent(MajorBaseVO parent) {
		this.parent = parent;
	}

	@JSONField(serialize=false)
	@Override
	public IChildVO[] getChildrenVO() {
		ArrayList<IChildVO> allChildren = new ArrayList<>();
		for(IBaseVO[] bvos : childes.values()) {
			for(IBaseVO bvo : bvos) {
				if(bvo instanceof IChildVO)
					allChildren.add((IChildVO) bvo);
			}
		}
		
		return (IChildVO[]) allChildren.toArray(new IChildVO[0]);
	}

	@JSONField(serialize=false)
	@Override
	public void setChildrenVO(IBaseVO[] vos) {
		//初始化所有子对象
		if(children != null) {
			IChildVO[] items = (IChildVO[]) vos;
			HashMap<Class<? extends IChildVO>, ArrayList<IChildVO>> clzs = new HashMap<>();
			for(int i=0; i<items.length; i++) {
				Class<? extends IChildVO> key = items[i].getClass();
				ArrayList<IChildVO> array;
				if(clzs.containsKey(key)) {
					array = clzs.get(key);
				}else {
					array = new ArrayList<IChildVO>();
				}
				array.add(items[i]);
				clzs.put(key, array);
			}
//			initChildrenClass((Class<? extends IBaseVO>[]) clzs.keySet().toArray());
			for(Class<? extends IChildVO> clazz : clzs.keySet()) {
				setChildren(clazz, clzs.get(clazz).toArray(new IChildVO[0]));
			}
		}

	}

	@Override
	public void setParentVO(CircularlyBaseVO vo) {
		this.setParent((MajorBaseVO) vo);
	}

	@JSONField(serialize=false)
	public void initChildrenClass(Class<? extends IChildVO>[] classes) {
		if (classes == null)
			return;
		clzIndex.clear();
		for (int i = 0; i < classes.length; i++) {
			clzIndex.put(classes[i], i);
			childClass.put(classes[i].getName(), classes[i]);
		}
		children = new IChildVO[classes.length][];

	}

	private <Child extends IChildVO> int getChildrenClassIndex(Class<Child> clazz){
		return clzIndex.get(clazz);
	}
	
	public Map<String, IBaseVO[]> getChildes() {
		return childes;
	}

	@SuppressWarnings("unchecked")
	@JSONField(serialize=false)
	@Override
	public List<Class<ChildBaseVO>> getChildrenClass() {
		if(childClass.isEmpty())
			return null;
		List<Class<ChildBaseVO>> clazzs = new ArrayList<Class<ChildBaseVO>>();
		Iterator<Class<? extends IChildVO>> it = childClass.values().iterator();
		while(it.hasNext()) {
			Class<? extends IChildVO> classChild = it.next();
			if(ChildBaseVO.class.isAssignableFrom(classChild)) {
				clazzs.add((Class<ChildBaseVO>) classChild);
			}
		}
		return  clazzs;
	}

	public boolean cntainerClass(String className) {
		return childClass.containsKey(className);
	}
	public Class<? extends IChildVO> getChildClass(String className) {
		return childClass.get(className);
	}
}

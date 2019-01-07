package tp.ms.base.rest.resource.service.strengthen;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import tp.ms.common.bean.utils.ObjectUtilms;
import tp.ms.common.bean.utils.ReflectionUtils;
import tp.ms.common.bean.vo.IBaseVO;

/**
 * VO操作工具类
 * 
 * @since 6.0
 * @version 2009-6-15 上午08:50:44
 * @author 钟鸣
 */
public class VOTool {
	/**
	 * 将两个VO的值合并，只处理可序列化的字段
	 * 
	 * @param vo        比较DB VO1
	 * @param anotherVO 比较Client VO2
	 */
	public void combine(IBaseVO vo, IBaseVO anotherVO) {
		Map<String, Field> set = this.getDifferentField(vo, anotherVO);
		Map<String, Field> anotherSet = abtainFields(anotherVO);

		for (String name : set.keySet()) {
			// 在VO中根本没有设置值
			if (!anotherSet.keySet().contains(name)) {
				continue;
			}
			Object value;
			try {
				value = ReflectionUtils.getFieldValue(anotherVO, name);
				ReflectionUtils.setFieldValue(vo, name, value);
			} catch (IllegalArgumentException e) {
			}
		}
		vo.setStatus(anotherVO.getStatus());
	}

	/**
	 * 得到两个VO值不同的字段，只是比较可序列化的字段
	 * 
	 * @param vo        比较DB VO1
	 * @param anotherVO 比较Client VO2
	 * @return Set<String> 两个VO值不同的字段
	 */
	public Map<String, Field> getDifferentField(IBaseVO vo, IBaseVO anotherVO) {
		if (vo.getClass() != anotherVO.getClass()) {
			return null;
		}
		Map<String, Field> set = abtainFields(vo);
		Map<String, Field> anotherSet = abtainFields(anotherVO); 

		Map<String, Field> changedSet = new HashMap<String, Field>();
		int onlyInOld = 0;
		for (String fieldName : set.keySet()) {
			// 根本就没有当前属性存在。
			if (!anotherSet.containsKey(fieldName)) {
				changedSet.put(fieldName, set.get(fieldName));
				onlyInOld++;
				continue;
			}
			Object value = null, another = null;
			try {
				value = ReflectionUtils.getFieldValue(vo, fieldName);
				another = ReflectionUtils.getFieldValue(anotherVO, fieldName);
			} catch (IllegalArgumentException e) {
			}
			if (!ObjectUtilms.isEqual(value, another)) {
				changedSet.put(fieldName, set.get(fieldName));
			}
		}
		// 第二个set中有key 第一个没有的集合 VO中的字段值被清空了，此时需要将另外一个VO中的字段名放入
		this.getAnotherSetKey(set, anotherSet, changedSet, onlyInOld);
		return changedSet;
	}

	private Map<String, Field> abtainFields(IBaseVO vo) {		
		Map<String, Field> fields = new HashMap<String, Field>();
		Class<?> clazz = vo.getClass();
		fields.putAll(abtainFields(clazz));
		while(clazz.getSuperclass() != Object.class){
			clazz = clazz.getSuperclass();
			fields.putAll(abtainFields(clazz));
		}
		return fields;
	}

	private Map<String, Field> abtainFields(Class<?> clazz) {
		Map<String, Field> fields = new HashMap<String, Field>();
		Field[] fs = clazz.getDeclaredFields();
		for(Field f: fs) {
			fields.put(f.getName(), f);
		}
		return fields;
	}

	/**
	 * 第二个set中有key 第一个没有的集合
	 * 
	 * @param set        第一个set
	 * @param anotherSet 第二个set
	 * @param count      第一个set中有 第二个set中没有的个数和
	 * @return
	 */
	private void getAnotherSetKey(Map<String, Field> set, Map<String, Field> anotherSet, Map<String, Field> changedSet, int count) {
		int newcount = anotherSet.size() - set.size() + count;
		if (newcount == 0) {
			return;
		}
		for (String name : anotherSet.keySet()) {
			if (!set.keySet().contains(name)) {
				changedSet.put(name, anotherSet.get(name));
				newcount--;
				if (newcount == 0) {
					break;
				}
			}
		}
	}
	/**
	 * 得到两个VO值不同的字段，只是比较可序列化的并且是动态属性的字段 用于前后台VO传输压缩流量所用。其他业务代码不推荐使用
	 * 
	 * @param vo        比较VO1
	 * @param anotherVO 比较VO2
	 * @return Set<String>
	 */
//	public Set<String> getDifferentFieldForDynamic(IBaseVO vo, IBaseVO anotherVO) {
//		IVOMetaStatisticInfo info = vo.getMetaData().getStatisticInfo();
//		IAttributeMeta[] attributes = info.getStaticAttributes();
//		Set<String> staticSet = new HashSet<String>();
//		Set<String> changedSet = new HashSet<String>();
//
//		for (IAttributeMeta attribute : attributes) {
//			staticSet.add(attribute.getName());
//			// 静态属性的字段根本无法压缩流量，因此自能默认是修改过的属性
//			changedSet.add(attribute.getName());
//		}
//
//		if (vo == anotherVO) {
//			return changedSet;
//		}
//
//		Set<String> set = vo.usedAttributeNames();
//		Set<String> anotherSet = anotherVO.usedAttributeNames();
//
//		int onlyInOld = 0;
//		for (String name : set) {
//			// 静态属性的字段根本无法压缩流量，因此自能默认是修改过的属性
//			if (staticSet.contains(name)) {
//				continue;
//			}
//			// 根本就没有当前属性存在，例如，当前单据模板上没有当前属性。
//			else if (!anotherSet.contains(name)) {
//				onlyInOld++;
//				continue;
//			} else {
//				Object value = vo.getAttributeValue(name);
//				Object another = anotherVO.getAttributeValue(name);
//				if (!PubAppTool.isEqual(value, another)) {
//					changedSet.add(name);
//				}
//			}
//			// anotherSet.remove(name);
//		}
//		// VO中的字段值被清空了，此时需要将另外一个VO中的字段名放入
//		// 第二个set中有key 第一个没有的集合
//		this.getAnotherSetKey(set, anotherSet, changedSet, onlyInOld);
//
//		return changedSet;
//	}
//
//	/**
//	 * 得到两个VO值不同的字段，只是比较可持续化的字段
//	 * 
//	 * @param vo        比较VO1
//	 * @param anotherVO 比较VO2
//	 * @return Set<String>
//	 */
//	public Set<String> getDifferentFieldForPersistent(IBaseVO vo, IBaseVO anotherVO) {
//		IVOMetaStatisticInfo info = vo.getMetaData().getStatisticInfo();
//		IAttributeMeta[] attributes = info.getPerisistentAttributes();
//		Set<String> persistentSet = new HashSet<String>();
//		for (IAttributeMeta attribute : attributes) {
//			persistentSet.add(attribute.getName());
//		}
//
//		Set<String> changedSet = new HashSet<String>();
//		Set<String> set = vo.usedAttributeNames();
//		Set<String> anotherSet = anotherVO.usedAttributeNames();
//		int onlyInOld = 0;
//		for (String name : set) {
//
//			// 根本就没有当前属性存在。
//			if (!anotherSet.contains(name)) {
//				changedSet.add(name);
//				onlyInOld++;
//				continue;
//			}
//			Object value = vo.getAttributeValue(name);
//			Object another = anotherVO.getAttributeValue(name);
//			if (!PubAppTool.isEqual(value, another)) {
//				changedSet.add(name);
//			}
//			// anotherSet.remove(name);
//		}
//
//		// VO中的字段值被清空了，此时需要将另外一个VO中的字段名放入
//		// 第二个set中有key 第一个没有的集合
//		int newcount = anotherSet.size() - set.size() + onlyInOld;
//		if (newcount != 0) {
//			for (String name : anotherSet) {
//				if (!set.contains(name) && persistentSet.contains(name)) {
//					changedSet.add(name);
//					newcount--;
//					if (newcount == 0) {
//						break;
//					}
//				}
//			}
//
//		}
//		return changedSet;
//	}
}

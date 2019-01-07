package tp.ms.common.bean.vo;

import java.util.HashMap;
import java.util.Map;

public class ObjectClassCacheUtil {

	static Map<Class<?>, Object> cache = new HashMap<>();
	
	ObjectClassCacheUtil() {
		
	}
	
	public static <V> Object registerCache(Class<?> k, V v) {
		if(cache.containsKey(k))
			return v;
		return cache.put(k, v);
	}
	
	public static Object captureCache(Class<?> k) {
		return cache.get(k);
	}

	public static boolean contains(Class<?> k) {
		return cache.containsKey(k);
	}
	
	
}

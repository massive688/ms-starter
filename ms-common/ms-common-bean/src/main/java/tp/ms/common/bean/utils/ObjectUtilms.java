package tp.ms.common.bean.utils;

import java.lang.management.ManagementFactory;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;


public class ObjectUtilms {
	
	public static final String[] STRING_NULL_VALUE = {"null","undefined","unknown"};
	
	private static boolean equalsNull(String string) {
		for (String nullstring : STRING_NULL_VALUE) {
			if(nullstring.equalsIgnoreCase(string)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isNumeric(String str){
		if(str==null){
			return false;
		}
		Boolean strResult = str.matches("-?[0-9]+.?[0-9]*(E|e)?[0-9]*");
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+.?[\\d]*(E|e)?[\\d]*$");  
		strResult = pattern.matcher(str).matches();  
	    return strResult;
	}

	public static <T> boolean isEmpty(T obj) {
		if (obj == null)
			return true;
		if (obj instanceof String)
			return isEmptyWithTrim((String) obj);
		if (ObjectUtils.isArray(obj))
			return isEmpty((Object[]) obj);
		if (obj instanceof Collection)
			return isEmptyCollection((Collection<?>) obj);
		if (obj instanceof Map)
			return isEmptyMap((Map<?, ?>) obj);
		return false;
	}

	public static boolean isNotEmpty(Object obj) {
		if (obj == null)
			return false;
		if (obj instanceof String)
			return isNotEmptyWithTrim((String) obj);
		if (ObjectUtils.isArray(obj))
			return isNotEmpty((Object[]) obj);
		if (obj instanceof Map)
			return isNotEmptyMap((Map<?, ?>) obj);
		if (obj instanceof Collection)
			return isNotEmptyCollection((Collection<?>) obj);
		return true;
	}

	public static <T> boolean isEmptyCollection(Collection<T> obj) {
		return CollectionUtils.isEmpty(obj);
	}

	public static boolean isEmptyMap(Map<?, ?> obj) {
		return obj == null || obj.isEmpty();
	}

	public static boolean isEmptyWithTrim(String str) {
		return str == null || str.trim().length() == 0 || equalsNull(str);
	}

	public static boolean isEmpty(Object[] array) {
		return ArrayUtils.isEmpty(array);
	}

	public static boolean isNotEmpty(Object[] array) {
		return !ArrayUtils.isEmpty(array);
	}

	public static boolean isNotEmptyCollection(Collection<?> obj) {
		return !isEmptyCollection(obj);
	}

	public static boolean isNotEmptyMap(Map<?, ?> obj) {
		return !isEmptyMap(obj);
	}

	public static boolean isNotEmptyWithTrim(String str) {
		return !isEmptyWithTrim(str);
	}

	public static boolean isTrue(String str) {
		return new Boolean(str).booleanValue();
	}

	public static boolean isFalse(String str) {
		return !isTrue(str);
	}
	
	public static boolean isDebug() {
		List<String> arguments = ManagementFactory.getRuntimeMXBean()
				.getInputArguments();
		boolean debuging = false;
		for (String str : arguments) {
			if (str.startsWith("-agentlib")) {
				debuging = true;
				break;
			}
		}
		// System.out.println(debuging? "debug mode" : "normal mode");
		return debuging;
	}

	@SuppressWarnings("unchecked")
	public static<T> T[] constructArray(Class<T> voClass, int size) {
	    T[] instances = (T[]) Array.newInstance(voClass, size);
	    for (int i = 0; i < size; i++) {
	      instances[i] = constructObject(voClass);
	    }
		return instances;
	}

	private static <T> T constructObject(Class<T> voClass) {
		try {
			return voClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
		      ExceptionUtils.handleThrowable(e);
		}
		return null;
	}

	public static boolean isEqual(Object value, Object another) {
		boolean flag = false;
	    if (value == null) {
	      flag = another == null;
	    }
	    else if (another == null) {
	      flag = false;
	    }
	    else {
	      flag = value.equals(another);
	    }
	    return flag;
	}

}

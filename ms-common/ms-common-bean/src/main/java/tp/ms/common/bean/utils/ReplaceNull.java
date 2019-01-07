package tp.ms.common.bean.utils;

import java.util.regex.Pattern;



public class ReplaceNull {
	public static String string(Object value) {
		return ObjectUtilms.isEmpty(value)? "" : String.valueOf(value).trim();
	}
	public static Double Double(Object value) {
		if(ObjectUtilms.isEmpty(value))
			return Double.valueOf(0);
		if(value instanceof Double){
			return (Double)value;
		}
		String doubleString = String.valueOf(value);
		return isNumeric(doubleString) ? new Double(doubleString) : Double.valueOf(0) ;
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
	
	public static Boolean Boolean(Object value) {
		if(ObjectUtilms.isEmpty(value))
			return Boolean.FALSE;
		if(value instanceof Boolean){
			return (Boolean)value;
		}
		return value.toString().equalsIgnoreCase("Y") ? Boolean.TRUE : Boolean.FALSE;
	}
	
	public static Integer integer(Object value) {
		if(ObjectUtilms.isEmpty(value))
			return 0;
		if(value instanceof Integer){
			return (Integer)value;
		}
		return isNumeric(value.toString()) ? Integer.parseInt(value.toString()) : 0 ;
	}
}

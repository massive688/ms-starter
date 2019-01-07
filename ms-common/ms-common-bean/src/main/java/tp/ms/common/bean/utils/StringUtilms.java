package tp.ms.common.bean.utils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;

public class StringUtilms {
	private static String sysCharset = System.getProperty("file.encoding");

	public static void main(String[] args) {
//		String en = encoderBASE64("asddgh你好");
//		System.out.println(en);
//		System.out.println(decoderBASE64(en));
		
		System.out.println(translateUpperString("Uill_TUeyp_temKplate", '_'));
	}

	public static String decoderBASE64(String s) {
		if (s == null)
			return null;
		try {
			// BASE64Decoder
			/*
			 * import sun.misc.BASE64Decoder; BASE64Decoder decoder = new
			 * BASE64Decoder();decoder.decodeBuffer(s);
			 */
			byte[] b = Base64.decodeBase64(s);
			return new String(b, sysCharset);
		} catch (Exception e) {
			return null;
		}
	}

	public static String encoderBASE64(String s) {
		if (s == null)
			return null;
		try {
			return Base64.encodeBase64String(s.getBytes(sysCharset));
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean isEmpty(String s) {
		return ObjectUtilms.isEmptyWithTrim(s);
	}

	/**
	 * 是否是数字
	 * @param value 需要判断的值
	 * @return 是否bool值
	 */
	@SuppressWarnings("static-access")
	public static boolean isNum(String value) {
		Pattern p = null;// 正则表达式
		Matcher m = null;// 操作符表达式
		boolean b = false;
		p = p.compile("^([+-]?)\\d*\\.?\\d+$");
		m = p.matcher(value);
		b = m.matches();
		return b;
	}

	/**
	 * 截取字符串
	 * 
	 * @param regex 判断的正则表达式
	 * @param resource 字符串资源
	 * @return 返回处理后的结果
	 */
	public static String substr(String regex, String resource) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(resource);
		if (matcher.find()) {
			return matcher.group(1);
		}
		return null;
	}

	public static String translateUpperString(String src, char tag) {
		if(src == null)
			return src;
		char[] originChar = src.toCharArray();
		char[] newChar = new char[originChar.length];
		int index = 0;
		for (int i = 0; i + index < originChar.length && i < newChar.length; i++) {
			char ar = originChar[i + index];
			if (i == 0 && ar > 96) {
				ar = (char) (ar - 32);
			} else {
				if (ar == tag) {
					newChar = Arrays.copyOf(newChar, newChar.length - 1);
					index++;
					ar = originChar[i + index];
					if(ar > 96)
						ar = (char) (ar - 32);
				}else if(i > 0 && ar < 91){
					ar = (char) (ar + 32);
				}
			}
			newChar[i] = ar;
		}
		src = new String(newChar);
		return src;
	}

	public static String translateLowerString(String src, Character tag) {
		if(src == null)
			return src;
		char[] originChar = src.toCharArray();
		char[] newChar = new char[originChar.length];
		int index = 0;
		for (int i = 0; i < originChar.length; i++) {
			char ar = originChar[i];
			if (ar < 91) {
				ar = (char) (ar + 32);
				index++;
				if (i == 0) {
					index--;
				} else {
					newChar = Arrays.copyOf(newChar, newChar.length + 1);
					newChar[i + index - 1] = tag;
				}
			}
			newChar[i + index] = ar;
		}
		src = new String(newChar);
		return src;
	}
}

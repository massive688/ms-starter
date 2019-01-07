package tp.ms.common.bean.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.function.Supplier;

public class ADate {

	public static void main(String[] args) {
		System.out.println((new ADate(System.currentTimeMillis())).toString());
		
		new Thread().start();
	}

	public ADate(Supplier<String> f) {
		
	}
	private long utcTime;

	/**
	 * 用yyyy-MM-dd形式的字符串构造日期类型，所使用的时区为本地时间所处时区
	 * 
	 * @param date
	 */
	public ADate(){
		this(System.currentTimeMillis());
	}
	public ADate(long m) {
		this.utcTime = m;
		utcTime = utcTime - utcTime % 1000;
	}
	public ADate(String date) {
		int[] v = internalParse(date);
		utcTime = new GregorianCalendar(v[0], v[1] - 1, v[2]).getTimeInMillis();
	}
	
	/**
	 * 根据本地时区，以yyyy-MM-dd的标准格式转化字符串
	 * 
	 * @return
	 */
	private String toLocalString() {
		GregorianCalendar localCalendar = localCalendar();
		return toDateString(localCalendar.get(Calendar.YEAR), localCalendar.get(Calendar.MONTH) + 1, localCalendar.get(Calendar.DATE));
	}
	public String toStdString(){
		return toLocalString();
	}
	/**
	 * 根据基准时区，当标准格式yyyy-MM-dd HH:mm:ss返回字符串
	 * 
	 * @return
	 */
	public static final TimeZone BASE_TIMEZONE = TimeZone.getTimeZone("GMT+08:00");
	private String toPersisted() {
		GregorianCalendar cal = new GregorianCalendar(BASE_TIMEZONE);
		cal.setTimeInMillis(utcTime);
		return toDateTimeString(cal.get(Calendar.YEAR), cal
				.get(Calendar.MONTH) + 1, cal.get(Calendar.DATE), cal
				.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal
				.get(Calendar.SECOND));
	}
	private String toDateTimeString(int year, int month, int day, int hour, int minute, int second){
		StringBuffer sb = new StringBuffer();
		String strYear = String.valueOf(year);
		for (int j = strYear.length(); j < 4; j++)
			sb.append('0');
		sb.append(strYear).append('-');

		append(sb, month, '-');
		append(sb, day, ' ');
		append(sb, hour, ':');
		append(sb, minute, ':');
		if (second < 10) {
			sb.append('0');
		}
		sb.append(second);
		return sb.toString();
	}
	private static void append(StringBuffer sb, int v, char split) {
		if (v < 10) {
			sb.append('0');
		}
		sb.append(v).append(split);
	}
	
	
	
	
	public String toString(){
		return toPersisted();
	}
	private static String toDateString(int year, int month, int day) {
		String strYear = String.valueOf(year);
		for (int j = strYear.length(); j < 4; j++)
			strYear = "0" + strYear;
		String strMonth = String.valueOf(month);
		if (strMonth.length() < 2)
			strMonth = "0" + strMonth;
		String strDay = String.valueOf(day);
		if (strDay.length() < 2)
			strDay = "0" + strDay;
		return strYear + "-" + strMonth + "-" + strDay;
	}

	public long getMillis() {
		return utcTime;
	}

	static int[] internalParse(String str) {
		if (str == null)
			throw new IllegalArgumentException("invalid date: " + str);

		str = str.trim();
		int spaceIndex = str.indexOf(' ');
		if (spaceIndex > -1) {
			str = str.substring(0, spaceIndex);
		}

		String[] tokens = new String[3];
		StringTokenizer st = new StringTokenizer(str, "-/");
		if (st.countTokens() != 3) {
			throw new IllegalArgumentException("invalid date: " + str);
		}

		int i = 0;
		while (st.hasMoreTokens()) {
			tokens[i++] = st.nextToken();
		}

		try {
			int year = Integer.parseInt(tokens[0]);
			int month = Integer.parseInt(tokens[1]);
			if (month < 1 || month > 12)
				throw new IllegalArgumentException("invalid date: " + str);
			int day = Integer.parseInt(tokens[2]);

			int daymax = isLeapYear(year) ? LEAP_MONTH_LENGTH[month - 1] : MONTH_LENGTH[month - 1];

			if (day < 1 || day > daymax)
				throw new IllegalArgumentException("invalid date: " + str);
			return new int[] { year, month, day };
		} catch (Throwable thr) {
			if (thr instanceof IllegalArgumentException) {
				throw (IllegalArgumentException) thr;
			} else {
				throw new IllegalArgumentException("invalid date: " + str);
			}
		}

	}
	
	public static int MONTH_LENGTH[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	public static int LEAP_MONTH_LENGTH[] = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	/**
	 * 是否闰年。
	 * 
	 * @return boolean
	 * @param year
	 *            int
	 */
	public static boolean isLeapYear(int year) {
		if ((year % 4 == 0) && (year % 100 != 0 || year % 400 == 0))
			return true;
		else
			return false;
	}
	
	private GregorianCalendar localCalendar() {
		GregorianCalendar localCalendar = new GregorianCalendar(CalendarDe.getGMTDefault());
		localCalendar.setTimeInMillis(this.utcTime);
		return localCalendar;
	}
	
	/**
	 * 返回MM格式月字符串
	 * 
	 * @return
	 */
	public String getStrMonth() {
		return toString().substring(5, 7);
	}
	/**
	 * 返回dd格式日期字符串
	 * 
	 * @return
	 */
	public String getStrDay() {
		return toString().substring(8, 10);
	}
	
	/**
	 * 获得年的数值
	 * 
	 * @return int
	 */
	public int getYear() {
		return basezoneCalendar().get(Calendar.YEAR);
	}

	private GregorianCalendar basezoneCalendar() {
		GregorianCalendar basezoneCalendar = new GregorianCalendar(
				BASE_TIMEZONE);
		basezoneCalendar.setTimeInMillis(this.utcTime);
		return basezoneCalendar;
	}
	
	static class CalendarDe {	
		
		public static final int MILLIS_PER_HOUR = 60 * 60 * 1000;
		
		public static final int MILLIS_PER_MINUTE = 60 * 1000;
		
		private static TimeZone defZone;
		
		public static void main(String[] args) {
			System.out.println(getGMTDefault());
		}
		
		/**
		 * 默认时区对应的标准GMT时区
		 * 
		 * @return
		 */
		public static TimeZone getGMTDefault() {
			if (defZone == null) {
				TimeZone zone = getGMTTimeZone(TimeZone.getDefault());
				TimeZone.setDefault(zone);
				return zone;
			}
			return defZone;
		}
		
		/**
		 * 输入时区对应的标准GMT时区
		 * 
		 * @param zone
		 *            输入时区
		 * @return
		 */
		public static TimeZone getGMTTimeZone(TimeZone zone) {
			if (zone == null) {
				return getGMTDefault();
			}
			if (zone.getID().startsWith("GMT") || zone.getID().startsWith("UTC")) {
				return zone;
			} else {
				long rawOffset = zone.getRawOffset();
				long absrawOffset = rawOffset > 0 ? rawOffset : -rawOffset;
				int hourOffset = (int) absrawOffset / MILLIS_PER_HOUR;
				int minueOffset = (int) ((absrawOffset % MILLIS_PER_HOUR) / MILLIS_PER_MINUTE);
				return toGMTZone(rawOffset >= 0, hourOffset, minueOffset);
			}
		}
		
		private static TimeZone toGMTZone(boolean positive, int hourOffset, int minueOffset) {
			StringBuffer sb = new StringBuffer("GMT");
			sb.append(positive ? '+' : '-');
			sb = hourOffset > 9 ? sb.append(hourOffset) : sb.append('0').append(
					hourOffset);
			sb.append(':');
			sb = minueOffset > 9 ? sb.append(minueOffset) : sb.append('0').append(
					minueOffset);
			return TimeZone.getTimeZone(sb.toString());
		}
	}

	public static Object now() {
		return new ADate();
	}
}

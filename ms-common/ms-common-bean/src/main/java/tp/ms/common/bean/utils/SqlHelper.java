package tp.ms.common.bean.utils;

public class SqlHelper {
	
	private StringBuffer where = new StringBuffer(" dr=0 ");

	public static SqlHelper build() {
		return new SqlHelper();
	}

	public SqlHelper appendConditional(String field, String value) {
		 where.append("and ").append(field).append("=").append("'").append(value).append("' ");
		 return this;
	}

	@Override
	public String toString() {
		return where.toString();
	}
}

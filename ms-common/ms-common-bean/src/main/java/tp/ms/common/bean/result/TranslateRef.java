package tp.ms.common.bean.result;

public interface TranslateRef extends DataSourceChoose {
	
	String getCodeField();
	
	String getNameField();
	
	String[] showFields();
	
	String getTable();
	
	String getPkField();
	
	default String getWherePart() {return " dr=0 ";};

}


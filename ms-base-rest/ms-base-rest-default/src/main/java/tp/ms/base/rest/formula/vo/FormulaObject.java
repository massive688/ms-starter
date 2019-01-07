package tp.ms.base.rest.formula.vo;

import lombok.Getter;

@Getter
public class FormulaObject {
	
	String fieldCode;

	String table;

	String where;

	public FormulaObject setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
		return this;
	}
	
	public FormulaObject setTable(String table) {
		this.table = table;
		return this;
	}
	
	public FormulaObject setWhere(String where) {
		this.where = where;
		return this;
	}
	
	public static FormulaObject build() {
		return new FormulaObject();
	}
}

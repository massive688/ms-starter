package tp.ms.base.rest.formula.api;

import tp.ms.base.rest.formula.httparam.FormulaExecValue;
import tp.ms.base.rest.formula.httparam.TranslateValue;

public interface FormulaValueService {

	String execute(TranslateValue data);

	String execute(FormulaExecValue data);
	

}

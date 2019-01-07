package tp.ms.base.rest.formula.httparam;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import tp.ms.common.bean.result.DataSourceChoose;

@Data
public class FormulaExecValue implements DataSourceChoose {

	@NotBlank
	String value;

	@NotBlank
	String translate;

	@NotNull
	String formula;

	String sessionKey;
}

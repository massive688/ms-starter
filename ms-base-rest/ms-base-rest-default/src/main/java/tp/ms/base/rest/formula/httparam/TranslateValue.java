package tp.ms.base.rest.formula.httparam;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import tp.ms.common.bean.result.DataSourceChoose;

@Data
public class TranslateValue implements DataSourceChoose {

	@NotBlank
	String value;

	@NotBlank
	String translate;

	@NotNull
	Integer show;

	String sessionKey;
}

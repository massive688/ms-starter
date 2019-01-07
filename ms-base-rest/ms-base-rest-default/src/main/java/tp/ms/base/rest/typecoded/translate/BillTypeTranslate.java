package tp.ms.base.rest.typecoded.translate;

import org.springframework.stereotype.Component;

import tp.ms.common.bean.result.TranslateRef;
import tp.ms.common.data.mybatis.annotation.SqlSessionKey;

@Component
public class BillTypeTranslate implements TranslateRef{

	@Override
	public String getCodeField() {
		return "component";
	}

	@Override
	public String getNameField() {
		return "name";
	}

	@Override
	public String[] showFields() {
		return new String[]{"component","name"};
	}

	@Override
	public String getTable() {
		return "ms_base_billtype";
	}

	@Override
	public String getPkField() {
		return "pk_billtype";
	}

	@Override
	public String getSessionKey() {
		return SqlSessionKey.CS6304;
	}

}

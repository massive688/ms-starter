package tp.ms.base.rest.ots.orgs.translate;

import org.springframework.stereotype.Component;

import tp.ms.common.bean.result.TranslateRef;
import tp.ms.common.data.mybatis.annotation.SqlSessionKey;

@Component
public class OrgsElementTranslate implements TranslateRef{

	@Override
	public String getCodeField() {
		return "code";
	}

	@Override
	public String getNameField() {
		return "name";
	}

	@Override
	public String[] showFields() {
		return new String[]{"code","name"};
	}

	@Override
	public String getTable() {
		return "ms_base_orgelements";
	}

	@Override
	public String getPkField() {
		return "pk_orgelements";
	}

	@Override
	public String getSessionKey() {
		return SqlSessionKey.CS6304;
	}

}

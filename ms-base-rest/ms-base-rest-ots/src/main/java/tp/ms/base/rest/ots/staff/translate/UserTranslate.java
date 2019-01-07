package tp.ms.base.rest.ots.staff.translate;

import org.springframework.stereotype.Component;

import tp.ms.common.bean.result.TranslateRef;
import tp.ms.common.data.mybatis.annotation.SqlSessionKey;

@Component
public class UserTranslate implements TranslateRef{

	@Override
	public String getCodeField() {
		return "user_code";
	}

	@Override
	public String getNameField() {
		return "user_name";
	}

	@Override
	public String[] showFields() {
		return new String[]{"user_code","user_name"};
	}

	@Override
	public String getTable() {
		return "ms_base_staff";
	}

	@Override
	public String getPkField() {
		return "pk_user";
	}

	@Override
	public String getSessionKey() {
		return SqlSessionKey.CS6304;
	}

}

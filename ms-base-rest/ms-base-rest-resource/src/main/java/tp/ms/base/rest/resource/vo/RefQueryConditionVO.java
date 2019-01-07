package tp.ms.base.rest.resource.vo;

import lombok.Data;
import tp.ms.common.bean.http.Symbol;

@Data
public class RefQueryConditionVO {
	String variable;
	Symbol symbol;
	Object value;
}

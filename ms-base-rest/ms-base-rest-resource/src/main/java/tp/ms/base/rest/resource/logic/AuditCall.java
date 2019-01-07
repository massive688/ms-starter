package tp.ms.base.rest.resource.logic;

import java.util.HashMap;
import java.util.Map;

public interface AuditCall<T> {

	T call(T object);

	default Map<String, Object> getDataMap(HashMap<String, Object> map){
		return map;
	};
	
}

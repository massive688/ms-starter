package tp.ms.common.bean.exception;

public class AssertUtils {

	public static void notNull(Object o, String message) {
		if(o == null) {
			throw new CarrierRuntimeException(message);
		}
	}

}

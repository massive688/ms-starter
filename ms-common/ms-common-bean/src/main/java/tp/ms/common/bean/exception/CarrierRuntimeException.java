package tp.ms.common.bean.exception;

public class CarrierRuntimeException extends RuntimeException {

	public CarrierRuntimeException(String message) {
		super(message);
	}

	public CarrierRuntimeException(Exception ex) {
		super(ex);
	}

	public CarrierRuntimeException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

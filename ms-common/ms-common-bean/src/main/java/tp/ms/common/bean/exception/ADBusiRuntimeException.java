package tp.ms.common.bean.exception;

public class ADBusiRuntimeException extends RuntimeException {

	public ADBusiRuntimeException(String message) {
		super(message);
	}

	public ADBusiRuntimeException(Exception ex) {
		super(ex);
	}

	public ADBusiRuntimeException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

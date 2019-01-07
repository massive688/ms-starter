package tp.ms.common.bean.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AExceptionUtils {

	public static void unSupported() {
		unSupported("不支持此种业务，请检查");
	}

	public static void unSupported(String message) {
	    throw new CarrierRuntimeException(message);
	}


	  /**
	   * 在EJB边界对所有的异常进行ADBusinessException包装
	   * 
	   * @param ex 要处理的异常
	   * @throws ADBusinessException 要抛出EJB的异常
	   */
	  public static void marsh(Exception ex) throws ADBusinessException {
	    Throwable cause = AExceptionUtils.unmarsh(ex);
	    log.error(cause.getMessage(), cause);

	    if (cause instanceof ADBusinessException) {
	      throw (ADBusinessException) cause;
	    }
	    else {
	      TransferException tex = new TransferException(cause);
	      throw tex;
	    }
	  }


	  /**
	   * 将最底层的异常解析出来
	   * 
	   * @param ex 要处理的异养
	   * @return 最底层的异常
	   */
	  public static Throwable unmarsh(Throwable ex) {
	    Throwable cause = ex.getCause();
	    if (cause != null) {
	      cause = AExceptionUtils.unmarsh(cause);
	    }
	    else {
	      cause = ex;
	    }
	    return cause;
	  }


	  /**
	   * 抛出业务异常
	   * 
	   * @param message 异常信息
	   */
	  public static void wrappADBusinessException(String message) {
	    log.error(message);

	    throw new CarrierRuntimeException(message);
	  }



	  /**
	   * 将异常装载到快速异常通道中向上传递
	   * 
	   * @param ex 要装载的异常
	   */
	  public static void wrappException(Exception ex) {
	    log.error(ex.getMessage(), ex.getCause());

	    throw new CarrierRuntimeException(ex);
	  }


}

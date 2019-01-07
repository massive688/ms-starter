package tp.ms.common.bean.support.context;

import tp.ms.common.bean.exception.ADBusinessException;

public interface BeanHelperEnv {

	String generateid();

	String generateid(String pkorgCode);

	String generateBatchCodes(String pkBilltype) throws ADBusinessException;

}

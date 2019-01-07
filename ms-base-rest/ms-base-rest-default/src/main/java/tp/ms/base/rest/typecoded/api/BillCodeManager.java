package tp.ms.base.rest.typecoded.api;

import tp.ms.base.rest.typecoded.bill.BillCode;
import tp.ms.common.bean.exception.ADBusinessException;

public interface BillCodeManager {
	
	BillCode newBillCode(String billtype, String pkCorp) throws ADBusinessException;
}

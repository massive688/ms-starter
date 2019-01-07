package tp.ms.base.rest.typecoded.api;

import tp.ms.base.rest.resource.service.IChildService;
import tp.ms.base.rest.typecoded.vo.MsBaseBillCodeRule;
import tp.ms.base.rest.typecoded.vo.MsBaseBillCodeRuleExample;
import tp.ms.common.bean.exception.ADBusinessException;

public interface BillCodeService extends IChildService<MsBaseBillCodeRule, MsBaseBillCodeRuleExample>{

	String[] generateBatchCodes(String billtype, String string, int num) throws ADBusinessException;

}

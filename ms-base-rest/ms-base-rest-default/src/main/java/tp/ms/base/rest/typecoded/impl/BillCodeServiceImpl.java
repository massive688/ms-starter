package tp.ms.base.rest.typecoded.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tp.ms.base.rest.resource.http.Pager;
import tp.ms.base.rest.resource.service.impl.ChildServiceImpl;
import tp.ms.base.rest.resource.vo.MajorBaseVO;
import tp.ms.base.rest.typecoded.api.BillCodeManager;
import tp.ms.base.rest.typecoded.api.BillCodeService;
import tp.ms.base.rest.typecoded.bill.BillCode;
import tp.ms.base.rest.typecoded.mapper.MsBaseBillCodeRuleMapper;
import tp.ms.base.rest.typecoded.vo.MsBaseBillCodeRule;
import tp.ms.base.rest.typecoded.vo.MsBaseBillCodeRuleExample;
import tp.ms.base.rest.typecoded.vo.MyBillcodereturn;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.support.context.BeanHelperEnv;
import tp.ms.common.bean.support.context.MsEnvContextHolder;
import tp.ms.common.bean.vo.BaseExample;
import tp.ms.common.bean.vo.BaseVO;
import tp.ms.common.data.mybatis.annotation.SqlSessionKey;
import tp.ms.common.data.mybatis.annotation.TargetSqlSession;
import tp.ms.common.data.mybatis.mapper.DaoMapper;

@Slf4j
@Service
@TargetSqlSession(SqlSessionKey.CS6304)
public class BillCodeServiceImpl extends ChildServiceImpl<MsBaseBillCodeRule, MsBaseBillCodeRuleExample> implements BillCodeService {//Generator

	@Autowired
	BillCodeManager billCodeManager;
	@Autowired
	BeanHelperEnv beanHelperEnv;
	
	
	private static Map<String, BillCode> billcodeMap = new HashMap<String, BillCode>();

	@Override
	public String[] generateBatchCodes(String billtype, String pkCorp, int num) throws ADBusinessException {
		String billcodekey = byBillcodeKey(pkCorp, billtype);
		BillCode billcodeobj = billcodeMap.get(billcodekey);
		if (billcodeobj == null) {
			billcodeobj = billCodeManager.newBillCode(billtype, pkCorp);
			billcodeMap.put(billcodekey, billcodeobj);
		}
		synchronized (billcodeobj) {
			return getBatchNewBillCodes(billcodeobj, num);
		}
	}

	private String byBillcodeKey(String pkCorp, String billtype) {
		return pkCorp + billtype;
	}

	private String[] getBatchNewBillCodes(BillCode billcodeobj, int num) throws ADBusinessException {
		List<String> codes = null;
		if (billcodeobj.getRtrdCodelist().size() > 0 && billcodeobj.getRuleVO() != null
				&& billcodeobj.getRuleVO().isAutofill()) {

			boolean breset = billcodeobj.isNeedResetToZero();
			// 归零标识
			if (breset) {
				billcodeobj.resetRelatedOperation();
			}
			if (!breset) {
				codes = billcodeobj.getRrtBillcodelist(num);
			}
		}
		if (codes == null) {
			codes = new ArrayList<String>();
		}
		if (codes.size() < num)
			codes = billcodeobj.getBillCode(num - codes.size());
		return codes.toArray(new String[0]);
	}

	public void returnBillcodeToMemory(MajorBaseVO vo) {
		String sn = vo.getCode();
		String billcodeKey = byBillcodeKey(vo.getPkCorp(), vo.getBilltype());
		BillCode billcode = billcodeMap.get(billcodeKey);
		if (billcode != null) {
			MyBillcodereturn myBillcodereturn = new MyBillcodereturn();
			myBillcodereturn.setPkBillcoderulebase(billcode.getRuleVO().getPkBaseBillCodeRule());
			myBillcodereturn.setPkBillcodereturn(beanHelperEnv.generateid(vo.getPkCorp()));
			myBillcodereturn.setRtsns(sn);
			myBillcodereturn.setMarkstr(billcodeKey);
			billcode.getRtrdCodelist().add(myBillcodereturn);
		}
		log.info("billcode {}", billcode);
	}
	

	@Autowired
	MsBaseBillCodeRuleMapper MsBaseBillCodeRuleMapper;
	
	@Override
	public DaoMapper<MsBaseBillCodeRule, MsBaseBillCodeRuleExample> getDao() {
		return MsBaseBillCodeRuleMapper;
	}

	@Override
	public BaseExample transformToExampleFromPagination(Pager page) throws ADBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Class<BaseVO>, BaseExample> transformToExampleFromByPolyArray(MsBaseBillCodeRule[] vos)
			throws ADBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MsBaseBillCodeRule insert(MsBaseBillCodeRule vos) throws ADBusinessException {
		vos.setPkCorp(MsEnvContextHolder.getContext().user().getPkOrg());
		return super.insert(vos);
	}

	@Override
	public void setExampleParentKeyCondition(MsBaseBillCodeRuleExample example, String parentKey) {
		example.createCriteria().andDrEqualTo((short)0)
		.andBilltypeEqualTo(parentKey);
	}

	@Override
	public void setQueryExample(MsBaseBillCodeRuleExample example, String key) {
		example.createCriteria().andDrEqualTo((short) 0)
		.andPkBaseBillCodeRuleEqualTo(key);
	}

}
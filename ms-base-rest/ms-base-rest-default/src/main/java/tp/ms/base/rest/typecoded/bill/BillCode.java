package tp.ms.base.rest.typecoded.bill;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Pattern;

import javax.naming.NamingException;
import javax.validation.ValidationException;

import org.omg.CORBA.SystemException;

import tp.ms.base.rest.typecoded.mapper.MsBaseBillCodeRuleMapper;
import tp.ms.base.rest.typecoded.mapper.MyBillcodereturnMapper;
import tp.ms.base.rest.typecoded.vo.MsBaseBillCodeRule;
import tp.ms.base.rest.typecoded.vo.MsBaseBillCodeRuleExample;
import tp.ms.base.rest.typecoded.vo.MyBillcodereturn;
import tp.ms.base.rest.typecoded.vo.MyBillcodereturnExample;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.utils.ADate;

public class BillCode {

	private MsBaseBillCodeRule ruleVO;
	private String lastsn;
	private List<MyBillcodereturn> rtrdCodelist;
	private static char[] value = "00000000000000000000".toCharArray();

	public BillCode(MsBaseBillCodeRule rulebase, List<MyBillcodereturn> list) {
		this.setRuleBase(rulebase);
		this.rtrdCodelist = list;
	}
	MsBaseBillCodeRuleMapper MsBaseBillCodeRuleMapper;
	MyBillcodereturnMapper myBillcodereturnMapper;
	public BillCode(MsBaseBillCodeRule rulebase, List<MyBillcodereturn> list,
			MsBaseBillCodeRuleMapper MsBaseBillCodeRuleMapper, MyBillcodereturnMapper myBillcodereturnMapper) {
		this.setRuleBase(rulebase);
		this.rtrdCodelist = list;
		this.MsBaseBillCodeRuleMapper = MsBaseBillCodeRuleMapper;
		this.myBillcodereturnMapper = myBillcodereturnMapper;
	}

	public List<String> getBillCode(int ncount) {
		List<String> billcodes = new ArrayList<String>();
		try {
			boolean flag = isNeedResetToZero();
			if (flag) {
				resetRelatedOperation();
			}
			if (!flag && getRuleVO().isAutofill()) {
				billcodes = getRrtBillcodelistFromDB(ncount, billcodes);
			}
			ncount -= billcodes.size();
			if (ncount > 0) {
				getSequenceBillCode(ncount, billcodes);
			}
			// 更新流水号vo和ms_base_bill_code_rule表
			updateRuleVO();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return billcodes;
	}

	private List<String> getRrtBillcodelistFromDB(int ncount, List<String> billcodes) throws ADBusinessException {

		MyBillcodereturnExample example = new MyBillcodereturnExample();
		MyBillcodereturnExample.Criteria criteria = example.createCriteria();
		criteria.andPkBillcoderulebaseEqualTo(getRuleVO().getPkBaseBillCodeRule());
		List<MyBillcodereturn> codeReturns = myBillcodereturnMapper.selectByExample(example);
		List<String> deleteReturns = new ArrayList<String>();
		while (!codeReturns.isEmpty()) {
			billcodes.add(codeReturns.get(0).getRtsns());
			deleteReturns.add(codeReturns.get(0).getPkBillcoderulebase());
		}
		criteria.andPkBillcoderulebaseIn(deleteReturns);
		myBillcodereturnMapper.deleteByExample(example);
		return billcodes;
	}

	public List<String> getRrtBillcodelist(int ncount) throws ADBusinessException {
		List<String> billcodes = new ArrayList<String>();
		MyBillcodereturnExample example = new MyBillcodereturnExample();
		MyBillcodereturnExample.Criteria criteria = example.createCriteria();
		List<String> deletePk = new ArrayList<String>();
		if (ncount >= getRtrdCodelist().size()) {
			while (getRtrdCodelist().size() > 0) {
				MyBillcodereturn rtrdCode = getRtrdCodelist().get(0);
				billcodes.add(rtrdCode.getRtsns());
				getRtrdCodelist().remove(rtrdCode);
			}
		} else {
			for (int i = 0; getRtrdCodelist().size() > 0 && ncount > i; i++) {
				MyBillcodereturn rtrdCode = getRtrdCodelist().get(0);
				billcodes.add(rtrdCode.getRtsns());
				deletePk.add(rtrdCode.getPkBillcodereturn());
				getRtrdCodelist().remove(rtrdCode);
			}
		}
		criteria.andPkBillcodereturnIn(deletePk);
		myBillcodereturnMapper.deleteByExample(example);
		return billcodes;
	}

	/**
	 * 取得根据流水号递增所得的单据号。
	 * 
	 * @return
	 * @throws ValidationException
	 * @throws Exception
	 */
	private void getSequenceBillCode(int ncount, List<String> billcodes) {
		// 检查是否有足够的单据号
		String codenosn = getBillCodeStr();
		for (int i = 0; i < ncount; i++) {
			getNextSNumber();// 取得下一个流水号
			String billcode = codenosn.replaceFirst("##SN##", lastsn);
			billcodes.add(billcode);
		}
	}

	/**
	 * 取得下一个流水号
	 */
	private void getNextSNumber() {
		/*
		 * 以下注释的代码有缺陷， 如果用户中途修改了流水号长度， 未清空流水号， 这里修改后的长度永远不能体现在编码中
		 */
		// String str;
		// str = new Double((Double.parseDouble("9" + lastsn) + 1)).longValue() + "";
		// lastsn = str.substring(1);

		// 2011-12-23因为不知道lastsn是否补位，是否修改了长度，每次使用期前先处理为Long型
		lastsn = (Long.parseLong(lastsn) + 1) + "";
		// 如果流水号补位
		if (ruleVO.getIsrefer() != 0) {
			// 如果补位但取出的流水号不够
			if (lastsn.length() < getRuleVO().getCodelength()) {
				String s = new String(value, 0, getRuleVO().getCodelength() - lastsn.length());
				lastsn = s + lastsn;
			}
		} else {
			// 流水号不补位
			lastsn = Long.parseLong(lastsn) + "";
		}
	}

	/**
	 * 更新规则VO至数据库。 step1:把最大流水更新到流水号表 step2:如果是系统时间把时间更新到elem中
	 * 
	 * @throws ADBusinessException
	 * 
	 * @throws SQLException
	 * @throws SystemException
	 * @throws NamingException
	 */
	private void updateRuleVO() throws ADBusinessException {
		getRuleVO().setLastserialnumber(lastsn);
		MsBaseBillCodeRule rulebasevo = getRuleVO();
		rulebasevo.setLasttimevalue(new ADate().toStdString());
		MsBaseBillCodeRuleExample example = new MsBaseBillCodeRuleExample();
		tp.ms.base.rest.typecoded.vo.MsBaseBillCodeRuleExample.Criteria criteria = example.createCriteria();
		criteria.andPkBaseBillCodeRuleEqualTo(rulebasevo.getPkBaseBillCodeRule());
		MsBaseBillCodeRuleMapper.updateByExample(rulebasevo, example);
	}

	/**
	 * 根据编码规则和单据VO得到单据编码流水号前缀
	 * 
	 * @param rulevo
	 *            编码规则
	 * @param billvo
	 *            单据VO
	 * @return
	 * @throws BusinessException
	 */
	private String getBillCodeStr() {
		String preStr = "";

		if (ruleVO == null)
			return preStr;

		preStr += ruleVO.getCodeprefix();

		preStr += geetTimeReflectStr();

		preStr += "##SN##";

		return preStr;
	}

	private String geetTimeReflectStr() {
		String timestr = "";
		if (ruleVO == null)
			return timestr;
		Calendar cld = new GregorianCalendar();
		cld.setTime(new Date());
		DateFormat df = new SimpleDateFormat(ruleVO.getTimeformat());
		return df.format(cld.getTime());
	}

	public void resetRelatedOperation() throws ADBusinessException {
		lastsn = new String(value, 0, ruleVO.getCodelength());
		// 清空回退表
		ruleVO.setLastserialnumber(lastsn);
		// 初始化流水号
		// updateSNRuleBaseVO(ruleVO, lastsn);
	}

	public boolean isNeedResetToZero() throws ADBusinessException {
		setLastSN();
		if (BillCodeUtilsisSystemTimeSN(ruleVO.getLasttimevalue())) {
			ADate datevalue = new ADate(ruleVO.getLasttimevalue());
			// UFDateTime sysdate =
			// SFServiceFacility.getServiceProviderService().getServerTime();
			ADate sysdate = new ADate(System.currentTimeMillis());
			String currtYear = sysdate.toString().substring(2, 4);
			String currtMonth = sysdate.getStrMonth();
			String currtDay = sysdate.getStrDay();
			String lastYear = datevalue.toString().substring(2, 4);
			String lastMonth = datevalue.getStrMonth();
			String lastDay = datevalue.getStrDay();
			int refer = ruleVO.getIsrefer();
			if ((refer == MsBaseBillCodeRule.REF_DAY
					&& (!currtYear.equals(lastYear) || !currtMonth.equals(lastMonth) || !currtDay.equals(lastDay)))
					|| (refer == MsBaseBillCodeRule.REF_MON
							&& (!currtYear.equals(lastYear) || !currtMonth.equals(lastMonth)))
					|| (refer == MsBaseBillCodeRule.REF_YES && !currtYear.equals(lastYear))) {
				return true;
			}
		}
		return false;
	}

	private boolean BillCodeUtilsisSystemTimeSN(String date) {
		return Pattern.compile("^[0-9]").matcher(date).find();
	}

	private void setLastSN() throws ADBusinessException {
		lastsn = getRuleVO().getLastserialnumber();
		// 如果流水号补位
		if (getRuleVO().getIsrefer() != 0) {
			// 如果补位但取出的流水号不够
			if (lastsn.length() < getRuleVO().getCodelength()) {
				String s = new String(value, 0, ruleVO.getCodelength() - lastsn.length());
				lastsn = s + lastsn;
			}
		} else {
			// 流水号不补位
			lastsn = Long.parseLong(lastsn) + "";
		}
	}

	public MsBaseBillCodeRule getRuleVO() {
		return this.ruleVO;
	}

	public void setRuleBase(MsBaseBillCodeRule rulebase) {
		this.ruleVO = rulebase;
	}

	public List<MyBillcodereturn> getRtrdCodelist() {
		return rtrdCodelist;
	}
}

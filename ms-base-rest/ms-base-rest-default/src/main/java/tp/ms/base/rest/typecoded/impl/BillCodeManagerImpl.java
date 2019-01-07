package tp.ms.base.rest.typecoded.impl;

import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tp.ms.base.rest.typecoded.api.BillCodeManager;
import tp.ms.base.rest.typecoded.bill.BillCode;
import tp.ms.base.rest.typecoded.mapper.MsBaseBillCodeRuleMapper;
import tp.ms.base.rest.typecoded.mapper.MyBillcodereturnMapper;
import tp.ms.base.rest.typecoded.vo.MsBaseBillCodeRule;
import tp.ms.base.rest.typecoded.vo.MsBaseBillCodeRuleExample;
import tp.ms.base.rest.typecoded.vo.MyBillcodereturnExample;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.support.context.BeanHelperEnv;
import tp.ms.common.bean.support.context.SpringContextHolder;
import tp.ms.common.bean.utils.ObjectUtilms;

@Component
public class BillCodeManagerImpl implements BillCodeManager {

	private Logger log = LoggerFactory.getLogger(BillCodeManagerImpl.class);

	private SqlSession session;

	@SuppressWarnings("unused")
	private Object getMsBaseBillCodeRuleMapper(Class<?> clz) throws ADBusinessException {
		Object mapper = null;
		try {
			mapper = SpringContextHolder.getBean(clz);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		if (mapper == null) {
			if (session == null) {
				try {
					Reader reader = Resources.getResourceAsReader("mybatis/mybatisConf.xml");
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
					session = sqlSessionFactory.openSession();
				} catch (IOException e) {
					log.error(e.getMessage());
					throw new ADBusinessException("mybatis/mybatisConf.xml 找不到");
				}
			}
			mapper = session.getMapper(clz);
		}
		return mapper;
	}

	@Autowired
	MsBaseBillCodeRuleMapper msBaseBillCodeRuleMapper;
	
	@Autowired
	MyBillcodereturnMapper myBillcodereturnMapper;

	private static Map<String, MsBaseBillCodeRule> ruleMap = new HashMap<>();

	public MsBaseBillCodeRule getMsBaseBillCodeRule(String billtype, String pkCorp) throws ADBusinessException {
		String corpKey = billtype + pkCorp;
		MsBaseBillCodeRule rulebase = ruleMap.get(corpKey);
		if (rulebase == null) {
			MsBaseBillCodeRuleExample example = new MsBaseBillCodeRuleExample();
			MsBaseBillCodeRuleExample.Criteria criteria = example.createCriteria();
			criteria.andBilltypeEqualTo(billtype);
			criteria.andDrEqualTo((short) 0);
//			criteria.andPkCorpEqualTo(pkCorp);
			List<MsBaseBillCodeRule> msBaseBillCodeRules = msBaseBillCodeRuleMapper.selectByExample(example);
			if (ObjectUtilms.isEmpty(msBaseBillCodeRules)) {
				rulebase = createNewRuleBase(billtype, pkCorp);
				msBaseBillCodeRuleMapper.insert(rulebase);
			} else {
				rulebase = msBaseBillCodeRules.get(0);
			}
			ruleMap.put(corpKey, rulebase);
		}
		return rulebase;
	}
	
	@Autowired
	BeanHelperEnv beanHelperEnv;

	private MsBaseBillCodeRule createNewRuleBase(String billtype, String pkCorp) {
		String fix = "AZ";
		MsBaseBillCodeRule MsBaseBillCodeRule = new MsBaseBillCodeRule();
		MsBaseBillCodeRule.setPrimaryKey(beanHelperEnv.generateid());
		MsBaseBillCodeRule.setTimeformat("yyMM");
		MsBaseBillCodeRule.setTs(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(System.currentTimeMillis()));
		MsBaseBillCodeRule.setLasttimevalue(new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis()));
		MsBaseBillCodeRule.setCodeprefix(fix);
		MsBaseBillCodeRule.setConstantfix(fix);
		MsBaseBillCodeRule.setIsrefer(Short.valueOf("2"));
		MsBaseBillCodeRule.setBilltype(billtype);
		MsBaseBillCodeRule.setCodelength(8);
		MsBaseBillCodeRule.setPkCorp(pkCorp);
		MsBaseBillCodeRule.setDr(0);
		MsBaseBillCodeRule.setLastserialnumber("00000000");
		return MsBaseBillCodeRule;
	}


	@Override
	public BillCode newBillCode(String billtype, String pkCorp) throws ADBusinessException {
		MsBaseBillCodeRule rulebase = getMsBaseBillCodeRule(billtype, pkCorp);
		MyBillcodereturnExample example = new MyBillcodereturnExample();
		MyBillcodereturnExample.Criteria criteria = example.createCriteria();
		criteria.andPkBillcoderulebaseEqualTo(rulebase.getPkBaseBillCodeRule());
		return new BillCode(rulebase, myBillcodereturnMapper.selectByExample(example), msBaseBillCodeRuleMapper, myBillcodereturnMapper);
	}
}

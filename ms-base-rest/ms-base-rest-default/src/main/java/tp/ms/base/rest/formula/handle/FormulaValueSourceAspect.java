package tp.ms.base.rest.formula.handle;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import tp.ms.base.rest.formula.vo.FormulaObject;
import tp.ms.base.rest.refinfo.vo.RefInfoQueryVO;
import tp.ms.common.bean.result.DataSourceChoose;
import tp.ms.common.bean.support.context.BeanHelperEnv;
import tp.ms.common.bean.utils.ReplaceNull;
import tp.ms.common.data.mybatis.annotation.SqlSessionKey;
import tp.ms.common.data.mybatis.config.MsSessionTemplateHolder;

@Slf4j
@Aspect
@Order(-95)
@Component
public class FormulaValueSourceAspect {

	protected static final ThreadLocal<String> clientSessionkey = new ThreadLocal<>();

	protected static final ThreadLocal<String> earlyDataSourceSessionKey = new ThreadLocal<>();
	@Autowired
	BeanHelperEnv beanHelper;

	@Pointcut("execution(* tp.ms.base.rest.formula.api.FormulaValueService.execute(..))")
	protected void formulaValuePintCut() {}


	@Pointcut("target(tp.ms.base.rest.formula.mapper.FormulaValueDaoMapper)")
	protected void formulaValueQuery() {}

	@Pointcut("execution(* tp.ms.base.rest.refinfo.mapper.MsBaseRefInfoMapper.execute*(..))")
	protected void refInfoDaoQuery() {}
	
//	@Before("formulaValuePintCutImpl()")
//	public void pointBefore3impl(JoinPoint jp) {
//		System.out.println("\r\n" + 
//				"	\r\n" + 
//				"	@Before(\"formulaValuePintCut() && args(data)\")\r\n" + 
//				"	public void pointBefore3(JoinPoint jp, TranslateValue data) {\r\n" + 
//				"		System.out.println(data.getSessionKey());\r\n" + 
//				"	}");
//	}
//	
//	@Before("formulaValuePintCutImpl() && args(data)")
//	public void pointBefore3impl(JoinPoint jp, TranslateValue data) {
//		System.out.println(data.getSessionKey());
//	}
//	
//	@Before("formulaValuePintCut()")
//	public void pointBefore3(JoinPoint jp) {
//		System.out.println("\r\n" + 
//				"	\r\n" + 
//				"	@Before(\"formulaValuePintCut() && args(data)\")\r\n" + 
//				"	public void pointBefore3(JoinPoint jp, TranslateValue data) {\r\n" + 
//				"		System.out.println(data.getSessionKey());\r\n" + 
//				"	}");
//	}
//	
//	@Before("formulaValuePintCut() && args(data)")
//	public void pointBefore3(JoinPoint jp, TranslateValue data) {
//		System.out.println(data.getSessionKey());
//	}

	@Before("formulaValuePintCut() && args(data)")
	public void pointBefore(JoinPoint jp, DataSourceChoose data) {
		log.info("current exec method : {}", jp.getSignature().toString());
		String targetSource = data.getSessionKey();
		log.info(" current use dataSource for wait :{}", targetSource);
		clientSessionkey.set(targetSource);
	};

	@Before("refInfoDaoQuery() && args(queryVO)")
	public void pointRefDaoBefore(JoinPoint jp, RefInfoQueryVO queryVO) {
		log.info("current exec method : {}", jp.getSignature().toString());
		String targetSource = queryVO.getDsKey();
		log.info(" current use dataSource for wait :{}", targetSource);
		earlyDataSourceSessionKey.set(MsSessionTemplateHolder.getSessionKey());
		switch (targetSource) {
		case "amsflow":
			targetSource = SqlSessionKey.AMSFLOW;
			break;
		case "nc6302":
			targetSource = SqlSessionKey.NC6302;
			break;
		case "mprocess":
			targetSource = SqlSessionKey.MPROCESS;
			break;
		case "hxtest":
			targetSource = SqlSessionKey.HXTEST;
			break;
		default:
			targetSource = SqlSessionKey.CS6304;
			break;
		}
		log.info(" current use dataSource for wait :{}", targetSource);
		MsSessionTemplateHolder.setSessionKey(targetSource);
	};


	@After("refInfoDaoQuery() && args(queryVO)")
	public void pointRefDaoAfter(JoinPoint jp, RefInfoQueryVO queryVO) {
		log.info("current exec method : {}", jp.getSignature().toString());
		log.info("return set early use aspact dataSource:{}", earlyDataSourceSessionKey.get());
		MsSessionTemplateHolder.setSessionKey(earlyDataSourceSessionKey.get());
		log.info("ThreadLocal remove:{}", earlyDataSourceSessionKey.get());
		earlyDataSourceSessionKey.remove();
	}
	
	@After("formulaValuePintCut() && args(data)")
	public void removeFormulaValueSourceAspectThreadLocalInfo(JoinPoint jp, DataSourceChoose data) {
		log.info("FormulaValueSourceAspect current ThreadLocal remove:{}", clientSessionkey.get());
		clientSessionkey.remove();
	}

	@Before("formulaValueQuery() && args(tp.ms.base.rest.formula.vo.FormulaObject) && args(param)")
	public void pointBefore(JoinPoint jp, FormulaObject param) {
		log.info("current exec method : {}", jp.getSignature().toString());
		earlyDataSourceSessionKey.set(MsSessionTemplateHolder.getSessionKey());
		String targetSource = ReplaceNull.string(clientSessionkey.get());
		switch (targetSource) {
		case "amsflow":
			targetSource = SqlSessionKey.AMSFLOW;
			break;
		case "nc6302":
			targetSource = SqlSessionKey.NC6302;
			break;
		case "mprocess":
			targetSource = SqlSessionKey.MPROCESS;
			break;
		case "hxtest":
			targetSource = SqlSessionKey.HXTEST;
			break;
		default:
			targetSource = SqlSessionKey.CS6304;
			break;
		}
		log.info(" current use dataSource for wait :{}", targetSource);
		MsSessionTemplateHolder.setSessionKey(targetSource);
	};


	@After("formulaValueQuery() && args(tp.ms.base.rest.formula.vo.FormulaObject) && args(param)")
	public void fvBefore(JoinPoint jp, FormulaObject param) {
		log.info("current exec method : {}", jp.getSignature().toString());
		log.info("return set early use aspact dataSource:{}", earlyDataSourceSessionKey.get());
		MsSessionTemplateHolder.setSessionKey(earlyDataSourceSessionKey.get());
		log.info("ThreadLocal remove:{}", earlyDataSourceSessionKey.get());
		earlyDataSourceSessionKey.remove();
	}

	

}

package tp.ms.base.rest.resource.logic;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import tp.ms.base.rest.resource.vo.MajorAuditBaseVO;
import tp.ms.base.rest.resource.vo.MajorBaseVO;
import tp.ms.common.bean.http.OperationValue;
import tp.ms.common.bean.support.context.BeanHelperEnv;
import tp.ms.common.bean.support.context.MsEnvContextHolder;
import tp.ms.common.bean.utils.ADate;
import tp.ms.common.bean.vo.BaseExample;
import tp.ms.common.bean.vo.BaseVO;
import tp.ms.common.bean.vo.User;

@Slf4j
@Aspect
@Order(-95)
@Component
public class DaoMapperOperationAspect {

	@Autowired
	BeanHelperEnv beanHelper;


	@Pointcut("target(tp.ms.common.data.mybatis.mapper.SuperDaoMapper) || execution(* tp.ms.common.data.mybatis.mapper..*(..))")
	protected void mapperTargetAspect() {}

	@Pointcut("mapperTargetAspect() && args(..)")
	public void daoMapperPintCut() { }
	
	@Before("mapperTargetAspect()")
	public void targetAspectBefore(JoinPoint jp) {
		log.info("jp {}", jp.getSignature().getName()) ;
	}
	
	@Before("daoMapperPintCut() && args(example)")
	public void pointBefore(JoinPoint jp, BaseExample example) {
		log.info("jp {}", jp.getSignature().getName()) ;
	}
	
	@Before("daoMapperPintCut() && args(record)")
	public void pointBefore(JoinPoint jp, BaseVO record) {
		log.info("jp {}", jp.getSignature().getName()) ;
	}
	
	@Before("daoMapperPintCut() && args(record,example)")
	public void pointBefore(JoinPoint jp, BaseVO record, BaseExample example) {
		log.info("jp {}", jp.getSignature().getName()) ;
		log.info("record {}", record) ;
		log.info("example {}", example) ;
	}
	
	@Around("daoMapperPintCut() && args(record)")
	public Object pointBefore(ProceedingJoinPoint point, BaseVO record) throws Throwable {
		String methodName = point.getSignature().getName().toUpperCase();
		if(methodName.startsWith(OperationValue.INSERT.name())) {
			return insertEnhancement(point);
		}else if(methodName.startsWith(OperationValue.UPDATE.name())) {
			return updateEnhancement(point);
		}
		return point.proceed();
	}

	private Object updateEnhancement(ProceedingJoinPoint point) throws Throwable {
		/*
		 * 在数据插入前（insert开头的方法）对参数进行校验及补全
		 */
		//得到参数
		Object[] args = point.getArgs();
		String now = ADate.now().toString();
		for(Object arg: args) {
			if(arg instanceof MajorBaseVO) {
				MajorBaseVO vo = (MajorBaseVO) arg;
				vo.setModifier(MsEnvContextHolder.getContext().user().getPkUser());
				vo.setModifiedtime(now);
			}
			if(arg instanceof BaseVO) {
				BaseVO vo = (BaseVO) arg;
				vo.setTs(now);
			}
		}
		return point.proceed(args);
	}
	private Object insertEnhancement(ProceedingJoinPoint point) throws Throwable {
		/*
		 * 在数据插入前（insert开头的方法）对参数进行校验及补全
		 */
		//得到参数
		Object[] args = point.getArgs();
		String now = ADate.now().toString();
		for(Object arg: args) {
			if(arg instanceof MajorBaseVO) {
				MajorBaseVO vo = (MajorBaseVO) arg;
				vo.setCreator(MsEnvContextHolder.getContext().user().getPkUser());
				vo.setCreationtime(now);
				vo.setPkCorp(MsEnvContextHolder.getContext().user().getPkOrg());
				vo.setPkGroup(MsEnvContextHolder.getContext().user().getPkGroup());
				if(MsEnvContextHolder.getContext().business() != null)
					vo.setBilltype(MsEnvContextHolder.getContext().business().getPkBilltype());
				vo.setEnabled(0);
			}
			
			if(arg instanceof MajorAuditBaseVO) {
				MajorAuditBaseVO vo = (MajorAuditBaseVO) arg;
				vo.setApproveStatus(-1);
			}
			if(arg instanceof BaseVO) {
				BaseVO vo = (BaseVO) arg;
				String pkOrg;
				if(vo.getPrimaryKey() == null) {
					User user = MsEnvContextHolder.getContext().user();
					if(user == null) {
						pkOrg = "000001AA0000000000000000000B01";
					}else {
						pkOrg = user.getPkOrg();
					}
					vo.setPrimaryKey(beanHelper.generateid(pkOrg));
				}
				vo.setTs(now);
				vo.setDr(0);
			}
		}
		return point.proceed(args);
	}
	
//	@AfterReturning(pointcut = "pointCut()", returning = "returnValue")
	public void log(JoinPoint point, Object returnValue) {
		System.out.println("@AfterReturning：模拟日志记录功能...");
		System.out.println("@AfterReturning：目标方法为：" + point.getSignature().getDeclaringTypeName() + "."
				+ point.getSignature().getName());
		System.out.println("@AfterReturning：参数为：" + Arrays.toString(point.getArgs()));
		System.out.println("@AfterReturning：返回值为：" + returnValue);
		System.out.println("@AfterReturning：被织入的目标对象为：" + point.getTarget());
	}

//	@After("pointCut()")
	public void releaseResource(JoinPoint point) {
		System.out.println("@After：模拟释放资源...");
		System.out.println(
				"@After：目标方法为：" + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
		System.out.println("@After：参数为：" + Arrays.toString(point.getArgs()));
		System.out.println("@After：被织入的目标对象为：" + point.getTarget());
	}

}

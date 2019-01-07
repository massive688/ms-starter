package tp.ms.base.rest.resource.logic;
//package com.ms.core.service.rest.common.logic;
//
//import java.util.Arrays;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.ms.core.service.rest.common.utils.ADate;
//import com.ms.core.service.rest.common.vo.BaseExample;
//import com.ms.core.service.rest.common.vo.BaseVO;
//import com.ms.core.service.rest.common.vo.ChildBaseVO;
//import com.ms.core.service.rest.context.supper.BeanHelperEnv;
//
////@Aspect
//@Component
//public class ChildDaoMapperOperationAspect {
//
//	@Autowired
//	BeanHelperEnv beanHelper;
//
////	@Pointcut("target(com.ms.core.service.rest.common.mapper.DaoMapper) && within(BaseExample) && args(example))")
//	public void daoMapperPintCut() {
//		BaseExample example;
//	}
//	
////	@Around("daoMapperPintCut() && args(com.ms.core.service.rest.common.vo.ChildBaseVO)")
//	public void pointBefore(ProceedingJoinPoint point, BaseVO record) throws Throwable {
//		String methodName = point.getSignature().getName().toUpperCase();
//		if(methodName.startsWith(OperationValue.INSERT.name())) {
//			insertEnhancement(point);
//		}else if(methodName.startsWith(OperationValue.UPDATE.name())) {
//			updateEnhancement(point);
//		}else if(methodName.startsWith(OperationValue.DELETE.name())) {
//			deleteEnhancement(point);
//		}else if(methodName.startsWith(OperationValue.SELECT.name())) {
//			selectEnhancement(point);
//		}
//	}
//	private void selectEnhancement(ProceedingJoinPoint point) {
//		// TODO Auto-generated method stub
//		
//	}
//	private void deleteEnhancement(ProceedingJoinPoint point) {
//		// TODO Auto-generated method stub
//		
//	}
//	private void updateEnhancement(ProceedingJoinPoint point) throws Throwable {
//		/*
//		 * 在数据插入前（insert开头的方法）对参数进行校验及补全
//		 */
//		//得到参数
//		Object[] args = point.getArgs();
//		for(Object arg: args) {
//			if(arg instanceof ChildBaseVO) {
//				ChildBaseVO vo = (ChildBaseVO) arg;
//				vo.setTs(ADate.now().toString());
//			}
//		}
//		point.proceed(args);
//	}
//	private void insertEnhancement(ProceedingJoinPoint point) throws Throwable {
//		/*
//		 * 在数据插入前（insert开头的方法）对参数进行校验及补全
//		 */
//		//得到参数
//		Object[] args = point.getArgs();
//		for(Object arg: args) {
//			if(arg instanceof ChildBaseVO) {
//				ChildBaseVO vo = (ChildBaseVO) arg;
//				if(vo.getPrimaryKey() == null)
//					vo.setPrimaryKey(beanHelper.generateid());
//				vo.setTs(ADate.now().toString());
//				vo.setDr(0);
//			}
//		}
//		point.proceed(args);
//	}
//	
//	@AfterReturning(pointcut = "pointCut()", returning = "returnValue")
//	public void log(JoinPoint point, Object returnValue) {
//		System.out.println("@AfterReturning：模拟日志记录功能...");
//		System.out.println("@AfterReturning：目标方法为：" + point.getSignature().getDeclaringTypeName() + "."
//				+ point.getSignature().getName());
//		System.out.println("@AfterReturning：参数为：" + Arrays.toString(point.getArgs()));
//		System.out.println("@AfterReturning：返回值为：" + returnValue);
//		System.out.println("@AfterReturning：被织入的目标对象为：" + point.getTarget());
//	}
//
//	@After("pointCut()")
//	public void releaseResource(JoinPoint point) {
//		System.out.println("@After：模拟释放资源...");
//		System.out.println(
//				"@After：目标方法为：" + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
//		System.out.println("@After：参数为：" + Arrays.toString(point.getArgs()));
//		System.out.println("@After：被织入的目标对象为：" + point.getTarget());
//	}
//
//}

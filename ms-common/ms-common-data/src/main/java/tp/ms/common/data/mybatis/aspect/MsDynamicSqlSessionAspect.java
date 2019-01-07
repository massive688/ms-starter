package tp.ms.common.data.mybatis.aspect;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.ibatis.binding.MapperProxy;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import lombok.extern.slf4j.Slf4j;
import tp.ms.common.data.mybatis.annotation.TargetSqlSession;
import tp.ms.common.data.mybatis.config.MsSessionTemplateHolder;

@Slf4j
@Aspect
@Order(-96)
@Component
public class MsDynamicSqlSessionAspect {

	protected static final ThreadLocal<String> preDatasourceHolder = new ThreadLocal<>();


    /*
     * 		args			参数
     *	 	@within 		拦截接口或者类带有TargetSqlSession注解，执行方法是在拦截的类或者接口里面的方法
     *		@annotation		拦截所有带有TargetSqlSession注解的方法
     *		@target			@target注解不可以单独使用，必须配合其他方式一起使用
     *		this			拦截 所有实现tp.ms.common.bean.mapper.SuperDaoMapper接口的所有方法 另一情况继承的方法不能被识别 重载了就识别
     *		target 			拦截 所有实现tp.ms.common.bean.mapper.SuperDaoMapper接口的所有方法 不是SuperDaoMapper接口内的方法都拦截 子孙都拦截 不重载只继承的方法也拦截
     *		execution(* com.ms..*apper.select*(..))  表达式形式，最简单的方式 
     */
	@Pointcut("@within(tp.ms.common.data.mybatis.annotation.TargetSqlSession) "
			+ "|| @annotation(tp.ms.common.data.mybatis.annotation.TargetSqlSession)")
	protected void sourceAspect() {}
	@Pointcut("target(tp.ms.base.rest.resource.service.IBaseService) "
			+ "&& @target(tp.ms.common.data.mybatis.annotation.TargetSqlSession)")
	protected void sourceServiceAspect() {}
	/*
	 * 怎么都拦截不了注解
	 */
//	@Pointcut("target(tp.ms.common.data.mybatis.mapper.SuperDaoMapper) "
//			+ "&& @target(tp.ms.common.data.mybatis.annotation.TargetSqlSession)")
//	protected void sourceMapperAspect() {}

	@Pointcut("target(tp.ms.common.data.mybatis.mapper.SuperDaoMapper) || execution(* tp.ms.common.data.mybatis.mapper..*(..))")
	protected void sourceMapperAspect() {}



	/*
	 * 根据@TargetSqlSession的属性值设置不同的dataSourceKey,以供DynamicDataSource
	 */
	@Before("sourceAspect() || sourceServiceAspect()")
	public void sourceServiceAspectMethodExecution(JoinPoint jp) {
		log.info("current exec method : {}", jp.getSignature().toString());
		determineServiceSource(jp);
	}
	/*
	 * 留着方法备用吧，懒了
	 * @param jp
	 */
//	@Before("sourect()")
//	public void sourceMapperAspectMethodExecution(JoinPoint jp) {
//		resetSource(jp);
//	}
//	@Before("sourceMspect()")
//	public void sourceMapperwithinAspectMethodExecution(JoinPoint jp) {
//		resetSource(jp);
//	}
	@Before("sourceMapperAspect()")
	public void sourceMAspectMethodExecution(JoinPoint jp) {
		log.info("current exec method : {}", jp.getSignature().toString());
		determineMapperSource(jp);
	}


	@After("sourceAspect() || sourceServiceAspect() || sourceMapperAspect()")
	public void restoreDataSourceAfterMethodExecution(JoinPoint jp) {
		log.info("current exec method : {}", jp.getSignature().toString());
		log.info("return set early use aspact dataSource:{}", preDatasourceHolder.get());
		MsSessionTemplateHolder.setSessionKey(preDatasourceHolder.get());
		log.info("ThreadLocal remove:{}", preDatasourceHolder.get());
		preDatasourceHolder.remove();
	}

	/*
	 * 通过拦截service层的注解确定数据源
	 * @param jp
	 */
	private void determineServiceSource(JoinPoint jp) {
		String methodName = jp.getSignature().getName();
		Class<?> targetClass = jp.getSignature().getDeclaringType();
		TargetSqlSession classAnnotation = (TargetSqlSession) targetClass.getAnnotation(TargetSqlSession.class);
		if(classAnnotation == null) {
			targetClass = jp.getTarget().getClass();
			classAnnotation = findFromClass(targetClass);
		}
		
		TargetSqlSession methodAnnotation = findFromMethod(targetClass, methodName);
		TargetSqlSession annotation = methodAnnotation == null ? classAnnotation : methodAnnotation;
		if(annotation != null) {
			resetSource(annotation);
		}
	}

	/*
	 * 通过拦截mapper接口解析出的注解确定数据源
	 * @param jp
	 */
	private void determineMapperSource(JoinPoint jp){
		if(isProxy(jp.getTarget())) {
			Class<?>[] interfaces = jp.getTarget().getClass().getInterfaces();
			for (Class<?> face : interfaces) {
				TargetSqlSession choDs = face.getAnnotation(TargetSqlSession.class);
				if (choDs != null) {
					resetSource(choDs);
					break;
				}
			}
		}
	}

	private boolean isProxy(Object object) {
		boolean result = object instanceof MapperProxy ;
		result = ClassUtils.isCglibProxy(object);
		result = Proxy.isProxyClass(object.getClass());
		return result;
	}
	
	/*
	 * 注解重置数据源
	 * @param annotation
	 */
	private void resetSource(TargetSqlSession annotation) {
		String targetSource = annotation == null ? null : annotation.value();
		log.info("early use aspact dataSource:{}", MsSessionTemplateHolder.getSessionKey());
		preDatasourceHolder.set(MsSessionTemplateHolder.getSessionKey());

		log.info("set current use dataSource:{}", targetSource);
		if (targetSource == null) {
			MsSessionTemplateHolder.setSessionKey(null);
			return;
		}
		MsSessionTemplateHolder.setSessionKey(targetSource);
	}
	/*
	 * 通过类找注解
	 * @param clazz
	 * @return
	 */
    private TargetSqlSession findFromClass(Class<?> clazz) {
		Class<?> searchType = clazz;
		TargetSqlSession classAnnotation = null;
		while (searchType != null) {
			classAnnotation = (TargetSqlSession) clazz.getAnnotation(TargetSqlSession.class);
			if(classAnnotation != null) return classAnnotation;
			searchType = searchType.getSuperclass();
		}
		return null;
    }
    /*
     * 	通过类及方法名找出该方法的注解
     * @param clazz
     * @param methodName
     * @return
     */
    private TargetSqlSession findFromMethod(Class<?> clazz, String methodName) {
		Class<?> searchType = clazz;
		while (searchType != null) {
			Method[] methods = (searchType.isInterface() ? searchType.getMethods() : searchType.getDeclaredMethods());
			for (Method method : methods) {
				if (methodName.equals(method.getName())) {
					TargetSqlSession choDs = method.getAnnotation(TargetSqlSession.class);
					return choDs;
				}
			}
			searchType = searchType.getSuperclass();
		}
		return null;
	}
}

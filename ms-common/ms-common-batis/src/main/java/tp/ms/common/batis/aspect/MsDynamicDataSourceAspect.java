package tp.ms.common.batis.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import tp.ms.common.batis.cfg.MsDynamicDataSourceContextHolder;

@Slf4j
@Aspect
@Order(-99)
//@Component
public class MsDynamicDataSourceAspect {

	protected static final ThreadLocal<String> preDatasourceHolder = new ThreadLocal<>();

	
	@Pointcut("@target(com.ms.dbatis.spring.dynamic.annotation.TargetDataSource)")
    public void annotationPoinCut() {
		log.info("annotationPoinCut dian {},{}", this, getClass().getName());
    }
	
	
//	@Pointcut("@within(com.ms.dbatis.spring.dynamic.annotation.TargetTransactional) || @annotation(com.ms.dbatis.spring.dynamic.annotation.TargetTransactional)")
//    public void transactionalPoinCut() {
//    }

    @Before(value = "annotationPoinCut()")
    public void changeDataSource(JoinPoint point) throws Throwable {
    	String key = determineDatasource(point);
		if (key == null) {
			MsDynamicDataSourceContextHolder.setDataSourceRouterKey(null);
			return;
		}
		preDatasourceHolder.set(MsDynamicDataSourceContextHolder.getDataSourceRouterKey());
		MsDynamicDataSourceContextHolder.setDataSourceRouterKey(key);
    }


	@After("annotationPoinCut()")
	public void restoreDataSourceAfterMethodExecution() {
		MsDynamicDataSourceContextHolder.setDataSourceRouterKey(preDatasourceHolder.get());
		preDatasourceHolder.remove();
	}
   /*
	 *
	 * @param jp
	 * @return
	 */
	public String determineDatasource(JoinPoint jp) {
		String methodName = jp.getSignature().getName();
		Class<?> targetClass = jp.getSignature().getDeclaringType();
		String dataSourceForTargetClass = resolveDataSourceFromClass(targetClass);
		String dataSourceForTargetMethod = resolveDataSourceFromMethod(targetClass, methodName);
		String resultDS = determinateDataSource(dataSourceForTargetClass, dataSourceForTargetMethod);
		return resultDS;
	}

	/**
	 *
	 * @param targetClass
	 * @return
	 */
	private String resolveDataSourceFromClass(Class<?> targetClass) {
		Transactional classAnnotation = (Transactional) targetClass.getAnnotation(Transactional.class);
		return null != classAnnotation ? resolveDataSourceName(classAnnotation) : null;
	}
	/**
	 *
	 * @param targetClass
	 * @param methodName
	 * @return
	 */
	private String resolveDataSourceFromMethod(Class<?> targetClass, String methodName) {
		Method m = findUniqueMethod(targetClass, methodName);
		if (m != null) {
			Transactional choDs = m.getAnnotation(Transactional.class);
			return resolveDataSourceName(choDs);
		}
		return null;
	}

	/**
	 *
	 * @param ds
	 * @return
	 */
	private String resolveDataSourceName(Transactional ds) {
		return ds == null ? null : ds.value();
	}

	/**
	 *
	 * @param classDS
	 * @param methodDS
	 * @return
	 */
	private String determinateDataSource(String classDS, String methodDS) {
		return methodDS == null ? classDS : methodDS;
	}

	/**
	 *
	 * @param clazz
	 * @param name
	 * @return
	 */
	private static Method findUniqueMethod(Class<?> clazz, String name) {
		Class<?> searchType = clazz;
		while (searchType != null) {
			Method[] methods = (searchType.isInterface() ? searchType.getMethods() : searchType.getDeclaredMethods());
			for (Method method : methods) {
				if (name.equals(method.getName())) {
					return method;
				}
			}
			searchType = searchType.getSuperclass();
		}
		return null;
	}
	
//    @Before(value = "@within(com.ms.dbatis.spring.dynamic.annotation.TargetDataSource)")
//    public void changeDataSource2(JoinPoint point) throws Throwable {
//    	TargetDataSource ds =	(TargetDataSource) point.getSignature().getDeclaringType().getAnnotation(TargetDataSource.class);
//           String dsId = ds.value();
//        if (MsDynamicDataSourceContextHolder.dataSourceIds.contains(dsId)) {
//            log.debug("Use DataSource :{} >", dsId, point.getSignature());
//        } else {
//            log.info("数据源[{}]不存在，使用默认数据源 >{}", dsId, point.getSignature());
//            MsDynamicDataSourceContextHolder.setDataSourceRouterKey(dsId);
//        }
//    }
//
//    @After(value = "@within(com.ms.dbatis.spring.dynamic.annotation.TargetDataSource)")
//    public void restoreDataSource2(JoinPoint point) {
////        log.debug("Revert DataSource : " + targetDataSource.value() + " > " + point.getSignature());
//        MsDynamicDataSourceContextHolder.removeDataSourceRouterKey();
//
//    }
	
//    @Before(value = "@within(targetDataSource) || @annotation(targetDataSource)",  argNames = "point, targetDataSource")
//    public void changeDataSource(JoinPoint point, TargetDataSource ds) throws Throwable {
//        String dsId = ds.value();
//        if (MsDynamicDataSourceContextHolder.dataSourceIds.contains(dsId)) {
//            log.debug("Use DataSource :{} >", dsId, point.getSignature());
//        } else {
//            log.info("数据源[{}]不存在，使用默认数据源 >{}", dsId, point.getSignature());
//            MsDynamicDataSourceContextHolder.setDataSourceRouterKey(dsId);
//        }
//    }

//    @After(value = "@annotation(targetDataSource)",  argNames = "point, targetDataSource")
//    public void restoreDataSource(JoinPoint point, TargetDataSource targetDataSource) {
//        log.debug("Revert DataSource : " + targetDataSource.value() + " > " + point.getSignature());
//        MsDynamicDataSourceContextHolder.removeDataSourceRouterKey();
//    }
}

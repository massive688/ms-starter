package tp.ms.common.data.transaction.config;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.util.ClassUtils;

public class CustomAnnotationTransactionAttributeSource extends AnnotationTransactionAttributeSource{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3100227393556915481L;


	/**
	 * Canonical value held in cache to indicate no transaction attribute was
	 * found for this method, and we don't need to look again.
	 */
	private static final TransactionAttribute NULL_TRANSACTION_ATTRIBUTE = new DefaultTransactionAttribute();


	/**
	 * Logger available to subclasses.
	 * <p>As this base class is not marked Serializable, the logger will be recreated
	 * after serialization - provided that the concrete subclass is Serializable.
	 */
	protected final Log logger = LogFactory.getLog(getClass());

	/**
	 * Cache of TransactionAttributes, keyed by method on a specific target class.
	 * <p>As this base class is not marked Serializable, the cache will be recreated
	 * after serialization - provided that the concrete subclass is Serializable.
	 */
	private final Map<Object, TransactionAttribute> attributeCache = new ConcurrentHashMap<>(1024);

	@Override
	@Nullable
	public TransactionAttribute getTransactionAttribute(Method method, @Nullable Class<?> targetClass) {
		if (method.getDeclaringClass() == Object.class) {
			return null;
		}

		// First, see if we have a cached value.
		Object cacheKey = getCacheKey(method, targetClass);
		Object cached = this.attributeCache.get(cacheKey);
		if (cached != null) {
			// Value will either be canonical value indicating there is no transaction attribute,
			// or an actual transaction attribute.
			if (cached == NULL_TRANSACTION_ATTRIBUTE) {
				return null;
			}
			else {
				return (TransactionAttribute) cached;
			}
		}
		else {
			if(cacheKey.toString().endsWith("BilltypeServiceImpl")) {
				System.out.println(cacheKey);
			}
			// We need to work it out.
			TransactionAttribute txAttr = computeTransactionAttribute(method, targetClass);
			// Put it in the cache.
			if (txAttr == null) {
				txAttr = obtainBaseServiceOrMapperTransactionAttribute(method, targetClass);

				if(txAttr == null) {
					this.attributeCache.put(cacheKey, NULL_TRANSACTION_ATTRIBUTE);	
					txAttr = NULL_TRANSACTION_ATTRIBUTE;
				}else {
					setTxAttr(cached, txAttr, method, targetClass);
				}
				if(cacheKey.toString().endsWith("BilltypeServiceImpl")) {
					System.out.println(txAttr);
				}
			}else {

				setTxAttr(cached, txAttr, method, targetClass);
				
				if(cacheKey.toString().endsWith("BilltypeServiceImpl")) {
					System.out.println(txAttr);
				}

			}
			return txAttr;
		}
	}

	private void setTxAttr(Object cached, TransactionAttribute txAttr, Method method, Class<?> targetClass) {
		String methodIdentification = ClassUtils.getQualifiedMethodName(method, targetClass);
		if (txAttr instanceof DefaultTransactionAttribute) {
			((DefaultTransactionAttribute) txAttr).setDescriptor(methodIdentification);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Adding transactional method '" + methodIdentification + "' with attribute: " + txAttr);
		}
		this.attributeCache.put(cached, txAttr);
	}

	private TransactionAttribute obtainBaseServiceOrMapperTransactionAttribute(Method method, Class<?> targetClass) {
		// Don't allow no-public methods as required.
		if (allowPublicMethodsOnly() && !Modifier.isPublic(method.getModifiers())) {
			return null;
		}

		// The method may be on an interface, but we need attributes from the target class.
		// If the target class is null, the method will be unchanged.
		Method specificMethod = AopUtils.getMostSpecificMethod(method, targetClass);

		// First try is the method in the target class.
		TransactionAttribute txAttr = findTransactionAttribute(specificMethod);
		if (txAttr != null) {
			return txAttr;
		}

		// Second try is the transaction attribute on the target class.
		txAttr = findTransactionAttribute(specificMethod.getDeclaringClass());
		if (txAttr != null && ClassUtils.isUserLevelMethod(method)) {
			return txAttr;
		}

		if (specificMethod != method) {
			// Fallback is to look at the original method.
			txAttr = findTransactionAttribute(method);
			if (txAttr != null) {
				return txAttr;
			}
			// Last fallback is the class of the original method.
			txAttr = findTransactionAttribute(method.getDeclaringClass());
			if (txAttr != null && ClassUtils.isUserLevelMethod(method)) {
				return txAttr;
			}
		}

		return null;
	}

}

package tp.ms.base.rest.resource.service.ace;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tp.ms.common.bean.exception.ADBusinessException;

public class CompareAroundProcesser<E> {
	private static final Logger log = LoggerFactory.getLogger(CompareAroundProcesser.class);
	private RuleCollection<Object> before;
	private RuleCollection<Object> after;

	public CompareAroundProcesser() {
		this.before = new RuleCollection<Object>(EventType.Before);
		this.after = new RuleCollection<Object>(EventType.After);
	}

	public void addBeforeRule(IRule<E> rule) {
		this.before.add(rule);
	}

	/**
	 * 添加处理后业务规则
	 * 
	 * @param rule 业务规则
	 */
	public void addAfterRule(IRule<E> rule) {
		this.after.add(rule);
	}

	/**
	 * 执行处理前业务规则
	 * 
	 */
	/**
	 * 
	 * @param vos 要处理的业务对象
	 * @param originVOs 早前原始的数据对象
	 * @return 业务处理后的业务对象。有过滤规则的，可能返回的业务对象有增有减
	 * @throws ADBusinessException  抛出业务处理异常
	 */
	@SuppressWarnings("unchecked")
	public E before(E vos, E originVOs) throws ADBusinessException {
		Iterator<Object> iterator = this.before.iterator();
		E items = vos;
		while (iterator.hasNext()) {
			long start = System.currentTimeMillis();
			Object obj = iterator.next();
			// if (obj instanceof ICompareRule) {
			// ICompareRule<E> rule = (ICompareRule<E>) obj;
			// rule.process(items, originVOs);
			// }
			// else
			if (obj instanceof IRule) {
				IRule<E> rule = (IRule<E>) obj;
				rule.process(items);
			}
			// else if (obj instanceof IFilterRule) {
			// IFilterRule<E> rule = (IFilterRule<E>) obj;
			// items = rule.process(items);
			// }
			if (obj != null) {
				// 为了统计每个业务规则的执行时间、sql执行数
				long end = System.currentTimeMillis();
				String msg = "前规则执行时间[" + (end - start) + "]ms";/* -=notranslate=- */
				log.debug(msg);
			}
		}
		return items;
	}

	/**
	 * 执行处理后比较型业务规则
	 * 
	 * @param vos 要处理的对象
	 * @param originVOs 要处理的对象的历史版本
	 * @return 业务处理后的业务对象。有过滤规则的，可能返回的业务对象有增有减
	 * @throws ADBusinessException  抛出业务处理异常
	 */
	@SuppressWarnings("unchecked")
	public E after(E vos, E originVOs) throws ADBusinessException {
		Iterator<Object> iterator = this.after.iterator();
		E items = vos;
		while (iterator.hasNext()) {
			long start = System.currentTimeMillis();
			Object obj = iterator.next();
			if (obj instanceof IRule) {
				IRule<E> rule = (IRule<E>) obj;
				rule.process(items);
			}
			// else if (obj instanceof ICompareRule) {
			// ICompareRule<E> rule = (ICompareRule<E>) obj;
			// rule.process(items, originVOs);
			// }

			// else if (obj instanceof IFilterRule) {
			// IFilterRule<E> rule = (IFilterRule<E>) obj;
			// items = rule.process(items);
			// }
			if (obj != null) {
				// 为了统计每个业务规则的执行时间、sql执行数
				long end = System.currentTimeMillis();
				String msg = "后规则执行时间[" + (end - start) + "]ms";/* -=notranslate=- */
				log.debug(msg);
			}
		}
		return items;
	}

}

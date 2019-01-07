package tp.ms.base.rest.resource.service.ace;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RuleCollection<E> {

	/**
	 * 要监听的事件类型
	 */
	private EventType eventType;
	/**
	 * 规则的集合
	 */
	private List<E> list = new ArrayList<E>();

	public RuleCollection(EventType eventType) {
		this.eventType = eventType;
	}

	/**
	 * 添加业务规则
	 *
	 * @param rule
	 *            业务规则
	 */
	public void add(E rule) {
		this.list.add(rule);
	}

	public Iterator<E> iterator() {
		return this.list.iterator();
	}
	
	public EventType getEventType() {
		return eventType;
	}
}

package tp.ms.base.rest.resource.service.ace;

import tp.ms.common.bean.exception.AExceptionUtils;

/**
 * 规则扩展事件类型
 * 
 * @since 6.0
 * @version 2009-11-20 上午08:37:09
 * @author 钟鸣
 */
public enum EventType {
  
 /*
   * 前事件类型
   */
  After("after"),

 /*
   * 后事件类型
   */
  Before("before");

 /*
   * 规则扩展事件类型对应的XML标签名
   */
  private String name;

  private EventType(String name) {
    this.name = name;
  }

 /*
   * 获取规则扩展事件类型对应的XML标签名
   * 
   * @return 对应的XML标签名
   */
  public String getName() {
    return this.name;
  }
  
 /*
   * 根据XML标签名得到对应的枚举类型
   * 
   * @param name XML标签名
   * @return 对应的枚举值
   */
  public static EventType getEventType(String name) {
    EventType type = null;
    if (EventType.After.getName().equals(name)) {
      type = EventType.After;
    }
    else if (EventType.Before.getName().equals(name)) {
      type = EventType.Before;
    }
    else {
      AExceptionUtils.unSupported();
    }
    return type;
  }
  
}

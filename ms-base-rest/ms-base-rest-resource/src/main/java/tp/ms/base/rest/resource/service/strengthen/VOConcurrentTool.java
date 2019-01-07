package tp.ms.base.rest.resource.service.strengthen;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import tp.ms.common.bean.exception.AExceptionUtils;
import tp.ms.common.bean.vo.IBaseVO;

/**
 * VO的并发控制工具
 * <ol>
 * <li>增加VO主键的中间件锁</li>
 * <li>检查两组对应VO的时间戳是否一致</li>
 * </ol>
 * 
 * @since 6.0
 * @version 2009-5-12 下午10:47:11
 * @author 钟鸣
 */
public class VOConcurrentTool {


  public void checkTS(IBaseVO vo, IBaseVO originVO) {
    boolean flag = true;
    Object ts = vo.getTs();
    Object originTs = originVO.getTs();
    if (ts == null) {
      flag = false;
    }
    else if (!ts.equals(originTs)) {
      flag = false;
    }
    if (!flag) {
      this.throwUnSynchronizedException(vo);
    }
  }

  
  Map<String, ReentrantLock> locks = new ConcurrentHashMap<String, ReentrantLock>();

//  Lock lock = new ReentrantLock();


  private void lock(Class<? extends IBaseVO> clazz, String id) {
	  String lockKey = bindLockKey(clazz, id);
	  ReentrantLock lock;
	  if(locks.containsKey(lockKey)) {
		  lock = locks.get(lockKey);
	  }else {
		  lock = new ReentrantLock();
		  locks.putIfAbsent(lockKey, lock);
	  }
	  if(lock == null || !(lock instanceof ReentrantLock)) {
		  lock = new ReentrantLock();
		  locks.putIfAbsent(lockKey, lock);
	  }
	  lock.lock();
//	  String.format("锁定【%s】ID失败%s", String.valueOf(clazz), id)
  }

  private String bindLockKey(Class<? extends IBaseVO> clazz, String id) {
	return clazz.getName()+id;
}

/**
   * 对VO实例数组进行加锁。加锁失败抛出异常
   * 
   * @param vo VO实例数组
   */
  public void lock(IBaseVO vo) {
    String id = vo.getPrimaryKey();
    this.lock(vo.getClass(), id);
  }

 /*
   * 抛出并发异常
   */
  public void throwUnSynchronizedException() {
    TVersionConflictException ex = new TVersionConflictException();
    AExceptionUtils.wrappException(ex);
  }

 /*
   * 抛出并发异常
   * 
   * @param item 发生并发异常的实体
   */
  public void throwUnSynchronizedException(IBaseVO item) {
    TVersionConflictException ex = new TVersionConflictException(item);
    AExceptionUtils.wrappException(ex);
  }

}

package tp.ms.base.rest.resource.service.strengthen;

/**
 * 实体的时间戳版本不一致的并发异常
 * 
 * @since 6.0
 * @version 2011-6-1 下午03:30:47
 * @author 钟鸣
 */
public class TVersionConflictException extends VersionConflictException {

  private static final long serialVersionUID = -8840170852754278855L;

 /*
   * 异常的详细信息
   */
  private String detailMessage = "该单据已经被他人修改，请刷新界面，重做业务";
  
 /*
   * 实体的时间戳版本不一致的并发异常默认构造函数
   */
  public TVersionConflictException() {
	  super();
  }

 /*
   * 实体的时间戳版本不一致的并发异常的构造函数
   * 
   * @param busiObject 出现异常的对象
   */
  public TVersionConflictException(Object busiObject) {
    super(busiObject);
  }

 /*
   * 实体的时间戳版本不一致的并发异常的构造函数
   * 
   * @param busiObject 出现异常的对象
   * @param detailMessage 详细信息
   */
  public TVersionConflictException(Object busiObject, String detailMessage) {
    super(busiObject);
  }

 /*
   * 实体的时间戳版本不一致的并发异常的构造函数
   * 
   * @param detailMessage 详细信息
   */
  public TVersionConflictException(String detailMessage) {
    this.detailMessage = detailMessage;
  }

 /*
   * 获取异常详细信息
   * 
   * @return 异常详细信息
   */
  public String getDetailMessage() {
    return this.detailMessage;
  }
  
  @Override
	public String getMessage() {
		return this.detailMessage;
	}
}

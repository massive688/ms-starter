package tp.ms.common.bean.vo;

/**
 * 数据对象的状态说明
 */
public interface VoStatus {
	//对象状态	0无操作 1更新 2新增 3删除
	byte UNCHANGED = 0;
	byte UPDATED = 1;
	byte NEW = 2;
	byte DELETED = 3;
}

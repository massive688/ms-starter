package tp.ms.base.rest.resource.service.strengthen;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tp.ms.base.rest.resource.vo.ChildBaseVO;
import tp.ms.base.rest.resource.vo.IPolyVO;
import tp.ms.common.bean.vo.IBaseVO;
import tp.ms.common.bean.vo.IChildVO;
import tp.ms.common.bean.vo.VoStatus;

/**
 * 单据的快照转化为单据VO<br>
 * 主要用于后台恢复前台传入的单据数据
 * 
 * @param <E> 一主多子单据类型
 * @since 6.0
 * @version 2008-10-5 下午09:22:58
 * @author 钟鸣
 */
public class ServerBillCombinClient<E extends IPolyVO> {

	/**
	 * 
	 * @param bill       更新前在数据库中的数据
	 * @param clientBill 当前在前台传入的数据
	 */
	public void combine(E bill, E clientBill) {
		IBaseVO parent = bill.getParent();
		VOTool tool = new VOTool();
		if (parent != null) {
			tool.combine(bill.getParent(), clientBill.getParent());
		}
		List<Class<ChildBaseVO>> childrenClass = clientBill.getChildrenClass();
		if (childrenClass == null) {
			return;
		}
		for (Class<ChildBaseVO> childClass : childrenClass) {
			this.combine(bill, clientBill, childClass);
		}
	}

	private void combine(E bill, E clientBill, Class<ChildBaseVO> childClass) {
		IChildVO[] childrenVO = clientBill.getChildren(childClass);
		if (childrenVO == null) {
			return;
		}
		Map<String, IBaseVO> changedIndex = new HashMap<String, IBaseVO>();
		List<IBaseVO> newList = new ArrayList<IBaseVO>();
		this.orgnizeData(childrenVO, changedIndex, newList);
		boolean flag = this.check(changedIndex, newList, bill, clientBill, childClass);
		if (!flag) {
			return;
		}
		VOTool tool = new VOTool();
		List<IBaseVO> oldList = new ArrayList<IBaseVO>();
		childrenVO = bill.getChildren(childClass);
		for (IBaseVO child : childrenVO) {
			String pk = child.getPrimaryKey();
			IBaseVO bvo = changedIndex.get(pk);
			if (bvo != null) {
				tool.combine(child, bvo);
			}
			oldList.add(child);
		}
		oldList.addAll(newList);
		IChildVO[] arr = (IChildVO[]) Array.newInstance(childClass, oldList.size());
		IChildVO[] vos = oldList.toArray(arr);
		bill.setChildren(childClass, vos);
	}

	private void orgnizeData(IChildVO[] childrenVO, Map<String, IBaseVO> changedIndex, List<IBaseVO> newList) {
		for (IChildVO vo : childrenVO) {
			// 前台界面变态，鄙视(ps:YY程序写了这。很牛逼吧！！哈哈)
			if (vo == null) {
				continue;
			}
			String pk = vo.getPrimaryKey();
			if ((pk != null) && (vo.getStatus() != VoStatus.NEW)) {
				changedIndex.put(pk, vo);
			} else {
				newList.add(vo);
			}
		}
	}

	private boolean check(Map<String, IBaseVO> changedIndex, List<IBaseVO> newList, E bill, E clientBill, Class<ChildBaseVO> childClass) {
		boolean flag = true;
		// 没有改变的VO
		if ((changedIndex.size() == 0) && (newList.size() == 0)) {
			return false;
		}
		IChildVO[] childrenVO = bill.getChildren(childClass);
		// 原始单据上没有当前子实体，而前台界面又新增了些子实体
		if (childrenVO == null) {
			bill.setChildren(childClass, clientBill.getChildren(childClass));
			flag = false;
		}
		return flag;
	}

	/**
	 * 将antherBill中的信息复制到bill中，只针对可序列化、可持续化字段
	 * 
	 * @param bills       原始全单据
	 * @param clientBills 单据快照
	 */
	public void combine(E[] bills, E[] clientBills) {
		int length = bills.length;
		for (int i = 0; i < length; i++) {
			this.combine(bills[i], clientBills[i]);
		}
	}

}

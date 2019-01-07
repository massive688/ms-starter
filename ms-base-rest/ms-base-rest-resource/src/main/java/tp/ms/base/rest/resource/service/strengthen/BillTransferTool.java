package tp.ms.base.rest.resource.service.strengthen;

import lombok.extern.slf4j.Slf4j;
import tp.ms.base.rest.resource.service.IPolyService;
import tp.ms.base.rest.resource.vo.AbstractPolyVO;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.vo.VoStatus;

@Slf4j
public class BillTransferTool<T extends AbstractPolyVO> {
	T originBill;
	T ClientFullInfoBill;
	T clientBill;

	IPolyService<T> polyService;


	public BillTransferTool(T vo, IPolyService<T> polyService) throws ADBusinessException {
		this.polyService = polyService;
		clientBill = vo;		
		if ((vo.getPrimaryKey() == null)
				|| (vo.getParent().getStatus() == VoStatus.NEW)) {
			this.initInserted(vo);
		}else {
			this.initUpdateed(vo);
	    }
	}

	private void initUpdateed(T pvo) throws ADBusinessException {
		BillConcurrentTool tool = new BillConcurrentTool();
	    log.info("Start");
//	    tool.lockBill(pvo);
	    log.info("锁定表头、表体主健"); /*-=notranslate=-*/

	    log.info("Start");
	    String id = pvo.getPrimaryKey();
	    log.info("获取单据主健"); /*-=notranslate=-*/

	    log.info("Start");
	    BillQueryTemplate<T> query = new BillQueryTemplate<T>(polyService, pvo.getClass());
	    this.originBill = (T) query.query(id);
	    log.info("查询原始单据VO"); /*-=notranslate=-*/

	    log.info("Start");
	    @SuppressWarnings("unchecked")
		T vo = (T) this.originBill.clone();

	    ServerBillCombinClient<T> combineClient = new ServerBillCombinClient<T>();
	    combineClient.combine(vo, pvo);
	    this.clientBill = vo;
	    log.info("前台单据VO补充完整"); /*-=notranslate=-*/

	    log.info("Start");
	    tool.checkTS(pvo, this.originBill);
	    log.info("检查时间戳"); /*-=notranslate=-*/
	}

	@SuppressWarnings("unchecked")
	private void initInserted(T pvo) {
		T vo = (T) pvo.clone();
	    this.originBill = vo;
	    this.clientBill = vo;
	}

	@SuppressWarnings("unchecked")
	public T getClientFullInfoBill() {
		return (T) clientBill.clone();
	}

	public T getOriginBill() {
		return originBill;
	}

}

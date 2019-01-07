package tp.ms.base.rest.resource.service.strengthen;

import lombok.extern.slf4j.Slf4j;
import tp.ms.base.rest.resource.service.ISingleService;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.vo.BaseExample;
import tp.ms.common.bean.vo.BaseVO;
import tp.ms.common.bean.vo.VoStatus;

@Slf4j
public class SingleTransferTool<T extends BaseVO, E extends BaseExample> {

	T originBaseVO;
	T ClientFullInfoBaseVO;
	T clientBaseVO;
	

	ISingleService<T, E> singleService;

	public SingleTransferTool(T vo, ISingleService<T, E> singleService) throws ADBusinessException {
		this.singleService = singleService;
		if ((vo.getPrimaryKey() == null)
				|| (vo.getStatus() == VoStatus.NEW)) {
			this.initInserted(vo);
		}else {
			this.initUpdateed(vo);
	    }
	}

	private void initUpdateed(T vo) throws ADBusinessException {
		SingleConcurrentTool tool = new SingleConcurrentTool();
	    log.info("Start");
	    log.info("锁定表头、表体主健"); /*-=notranslate=-*/

	    log.info("Start");
	    String id = vo.getPrimaryKey();
	    log.info("获取单据主健"); /*-=notranslate=-*/

	    log.info("Start");
	    SingleQueryTemplate<T, E> query = new SingleQueryTemplate<T, E>(singleService);
	    this.originBaseVO = (T) query.query(id);
	    log.info("查询原始单据VO"); /*-=notranslate=-*/

	    log.info("Start");
	    @SuppressWarnings("unchecked")
		T origvo = (T) this.originBaseVO.clone();

	    ServerSingleCombinClient<T> combineClient = new ServerSingleCombinClient<T>();
	    combineClient.combine(origvo, vo);
	    this.clientBaseVO = origvo;
	    log.info("前台单据VO补充完整"); /*-=notranslate=-*/

	    log.info("Start");
	    tool.checkTS(vo, this.originBaseVO);
	    log.info("检查时间戳"); /*-=notranslate=-*/
	}

	@SuppressWarnings("unchecked")
	private void initInserted(T vo) {
		T clone = (T) vo.clone();
	    this.originBaseVO = clone;
	    this.clientBaseVO = clone;
	}

	@SuppressWarnings("unchecked")
	public T getClientFullInfoBaseVO() {
		return (T) clientBaseVO.clone();
	}

	public T getOriginBaseVO() {
		return originBaseVO;
	}
}

package tp.ms.base.rest.resource.service.strengthen;

import tp.ms.common.bean.vo.BaseVO;

public class ServerSingleCombinClient<T extends BaseVO> {

	public void combine(T origvo, T vo) {
		VOTool tool = new VOTool();
		if (vo != null) {
			tool.combine(origvo, vo);
		}
	}

}

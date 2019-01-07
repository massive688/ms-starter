package tp.ms.base.rest.refinfo.api;

import java.util.List;

import tp.ms.base.rest.refinfo.vo.BaseRefInfoVO;
import tp.ms.base.rest.refinfo.vo.MyBaseRefInfo;
import tp.ms.base.rest.refinfo.vo.MyBaseRefInfoExample;
import tp.ms.base.rest.resource.http.Pager;
import tp.ms.base.rest.resource.service.ISingleService;

public interface BaseRefInfoService extends ISingleService<MyBaseRefInfo, MyBaseRefInfoExample>{

	String getRefClass(String translate);

	List<BaseRefInfoVO> executeQueryByPager(String refid, Pager data);

}

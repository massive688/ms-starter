package tp.ms.base.rest.process.api;

import com.github.pagehelper.PageInfo;

import tp.ms.base.rest.process.vo.MyAdreamHomeWaitingMatter;
import tp.ms.base.rest.process.vo.MyAdreamHomeWaitingMatterExample;
import tp.ms.base.rest.resource.http.Pager;
import tp.ms.base.rest.resource.service.IChildService;

public interface HomeWaitingMatterService extends IChildService<MyAdreamHomeWaitingMatter, MyAdreamHomeWaitingMatterExample>{

	MyAdreamHomeWaitingMatter queryTaskByProcessId(String processInstanceId);

	PageInfo<MyAdreamHomeWaitingMatter> queryProcessByPager(Pager pager);

}

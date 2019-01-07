package tp.ms.base.rest.process.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tp.ms.base.rest.process.api.HomeWaitingMatterService;
import tp.ms.base.rest.process.mapper.MyAdreamHomeWaitingMatterMapper;
import tp.ms.base.rest.process.vo.MyAdreamHomeWaitingMatter;
import tp.ms.base.rest.process.vo.MyAdreamHomeWaitingMatterExample;
import tp.ms.base.rest.resource.http.Pager;
import tp.ms.base.rest.resource.service.impl.ChildServiceImpl;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.support.context.MsEnvContextHolder;
import tp.ms.common.bean.utils.ObjectUtilms;
import tp.ms.common.bean.vo.BaseExample;
import tp.ms.common.bean.vo.BaseVO;
import tp.ms.common.data.mybatis.mapper.DaoMapper;

@Service
public class HomeWaitingMatterServiceImpl extends ChildServiceImpl<MyAdreamHomeWaitingMatter, MyAdreamHomeWaitingMatterExample> implements HomeWaitingMatterService {

	@Autowired
	MyAdreamHomeWaitingMatterMapper dao;
	
	@Override
	public DaoMapper<MyAdreamHomeWaitingMatter, MyAdreamHomeWaitingMatterExample> getDao() {
		return dao;
	}

	@Override
	public Map<Class<BaseVO>, BaseExample> transformToExampleFromByPolyArray(MyAdreamHomeWaitingMatter[] vos)
			throws ADBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseExample transformToExampleFromPagination(Pager page) throws ADBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setExampleParentKeyCondition(MyAdreamHomeWaitingMatterExample example, String parentKey) {
		example.createCriteria().andDrEqualTo(0)
		.andPkHomeRootEqualTo(parentKey);
	}

	@Override
	public void setQueryExample(MyAdreamHomeWaitingMatterExample example, String key) {
		MyAdreamHomeWaitingMatterExample.Criteria criteria = example.createCriteria();
		criteria.andDrEqualTo(0);
		if(key != null)
			criteria.andPkHomeWaitingMatterEqualTo(key);
	}




	@Override
	public MyAdreamHomeWaitingMatter queryTaskByProcessId(String processInstanceId) {
		MyAdreamHomeWaitingMatterExample example = new MyAdreamHomeWaitingMatterExample();
		example.createCriteria().andDrEqualTo(0)
		.andProcessIdEqualTo(processInstanceId);
		List<MyAdreamHomeWaitingMatter> list = dao.selectByExample(example);
		if(ObjectUtilms.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public PageInfo<MyAdreamHomeWaitingMatter> queryProcessByPager(Pager pager) {
		PageHelper.startPage(pager.getCurrent(), pager.getShowNum());
		return new PageInfo<MyAdreamHomeWaitingMatter>(dao.selectProcessMatter(MsEnvContextHolder.getContext().user().getPkUser()));
	}
	
}

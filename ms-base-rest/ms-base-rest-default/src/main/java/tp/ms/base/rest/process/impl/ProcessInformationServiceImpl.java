package tp.ms.base.rest.process.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tp.ms.base.rest.process.api.ProcessInformationService;
import tp.ms.base.rest.process.mapper.MsWorkableFlowProcessInformationMapper;
import tp.ms.base.rest.process.vo.MsWorkableFlowProcessInformation;
import tp.ms.base.rest.process.vo.MsWorkableFlowProcessInformationExample;
import tp.ms.base.rest.process.vo.MsWorkableFlowProcessInformationExample.Criteria;
import tp.ms.base.rest.resource.http.Pager;
import tp.ms.base.rest.resource.service.impl.ChildServiceImpl;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.support.context.MsEnvContextHolder;
import tp.ms.common.bean.vo.BaseExample;
import tp.ms.common.bean.vo.BaseVO;
import tp.ms.common.data.mybatis.mapper.DaoMapper;

@Service
public class ProcessInformationServiceImpl extends ChildServiceImpl<MsWorkableFlowProcessInformation, MsWorkableFlowProcessInformationExample> implements ProcessInformationService {

	@Autowired
	MsWorkableFlowProcessInformationMapper dao;
	
	@Override
	public DaoMapper<MsWorkableFlowProcessInformation, MsWorkableFlowProcessInformationExample> getDao() {
		return dao;
	}

	@Override
	public Map<Class<BaseVO>, BaseExample> transformToExampleFromByPolyArray(MsWorkableFlowProcessInformation[] vos)
			throws ADBusinessException {
		return null;
	}

	@Override
	public BaseExample transformToExampleFromPagination(Pager page) throws ADBusinessException {
		return null;
	}

	@Override
	public void setExampleParentKeyCondition(MsWorkableFlowProcessInformationExample example, String parentKey) {
	}

	@Override
	public void setQueryExample(MsWorkableFlowProcessInformationExample example, String key) {
		Criteria criteria = example.createCriteria();
		criteria.andDrEqualTo(0);
		if(key != null)
			criteria.andPkProcessInformationEqualTo(key);
	}

	@Override
	public List<MsWorkableFlowProcessInformation> queryByProcessId(String processId) {
		MsWorkableFlowProcessInformationExample example = new MsWorkableFlowProcessInformationExample();
		Criteria criteria = example.createCriteria();
		criteria.andDrEqualTo(0).andProcessIdEqualTo(processId);
		return dao.selectByExample(example);
	}

	@Override
	public List<MsWorkableFlowProcessInformation> queryHandledProcessOnOwn() {
		MsWorkableFlowProcessInformationExample example = new MsWorkableFlowProcessInformationExample();
		MsWorkableFlowProcessInformationExample.Criteria criteria = example.createCriteria();
		criteria.andDrEqualTo(0).andOperatorIDEqualTo(MsEnvContextHolder.getContext().user().getPkUser());
		return dao.selectByExample(example);
	}

}

package tp.ms.base.rest.typecoded.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tp.ms.base.rest.resource.http.Pager;
import tp.ms.base.rest.resource.service.impl.ChildServiceImpl;
import tp.ms.base.rest.typecoded.api.BillTemplateService;
import tp.ms.base.rest.typecoded.mapper.MsBaseBillTemplateMapper;
import tp.ms.base.rest.typecoded.vo.MsBaseBillTemplate;
import tp.ms.base.rest.typecoded.vo.MsBaseBillTemplateExample;
import tp.ms.base.rest.typecoded.vo.MsBaseBillTemplateExample.Criteria;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.vo.BaseExample;
import tp.ms.common.bean.vo.BaseVO;
import tp.ms.common.data.mybatis.annotation.SqlSessionKey;
import tp.ms.common.data.mybatis.annotation.TargetSqlSession;
import tp.ms.common.data.mybatis.mapper.DaoMapper;

@Service
@TargetSqlSession(SqlSessionKey.CS6304)
public class BillTemplateServiceImpl extends ChildServiceImpl<MsBaseBillTemplate, MsBaseBillTemplateExample> implements BillTemplateService {

	@Autowired
	MsBaseBillTemplateMapper dao;

	@Override
	public DaoMapper<MsBaseBillTemplate, MsBaseBillTemplateExample> getDao() {
		return dao;
	}


	@Override
	public Map<Class<BaseVO>, BaseExample> transformToExampleFromByPolyArray(MsBaseBillTemplate[] vos)
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
	public void setExampleParentKeyCondition(MsBaseBillTemplateExample example, String parentKey) {
		example.createCriteria().andDrEqualTo(0)
		.andPkBilltypeEqualTo(parentKey);
	}


	@Override
	public void setQueryExample(MsBaseBillTemplateExample example, String key) {
		Criteria criteria = example.createCriteria();
		criteria.andDrEqualTo(0);
		if(key != null)
			criteria.andPkBaseBillTemplateEqualTo(key);
	}

}

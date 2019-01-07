package tp.ms.base.rest.typecoded.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tp.ms.base.rest.resource.http.Pager;
import tp.ms.base.rest.resource.service.ace.CommonAroundProcesser;
import tp.ms.base.rest.resource.service.impl.MajorServiceImpl;
import tp.ms.base.rest.typecoded.api.BilltypeMajorService;
import tp.ms.base.rest.typecoded.mapper.MsBaseBilltypeMapper;
import tp.ms.base.rest.typecoded.vo.MsBaseBilltype;
import tp.ms.base.rest.typecoded.vo.MsBaseBilltypeExample;
import tp.ms.base.rest.typecoded.vo.MsBaseBilltypeExample.Criteria;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.vo.BaseExample;
import tp.ms.common.bean.vo.BaseVO;
import tp.ms.common.data.mybatis.annotation.SqlSessionKey;
import tp.ms.common.data.mybatis.annotation.TargetSqlSession;
import tp.ms.common.data.mybatis.mapper.DaoMapper;

@Service
@TargetSqlSession(SqlSessionKey.CS6304)
public class BilltypeMajorServiceImpl extends MajorServiceImpl<MsBaseBilltype, MsBaseBilltypeExample> implements BilltypeMajorService {

	@Autowired
	MsBaseBilltypeMapper dao;

	@Override
	public DaoMapper<MsBaseBilltype, MsBaseBilltypeExample> getDao() {
		return dao;
	}
	
	@Override
	public Map<Class<BaseVO>, BaseExample> transformToExampleFromByPolyArray(MsBaseBilltype[] vos)
			throws ADBusinessException {
		return null;
	}
	
	@Override
	public void addBeforeRule(CommonAroundProcesser<MsBaseBilltype> aroundProcesser) {
//		aroundProcesser.addBeforeRule(new CreateBillCodeRule<MsBaseBilltype>("code"));
		super.addBeforeRule(aroundProcesser);
	}




	@Override
	public BaseExample transformToExampleFromPagination(Pager page) throws ADBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setQueryExample(MsBaseBilltypeExample example, String key) {
		Criteria criteria = example.createCriteria();
		criteria.andDrEqualTo(0);
		if(key != null)	
			criteria.andPkBilltypeEqualTo(key);
	}


}

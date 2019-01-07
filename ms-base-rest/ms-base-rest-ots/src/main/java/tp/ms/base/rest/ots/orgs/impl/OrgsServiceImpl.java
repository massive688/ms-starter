package tp.ms.base.rest.ots.orgs.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tp.ms.base.rest.ots.orgs.api.OrgsService;
import tp.ms.base.rest.ots.orgs.entity.MyAdreamOrgelements;
import tp.ms.base.rest.ots.orgs.entity.MyAdreamOrgelementsExample;
import tp.ms.base.rest.ots.orgs.entity.MyAdreamOrgelementsMapper;
import tp.ms.base.rest.resource.http.Pager;
import tp.ms.base.rest.resource.service.impl.MajorServiceImpl;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.vo.BaseExample;
import tp.ms.common.bean.vo.BaseVO;
import tp.ms.common.data.mybatis.mapper.DaoMapper;

@Service
public class OrgsServiceImpl extends MajorServiceImpl<MyAdreamOrgelements, MyAdreamOrgelementsExample> implements OrgsService {

	@Autowired
	MyAdreamOrgelementsMapper dao;
	
	@Override
	public DaoMapper<MyAdreamOrgelements, MyAdreamOrgelementsExample> getDao() {
		return dao;
	}

	@Override
	public BaseExample transformToExampleFromPagination(Pager page) throws ADBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Class<BaseVO>, BaseExample> transformToExampleFromByPolyArray(MyAdreamOrgelements[] vos)
			throws ADBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setQueryExample(MyAdreamOrgelementsExample example, String key) {
		example.createCriteria().andDrEqualTo((short) 0)
		.andPkOrgelementsEqualTo(key);
	}
	

}

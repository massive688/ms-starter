package tp.ms.base.rest.typecoded.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tp.ms.base.rest.resource.service.IChildService;
import tp.ms.base.rest.resource.service.IMajorService;
import tp.ms.base.rest.resource.service.ace.CommonAroundProcesser;
import tp.ms.base.rest.resource.service.impl.PolyServiceImpl;
import tp.ms.base.rest.resource.vo.ChildBaseVO;
import tp.ms.base.rest.typecoded.api.BillCodeService;
import tp.ms.base.rest.typecoded.api.BillTemplateService;
import tp.ms.base.rest.typecoded.api.BilltypeMajorService;
import tp.ms.base.rest.typecoded.api.BilltypeService;
import tp.ms.base.rest.typecoded.rule.VerificationComponentTypeRelationTemplate;
import tp.ms.base.rest.typecoded.vo.MsBaseBillCodeRule;
import tp.ms.base.rest.typecoded.vo.MsBaseBillTemplate;
import tp.ms.base.rest.typecoded.vo.MsBaseBilltype;
import tp.ms.base.rest.typecoded.vo.MsBaseBilltypeExample;
import tp.ms.base.rest.typecoded.vo.PolyMsBaseBilltypeVO;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.vo.BaseExample;

@Service
public class BilltypeServiceImpl extends PolyServiceImpl<PolyMsBaseBilltypeVO> implements BilltypeService {

	@Autowired
	BilltypeMajorService majorService;
	@Autowired
	BillCodeService billCodeService;
	@Autowired
	BillTemplateService billTemplateService;

	@SuppressWarnings("unchecked")
	@Override
	public IMajorService<MsBaseBilltype, MsBaseBilltypeExample> getMajorService()
			throws ADBusinessException {
		return majorService;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <C extends ChildBaseVO, B extends BaseExample> IChildService getChildService(Class<C> clz)
			throws ADBusinessException {
		if(clz == MsBaseBillCodeRule.class)
			return billCodeService;
		if(clz == MsBaseBillTemplate.class)
			return billTemplateService;
		return null;
	}


	@Override
	public void addBeforeRule(CommonAroundProcesser<PolyMsBaseBilltypeVO> aroundProcesser) {
		aroundProcesser.addBeforeRule(new VerificationComponentTypeRelationTemplate());
		super.addBeforeRule(aroundProcesser);
	}
	


}

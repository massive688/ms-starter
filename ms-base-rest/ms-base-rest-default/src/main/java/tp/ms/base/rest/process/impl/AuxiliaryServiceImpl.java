package tp.ms.base.rest.process.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tp.ms.base.rest.ots.orgs.api.OrgsService;
import tp.ms.base.rest.ots.orgs.entity.MyAdreamOrgelements;
import tp.ms.base.rest.ots.staff.entity.MyAdreamStaffRelation;
import tp.ms.base.rest.ots.staff.entity.MyAdreamStaffRelationExample;
import tp.ms.base.rest.ots.staff.entity.MyAdreamStaffRelationExample.Criteria;
import tp.ms.base.rest.ots.staff.entity.MyAdreamStaffRelationMapper;
import tp.ms.base.rest.process.api.AuxiliaryService;
import tp.ms.base.rest.process.api.HomeWaitingMatterService;
import tp.ms.base.rest.process.api.ProcessInformationService;
import tp.ms.base.rest.process.vo.MsWorkableFlowProcessInformation;
import tp.ms.base.rest.process.vo.MyAdreamHomeWaitingMatter;
import tp.ms.base.rest.resource.vo.MajorAuditBaseVO;
import tp.ms.common.bean.exception.ADBusinessException;
import tp.ms.common.bean.support.context.BeanHelperEnv;
import tp.ms.common.bean.support.context.MsEnvContextHolder;
import tp.ms.common.data.source.annotation.TransactionalKey;

@Service
public class AuxiliaryServiceImpl implements AuxiliaryService {

	@Autowired
    private OrgsService orgsService;

	@Autowired
    private BeanHelperEnv beanHelperEnv;

	@Autowired
    private MyAdreamStaffRelationMapper staffRelationMapper;

	@Autowired
	HomeWaitingMatterService waitingMatterService;

	@Autowired
	ProcessInformationService informationService;
	
	
	
	@Transactional(value=TransactionalKey.JTA, rollbackFor= {Exception.class, ADBusinessException.class})
	@Override
	public <T extends MajorAuditBaseVO> T submitAfterAround(T vo) throws ADBusinessException {
        //得到申请人
        String proposer = MsEnvContextHolder.getContext().user().getUserName();
        //得到岗位的关联信息
        MyAdreamStaffRelationExample example = new MyAdreamStaffRelationExample();
        Criteria criteria = example.createCriteria();
        criteria.andPkUserEqualTo(MsEnvContextHolder.getContext().user().getPkUser())
        .andIsMainEqualTo((short) 0);
        MyAdreamStaffRelation relation = staffRelationMapper.selectByExample(example).get(0);
        //得到岗位信息
        MyAdreamOrgelements postInfo = orgsService.queryByKey(relation.getPkPost());
        //得到申请人岗位
        String proposerPost = postInfo.getName();
        //流程的主题信息
        String theme = vo.getName();

        MyAdreamHomeWaitingMatter waitingMatter = new MyAdreamHomeWaitingMatter();
        waitingMatter.setPkHomeRoot("1000015EHPYRYV000000000001EZ09"); //不变值
        waitingMatter.setBilltype(vo.getBilltype());
        waitingMatter.setProcessId(vo.getProcessId());
        waitingMatter.setFormContentId(vo.getPrimaryKey());
        waitingMatter.setPrimaryKey(beanHelperEnv.generateid(MsEnvContextHolder.getContext().user().getPkOrg()));
        waitingMatter.setTheme(theme);
        waitingMatter.setProposer(proposer);
        waitingMatter.setProposerPost(proposerPost);
        waitingMatter.setProcessUrl("/app/adream/activity/record/activity-app.html");
        waitingMatterService.insert(waitingMatter);
        
		return vo;
        
	}



	@Override
	public void handleAfterSubmitInformation(MsWorkableFlowProcessInformation info) throws ADBusinessException {
		informationService.insert(info);
	}



}

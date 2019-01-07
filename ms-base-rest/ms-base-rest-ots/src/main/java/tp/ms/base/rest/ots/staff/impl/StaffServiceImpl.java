package tp.ms.base.rest.ots.staff.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.val;
import lombok.extern.slf4j.Slf4j;
import tp.ms.base.rest.ots.orgs.entity.MyAdreamOrgelements;
import tp.ms.base.rest.ots.orgs.entity.MyAdreamOrgelementsExample;
import tp.ms.base.rest.ots.orgs.entity.MyAdreamOrgelementsMapper;
import tp.ms.base.rest.ots.staff.api.StaffService;
import tp.ms.base.rest.ots.staff.entity.MyAdreamStaff;
import tp.ms.base.rest.ots.staff.entity.MyAdreamStaffExample;
import tp.ms.base.rest.ots.staff.entity.MyAdreamStaffMapper;
import tp.ms.base.rest.ots.staff.entity.MyAdreamStaffRelation;
import tp.ms.base.rest.ots.staff.entity.MyAdreamStaffRelationExample;
import tp.ms.base.rest.ots.staff.entity.MyAdreamStaffRelationMapper;
import tp.ms.common.bean.support.context.BeanHelperEnv;
import tp.ms.common.bean.utils.ObjectUtilms;
import tp.ms.common.data.mybatis.annotation.SqlSessionKey;
import tp.ms.common.data.mybatis.annotation.TargetSqlSession;

@Slf4j
@Service
public class StaffServiceImpl implements StaffService{

	@Autowired
	BeanHelperEnv beanHelper;

	@Autowired
	MyAdreamStaffMapper mapper;
	@Autowired
	MyAdreamStaffRelationMapper mapperRelation;
	@Autowired
	MyAdreamOrgelementsMapper mapperOrgelements;
	
	@Override
	@TargetSqlSession(SqlSessionKey.CS6304)
	public MyAdreamStaff getStaff(String userid, Map<String, Object> result) {
		val example = new MyAdreamStaffExample();
		example.createCriteria().andUseridEqualTo(userid)
		.andDrEqualTo((short) 0);
		val vos = mapper.selectByExample(example);
		MyAdreamStaff vo = null;
		if(vos.isEmpty()) {
			if(result != null)
				vo = createMyAdreamStaff(userid, result);
			else
				return null;
		}else {
			vo = vos.get(0);
		}
		if(result == null)
			return vo;
		vo.setServerResult(result);
		//封装岗位关联
		packageStaffRelation(vo);
		//封装Cas服务用户信息
		completeAfterPackageCasInfo(vo);
		return vo;
	}
	
	private void packageStaffRelation(MyAdreamStaff vo) {
		val sr = new MyAdreamStaffRelationExample();
		MyAdreamStaffRelationExample.Criteria criteria = sr.createCriteria();
		criteria.andPkUserEqualTo(vo.getPkUser());
		List<MyAdreamStaffRelation> post = mapperRelation.selectByExample(sr);
		if(ObjectUtilms.isNotEmptyCollection(post)) {
			List<String> posts = new ArrayList<>();
			for(MyAdreamStaffRelation mr : post) {
				posts.add(mr.getPkPost());
			}
			val orgse = new MyAdreamOrgelementsExample();
			MyAdreamOrgelementsExample.Criteria orgsecri = orgse.createCriteria();
			orgsecri.andTypeEqualTo((short) 3);
			orgsecri.andPkOrgelementsIn(posts);
			List<MyAdreamOrgelements> orgses = mapperOrgelements.selectByExample(orgse);
			Map<String,Set<String>> dp = new HashMap<String,Set<String>>();
			for(MyAdreamOrgelements ose : orgses) {
				Set<String> pos;
				if(dp.containsKey(ose.getParentId())) {
					pos = dp.get(ose.getParentId());
				}else {
					pos = new HashSet<String>();
				}
				pos.add(ose.getPkOrgelements());
				dp.put(ose.getParentId(), pos);
			}
			vo.setDp(dp);
		}
	}

	/**
	 *   完成后封装Cas服务用户信息
	 * @param vo
	 */
	private void completeAfterPackageCasInfo(MyAdreamStaff vo) {
		String headPhoto = (String)vo.getServerResult().get("headimgurl");
		vo.setHeadPhoto(headPhoto == null?"https://my.adream.org/my/welcome/img/u1/e8632.jpeg":headPhoto);
	}

	private MyAdreamStaff createMyAdreamStaff(String username, Map<String, Object> result) {
		val vo = new MyAdreamStaff();
		vo.setPkUser(beanHelper.generateid());
		vo.setPkCorp("000001AA0000000000000000000B03");
		vo.setEnabled(0);
		vo.setCreator("admin");
		vo.setPkGroup("000001AAADREAM0000000000000A01");
		vo.setUserCode((String)result.get("uname"));
		vo.setNickname((String)result.get("uname"));
		vo.setUserName((String)result.get("realName"));
		vo.setUserid((String)result.get("uid"));
		vo.setRoles("4");
		vo.setServerResult(result);
		int order = mapper.insert(vo);
		val relation = new MyAdreamStaffRelation();
		relation.setPkUser(vo.getPkUser());
		relation.setPkPost("3000010014TOATFI30000000010001");
		relation.setIsMain(0);

		completeAfterPackageCasInfo(vo); //封装后信息保存到本地库
		
		order = mapperRelation.insert(relation);
		log.info("Staff and Relation Insert {}", order > 0 ? "Success" : "Fail");
		return vo;
	}

	@Override
	public MyAdreamStaff queryByKey(String pkUser) {
		return mapper.selectByPrimaryKey(pkUser);
	}

}

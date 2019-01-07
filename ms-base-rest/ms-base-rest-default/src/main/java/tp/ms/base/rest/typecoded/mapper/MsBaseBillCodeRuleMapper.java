package tp.ms.base.rest.typecoded.mapper;

import tp.ms.base.rest.typecoded.vo.MsBaseBillCodeRule;
import tp.ms.base.rest.typecoded.vo.MsBaseBillCodeRuleExample;
import tp.ms.common.data.mybatis.annotation.SqlSessionKey;
import tp.ms.common.data.mybatis.annotation.TargetSqlSession;
import tp.ms.common.data.mybatis.mapper.DaoMapper;


@TargetSqlSession(SqlSessionKey.CS6304)
public interface MsBaseBillCodeRuleMapper extends DaoMapper<MsBaseBillCodeRule, MsBaseBillCodeRuleExample> {
    
}
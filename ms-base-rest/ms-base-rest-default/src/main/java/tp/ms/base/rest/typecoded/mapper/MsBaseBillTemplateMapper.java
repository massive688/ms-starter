package tp.ms.base.rest.typecoded.mapper;

import tp.ms.base.rest.typecoded.vo.MsBaseBillTemplate;
import tp.ms.base.rest.typecoded.vo.MsBaseBillTemplateExample;
import tp.ms.common.data.mybatis.annotation.SqlSessionKey;
import tp.ms.common.data.mybatis.annotation.TargetSqlSession;
import tp.ms.common.data.mybatis.mapper.DaoMapper;

@TargetSqlSession(SqlSessionKey.CS6304)
public interface MsBaseBillTemplateMapper extends DaoMapper<MsBaseBillTemplate, MsBaseBillTemplateExample>{
}
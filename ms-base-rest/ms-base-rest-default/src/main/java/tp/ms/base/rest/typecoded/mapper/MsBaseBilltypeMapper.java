package tp.ms.base.rest.typecoded.mapper;

import tp.ms.base.rest.typecoded.vo.MsBaseBilltype;
import tp.ms.base.rest.typecoded.vo.MsBaseBilltypeExample;
import tp.ms.common.data.mybatis.annotation.SqlSessionKey;
import tp.ms.common.data.mybatis.annotation.TargetSqlSession;
import tp.ms.common.data.mybatis.mapper.DaoMapper;

@TargetSqlSession(SqlSessionKey.CS6304)
public interface MsBaseBilltypeMapper extends DaoMapper<MsBaseBilltype, MsBaseBilltypeExample> {

}
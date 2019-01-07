package tp.ms.base.rest.ots.orgs.entity;

import tp.ms.common.data.mybatis.annotation.SqlSessionKey;
import tp.ms.common.data.mybatis.annotation.TargetSqlSession;
import tp.ms.common.data.mybatis.mapper.DaoMapper;

@TargetSqlSession(SqlSessionKey.CS6304)
public interface MyAdreamOrgelementsMapper extends DaoMapper<MyAdreamOrgelements, MyAdreamOrgelementsExample> {

}
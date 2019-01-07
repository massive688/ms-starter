package tp.ms.base.rest.typecoded.mapper;

import tp.ms.base.rest.typecoded.vo.MyBillcodereturn;
import tp.ms.base.rest.typecoded.vo.MyBillcodereturnExample;
import tp.ms.common.data.mybatis.annotation.SqlSessionKey;
import tp.ms.common.data.mybatis.annotation.TargetSqlSession;
import tp.ms.common.data.mybatis.mapper.DaoMapper;


@TargetSqlSession(SqlSessionKey.CS6304)
//@Transactional(propagation=Propagation.NESTED, timeout = 5000, rollbackFor= {Exception.class, ADBusinessException.class})
public interface MyBillcodereturnMapper extends DaoMapper<MyBillcodereturn, MyBillcodereturnExample>{
  
}
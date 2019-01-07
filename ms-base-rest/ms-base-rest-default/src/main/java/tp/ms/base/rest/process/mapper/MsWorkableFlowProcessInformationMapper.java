package tp.ms.base.rest.process.mapper;

import tp.ms.base.rest.process.vo.MsWorkableFlowProcessInformation;
import tp.ms.base.rest.process.vo.MsWorkableFlowProcessInformationExample;
import tp.ms.common.data.mybatis.annotation.SqlSessionKey;
import tp.ms.common.data.mybatis.annotation.TargetSqlSession;
import tp.ms.common.data.mybatis.mapper.DaoMapper;

@TargetSqlSession(SqlSessionKey.CS6304)
public interface MsWorkableFlowProcessInformationMapper extends DaoMapper<MsWorkableFlowProcessInformation, MsWorkableFlowProcessInformationExample> {
	
}
package tp.ms.base.rest.quartz.mapper;

import java.util.List;

import tp.ms.base.rest.quartz.vo.JobTrigger;
import tp.ms.common.data.mybatis.annotation.SqlSessionKey;
import tp.ms.common.data.mybatis.annotation.TargetSqlSession;

@TargetSqlSession(SqlSessionKey.MPROCESS)
public interface JobTriggerMapper {
	public List<JobTrigger> getJobTriggerDetails();
}

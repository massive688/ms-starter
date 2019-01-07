package tp.ms.base.rest.quartz.api;


import java.util.List;

import com.github.pagehelper.PageInfo;

import tp.ms.base.rest.quartz.vo.JobTrigger;
import tp.ms.base.rest.resource.http.Pager;
import tp.ms.common.bean.exception.ADBusinessException;

public interface JobTriggerService {
	public PageInfo<JobTrigger> getJobTriggerDetails(int pageNum, int pageSize);

	public List<JobTrigger> getJobTriggerDetails(Pager pager);

	public Object jobDelete(String jobClassName, String jobGroupName) throws ADBusinessException;

	public Object addJob(String jobClassName, String jobGroupName, String cronExpression) throws ADBusinessException;

	public Object jobPause(String jobClassName, String jobGroupName) throws ADBusinessException;

	public Object jobResume(String jobClassName, String jobGroupName) throws ADBusinessException;

	public Object jobReschedule(String jobClassName, String jobGroupName, String cronExpression) throws ADBusinessException;

	public Object jobImmediately(String jobClassName, String jobGroupName) throws ADBusinessException;
}

package tp.ms.base.rest.quartz.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;
import tp.ms.base.rest.quartz.api.JobTriggerService;
import tp.ms.base.rest.quartz.mapper.JobTriggerMapper;
import tp.ms.base.rest.quartz.vo.JobTrigger;
import tp.ms.base.rest.resource.http.Pager;
import tp.ms.common.bean.exception.ADBusinessException;

@Slf4j
@Service
public class JobTriggerServiceImpl implements JobTriggerService{

	@Autowired
	private JobTriggerMapper jobTriggerMapper;

	// 加入Qulifier注解，通过名称注入bean
	@Autowired
	private SchedulerFactoryBean schedulerFactory;
	
	Scheduler scheduler;
	
	public PageInfo<JobTrigger> getJobTriggerDetails(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<JobTrigger> list = jobTriggerMapper.getJobTriggerDetails();
		PageInfo<JobTrigger> page = new PageInfo<JobTrigger>(list);
		return page;
	}

	@Override
	public List<JobTrigger> getJobTriggerDetails(Pager pager) {
		PageHelper.startPage(pager.getCurrent(), pager.getShowNum());
		List<JobTrigger> list = jobTriggerMapper.getJobTriggerDetails();
		return list;
	}

	@Override
	public Object jobDelete(String jobClassName, String jobGroupName) throws ADBusinessException {
		try {
			scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
			scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
			scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
		} catch (SchedulerException e) {
			throw new ADBusinessException(e);
		}
		return "成功";
	}

	@SuppressWarnings("unchecked")
	public static Class<? extends Job> getClass(String className) throws ClassNotFoundException {
		Class<?> clazz = Class.forName(className);
		return (Class<? extends Job>) clazz;
	}


	@PostConstruct
	public void init() {
		scheduler = schedulerFactory.getScheduler();
	}

	@Override
	public Object addJob(String jobClassName, String jobGroupName, String cronExpression) throws ADBusinessException {
		// 构建job信息
		JobDetail jobDetail;
		try {
			scheduler.start();
			jobDetail = JobBuilder.newJob(getClass(jobClassName))
					.withIdentity(jobClassName, jobGroupName).build();
			// 表达式调度构建器(即任务执行的时间)
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

			// 按新的cronExpression表达式构建一个新的trigger
			CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
					.withSchedule(scheduleBuilder).build();
			
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (Exception e) {
			log.error("创建定时任务失败" + e);
			throw new ADBusinessException("创建定时任务失败");
		}
		return "创建定时任务成功";
	}

	@Override
	public Object jobPause(String jobClassName, String jobGroupName) throws ADBusinessException {
		try {
			scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
		} catch (SchedulerException e) {
			throw new ADBusinessException(e);
		}
		return "暂停任务成功";
	}

	@Override
	public Object jobResume(String jobClassName, String jobGroupName) throws ADBusinessException {

		try {
			scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
		} catch (SchedulerException e) {
			throw new ADBusinessException(e);
		}
		return "恢复任务成功";
	}

	@Override
	public Object jobReschedule(String jobClassName, String jobGroupName, String cronExpression) throws ADBusinessException {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			
			if(trigger == null) {
				addJob(jobClassName, jobGroupName, cronExpression);
			}else {
				// 按新的cronExpression表达式重新构建trigger
				trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

				// 按新的trigger重新设置job执行
				scheduler.rescheduleJob(triggerKey, trigger);
			}
		} catch (SchedulerException e) {
			System.out.println("更新定时任务失败" + e);
			throw new ADBusinessException("更新定时任务失败");
		}
		return "更新重置定时任务成功";
	}

	@Override
	public Object jobImmediately(String jobClassName, String jobGroupName) throws ADBusinessException {
		try {
			scheduler.triggerJob(JobKey.jobKey(jobClassName, jobGroupName));
		} catch (SchedulerException e) {
			log.error(e.getMessage(), e);
			throw new ADBusinessException("手动定时任务执行失败");
		}
		return "手动定时任务执行成功";
	}
}
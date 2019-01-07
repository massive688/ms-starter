package tp.ms.base.rest.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;
import tp.ms.base.rest.quartz.api.JobTriggerService;
import tp.ms.base.rest.quartz.vo.JobTrigger;
import tp.ms.base.rest.resource.BasisResource;
import tp.ms.base.rest.resource.http.Pager;
import tp.ms.common.bean.result.Result;
import tp.ms.common.bean.result.ResultSupport;

@RestController
@RequestMapping(value = "/job")
@Slf4j
public class QuartzJobController extends BasisResource<Object>{
	
	@Autowired
	private JobTriggerService jobTriggerService;

	@PostMapping(value = "/add")
	public Result<Object> addjob(@RequestParam(value = "jobClassName") String jobClassName,
			@RequestParam(value = "jobGroupName") String jobGroupName,
			@RequestParam(value = "cronExpression") String cronExpression) throws Exception {
		log.info("add job");
		return ResultSupport.ok(jobTriggerService.addJob(jobClassName, jobGroupName, cronExpression));
	}

	@PostMapping(value = "/pause")
	public Result<Object> pausejob(@RequestParam(value = "jobClassName") String jobClassName,
			@RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
		return ResultSupport.ok(jobTriggerService.jobPause(jobClassName, jobGroupName));
	}

	@PostMapping(value = "/resume")
	public Result<Object> resumejob(@RequestParam(value = "jobClassName") String jobClassName,
			@RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
		return ResultSupport.ok(jobTriggerService.jobResume(jobClassName, jobGroupName));
	}

	@PostMapping(value = "/reschedule")
	public Result<Object> rescheduleJob(@RequestParam(value = "jobClassName") String jobClassName,
			@RequestParam(value = "jobGroupName") String jobGroupName,
			@RequestParam(value = "cronExpression") String cronExpression) throws Exception {
		return ResultSupport.ok(jobTriggerService.jobReschedule(jobClassName, jobGroupName, cronExpression));
	}

	@PostMapping(value = "/delete")
	public Result<Object> deletejob(@RequestParam(value = "jobClassName") String jobClassName,
			@RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
		return ResultSupport.ok(jobTriggerService.jobDelete(jobClassName, jobGroupName));
	}

	@PostMapping(value = "/query")
	public Result<Pager> queryJob(@RequestBody Pager pager) {
		List<JobTrigger> res = jobTriggerService.getJobTriggerDetails(pager);
		PageInfo<JobTrigger> jobTrigger = new PageInfo<JobTrigger>(res);
		return ResultSupport.ok(new Pager(jobTrigger));
	}



	@PostMapping(value = "/immediately")
	public Result<Object> immediatelyJob(@RequestParam(value = "jobClassName") String jobClassName,
			@RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
		return ResultSupport.ok(jobTriggerService.jobImmediately(jobClassName, jobGroupName));
	}
	
}

package tp.ms.base.rest.quartz.demo;

import java.util.Calendar;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;  
  
@Slf4j
public class HelloJob implements Job {  
  
    private static Logger _log = LoggerFactory.getLogger(HelloJob.class);  
     
    public HelloJob() {  
          
    }  
     
    public void execute(JobExecutionContext context)  
        throws JobExecutionException {  
        _log.error("Hello Job执行时间: " + new Date());  

		try {
			log.info(context.getScheduler().getSchedulerName());
			String jobParam = (String) context.getJobDetail().getJobDataMap().get("jobParam");
			if (jobParam != null) {
				log.info(jobParam.toString());
			}
			log.info(Integer.toString(Calendar.getInstance().get(Calendar.SECOND)));
		} catch (SchedulerException e) {
			log.error(e.getMessage(), e);
		}
    }  
}  

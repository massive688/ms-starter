package org.ms.base.rest.orgs.staff;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;

/**
 * Unit test for simple App.
 */
@Slf4j
public class AppTest extends TestCase {

//	@Test
	@SuppressWarnings("unused")
	public void testTask() {
		try {
			log.info("ass");
			String job_name = "动态任务调度";
			System.out.println("【系统启动】开始(每1秒输出一次)...");
//			JobSchedule.addJob(job_name, HelloJob.class, "0/1 * * * * ?");

//			Thread.sleep(5000);
			System.out.println("【修改时间】开始(每2秒输出一次)...");
//			JobSchedule.modifyJobTime(job_name, "10/2 * * * * ?");
//			Thread.sleep(6000);
			System.out.println("【移除定时】开始...");
//			JobSchedule.removeJob(job_name);
			System.out.println("【移除定时】成功");

			System.out.println("【再次添加定时任务】开始(每10秒输出一次)...");
//			JobSchedule.addJob(job_name, HelloJob.class, "*/10 * * * * ?");
//			Thread.sleep(60000);
			System.out.println("【移除定时】开始...");
//			JobSchedule.removeJob(job_name);
			System.out.println("【移除定时】成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

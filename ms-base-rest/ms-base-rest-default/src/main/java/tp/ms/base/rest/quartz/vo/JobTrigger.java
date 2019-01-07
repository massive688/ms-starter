package tp.ms.base.rest.quartz.vo;

import java.math.BigInteger;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobTrigger {
	String jobName;
	String jobGroup;
	String jobClassName;
	String triggerName;
	String triggerGroup;
	BigInteger REPEAT_INTERVAL;
	BigInteger TIMES_TRIGGERED;
	String cronExpression;
	String timeZoneId;
}

package tp.ms.common.batis.aspect;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Order(-90)
@Component
public class MapperExecutionDSAOPOperation {

	/*
	 * http://blog.csdn.net/yunshixin/article/details/52444049
	 * @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	 */
	@Pointcut("execution(* com.ms..*apper.select*(..))")
	public void executionPoinCut() {
	}

	@Around("executionPoinCut()")
	public Object insertProcess(ProceedingJoinPoint point) throws Throwable {
		// 用改变后的参数执行目标方法
		Object returnValue = point.proceed();
		return returnValue;
	}

	@Before("executionPoinCut()")
	public void permissionCheck(JoinPoint point) {
	}

	@AfterReturning(pointcut = "executionPoinCut()", returning = "returnValue")
	public void log(JoinPoint point, Object returnValue) {

	}

	@After("executionPoinCut()")
	public void releaseResource(JoinPoint point) {
	}

	/**
     * 拦截web层异常，记录异常日志，并返回友好信息到前端
     * 目前只拦截Exception，是否要拦截Error需再做考虑
     *
     * @param e 异常对象
     */
    @AfterThrowing(pointcut = "executionPoinCut()", throwing = "e")
    public void handleThrowing(Exception e) {
    }
    /**
     * 将内容输出到浏览器
     *
     * @param content 输出内容
     */
    @SuppressWarnings("unused")
	private void writeContent(String content) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "text/plain;charset=UTF-8");
        response.setHeader("icop-content-type", "exception");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(content);
        writer.flush();
        writer.close();
    }
}

package tp.ms.base.rest.resource.logic;
//package com.ms.core.service.rest.common.logic;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Method;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ReflectionUtils;
//import org.springframework.validation.Errors;
//import org.springframework.validation.FieldError;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ms.dbatis.spring.dynamic.annotation.TargetDataSource;
//
////@Aspect
//@Order(-95)
//@Component
//public class RequestMappingInterceptor  {
//	
//    private final static ObjectMapper jsonMapper = new ObjectMapper();  
//
////    @Around("target(com.ms.core.service.rest.common.http.BaseController1) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
//	public Object execution(ProceedingJoinPoint joinPoint) throws Throwable {
//		
//
//        //拦截的方法名
//        String methodName = joinPoint.getSignature().getName();
//        Object[] args = joinPoint.getArgs();
//        Object target = joinPoint.getTarget();
//		//通过反射获取对象注解的方法
//        Method method = ReflectionUtils.findMethod(target.getClass(), methodName);
//        //获取该方法在参数上的注解，每个参数可以有多个注解，得到的是一个二维数组
//
//        Annotation[][] parameterAnnotaions = method.getParameterAnnotations();
//        Errors errors = null;
//        for(int i = 0; i < parameterAnnotaions.length; i++){
//            Annotation[] oneParameterAnnotaions = parameterAnnotaions[i];
//             
//            for(int j = 0; j < oneParameterAnnotaions.length; j++){
//                //获取到基于正则表达式上的注解，在实际应用中可能需要根据业务对其他注解也进行相关的校验
//            	Class<? extends Annotation> patternClass = oneParameterAnnotaions[j].annotationType();
//            	
//                if( patternClass == Pattern.class){
//                	Pattern pattern = (Pattern)oneParameterAnnotaions[j];
//                    boolean isValid = false;
//                    if(args[i] != null){
//                        String regexp = pattern.regexp();
//                         
//                        java.util.regex.Pattern patt = java.util.regex.Pattern.compile(regexp);
//                         
//                        boolean matched = patt.matcher(args[i].toString()).matches();
//                         
//                        if(matched){
//                           isValid = true;
//                        }
//                    }
//					//记录字段错误
//                    if(!isValid){
//                       FieldError objectError = new FieldError("", pattern.message(), methodName);
//                       errors.getAllErrors().add(objectError);
//                    }
//                }
//            }
//        }
//        //把字段校验错误传个接口的参数
//        args[args.length-1] = errors;
//        Object result = joinPoint.proceed(args);
//        
//        // 随意处理结果吧
//        System.out.println("After: result: "+ jsonMapper.writeValueAsString(result));  
//
//        return result;
//	}
//
//}
//
//

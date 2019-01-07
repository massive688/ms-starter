package tp.ms.common.bean.support.context;

import org.springframework.context.ApplicationContext;

public class SpringContextHolder {// implements ApplicationContextAware{
    
   /*  
     * 以静态变量保存ApplicationContext,可在任意代码中取出ApplicaitonContext.  
     */    
   private static ApplicationContext context;    
   
  /* 
    * 	实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.     
    * @param context 传入的spring的上下文
    */
   public static void setApplicationContext(ApplicationContext context) {    
       SpringContextHolder.context =context;
   }
   
   public static ApplicationContext getApplicationContext() {    
       return context;    
   }    
  /*
    * 
    * 	 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.     
    * @param name 需要获取的类名称
    * @return 返回类的实例
    */
   public static Object getBean(String name) {    
       return context.getBean(name);    
   } 
   
   public static <T> T getBean(Class<T> clazz) {    
       return (T) context.getBean(clazz);    
   }
   
}

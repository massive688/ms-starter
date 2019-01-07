package tp.ms.common.batis.session;

import java.util.ArrayList;
import java.util.List;


public abstract class MsSessionTemplateHolder {
 
//    public final static String SESSION_FACTORY_MYSQL = "mysql";
//    public final static String SESSION_FACTORY_ORACLE = "oracle";
   /*
     * 存储已经注册的模板的key
     */
    public static List<String> sessionKeys =new ArrayList<>();
    
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  
    
    public static void setSessionKey(String contextType) {  
        contextHolder.set(contextType);  
    }  
      
    public static String getSessionKey() {  
        return contextHolder.get();  
    }  
      
    public static void clearSessionKey() {  
        contextHolder.remove();  
    }  
    
    public static boolean containsSessionKey(String sessionKey){
        return sessionKeys.contains(sessionKey);
    }
}
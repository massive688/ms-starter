package tp.ms.common.data.mybatis.config;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>function:</b> 多数据源
 * @author hoojo
 * @createDate 2013-9-27 上午11:36:57
 * @file CustomerContextHolder.java
 * @package com.hoo.framework.spring.support
 * @project SHMB
 * @blog http://blog.csdn.net/IBM_hoojo
 * @email hoojo_@126.com
 * @version 1.0
 */
public abstract class MsSessionTemplateHolder {
 
//    public final static String SESSION_FACTORY_MYSQL = "mysql";
//    public final static String SESSION_FACTORY_ORACLE = "oracle";
    /**
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
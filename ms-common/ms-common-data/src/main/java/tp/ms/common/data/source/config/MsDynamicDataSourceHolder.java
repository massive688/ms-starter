package tp.ms.common.data.source.config;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: yukong
 * @Date: 2018/8/15 10:49
 * @Description: 数据源上下文
 */
public class MsDynamicDataSourceHolder {

    /**
     * 存储已经注册的数据源的key
     */
    public static List<String> dataSourceKeys =new ArrayList<>();

    /**
     * 线程级别的私有变量
     */
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();


    /**
     * 取得当前数据源。
     *
     * @return
     */
    public static String getDataSourceRouterKey () {
        String str = contextHolder.get();
        return str;
    }

    /**
     * 设置当前数据库。
     *
     * @param dbType
     */
    public static void setDataSourceRouterKey (String dataSourceRouterKey) {
        contextHolder.set(dataSourceRouterKey);
    }

    /**
     * 设置数据源之前一定要先移除
     * 清除上下文数据
     */
    public static void removeDataSourceRouterKey() {
    	contextHolder.remove();
    }

    /**
     *
     * @param dataSourceId
     * @return
     */
    public static boolean containsDataSource(String dataSourceId){
        return dataSourceKeys.contains(dataSourceId);
    }
}
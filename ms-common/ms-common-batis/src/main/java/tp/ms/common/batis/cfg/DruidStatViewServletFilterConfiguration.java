package tp.ms.common.batis.cfg;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
  
/**
 * druid数据源状态监控.
 * @author Administrator
 *
 */
  
@Configuration
public class DruidStatViewServletFilterConfiguration {
	
    @Bean
    public ServletRegistrationBean<StatViewServlet> statViewServletRegistrationBean() {
        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<StatViewServlet>();
        registrationBean.setServlet(new StatViewServlet());
        registrationBean.addUrlMappings("/druid/*");
        registrationBean.addInitParameter("allow", "192.168.4.168,127.0.0.1,localhost");// IP白名单(没有配置或者为空，则允许所有访问)
        registrationBean.addInitParameter("deny", "192.168.1.73");// IP黑名单 (存在共同时，deny优先于allow)
        registrationBean.addInitParameter("loginUsername", "admin");// 用户名
        registrationBean.addInitParameter("loginPassword", "123456");// 密码
        registrationBean.addInitParameter("resetEnable", "false");// 禁用HTML页面上的“Reset All”功能
        return registrationBean;
    }
    
    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilterRegistrationBean() {
        FilterRegistrationBean<WebStatFilter> registrationBean = new FilterRegistrationBean<WebStatFilter>();
        WebStatFilter filter = new WebStatFilter();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico, /druid/*");
        return registrationBean;
    }
}

/**
 * 
 * 
 * https://blog.csdn.net/liuchuanhong1/article/details/78106648
 * 自定义属性文件配置
 * 
 *
 * # 数据库访问配置
# 主数据源，默认的
# 下面为连接池的补充设置，应用到上面所有数据源中
# 初始化大小，最小，最大 
# 配置获取连接等待超时的时间
# 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
# 配置一个连接在池中最小生存的时间，单位是毫秒
# 打开PSCache，并且指定每个连接上PSCache的大小
# 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
# 通过connectProperties属性来打开mergeSql功能；慢SQL记录
# 合并多个DruidDataSource的监控数据
#useGlobalDataSourceStat:true
8*/
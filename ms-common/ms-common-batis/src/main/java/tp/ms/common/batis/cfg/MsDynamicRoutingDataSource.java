package tp.ms.common.batis.cfg;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: yukong
 * @Date: 2018/8/15 10:47
 * @Description: 动态数据源路由配置
 */
@Slf4j
public class MsDynamicRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceName = MsDynamicDataSourceContextHolder.getDataSourceRouterKey();
        log.info("当前数据源是：{}", dataSourceName);
        return MsDynamicDataSourceContextHolder.getDataSourceRouterKey();
    }
}
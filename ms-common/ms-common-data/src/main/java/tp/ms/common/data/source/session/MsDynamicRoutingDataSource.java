package tp.ms.common.data.source.session;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import lombok.extern.slf4j.Slf4j;
import tp.ms.common.data.source.config.MsDynamicDataSourceHolder;

/**
 * @Auther: yukong
 * @Date: 2018/8/15 10:47
 * @Description: 动态数据源路由配置
 */
@Slf4j
public class MsDynamicRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceName = MsDynamicDataSourceHolder.getDataSourceRouterKey();
        log.info("当前数据源是：{}", dataSourceName);
        return MsDynamicDataSourceHolder.getDataSourceRouterKey();
    }
}
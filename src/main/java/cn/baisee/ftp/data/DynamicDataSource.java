package cn.baisee.ftp.data;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;
/**
 * @author xiaoliu
 * @ClassName DynamicDataSource
 * @date 2021/12/10 16:33
 */

public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 设置默认属性
     *
     * @param defaultTargetDataSource 默认数据源
     * @param targetDataSources       用来存放要切换的数据源
     */
    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        // afterPropertiesSet()方法调用时用来将targetDataSources的属性写入resolvedDataSources中的
        super.afterPropertiesSet();
    }

    /**
     * 用于决定用哪个数据库
     *
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {

        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}

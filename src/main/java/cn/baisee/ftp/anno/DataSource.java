package cn.baisee.ftp.anno;


import cn.baisee.ftp.data.DataSourceType;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {


    /**
     * 切换数据源名称
     *
     * @return
     */
    DataSourceType value() default DataSourceType.MASTER;
}

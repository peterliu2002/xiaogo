package cn.baisee.ftp.aop;

/**
 * @author xiaoliu
 * @ClassName DataSourceAspect
 * @date 2021/12/10 16:40
 */
import cn.baisee.ftp.anno.DataSource;
import cn.baisee.ftp.data.DynamicDataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component

public class DataSourceAspect {

    /**
     * 定义切点
     */
    @Pointcut("@annotation(cn.baisee.ftp.anno.DataSource)")
    public void dsPointCut() {
    }

    /**
     * 环绕通知，设置数据源
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        // 获取方法上的注解
        DataSource dataSource = method.getAnnotation(DataSource.class);


        if (dataSource != null) {
            DynamicDataSourceContextHolder.setDataSourceType(dataSource.value().name());
        }
        try {
            return point.proceed();
        } finally {
            // 移除当前线程存储的 Value 值。当 ThreadLocal 不在使用，最好在 finally 语句块中，调用 remove() 方法，释放去 Value 的引用，避免内存泄露。
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
    }
}

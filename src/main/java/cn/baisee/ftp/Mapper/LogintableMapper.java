package cn.baisee.ftp.Mapper;

import cn.baisee.ftp.anno.DataSource;
import cn.baisee.ftp.data.DataSourceType;
import cn.baisee.ftp.entity.Logintable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xiaoliu
 * @ClassName LogintableMapper
 * @date 2021/12/26 13:32
 */
@Mapper

public interface LogintableMapper {

    List<Logintable> selectall();

    Logintable selectone(Integer lid);
    @Select("select * from logintable where #{account}")
    Logintable selectone(String account);



}

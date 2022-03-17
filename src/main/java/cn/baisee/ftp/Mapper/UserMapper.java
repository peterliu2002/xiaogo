package cn.baisee.ftp.Mapper;

import cn.baisee.ftp.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user")
     List<User> selectAll();

}

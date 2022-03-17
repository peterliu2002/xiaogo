package cn.baisee.ftp.Mapper;

import cn.baisee.ftp.entity.Goods;
import cn.baisee.ftp.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xiaoliu
 * @ClassName GoodsMapper
 * @date 2021/12/11 9:11
 */
@Mapper
public interface GoodsMapper {
    @Select("select * from goods")
     List<Goods> selectAll();
}

package cn.baisee.ftp.service.impl;

import cn.baisee.ftp.Mapper.GoodsMapper;
import cn.baisee.ftp.entity.Goods;
import cn.baisee.ftp.service.GoodsSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaoliu
 * @ClassName GoodsServiceImpl
 * @date 2021/12/11 9:17
 */
@Service
public class GoodsServiceImpl implements GoodsSerivce {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> selectAll() {
        return goodsMapper.selectAll();
    }
}

package cn.baisee.ftp.service;

import cn.baisee.ftp.entity.Goods;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GoodsSerivce {


     List<Goods> selectAll();
}

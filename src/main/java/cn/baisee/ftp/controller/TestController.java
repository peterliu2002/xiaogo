package cn.baisee.ftp.controller;

import cn.baisee.ftp.anno.DataSource;
import cn.baisee.ftp.data.DataSourceType;
import cn.baisee.ftp.entity.Goods;
import cn.baisee.ftp.entity.User;
import cn.baisee.ftp.service.GoodsSerivce;
import cn.baisee.ftp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author xiaoliu
 * @ClassName TestController
 * @date 2021/12/11 9:10
 */
@Controller
public class TestController {

    @Autowired
    private UserService userService;
    @Autowired
    private GoodsSerivce goodsSerivce;

    @RequestMapping("/user")
    @DataSource
    @ResponseBody
    public String a(){

        List<User> users = userService.selectAll();
        users.forEach(System.out::println);
        return "user";
    }
    @RequestMapping("/goods")
    @DataSource(DataSourceType.SLAVE)
    @ResponseBody
    public String b(){

        List<Goods> goods = goodsSerivce.selectAll();
        goods.forEach(System.out::println);
        return "goods";
    }





}

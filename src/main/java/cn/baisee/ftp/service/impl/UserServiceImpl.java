package cn.baisee.ftp.service.impl;

import cn.baisee.ftp.Mapper.UserMapper;
import cn.baisee.ftp.entity.User;
import cn.baisee.ftp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaoliu
 * @ClassName UserServiceImpl
 * @date 2021/12/11 9:15
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }
}

package cn.baisee.ftp.service;

import cn.baisee.ftp.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

     List<User> selectAll();
}

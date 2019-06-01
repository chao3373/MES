package com.shenke.serviceImpl;

import com.shenke.entity.User;
import com.shenke.repository.UserRepository;
import com.shenke.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Auther: Administrator
 * @Date: 2019/6/1 09:44
 * @Description:
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}

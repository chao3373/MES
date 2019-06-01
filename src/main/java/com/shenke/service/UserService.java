package com.shenke.service;

import com.shenke.entity.User;

/**
 * @Auther: Administrator
 * @Date: 2019/6/1 09:44
 * @Description:
 */
public interface UserService {

    User findByUserName(String userName);
}

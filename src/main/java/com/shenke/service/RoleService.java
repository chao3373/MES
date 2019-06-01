package com.shenke.service;

import com.shenke.entity.Role;

import java.util.List;
import java.util.Optional;

/**
 * @Auther: Administrator
 * @Date: 2019/6/1 11:36
 * @Description:
 */
public interface RoleService {

    List<Role> findByUserId(Integer id);

    Role findById(Integer roleId);
}

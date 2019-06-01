package com.shenke.service;

import com.shenke.entity.Menu;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2019/6/1 11:50
 * @Description:
 */
public interface MenuService {

    List<Menu> findByParentIdAndRoleId(Integer parentId, Integer roleId);
}

package com.shenke.repository;

import com.shenke.entity.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Auther: Administrator
 * @Date: 2019/6/1 11:58
 * @Description:
 */
public interface RoleMenuRepository extends JpaRepository<RoleMenu, Integer>, JpaSpecificationExecutor<RoleMenu> {

}

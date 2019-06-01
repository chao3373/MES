package com.shenke.repository;

import com.shenke.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2019/6/1 11:52
 * @Description:
 */
public interface MenuRepository extends JpaRepository<Menu, Integer>, JpaSpecificationExecutor<Menu> {

    @Query(value = "select * from t_menu where p_id = ?1 and id in(select menu_id from t_role_menu where role_id =?2)", nativeQuery = true)
    List<Menu> findByParentIdAndRoleId(Integer parentId, Integer roleId);

    @Query(value = "select * from t_ment where role_id = ?1", nativeQuery = true)
    List<Menu> findByRoleId(Integer id);
}

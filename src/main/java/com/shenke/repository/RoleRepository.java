package com.shenke.repository;

import com.shenke.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
 import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2019/6/1 11:38
 * @Description:
 */
public interface RoleRepository extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<Role> {

    @Query(value = "select r.* from t_user u, t_role r, t_user_role ur where ur.user_id = u.id and ur.role_id = r.id = ?1", nativeQuery = true)
    List<Role> findByUserId(Integer id);

}

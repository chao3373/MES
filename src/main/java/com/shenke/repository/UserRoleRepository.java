package com.shenke.repository;

import com.shenke.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Auther: Administrator
 * @Date: 2019/6/1 11:54
 * @Description:
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Integer>, JpaSpecificationExecutor<UserRole> {

    /**
     * 根据userId删除
     * @param userId
     */
    @Modifying
    @Query(value = "delete from t_user_role where user_id = ?1",nativeQuery = true)
    public void deleteByUserId(Integer userId);
}

package com.shenke.repository;


import com.shenke.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @Auther: Administrator
 * @Date: 2019/6/1 09:46
 * @Description:
 */
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    @Query(value = "select * from t_user where user_name = ?1", nativeQuery = true)
    User findByUserName(String userName);
}

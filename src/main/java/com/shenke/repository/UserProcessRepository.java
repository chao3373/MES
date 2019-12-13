package com.shenke.repository;


import com.shenke.entity.User;
import com.shenke.entity.UserProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserProcessRepository extends JpaRepository<UserProcess,Integer> , JpaSpecificationExecutor<UserProcess> {

    /**
     * 根据UserId删除
     * @param id
     */
    @Modifying
    @Query(value = "delete from t_user_process where user_id =?1",nativeQuery = true)
    public void deleteByUserId(Integer id);

    /**
     * 根据用户ID查找
     * @param id
     * @return
     */
    @Query(value = "select * from  t_user_process where  user_id =?1",nativeQuery = true)
    public List<UserProcess> findByUserId(Integer id);


    @Query(value = "select * from t_user_process where user_id = ?2 and process_id like ?1",nativeQuery = true)
    List<UserProcess> processListByUser(String s, Integer id);

    /**
     * 通过工序id删除
     * @param ids
     */
    @Modifying
    @Query(value = "delete from t_user_process where process_id in ?1",nativeQuery = true)
    void deleteByProcessIds(Integer[] ids);
}

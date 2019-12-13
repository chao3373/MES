package com.shenke.repository;

import com.shenke.entity.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;


public interface UserProductRespository extends JpaRepository<UserProduct,Integer> , JpaSpecificationExecutor<UserProduct> {

    /**
     * 根据userId删除
     * @param id
     */
    @Modifying
    @Query(value = "delete from  t_user_product where user_id = ?1",nativeQuery = true)
    public void delectByUserId(Integer id);


    /**
     * 根据工序id删除
     * @param ids
     */
    @Modifying
    @Query(value = "delete from t_user_product where process_id in ?1",nativeQuery = true)
    void deleteByProcessIds(Integer[] ids);
}

package com.shenke.repository;

import com.shenke.entity.YuanLiaoRequire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface YuanLiaoRequireRepository extends JpaRepository<YuanLiaoRequire,Integer> , JpaSpecificationExecutor<YuanLiaoRequire> {

    /**
     * 通过订单id查询
     * @param id
     * @return
     */
    @Query(value = "select * from  t_yuan_liao_require where sale_list_id = ?1",nativeQuery = true)
    List<YuanLiaoRequire> findBySaleListId(Integer id);

    /**
     * 通过订单编号删除
     * @param id
     */
    @Modifying
    @Query(value = "delete from t_yuan_liao_require where sale_list_id = ?1",nativeQuery = true)
    void deleteBySaleListId(Integer id);

    /**
     * 通过物料id删除
     * @param id
     */
    @Modifying
    @Query(value = "delete from t_yuan_liao_require where wuliao_id = ?1",nativeQuery = true)
    void deleteByWuliaoId(Integer id);
}

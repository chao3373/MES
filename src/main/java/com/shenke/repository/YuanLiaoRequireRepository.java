package com.shenke.repository;

import com.shenke.entity.YuanLiaoRequire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
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
}

package com.shenke.repository;

import com.shenke.entity.Wuliao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WuliaoRepository extends JpaRepository<Wuliao,Integer> , JpaSpecificationExecutor<Wuliao> {


    /**
     * 根据大图ID查找
     * @param id
     * @return
     */
    @Query(value = "select * from t_wuliao where big_drawing_id =?1",nativeQuery = true)
    public List<Wuliao> findByBigDrawingId(Integer id);

    /**
     * 根据订单Id查询
     * @param saleListId
     * @return
     */
    @Query(value = "select * from  t_wuliao where sale_list_id = ?1",nativeQuery = true)
    List<Wuliao> findBySaleListId(Integer saleListId);
}

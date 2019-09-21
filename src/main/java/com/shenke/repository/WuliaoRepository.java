package com.shenke.repository;

import com.shenke.entity.Wuliao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface WuliaoRepository extends JpaRepository<Wuliao,Integer> , JpaSpecificationExecutor<Wuliao> {


    /**
     * 根据大图ID查找
     * @param id
     * @return
     */
    @Query(value = "select * from t_wuliao where big_drawing_id =?1",nativeQuery = true)
    public Wuliao findByBigDrawingId(Integer id);
}

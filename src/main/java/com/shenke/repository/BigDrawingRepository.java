package com.shenke.repository;

import com.shenke.entity.BigDrawing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BigDrawingRepository extends JpaRepository<BigDrawing, Integer>, JpaSpecificationExecutor<BigDrawing>{

    @Query( value = "select * from t_big_drawing where name like ?1" ,nativeQuery =true)
    public List<BigDrawing> conboList(String q);


    /**
     * 根据名称查询
     * @return
     */
    @Query(value = "select * from t_big_drawing where drawing_id =?1 group by drawing_id" , nativeQuery = true)
    public BigDrawing findDrawingId(String DrawingId);



    @Query(value = "select id from  t_big_drawing where drawing_id =?1",nativeQuery = true)
    public Integer findIdByDrawingId(String drawingId);


    /**
     * 根据物料号查询大图信息
     * @param wuliaoId
     * @return
     */
    @Query(value = "select * from t_big_drawing where wuliao_Id =?1",nativeQuery = true)
    public BigDrawing findByWuLiaoId(String wuliaoId);

    /**
     * 根据物料号模糊查询大图信息
     * @param s
     * @return
     */
    @Query(value = "select * from t_big_drawing where wuliao_id like ?1",nativeQuery = true)
    List<BigDrawing> findLikeWuliaoId(String s);

    /**
     * 修改展开工时
     * @param time
     * @param id
     */
    @Modifying
    @Query(value = "update t_big_drawing set yu_gu_gong_shi = ?1 where id = ?2 ",nativeQuery = true)
    void updateTime(Double time,Integer id);
}
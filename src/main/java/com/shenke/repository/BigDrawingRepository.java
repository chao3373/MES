package com.shenke.repository;

import com.shenke.entity.BigDrawing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
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


    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Query(value = "select * from t_big_drawing where id =?1",nativeQuery = true)
    public BigDrawing findByaId(Integer id);

    @Query(value = "select id from  t_big_drawing where drawing_id =?1",nativeQuery = true)
    public Integer findIdByDrawingId(String drawingId);
}
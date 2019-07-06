package com.shenke.repository;

import com.shenke.entity.Drawing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DrawingRepository extends JpaRepository<Drawing, Integer>, JpaSpecificationExecutor<Drawing>{

    @Query( value = "select * from t_drawing where name like ?1" ,nativeQuery =true)
    public List<Drawing> conboList(String q);


    /**
     * 根据名称查询
     * @return
     */
    @Query(value = "select * from t_drawing where drawing_id =?1 group by drawing_id" , nativeQuery = true)
    public Drawing findDrawingId(String DrawingId);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Query(value = "select * from t_drawing where drawing_id =?1",nativeQuery = true)
    public Drawing findByaId(Integer id);
}
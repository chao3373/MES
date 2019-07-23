package com.shenke.repository;

import com.shenke.entity.DrawingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DrawingTypeRespository extends JpaRepository<DrawingType,Integer>, JpaSpecificationExecutor<DrawingType> {


    /**
     * 根据大图id来查询小图id
     * @param id
     * @return
     */
    @Query(value = "select * from t_drawing_type where big_drawing_id =?1",nativeQuery = true)
    public List<DrawingType> findByBigDrawingId(Integer id);


}

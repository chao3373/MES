package com.shenke.repository;

import com.shenke.entity.DrawingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DrawingTypeRespository extends JpaRepository<DrawingType,Integer>, JpaSpecificationExecutor<DrawingType> {


    /**
     * 根据大图id来查询小图id
     * @param id
     * @return
     */
    @Query(value = "select * from t_drawing_type where sale_list_id =?1",nativeQuery = true)
    public List<DrawingType> findBySaleListId(Integer id);

    /**
     * 根据id修改状态
     * @param id
     * @param state
     */
    @Modifying
    @Query(value = "update t_drawing_type set state =?2 where id =?1",nativeQuery = true)
    public void setState(Integer id,String state);


}

package com.shenke.repository;

import com.shenke.entity.BigDrawingProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.ManyToOne;
import java.util.List;

public interface BigDrawingProcessRepository extends JpaRepository<BigDrawingProcess,Integer> , JpaSpecificationExecutor<BigDrawingProcess> {

    @Modifying
    @Query(value = "delete from t_big_drawing_process where big_drawing_id = ?1",nativeQuery = true)
    void deleteByDrawingId(Integer drawingId);

    @Query(value = "select * from t_big_drawing_process where big_drawing_id = ?1",nativeQuery = true)
    List<BigDrawingProcess> findByBigDrawingId(Integer id);

    /**
     * 通过工序id删除
     * @param ids
     */
    @Modifying
    @Query(value = "delete from t_big_drawing_process where process_id in ?1",nativeQuery = true)
    void deleteByProcessIds(Integer[] ids);
}

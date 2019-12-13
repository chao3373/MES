package com.shenke.repository;

import com.shenke.entity.DrawingProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DrawingProcessRepository extends JpaRepository<DrawingProcess,Integer>, JpaSpecificationExecutor<DrawingProcess> {


    /**
     * 根据小图纸id删除所属工序信息
     * @param drawingId
     */
    @Modifying
    @Query(value = "delete from t_drawing_process where  drawing_id =?1 ",nativeQuery = true)
    public void deleteByDrawingId(Integer drawingId);

    /**
     * 根据小图纸号查询工序
     * @param id
     * @return
     */
    @Query(value = "select * from  t_drawing_process where  drawing_id =?1",nativeQuery = true)
    public List<DrawingProcess> findByDrawingId(Integer id);


    @Query(value = "select * from  t_drawing_process where  drawing_id in ?1 order by drawing_id",nativeQuery = true)
    public List<DrawingProcess> findByArr(Integer[] a);

    /**
     * 通过工序id删除
     * @param ids
     */
    @Modifying
    @Query(value = "delete from t_drawing_process where process_id in ?1",nativeQuery = true)
    void deleteByProcessIds(Integer[] ids);
}

package com.shenke.repository;

import com.shenke.entity.DrawingProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DrawingProcessRepository extends JpaRepository<DrawingProcess,Integer>, JpaSpecificationExecutor<DrawingProcess> {

    /**
     * 查询状态为任务下发的信息
     * @return
     */
    @Query(value = "select * from (select * from t_drawing_process where state = '任务下发' ORDER BY sale_list_id, inform_num, drawing_id, process_id) a GROUP BY a.inform_num",nativeQuery = true)
    public List<DrawingProcess> findProcessIssue();


    /**
     * 按照工序查找
     * @param id
     * @return
     */
    @Query(value = "SELECT * FROM t_drawing_process WHERE process_id =?1",nativeQuery = true)
    public List<DrawingProcess> findByProcess(Integer id);

    /**
     * 更新完成数量
     * @param accomplishNum
     */
    @Modifying
    @Query(value = "update t_drawing_process set accomplish_num =?1 where id =?2",nativeQuery = true)
    public void updateAccomplishNum(Integer accomplishNum,Integer id);


    @Modifying
    @Query(value = "update t_drawing_process set state = '生产完成' where id =?1",nativeQuery = true)
    public void setState(Integer id);


    /**
     * 查找同一单号下未生产的最小工序
     * @param informNum
     * @return
     */
    @Query(value = "SELECT MIN(process_id) FROM t_drawing_process WHERE inform_num =?1 AND state = '任务下发'",nativeQuery = true)
    public Integer findMinProcess(String informNum);

    /**
     * 获取当天最大的生产通知单号
     * @return
     */
    @Query(value = "SELECT MAX(inform_Num) FROM t_drawing_process ", nativeQuery = true)
    public String getTodayMaxPurchaseNumber();

    /**
     * 按照通知单号查找
     * @param informNum
     * @return
     */
    @Query(value = "select * from t_drawing_process where inform_num =?1",nativeQuery = true)
    public List<DrawingProcess> findByInformNum(String informNum);


    /**
     * 根据销售单号Id查询
     * @param saleListId
     * @return
     */
    @Query(value = "select * from t_drawing_process where sale_list_id =?1",nativeQuery = true)
    public List<DrawingProcess> findBySaleListId(Integer saleListId);

    /**
     * 按照通知单号修改状态
     * @param informNum
     */
    @Modifying
    @Query(value = "update t_drawing_process set state = '准备入库' where inform_num =?1",nativeQuery = true)
    public void updateStateByInformNum(String informNum);

}

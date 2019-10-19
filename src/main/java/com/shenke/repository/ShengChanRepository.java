package com.shenke.repository;

import com.shenke.entity.ShengChan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShengChanRepository extends JpaRepository<ShengChan,Integer> , JpaSpecificationExecutor<ShengChan> {

    /**
     * "任务下发"界面显示的信息
     * @return
     */
    @Query(value = "select * from  t_sheng_chan where state = '任务下发' group by datu_code",nativeQuery = true)
    public List<ShengChan> listProduct();

    /**
     * "工序加工" 界面显示
     * @param arr
     * @return
     */
    @Query(value = "select * from  t_sheng_chan where state = '任务下发' and process_id in ?1 order by refer_date asc",nativeQuery = true)
    public List<ShengChan> showInProcessProduct(Integer[] arr);


    /**
     * 查询前一工序的完成数量
     * @param xiaotuCode
     * @param i
     * @return
     */
    @Query(value = "select accomplish_num from t_sheng_chan where xiaotu_code =?1 and code =?2",nativeQuery = true)
    public Integer selectBeforeProcess(String xiaotuCode, int i);


    /**
     * 更新完成数量
     * @param id
     * @param num
     */
    @Modifying
    @Query(value = "update t_sheng_chan set accomplish_num =?2 where id =?1",nativeQuery = true)
    public void updateAccomplishNum(Integer id, Integer num);

    /**
     * 根据大图物料号查询
     * @param code
     * @return
     */
    @Query(value = "select * from t_sheng_chan where big_drawing_id = (select id from t_big_drawing where wuliao_id = ?1) group by big_drawing_id",nativeQuery = true)
    public List<ShengChan> selectByWuliao(String code);

    /**
     * 根据小图物料号查询
     * @param code
     * @return
     */
    @Query(value = "select * from t_sheng_chan where drawing_id = (select id from t__drawing where wuliao_id = ?1) group by drawing_id",nativeQuery = true)
    public List<ShengChan> selectByTuzhi(String code);

    /**
     * 查找同一小图编号下的最大生产排序序号
     * @param xiaotuCode
     * @return
     */
    @Query(value = "SELECT MAX(code) FROM t_sheng_chan WHERE xiaotu_code = ?1 ",nativeQuery = true)
    public Integer findMaxCode(String xiaotuCode);

    /**
     * 根据id修改状态
     * @param id
     */
    @Modifying
    @Query(value = "update t_sheng_chan set state = '生产完成' where id = ?1",nativeQuery = true)
    public void updatState(Integer id);

    /**
     * 按照订单编号查找
     * @param
     * @return
     */
    @Query(value = "select * from t_sheng_chan where sale_list_id =?1 and is_datu = 1 GROUP BY xiaotu_code",nativeQuery = true)
    List<ShengChan> findBySaleListId(Integer id);
}

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
    @Query(value = "SELECT * FROM t_sheng_chan GROUP BY sale_list_id,biaoqian_code desc",nativeQuery = true)
    public List<ShengChan> listProduct();

    /**
     * "工序加工" 界面显示
     * @param arr
     * @return
     */
    @Query(value = "select * from  t_sheng_chan where state = '未生产' and process_id in ?1 order by refer_date asc",nativeQuery = true)
    public List<ShengChan> showInProcessProduct(Integer[] arr);


    /**
     * 查询前一工序的完成数量
     * @param biaoqianCode
     * @param i
     * @return
     */
    @Query(value = "select accomplish_num from t_sheng_chan where biaoqian_code =?1 and code =?2",nativeQuery = true)
    public Integer selectBeforeProcess(String biaoqianCode, int i);


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
    @Query(value = "update t_sheng_chan set state = ?2 where id = ?1",nativeQuery = true)
    public void updatState(Integer id,String state);

    /**
     * 按照订单编号查找
     * @param
     * @return
     */
    @Query(value = "select * from t_sheng_chan where sale_list_id =?1 and is_datu = 1 GROUP BY xiaotu_code",nativeQuery = true)
    List<ShengChan> findBySaleListId(Integer id);

    /**
     * 查找该订单id下所有工序中完成数量最少的工序
     * @param id
     * @return
     */
    @Query(value = "select min(accomplish_num) from t_sheng_chan where sale_list_id = ?1",nativeQuery = true)
    Integer findMinAccomplishNumBySaleListId(Integer id);

    /**
     * 通过状态查找
     * @param state
     * @return
     */
    @Query(value = "select * from t_sheng_chan where state = ?1",nativeQuery = true)
    List<ShengChan> findByState(String state);


    /**
     * 通过工序状态判断当前登录员工是否有未工作的工序
     * @param s
     * @return
     */
    @Query(value = "select * from t_sheng_chan where state like ?1",nativeQuery = true)
    List<ShengChan> findByUserForState(String s);

    /**
     * 通过salelistId 查询大图生产情况
     * @param id
     * @return
     */
    @Query(value = "select * from (select * from t_sheng_chan where sale_list_id = ?1 and is_datu = 0) as a where state ='不可生产'",nativeQuery = true)
    List<ShengChan> findBySaleListIdAboutDatu(Integer id);

    /**
     * 通过salelistId 查询小图生产情况
     * @param id
     * @return
     */
    @Query(value = "select * from (select * from t_sheng_chan where sale_list_id = ?1 and is_datu = 1) as b where state = '未生产' or state like '%生产中%'",nativeQuery = true)
    List<ShengChan> findBySaleListIdAboutXiaotu(Integer id);

    /**
     * 通过salelistId修改大图的状态  （从“不可生产”---> “未生产”）
     * @param saleListId
     */
    @Modifying
    @Query(value = "update t_sheng_chan set state = '未生产' where sale_list_id = ?1 and is_datu = 0",nativeQuery = true)
    void updateDatuState(Integer saleListId);

    /**
     * 通过工序id删除
     * @param ids
     */
    @Modifying
    @Query(value = "delete from t_sheng_chan where process_id in ?1",nativeQuery = true)
    void deleteByProcessIds(Integer[] ids);

    /**
     * 销售订单追踪界面根据saleList 的 id 查找该id下所有的工序信息
     * @param id
     * @return
     */
    @Query(value = "select * from  t_sheng_chan where sale_list_id = ?1",nativeQuery = true)
    List<ShengChan> findBySaleList(Integer id);
}

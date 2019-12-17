package com.shenke.repository;

import com.shenke.entity.SaleList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface SaleListRepository  extends JpaRepository<SaleList, Integer>, JpaSpecificationExecutor<SaleList> {


    /**
     * 查询状态为下单的产品信息
     * @return
     */
    @Query(value = "select * from t_sale_list where state = '下单' and cunzai = '存在'",nativeQuery = true)
    public List<SaleList> xiadan();

    /**
     * 根据id修改存在状态
     * @param id
     */
    @Modifying
    @Query(value = "update t_sale_list set cunzai = '存在' where id =?1", nativeQuery = true)
    public void addCunZai(Integer id);


    /**
     * 设置订单状态
     * @param id
     * @param state
     */
    @Modifying
    @Query(value = "update  t_sale_list set state =?2 where id =?1",nativeQuery = true)
    public void setState(Integer id ,String state);


    /**
     * 按照状态查询订单信息
     * @param state
     * @return
     */
    @Query(value = "select * from t_sale_list where state =?1 and remark >= 0 order by remark DESC",nativeQuery = true)
    public List<SaleList> findByState(String state);

    /**
     * 设置展开单号
     * @param id
     * @param openNum
     */
    @Modifying
    @Query(value = "update t_sale_list set open_num =?2 where id =?1 ",nativeQuery = true)
    public void setOpenNum(Integer id,String openNum);

    /**
     * 获取最大展开单号
     * @return
     */
    @Query(value = "SELECT MAX(open_num) FROM t_sale_list", nativeQuery = true)
    public String getMaxOpenNum();


    /**
     * 获取当天最大的销售单号
     * @return
     */
    @Query(value = "SELECT MAX(sale_number) FROM t_sale_list WHERE TO_DAYS(sale_date)=TO_DAYS(NOW())", nativeQuery = true)
    public String getTodayMaxSaleNumber();


    /**
     * 显示可以被加急.暂停等的订单
     * @return
     */
    @Query(value = "select * FROM t_sale_list WHERE state = '下单' or state = '图纸展开' or state = '审核通过' order by remark DESC",nativeQuery = true)
    public List<SaleList> urgent();


    /**
     * 设置订单备注
     * @param
     * @param remark
     */
    @Modifying
    @Query(value = "update t_sale_list set remark = ?2 where id in ?1",nativeQuery = true)
    public void setRemark(Integer []Ids,Integer remark);

    /**
     * 根据销售单号查询
     * @param saleNumber
     * @return
     */
    @Query(value = "select * from t_sale_list where sale_number like ?1",nativeQuery = true)
    public List<SaleList> findBySaleNumber(String saleNumber);

    /**
     * 显示setOpenTime界面的信息
     * @return
     */
    @Query(value = "select * from t_sale_list where state = '下单' and cunzai is null group by wuliao_id",nativeQuery = true)
    public List<SaleList> setOpenTime();

    /**
     * 显示setOpenTime界面的交期
     * @param wuliaoId
     * @return
     */
    @Query(value = "select min(refer_date) from t_sale_list where wuliao_id = ?1 and state = '下单' and cunzai is null ",nativeQuery = true)
    Date findminReferDate(String wuliaoId);

    /**
     * 把cunzai的状态改为分配工时
     * @param id
     */
    @Modifying
    @Query(value = "update t_sale_list set cunzai =?2 where id =?1",nativeQuery = true)
    public void setCunZai(Integer id,String cunzai);

    /**
     * 图纸展开界面的显示信息
     * @return
     */
    @Query(value = "select * from t_sale_list where cunzai = '分配工时' and state = '下单' group by wuliao_id order by sale_date ASC ",nativeQuery = true)
    public List<SaleList> showTuZhiOpen();

    /**
     * 通过物料号 和 存在状态模糊查询
     * @param wuliaoId
     * @return
     */
    @Query(value = "select * from t_sale_list where wuliao_id like ?1 and cunzai = ?2 group by wuliao_id order by sale_date ASC",nativeQuery = true)
    List<SaleList> selectLikeWuliaoId(String wuliaoId,String state);

    /**
     * 通过物料号和存在状态为空查询
     * @param wuliaoId
     * @return
     */
    @Query(value = "select * from t_sale_list where wuliao_id like ?1 and cunzai is null group by wuliao_id",nativeQuery = true)
    List<SaleList> selectWuliaoId(String wuliaoId);

    /**
     * 通过物料信息查找存在状态
     * @param wuliaoId
     * @return
     */
    @Query(value = "select cunzai from t_sale_list where wuliao_id = ?1",nativeQuery = true)
    String[] findCunZaiByWuliaoId(String wuliaoId);

    /**
     * 通过wuliaoId修改展开状态
     * @param
     * @param state
     */
    @Modifying
    @Query(value = "update t_sale_list set open_state = ?2 where wuliao_id = ?1 and cunzai = '分配工时' and state = '下单'",nativeQuery = true)
    void setOpenState(String wuliaoId, String state);

    /**
     * 设置存在状态
     * @param wuliaoIds
     * @param cunzai
     */
    @Modifying
    @Query(value = "update t_sale_list set cunzai = ?2 where wuliao_id in ?1 and cunzai is null and state = '下单'",nativeQuery = true)
    void setCunZaiByWuliaoIds(String[] wuliaoIds,String cunzai);

    /**
     * 图纸展开界面通过 物料号 存在状态 下单状态查找 用于保存物料明细时候使用
     * @param wuliaoId
     * @return
     */
    @Query(value = "select * from t_sale_list where wuliao_id = ?1 and cunzai = '分配工时' and  state = '下单'",nativeQuery = true)
    List<SaleList> findByWuliaoIdTuzhiOpen(String wuliaoId);

    /**
     * 查找做大的出库单号
     * @return
     */
    @Query(value = "select max(out_code) from t_sale_list",nativeQuery = true)
    public String selectMaxOutCode();

    /**
     * 无订单下单对比
     * @param num
     * @param wuliaoId
     * @rern
     */
    @Query(value = "select * from t_sale_list where sale_number like '%无订单%' and num = ?1 and wuliao_id = ?2 and shenqing_number = ?3 and xiangmu_id = ?4",nativeQuery = true)
    List<SaleList> notSaleNumber(Integer num, String wuliaoId, String shenqingNumber, String xiangmuId);

    /**
     * 查找所有无订单号的订单
     * @rern
     */
    @Query(value = "select * from t_sale_list where sale_number like '%无订单%'",nativeQuery = true)
    List<SaleList> listAllNoSaleNumber();

    /**
     * 替换订单号
     * @param saleNumber
     * @param id
     */
    @Modifying
    @Query(value = "update t_sale_list set sale_number = ?1 where id = ?2",nativeQuery = true)
    void updateSaleNumber(String saleNumber,Integer id);

    /**
     * 根据展开状态查询
     * @param state
     * @return
     */
    @Query(value = "select * from t_sale_list where open_state = ?1",nativeQuery = true)
    List<SaleList> findByOpenState(String state);

    /**
     * 信息录入界面根据订单号模糊查询
     * @param s
     * @return
     */
    @Query(value = "select * from  t_sale_list where sale_number like ?1 and cunzai = '存在' and state = '下单'",nativeQuery = true)
    List<SaleList> findBySaleNumberXinxiLuRu(String s);

    /**
     * 查找remark<0的saleList
     * @return
     */
    @Query(value = "select id from t_sale_list where remark < 0",nativeQuery = true)
    Integer[] findRemark();
}

package com.shenke.repository;

import com.shenke.entity.SaleList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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
     * @param id
     * @param remark
     */
    @Modifying
    @Query(value = "update t_sale_list set remark =?2 where id =?1",nativeQuery = true)
    public void setRemark(Integer id,Integer remark);

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
    @Query(value = "select * from t_sale_list where state = '下单' and cunzai is null",nativeQuery = true)
    public List<SaleList> setOpenTime();

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
    @Query(value = "select * from t_sale_list where cunzai = '分配工时' and state = '下单' order by sale_date ASC",nativeQuery = true)
    public List<SaleList> showTuZhiOpen();

    /**
     * 通过物料号 和 存在状态模糊查询
     * @param wuliaoId
     * @return
     */
    @Query(value = "select * from t_sale_list where wuliao_id like ?1 and cunzai = ?2 order by sale_date ASC ",nativeQuery = true)
    List<SaleList> selectLikeWuliaoId(String wuliaoId,String state);

    /**
     * 通过物料号和存在状态为空查询
     * @param wuliaoId
     * @return
     */
    @Query(value = "select * from t_sale_list where wuliao_id like ?1 and cunzai is null",nativeQuery = true)
    List<SaleList> selectLikeWuliaoId(String wuliaoId);
}

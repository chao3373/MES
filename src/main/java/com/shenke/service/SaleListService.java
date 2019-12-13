package com.shenke.service;


import com.shenke.entity.DrawingProcess;
import com.shenke.entity.SaleList;
import com.sun.javafx.collections.MappingChange;
import org.eclipse.jdt.internal.compiler.ast.IntersectionCastTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface SaleListService {

    /**
     * 保存订单
     */
    public void save(List<SaleList> plgList);

    /**
     * 查询下单成功的产品信息
     * @return
     */
    public List<SaleList> xiadan();

    /**
     * 添加大图之后 修改大图存在状态
     * @param id
     */
    public void addCunZai(Integer id);

    /**
     * 修改订单状态
     * @param id
     * @param state
     */
    public void setState(Integer id,String state);

    /**
     * 设置展开单号
     * @param id
     * @param openNum
     */
    public void setOpenNum(Integer id,String openNum);

    /**
     * 根据状态查找
     * @param state
     * @return
     */
    public List<SaleList> findByState(String state);

    /**
     * 获取最大展开单号
     * @return
     */
    public String getMaxOpenNum();

    /**
     * 根据id查找订单
     * @param id
     * @return
     */
    public SaleList findById(Integer id);

    /**
     * 获取当日最大销售单号
     * @return
     */
    public String getTodayMaxSaleNumber();


    /**
     * 显示可以被加急的订单
     * @return
     */
    public List<SaleList> urgent();


    /**
     * 设置 订单备注
     * @param id
     * @param remark
     */
    public void setRemark(Integer id,Integer remark);


    List<SaleList> findAll();


    public Map<String,Object> dingDanZhuiZong(SaleList saleList,String saleDated,String referDated,Integer page, Integer rows);

    public List<SaleList> findBySaleNumber(String saleNumber);

    //setOpenTime界面显的列（状态为下单，物料编号在数据库中不存在的）
    public List<SaleList> setOpenTime();

    //setOpenTime界面显示的最近的交期
    public java.util.Date findminReferDate(String wuliaoId);

    //保存展开工时
    public void baoCunOpenTime(Double yuGuGongShi,String wuliaoId);

    /**
     * 图纸展开界面显示
     * @return
     */
    public List<SaleList> showTuZhiOpen();

    //设置存在状态
    public void setCunZai(Integer id,String cunzai);

    //通过物料号 状态模糊查询
    public List<SaleList> selectLikeWuliaoId(String wuliaoId,String state);

    //通过物料号模糊查询
    public List<SaleList> selectWuliaoId(String wuliaoId);


    //通过物料号查找存在状态
    String findCunZaiByWuliaoId(String wuliaoId);

    //设置展开状态
    void setOpenState(String wuliaoId, String state);

    //无订单下单对比
    //List<SaleList> notSaleNumber(SaleList saleList);

    Map<String,Object> aaaa(List<SaleList> plgList);

    void setCunZaiByWuliaoIds(String[] wuliaoIds,String cunzai);

    //图纸展开界面通过 物料号 存在状态 下单状态查找
    public List<SaleList> findByWuliaoIdTuzhiOpen(String wuliaoId);

    //查找最大的出库编码
    public String selectMaxOutCode();

    //保存对象
    void saveObj(SaleList saleList1);

    //搜索关于无订单
    List<SaleList> findAboutNoSaleNumber(SaleList saleList,String saleDateCha,String referDateCha);

    //显示所有无订单号的订单
    List<SaleList> listAllNoSaleNumber();

    //替换订单编号
    void updateSaleNumber(String saleNumber,Integer id);

    /**
     * 关于saleList的多条件查询
     * @param saleList
     * @return
     */
    List<SaleList> selectAboutSaleList(SaleList saleList);

    /**
     * 通过展开状态查找
     * @param state
     * @return
     */
    List<SaleList> findByOpenState(String state);

    /**
     * 信息录入界面订单号模糊查询
     * @param s
     * @return
     */
    List<SaleList> findBySaleNumberXinxiLuRu(String s);
}

package com.shenke.service;


import com.shenke.entity.DrawingProcess;
import com.shenke.entity.SaleList;
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
     * 设置准备工时
     * @param id
     * @param prepareTime
     */
    public void setPrepareTime(Integer id,Double prepareTime);

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

    public List<SaleList> dingDanZhuiZong(SaleList saleList,String saleDated,Integer yaoqiu);
}

package com.shenke.service;


import com.shenke.entity.DrawingProcess;
import com.shenke.entity.SaleList;
import org.springframework.stereotype.Service;

import java.util.List;

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

}

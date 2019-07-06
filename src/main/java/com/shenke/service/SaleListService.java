package com.shenke.service;


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

}

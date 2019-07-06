package com.shenke.service;

import com.shenke.entity.Purchase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PurchaseService {

    /** 
    * @Description: 获取当天最大的采购单号 
    * @Param:
    * @return:  
    * @Author: Andy
    * @Date:  
    */
    public String getTodayMaxPurchaseNumber();

    /**
     * 保存采购单
     * @param purchase
     */
    public void save(Purchase purchase);


    /**
     * 查询所有的采购单
     * @param purchase
     * @return
     */
    public List<Purchase> listPurchase(Purchase purchase);


    /**
     * 根据单据号查询订单
     * @param purchaseNumber
     * @return
     */
    public List<Purchase> findByPurchaseNumber(String purchaseNumber);


}

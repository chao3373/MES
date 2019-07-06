package com.shenke.service;

import com.shenke.entity.Purchase;
import com.shenke.entity.PurchaseProduct;

import java.util.List;

public interface PurchaseProductService {


    /**
     * 保存采购商品
     * @param list
     */
    public void save (List<PurchaseProduct> list);

    /**
     *根据采购单 查询 采购商品信息
     * @param id
     * @return
     */
    public List<PurchaseProduct> listProductByPurchase(Integer id);
}

package com.shenke.repository;

import com.shenke.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Description: 采购单Repository
 * @Param:
 * @return:
 * @Author: Andy
 * @Date:
 */
public interface PurchaseRepository extends JpaRepository<Purchase, Integer>, JpaSpecificationExecutor<Purchase> {

    /**
     * @Description: 查询当天最大的采购单号
     * @Param:
     * @return:
     * @Author: Andy
     * @Date:
     */
    @Query(value = "SELECT MAX(purchase_number) FROM t_purchase WHERE TO_DAYS(purchase_date)=TO_DAYS(NOW())", nativeQuery = true)
    public String getTodayMaxPurchaseNumber();


    /**
     * 根据单据号查询订单
     * @param purchaseNumber
     * @return
     */
    @Query(value = "select * from t_purchase where  purchase_number =?1",nativeQuery = true)
    public List<Purchase> findByPurchaseNumber(String purchaseNumber);
}

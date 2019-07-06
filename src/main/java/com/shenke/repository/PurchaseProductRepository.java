package com.shenke.repository;

import com.shenke.entity.Purchase;
import com.shenke.entity.PurchaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct,Integer>, JpaSpecificationExecutor<Purchase> {


    @Query(value = "select * from t_purchase_product where purchase_id =?1",nativeQuery = true)
    public List<PurchaseProduct> listProductByPurchase(Integer id);
}

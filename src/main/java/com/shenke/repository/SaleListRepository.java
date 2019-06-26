package com.shenke.repository;

import com.shenke.entity.SaleList;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaleListRepository  extends JpaRepository<SaleList, Integer>, JpaSpecificationExecutor<SaleList> {


    /**
     * 查询状态为下单的产品信息
     * @return
     */
    @Query(value = "select * from t_sale_list where state = '下单'",nativeQuery = true)
    public List<SaleList> xiadan();

}

package com.shenke.repository;

import com.shenke.entity.SaleList;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
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
    @Query(value = "select * from t_sale_list where state = '下单'",nativeQuery = true)
    public List<SaleList> xiadan();

    /**
     * 根据id修改存在状态
     * @param id
     */
    @Modifying
    @Query(value = "update t_sale_list set cunzai = '存在' where id =?1", nativeQuery = true)
    public void addCunZai(Integer id);

}

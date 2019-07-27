package com.shenke.repository;

import com.shenke.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer>, JpaSpecificationExecutor<Supplier> {

    /***
     * 根据名称模糊查询
     * @param s
     * @return
     */
    @Query(value = "select * from t_supplier where name like ?1", nativeQuery = true)
    List<Supplier> selectByName(String s);
}

package com.shenke.repository;

import com.shenke.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier,Integer>, JpaSpecificationExecutor<Supplier> {

    /**
     * 根据供应商名称查询信息
     * @param name
     * @return
     */
    @Query(value = "select * from t_supplier where name=?1",nativeQuery = true)
    public Supplier findBySupplierName(String name);

    /**
     * 下拉框模糊查询
     * @param string
     * @return
     */
    @Query(value = "select * from t_supplier where name like ?1",nativeQuery = true)
    public List<Supplier> findByName(String string);

}

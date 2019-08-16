package com.shenke.repository;

import com.shenke.entity.ClerkProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 员工工作情况监控
 */
public interface ClerkProductRepository extends JpaRepository<ClerkProduct,Integer>, JpaSpecificationExecutor<ClerkProduct> {

}

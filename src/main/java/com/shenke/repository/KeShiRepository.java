package com.shenke.repository;

import com.shenke.entity.KeShi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Auther: Administrator
 * @Date: 2019/6/1 10:49
 * @Description:
 */
public interface KeShiRepository extends JpaRepository<KeShi, Integer>, JpaSpecificationExecutor<KeShi> {

}

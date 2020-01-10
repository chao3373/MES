package com.shenke.repository;


import com.shenke.entity.OpenTimeCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OpenTimeCountRepository extends JpaRepository<OpenTimeCount,Integer>, JpaSpecificationExecutor<Integer> {

}

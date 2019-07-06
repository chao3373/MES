package com.shenke.repository;

import com.shenke.entity.DrawingProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DrawingProcessRepository extends JpaRepository<DrawingProcess,Integer>, JpaSpecificationExecutor<DrawingProcess> {

}

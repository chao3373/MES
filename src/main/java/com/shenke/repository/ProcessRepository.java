package com.shenke.repository;

import com.shenke.entity.Process;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProcessRepository extends JpaRepository<Process,Integer>, JpaSpecificationExecutor<Process> {

    @Query(value="select * from t_process where name like ?1", nativeQuery=true)
    public List<Process> findProcessCombobox(String string);
}

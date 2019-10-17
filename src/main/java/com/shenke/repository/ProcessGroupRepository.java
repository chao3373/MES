package com.shenke.repository;

import com.shenke.entity.ProcessGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProcessGroupRepository extends JpaRepository<ProcessGroup,Integer> , JpaSpecificationExecutor<Integer> {
    /***
     * 根据工序组名称查询工序组信息
     * @param s
     * @return
     */
    @Query(value = "select * from t_process_group where process_group like ?1", nativeQuery = true)
    List<ProcessGroup> findBytext(String s);
}

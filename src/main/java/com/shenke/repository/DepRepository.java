package com.shenke.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shenke.entity.Dep;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 部门管理Repository
 * @author Administrator
 *
 */
public interface DepRepository extends JpaRepository<Dep, Integer>, JpaSpecificationExecutor<Dep>{

    /***
     * 根据部门名称查询部门信息
     * @param s
     * @return
     */
    @Query(value = "select * from t_dep where text like ?1", nativeQuery = true)
    List<Dep> findBytext(String s);
}

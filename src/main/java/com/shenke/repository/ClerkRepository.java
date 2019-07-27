package com.shenke.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.shenke.entity.Clerk;

/**
 * 员工Repository接口
 *
 * @author Administrator
 */
public interface ClerkRepository extends JpaRepository<Clerk, Integer>, JpaSpecificationExecutor<Clerk> {

    /**
     * 根据dep_id查询所有员工信息
     *
     * @param id
     * @return
     */
    @Query(value = "select * from t_clerk where dep_id=?1", nativeQuery = true)
    public List<Clerk> findByDepId(Integer id);

    /**
     * 根据dep_id删除所有员工信息
     *
     * @param id
     * @return
     */
    @Modifying
    @Query(value = "delete from t_clerk where dep_id=?1", nativeQuery = true)
    public void deleteByDepId(Integer id);

    /***
     * 根据员工姓名模糊查询员工信息
     * @param clerkName
     * @return
     */
    @Query(value = "select * from t_clerk where name like ?1", nativeQuery = true)
    List<Clerk> clerkName(String clerkName);
}

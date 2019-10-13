package com.shenke.repository;

import com.shenke.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StorageRepository extends JpaRepository<Storage,Object>, JpaSpecificationExecutor<Storage> {

    /**
     * 查询id最大的
     *
     * @return
     */
    @Query(value = "SELECT * FROM t_storage ORDER BY id DESC LIMIT 0,1", nativeQuery = true)
    public Storage selectByMaxId();

    /**
     * 根据id修改状态
     * @param ids
     * @param state
     */
    @Modifying
    @Query(value = "update t_storage set state = ?2 where id in ?1",nativeQuery = true)
    void updateState(Integer[] ids, String state);

    /**
     * 根据状态查找
     * @param state
     * @return
     */
    @Query(value = "select * from t_storage where state = ?1",nativeQuery = true)
    public List<Storage> findByState(String state);
}

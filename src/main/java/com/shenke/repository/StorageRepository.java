package com.shenke.repository;

import com.shenke.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StorageRepository extends JpaRepository<Storage,Object>, JpaSpecificationExecutor<Storage> {

    /**
     * 根据状态查找
     * @param state
     * @return
     */
    @Query(value = "select * from t_storage where state = ?1",nativeQuery = true)
    public List<Storage> findByState(String state);

    @Query(value = "select max(fahuo_number) from t_storage",nativeQuery = true)
    String selectMaxOutCode();
}

package com.shenke.repository;

import com.shenke.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface StorageRepository extends JpaRepository<Storage,Object>, JpaSpecificationExecutor<Storage> {

    /**
     * 查询id最大的
     *
     * @return
     */
    @Query(value = "SELECT * FROM t_storage ORDER BY id DESC LIMIT 0,1", nativeQuery = true)
    public Storage selectByMaxId();
}

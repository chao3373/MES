package com.shenke.repository;

import com.shenke.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StorageRepository extends JpaRepository<Storage,Object>, JpaSpecificationExecutor<Storage> {

}

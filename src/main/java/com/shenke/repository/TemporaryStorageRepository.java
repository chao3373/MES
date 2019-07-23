package com.shenke.repository;

import com.shenke.entity.TemporaryStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TemporaryStorageRepository extends JpaRepository<TemporaryStorage,Integer>, JpaSpecificationExecutor<TemporaryStorage> {

}

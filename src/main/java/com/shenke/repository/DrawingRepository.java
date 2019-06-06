package com.shenke.repository;

import com.shenke.entity.Drawing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DrawingRepository extends JpaRepository<Drawing, Integer>, JpaSpecificationExecutor<Drawing>{

    @Query( value = "select * from t_drawing where name like ?1" ,nativeQuery =true)
    public List<Drawing> conboList(String q);

}
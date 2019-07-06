package com.shenke.repository;

import com.shenke.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Integer>, JpaSpecificationExecutor<Client> {


    /**
     * 根据客户名称
     * @param name
     * @return
     */
    @Query(value = "select * from t_client where name=?1",nativeQuery = true)
    public Client findByClientName(String name);


    /**
     * 下拉框模糊查询
     * @param string
     * @return
     */
    @Query(value = "select * from t_client where name like ?1",nativeQuery = true)
    public List<Client> findCombobox(String string);

}

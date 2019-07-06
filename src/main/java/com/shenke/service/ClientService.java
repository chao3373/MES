package com.shenke.service;


import com.shenke.entity.Client;

import java.util.List;

/**
 *
 * 客户Service接口
 *
 */
public interface ClientService {

    /**
     * 查询客户信息
     * @param client
     * @return
     */
    public List<Client> list(Client client);

    /**
     * 根据客户名称查询客户信息
     * @param name
     * @return
     */
    public Client findByClientName(String name);


    /**
     * 添加或删除客户信息
     * @param client
     */
    public void save(Client client);


    /**
     * 根据id查询客户信息
     * @param id
     * @return
     */
    public Client findById(Integer id);

    /**
     * 根据id删除客户信息
     * @param id
     */
    public void deleteById(Integer id);


    /**
     * 下拉框模糊查询
     * @param string
     * @return
     */
    public List<Client> findCombobox(String string);
}

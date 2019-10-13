package com.shenke.service;

import com.shenke.entity.Storage;

import java.util.List;
import java.util.Map;

public interface StorageService {

    /**
     * 保存
     * @param storage
     */
    public void save (Storage storage);

    /**
     * 根据ID查找对象
     * @return
     */
    public Storage findById(Integer id);

    /**
     * 根据Id修改状态
     * @param ids
     * @param state
     */
    public void updateState(Integer[] ids, String state);

    /**
     * 根据状态查找
     * @param state
     * @return
     */
    public List<Storage> findByState(String state);

    public List<Storage> detail(Map<String, Object> map1);
}

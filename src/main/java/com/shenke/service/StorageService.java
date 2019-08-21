package com.shenke.service;

import com.shenke.entity.Storage;

public interface StorageService {

    /**
     * 保存
     * @param storage
     */
    public void save (Storage storage);

    /**
     * 查询最大ID
     * @return
     */
    public Storage selectByMaxId();

    /**
     * 根据ID查找对象
     * @return
     */
    public Storage findById(Integer id);
}

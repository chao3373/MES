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

    public List<Storage> detail(Map<String, Object> map1);

    String selectMaxOutCode();
}

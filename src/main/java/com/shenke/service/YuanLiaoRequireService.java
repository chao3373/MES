package com.shenke.service;

import com.shenke.entity.YuanLiaoRequire;

import java.util.List;

public interface YuanLiaoRequireService {

    /**
     * 保存原料需求单
     * @param yuanLiaoRequire
     */
    void save(YuanLiaoRequire yuanLiaoRequire);

    /**
     * 通过订单id查询
     * @param id
     * @return
     */
    public List<YuanLiaoRequire> findBySaleListId(Integer id);
}

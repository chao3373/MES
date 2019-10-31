package com.shenke.service;

import com.shenke.entity.Wuliao;

import java.util.List;

public interface WuliaoService {
    public void save(List<Wuliao> list);

    public List<Wuliao> findByBigDrawingId(Integer id);

    public List<Wuliao> findBySaleListId(Integer saleListId);

    void saveOld(Wuliao wuliao1);

    void deleteByBigDrawingId(Integer id);
}

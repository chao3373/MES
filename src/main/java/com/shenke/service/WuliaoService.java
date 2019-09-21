package com.shenke.service;

import com.shenke.entity.Wuliao;

public interface WuliaoService {
    public void save(Wuliao wuliao);

    public Wuliao findByBigDrawingId(Integer id);

}

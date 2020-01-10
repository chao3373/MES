package com.shenke.service;

import com.shenke.entity.OpenTimeCount;

import java.util.Map;

public interface OpenTimeCountService {

    void save(OpenTimeCount openTimeCount);

    Map<String, Object> selectOpenTime(String userName, String wuliaoId, String btime, String etime, Integer page, Integer rows);
}

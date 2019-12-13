package com.shenke.service;

import com.shenke.entity.ShowGongShi;

import java.util.List;

public interface ShowGongShiService {

    //设置是否显示工时
    void updateShowGongShi(Integer show);

    List<ShowGongShi> select();
}

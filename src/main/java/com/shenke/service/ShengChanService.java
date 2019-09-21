package com.shenke.service;

import com.shenke.entity.ShengChan;

import java.util.List;

public interface ShengChanService {

    /**
     * 保存
     * @param shengChan
     */
    public void save(ShengChan shengChan);

    /**
     * “生产任务”  界面显示
     * @return
     */
    public List<ShengChan> listProduct();

    /**
     * "工序加工" 界面显示
     * @param arr
     */
    public List<ShengChan> showInProcessProduct(Integer[] arr);
}

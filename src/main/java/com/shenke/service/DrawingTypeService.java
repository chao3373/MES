package com.shenke.service;

import com.shenke.entity.DrawingType;

import java.util.List;

public interface DrawingTypeService {

    /**
     * 添加子图
     * @param drawingType
     */
    public void addSonDrawing(DrawingType drawingType);

    /**
     * 根据订单id查询小图id
     * @param id
     * @return
     */
    public List<DrawingType> findBySaleListId(Integer id);


    /**
     * 根据id修改状态
     * @param id
     * @param state
     */
    public void setState(Integer id,String state);


}

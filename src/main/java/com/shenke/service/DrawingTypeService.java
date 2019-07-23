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
     * 根据大图id查询小图id
     * @param id
     * @return
     */
    public List<DrawingType> findByBigDrawingId(Integer id);


}

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
     * 根据大图物料号id查询小图
     * @param id
     * @return
     */
    public List<DrawingType> findByBigDrawingId(Integer id);


    void deleteByBigDrawingId(Integer id);

    /**
     * 按照id删除
     * @param id
     */
    void deleteById(Integer id);

    DrawingType findById(Integer id);

    void save(DrawingType drawingType);
}

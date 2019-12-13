package com.shenke.service;

import com.shenke.entity.Drawing;
import com.shenke.entity.DrawingProcess;

import java.util.List;

public interface DrawingProcessService {

    public void deleteByDrawingId(Integer drawingId);

    public void save(DrawingProcess drawingProcess);

    public List<DrawingProcess> findByDrawingId(Integer id);

    public List<DrawingProcess> findByArr(Integer []a);


    void deleteByProcessIds(Integer[] ids);
}

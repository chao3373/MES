package com.shenke.service;

import com.shenke.entity.BigDrawingProcess;

import java.util.List;

public interface BigDrawingProcessService {
    void deleteByDrawingId(Integer drawingId);

    void save(BigDrawingProcess bigDrawingProcess);

    List<BigDrawingProcess> findByBigDrawingId(Integer id);

    void deleteByProcessIds(Integer[] ids);
}

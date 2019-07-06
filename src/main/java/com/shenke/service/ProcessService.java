package com.shenke.service;


import com.shenke.entity.DrawingProcess;
import com.shenke.entity.Process;

import java.util.List;

public interface ProcessService {

    /**
     * 查找所有的工序信息
     * @param process
     * @return
     */
    public List<Process> list(Process process);

    /**
     * 根据id查询对象
     * @param id
     * @return
     */
    public Process findById(Integer id);

    public void saveDrawingProcess(DrawingProcess drawingProcess);

    public List<Process> findProcessCombobox(String name);
}

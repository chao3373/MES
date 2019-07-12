package com.shenke.service;

import com.shenke.entity.Drawing;
import com.shenke.entity.DrawingProcess;

import java.util.List;

public interface DrawingProcessService {

    /**
     * 查询所有的信息
     * @return
     */
    public List<DrawingProcess> findStateNull();

    /**
     * 生产完成
     * @param id
     */
    public void setState(Integer id);

    /**
     * 查找审核通过的信息
     * @return
     */
    public List<DrawingProcess> findAuditPass();

    /**
     * 保存图纸所含工序
     * @param drawingProcess
     */
    public void saveDrawingProcess(DrawingProcess drawingProcess);

    /**
     * 查找状态为任务下发的信息
     * @return
     */
    public List<DrawingProcess> findProcessIssue();

    /**
     * 按照工序名称查找信息
     * @return
     */
    public List<DrawingProcess> findByProcess(Integer id);

    /**
     * 更新完成数量
     * @param accomplishNum
     */
    public void updateAccomplishNum(Integer accomplishNum,Integer id);

    /**
     * 通过id查找对象
     * @param id
     * @return
     */
    public DrawingProcess findById(Integer id);
}

package com.shenke.service;

import com.shenke.entity.Drawing;
import com.shenke.entity.DrawingProcess;

import java.util.List;

public interface DrawingProcessService {


    /**
     * 生产完成
     * @param id
     */
    public void setState(Integer id);

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

    /**
     * 查找某一通知单号的下未生产的最初工序
     * @param informNum
     * @return
     */
    public Integer findMinProcess(String informNum);

    /**
     * 获取最大的生产任务单号
     * @return
     */
    public String getTodayMaxinformNumNumber();


    /**
     * 按照任务单号查找状态
     * @param informNum
     * @return
     */
    public Object[] findStateByInformNum(String informNum);
}

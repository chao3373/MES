package com.shenke.service;

import com.shenke.entity.BigDrawing;

import java.util.List;


public interface BigDrawingService {

    /***
     *
     * 查询所有的信息
     * @return
     */
    public List<BigDrawing> list(BigDrawing bigDrawing);

    /***
     *
     * 保存或是修改信息
     */
    public void save(BigDrawing bigDrawing);

    /***
     * 删除信息
     * @param id
     */
    public void delete(Integer id);

    /***
     * 模糊查询
     * @param q
     * @return
     */
    public List<BigDrawing> conboList(String q);

    /**
     * 根据大图纸号查询
     * @return
     */
    public BigDrawing findBigDrawingId(String DrawingId);

    public BigDrawing findById(Integer id);

    /**
     * 根据物料单号查询
     * @param wuliaoId
     * @return
     */
    public BigDrawing findByWuLiaoId(String wuliaoId);


    /**
     * 根据图纸号查询id
     * @param drawingId
     * @return
     */
    public Integer findIdByDrawingId(String drawingId);


    /**
     * 通过物料号模糊查询
     * @param s
     * @return
     */
    public List<BigDrawing> findLikeWuliaoId(String s);

    /**
     * 修改展开工时
     * @param time
     */
    void updateTime(Double time,Integer id);
}

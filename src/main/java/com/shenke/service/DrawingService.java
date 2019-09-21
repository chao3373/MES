package com.shenke.service;

import com.shenke.entity.Drawing;

import java.util.List;

public interface DrawingService {

    /***
     * 查询所有的信息
     * @param drawing
     * @return
     */
    public List<Drawing> list(Drawing drawing);

    /***
     * 保存或是修改信息
     * @param drawing
     */
    public void save(Drawing drawing);

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
    public List<Drawing> conboList(String q);

    /**
     * 根据名称查询
     * @return
     */
    public Drawing findByWuliaoId(String wuliaoId);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Drawing findById(Integer id);

}

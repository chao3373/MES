package com.shenke.service;

import com.shenke.entity.Supplier;

import java.util.List;

/***
 * 供应商
 */
public interface SupplierService {

    /***
     * 查询供应商信息
     * @param supplier
     * @return
     */
    public List<Supplier> list(Supplier supplier);

    /***
     * 按照供应商名称查询
     * @param name
     * @return
     */
    public Supplier findBySupplierName(String name);

    /***
     * 添加或修改供应商信息
     * @param supplier
     */
    public void save (Supplier supplier);

    /***
     * 根据ID查询
     * @param id
     * @return
     */
    public Supplier findById(Integer id);

    /***
     * 根据id删除供应商信息
     * @param id
     */
    public void delete (Integer id);

    /***
     * 下拉框模糊查询
     * @param string
     * @return
     */
    public List<Supplier> findByName(String string);
}

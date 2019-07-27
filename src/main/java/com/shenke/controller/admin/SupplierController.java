package com.shenke.controller.admin;

import com.shenke.entity.Supplier;
import com.shenke.service.SupplierService;
import com.shenke.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/supplier")
public class SupplierController {

    @Resource
    private SupplierService supplierService;

    /***
     * 保存供应商
     * @param supplier
     * @return
     */
    @RequestMapping("/save")
    public String save(Supplier supplier){
        supplierService.save(supplier);
        return "保存成功！";
    }

    /***
     * 查询所有供应商信息
     * @return
     */
    @RequestMapping("/list")
    public List<Supplier> list(){
        return supplierService.list();
    }

    /***
     * 根据id数组删除
     * @param ids
     * @return
     */
    @RequestMapping("/deleteByIds")
    public String deleteByIds(Integer[] ids){
        supplierService.deleteByIds(ids);
        return "删除成功！";
    }

    /**
     * 根据名称模糊查询
     * @param name
     * @return
     */
    @RequestMapping("/selectByName")
    public List<Supplier> selectByName(String name){
        if (StringUtil.isEmpty(name)){
            name = "";
        }
        return supplierService.selectByName("%" + name + "%");
    }
}

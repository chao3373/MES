package com.shenke.controller.admin;

import java.util.List;
import java.util.Map;
import com.shenke.entity.Supplier;
import com.shenke.service.SupplierService;
import com.sun.javafx.collections.MappingChange;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 *供应商Controller
 */
@RestController
@RequestMapping("admin/supplier")
public class SupplierAdminController {

    @Resource
    private SupplierService supplierService;

    /***
     * 显示供应商信息
     * @param supplier
     * @return
     */
    @RequestMapping("/list")
    public Map<String,Object> list(Supplier supplier){
        Map<String,Object> map = new HashMap<>();
        List<Supplier> list = supplierService.list(supplier);
        map.put("rows",list);
        System.out.println("11111");
        System.out.println(list);
        System.out.println("11111");
        return map;
    }

    /***
     * 添加或修改仓库信息
     * @param supplier
     * @return
     */
    @RequestMapping("/save")
    public Map<String,Object> save (Supplier supplier){
        System.out.println("22222");
        Map<String,Object> map = new HashMap<>();
        if(supplier.getId() == null){
            if(supplierService.findBySupplierName(supplier.getName())!=null){
                map.put("success",false);
                map.put("errorInfo","供应商名称已存在");
                return map;
            }
        }
        supplierService.save(supplier);
        map.put("success",true);
        return map;
    }

    /***
     * 删除供应商信息
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public Map<String,Object> delete(Integer id){
        System.out.println("333333");
        Map<String,Object> map = new HashMap<>();
        supplierService.delete(id);
        map.put("success",true);
        return map;
    }

    /***
     * combobox下拉模糊查询
     * @param q
     * @return
     * @throws Exception
     */
    @RequestMapping("/supplierList")
    public List<Supplier> comboList(String q) throws  Exception{
        System.out.println("33333");
        if(q==null){
            q="";
        }
        return supplierService.findByName("%" + q +"%");
    }
}

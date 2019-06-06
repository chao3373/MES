package com.shenke.controller.admin;


import com.shenke.entity.Drawing;
import com.shenke.service.DrawingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 图纸Controller
 *
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/drawing")
public class DrawingAdminController {

    @Resource
    private DrawingService drawingService;
    /**
     * 分页查询商标信息
     *
     * @param
     * @throws Exception
     */
    @RequestMapping("/list")
    public Map<String, Object> list(Drawing drawing) throws Exception {
        System.out.println("==========");
        System.out.println(drawing);
        Map<String, Object> map = new HashMap<>();
        List<Drawing> list = drawingService.list(drawing);
        map.put("rows",list);
        return map;
    }

    /**
     * 添加或修改仓库信息
     */
    @RequestMapping("/save")
    public Map<String, Object> save(Drawing drawing) {
        Map<String, Object> resultMap = new HashMap<>();
        /*if (drawing.getId() == null) {
            if (drawingService.findByBrandName(dao.getName()) != null) {
                resultMap.put("success", false);
                resultMap.put("errorInfo", "商标已经存在！");
                return resultMap;
            }
        }*/
        drawingService.save(drawing);
        resultMap.put("success", true);
        return resultMap;
    }
    /**
     * 删除商标信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public Map<String,Object> delete(Integer id){
        Map<String,Object> map = new HashMap<>();
        drawingService.delete(id);
        map.put("success",true);
        return map;
    }


/**
     * 下拉框模糊查询
     *
     * @param q
     * @return
     * @throws Exception
     */
    @RequestMapping("/conbox")
    public List<Drawing> comboList(String q){
        if(q == null){
            q="";
        }
        return drawingService.conboList("%"+ q + "%" );
    }
}

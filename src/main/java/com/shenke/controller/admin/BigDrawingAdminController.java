package com.shenke.controller.admin;


import com.shenke.entity.BigDrawing;
import com.shenke.service.BigDrawingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图纸Controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/admin/bigdrawing")
public class BigDrawingAdminController {

    @Resource
    private BigDrawingService bigDrawingService;
    /**
     * 分页查询商标信息
     *
     * @param
     * @throws Exception
     */
    @RequestMapping("/list")
    public Map<String, Object> list(BigDrawing bigDrawing) throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<BigDrawing> list = bigDrawingService.list(bigDrawing);
        map.put("rows", list);
        return map;
    }

    /**
     * 添加或修改大图纸信息
     */
    @RequestMapping("/save")
    public Map<String, Object> save(BigDrawing bigDrawing) {
        if (!bigDrawing.getDrawingURL().isEmpty()) {
            String fileName = bigDrawing.getDrawingURL().getOriginalFilename();
            String path = "D:/drawing/";
            File filepath = new File(path, fileName);
            //判断路径是否存在不存在则创建
            if (!filepath.getParentFile().exists()){
                filepath.getParentFile().mkdirs();
            }
            //将文件上传到目标文件中
            try {
                bigDrawing.getDrawingURL().transferTo(new File(path + File.separator + fileName));
                bigDrawing.setUrl(path + fileName);
                System.out.println(bigDrawing.getUrl());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Map<String, Object> resultMap = new HashMap<>();
        bigDrawingService.save(bigDrawing);
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
    public Map<String, Object> delete(Integer id) {
        Map<String, Object> map = new HashMap<>();
        bigDrawingService.delete(id);
        map.put("success", true);
        return map;
    }


    /**
     * 下拉框模糊查询
     *
     * @param q
     * @return
     * @throws Exception
     */
    @RequestMapping("/bigdrawingList")
    public List<BigDrawing> comboList(String q) {
        if (q == null) {
            q = "";
        }
        return bigDrawingService.conboList("%" + q + "%");
    }
}

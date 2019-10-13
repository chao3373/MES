package com.shenke.controller.admin;


import com.shenke.entity.Drawing;
import com.shenke.entity.Log;
import com.shenke.service.DrawingService;
import com.shenke.service.LogService;
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
 * 小图纸Controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/admin/drawing")
public class DrawingAdminController {

    @Resource
    private DrawingService drawingService;

    @Resource
    private LogService logService;

    /**
     * 分页查询图纸信息
     *
     * @param
     * @throws Exception
     */
    @RequestMapping("/list")
    public Map<String, Object> list(Drawing drawing) throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<Drawing> list = drawingService.list(drawing);
        map.put("rows", list);
        logService.save(new Log(Log.SEARCH_ACTION, "查询图纸信息"));
        return map;
    }

    /**
     * 添加或修改仓库信息
     */
    @RequestMapping("/save")
    public Map<String, Object> save(HttpServletRequest request, Drawing drawing) {
        if (!drawing.getDrawingURL().isEmpty()) {
            String fileName = drawing.getDrawingURL().getOriginalFilename();
            String path = "D:/drawing/";
            File filepath = new File(path, fileName);
            //判断路径是否存在不存在则创建
            if (!filepath.getParentFile().exists()){
                filepath.getParentFile().mkdirs();
            }
            //将文件上传到目标文件中
            try {
                drawing.getDrawingURL().transferTo(new File(path + File.separator + fileName));
                drawing.setUrl(fileName);
                System.out.println(drawing.getUrl());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Map<String, Object> resultMap = new HashMap<>();
        drawingService.save(drawing);

        System.out.println("***************");
        System.out.println(drawing);
        System.out.println("***************");
        resultMap.put("success", true);
        logService.save(new Log(Log.UPDATE_ACTION, "添加或修改图纸信息"));
        return resultMap;
    }

    /**
     * 删除图纸信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public Map<String, Object> delete(Integer id) {
        Map<String, Object> map = new HashMap<>();
        drawingService.delete(id);
        map.put("success", true);
        logService.save(new Log(Log.DELETE_ACTION, "删除图纸信息"));
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
    public List<Drawing> comboList(String q) {
        if (q == null) {
            q = "";
        }
        logService.save(new Log(Log.SEARCH_ACTION, "模糊查询图纸信息"));
        return drawingService.conboList("%" + q + "%");
    }

    /**
     * 保存小图纸
     * @param smallIds
     * @return
     */
    @RequestMapping("/savaAboutWuliaoId")
    public Map<String,Object> savaAboutWuliaoId(String []smallIds){
        Map<String,Object> map = new HashMap<>();
        for(int i =0 ;i<smallIds.length;i++) {
            System.out.println(drawingService.findByWuliaoId(smallIds[i]));
            if(drawingService.findByWuliaoId(smallIds[i]) == null){
                System.out.println("到这了");
                Drawing drawing = new Drawing();
                drawing.setWuliaoId(smallIds[i]);
                drawingService.save(drawing);
            }
        }
        map.put("success",true);
        return map;
    }
}

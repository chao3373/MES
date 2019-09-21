package com.shenke.controller.admin;


import com.shenke.entity.BigDrawing;
import com.shenke.entity.Wuliao;
import com.shenke.service.BigDrawingService;
import com.shenke.service.WuliaoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 大图纸生产物料Controller
 */

@RestController
@RequestMapping("/admin/wuliao")
public class WuliaoAdminController {

    @Resource
    private WuliaoService wuliaoService;

    @Resource
    private BigDrawingService bigDrawingService;

    @RequestMapping("/save")
    public Map<String,Object> save(String wuliaoId,String mingxi){
        Map<String,Object> map = new HashMap<>();
        Wuliao wuliao = new Wuliao();
        wuliao.setBigDrawing(bigDrawingService.findByWuLiaoId(wuliaoId));
        wuliao.setWuliao(mingxi);
        Integer id = bigDrawingService.findByWuLiaoId(wuliaoId).getId();
        System.out.println("id"+id);
        Wuliao wuliaoUpdate = wuliaoService.findByBigDrawingId(id);
        if(wuliaoUpdate!=null){
            wuliaoUpdate.setWuliao(mingxi);
            wuliaoService.save(wuliaoUpdate);
        }
        else {
            wuliaoService.save(wuliao);
        }
        map.put("success",true);
        return map;
    }
}

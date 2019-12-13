package com.shenke.controller.admin;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shenke.entity.BigDrawing;
import com.shenke.entity.SaleList;
import com.shenke.entity.User;
import com.shenke.entity.Wuliao;
import com.shenke.service.BigDrawingService;
import com.shenke.service.SaleListService;
import com.shenke.service.WuliaoService;
import com.shenke.service.YuanLiaoRequireService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

    @Resource
    private SaleListService saleListService;

    @Resource
    private YuanLiaoRequireService yuanLiaoRequireService;

    @RequestMapping("/save")
    public Map<String,Object> save(String data,String bigDrawing){
        Map<String,Object> map = new HashMap<>();

        BigDrawing bigDrawing1 = bigDrawingService.findByWuLiaoId(bigDrawing);

        //通过大图id删除对应物料信息
        //wuliaoService.deleteByBigDrawingId(bigDrawing1.getId());
        Gson gson = new Gson();
        List<Wuliao> plgList = gson.fromJson(data, new TypeToken<List<Wuliao>>() {
        }.getType());
        for(Wuliao wuliao : plgList){
            if(wuliao.getId() == null){
                wuliao.setBigDrawing(bigDrawing1);
                wuliaoService.save(wuliao);
            }
        }
        map.put("success",true);
        return map;
    }

    /**
     * 保存老图的物料明细
     * @param wuliaoId
     */
    /*@RequestMapping("/saveOld")
    public void saveOld(Integer id,String wuliaoId){
        List<Wuliao> list = wuliaoService.findBySaleListId(id);

        System.out.println("********************");
        System.out.println(list);
        System.out.println("********************");
        System.out.println(list.size());
        System.out.println("********************");

        if(list.size() == 0){
            List<Wuliao> list2 = wuliaoService.findByBigDrawingId(bigDrawingService.findByWuLiaoId(wuliaoId).getId());
            for(Wuliao wuliao : list2){
                *//*wuliao.setId(null);
                wuliao.setSaleList(saleListService.findById(id));
                wuliao.setShenQingDate(new Date());*//*

                Wuliao wuliao1 = new Wuliao();
                wuliao1.setUser(wuliao.getUser());
                wuliao1.setBigDrawing(wuliao.getBigDrawing());
                wuliao1.setChangJia(wuliao.getChangJia());
                wuliao1.setGuiGe(wuliao.getGuiGe());
                wuliao1.setNum(wuliao.getNum());
                wuliao1.setName(wuliao.getName());
                wuliao1.setSaleList(saleListService.findById(id));
                wuliao1.setShenQingDate(new Date());
                wuliaoService.saveOld(wuliao1);
            }
           // wuliaoService.save(list2);
        }
    }*/

    /**
     * 通过大图纸id查找
     * @param wuliaoId
     * @return
     */
    @RequestMapping("/findByBigDrawingId")
    public Map<String,Object> findByBigDrawingId(String wuliaoId){
        Map<String,Object> map = new HashMap<>();
        BigDrawing bigDrawing = bigDrawingService.findByWuLiaoId(wuliaoId);
        map.put("rows",wuliaoService.findByBigDrawingId(bigDrawing.getId()));

        return map;
    }

    @RequestMapping("/deleteById")
    public Map<String,Object> deleteById(Integer id){
        Map<String,Object> map = new HashMap<>();
        yuanLiaoRequireService.deleByWuliaoId(id);
        wuliaoService.deleteById(id);
        map.put("success",true);
        return map;
    }
}

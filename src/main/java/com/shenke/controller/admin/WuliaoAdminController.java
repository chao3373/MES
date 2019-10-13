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

    @RequestMapping("/save")
    public Map<String,Object> save(String data, Integer saleListId, String bigDrawing, HttpSession session){
        Map<String,Object> map = new HashMap<>();

        Gson gson = new Gson();
        List<Wuliao> plgList = gson.fromJson(data, new TypeToken<List<Wuliao>>() {
        }.getType());

        for(Wuliao wuliao : plgList){
            wuliao.setBigDrawing(bigDrawingService.findByWuLiaoId(bigDrawing));
            wuliao.setSaleList(saleListService.findById(saleListId));
            wuliao.setUser((User) session.getAttribute("currentUser"));
            wuliao.setShenQingDate(new Date());

        }
        System.out.println("**********************");
        System.out.println(plgList);
        System.out.println("**********************");
        wuliaoService.save(plgList);
        map.put("success",true);
        return map;
    }

    /**
     * 保存老图的物料明细
     * @param id
     * @param wuliaoId
     */
    @RequestMapping("/saveOld")
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
                /*wuliao.setId(null);
                wuliao.setSaleList(saleListService.findById(id));
                wuliao.setShenQingDate(new Date());*/

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
    }
}

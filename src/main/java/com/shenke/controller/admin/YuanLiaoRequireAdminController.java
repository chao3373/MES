package com.shenke.controller.admin;


import com.shenke.entity.*;
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


@RestController
@RequestMapping("/admin/yuanLiaoRequire")
public class YuanLiaoRequireAdminController {

    @Resource
    private BigDrawingService bigDrawingService;

    @Resource
    private WuliaoService wuliaoService;

    @Resource
    private SaleListService saleListService;

    @Resource
    private YuanLiaoRequireService yuanLiaoRequireService;

    /**
     * 保存原料需求单
     * @param num
     * @param wuliaoId
     * @param saleListId
     * @param session
     * @return
     */
    @RequestMapping("/save")
    public Map<String,Object> save (Integer num,String wuliaoId,Integer saleListId, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        BigDrawing bigDrawing = bigDrawingService.findByWuLiaoId(wuliaoId);

        List<Wuliao> list = wuliaoService.findByBigDrawingId(bigDrawing.getId());

        for (Wuliao wuliao : list){
            YuanLiaoRequire yuanLiaoRequire = new YuanLiaoRequire();
            yuanLiaoRequire.setTao(num);
            yuanLiaoRequire.setSaleList(saleListService.findById(saleListId));
            yuanLiaoRequire.setShenQingDate(new Date());
            yuanLiaoRequire.setWuliao(wuliao);
            yuanLiaoRequire.setUser((User) session.getAttribute("currentUser"));
            yuanLiaoRequire.setState("备货中");
            yuanLiaoRequire.setSumNum(num * wuliao.getNum());
            yuanLiaoRequireService.save(yuanLiaoRequire);
        }
        map.put("success",true);
        return map;
    }

    /**
     * 通过订单id查询
     * @param id
     * @return
     */
    @RequestMapping("/findBySaleListId")
    public Map<String,Object> findBySaleListId(Integer id){
        Map<String,Object> map = new HashMap<>();
        map.put("rows",yuanLiaoRequireService.findBySaleListId(id));
        return map;
    }

    /**
     * 保存老图的物料信息
     * @param id
     * @param wuliaoId
     * @param session
     * @return
     */
    @RequestMapping("/saveOld")
    public Map<String,Object> saveOld(Integer id,String wuliaoId,HttpSession session){
        Map<String,Object> map = new HashMap<>();
        BigDrawing bigDrawing = bigDrawingService.findByWuLiaoId(wuliaoId);
        SaleList saleList = saleListService.findById(id);
        List<YuanLiaoRequire> list1 = yuanLiaoRequireService.findBySaleListId(saleList.getId());
        if(list1.size() == 0){
            List<Wuliao> list = wuliaoService.findByBigDrawingId(bigDrawing.getId());
            for (Wuliao wuliao : list){
                YuanLiaoRequire yuanLiaoRequire = new YuanLiaoRequire();
                yuanLiaoRequire.setSumNum(saleList.getNum() * wuliao.getNum());
                yuanLiaoRequire.setTao(saleList.getNum());
                yuanLiaoRequire.setState("未备货");
                yuanLiaoRequire.setUser((User)session.getAttribute("currentUser"));
                yuanLiaoRequire.setWuliao(wuliao);
                yuanLiaoRequire.setSaleList(saleList);
                yuanLiaoRequire.setShenQingDate(new Date());
                yuanLiaoRequireService.save(yuanLiaoRequire);
            }
        }

        return map;
    }
}

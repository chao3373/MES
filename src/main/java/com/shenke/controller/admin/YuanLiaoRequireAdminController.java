package com.shenke.controller.admin;


import com.shenke.entity.BigDrawing;
import com.shenke.entity.User;
import com.shenke.entity.Wuliao;
import com.shenke.entity.YuanLiaoRequire;
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
}

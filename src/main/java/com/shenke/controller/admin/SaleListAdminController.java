package com.shenke.controller.admin;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shenke.entity.*;
import com.shenke.service.*;
import com.shenke.util.DateUtil;
import com.shenke.util.StringUtil;
import org.apache.commons.collections.map.HashedMap;
import org.hibernate.collection.internal.PersistentList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.persistence.ManyToOne;
import javax.servlet.http.HttpSession;
import java.util.*;

/***
 * 销售订单Controller
 */
@RestController
@RequestMapping("/admin/saleList")
public class SaleListAdminController {
    @Resource
    private SaleListService saleListService;

    @Resource
    private BigDrawingService bigDrawingService;

    @Resource
    private DrawingTypeService drawingTypeService;

    @Resource
    private LogService logService;

    @Resource
    private DrawingProcessService drawingProcessService;

    @Resource
    private WuliaoService wuliaoService;

    @Resource
    private BigDrawingProcessService bigDrawingProcessService;

    /**
     * 保存添加订单
     * @return
     */
    @RequestMapping("/save")
    public Map<String, Object> save(String data) throws Exception {
        Map<String,Object> map = new HashMap<>();
        Gson gson = new Gson();
        List<SaleList> plgList = gson.fromJson(data, new TypeToken<List<SaleList>>() {
        }.getType());

       for(SaleList saleList : plgList){
           saleList.setState("下单");
           if(bigDrawingService.findByWuLiaoId(saleList.getWuliaoId())!=null){
               saleList.setCunzai(saleListService.findCunZaiByWuliaoId(saleList.getWuliaoId()));
           }
           saleList.setRemark(0);
        }
        saleListService.save(plgList);
        map.put("success",true);
        logService.save(new Log(Log.ADD_ACTION, "保存添加订单"));
        return map;
    }

    @RequestMapping("/notSaleNumber")
    public Map<String,Object> notSaleNumber(String data){
        Map<String,Object> map = new HashMap<>();
        Gson gson = new Gson();
        List<SaleList> plgList = gson.fromJson(data, new TypeToken<List<SaleList>>() {
        }.getType());

        for(SaleList saleList : plgList){
            map.put("rows",saleListService.notSaleNumber(saleList));
        }
        return map;
    }

    /**
     *
     * 查询状态为下单的产品信息
     * @return
     */
    @RequestMapping("/xiadanSuccess")
    public Map<String,Object> xiadanSuccess(){
        Map<String,Object> map = new HashMap<>();
        List<SaleList> list = saleListService.xiadan();

        //显示大图所对应的工序集合
        for (SaleList saleList : list){
            BigDrawing bigDrawing = bigDrawingService.findByWuLiaoId(saleList.getWuliaoId());
            List<BigDrawingProcess> bdpList = bigDrawingProcessService.findByBigDrawingId(bigDrawing.getId());
            StringBuffer sb = new StringBuffer();

            for (BigDrawingProcess bigDrawingProcess : bdpList){
                sb.append(","+bigDrawingProcess.getProcess().getName());
            }
            saleList.setGongxus(sb.toString().replaceFirst(",",""));
        }

        System.out.println("**************list****************");
        System.out.println(list);
        System.out.println("**************list****************");

        map.put("rows",list);
        map.put("success",true);
        logService.save(new Log(Log.SEARCH_ACTION, "查询状态为下单的商品"));
        return map;
    }

    /**
     * 根据id修改存在状态
     * @param id
     * @return
     */
    @RequestMapping("/addCunZai")
    public Map<String,Object> addCunZai(Integer id){
        Map<String,Object> map = new HashMap<>();
        saleListService.addCunZai(id);
        map.put("success",true);
        logService.save(new Log(Log.SEARCH_ACTION, "根据id修改存在状态"));
        return map;
    }

    /**
     * 点击大图纸信息显示所包含的小图纸信息
     * @return
     */
    @RequestMapping("/bigSmallDrawing")
    public Map<String,Object> BigSmallDrawing(String wuliaoId){
        Map<String,Object> map = new HashMap<>();
        List<DrawingType> list = drawingTypeService.findByBigDrawingId(bigDrawingService.findByWuLiaoId(wuliaoId).getId());

        //显示小图所含的工序集合
        for (DrawingType d : list){
            List<DrawingProcess> dpList = drawingProcessService.findByDrawingId(d.getDrawing().getId());
            StringBuffer sb = new StringBuffer();
            for (DrawingProcess dp : dpList){
                sb.append(","+dp.getProcess().getName());
            }
            d.setGongxus(sb.toString().replaceFirst(",",""));
        }
        map.put("rows",list);
        logService.save(new Log(Log.SEARCH_ACTION, "根据大图纸查询小图纸信息"));
        return map;
    }

    /**
     * 设置订单状态
     * @param id
     * @param state
     * @return
     */
    @RequestMapping("/setState")
    public Map<String,Object> setState(Integer id,String state){
        Map<String,Object> map = new HashMap<>();
        saleListService.setState(id,state);
        map.put("success",true);
        logService.save(new Log(Log.UPDATE_ACTION, "设置订单状态"));
        return map;
    }

    /**
     * 按照状态查询
     * @param state
     * @return
     */
    @RequestMapping("/findByState")
    public Map<String,Object> findByState(String state){
        Map<String,Object> map = new HashMap<>();
        List<SaleList> list = saleListService.findByState(state);
        map.put("rows",list);
        logService.save(new Log(Log.SEARCH_ACTION, "按照订单状态查询订单信息"));
        return map;
    }


    /**
     * 显示可以被加急的订单
     * @return
     */
    @RequestMapping("/urgent")
    public Map<String,Object> urgent(){
        Map<String,Object> map = new HashMap<>();
        List<SaleList> list = saleListService.urgent();
        map.put("rows",list);
        return map;
    }

    /**
     * 设置订单备注
     * @param remark
     * @return
     */
    @RequestMapping("/setRemark")
    public Map<String,Object> setRemark(String Ids,Integer remark){

        Map<String,Object> map = new HashMap<>();
        String idsStr[] = Ids.split(",");
        for(int i=0;i<idsStr.length;i++){
            saleListService.setRemark(Integer.parseInt(idsStr[i]),remark);
        }

        map.put("success",true);
        return map;
    }

    /**
     * 产品返修
     * @param state
     * @return
     */
    @RequestMapping("/fanXiu")
    public Map<String,Object> fanXiu(Integer saleListId,String state){
        Map<String,Object> map = new HashMap<>();
        saleListService.setState(saleListId,state);
        map.put("success",true);
        return map;
    }

    /**
     * 查找全部
     * @return
     */
    @RequestMapping("/findAll")
    public Map<String,Object> findAll(){
        Map<String,Object> map = new HashMap<>();
        List<SaleList> list = saleListService.findAll();
        map.put("rows",list);
        return map;
    }


    /**
     * 订单追踪
     * @param saleList
     * @param saleDated
     * @return
     */
    @RequestMapping("/dingDanZhuiZong")
    public Map<String,Object> dingDanZhuiZong(SaleList saleList,String saleDated,Integer yaoqiu){
        Map<String,Object> map = new HashMap<>();//此处的要求为同样的查询字段但是要求不相同
        List<SaleList> list = saleListService.dingDanZhuiZong(saleList,saleDated,yaoqiu);
        map.put("success",true);
        map.put("rows",list);
        return map;
    }

    @RequestMapping("/setStateByIds")
    public Map<String,Object> setStateByIds(String Ids,String state){
        Map<String,Object> map = new HashMap<>();
        for (int i = 0;i<Ids.split(",").length;i++){
            saleListService.setState(Integer.parseInt(Ids.split(",")[i]),state);
        }
        map.put("success",true);
        return map;
    }

    /**
     * 根据销售单号查询
     * @param saleNumber
     * @return
     */
    @RequestMapping("/findBySaleNumber")
    public Map<String,Object> findBySaleNumber(String saleNumber){
        Map<String,Object> map = new HashMap<>();
        map.put("rows",saleListService.findBySaleNumber("%"+saleNumber+"%"));
        return map;
    }

    /**
     * 显示setOpenTime界面的信息
     * @return
     */
    @RequestMapping("/setOpenTime")
    public Map<String,Object> setOpenTime(){
        Map<String,Object> map = new HashMap<>();
        map.put("rows",saleListService.setOpenTime());
        return  map;
    }

    /**
     * 保存展开工时
     * @param yuGuGongShi
     * @return
     */
    @RequestMapping("/baoCunOpenTime")
    public Map<String,Object> baoCunOpenTime(Integer []ids,Double yuGuGongShi,String []wuliaoIds){
        Map<String,Object> map = new HashMap<>();

        for (int i=0;i<wuliaoIds.length;i++){
            saleListService.baoCunOpenTime(yuGuGongShi,wuliaoIds[i]);
        }

        for (int i= 0;i<ids.length;i++){
            saleListService.setCunZai(ids[i],"分配工时");
        }
        map.put("success",true);
        return map;
    }


    /**
     * 图纸展开界面显示的信息
     * @return
     */
    @RequestMapping("/showTuZhiOpen")
    public Map<String,Object> showTuZhiOpen(){
        Map<String,Object> map = new HashMap<>();
        map.put("rows",saleListService.showTuZhiOpen());
        return map;
    }

    /**
     * 通过物料编号模糊查询
     * @param wuliaoId
     * @param state
     * @return
     */
    @RequestMapping("/selectLikeWuliaoId")
    public Map<String,Object> selectLikeWuliaoId(String wuliaoId,String state){
        Map<String,Object> map = new HashMap<>();
        if (StringUtil.isEmpty(wuliaoId)){
            wuliaoId = "";
        }
        if (StringUtil.isEmpty(state)){
            map.put("rows",saleListService.selectWuliaoId("%"+wuliaoId+"%"));
        }else {
            map.put("rows",saleListService.selectLikeWuliaoId("%"+wuliaoId+"%",state));
        }
        return map;
    }

    /**
     * 图纸展开完成
     * @param wuliaoId
     * @param id
     * @return
     */
    @RequestMapping("/finishOpen")
    public Map<String,Object> finishOpen(String wuliaoId,Integer id){
        Map<String,Object> map = new HashMap<>();
        saleListService.setCunZai(id,"存在");
        map.put("success",true);
        return map;
    }

    /**
     *
     * @return
     */
    @RequestMapping("/setOpenState")
    public Map<String,Object> setOpenState(Integer id, String state, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        if(state != ""){
            User user = (User) session.getAttribute("currentUser"); //获取当前登录用户对象
            state = state + "：" +user.getTrueName();
        }

        saleListService.setOpenState(id,state);
        map.put("success",true);
        return map;
    }

    /**
     * 返回当前用户
     * @param session
     * @return
     */
    @RequestMapping("/dangqianUser")
    public Map<String,Object> dangqianUser(HttpSession session){
        Map<String,Object> map = new HashMap<>();
        User user = (User) session.getAttribute("currentUser");
        map.put("user",user.getTrueName());
        return map;
    }
}

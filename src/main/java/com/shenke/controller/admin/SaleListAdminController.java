package com.shenke.controller.admin;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shenke.entity.*;
import com.shenke.service.*;
import com.shenke.util.DateUtil;
import com.shenke.util.StringUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 保存添加订单
     * @return
     */
    @RequestMapping("/save")
    public Map<String, Object> save(String goodsJson) throws Exception {
        Map<String,Object> map = new HashMap<>();
        Gson gson = new Gson();
        List<SaleList> plgList = gson.fromJson(goodsJson, new TypeToken<List<SaleList>>() {
        }.getType());

       for(SaleList saleList : plgList){
           saleList.setState("下单");
           if(bigDrawingService.findByWuLiaoId(saleList.getWuliaoId())!=null){
               saleList.setCunzai("存在");
               saleList.setRemark(0);
           }
           saleList.setRemark(0);
        }
        saleListService.save(plgList);
        map.put("success",true);
        logService.save(new Log(Log.ADD_ACTION, "保存添加订单"));
        return map;
    }

    /**
     * 查询状态为下单的产品信息
     * @return
     */
    @RequestMapping("/xiadanSuccess")
    public Map<String,Object> xiadanSuccess(){
        Map<String,Object> map = new HashMap<>();
        List<SaleList> list = saleListService.xiadan();
        map.put("rows",list);
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
     * 获取销售单号
     * @return
     * @throws Exception
     */
    @RequestMapping("/genCode")
    public String genCode() throws Exception {
        StringBuffer code = new StringBuffer("XS");
        code.append(DateUtil.getCurrentDateStr());
        String saleNumber = saleListService.getTodayMaxSaleNumber();
        if (saleNumber != null) {
            code.append(StringUtil.formatCode(saleNumber));
        } else {
            code.append("0001");
        }
        return code.toString();
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
        map.put("rows",saleListService.findBySaleNumber(saleNumber));
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
     * @param id
     * @param yuGuGongShi
     * @return
     */
    @RequestMapping("/baoCunOpenTime")
    public Map<String,Object> baoCunOpenTime(Integer id,Double yuGuGongShi,String wuliaoId){
        Map<String,Object> map = new HashMap<>();
        saleListService.baoCunOpenTime(yuGuGongShi,wuliaoId);
        saleListService.setCunZai(id,"分配工时");
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
}

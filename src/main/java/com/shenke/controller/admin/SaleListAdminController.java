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
import java.lang.reflect.MalformedParameterizedTypeException;
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

    @RequestMapping("/aaa")
    public Map<String,Object> aaa(){
        Map<String,Object> map = new HashMap<>();
        StringBuffer code = new StringBuffer("FH");
        String a = saleListService.selectMaxOutCode();
        if(a!=null){
            code.append(StringUtil.formatCode(a));
        }else{
            code.append("00000001");
        }

        for(int i = 0 ; i < 3 ;i ++){
            String bianma = code.toString();
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println(bianma);
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
            code = new StringBuffer("FH");
            code = code.append(StringUtil.formatCode(bianma));
        }
        map.put("aaa",code.toString());
        return map;
    }


    @RequestMapping("/save")
    public Map<String, Object> save(String data) throws Exception {
        Map<String,Object> map = new HashMap<>();
        Gson gson = new Gson();
        List<SaleList> plgList = gson.fromJson(data, new TypeToken<List<SaleList>>() {
        }.getType());

        //设置发货编码
        StringBuffer code = new StringBuffer("FH");
        String outCode = saleListService.selectMaxOutCode();
        if(outCode!=null){
            code.append(StringUtil.formatCode(outCode));
        }else{
            code.append("00000001");
        }
       for(SaleList saleList : plgList){

           String bianma = code.toString();

           saleList.setOutCode(bianma);
           saleList.setState("下单");
           if(bigDrawingService.findByWuLiaoId(saleList.getWuliaoId())!=null){
               saleList.setCunzai(saleListService.findCunZaiByWuliaoId(saleList.getWuliaoId()));
           }
           saleList.setRemark(0);
           code = new StringBuffer("FH");
           code = code.append(StringUtil.formatCode(bianma));
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

        Map<String,Object> map1 = saleListService.aaaa(plgList);

        Object show = map1.get("list");
        Object daochu = map1.get("list2");


        map.put("show",show);
        map.put("daochu",daochu);

        map.put("success",true);
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
        if(state == "任务下发"){
            logService.save(new Log(Log.UPDATE_ACTION, "任务下发，id:"+id));
        }
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
    public Map<String,Object> setRemark(Integer []Ids,Integer remark){
        Map<String,Object> map = new HashMap<>();
        saleListService.setRemark(Ids,remark);
        String jiaji = "";
        switch (remark){
            case -2 :
                jiaji = "撤销订单";
                break;
            case -1:
                jiaji = "订单暂停";
                break;
            case  0:
                jiaji = "订单恢复";
                break;
            case 1:
                jiaji = "订单加急";
                break;
            case 2:
                jiaji = "重要订单";
                break;
        }
        logService.save(new Log(Log.UPDATE_ACTION, "操作："+jiaji+";ID:"+Arrays.toString(Ids)));
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
    public Map<String,Object> dingDanZhuiZong(SaleList saleList,String saleDated,String referDated,Integer page, Integer rows){
        return saleListService.dingDanZhuiZong(saleList,saleDated,referDated,page,rows);
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
        System.out.println("**********************************");
        System.out.println(map);
        System.out.println("**********************************");
        return map;
    }

    /**
     * 信息录入界面根据销售单号查询
     * @param saleNumber
     * @return
     */
    @RequestMapping("/findBySaleNumberXinxiLuRu")
    public Map<String,Object> findBySaleNumberXinxiLuRu(String saleNumber){
        Map<String,Object> map = new HashMap<>();
        List<SaleList> list = saleListService.findBySaleNumberXinxiLuRu("%"+saleNumber+"%");
        for (SaleList saleList : list){
            BigDrawing bigDrawing = bigDrawingService.findByWuLiaoId(saleList.getWuliaoId());
            List<BigDrawingProcess> bdpList = bigDrawingProcessService.findByBigDrawingId(bigDrawing.getId());
            StringBuffer sb = new StringBuffer();

            for (BigDrawingProcess bigDrawingProcess : bdpList){
                sb.append(","+bigDrawingProcess.getProcess().getName());
            }
            saleList.setGongxus(sb.toString().replaceFirst(",",""));
        }
        map.put("rows",list);
        return map;
    }

    /**
     * 显示setOpenTime界面的信息
     * @return
     */
    @RequestMapping("/setOpenTime")
    public Map<String,Object> setOpenTime(){
        Map<String,Object> map = new HashMap<>();
        List<SaleList> list = saleListService.setOpenTime();
        map.put("rows",list);
        return  map;
    }

    /**
     * 保存展开工时
     * @param yuGuGongShi
     * @return
     */
    @RequestMapping("/baoCunOpenTime")
    public Map<String,Object> baoCunOpenTime(Double yuGuGongShi,String data){
        Map<String,Object> map = new HashMap<>();

        Gson gson = new Gson();
        List<SaleList> plgList = gson.fromJson(data, new TypeToken<List<SaleList>>() {
        }.getType());

        String []wuliaoIds = new String[plgList.size()];
        Integer i = 0;
        for (SaleList saleList : plgList){
            saleListService.baoCunOpenTime(yuGuGongShi,saleList);
            wuliaoIds[i] = saleList.getWuliaoId();
            i=i+1;
        }

        saleListService.setCunZaiByWuliaoIds(wuliaoIds,"分配工时");
        logService.save(new Log(Log.UPDATE_ACTION, "添加预估工时："+yuGuGongShi+";物料号:"+Arrays.toString(wuliaoIds)));

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
     * @return
     */
    @RequestMapping("/finishOpen")
    public Map<String,Object> finishOpen(String wuliaoId){
        Map<String,Object> map = new HashMap<>();
        List<SaleList> list = saleListService.findByWuliaoIdTuzhiOpen(wuliaoId);
        for(SaleList saleList : list){
            saleList.setCunzai("存在");
        }
        System.out.println("11111111111111111111111111111111111111111111111111s");
        saleListService.save(list);
        map.put("success",true);
        return map;
    }

    /**
     *
     * @return
     */
    @RequestMapping("/setOpenState")
    public Map<String,Object> setOpenState(String wuliaoId, String state, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        User user = (User) session.getAttribute("currentUser"); //获取当前登录用户对象
        if(state.equals("展开中")){
            state = state + "：" +user.getTrueName();

            List<SaleList> list = saleListService.findByOpenState(state);

            if(list.size() != 0){
                map.put("success",true);
                map.put("error","您有正在展开的图纸！");

            }else {
                saleListService.setOpenState(wuliaoId,state);
                map.put("success",true);
            }
        }else {
            saleListService.setOpenState(wuliaoId,state+"："+user.getTrueName());
            map.put("success",true);
        }
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
        System.out.println("**************++++++***************");
        System.out.println(user.getProcess());
        System.out.println("**************++++++***************");
        map.put("user",user.getTrueName());
        map.put("bestProcess",user.getProcess());
        return map;
    }

    //查找
    @RequestMapping("/findAboutNoSaleNumber")
    public Map<String,Object> findAboutNoSaleNumber(SaleList saleList,String saleDateCha,String referDateCha){
        Map<String,Object> map = new HashMap<>();
        List<SaleList> list = saleListService.findAboutNoSaleNumber(saleList,saleDateCha,referDateCha);
        map.put("rows",list);
        map.put("success",true);
        System.out.println("***********************8");
        System.out.println(map);
        System.out.println("***********************8");
        return map;
    }

    /**
     * 显示所有无订单
     * @return
     */
    @RequestMapping("listAllNoSaleNumber")
    public Map<String,Object> listAllNoSaleNumber(){
        Map<String,Object> map = new HashMap<>();
        map.put("rows",saleListService.listAllNoSaleNumber());
        return map;
    }

    /**
     * 替换订单编号
     * @return
     */
    @RequestMapping("/updateSaleNumber")
    public Map<String,Object> updateSaleNumber(String saleNumber,Integer id){
        Map<String,Object> map = new HashMap<>();
        saleListService.updateSaleNumber(saleNumber,id);
        map.put("success",true);
        return map;
    }

    /**
     * 关于saleList对象的多条件查询
     * @param
     * @return
     */
    @RequestMapping("/selectAboutSaleList")
    public Map<String,Object> selectAboutSaleList(SaleList saleList,String data){
        Map<String,Object> map = new HashMap<>();
        System.out.println("******************************");
        System.out.println(saleList);
        System.out.println(data);
        System.out.println("******************************");
        List<SaleList> list = saleListService.selectAboutSaleList(saleList);

        map.put("rows",list);
        return map;
    }

    /**
     * 发货条码界面显示
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/showInFaHuoMa")
    public Map<String,Object> showInFaHuoMa(Integer page, Integer rows,String saleNumber,String wuliaoId){
        return saleListService.showInFaHuoMa(page,rows,saleNumber,wuliaoId);
    }


    /**
     * 图纸展开界面点击物料号显示被groupBy的信息
     * @return
     */
    @RequestMapping("/tuzhiOpenChakan")
    public Map<String,Object> tuzhiOpenChakan(String wuliaoId){
        Map<String,Object> map = new HashMap<>();
        map.put("rows",saleListService.tuzhiOpenChakan(wuliaoId));
        map.put("success",true);
        return map;
    }
}

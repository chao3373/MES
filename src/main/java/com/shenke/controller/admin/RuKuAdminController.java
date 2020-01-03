package com.shenke.controller.admin;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shenke.entity.RuKu;
import com.shenke.entity.SaleList;
import com.shenke.entity.ShengChan;
import com.shenke.service.RuKuService;
import com.shenke.service.SaleListService;
import com.shenke.service.ShengChanService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.beans.IntrospectionException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/ruKu")
public class RuKuAdminController {

    @Resource
    private ShengChanService shengChanService;

    @Resource
    private RuKuService ruKuService;

    @Resource
    private SaleListService saleListService;

    @RequestMapping("/save")
    public Map<String,Object> save(String data){
        Map<String,Object> map = new HashMap<>();
        Gson gson = new Gson();
        List<ShengChan> plgList = gson.fromJson(data, new TypeToken<List<ShengChan>>() {
        }.getType());

        for (ShengChan shengChan : plgList){
            SaleList saleList = shengChan.getSaleList();
            List<ShengChan> list = shengChanService.findBySaleList(saleList.getId());
            Integer n = 0;
            for(ShengChan shengChan1 : list){
                System.out.println(shengChan1.getState());
                System.out.println(shengChan1.getState().equals("生产完成"));
                if(!shengChan1.getState().equals("生产完成")){
                    n = 1;
                }
            }
            System.out.println("************************");
            System.out.println(n);
            System.out.println("************************");
            if(n == 0 ){
                System.out.println("******************************");
                System.out.println("到这到这");
                System.out.println("******************************");
                RuKu ruKu = new RuKu();
                ruKu.setNum(saleList.getNum());
                ruKu.setRukuNum(0);
                ruKu.setState("准备入库");
                ruKu.setOrderNum(saleList.getNum());
                ruKu.setSaleList(saleList);
                ruKu.setOutCode(saleList.getOutCode());
                ruKuService.save(ruKu);
            }
        }

        /*for (ShengChan shengChan : plgList){
            Integer saleListId = shengChan.getSaleList().getId();
            RuKu ruKu = ruKuService.findBySaleListId(saleListId);
            SaleList saleList =shengChan.getSaleList();
            Integer minNum = shengChanService.findMinAccomplishNumBySaleListId(saleListId);

            //判断大图是否可以修改状态为“未生产”
            List<ShengChan> datu = shengChanService.findBySaleListIdAboutDatu(saleListId);
            List<ShengChan> xiaotu = shengChanService.findBySaleListIdAboutXiaotu(saleListId);
            Integer m;
            Integer n;
            if(datu.size() == 0){
                m=1; // 大图已开始生产
            }else {
                m=2; //大图一定没有开始生产
            }
            if(xiaotu.size() == 0){
                n=1; //小图一定生产完了
            }else {
                n=2; // 小图生产开始但是没有生产完
            }

            if(n==1 && m== 2){
                shengChanService.updateDatuState(saleListId);
            }

            if (ruKu != null) {
                ruKu.setNum(minNum);
                ruKuService.save(ruKu);
                if(ruKu.getNum() == ruKu.getOrderNum()){
                    saleListService.setState(shengChan.getSaleList().getId(),"生产完成");
                }
            } else {
                RuKu ruKu1 = new RuKu();
                ruKu1.setNum(minNum);
                ruKu1.setRukuNum(0);
                ruKu1.setState("准备入库");
                ruKu1.setOrderNum(shengChan.getNum());
                ruKu1.setSaleList(saleList);
                ruKu1.setOutCode(saleList.getOutCode());
                ruKuService.save(ruKu1);
                if(ruKu1.getNum() == ruKu1.getOrderNum()){
                    saleListService.setState(shengChan.getSaleList().getId(),"生产完成");
                }
            }
        }*/
        map.put("success",true);
        return map;
    }

    /**
     * 显示 状状态为 准备入库的信息
     * @return
     */
    @RequestMapping("/list")
    public Map<String,Object> list(){
        Map<String,Object> map = new HashMap<>();
        map.put("rows",ruKuService.list());
        return map;
    }

    /**
     * 通过大图编号搜索
     * @param outCode
     * @return
     */
    @RequestMapping("/findByoutCode")
    public Map<String,Object> findByoutCode(String outCode){
        Map<String,Object> map = new HashMap<>();
        map.put("rows",ruKuService.findByoutCode(outCode));
        map.put("success",true);
        return map;
    }


    /**
     * 产品入库
     * @param
     * @return
     */
    @RequestMapping("/ruku")
    public Map<String,Object> ruku(Integer id){
        Map<String,Object> map = new HashMap<>();
        ruKuService.updateState(id);
        map.put("success",true);
        return map;
    }
}

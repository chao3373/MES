package com.shenke.controller.admin;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shenke.entity.RuKu;
import com.shenke.entity.SaleList;
import com.shenke.entity.ShengChan;
import com.shenke.service.RuKuService;
import com.shenke.service.SaleListService;
import com.shenke.service.ShengChanService;
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
    public Map<String,Object> save(Integer id,Integer saleListId){
        Map<String,Object> map = new HashMap<>();
        ShengChan shengChan = shengChanService.findOne(id);
        Integer minNum = shengChanService.findMinAccomplishNumBySaleListId(saleListId);
        RuKu ruKu = ruKuService.findBySaleListId(saleListId);
        SaleList saleList = saleListService.findById(saleListId);

        if(ruKu != null){
            ruKu.setNum(minNum);
            ruKuService.save(ruKu);
            if(ruKu.getNum() == ruKu.getOrderNum()){
                saleListService.setState(shengChan.getSaleList().getId(),"生产完成");
            }
        }else {
            RuKu ruKu1 = new RuKu();
            ruKu1.setNum(minNum);
            ruKu1.setRukuNum(0);
            ruKu1.setState("准备入库");
            ruKu1.setOrderNum(saleList.getNum());
            ruKu1.setSaleList(saleList);
            ruKu1.setDatuCode(shengChan.getDatuCode());
            ruKuService.save(ruKu1);
            if(ruKu1.getNum() == ruKu1.getOrderNum()){
                saleListService.setState(shengChan.getSaleList().getId(),"生产完成");
            }
        }

        /*if(shengChanService.findMaxCode(xiaotuCode) == shengChan.getCode()){
            RuKu ruku = ruKuService.findOneByxiaotuCode(xiaotuCode);
            if(ruku != null){
                ruku.setNum(shengChan.getAccomplishNum());
                ruKuService.save(ruku);
            }else {
                RuKu ruku1 = new RuKu();
                ruku1.setBigDrawing(shengChan.getBigDrawing());
                ruku1.setNum(shengChan.getAccomplishNum());
                ruku1.setDatuCode(shengChan.getDatuCode());
                ruku1.setDrawing(shengChan.getDrawing());
                ruku1.setSaleList(shengChan.getSaleList());
                ruku1.setState("准备入库");
                ruku1.setXiaotuCode(xiaotuCode);
                ruku1.setRukuNum(0);
                ruKuService.save(ruku1);
            }

        }*/
        map.put("success",true);
        return map;
    }

    /**
     * 多选生产时候的保存
     * @param data
     * @return
     */
    @RequestMapping("/saveDuoXuan")
    public Map<String,Object> saveDuoXuan(String data) {
        Map<String, Object> map = new HashMap<>();
        Gson gson = new Gson();
        List<ShengChan> plgList = gson.fromJson(data, new TypeToken<List<ShengChan>>() {
        }.getType());

        for (ShengChan shengChan : plgList){
            Integer saleListId = shengChan.getSaleList().getId();
            RuKu ruKu = ruKuService.findBySaleListId(saleListId);
            SaleList saleList =shengChan.getSaleList();
            Integer minNum = shengChanService.findMinAccomplishNumBySaleListId(saleListId);

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
                ruKu1.setOrderNum(saleList.getNum());
                ruKu1.setSaleList(saleList);
                ruKu1.setDatuCode(shengChan.getDatuCode());
                ruKuService.save(ruKu1);
                if(ruKu1.getNum() == ruKu1.getOrderNum()){
                    saleListService.setState(shengChan.getSaleList().getId(),"生产完成");
                }
            }
        }
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
     * @param datuCode
     * @return
     */
    @RequestMapping("/findByDatuCode")
    public Map<String,Object> findByDatuCode(String datuCode){
        Map<String,Object> map = new HashMap<>();
        map.put("rows",ruKuService.findByDatuCode(datuCode));
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

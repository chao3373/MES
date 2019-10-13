package com.shenke.controller.admin;

import com.shenke.entity.RuKu;
import com.shenke.entity.ShengChan;
import com.shenke.service.RuKuService;
import com.shenke.service.ShengChanService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/ruKu")
public class RuKuAdminController {

    @Resource
    private ShengChanService shengChanService;

    @Resource
    private RuKuService ruKuService;

    @RequestMapping("/save")
    public Map<String,Object> save(Integer id){
        Map<String,Object> map = new HashMap<>();
        ShengChan shengChan = shengChanService.findOne(id);
        String xiaotuCode = shengChan.getXiaotuCode();

        if(shengChanService.findMaxCode(xiaotuCode) == shengChan.getCode()){
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
            map.put("success",true);
        }
        return map;
    }

    /**
     * 显示 状状态为 准备出库的信息
     * @return
     */
    @RequestMapping("/list")
    public Map<String,Object> list(){
        Map<String,Object> map = new HashMap<>();
        map.put("rows",ruKuService.list());
        return map;
    }

    /**
     * 产品出库
     * @param Ids
     * @return
     */
    @RequestMapping("/ruku")
    public Map<String,Object> ruku(Integer []Ids){
        Map<String,Object> map = new HashMap<>();
        for(int i = 0; i< Ids.length ;i++){
            ruKuService.updateState(Ids[i]);
        }
        map.put("success",true);
        return map;
    }
}

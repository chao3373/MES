package com.shenke.controller.admin;

import com.shenke.entity.Log;
import com.shenke.entity.ShowGongShi;
import com.shenke.service.LogService;
import com.shenke.service.ShowGongShiService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/showGongShi")
public class ShowGongShiAdminController {

    @Resource
    private ShowGongShiService showGongShiService;

    @Resource
    private LogService logService;

    /**
     * 设置是否显示工时
     * @param show
     * @return
     */
    @RequestMapping("/updateShowGongShi")
    public Map<String,Object> updateShowGongShi(Integer show){
        Map<String,Object> map = new HashMap<>();
        showGongShiService.updateShowGongShi(show);
        String xianshi = "";
        if(show == 0){
            xianshi = "显示";
        }else if (show == 1){
            xianshi = "取消显示";
        }
        logService.save(new Log(Log.UPDATE_ACTION,"设置工序加工界面"+xianshi+"操作、准备工时"));
        map.put("success",true);
        return map;
    }

    @RequestMapping("/select")
    private Map<String,Object> select(){
        Map<String,Object> map = new HashMap<>();
        List<ShowGongShi> list = showGongShiService.select();
        if(list.size() == 1){

            map.put("show",list.get(0).getIsShow());
            map.put("success",true);
        }
        return map;
    }
}

package com.shenke.controller.admin;

import com.shenke.controller.UserController;
import com.shenke.entity.*;
import com.shenke.service.*;
import com.shenke.util.TiaoMaUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/admin/shengChan")
public class ShengChanAdminController {

    @Resource
    private ShengChanService shengChanService;

    @Resource
    private BigDrawingService bigDrawingService;

    @Resource
    private DrawingTypeService drawingTypeService;

    @Resource
    private DrawingProcessService drawingProcessService;

    @Resource
    private SaleListService saleListService;

    @Resource
    private UserProcessService userProcessService;


    @RequestMapping("/save")
    public Map<String, Object> save(String wuliaoId, Integer id) {
        Map<String, Object> map = new HashMap<>();

        BigDrawing bigDrawing = bigDrawingService.findByWuLiaoId(wuliaoId);
        SaleList saleList = saleListService.findById(id);

        List<DrawingType> list = drawingTypeService.findByBigDrawingId(bigDrawing.getId());
        List list1 = new ArrayList();

        for (DrawingType drawingType : list) {
            list1.add(drawingType.getDrawing().getId());
        }
        Integer[] a = new Integer[list1.size()];
        list1.toArray(a);
        List<DrawingProcess> list2 = drawingProcessService.findByArr(a);

        String wlCode = "WL" + new Date().getTime();
        String str = "TZ" + new Date().getTime();
        Integer xid = list2.get(0).getDrawing().getId();
        for (DrawingProcess drawingProcess : list2) {
            ShengChan shengChan = new ShengChan();
            if (drawingProcess.getDrawing().getId() == xid) {
                shengChan.setXiaotuCode(str);
            } else {
                str = "TZ" + new Date().getTime();
                shengChan.setXiaotuCode(str);
                xid = drawingProcess.getDrawing().getId();
            }
            shengChan.setSaleList(saleList);
            shengChan.setBigDrawing(bigDrawing);
            shengChan.setDrawing(drawingProcess.getDrawing());
            shengChan.setProcess(drawingProcess.getProcess());
            shengChan.setDatuCode(wlCode);
//            shengChan.setXiaotuCode("TZ" + new SimpleDateFormat("yyyyMMddHHMMss").format(new Date()) + drawingProcess.getDrawing().getId());
            shengChan.setState("任务下发");
            shengChan.setAccomplishNum(0);
            shengChan.setNum(saleList.getNum());
            shengChan.setReferDate(saleList.getReferDate());
            shengChanService.save(shengChan);
        }
        map.put("success", true);
        return map;
    }

    /**
     * 显示“生产任务”界面
     * @return
     */
    @RequestMapping("/listProduct")
    public Map<String,Object> listProduct(){
        Map<String,Object> map = new HashMap<>();
        map.put("rows",shengChanService.listProduct());
        return map;
    }

    /**
     * 生成、打印条码
     * @param code
     * @return
     */
    @RequestMapping("/tiaoma")
    public Map<String,Object> tiaoma(String code){
        Map<String,Object> map = new HashMap<>();
        TiaoMaUtil.generateFile(code,"D:/tm/" + code + ".png");
        map.put("url","/tm/" + code + ".png");
        map.put("success",true);
        return map;
    }

    /**
     * 在“工序生产”界面显示
     * @return
     */
    @RequestMapping("/showInProcessProduct")
    public Map<String,Object> showInProcessProduct(HttpSession session){
        Map<String,Object> map = new HashMap<>();
        User user = (User) session.getAttribute("currentUser"); //获取当前登录用户对象
        List<UserProcess> list = userProcessService.findByUserId(user.getId());
        List list1 = new ArrayList();
        for (UserProcess up : list){
            list1.add(up.getProcess().getId());
        }
        Integer []Arr = new  Integer[list1.size()];
        list1.toArray(Arr);
        List<ShengChan> list2 = shengChanService.showInProcessProduct(Arr);
        map.put("rows",list2);
        map.put("userName",user.getUserName());
        return map;
    }



}

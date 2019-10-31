package com.shenke.controller.admin;

import com.shenke.entity.*;
import com.shenke.service.*;
import com.shenke.util.TiaoMaUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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

    @Resource
    private UserProductService userProductService;

    @Resource
    private RuKuService ruKuService;

    @Resource
    private BigDrawingProcessService bigDrawingProcessService;


    @RequestMapping("/save")
    public Map<String, Object> save(String wuliaoId, Integer id) {
        Map<String, Object> map = new HashMap<>();
        //大图对象
        BigDrawing bigDrawing = bigDrawingService.findByWuLiaoId(wuliaoId);
        //订单对象
        SaleList saleList = saleListService.findById(id);
        //大图所对应的小图集合
        List<DrawingType> list = drawingTypeService.findByBigDrawingId(bigDrawing.getId());
        //大图对应的工序集合
        List<BigDrawingProcess> list3 = bigDrawingProcessService.findByBigDrawingId(bigDrawing.getId());
        //生成物料编码
        String wlCode = "WL" + new Date().getTime();
        //生成图纸编码
        String str = "TZ" + new Date().getTime();
        if(list.size() != 0){
            List list1 = new ArrayList();
            for (DrawingType drawingType : list) {
                list1.add(drawingType.getDrawing().getId());
            }
            Integer[] a = new Integer[list1.size()];
            list1.toArray(a);
            //小图对应的工序集合
            List<DrawingProcess> list2 = drawingProcessService.findByArr(a);
            Integer xid = list2.get(0).getDrawing().getId();
            //生成小图生产任务
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
                shengChan.setCode(drawingProcess.getCode());
                shengChan.setDatuCode(wlCode);
//            shengChan.setXiaotuCode("TZ" + new SimpleDateFormat("yyyyMMddHHMMss").format(new Date()) + drawingProcess.getDrawing().getId());
                shengChan.setState("任务下发");
                shengChan.setAccomplishNum(0);
                shengChan.setNum(saleList.getNum());
                shengChan.setReferDate(saleList.getReferDate());
                shengChan.setZbGongShi(drawingProcess.getZbGongShi());
                shengChan.setCzGongShi(drawingProcess.getCzGongShi());
                shengChan.setIsDatu(1);
                shengChanService.save(shengChan);
            }
            //生成大图生产任务
            for (BigDrawingProcess bigDrawingProcess: list3) {
                ShengChan shengChan = new ShengChan();
                shengChan.setSaleList(saleList);
                shengChan.setBigDrawing(bigDrawing);
                shengChan.setProcess(bigDrawingProcess.getProcess());
                shengChan.setCode(bigDrawingProcess.getCode());
                shengChan.setDatuCode(wlCode);
                shengChan.setState("任务下发");
                shengChan.setAccomplishNum(0);
                shengChan.setNum(saleList.getNum());
                shengChan.setReferDate(saleList.getReferDate());
                shengChan.setZbGongShi(bigDrawingProcess.getZbGongShi());
                shengChan.setCzGongShi(bigDrawingProcess.getCzGongShi());
                shengChan.setIsDatu(0);
                shengChanService.save(shengChan);
            }
        }else {
            //生成大图生产任务
            for (BigDrawingProcess bigDrawingProcess: list3) {
                ShengChan shengChan = new ShengChan();
                shengChan.setSaleList(saleList);
                shengChan.setBigDrawing(bigDrawing);
                shengChan.setProcess(bigDrawingProcess.getProcess());
                shengChan.setCode(bigDrawingProcess.getCode());
                shengChan.setDatuCode(wlCode);
                shengChan.setState("任务下发");
                shengChan.setAccomplishNum(0);
                shengChan.setNum(saleList.getNum());
                shengChan.setReferDate(saleList.getReferDate());
                shengChan.setZbGongShi(bigDrawingProcess.getZbGongShi());
                shengChan.setCzGongShi(bigDrawingProcess.getCzGongShi());
                shengChan.setIsDatu(0);
                shengChanService.save(shengChan);
            }
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

        System.out.println("*******************");
        System.out.println(code);
        System.out.println("*******************");
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
        //通过工序状态判断当前登录员工是否有未工作的工序
        List<ShengChan> list3 = shengChanService.findByUserForState("%"+user.getTrueName());
        //若有扫码但是没有生产的工序 就显示没有生产的工序
        if(list3.size() != 0){
            map.put("rows",list3);
        }else {
            List<UserProcess> list = userProcessService.findByUserId(user.getId());
            List list1 = new ArrayList();
            for (UserProcess up : list){
                list1.add(up.getProcess().getId());
            }
            if(list1.size() == 0){
                return map;
            }
            Integer []Arr = new  Integer[list1.size()];
            list1.toArray(Arr);

            List<ShengChan> list2 = shengChanService.showInProcessProduct(Arr);

            for(ShengChan shengChan : list2){
                if(shengChan.getCode() == 1){
                    shengChan.setAllowNum(shengChan.getNum() - shengChan.getAccomplishNum());
                }else {
                    if(shengChanService.selectBeforeProcess(shengChan.getXiaotuCode(),shengChan.getCode()-1) == shengChan.getNum()){
                        shengChan.setAllowNum(shengChan.getNum() - shengChan.getAccomplishNum());
                    }else {
                        shengChan.setAllowNum(shengChanService.selectBeforeProcess(shengChan.getXiaotuCode(),shengChan.getCode()-1));
                    }
                }
            }
            map.put("rows",list2);
        }
        return map;
    }

    /**
     * 更新完成数量 保存生产记录
     * @param id
     * @param num
     * @param session
     * @return
     */
    @RequestMapping("/updateAccomplishNum")
    public Map<String,Object> updateAccomplishNum(Integer id,Integer num,HttpSession session){
        Map<String,Object> map = new HashMap<>();
        ShengChan shengChan = shengChanService.findOne(id);
        String xiaotuCode = shengChan.getXiaotuCode();
        Integer code = shengChan.getCode();

        if(shengChan.getCode() == 1){
            if(num > (shengChan.getNum()-shengChan.getAccomplishNum())){
                map.put("success", false);
                map.put("errorInfo", "输入数量有误！");
                return map;
            }
        }else {
            if((num+shengChan.getAccomplishNum())>shengChanService.selectBeforeProcess(xiaotuCode,code-1)){
                map.put("success", false);
                map.put("errorInfo", "输入数量有误！");
                return map;
            }
        }

        Integer sumNum = num + shengChan.getAccomplishNum();
        shengChanService.updateAccomplishNum(id, sumNum);
        if(sumNum == shengChan.getNum()){
            shengChanService.updatState(id,"生产完成");
        }else {
            shengChanService.updatState(id,"任务下发");
        }

        UserProduct userProduct = new UserProduct();
        userProduct.setBigDrawing(shengChan.getBigDrawing());
        userProduct.setDateStartProduct(shengChan.getStartDate());
        userProduct.setDateInProduct(new Date());
        userProduct.setDrawing(shengChan.getDrawing());
        userProduct.setNum(num);
        userProduct.setShengChan(shengChan);
        userProduct.setSaleList(shengChan.getSaleList());
        userProduct.setProcess(shengChan.getProcess());
        userProduct.setXiangmuId(shengChan.getSaleList().getXiangmuId());
        userProduct.setUser((User) session.getAttribute("currentUser")); //获取当前登录用户对象);
        userProduct.setCzGongShi(shengChan.getCzGongShi());
        userProduct.setZbGongShi(shengChan.getZbGongShi());

        userProductService.save(userProduct);

        map.put("id",id);
        map.put("num",num);
        map.put("success",true);
        return map;
    }


    /**
     * 更新完成数量 保存生产记录(多选)
     * @param id
     * @param num
     * @param session
     * @return
     */
    @RequestMapping("/updateAccomplishNumDuoXuan")
    public Map<String,Object> updateAccomplishNumDuoXuan(Integer []id,Integer num,HttpSession session){
        Map<String,Object> map = new HashMap<>();
        System.out.println("******************************");
        System.out.println(id);
        System.out.println("******************************");
        for(int i = 0;i<id.length;i++){
            ShengChan shengChan = shengChanService.findOne(id[i]);
            String xiaotuCode = shengChan.getXiaotuCode();
            Integer code = shengChan.getCode();

            if(shengChan.getCode() == 1){
                if(num > (shengChan.getNum()-shengChan.getAccomplishNum())){
                    map.put("errorInfo", "输入数量有误！");
                    map.put("success", false);
                    return map;
                }
            }else {
                if((num+shengChan.getAccomplishNum())>shengChanService.selectBeforeProcess(xiaotuCode,code-1)){
                    map.put("errorInfo", "输入数量有误！");
                    map.put("success", false);
                    return map;
                }
            }

            Integer sumNum = num + shengChan.getAccomplishNum();
            shengChanService.updateAccomplishNum(id[i], sumNum);
            if(sumNum == shengChan.getNum()){
                shengChanService.updatState(id[i],"生产完成");
            }else {
                shengChanService.updatState(id[i],"任务下发");
            }

            UserProduct userProduct = new UserProduct();
            userProduct.setDateStartProduct(shengChan.getStartDate());
            userProduct.setBigDrawing(shengChan.getBigDrawing());
            userProduct.setDateInProduct(new Date());
            userProduct.setNum(num);
            userProduct.setDrawing(shengChan.getDrawing());
            userProduct.setShengChan(shengChan);
            userProduct.setSaleList(shengChan.getSaleList());
            userProduct.setXiangmuId(shengChan.getSaleList().getXiangmuId());
            userProduct.setProcess(shengChan.getProcess());
            userProduct.setUser((User) session.getAttribute("currentUser")); //获取当前登录用户对象);
            userProduct.setZbGongShi(shengChan.getZbGongShi());
            userProduct.setCzGongShi(shengChan.getCzGongShi());

            userProductService.save(userProduct);

        /*    map.put("id",id);
            map.put("num",num);*/
            map.put("success",true);
        }

        return map;
    }

    /**
     * 生产任务界面查询
     * @param xuanze
     * @param code
     * @return
     */
    @RequestMapping("/selectRenwu")
    public Map<String,Object> selectRenwu(Integer xuanze,String code){
        Map<String,Object> map = new HashedMap();
        if (xuanze == 1){
            map.put("rows",shengChanService.selectByWuliao(code));
        }
        if (xuanze == 2){

            map.put("rows",shengChanService.selectByTuzhi(code));
        }
        return map;
    }

    @RequestMapping("/findBySaleListId")
    public Map<String,Object> findBySaleListId(Integer id){
        Map<String,Object> map = new HashMap<>();
        map.put("rows",shengChanService.findBySaleListId(id));
        return map;
    }

    /**
     * 设置工序开始生产
     * @param id
     * @param state
     * @return
     */
    @RequestMapping("/startProduct")
    public Map<String,Object> startProduct(Integer id,String state){
        Map<String,Object> map = new HashMap<>();

        if(shengChanService.findByState(state).size() == 1){
            map.put("error","请先生产完您已扫码的工序!");
            map.put("success",true);
        }else {
            //shengChanService.updatState(id,state);
            ShengChan shengChan = shengChanService.findOne(id);
            shengChan.setStartDate(new Date());
            shengChan.setState(state);
            shengChanService.save(shengChan);
            map.put("success",true);
        }
        return map;
    }

    /**
     * 设置工序开始生产(多选)
     * @param id
     * @param state
     * @return
     */
    @RequestMapping("/startProductDuoXuan")
    public Map<String,Object> startProductDuoXuan(Integer []id,String state){
        Map<String,Object> map = new HashMap<>();
        for (int i = 0;i<id.length;i++){
            /*if(shengChanService.findByState(state).size() >0){
                map.put("error","请先生产完您已扫码的工序!");
                map.put("success",true);
            }else {
            }*/
            ShengChan shengChan = shengChanService.findOne(id[i]);
            shengChan.setStartDate(new Date());
            shengChan.setState(state);
            shengChanService.save(shengChan);
        }
        map.put("success",true);
        return map;
    }

}

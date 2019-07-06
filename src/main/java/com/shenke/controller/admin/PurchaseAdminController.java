package com.shenke.controller.admin;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shenke.entity.Purchase;
import com.shenke.entity.PurchaseProduct;
import com.shenke.service.PurchaseProductService;
import com.shenke.service.PurchaseService;
import com.shenke.util.DateUtil;
import com.shenke.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 采购单Controller
 * @Param:
 * @return:
 * @Author: Andy
 * @Date:
 */
@RestController
@RequestMapping("/admin/purchase")
public class PurchaseAdminController {

    @Resource
    private PurchaseService purchaseService;

    @Resource
    private PurchaseProductService purchaseProductService;

    /**
     *保存采购单
     */
    @RequestMapping("/save")
    public Map<String,Object> save(String purchaseNumber, String purchaseAgent, String purchaseDate,
                                   Double sumWeightCol, Double sumMoneyCol, String remark, String goodsJson) throws ParseException {
        Map<String,Object> map = new HashMap<>();

        System.out.println(purchaseDate);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = sdf.parse(purchaseDate);

        System.out.println(date);


        Purchase purchase = new Purchase();
        purchase.setPurchaseNumber(purchaseNumber);
        purchase.setPurchaseAgent(purchaseAgent);
        purchase.setPurchaseDate(date);
        purchase.setRemark(remark);
        purchase.setSumMoney(sumMoneyCol);
        purchase.setSumWeight(sumWeightCol);

        Gson gson = new Gson();
        List<PurchaseProduct> list = gson.fromJson(goodsJson, new TypeToken<List<PurchaseProduct>>() {
        }.getType());
        for (PurchaseProduct purchaseProduct: list) {
            purchaseProduct.setPurchase(purchase);
        }

        System.out.println(purchase);

        purchaseService.save(purchase);
        purchaseProductService.save(list);

        map.put("success",true);
        return map;
    }

    /**
     * 获取进货单号
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/genCode")
    public String genCode() throws Exception {
        StringBuffer code = new StringBuffer("CG");
        code.append(DateUtil.getCurrentDateStr());
        String purchaseNumber = purchaseService.getTodayMaxPurchaseNumber();
        if (purchaseNumber != null) {
            code.append(StringUtil.formatCode(purchaseNumber));
        } else {
            code.append("0001");
        }
        return code.toString();
    }

    /**
     * 查询所有的采购信息
     * @param purchase
     * @return
     */
    @RequestMapping("/listPurchase")
    public Map<String,Object> listPurchase(Purchase purchase){
        Map<String,Object> map = new HashMap<>();
        List<Purchase> list = purchaseService.listPurchase(purchase);
        map.put("rows",list);
        return map;
    }

    /**
     * 根据采购单查询商品信息
     * @param id
     * @return
     */
    @RequestMapping("/listProductByPurchase")
    public Map<String,Object> listProductByPurchase(Integer id){
        System.out.println(id);
        Map<String,Object> map = new HashMap<>();
        List<PurchaseProduct> list = purchaseProductService.listProductByPurchase(id);
        System.out.println(list);
        map.put("rows",list);
        return map;
    }

    /**
     * 根据单据号查询订单
     * @param purchaseNumber
     * @return
     */
    @RequestMapping("/findByPurchaseNumber")
    public Map<String,Object> findByPurchaseNumber(String purchaseNumber){
        Map<String,Object> map = new HashMap<>();
        List<Purchase> list = purchaseService.findByPurchaseNumber(purchaseNumber);
        map.put("rows",list);
        return map;
    }
}
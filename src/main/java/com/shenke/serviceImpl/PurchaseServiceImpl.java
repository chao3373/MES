package com.shenke.serviceImpl;

import com.shenke.entity.Purchase;
import com.shenke.repository.PurchaseRepository;
import com.shenke.service.PurchaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("purchaseService")
public class PurchaseServiceImpl implements PurchaseService {

    @Resource
    private PurchaseRepository purchaseRepository;

    /**
     * @Description: 获取当天最大的采购单号
     * @Param:
     * @return:
     * @Author: Andy
     * @Date:
     */
    @Override
    public String getTodayMaxPurchaseNumber() {
        return purchaseRepository.getTodayMaxPurchaseNumber();
    }

    @Override
    public void save(Purchase purchase) {
        System.out.println("***************impl*****************");
        System.out.println(purchase);
        System.out.println("***************impl*****************");
        purchaseRepository.save(purchase);
    }

    @Override
    public List<Purchase> listPurchase(Purchase purchase) {
        return purchaseRepository.findAll();
    }

    @Override
    public List<Purchase> findByPurchaseNumber(String purchaseNumber) {
        return purchaseRepository.findByPurchaseNumber(purchaseNumber);
    }
}

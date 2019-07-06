package com.shenke.serviceImpl;

import com.shenke.entity.PurchaseProduct;
import com.shenke.repository.PurchaseProductRepository;
import com.shenke.service.PurchaseProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("purchaseProductService")
public class PurchaseProductServiceImpl implements PurchaseProductService {

    @Resource
    private PurchaseProductRepository purchaseProductRepository;

    @Override
    public void save(List<PurchaseProduct> list) {
        for (PurchaseProduct purchaseProduct: list) {
            purchaseProductRepository.save(purchaseProduct);
        }
    }

    @Override
    public List<PurchaseProduct> listProductByPurchase(Integer id) {
        return purchaseProductRepository.listProductByPurchase(id);
    }
}

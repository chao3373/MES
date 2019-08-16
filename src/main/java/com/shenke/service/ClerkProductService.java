package com.shenke.service;

import com.shenke.entity.ClerkProduct;

import java.util.List;
import java.util.Map;

public interface ClerkProductService {

    void save(ClerkProduct clerkProduct);

    List<ClerkProduct> findAll();

    public List<ClerkProduct> findShengchan(ClerkProduct clerkProduct,String dateInProducedd);
}

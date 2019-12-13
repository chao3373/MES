package com.shenke.service;


import com.shenke.entity.UserProduct;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserProductService {

    public void save(UserProduct userProduct);

    public void delectByUserId(Integer id);

    public List<UserProduct> list(UserProduct userProduct);


    List<UserProduct> findAll();

    void deleteByProcessIds(Integer[] ids);
}

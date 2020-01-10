package com.shenke.service;


import com.shenke.entity.UserProduct;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserProductService {

    public void save(UserProduct userProduct);

    public void delectByUserId(Integer id);

    public Map<String,Object> list(Integer process_id,Integer processGroup,String user_trueName,String btime,String etime,Integer page,Integer rows);


    List<UserProduct> findAll();

    void deleteByProcessIds(Integer[] ids);
}

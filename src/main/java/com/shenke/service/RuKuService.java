package com.shenke.service;

import com.shenke.entity.RuKu;

import java.util.List;

public interface RuKuService {

    public void save(RuKu ruku);

    public List<RuKu> list();

    public void updateState(Integer id);

    public RuKu findById(Integer id);

    RuKu findBySaleListId(Integer saleListId);

    public List<RuKu> findByoutCode(String outCode);
}

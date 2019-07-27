package com.shenke.service;

import com.shenke.entity.Supplier;

import java.util.List;

public interface SupplierService {

    void save(Supplier supplier);

    List<Supplier> list();

    void deleteByIds(Integer[] ids);

    List<Supplier> selectByName(String s);
}

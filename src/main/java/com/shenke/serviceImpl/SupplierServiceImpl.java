package com.shenke.serviceImpl;

import com.shenke.entity.SaleList;
import com.shenke.entity.Supplier;
import com.shenke.repository.SupplierRepository;
import com.shenke.service.SupplierService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/***
 * 供应商Sercive实现类
 */
@Service("supplierService")
public class SupplierServiceImpl implements SupplierService {

    @Resource
    private SupplierRepository supplierRepository;

    @Override
    public List<Supplier> list(Supplier supplier) {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier findBySupplierName(String name) {
        return supplierRepository.findBySupplierName(name);
    }

    @Override
    public void save(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    @Override
    public Supplier findById(Integer id) {
        return supplierRepository.getOne(id);
    }

    @Override
    public void delete(Integer id) {
        supplierRepository.deleteById(id);
    }

    @Override
    public List<Supplier> findByName(String string) {
        return supplierRepository.findByName(string);
    }
}

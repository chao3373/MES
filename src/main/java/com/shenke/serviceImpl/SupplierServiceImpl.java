package com.shenke.serviceImpl;

import com.shenke.entity.Supplier;
import com.shenke.repository.SupplierRepository;
import com.shenke.service.SupplierService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("supplierService")
public class SupplierServiceImpl implements SupplierService {

    @Resource
    private SupplierRepository supplierRepository;

    /**
     * 保存供应商信息
     * @param supplier
     */
    @Override
    public void save(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    /***
     * 查询所有供应商信息
     * @return
     */
    @Override
    public List<Supplier> list() {
        return supplierRepository.findAll();
    }

    /***
     * 根据id数组删除
     * @param ids
     */
    @Override
    public void deleteByIds(Integer[] ids) {
        for (int i = 0; i < ids.length; i++) {
            supplierRepository.deleteById(ids[i]);
        }
    }

    /***
     * 根据名称模糊查询
     * @param s
     * @return
     */
    @Override
    public List<Supplier> selectByName(String s) {
        return supplierRepository.selectByName(s);
    }
}

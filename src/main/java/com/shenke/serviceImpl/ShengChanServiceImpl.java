package com.shenke.serviceImpl;

import com.shenke.entity.ShengChan;
import com.shenke.repository.ShengChanRepository;
import com.shenke.service.ShengChanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("shengChanService")
@Transactional
public class ShengChanServiceImpl implements ShengChanService {

    @Resource
    private ShengChanRepository shengChanRepository;

    @Override
    public void save(ShengChan shengChan) {
        shengChanRepository.save(shengChan);
    }

    @Override
    public List<ShengChan> listProduct() {
        return shengChanRepository.listProduct();
    }

    @Override
    public List<ShengChan> showInProcessProduct(Integer[] arr) {
        return shengChanRepository.showInProcessProduct(arr);
    }

    @Override
    public Integer selectBeforeProcess(String xiaotuCode, int i) {
        return shengChanRepository.selectBeforeProcess(xiaotuCode,i);
    }

    @Override
    public ShengChan findOne(Integer id) {
        return shengChanRepository.findById(id).get();
    }

    @Override
    public void updateAccomplishNum(Integer id, Integer num) {
        shengChanRepository.updateAccomplishNum(id,num);
    }

    @Override
    public List<ShengChan> selectByWuliao(String code) {
        return shengChanRepository.selectByWuliao(code);
    }

    @Override
    public List<ShengChan> selectByTuzhi(String code) {
        return shengChanRepository.selectByTuzhi(code);
    }

    @Override
    public Integer findMaxCode(String xiaotuCode) {
        return shengChanRepository.findMaxCode(xiaotuCode);
    }

    @Override
    public void updatState(Integer id) {
        shengChanRepository.updatState(id);
    }
}

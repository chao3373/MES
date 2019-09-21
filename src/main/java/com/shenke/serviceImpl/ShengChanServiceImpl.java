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
}

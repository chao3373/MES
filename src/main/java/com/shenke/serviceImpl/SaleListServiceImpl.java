package com.shenke.serviceImpl;

import com.shenke.entity.DrawingProcess;
import com.shenke.entity.SaleList;
import com.shenke.repository.SaleListRepository;
import com.shenke.service.SaleListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("SaleListService")
@Transactional
public class SaleListServiceImpl implements SaleListService {

    @Resource
    private SaleListRepository saleListRepository;

    @Override
    public void save(List<SaleList> plgList) {
        for(SaleList saleList : plgList){
            saleListRepository.save(saleList);
        }
    }

    @Override
    public List<SaleList> xiadan() {
        return saleListRepository.xiadan();
    }

    @Override
    public void addCunZai(Integer id) {
        saleListRepository.addCunZai(id);
    }

    @Override
    public void setState(Integer id, String state ) {
        System.out.println("****************************");
        System.out.println(id + state);
        System.out.println("****************************");
        saleListRepository.setState(id,state);
    }

    @Override
    public void setPrepareTime(Integer id,Double prepareTime) {
        saleListRepository.setPrepareTime(id,prepareTime);
    }


    @Override
    public List<SaleList> findByState(String state) {
        return saleListRepository.findByState(state);
    }


}

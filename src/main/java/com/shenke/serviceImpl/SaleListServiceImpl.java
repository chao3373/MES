package com.shenke.serviceImpl;

import com.shenke.entity.DrawingProcess;
import com.shenke.entity.SaleList;
import com.shenke.repository.SaleListRepository;
import com.shenke.service.SaleListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("saleListService")
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
        saleListRepository.setState(id,state);
    }

    @Override
    public void setOpenNum(Integer id, String openNum) {
        saleListRepository.setOpenNum(id,openNum);
    }

    @Override
    public void setPrepareTime(Integer id,Double prepareTime) {
        saleListRepository.setPrepareTime(id,prepareTime);
    }


    @Override
    public List<SaleList> findByState(String state) {
        return saleListRepository.findByState(state);
    }

    @Override
    public String getMaxOpenNum() {
        return saleListRepository.getMaxOpenNum();
    }

    @Override
    public SaleList findById(Integer id) {
        return saleListRepository.findById(id).get();
    }

    @Override
    public String getTodayMaxSaleNumber() {
        return saleListRepository.getTodayMaxSaleNumber();
    }

    @Override
    public List<SaleList> urgent() {
        return saleListRepository.urgent();
    }

    @Override
    public void setRemark(Integer id, Integer remark) {
        saleListRepository.setRemark(id,remark);
    }

}

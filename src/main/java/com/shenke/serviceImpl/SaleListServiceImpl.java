package com.shenke.serviceImpl;

import com.shenke.entity.BigDrawing;
import com.shenke.entity.DrawingProcess;
import com.shenke.entity.SaleList;
import com.shenke.repository.BigDrawingRepository;
import com.shenke.repository.SaleListRepository;
import com.shenke.service.SaleListService;
import com.shenke.util.StringUtil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Service("saleListService")
@Transactional
public class SaleListServiceImpl implements SaleListService {

    @Resource
    private SaleListRepository saleListRepository;

    @Resource
    private BigDrawingRepository bigDrawingRepository;

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
        System.out.println("*****************************");
        System.out.println(id +  state);
        System.out.println("*****************************");
        saleListRepository.setState(id,state);
    }

    @Override
    public void setOpenNum(Integer id, String openNum) {
        saleListRepository.setOpenNum(id,openNum);
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

    @Override
    public List<SaleList> findAll() {
        return saleListRepository.findAll();
    }

    @Override
    public List<SaleList> dingDanZhuiZong(SaleList saleList,String saleDated,Integer yaoqiu) {
        return saleListRepository.findAll(new Specification<SaleList>() {
            @Override
            public Predicate toPredicate(Root<SaleList> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if(StringUtil.isNotEmpty(saleDated)){
                    try {
                        predicate.getExpressions().add(cb.equal(root.get("saleDate"), new SimpleDateFormat("yyy-MM-dd").parse(saleDated)));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if(StringUtil.isNotEmpty(saleList.getSaleNumber())){
                    predicate.getExpressions().add(cb.equal(root.get("saleNumber"),saleList.getSaleNumber()));
                }
                if(StringUtil.isNotEmpty(saleList.getShenqingNumber())){
                    predicate.getExpressions().add(cb.equal(root.get("shenqingNumber"),saleList.getShenqingNumber()));
                }
                if(StringUtil.isNotEmpty(saleList.getTuzhiId())){
                    predicate.getExpressions().add(cb.equal(root.get("tuzhiId"),saleList.getTuzhiId()));
                }
                if(StringUtil.isNotEmpty(saleList.getXiangmuId())) {
                    predicate.getExpressions().add(cb.equal(root.get("xiangmuId"),saleList.getXiangmuId()));
                }
                if(saleList.getWuliaoId()!=null){
                    predicate.getExpressions().add(cb.equal(root.get("wuliaoId"),saleList.getWuliaoId()));
                }
                if(yaoqiu!=null && yaoqiu == 1){
                    predicate.getExpressions().add(cb.like(root.get("state"), "%合格入库%"));
                }
                return predicate;
            }
        });
    }

    @Override
    public List<SaleList> findBySaleNumber(String saleNumber) {
        return saleListRepository.findBySaleNumber(saleNumber);
    }

    @Override
    public List<SaleList> setOpenTime() {
        return saleListRepository.setOpenTime();
    }

    @Override
    public void baoCunOpenTime(Double yuGuGongShi,String wuliaoId) {
        BigDrawing bigDrawing = new BigDrawing();
        bigDrawing.setWuliaoId(wuliaoId);
        bigDrawing.setYuGuGongShi(yuGuGongShi);

        bigDrawingRepository.save(bigDrawing);
    }

    @Override
    public List<SaleList> showTuZhiOpen() {
        return saleListRepository.showTuZhiOpen();
    }

    @Override
    public void setCunZai(Integer id,String cunzai) {
        saleListRepository.setCunZai(id,cunzai);
    }

    @Override
    public List<SaleList> selectLikeWuliaoId(String wuliaoId,String state) {
        return saleListRepository.selectLikeWuliaoId(wuliaoId,state);
    }

    @Override
    public List<SaleList> selectWuliaoId(String wuliaoId) {
        return saleListRepository.selectLikeWuliaoId(wuliaoId);
    }
}

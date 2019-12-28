package com.shenke.serviceImpl;

import com.shenke.entity.BigDrawing;
import com.shenke.entity.DrawingProcess;
import com.shenke.entity.SaleList;
import com.shenke.repository.BigDrawingRepository;
import com.shenke.repository.SaleListRepository;
import com.shenke.service.SaleListService;
import com.shenke.util.GetResultUtils;
import com.shenke.util.LogUtil;
import com.shenke.util.StringUtil;
import com.sun.javafx.collections.MappingChange;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("saleListService")
@Transactional
public class SaleListServiceImpl implements SaleListService {

    @Resource
    private SaleListRepository saleListRepository;

    @Resource
    private BigDrawingRepository bigDrawingRepository;

    @PersistenceContext
    private EntityManager entityManager;

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
    public void setRemark(Integer []Ids, Integer remark) {
        saleListRepository.setRemark(Ids,remark);
    }

    @Override
    public List<SaleList> findAll() {
        return saleListRepository.findAll();
    }

    @Override
    public Map<String,Object> dingDanZhuiZong(SaleList saleList,String saleDated,String referDated,Integer page, Integer rows) {
        Map<String,Object> map = new HashMap<>();

        System.out.println("******************************************");
        System.out.println(saleList);
        System.out.println(referDated);
        System.out.println(saleDated);
        System.out.println(page);
        System.out.println(rows);
        System.out.println("******************************************");

        String selectSqlStar = "select " +
                "id," +
                "remark," +
                "sale_number as saleNumber," +
                "hang_hao as hangHao," +
                "wuliao_id as wuliaoId," +
                "xiangmu_id as xiangmuId," +
                "shenqing_number as shenqingNumber," +
                "kucunzuzhi," +
                "state," +
                "num," +
                "sale_date as saleDate," +
                "refer_date as referDate " +
                "from t_sale_list where true ";

        String pg = "";
        if (page != null && rows != null) {
            Integer star = (page - 1) * rows;
            pg += " limit " + star + "," + rows + "";
        }
        String sql = "";

        if(StringUtil.isNotEmpty(saleList.getSaleNumber())){
            sql += "and sale_number = '" + saleList.getSaleNumber() + "'";
        }
        if(StringUtil.isNotEmpty((saleList.getShenqingNumber()))){
            sql += "and shenqing_number = '" + saleList.getShenqingNumber() + "'";
        }
        if(StringUtil.isNotEmpty((saleList.getXiangmuId()))){
            sql += "and xiangmu_id = '" + saleList.getXiangmuId() + "'";
        }
        if(StringUtil.isNotEmpty(saleList.getWuliaoId())){
            sql += "and wuliao_id like '%"+ saleList.getWuliaoId() +"%'";
        }
        if(StringUtil.isNotEmpty(saleDated)){
            sql += "and sale_date = '" + saleDated + "'";
        }
        if(StringUtil.isNotEmpty(referDated)){
            sql += "and refer_date '" + referDated + "'";
        }

        System.out.println("查询所有的sql语句：");
        LogUtil.printLog(selectSqlStar + sql);

        List result = GetResultUtils.getResult(selectSqlStar + sql + pg, entityManager);

        map.put("rows",result);
        map.put("success",true);
        map.put("total",GetResultUtils.getResult(selectSqlStar + sql, entityManager).size());
        return map;
    }

    @Override
    public List<SaleList> findBySaleNumber(String saleNumber) {
        System.out.println("****************");
        System.out.println(saleNumber);
        System.out.println("****************");
        return saleListRepository.findBySaleNumber(saleNumber);
    }

    @Override
    public List<SaleList> setOpenTime() {
        return saleListRepository.setOpenTime();
    }

    @Override
    public Date findminReferDate(String wuliaoId) {
        return saleListRepository.findminReferDate(wuliaoId);
    }

    @Override
    public void baoCunOpenTime(Double yuGuGongShi,SaleList saleList) {
        BigDrawing bigDrawing = new BigDrawing();
        bigDrawing.setWuliaoId(saleList.getWuliaoId());
        bigDrawing.setTuZhiName(saleList.getTuzhiName());
        bigDrawing.setTuZhiId(saleList.getTuzhiId());
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
        return saleListRepository.selectWuliaoId(wuliaoId);
    }

    @Override
    public String findCunZaiByWuliaoId(String wuliaoId) {
        String []cunzai = saleListRepository.findCunZaiByWuliaoId(wuliaoId);
        return cunzai[0];
    }

    @Override
    public void setOpenState(String wuliaoId, String state) {
        saleListRepository.setOpenState(wuliaoId,state);
    }

    /*@Override
    public List<SaleList> notSaleNumber(SaleList saleList) {
        System.out.println("*****************predicate*****************");
        System.out.println();
        System.out.println(saleListRepository.notSaleNumber(saleList.getNum(),saleList.getWuliaoId()));
        System.out.println("*****************predicate*****************");
        return saleListRepository.notSaleNumber(saleList.getNum(),saleList.getWuliaoId());
          *//*  return saleListRepository.findAll(new Specification<SaleList>() {
            @Override
            public Predicate toPredicate(Root<SaleList> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if(saleList != null){
                    if(saleList.getNum() != null){
                        predicate.getExpressions().add(cb.equal(root.get("num"),saleList.getNum()));
                    }
                    if(StringUtil.isNotEmpty(saleList.getWuliaoId())){
                        predicate.getExpressions().add(cb.equal(root.get("wuliaoId"), saleList.getWuliaoId()));
                    }
                    predicate.getExpressions().add(cb.like(root.get("saleNumber"), "无订单%"));
                }

                System.out.println("*****************predicate*****************");
                System.out.println(predicate);
                System.out.println("*****************predicate*****************");
             return predicate;
            }
        });*//*
    }*/

    @Override
    public Map<String,Object> aaaa(List<SaleList> plgList) {
        Map<String,Object> map = new HashMap<>();
        List<SaleList> list = new ArrayList<>();
        List<SaleList> list2 = new ArrayList<>();
        for(SaleList saleList : plgList){
            List<SaleList> lists = saleListRepository.notSaleNumber(saleList.getNum(),saleList.getWuliaoId(),saleList.getShenqingNumber(),saleList.getXiangmuId());

            if(lists.size()!=0){
                for(SaleList saleList1 : lists){
                    list.add(saleList1);
                }
                list.add(saleList);
            }else {
                list2.add(saleList);
            }
        }
        map.put("list",list);
        map.put("list2",list2);
        return map;
    }

    @Override
    public void setCunZaiByWuliaoIds(String[] wuliaoIds,String cunzai) {
        saleListRepository.setCunZaiByWuliaoIds(wuliaoIds,cunzai);
    }

    @Override
    public List<SaleList> findByWuliaoIdTuzhiOpen(String wuliaoId) {
        return saleListRepository.findByWuliaoIdTuzhiOpen(wuliaoId);
    }

    @Override
    public String selectMaxOutCode() {
        return saleListRepository.selectMaxOutCode();
    }

    @Override
    public void saveObj(SaleList saleList1) {
        saleListRepository.save(saleList1);
    }

    @Override
    public List<SaleList> findAboutNoSaleNumber(SaleList saleList,String saleDateCha,String referDateCha) {
        return saleListRepository.findAll(new Specification<SaleList>() {
            @Override
            public Predicate toPredicate(Root<SaleList> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if(StringUtil.isNotEmpty(saleDateCha)){
                    java.util.Date saleDate = new Date();
                    try {
                        saleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(saleDateCha+" 00:00:00");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println("下單時間:"+saleDate);
                    predicate.getExpressions().add(cb.equal(root.get("saleDate"),saleDate));
                }
                if(StringUtil.isNotEmpty(referDateCha)){
                    java.util.Date referDate = new Date();
                    try {
                        referDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(referDateCha+" 00:00:00");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println("交貨日期;"+referDate);
                    predicate.getExpressions().add(cb.equal(root.get("referDate"),referDate));
                }
                if(saleList.getNum() != null){
                    predicate.getExpressions().add(cb.equal(root.get("num"), saleList.getNum()));
                }
                if(StringUtil.isNotEmpty(saleList.getShenqingNumber())){
                    predicate.getExpressions().add(cb.equal(root.get("shenqingNumber"),saleList.getShenqingNumber()));
                }
                if(StringUtil.isNotEmpty(saleList.getHangHao())){
                    predicate.getExpressions().add(cb.equal(root.get("zhiId"),saleList.getTuzhiId()));
                }
                if(StringUtil.isNotEmpty(saleList.getXiangmuId())) {
                    predicate.getExpressions().add(cb.equal(root.get("xiangmuId"),saleList.getXiangmuId()));
                }
                if(StringUtil.isNotEmpty(saleList.getWuliaoId())){
                    predicate.getExpressions().add(cb.equal(root.get("wuliaoId"),saleList.getWuliaoId()));
                }
                if(StringUtil.isNotEmpty(saleList.getKucunzuzhi())){
                    predicate.getExpressions().add(cb.equal(root.get("kucunzuzhi"),saleList.getKucunzuzhi()));
                }
                predicate.getExpressions().add(cb.like(root.get("saleNumber"),"%无订单%"));
                return predicate;
            }
        });
    }

    @Override
    public List<SaleList> listAllNoSaleNumber() {
        return saleListRepository.listAllNoSaleNumber();
    }

    @Override
    public void updateSaleNumber(String saleNumber,Integer id) {
        saleListRepository.updateSaleNumber(saleNumber,id);
    }

    /**
     * 关于saleList的多条件查询
     * @param saleList
     * @return
     */
    @Override
    public List<SaleList> selectAboutSaleList(SaleList saleList) {
        return saleListRepository.findAll(new Specification<SaleList>() {
            @Override
            public Predicate toPredicate(Root<SaleList> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();

                if(StringUtil.isNotEmpty(saleList.getXiangmuId())) {
                    predicate.getExpressions().add(cb.like(root.get("xiangmuId"),saleList.getXiangmuId()));
                }
                if(StringUtil.isNotEmpty(saleList.getWuliaoId())){
                    predicate.getExpressions().add(cb.like(root.get("wuliaoId"),saleList.getWuliaoId()));
                }
                if(StringUtil.isNotEmpty(saleList.getState())){
                    System.out.println("状态：+++" + saleList.getState() );
                    predicate.getExpressions().add(cb.equal(root.get("state"),saleList.getState()));
                }
                if(StringUtil.isNotEmpty(saleList.getCunzai())){
                    System.out.println("存在：++"+saleList.getCunzai());
                    predicate.getExpressions().add(cb.equal(root.get("cunzai"),saleList.getCunzai()));
                }
                query.groupBy(root.get("wuliaoId"));
                return predicate;
            }
        });
    }

    @Override
    public List<SaleList> findByOpenState(String state) {
        return saleListRepository.findByOpenState(state);
    }

    @Override
    public List<SaleList> findBySaleNumberXinxiLuRu(String s) {
        return saleListRepository.findBySaleNumberXinxiLuRu(s);
    }

    @Override
    public Integer[] findRemark() {
        return saleListRepository.findRemark();
    }


}

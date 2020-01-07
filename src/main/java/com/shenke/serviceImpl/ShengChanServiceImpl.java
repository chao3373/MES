package com.shenke.serviceImpl;

import com.shenke.entity.ShengChan;
import com.shenke.repository.ShengChanRepository;
import com.shenke.service.ShengChanService;
import com.shenke.util.GetResultUtils;
import com.shenke.util.LogUtil;
import com.shenke.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("shengChanService")
@Transactional
public class ShengChanServiceImpl implements ShengChanService {

    @Resource
    private ShengChanRepository shengChanRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ShengChan> findByUserForState(String s) {
        return shengChanRepository.findByUserForState(s);
    }

    @Override
    public void save(ShengChan shengChan) {
        shengChanRepository.save(shengChan);
    }

    @Override
    public Map<String,Object> listProduct(Integer page, Integer rows,String saleNumber,String wuliaoId) {

        String selectSqlStart = "select " +
                "d.id as id," +
                "a.sale_number as saleNumber," +
                "b.wuliao_id as datu," +
                "b.tu_zhi_name as tuzhimingcheng," +
                "c.wuliao_id as xaiotu," +
                "a.xiangmu_id as xiangmuhao," +
                "a.shenqing_number as shenqingNumber," +
                "d.num," +
                "a.sale_date as saleDate," +
                "d.refer_date as referDate," +
                "a.remark ," +
                "d.biaoqian_code as biaoqianCode " +
                " from t_sale_list as a , t_big_drawing as b , " +
                " t_sheng_chan as d LEFT JOIN t_drawing as c ON d.drawing_id = c.id" +
                " where a.id = d.sale_list_id and b.id = d.big_drawing_id  ";

                String selectSqlEnd = " GROUP BY sale_list_id,biaoqian_code desc ORDER BY sale_list_id DESC ";

        String pg = "";
        if (page != null && rows != null) {
            pg += " LIMIT " + (page - 1) * rows + ", " + rows;
        }

        String sql = "";
        if(StringUtil.isNotEmpty(saleNumber)){
            sql += " and a.sale_number = '" + saleNumber +"'";
        }
        if(StringUtil.isNotEmpty(wuliaoId)){
            sql += " and b.wuliao_id = '" + wuliaoId + "'";
        }

        LogUtil.printLog("===查询===");
        LogUtil.printLog("查询所有信息语句：" + selectSqlStart+ sql + selectSqlEnd + pg);
        List result = GetResultUtils.getResult(selectSqlStart + sql + selectSqlEnd +pg, entityManager);

        LogUtil.printLog("===查询===");
        LogUtil.printLog("查询数量语句：" + "select count(*) from (" + selectSqlStart + sql + selectSqlEnd +")as table");
        Integer count = GetResultUtils.getInteger("select count(*) from (" + selectSqlStart+ sql + selectSqlEnd +")as e", entityManager);
        Map<String,Object> map = new HashMap<>();
        map.put("rows",result);
        map.put("total",count);
        return map;
    }

    @Override
    public List<ShengChan> showInProcessProduct(Integer[] Arr , Integer []arr) {
        if(arr.length == 0){
            return shengChanRepository.showInProcessProduct(Arr);
        }else {
            return shengChanRepository.showInProcessProductZanTing(Arr,arr);
        }


//        return shengChanRepository.showInProcessProduct(Arr,arr);
    }

    @Override
    public Integer selectBeforeProcess(String biaoqianCode, int i) {
        return shengChanRepository.selectBeforeProcess(biaoqianCode,i);
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
    public void updatState(Integer id,String state) {
        shengChanRepository.updatState(id,state);
    }

    @Override
    public List<ShengChan> findBySaleListId(Integer id) {
        return shengChanRepository.findBySaleListId(id);
    }

    @Override
    public Integer findMinAccomplishNumBySaleListId(Integer id) {
        return shengChanRepository.findMinAccomplishNumBySaleListId(id);
    }

    @Override
    public List<ShengChan> findByState(String state) {
        return shengChanRepository.findByState(state);
    }

    @Override
    public List<ShengChan> findBySaleListIdAboutDatu(Integer id) {
        return shengChanRepository.findBySaleListIdAboutDatu(id);
    }

    @Override
    public List<ShengChan> findBySaleListIdAboutXiaotu(Integer id) {
        return shengChanRepository.findBySaleListIdAboutXiaotu(id);
    }

    @Override
    public void updateDatuState(Integer saleListId) {
        shengChanRepository.updateDatuState(saleListId);
    }

    @Override
    public void deleteByProcessIds(Integer[] ids) {
        System.out.println("2");
        shengChanRepository.deleteByProcessIds(ids);
    }

    @Override
    public List<ShengChan> findBySaleList(Integer id) {
        return shengChanRepository.findBySaleList(id);
    }

    @Override
    public List<ShengChan> findByXiaotuhao(Integer id) {
        return shengChanRepository.findByXiaotuhao(id);
    }
}

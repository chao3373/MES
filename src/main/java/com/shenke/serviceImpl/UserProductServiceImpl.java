package com.shenke.serviceImpl;

import com.shenke.entity.UserProduct;
import com.shenke.repository.UserProductRespository;
import com.shenke.service.UserProductService;
import com.shenke.util.GetResultUtils;
import com.shenke.util.LogUtil;
import com.shenke.util.StringUtil;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserProductServiceImpl implements UserProductService {

    @Resource
    private UserProductRespository userProductRespository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(UserProduct userProduct) {
        userProductRespository.save(userProduct);
    }

    @Override
    public void delectByUserId(Integer id) {
        userProductRespository.delectByUserId(id);
    }

    @Override
    public Map<String,Object> list(Integer process_id,Integer processGroup,String user_trueName,String btime,String etime,Integer page,Integer rows) {
        String selectSqlStart = "select f.id as id," +
                "a.sale_number as saleNumber," +
                "a.xiangmu_id as xiangmuhao," +
                "b.wuliao_id as datu," +
                "c.wuliao_id as xiaotu," +
                "g.name as gongxu," +
                "h.process_group as gongxuzu," +
                "g.id as process_id," +
                "f.num," +
                "e.num as sumNum," +
                "f.zb_gong_shi as zbGongShi," +
                "f.cz_gong_shi as czGongShi," +
                "d.true_name as user," +
                "f.date_start_product as dateStartProduct," +
                "f.date_in_product as dateInProduct " +
                " from t_sale_list as a , t_big_drawing as b ," +
                "t_user as d, t_sheng_chan as e, t_user_product as f left join " +
                " t_drawing as c on f.drawing_id = c.id, t_process as g ,t_process_group as h where " +
                "a.id = f.sale_list_id and b.id = f.big_drawing_id " +
                "and d.id = f.user_id and e.id = f.sheng_chan_id and g.id = f.process_id and g.process_group_id = h.id";

        String sql = "";
        String pg = "";
        if (page != null && rows != null) {
            pg += " LIMIT " + (page - 1) * rows + ", " + rows;
        }

        if(process_id != null){
            sql += " and g.id = " + process_id;
        }
        if(processGroup != null){
            sql += " and h.id = " + processGroup;
        }
        if(StringUtil.isNotEmpty(user_trueName)){
            sql += " and d.true_name = '" + user_trueName + "'";
        }
        if(StringUtil.isNotEmpty(btime) && StringUtil.isNotEmpty(etime)){
            btime = btime + " 00:00:00";
            etime = etime + " 23:59:59";
            sql += " and f.date_start_product > '" + btime + "' and f.date_in_product < '" + etime + "'";
        }

        LogUtil.printLog("===生产情况查询===");
        LogUtil.printLog("查询所有信息语句：" + selectSqlStart + sql + pg);
        List result = GetResultUtils.getResult(selectSqlStart + sql + pg, entityManager);

        LogUtil.printLog("查询总条数语句：" + "select count(*) from (" + selectSqlStart + sql +") as h ");
        Integer count = GetResultUtils.getInteger("select count(*) from (" + selectSqlStart + sql +") as h ", entityManager);


        //总数量总工时
        Integer sumNum = GetResultUtils.getInteger("select sum(num) from (" + selectSqlStart + sql +") as i ", entityManager);
        Double sumGongShi = GetResultUtils.getDouble("select sum(num*(zbGongShi+czGongShi)) from (" + selectSqlStart + sql +") as j ", entityManager);

        System.out.println("+++++++++++++++++++++++");
        System.out.println("总数："+sumNum);
        System.out.println("总工时："+sumGongShi);
        System.out.println("+++++++++++++++++++++++");
        Map<String,Object> map = new HashMap<>();
        map.put("rows",result);
        map.put("total",count);
        map.put("sumNum",sumNum);
        map.put("sumGongShi",sumGongShi);
        return map;
    }

    @Override
    public List<UserProduct> findAll() {
        return userProductRespository.findAll();
    }

    @Override
    public void deleteByProcessIds(Integer[] ids) {
        System.out.println("1");
        userProductRespository.deleteByProcessIds(ids);
    }


}

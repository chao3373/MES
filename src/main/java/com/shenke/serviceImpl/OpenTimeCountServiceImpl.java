package com.shenke.serviceImpl;

import com.shenke.entity.OpenTimeCount;
import com.shenke.repository.OpenTimeCountRepository;
import com.shenke.service.OpenTimeCountService;
import com.shenke.util.GetResultUtils;
import com.shenke.util.LogUtil;
import com.shenke.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("outTimeCountService")
@Transactional
public class OpenTimeCountServiceImpl implements OpenTimeCountService {

    @Resource
    private OpenTimeCountRepository openTimeCountRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(OpenTimeCount openTimeCount) {
        openTimeCountRepository.save(openTimeCount);
    }

    @Override
    public Map<String, Object> selectOpenTime(String userName, String wuliaoId, String btime, String etime, Integer page, Integer rows) {
        String selectSqlStart = "select id, " +
                "wuliao_id as wuliaoId," +
                "user_name as userName," +
                "yu_gu_gong_shi as yuGuGongShi," +
                "shi_ji_gong_shi as shiJiGongShi," +
                "start_date as startDate," +
                "stop_date as stopDate " +
                " from t_open_time_count where true ";

        String sql = "";
        String pg = "";
        if (page != null && rows != null) {
            pg += " LIMIT " + (page - 1) * rows + ", " + rows;
        }


        if(StringUtil.isNotEmpty(userName)){
            sql += " and user_name = '" + userName + "'";
        }
        if(StringUtil.isNotEmpty(wuliaoId)){
            sql += " and wuliao_id = '" + wuliaoId + "'";
        }
        if(StringUtil.isNotEmpty(btime) && StringUtil.isNotEmpty(etime)){
            btime = btime + " 00:00:00";
            etime = etime + " 23:59:59";
            sql += " and f.date_start_product > '" + btime + "' and f.date_in_product < '" + etime + "'";
        }

        LogUtil.printLog("===图纸展开查询===");
        LogUtil.printLog("查询所有信息语句：" + selectSqlStart + sql + pg);
        List result = GetResultUtils.getResult(selectSqlStart + sql + pg, entityManager);

        LogUtil.printLog("查询总条数语句：" + "select count(*) from (" + selectSqlStart + sql +") as h ");
        Integer count = GetResultUtils.getInteger("select count(*) from (" + selectSqlStart + sql +") as h ", entityManager);


        //总数量总工时
        Double sumShiJiGongShi = GetResultUtils.getDouble("select sum(shiJiGongShi) from (" + selectSqlStart + sql +") as i ", entityManager);
        Double sumYuGuGongShi = GetResultUtils.getDouble("select sum(yuGuGongShi) from (" + selectSqlStart + sql +") as i ", entityManager);

        Map<String,Object> map = new HashMap<>();
        map.put("rows",result);
        map.put("total",count);
        map.put("sumShiJiGongShi",sumShiJiGongShi);
        map.put("sumYuGuGongShi",sumYuGuGongShi);
        return map;
    }
}

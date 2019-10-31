package com.shenke.service;

import com.shenke.entity.ShengChan;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface ShengChanService {

    /**
     * 通过工序状态判断当前登录员工是否有未工作的工序
     * @param s
     * @return
     */
    List<ShengChan> findByUserForState(String s);

    /**
     * 保存
     * @param shengChan
     */
    public void save(ShengChan shengChan);

    /**
     * “生产任务”  界面显示
     * @return
     */
    public List<ShengChan> listProduct();

    /**
     * "工序加工" 界面显示
     * @param arr
     */
    public List<ShengChan> showInProcessProduct(Integer[] arr);

    /**
     * 查找此工序可以生产的件数（即前一工序已生产的件数）
     * @param xiaotuCode
     * @param i
     * @return
     */
    public Integer selectBeforeProcess(String xiaotuCode, int i);

    /**
     * 通过Id查询对象
     * @param id
     * @return
     */
    public ShengChan findOne(Integer id);

    /**
     * 根据Id更新完成数量
     * @param id
     * @param num
     */
    public void updateAccomplishNum(Integer id,Integer num);

    /**
     * 根据大图物料号查询
     * @param code
     * @return
     */
    public List<ShengChan> selectByWuliao(String code);

    /**
     * 根据小图物料号查询
     * @param code
     * @return
     */
    public List<ShengChan> selectByTuzhi(String code);

    /**
     * 查找同一小图编码下的最大order（顺序）
     * @param xiaotuCode
     * @return
     */
    public Integer findMaxCode(String xiaotuCode);

    /**
     * 根据id修改状态
     * @param id
     */
    public void updatState(Integer id,String state);

    public  List<ShengChan> findBySaleListId(Integer id);

    /**
     * 查找该订单id下所有工序中完成数量最少的工序
     * @param id
     * @return
     */
    public Integer findMinAccomplishNumBySaleListId(Integer id);

    /**
     * 根据状态查找
     * @param state
     * @return
     */
    List<ShengChan> findByState(String state);
}
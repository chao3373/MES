package com.shenke.service;

import com.shenke.entity.ShengChan;
import java.util.Map;

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
    public Map<String,Object> listProduct(Integer page, Integer rows,String saleNumber,String wuliaoId);

    /**
     * "工序加工" 界面显示
     * @param arr
     */
    public List<ShengChan> showInProcessProduct(Integer[] Arr,Integer []arr);

    /**
     * 查找此工序可以生产的件数（即前一工序已生产的件数）
     * @param biaoqianCode
     * @param i
     * @return
     */
    public Integer selectBeforeProcess(String biaoqianCode, int i);

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

    /**
     * 通过salelistId 查询大图生产情况
     * @param id
     * @return
     */
    List<ShengChan> findBySaleListIdAboutDatu(Integer id);

    /**
     * 通过saleListId 查询小图生产情况
     * @param id
     * @return
     */
    List<ShengChan> findBySaleListIdAboutXiaotu(Integer id);

    /**
     * 通过salelistId修改大图的状态  （从“不可生产”---> “未生产”）
     * @param saleListId
     */
    void updateDatuState(Integer saleListId);

    void deleteByProcessIds(Integer[] ids);

    /**
     * 销售订单追踪界面根据saleList 的 id 查找该id下所有的工序信息
     * @param id
     * @return
     */
    List<ShengChan> findBySaleList(Integer id);

    List<ShengChan> findByXiaotuhao(Integer id);
}

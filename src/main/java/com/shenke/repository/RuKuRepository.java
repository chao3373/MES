package com.shenke.repository;

import com.shenke.entity.RuKu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RuKuRepository extends JpaRepository<RuKu,Integer> , JpaSpecificationExecutor<RuKu> {
    /**
     * 查询状态为准备入库的信息
     * @return
     */
    @Query(value = "select * from t_ru_ku where state = '准备入库'",nativeQuery = true)
    public List<RuKu> list();

    /**
     * 修改状态未 “入库”
     * @param id
     */
    @Modifying
    @Query(value = "update t_ru_ku set state = '入库' where id = ?1",nativeQuery = true)
    public void updateState(Integer id);

    /**
     * 根据订单id查找
     * @param saleListId
     * @return
     */
    @Query(value = "select * from t_ru_ku where sale_list_id = ?1",nativeQuery = true)
    RuKu findBySaleListId(Integer saleListId);

    /**
     * 通过大图图纸查找
     * @param outCode
     * @return
     */
    @Query(value = "select * from t_ru_ku where out_code = ?1",nativeQuery = true)
    List<RuKu> findByoutCode(String outCode);
}

package com.shenke.repository;

import com.shenke.entity.ShowGongShi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ShowGongShiRepository extends JpaRepository<ShowGongShi,Integer> , JpaSpecificationExecutor<Integer> {


    /**
     * 设置是否显示工时
     * @param show
     */
    @Modifying
    @Query(value = "update t_show_gong_shi SET is_show = ?1 ",nativeQuery = true)
    void updateShowGongShi(Integer show);


}

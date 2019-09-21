package com.shenke.repository;

import com.shenke.entity.ShengChan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShengChanRepository extends JpaRepository<ShengChan,Integer> , JpaSpecificationExecutor<ShengChan> {

    /**
     * "任务下发"界面显示的信息
     * @return
     */
    @Query(value = "select * from  t_sheng_chan where state = '任务下发' group by drawing_id",nativeQuery = true)
    public List<ShengChan> listProduct();

    /**
     * "工序加工" 界面显示
     * @param arr
     * @return
     */
    @Query(value = "select * from  t_sheng_chan where state = '任务下发' and process_id in ?1 order by refer_date asc",nativeQuery = true)
    public List<ShengChan> showInProcessProduct(Integer[] arr);
}

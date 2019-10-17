package com.shenke.repository;

import com.shenke.entity.Process;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProcessRepository extends JpaRepository<Process,Integer>, JpaSpecificationExecutor<Process> {

    /**
     * 下拉模糊查询工序
     * @param string
     * @return
     */
    @Query(value="select * from t_process where name like ?1", nativeQuery=true)
    public List<Process> findProcessCombobox(String string);

    /**
     * 根据 工序组id查询
     * @param id
     * @return
     */
    @Query(value = "select * from t_process where process_group_id = ?1",nativeQuery = true)
    List<Process> findByPGId(Integer id);

    /**
     * 根据 工序组id删除
     * @param id
     */
    @Query(value = "delete from t_process where process_group_id = ?1",nativeQuery = true)
    void deleteByPGId(Integer id);

    /**
     * 根据工序名称模糊查询
     * @param s
     * @return
     */
    @Query(value = "select * from t_process where name like ?1",nativeQuery = true)
    List<Process> selectByName(String s);
}

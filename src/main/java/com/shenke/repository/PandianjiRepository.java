package com.shenke.repository;

import com.shenke.entity.Pandianji;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 盘点机Repository接口
 * @author shao
 *
 */

public interface PandianjiRepository extends JpaRepository<Pandianji, Integer>, JpaSpecificationExecutor<Pandianji> {
	/**
	 * 根据名称查找盘点机实体
	 * 
	 */
	@Query(value = "select * from t_pandianji where name =?1",nativeQuery = true)
	public Pandianji findByPandianjiName(String name);
	
	/**
	 * 下拉框模糊查询
	 * 
	 */
	
	@Query(value="select * from t_pandianji where pid like ?1",nativeQuery =true)
	public List<Pandianji> findByPid(String string);
}

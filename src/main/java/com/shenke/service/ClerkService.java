package com.shenke.service;

import java.util.List;
import org.springframework.data.domain.Sort.Direction;
import com.shenke.entity.Clerk;

/**
 * 员工Service接口
 * @author Administrator
 *
 */
public interface ClerkService {

	/**
	 * 根据dep_id查询员工
	 * @param id
	 * @return
	 */
	public  List<Clerk> findByDepId(Integer id);

	/***
	 * 根据depid删除员工信息
	 * @param id
	 */
	void deleteByDepId(Integer id);

	List<Clerk> findAll();

	void add(Clerk clerk);

	void deleteByIds(Integer[] ids);

	List<Clerk> clerkName(String clerkName);

	List<Clerk> combobox(String s);

	Clerk findById(Integer clerkId);
}

package com.shenke.service;

import com.shenke.entity.Dep;

import java.util.List;

/**
 * 部门管理Service
 * @author Administrator
 *
 */
public interface DepService {

	List<Dep> findAll();

	void add(Dep dep);

	void deleteById(Integer id);

	List<Dep> findBytext(String s);
}

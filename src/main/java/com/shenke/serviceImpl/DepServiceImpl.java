package com.shenke.serviceImpl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.shenke.entity.Dep;
import com.shenke.repository.DepRepository;
import com.shenke.service.DepService;

/**
 * 部门管理Service实现类
 * @author Administrator
 *
 */
@Service("depService")
public class DepServiceImpl implements DepService{

	@Resource
	private DepRepository depRepository;

	/***
	 * 查询所有部门信息
	 * @return
	 */
    @Override
    public List<Dep> findAll() {
        return depRepository.findAll();
    }

	/***
	 * 添加部门
	 * @param dep
	 */
	@Override
	public void add(Dep dep) {
		depRepository.save(dep);
	}

	/***
	 * 根据id删除员工信息
	 * @param id
	 */
	@Override
	public void deleteById(Integer id) {
		depRepository.deleteById(id);
	}

	/***
	 * 根据部门名称查询部门信息
	 * @param s
	 * @return
	 */
	@Override
	public List<Dep> findBytext(String s) {
		System.out.println(s);
		System.out.println(depRepository.findBytext(s));
		return depRepository.findBytext(s);
	}
}

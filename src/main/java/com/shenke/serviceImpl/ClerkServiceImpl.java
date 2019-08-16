package com.shenke.serviceImpl;

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.shenke.entity.Clerk;
import com.shenke.repository.ClerkRepository;
import com.shenke.service.ClerkService;
import com.shenke.util.StringUtil;
import org.springframework.transaction.annotation.Transactional;

/**
 * 员工Service实现类
 * 
 * @author Administrator
 *
 */
@Service("clerkService")
@Transactional
public class ClerkServiceImpl implements ClerkService {

	@Resource
	private ClerkRepository clerkRepository;

	/***
	 * 根据部门id查询员工信息
	 * @param id
	 * @return
	 */
	@Override
	public List<Clerk> findByDepId(Integer id) {
		return clerkRepository.findByDepId(id);
	}

	/**
	 * 根据部门id删除员工信息
	 * @param id
	 */
	@Override
	public void deleteByDepId(Integer id) {
		clerkRepository.deleteByDepId(id);
	}

	/***
	 * 查询所有员工信息
	 * @return
	 */
	@Override
	public List<Clerk> findAll() {
		return clerkRepository.findAll();
	}

	/***
	 * 添加员工信息
	 * @param clerk
	 */
	@Override
	public void add(Clerk clerk) {
		clerkRepository.save(clerk);
	}

	/**
	 * 根据id数组删除员工信息
	 * @param ids
	 */
	@Override
	public void deleteByIds(Integer[] ids) {
		for (int i = 0; i < ids.length; i++) {
			clerkRepository.deleteById(ids[i]);
		}
	}

	/***
	 * 根据员工姓名模糊查询员工信息
	 * @param clerkName
	 * @return
	 */
	@Override
	public List<Clerk> clerkName(String clerkName) {
		return clerkRepository.clerkName(clerkName);
	}

	@Override
	public List<Clerk> combobox(String s) {
		return clerkRepository.combobo(s);
	}

    @Override
    public Clerk findById(Integer clerkId) {
        return clerkRepository.findById(clerkId).get();
    }

}

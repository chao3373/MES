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
	
	@Override
	public List<Dep> findByParentId(Integer parentId) {
		return depRepository.findByParentId(parentId);
	}
	
	@Override
	public Dep findById(Integer id) {
		return depRepository.getOne(id);
	}

	@Override
	public void save(Dep dep) {
		depRepository.save(dep);
	}

	@Override
	public void delete(Integer id) {
		depRepository.deleteById(id);
	}
	
}

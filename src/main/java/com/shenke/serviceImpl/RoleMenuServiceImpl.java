package com.shenke.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shenke.entity.RoleMenu;
import com.shenke.repository.RoleMenuRepository;
import com.shenke.service.RoleMenuService;

import java.util.List;

@Service("roleMenuService")
@Transactional
public class RoleMenuServiceImpl implements RoleMenuService{

	@Resource
	private RoleMenuRepository roleMenuServiceRepository;
	
	@Override
	public void deleteByRoleId(Integer id) {
		roleMenuServiceRepository.deleteByRoleId(id);
	}

	@Override
	public void save(RoleMenu roleMenu) {
		roleMenuServiceRepository.save(roleMenu);
	}

	@Override
	public void teess() {
		List<RoleMenu> all = roleMenuServiceRepository.findAll();

		System.out.println(all);
		System.out.println(all.get(0).getMenu().getId());
		System.out.println(all.get(0).getRole().getId());
		System.out.println(all.get(0).getMenu());
		System.out.println(all.get(0).getRole());
	}

}

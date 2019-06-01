package com.shenke.serviceImpl;

import com.shenke.entity.Role;
import com.shenke.repository.RoleRepository;
import com.shenke.service.RoleService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @Auther: Administrator
 * @Date: 2019/6/1 11:36
 * @Description:
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleRepository roleRepository;

    @Override
    public List<Role> findByUserId(Integer id) {
        return roleRepository.findByUserId(id);
    }

    @Override
    public Role findById(Integer roleId) {
        return roleRepository.findById(roleId).get();
    }
}

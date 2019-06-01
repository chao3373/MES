package com.shenke.serviceImpl;

import com.shenke.entity.Menu;
import com.shenke.repository.MenuRepository;
import com.shenke.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2019/6/1 11:50
 * @Description:
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuRepository menuRepository;

    @Override
    public List<Menu> findByParentIdAndRoleId(Integer parentId, Integer roleId) {
        return menuRepository.findByParentIdAndRoleId(parentId, roleId);
    }
}

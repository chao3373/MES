package com.shenke.realm;

import com.shenke.entity.Menu;
import com.shenke.entity.Role;
import com.shenke.entity.User;
import com.shenke.repository.MenuRepository;
import com.shenke.repository.RoleRepository;
import com.shenke.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Auther: Administrator
 * @Date: 2019/6/1 11:43
 * @Description:
 */
public class MyRealm extends AuthorizingRealm {

    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private MenuRepository menuRepository;


    /***
     *授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("开始授权");
        //获取当前登录的用户名
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        //根据用户名查询用户
        User user = userRepository.findByUserName(userName);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //根据用户id查询拥有的角色
        List<Role> roles = roleRepository.findByUserId(user.getId());
        //创建set集合将用户所属的角色名存放到set集合中
        Set<String> roleSet = new HashSet<>();
        for (Role role : roles) {
            roleSet.add(role.getName());
            //根据角色的id查询所能使用的菜单
            List<Menu> menuList = menuRepository.findByRoleId(role.getId());
        }
        return null;
    }

    /***
     *身份权限认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        return null;
    }
}

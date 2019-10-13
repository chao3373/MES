package com.shenke.service;

import com.shenke.entity.User;
import org.springframework.data.domain.Sort.Direction;

import java.util.List;

/**
 * 用户Service
 * @author Administrator
 *
 */
public interface UserService {

	
	/**
	 * 根据用户名查询用户
	 * @param userName
	 * @return
	 */
	public User findByUserName(String userName);

	/**
	 * 根据条件分页查询用户信息
	 * @param user
	 * @param page
	 * @param rows
	 * @param asc
	 * @param string
	 * @return
	 */
	public List<User> list(User user, Integer page, Integer rows, Direction asc, String... string);

	/**
	 * 总记录数
	 * @param user
	 * @return
	 */
	public Long getCount(User user);

	/**
	 * 根据用户id查询用户
	 * @param userId
	 * @return
	 */
	public User findById(Integer userId);

	/**
	 * 添加或修改用户信息
	 * @param user
	 */
	public void save(User user);

	/**
	 * 根据id删除用户
	 * @param id
	 */
	public void delete(Integer id);
	
	/**
	 * 根据用户名和密码判断是否存在该用户
	 * @return
	 */
	public Object findNamePsw(String name, String psw);

	/**
	 * 下拉框模糊查询
	 * @param s
	 * @return
	 */
    public List<User> combobox(String s);
}

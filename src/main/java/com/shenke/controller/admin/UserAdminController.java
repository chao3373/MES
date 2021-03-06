package com.shenke.controller.admin;

import com.shenke.entity.*;
import com.shenke.entity.Process;
import com.shenke.service.*;
import com.shenke.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台管理用户Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/user")
public class UserAdminController {
	
	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private UserRoleService userRoleService;
	
	@Resource
	private LogService logService;

	@Resource
	private UserProcessService userProcessService;

	@Resource
	private UserProductService userProductService;

	@Resource
	private ProcessService processService;
	
	/**
	 * 分页查询用户信息
	 * @param user
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	@RequiresPermissions(value="用户管理")
	public Map<String, Object> list(User user, @RequestParam(value="page",required=false)Integer page, @RequestParam(value="rows",required=false)Integer rows) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<User> userList = userService.list(user, page, rows, Direction.ASC, "id");
		for (User u : userList) {
			List<Role> roleList = roleService.findByUserId(u.getId());
			StringBuffer sb = new StringBuffer();
			for (Role r : roleList) {
				sb.append("," + r.getName());
			}

			List<UserProcess> upList = userProcessService.findByUserId(u.getId());
			StringBuffer sb2 = new StringBuffer();
			for (UserProcess up : upList){
				sb2.append(","+up.getProcess().getName());
			}
			u.setGongxus(sb2.toString().replaceFirst(",", ""));
			u.setRoles(sb.toString().replaceFirst(",", ""));
		}
		Long total = userService.getCount(user);
		resultMap.put("rows", userList);
		resultMap.put("total", total);
		logService.save(new Log(Log.SEARCH_ACTION, "查询用户信息"));
		System.out.println(resultMap);
		return resultMap;
	}
	
	/**
	 * 保存用户角色设置
	 * @param roleIds
	 * @param userId
	 * @return
	 */
	@RequestMapping("/saveRoleSet")
	@ResponseBody
	@RequiresPermissions(value="用户管理")
	public Map<String, Object> saveRoleSet(String roleIds, Integer userId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		userRoleService.deleteByUserId(userId);
		String jueSe = "";
		if (StringUtil.isNotEmpty(roleIds)) {
			String roleIdStr[] = roleIds.split(",");
			for (int i = 0; i < roleIdStr.length; i++) {
				Role role = roleService.findById(Integer.parseInt(roleIdStr[i]));
				UserRole userRole = new UserRole();
				userRole.setUser(userService.findById(userId));
				userRole.setRole(role);
				jueSe = jueSe + role.getName() + ",";
				userRoleService.save(userRole);
			}
		}
		resultMap.put("success", true);
		logService.save(new Log(Log.UPDATE_ACTION, "给"+userService.findById(userId).getTrueName()+"设置角色为："+jueSe));
		return resultMap;
	}
	
	/**
	 * 添加或者修改用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	@RequiresPermissions(value="用户管理")
	public Map<String, Object> save(User user) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(user.getId()==null) {
			if(userService.findByUserName(user.getUserName())!=null) {
				resultMap.put("success", false);
				resultMap.put("errorInfo", "用户名已经存在！");
				return resultMap;
			}
		}
		if(user.getId()!=null) {
			logService.save(new Log(Log.UPDATE_ACTION, "更新用户信息"+user));
		}else {
			logService.save(new Log(Log.ADD_ACTION, "添加用户信息"+user));
		}
		userService.save(user);
		resultMap.put("success", true);
		return resultMap;
	}
	
	/**
	 * 删除用户信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@RequiresPermissions(value=("用户管理"))
	public Map<String, Object> delete(Integer id) {
		logService.save(new Log(Log.DELETE_ACTION, "删除用户信息"+userService.findById(id)));
		Map<String, Object> resultMap = new HashMap<String, Object>();
		userRoleService.deleteByUserId(id);
		userProductService.delectByUserId(id);
		userProcessService.deleteByUserId(id);
		userService.delete(id);
		resultMap.put("success", true);
		return resultMap;
	}
	
	/**
	 * 修改密码
	 * @param newPassword
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/modifyPassword")
	@ResponseBody
	@RequiresPermissions(value="修改密码")
	public Map<String,Object> modifyPassword(String newPassword, HttpSession session)throws Exception{
		Map<String,Object> resultMap=new HashMap<>();
		User currentUser=(User) session.getAttribute("currentUser");
		User user=userService.findById(currentUser.getId());
		user.setPassword(newPassword);
		userService.save(user);
		resultMap.put("success", true);
		logService.save(new Log(Log.UPDATE_ACTION,"修改密码"));
		return resultMap;
	}
	
	/**
	 * 安全退出
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/logout")
	@RequiresPermissions(value="安全退出")
	public String logout(HttpSession session)throws Exception{
		logService.save(new Log(Log.LOGOUT_ACTION,"用户注销"));
		SecurityUtils.getSubject().logout();
		return "redirect:/login.html";
	}

	/**
	 * 下拉框模糊查询用户
	 *
	 * @param q
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/combobox")
	public List<User> combobox(String q) throws Exception {
		System.out.println("123456789");
		if (q == null) {
			q = "";
		}
		System.out.println("***************************************");
		System.out.println(userService.combobox("%" + q + "%"));
		System.out.println("***************************************");
		return userService.combobox("%" + q + "%");
	}

	/**
	 * 保存员工最优工序
	 * @return
	 */
	@RequestMapping("/saveBestProcess")
	@ResponseBody
	public Map<String,Object> saveBestProcess(Integer id,Integer processId){
		Map<String,Object> map = new HashMap<>();
		if(id == null || processId == null){
			return map;
		}
		User user = userService.findById(id);
		Process process = processService.findById(processId);

		user.setProcess(process);
		userService.save(user);
		map.put("success",true);
		logService.save(new Log(Log.UPDATE_ACTION,"给["+user.getTrueName()+"]设置最优工序："+process.getName()));
		return map;
	}


	
}

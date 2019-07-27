package com.shenke.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.shenke.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shenke.entity.Clerk;
import com.shenke.service.ClerkService;
import com.shenke.service.LogService;

/**
 * 员工管理Controller
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/clerk")
public class ClerkAdminController {

	@Resource
	private ClerkService clerkService;

	@Resource
	private LogService logService;

	/***
	 * 查询所有员工信息
	 * @return
	 */
	@RequestMapping("/list")
	public List<Clerk> list(){
		return clerkService.findAll();
	}

	/***
	 * 根据部门id查询员工信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/findByDepId")
	public Map<String, Object> findByDepId(Integer id){
		Map<String, Object> map = new HashMap<>();
		map.put("success", true);
		map.put("rows", clerkService.findByDepId(id));
		return map;
	}

	/***
	 * 添加员工信息
	 * @param clerk
	 */
	@RequestMapping("/add")
	public Map<String, Object> add(Clerk clerk){
		Map<String, Object> map = new HashMap<>();
		clerkService.add(clerk);
		map.put("success", true);
		return map;
	}

	/***
	 * 根据id数组删除员工信息
	 * @param
	 */
	@RequestMapping("/deleteByIds")
	public void deleteByIds(Integer[] ids){
		clerkService.deleteByIds(ids);
	}

	/***
	 * 根据姓名模糊查询员工信息
	 * @param clerkName
	 * @return
	 */
	@RequestMapping("/selectByName")
	public Map<String, Object> selectByName(String clerkName){
		Map<String, Object> map = new HashMap<>();
		map.put("success", true);
		if (StringUtil.isEmpty(clerkName)){
			clerkName = "";
		}
		System.out.println(clerkName);
		map.put("rows", clerkService.clerkName("%" + clerkName + "%"));
		return map;
	}

}

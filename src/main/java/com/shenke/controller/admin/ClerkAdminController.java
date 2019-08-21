package com.shenke.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.shenke.entity.Log;
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
		logService.save(new Log(Log.SEARCH_ACTION, "查询所有员工信息"));
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
		logService.save(new Log(Log.SEARCH_ACTION, "根据id查询员工信息"));
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
		logService.save(new Log(Log.SEARCH_ACTION, "添加员工信息"));
		return map;
	}

	/***
	 * 根据id数组删除员工信息
	 * @param
	 */
	@RequestMapping("/deleteByIds")
	public void deleteByIds(Integer[] ids){
		clerkService.deleteByIds(ids);
		logService.save(new Log(Log.SEARCH_ACTION, "删除员工信息"));
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
		logService.save(new Log(Log.SEARCH_ACTION, "根据员工姓名模糊查询员工信息"));
		return map;
	}

	/**
	 * 下拉框模糊查询生产员工
	 *
	 * @param q
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/combobox")
	public List<Clerk> combobox(String q) throws Exception {
		if (q == null) {
			q = "";
		}
		logService.save(new Log(Log.SEARCH_ACTION, "下拉框模糊查询员工信息"));
		return clerkService.combobox("%" + q + "%");
	}

}

package com.shenke.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.shenke.entity.Clerk;
import com.shenke.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.shenke.entity.Dep;
import com.shenke.entity.Log;
import com.shenke.service.ClerkService;
import com.shenke.service.DepService;
import com.shenke.service.LogService;

/**
 * 部门管理Controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/admin/dep")
public class DepAdminController {

    @Resource
    private DepService depService;

    @Resource
    private ClerkService clerkService;

    @Resource
    private LogService logService;

    /***
     * 查询所有部门信息
     * @return
     */
    @RequestMapping("/findAll")
    public Map<String, Object> findAll() {
        Map<String, Object> map = new HashMap<>();
        List<Dep> all = depService.findAll();
        map.put("success", true);
        map.put("data", all);
        logService.save(new Log(Log.SEARCH_ACTION, "查询所有部门信息"));
        return map;
    }

    /***
     * 添加部门
     * @param dep
     * @return
     */
    @RequestMapping("/add")
    public Map<String, Object> add(Dep dep) {
        Map<String, Object> map = new HashMap<>();
        depService.add(dep);
        map.put("success", true);
        logService.save(new Log(Log.ADD_ACTION, "添加部门"));
        return map;
    }

    /***
     * 删除部门以及部门下的员工信息
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public Map<String, Object> delete(Integer id) {
        Map<String, Object> map = new HashMap<>();
        List<Clerk> byDepId = clerkService.findByDepId(id);
        if (byDepId.size() > 0) {
			clerkService.deleteByDepId(id);
        }
        depService.deleteById(id);
        map.put("success", true);
        logService.save(new Log(Log.DELETE_ACTION, "删除部门以及部门下的员工信息"));
        return map;
    }

    /***
     * 下拉框模糊查询所有部门信息
     * @param
     * @return
     */
    @RequestMapping("/down")
    public List<Dep> down(String s){
        if (StringUtil.isEmpty(s)){
            s = "";
        }
        logService.save(new Log(Log.SEARCH_ACTION, "模糊查询所有部门信息"));
        return depService.findBytext("%" + s + "%");
    }

}

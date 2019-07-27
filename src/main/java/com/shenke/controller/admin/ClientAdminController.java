package com.shenke.controller.admin;

import com.shenke.entity.Client;
import com.shenke.entity.Log;
import com.shenke.service.ClientService;
import com.shenke.service.LogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 客户
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/client")
public class ClientAdminController {

	@Resource
	private ClientService clientService;

	@Resource
	private LogService logService;
	/**
	 * 查询客户信息
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public Map<String, Object> list(Client client)throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		List<Client> entrepotList = clientService.list(client);
		resultMap.put("rows", entrepotList);
		logService.save(new Log(Log.SEARCH_ACTION, "查询客户信息"));
		return resultMap;
	}

	/**
	 * 添加或修改仓库信息
	 */
	@RequestMapping("/save")
	@RequiresPermissions("商标设置")
	public Map<String, Object> save(Client client) {
		Map<String, Object> resultMap = new HashMap<>();
		if (client.getId() == null) {
			if (clientService.findByClientName(client.getName()) != null) {
				resultMap.put("success", false);
				resultMap.put("errorInfo", "客户已经存在！");
				return resultMap;
			}
		}
		clientService.save(client);
		resultMap.put("success", true);
		logService.save(new Log(Log.UPDATE_ACTION, "添加或修改客户信息"));
		return resultMap;
	}

	/**
	 * 删除商标信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public Map<String, Object> delete(Integer id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		clientService.deleteById(id);
		resultMap.put("success", true);
		logService.save(new Log(Log.DELETE_ACTION, "删除客户信息"));
		return resultMap;
	}

	/**
	 * 下拉框模糊查询
	 * 
	 * @param q
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/clientList")
	public List<Client> comboList(String q) throws Exception {
		if (q == null) {
			q = "";
		}
		logService.save(new Log(Log.SEARCH_ACTION, "模糊查询客户信息"));
		return clientService.findCombobox("%" + q + "%");
	}
}

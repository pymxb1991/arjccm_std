/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.fence.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.fence.entity.CcmElectronicFence;
import com.arjjs.ccm.modules.ccm.fence.service.CcmElectronicFenceService;
import com.arjjs.ccm.modules.ccm.tree.entity.CcmTree;
import com.arjjs.ccm.modules.ccm.tree.service.CcmTreeService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * 电子围栏实体类Controller
 * @author lgh
 * @version 2019-05-31
 */
@Controller
@RequestMapping(value = "${adminPath}/fence/ccmElectronicFence")
public class CcmElectronicFenceController extends BaseController {

	@Autowired
	private CcmElectronicFenceService ccmElectronicFenceService;

//	@Autowired
//	private CcmMobileDeviceService ccmMobileDeviceService;

	@ModelAttribute
	public CcmElectronicFence get(@RequestParam(required=false) String id) {
		CcmElectronicFence entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmElectronicFenceService.get(id);
		}
		if (entity == null){
			entity = new CcmElectronicFence();
		}
		return entity;
	}

	@RequiresPermissions("fence:ccmElectronicFence:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmElectronicFence ccmElectronicFence, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmElectronicFence> page = ccmElectronicFenceService.findPage(new Page<CcmElectronicFence>(request, response), ccmElectronicFence);
		model.addAttribute("page", page);
		return "ccm/fence/ccmElectronicFenceList";
	}

	@RequiresPermissions("fence:ccmElectronicFence:view")
	@RequestMapping(value = "form")
	public String form(CcmElectronicFence ccmElectronicFence, Model model) {
		model.addAttribute("ccmElectronicFence", ccmElectronicFence);
		return "ccm/fence/ccmElectronicFenceForm";
	}

	@RequiresPermissions("fence:ccmElectronicFence:edit")
	@RequestMapping(value = "save")
	public void save(CcmElectronicFence ccmElectronicFence, Model model, RedirectAttributes redirectAttributes, HttpServletResponse response) {
		ccmElectronicFenceService.save(ccmElectronicFence);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		addMessage(redirectAttributes, "保存电子围栏成功");
		//return "redirect:"+Global.getAdminPath()+"/fence/ccmElectronicFence/?repage";
	}

	@RequiresPermissions("fence:ccmElectronicFence:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmElectronicFence ccmElectronicFence, RedirectAttributes redirectAttributes) {
		ccmElectronicFenceService.delete(ccmElectronicFence);
//		//删除关联设备的规则
//		ccmMobileDeviceService.delElecInfoByElecId(ccmElectronicFence.getId());
		addMessage(redirectAttributes, "删除电子围栏成功");
		return "redirect:"+Global.getAdminPath()+"/fence/ccmElectronicFence/?repage";
	}

	@Autowired
	private CcmTreeService ccmTreeService;

	/***
	 * 电子围栏tree
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeElecFence")
	public List<Map<String, Object>> treeElecFence(@RequestParam(required = false) String extId,
			HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<CcmTree> list = ccmTreeService.findListTreeElecFence(new CcmTree());
		Map<String, Object> map2 = Maps.newHashMap();
		map2.put("id", "10000");
		map2.put("pId", "0");
		map2.put("name", "电子围栏列表");
		map2.put("areaMap", "");
		map2.put("areaPoint", "");
		map2.put("type", "elecfence");
		map2.put("icon", "");
		mapList.add(map2);

		for (int i = 0; i < list.size(); i++) {
			CcmTree e = list.get(i);
			// 当前的是否为被取消的内容
			if (StringUtils.isBlank(extId) || (extId != null && !extId.equals(e.getId()))) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				String name = e.getName();
				if("LineString".equalsIgnoreCase(e.getMore1())) {//线
					name =  name + "(线)";
				}else if("Polygon".equalsIgnoreCase(e.getMore1())) {
					name =  name + "(面)";
				}else if("Point".equalsIgnoreCase(e.getMore1())) {//点
					name =  name + "(点)";
				}
				map.put("name", name);
				map.put("areaMap", e.getAreaMap());
				map.put("areaPoint", e.getAreaPoint());
				map.put("type", e.getType());
				map.put("icon", "");
				mapList.add(map);
			}
		}
		return mapList;
	}


}
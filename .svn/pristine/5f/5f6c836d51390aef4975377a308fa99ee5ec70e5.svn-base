/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.web;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmMobileDevice;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmTracingpoint;
import com.arjjs.ccm.modules.ccm.patrol.service.CcmTracingpointService;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.tool.DateJsonValueProcessor;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
/**
 * 实时轨迹点Controller
 * @author arj
 * @version 2018-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/patrol/ccmTracingpoint")
public class CcmTracingpointController extends BaseController {

	@Autowired
	private CcmTracingpointService ccmTracingpointService;
	
	@ModelAttribute
	public CcmTracingpoint get(@RequestParam(required=false) String id) {
		CcmTracingpoint entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmTracingpointService.get(id);
		}
		if (entity == null){
			entity = new CcmTracingpoint();
		}
		return entity;
	}
	
	@RequiresPermissions("patrol:ccmTracingpoint:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmTracingpoint ccmTracingpoint, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmTracingpoint> page = ccmTracingpointService.findPage(new Page<CcmTracingpoint>(request, response), ccmTracingpoint); 
		model.addAttribute("page", page);
		return "ccm/patrol/ccmTracingpointList";
	}

	@RequiresPermissions("patrol:ccmTracingpoint:view")
	@RequestMapping(value = "form")
	public String form(CcmTracingpoint ccmTracingpoint, Model model) {
		model.addAttribute("ccmTracingpoint", ccmTracingpoint);
		return "ccm/patrol/ccmTracingpointForm";
	}

	@RequiresPermissions("patrol:ccmTracingpoint:edit")
	@RequestMapping(value = "save")
	public String save(CcmTracingpoint ccmTracingpoint, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmTracingpoint)){
			return form(ccmTracingpoint, model);
		}
		ccmTracingpointService.save(ccmTracingpoint);
		addMessage(redirectAttributes, "保存实时轨迹点成功");
		return "redirect:"+Global.getAdminPath()+"/patrol/ccmTracingpoint/?repage";
	}
	
	@RequiresPermissions("patrol:ccmTracingpoint:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmTracingpoint ccmTracingpoint, RedirectAttributes redirectAttributes) {
		ccmTracingpointService.delete(ccmTracingpoint);
		addMessage(redirectAttributes, "删除实时轨迹点成功");
		return "redirect:"+Global.getAdminPath()+"/patrol/ccmTracingpoint/?repage";
	}
	
	
	
	/******历史轨迹查询    pengjianqiang20180905******/

	@RequiresPermissions("patrol:ccmTracingpoint:view")
	@RequestMapping(value = {"indexTrajectoryPoint"})
	public String indexTrajectoryPoint(HttpServletResponse response) {
		return "ccm/patrol/spsTrajectoryPointIndex";
	}
	
	@RequiresPermissions("patrol:ccmTracingpoint:view")
	@RequestMapping(value = "listQuery")
	public void listQuery(CcmTracingpoint ccmTracingpoint, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		response.setContentType("application/json; charset=UTF-8");
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("area") || name.equals("createBy") || name.equals("updateBy") || name.equals("currentUser") || name.equals("page") || name.equals("sqlMap")) {
					return true;
				} else {
					return false;
				}
			}
		});
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		CcmRestResult result = new CcmRestResult();
		result.setResult(ccmTracingpointService.findList(ccmTracingpoint));
		response.getWriter().print(JSONObject.fromObject(result,jsonConfig));
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<CcmMobileDevice> list = ccmTracingpointService.findDeptDeviceList(new CcmMobileDevice());
		for (int i=0; i<list.size(); i++){
			CcmMobileDevice e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()))){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				map.put("typeClass", e.getTypeClass());
				map.put("type", e.getType());
				map.put("typeCaption", e.getTypeCaption());
				mapList.add(map);
			}
		}
		return mapList;
	}

}
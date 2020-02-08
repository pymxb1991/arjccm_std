/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.sys.web;

import java.io.IOException;
import java.util.ArrayList;
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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.sys.entity.SysDicts;
import com.arjjs.ccm.modules.ccm.sys.service.SysDictsService;
import com.arjjs.ccm.modules.sys.entity.Dict;

/**
 * 字典树Controller
 * @author liang
 * @version 2018-10-16
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/sysDicts")
public class SysDictsController extends BaseController {

	@Autowired
	private SysDictsService sysDictsService;
	
	@ModelAttribute
	public SysDicts get(@RequestParam(required=false) String id) {
		SysDicts entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysDictsService.get(id);
		}
		if (entity == null){
			entity = new SysDicts();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:sysDicts:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysDicts sysDicts, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<SysDicts> list = new ArrayList<>(); 
		if((!"".equals(sysDicts.getType()) && sysDicts.getType() !=null) || (!"".equals(sysDicts.getDescription()) && sysDicts.getDescription() !=null)){
			list = sysDictsService.findList(sysDicts);
		}
		model.addAttribute("list", list);
		List<String> typeList = sysDictsService.findTypeList(sysDicts); 
		model.addAttribute("typeList", typeList);
		return "ccm/sys/sysDictsList";
	}

	@RequiresPermissions("sys:sysDicts:view")
	@RequestMapping(value = "form")
	public String form(SysDicts sysDicts, Model model) {
		if (sysDicts.getParent()!=null && StringUtils.isNotBlank(sysDicts.getParent().getId())){
			sysDicts.setParent(sysDictsService.get(sysDicts.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(sysDicts.getId())){
				SysDicts sysDictsChild = new SysDicts();
				sysDictsChild.setParent(new SysDicts(sysDicts.getParent().getId()));
				List<SysDicts> list = sysDictsService.findList(sysDicts); 
				if (list.size() > 0){
					sysDicts.setSort(list.get(list.size()-1).getSort());
					if (sysDicts.getSort() != null){
						sysDicts.setSort(sysDicts.getSort() + 30);
					}
				}
			}
		}
		if (sysDicts.getSort() == null){
			sysDicts.setSort(30);
		}
		model.addAttribute("sysDicts", sysDicts);
		return "ccm/sys/sysDictsForm";
	}

	@RequiresPermissions("sys:sysDicts:edit")
	@RequestMapping(value = "save")
	public String save(SysDicts sysDicts, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysDicts)){
			return form(sysDicts, model);
		}
		sysDictsService.save(sysDicts);
		addMessage(redirectAttributes, "保存字典树成功");
		return "redirect:"+Global.getAdminPath()+"/sys/sysDicts/?repage";
	}
	
	@RequiresPermissions("sys:sysDicts:edit")
	@RequestMapping(value = "delete")
	public String delete(SysDicts sysDicts, RedirectAttributes redirectAttributes) {
		sysDictsService.delete(sysDicts);
		addMessage(redirectAttributes, "删除字典树成功");
		return "redirect:"+Global.getAdminPath()+"/sys/sysDicts/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response, SysDicts sysDicts) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<SysDicts> list = sysDictsService.findList(sysDicts);
		for (int i=0; i<list.size(); i++){
			SysDicts e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getLabel());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
	//根据类型名称查询警情状态信息
	@ResponseBody
	@RequestMapping(value = "findAlarmInfoByTypeName")
	public void findAlarmInfoByTypeName(HttpServletRequest request, HttpServletResponse response,String type) throws IOException {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("area") || name.equals("createBy") || name.equals("updateBy")
						|| name.equals("currentUser") || name.equals("page") || name.equals("sqlMap")
						|| name.equals("global") || name.equals("updateDate") || name.equals("createDate")) {
					return true;
				} else {
					return false;
				}
			}
		});
		SysDicts sysDicts = new SysDicts();
		sysDicts.setType(type);
		List<SysDicts> sysDictsList = sysDictsService.findAlarmInfoByTypeName(sysDicts);
		response.getWriter().print(JSONArray.fromObject(sysDictsList,jsonConfig));
	}
}
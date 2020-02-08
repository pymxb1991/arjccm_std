/**
 * Copyright &copy; 2012-2016 <a href="http://www.arjjs.com">arjjs</a> All rights reserved.
 */
package com.arjjs.ccm.modules.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
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
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.sys.entity.Dict;
import com.arjjs.ccm.modules.sys.service.DictService;
import com.arjjs.ccm.modules.sys.utils.DictUtils;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 字典Controller
 * 
 * @author admin001
 * @version 2014-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dict")
public class DictController extends BaseController {

	@Autowired
	private DictService dictService;
	@Autowired
	private TaskService taskService;

	@ModelAttribute
	public Dict get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return dictService.get(id);
		} else {
			return new Dict();
		}
	}

	// 信息查阅
	@RequestMapping(value = { "index" })
	public String index(Dict dict, Model model) {
		return "plm/act/actTaskApplyIndex";
	}

	// 我的请求
	@RequestMapping(value = { "indexList" })
	public String indexList(Dict dict, Model model) {
		return "plm/act/plmActIndex";
	}

	// 待办事宜
	@RequestMapping(value = { "actIndex" })
	public String actIndex(Dict dict, Model model) {
		return "plm/act/actTaskTodoIndex";
	}

	// 已办流程
	@RequestMapping(value = { "historicIndex" })
	public String historicIndex(Dict dict, Model model) {
		return "plm/act/actTaskHistoricIndex";
	}

	// 流程归档
	@RequestMapping(value = { "indexFile" })
	public String indexFile(Dict dict, Model model) {
		return "plm/act/plmActIndexFile";
	}

	// 流程督办
	@RequestMapping(value = { "indexSupTodo" })
	public String indexSupTodo(Dict dict, Model model) {
		return "plm/act/plmActSupTodoIndex";
	}

	@RequiresPermissions("sys:dict:view")
	@RequestMapping(value = { "list", "" })
	public String list(Dict dict, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<String> typeList = dictService.findTypeList();
		model.addAttribute("typeList", typeList);
		Page<Dict> page = dictService.findPage(new Page<Dict>(request, response), dict);
		model.addAttribute("page", page);
		return "modules/sys/dictList";
	}

	@RequiresPermissions("sys:dict:view")
	@RequestMapping(value = "form")
	public String form(Dict dict, Model model) {
		model.addAttribute("dict", dict);
		return "modules/sys/dictForm";
	}

	@RequiresPermissions("sys:dict:edit")
	@RequestMapping(value = "save") // @Valid
	public String save(Dict dict, Model model, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/dict/?repage&type=" + dict.getType();
		}
		if (!beanValidator(model, dict)) {
			return form(dict, model);
		}
		dictService.save(dict);
		addMessage(redirectAttributes, "保存字典'" + dict.getLabel() + "'成功");
		return "redirect:" + adminPath + "/sys/dict/?repage&type=" + dict.getType();
	}

	@RequiresPermissions("sys:dict:edit")
	@RequestMapping(value = "delete")
	public String delete(Dict dict, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/dict/?repage";
		}
		dictService.delete(dict);
		addMessage(redirectAttributes, "删除字典成功");
		return "redirect:" + adminPath + "/sys/dict/?repage&type=" + dict.getType();
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String type,
			HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		Dict dict = new Dict();
		dict.setType(type);
		List<Dict> list = dictService.findList(dict);
		for (int i = 0; i < list.size(); i++) {
			Dict e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParentId());
			map.put("name", StringUtils.replace(e.getLabel(), " ", ""));
			mapList.add(map);
		}
		return mapList;
	}

	/**
	 * 获取多级分类树JSON数据。
	 * 
	 * @param extValue 一级分类不希望显示的value值
	 * @param type     一级分类数据字典type值
	 * @return
	 */
	@RequiresPermissions("logistics:plmEquipmentLogi:view")
	@ResponseBody
	@RequestMapping(value = "treeData2")
	public List<Map<String, Object>> treeData2(@RequestParam(required = false) String extValue,
			@RequestParam(required = false) String type, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Dict> dictList = DictUtils.getDictList(type);
		for (Dict dict : dictList) {
			if (StringUtils.isBlank(extValue) || (extValue != null && !extValue.equals(dict.getValue()))) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", dict.getId());
				map.put("pId", dict.getParentId());
				map.put("name", dict.getLabel());
				map.put("value", dict.getValue());
				mapList.add(map);
				List<Dict> childList = DictUtils.getDictList(dict.getId());
				for (Dict dict2 : childList) {
					Map<String, Object> map2 = Maps.newHashMap();
					map2.put("id", dict2.getId());
					map2.put("pId", dict2.getParentId());
					map2.put("name", dict2.getLabel());
					map2.put("value", dict2.getValue());
					mapList.add(map2);
				}
			}
		}
		return mapList;
	}

	/*
	 * 流程汇总树形表
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData3")
	public List<Map<String, Object>> treeData3(@RequestParam(required = false) String type,
			HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		Dict dict = new Dict();
		dict.setType(type);
		List<Dict> list = dictService.findListAll(dict);
		for (int i = 0; i < list.size(); i++) {
			Dict e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParentId());
			map.put("name", e.getName());
			// map.put("urlsrc", e.getUrl());
			mapList.add(map);
		}
		return mapList;
	}

	/*
	 * 我的请求流程树形结构
	 */
	@SuppressWarnings("unused")
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData4")
	public List<Map<String, Object>> treeData4(HttpServletRequest request, @RequestParam(required = false) String type,
			HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		Dict dict = new Dict();
		dict.setType(type);
		String status = request.getParameter("status");
		String supExeId = request.getParameter("supExeId");
		dict.setStatus(status);
		if (supExeId == null && supExeId == "") {
			dict.setSupExeId("02");
		} else {
			dict.setSupExeId(supExeId);
		}
		List<Dict> list = dictService.findAll(dict);
		for (int i = 0; i < list.size(); i++) {
			Dict e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParentId());
			map.put("name", e.getName());
			map.put("procInsId", e.getProcInsId());
			if (e.getProcInsId() != "" && e.getProcInsId() != null) {
				if (e.getCount() == null) {
					map.put("count", 0);
				} else {
					map.put("count", e.getCount());
				}
			}
			mapList.add(map);
		}
		return mapList;
	}

	/*
	 * 待办树形结构
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData5")
	public List<Map<String, Object>> treeData5(HttpServletRequest request, @RequestParam(required = false) String type,
			HttpServletResponse response) {
		// 获取签收数据
		String userId = UserUtils.getUser().getLoginName();
		TaskQuery toClaimQuery = taskService.createTaskQuery().taskCandidateUser(userId).includeProcessVariables()
				.active().orderByTaskCreateTime().desc();

		List<Task> toClaimList = toClaimQuery.list();

		List<Map<String, Object>> mapList = Lists.newArrayList();
		Dict dict = new Dict();
		dict.setType(type);
		List<Dict> list = dictService.findNumberAll(dict);
		for (int i = 0; i < list.size(); i++) {
			Dict e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParentId());
			map.put("name", e.getName());
			map.put("procInsId", e.getProcInsId());
			if (e.getProcInsId() != "" && e.getProcInsId() != null) {

				for (Task task2 : toClaimList) {
					String definitionId = task2.getProcessDefinitionId();
					// 截取“：”之前
					String definition = definitionId.substring(0, definitionId.indexOf(":"));
					String dictActId = e.getId();
					String actId = "";
					if (dictActId.contains(":")) {
						actId = dictActId.substring(0, dictActId.indexOf(":"));
					}

					if (definition.equals(actId)) {
						e.setCount(e.getCount() + 1);
					}
				}

				if (e.getCount() == null) {
					map.put("count", 0);
				} else {
					map.put("count", e.getCount());
				}
			}
			mapList.add(map);
		}

		return mapList;
	}

	/*
	 * 已办流程树形结构
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData6")
	public List<Map<String, Object>> treeData6(HttpServletRequest request, @RequestParam(required = false) String type,
			HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		Dict dict = new Dict();
		dict.setType(type);
		List<Dict> list = dictService.findAllNumber(dict);
		for (int i = 0; i < list.size(); i++) {
			Dict e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParentId());
			map.put("name", e.getName());
			map.put("procInsId", e.getProcInsId());
			if (e.getProcInsId() != "" && e.getProcInsId() != null) {
				if (e.getCount() == null) {
					map.put("count", 0);
				} else {
					map.put("count", e.getCount());
				}
			}
			mapList.add(map);
		}
		return mapList;
	}

	@ResponseBody
	@RequestMapping(value = "listData")
	public List<Dict> listData(@RequestParam(required = false) String type) {
		Dict dict = new Dict();
		dict.setType(type);
		return dictService.findList(dict);
	}

	/**
	 * 根据一级分类查询二级分类列表
	 * 
	 * @param value 一级分类数据字典value值
	 * @param type  一级分类数据字典type值
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "typeChildList")
	public List<Dict> typeChildList(String value, String type, Model model) {
		return DictUtils.getDictChildList(value, type);
	}
}

package com.arjjs.ccm.modules.flat.tree.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.common.utils.SpringContextHolder;
import com.arjjs.ccm.modules.flat.alarm.service.BphAlarmInfoService;
import com.arjjs.ccm.modules.flat.tree.service.FlatTreeService;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.tool.LayUIBean;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "${adminPath}/tree")
public class FlatTreeController {
	
	@Autowired
	FlatTreeService flatTreeService;

	/**
	 * @desc dtree单选部门树形
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "officeTreeData")
	public LayUIBean officeTreeData(String type) {
		LayUIBean result = new LayUIBean();
		result.setCode("0");
		result.setMsg("操作成功");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		BphAlarmInfoService bean = SpringContextHolder.getBean("bphAlarmInfoService");
		List<Office> list = bean.findOfficeAllList(type);
		for (int i=0; i<list.size(); i++){
			Office e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("title", e.getName());
			map.put("parentId", e.getParent().getId());
			mapList.add(map);
		}
		result.setData(mapList);
		return result;
	}
	
	/**
	 * @desc dtree部门和用户树形
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="officeAndUserTreeData")
	public LayUIBean officeAndUserTreeData() {
		return flatTreeService.officeAndUserTreeData();
	}
	
	/**
	 * @desc dtree部门
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="officeTreeDatas")
	public LayUIBean officeTreeDatas() {
		return flatTreeService.officeTreeData();
	}
}

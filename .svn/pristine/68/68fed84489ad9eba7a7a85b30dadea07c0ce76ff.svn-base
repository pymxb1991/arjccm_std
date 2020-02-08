/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.list.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
import com.arjjs.ccm.modules.ccm.list.entity.CcmList;
import com.arjjs.ccm.modules.ccm.list.service.CcmListService;
import com.arjjs.ccm.tool.CommUtil;

/**
 * 名单库实体类Controller
 * @author jpy
 * @version 2019-06-04
 */
@Controller
@RequestMapping(value = "${adminPath}/list/ccmList")
public class CcmListController extends BaseController {

	@Autowired
	private CcmListService ccmListService;
	
	@ModelAttribute
	public CcmList get(@RequestParam(required=false) String id) {
		CcmList entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmListService.get(id);
		}
		if (entity == null){
			entity = new CcmList();
		}
		return entity;
	}
	
	@ResponseBody
	@RequiresPermissions("list:ccmList:view")
	@RequestMapping(value = "getList")
	public Object getList(CcmList ccmList) {
		List<CcmList> list = ccmListService.getList(ccmList);
		Integer count = ccmListService.getPeopleCount(ccmList);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("count", count);
		return map;
	}
	
	@RequiresPermissions("list:ccmList:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmList ccmList, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmList> page = ccmListService.findPage(new Page<CcmList>(request, response), ccmList); 
		model.addAttribute("page", page);
		return "ccm/list/ccmListList";
	}

	@RequiresPermissions("list:ccmList:view")
	@RequestMapping(value = "form")
	public String form(CcmList ccmList, Model model) {
		model.addAttribute("ccmList", ccmList);
		return "ccm/list/ccmListForm";
	}

	@RequiresPermissions("list:ccmList:edit")
	@RequestMapping(value = "save")
	public void save(CcmList ccmList, Model model, RedirectAttributes redirectAttributes, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!beanValidator(model, ccmList)){
			CommUtil.openWinExpDiv(out, "保存名单库失败");
		}else {			
			ccmListService.save(ccmList);
			CommUtil.openWinExpDiv(out, "保存名单库成功");
		}
	}
	
	@RequiresPermissions("list:ccmList:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmList ccmList, RedirectAttributes redirectAttributes) {
		ccmListService.delete(ccmList);
		addMessage(redirectAttributes, "删除名单库成功");
		return "redirect:"+Global.getAdminPath()+"/list/ccmList/?repage";
	}

	//得到动态库和静态库总数
	@ResponseBody
	@RequestMapping(value = "getCount")
	public Object getCount(CcmList ccmList) {
		ccmList.setType("01");
		Integer dynamicLibrary = ccmListService.getPeopleCount(ccmList);
		ccmList.setType("02");
		Integer staticLibrary = ccmListService.getPeopleCount(ccmList);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dynamicLibrary", dynamicLibrary);
		map.put("staticLibrary", staticLibrary);
		return map;
	}
}
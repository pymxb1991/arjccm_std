/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.view.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgComprehensive;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgComprehensiveService;
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
import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.view.entity.VCcmOrg;
import com.arjjs.ccm.modules.ccm.view.service.VCcmOrgService;

/**
 * 综治机构Controller
 * @author liang
 * @version 2018-01-12
 */
@Controller
@RequestMapping(value = "${adminPath}/view/vCcmOrg")
public class VCcmOrgController extends BaseController {

	@Autowired
	private VCcmOrgService vCcmOrgService;

	@Autowired
	private CcmOrgComprehensiveService ccmOrgComprehensiveService;

	@ModelAttribute
	public VCcmOrg get(@RequestParam(required=false) String id) {
		VCcmOrg entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = vCcmOrgService.get(id);
		}
		if (entity == null){
			entity = new VCcmOrg();
		}
		return entity;
	}
	
	@RequiresPermissions("view:vCcmOrg:view")
	@RequestMapping(value = {"index", ""})
	public String index(VCcmOrg vCcmOrg,Model model) {
		return "ccm/view/CcmOrgIndex";
	}
	
	@RequiresPermissions("view:vCcmOrg:view")
	@RequestMapping(value = {"list", ""})
	public String list(VCcmOrg vCcmOrg, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<VCcmOrg> list = vCcmOrgService.findList(vCcmOrg); 
		model.addAttribute("list", list);
		model.addAttribute("vCcmOrg",vCcmOrg);
		
		return "ccm/view/vCcmOrgList";
	}

	@RequiresPermissions("view:vCcmOrg:view")
	@RequestMapping(value = "form")
	public String form(VCcmOrg vCcmOrg, Model model) {
		if (vCcmOrg.getParent()!=null && StringUtils.isNotBlank(vCcmOrg.getParent().getId())){
			vCcmOrg.setParent(vCcmOrgService.get(vCcmOrg.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(vCcmOrg.getId())){
				VCcmOrg vCcmOrgChild = new VCcmOrg();
				vCcmOrgChild.setParent(new VCcmOrg(vCcmOrg.getParent().getId()));
				List<VCcmOrg> list = vCcmOrgService.findList(vCcmOrg); 
				if (list.size() > 0){
					vCcmOrg.setSort(list.get(list.size()-1).getSort());
					if (vCcmOrg.getSort() != null){
						vCcmOrg.setSort(vCcmOrg.getSort() + 30);
					}
				}
			}
		}
		if (vCcmOrg.getSort() == null){
			vCcmOrg.setSort(30);
		}
		CcmOrgComprehensive ccmOrgComprehensive = ccmOrgComprehensiveService.findOfficeId(vCcmOrg.getId());
		if(ccmOrgComprehensive!=null && StringUtils.isNotEmpty(ccmOrgComprehensive.getMaxArriveTime())){
			vCcmOrg.setMaxArriveTime(ccmOrgComprehensive.getMaxArriveTime());
		}
		if(ccmOrgComprehensive!=null && StringUtils.isNotEmpty(ccmOrgComprehensive.getMaxDispatchTime())){
			vCcmOrg.setMaxDispatchTime(ccmOrgComprehensive.getMaxDispatchTime());
		}
		model.addAttribute("vCcmOrg", vCcmOrg);
		return "ccm/view/vCcmOrgForm";
	}

	@RequiresPermissions("view:vCcmOrg:edit")
	@RequestMapping(value = "save")
	public String save(VCcmOrg vCcmOrg, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, vCcmOrg)){
			return form(vCcmOrg, model);
		}
		vCcmOrgService.save(vCcmOrg);
		addMessage(redirectAttributes, "保存综治机构成功");
		return "redirect:"+Global.getAdminPath()+"/view/vCcmOrg/?repage";
	}
	
	@RequiresPermissions("view:vCcmOrg:edit")
	@RequestMapping(value = "delete")
	public String delete(VCcmOrg vCcmOrg, RedirectAttributes redirectAttributes) {
		vCcmOrgService.delete(vCcmOrg);
		addMessage(redirectAttributes, "删除综治机构成功");
		return "redirect:"+Global.getAdminPath()+"/view/vCcmOrg/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<VCcmOrg> list = vCcmOrgService.findList(new VCcmOrg());
		for (int i=0; i<list.size(); i++){
			VCcmOrg e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}
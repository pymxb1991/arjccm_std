/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.web;

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

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseSchoolrim;
import com.arjjs.ccm.modules.ccm.house.service.CcmHouseSchoolrimService;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgCommonality;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgComPopService;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgCommonalityService;
import com.arjjs.ccm.modules.ccm.report.service.CcmPeopleAmountService;
import com.arjjs.ccm.tool.SearchTabMore;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


/**
 * 公共机构管理Controller
 * @author liang
 * @version 2018-02-24
 */
@Controller
@RequestMapping(value = "${adminPath}/org/ccmOrgCommonality")
public class CcmOrgCommonalityController extends BaseController {

	@Autowired
	private CcmOrgCommonalityService ccmOrgCommonalityService;
	@Autowired
	private CcmPeopleAmountService ccmPeopleAmountService;
	@Autowired
	private CcmOrgComPopService ccmOrgComPopService;
	@Autowired
	private CcmHouseSchoolrimService ccmHouseSchoolrimService;
	
	
	@ModelAttribute
	public CcmOrgCommonality get(@RequestParam(required=false) String id) {
		CcmOrgCommonality entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmOrgCommonalityService.get(id);
		}
		if (entity == null){
			entity = new CcmOrgCommonality();
		}
		return entity;
	}
	
	@RequiresPermissions("org:ccmOrgCommonality:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmOrgCommonality ccmOrgCommonality, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmOrgCommonality> page = ccmOrgCommonalityService.findPage(new Page<CcmOrgCommonality>(request, response), ccmOrgCommonality); 
		model.addAttribute("page", page);
		return "ccm/org/ccmOrgCommonalityList";
	}

	@RequiresPermissions("org:ccmOrgCommonality:view")
	@RequestMapping(value = "form")
	public String form(CcmOrgCommonality ccmOrgCommonality, Model model) {
		model.addAttribute("ccmOrgCommonality", ccmOrgCommonality);
		return "ccm/org/ccmOrgCommonalityForm";
	}

	@RequiresPermissions("org:ccmOrgCommonality:edit")
	@RequestMapping(value = "save")
	public String save(CcmOrgCommonality ccmOrgCommonality, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmOrgCommonality)){
			return form(ccmOrgCommonality, model);
		}
		ccmOrgCommonalityService.save(ccmOrgCommonality);
		addMessage(redirectAttributes, "保存公共机构管理成功");
		return "redirect:"+Global.getAdminPath()+"/org/ccmOrgCommonality/?repage";
	}
	
	@RequiresPermissions("org:ccmOrgCommonality:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmOrgCommonality ccmOrgCommonality, RedirectAttributes redirectAttributes) {
		ccmOrgCommonalityService.delete(ccmOrgCommonality);
		addMessage(redirectAttributes, "删除公共机构管理成功");
		return "redirect:"+Global.getAdminPath()+"/org/ccmOrgCommonality/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<CcmOrgCommonality> list = ccmOrgCommonalityService.findList(new CcmOrgCommonality());
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				CcmOrgCommonality c = list.get(i);
				if ((StringUtils.isBlank(extId) || (extId != null && !extId.equals(c.getId()) ) )) {
					Map<String, Object> map = Maps.newHashMap();
					map.put("id", c.getId());
					map.put("pId", "0");
					map.put("name", c.getName());
					mapList.add(map);
				}
			}
		}
		return mapList;
	}
	
	

	/**
	 * 首页概况各种总数查询
	 */
	@ResponseBody
	@RequestMapping(value = "getOthersAll")
	public List<String> getOthersAll(Model model) {
		List<String> listOthersAll = new ArrayList<>();
		//实有人口总数
		SearchTabMore searchTabMore1 = ccmPeopleAmountService.getnumPopFollowPop();
		if(searchTabMore1!=null){
			int p =
			Integer.parseInt(searchTabMore1.getValue()!=null?searchTabMore1.getValue():"0")
			+Integer.parseInt(searchTabMore1.getValue1()!=null?searchTabMore1.getValue1():"0")
			+Integer.parseInt(searchTabMore1.getValue2()!=null?searchTabMore1.getValue2():"0")
			+Integer.parseInt(searchTabMore1.getValue3()!=null?searchTabMore1.getValue3():"0");
			listOthersAll.add(p+"");//实有人口总数
		}else{
			listOthersAll.add("0");//实有人口总数
		}
	
		//社区民警/辅警
		int num2 = ccmOrgComPopService.findPop();
		listOthersAll.add(num2+"");
		//机构
		String[] com = {"09","01","02"};//养老//公安//医院
		for(int n=0;n<com.length;n++){
			CcmOrgCommonality ccmOrgCommonality = new CcmOrgCommonality();
			ccmOrgCommonality.setType(com[n]);
			List<CcmOrgCommonality> list = ccmOrgCommonalityService.findList(ccmOrgCommonality);
			listOthersAll.add(list.size()+"");
		}
		//学校
		List<CcmHouseSchoolrim> school = ccmHouseSchoolrimService.findList(new CcmHouseSchoolrim());
		listOthersAll.add(school.size()+"");//学校数量

		//学校类型
		String[] schoolType = {"21","31","34"};//小学//普通初中//普通高中
		for(int n = 0; n < schoolType.length; n++){
			CcmHouseSchoolrim ccmHouseSchoolrim = new CcmHouseSchoolrim();
			ccmHouseSchoolrim.setSchoolType(schoolType[n]);
			List<CcmHouseSchoolrim> list = ccmHouseSchoolrimService.findList(ccmHouseSchoolrim);
			listOthersAll.add(list.size()+"");
		}

		//学生人数、教职工人数、教学经费
		SearchTabMore searchTabMore2 = ccmHouseSchoolrimService.getCountInfo();
		if(searchTabMore2 != null){
			listOthersAll.add(searchTabMore2.getValue()!=null?searchTabMore2.getValue():"0");//学生人数
			listOthersAll.add(searchTabMore2.getValue1()!=null?searchTabMore2.getValue1():"0");//教职工人数
			listOthersAll.add(searchTabMore2.getValue2()!=null?searchTabMore2.getValue2():"0");//教学经费
		}else{
			listOthersAll.add("0");//学生人数
			listOthersAll.add("0");//教职工人数
			listOthersAll.add("0");//教学经费
		}

		//医院数、医生人数、床位数
		CcmOrgCommonality ccmOrgCommonality = new CcmOrgCommonality();
		ccmOrgCommonality.setType("02");
		SearchTabMore searchTabMore3 = ccmOrgCommonalityService.getCountInfo(ccmOrgCommonality);
		if(searchTabMore3 != null){
			listOthersAll.add(searchTabMore3.getValue()!=null?searchTabMore3.getValue():"0");//医院数
			listOthersAll.add(searchTabMore3.getValue1()!=null?searchTabMore3.getValue1():"0");//医生人数
			listOthersAll.add(searchTabMore3.getValue2()!=null?searchTabMore3.getValue2():"0");//床位数
		}else{
			listOthersAll.add("0");//医院数
			listOthersAll.add("0");//医生人数
			listOthersAll.add("0");//床位数
		}
		
		
		return listOthersAll;
	}
	
	
	
	
	
	
	
	
	
	
}
/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.web;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.IdGen;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgComprehensive;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgComprehensiveService;
import com.arjjs.ccm.modules.sys.entity.Office;

/**
 * 综治机构Controller
 * @author liang
 * @version 2018-01-12
 */
@Controller
@RequestMapping(value = "${adminPath}/org/ccmOrgComprehensive")
public class CcmOrgComprehensiveController extends BaseController {

	@Autowired
	private CcmOrgComprehensiveService ccmOrgComprehensiveService;
	
	@ModelAttribute
	public CcmOrgComprehensive get(@RequestParam(required=false) String id) {
		CcmOrgComprehensive entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmOrgComprehensiveService.get(id);
		}
		if (entity == null){
			entity = new CcmOrgComprehensive();
		}
		return entity;
	}
	
	@RequiresPermissions("org:ccmOrgComprehensive:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmOrgComprehensive ccmOrgComprehensive, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmOrgComprehensive> page = ccmOrgComprehensiveService.findPage(new Page<CcmOrgComprehensive>(request, response), ccmOrgComprehensive); 
		model.addAttribute("page", page);
		return "ccm/org/ccmOrgComprehensiveList";
	}

	@RequiresPermissions("org:ccmOrgComprehensive:view")
	@RequestMapping(value = "form")
	public String form(CcmOrgComprehensive ccmOrgComprehensive, Model model) {
		model.addAttribute("ccmOrgComprehensive", ccmOrgComprehensive);
		return "ccm/org/ccmOrgComprehensiveForm";
	}

	@RequiresPermissions("org:ccmOrgComprehensive:edit")
	@RequestMapping(value = "save")
	public String save(CcmOrgComprehensive ccmOrgComprehensive, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmOrgComprehensive)){
			return form(ccmOrgComprehensive, model);
		}
		// TODO 查询 当前  数据是否 含有 office_id
		ccmOrgComprehensive.setOffice(new Office(ccmOrgComprehensive.getId()));
		CcmOrgComprehensive ccmOrgComprehensive2 = ccmOrgComprehensiveService.findOfficeId(ccmOrgComprehensive.getId());
		if(ccmOrgComprehensive2==null){
			ccmOrgComprehensive.setIsNewRecord(true);//新添
			ccmOrgComprehensive.setId(IdGen.uuid());
			ccmOrgComprehensiveService.save(ccmOrgComprehensive);
		}else{
			ccmOrgComprehensive.setIsNewRecord(false);//修改
			ccmOrgComprehensive.setId(ccmOrgComprehensive2.getId());
			ccmOrgComprehensiveService.save(ccmOrgComprehensive);
		}
			
		
		addMessage(redirectAttributes, "保存综治机构成功");
		//return "redirect:"+Global.getAdminPath()+"/org/ccmOrgComprehensive/?repage";
		return "redirect:"+Global.getAdminPath()+"/view/vCcmOrg/list?repage";
	}
	
	@RequiresPermissions("org:ccmOrgComprehensive:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmOrgComprehensive ccmOrgComprehensive, RedirectAttributes redirectAttributes) {
		ccmOrgComprehensiveService.delete(ccmOrgComprehensive);
		addMessage(redirectAttributes, "删除综治机构成功");
		return "redirect:"+Global.getAdminPath()+"/org/ccmOrgComprehensive/?repage";
	}

}
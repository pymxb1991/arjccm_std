/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.ncount.web;

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
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.ncount.entity.PbsNcountdetail;
import com.arjjs.ccm.modules.pbs.ncount.service.PbsNcountdetailService;

/**
 * 统计计数明细信息Controller
 * @author lc
 * @version 2018-07-12
 */
@Controller
@RequestMapping(value = "${adminPath}/ncount/pbsNcountdetail")
public class PbsNcountdetailController extends BaseController {

	@Autowired
	private PbsNcountdetailService pbsNcountdetailService;
	
	@ModelAttribute
	public PbsNcountdetail get(@RequestParam(required=false) String id) {
		PbsNcountdetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pbsNcountdetailService.get(id);
		}
		if (entity == null){
			entity = new PbsNcountdetail();
		}
		return entity;
	}
	
	@RequiresPermissions("ncount:pbsNcountdetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(PbsNcountdetail pbsNcountdetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsNcountdetail> page = pbsNcountdetailService.findPage(new Page<PbsNcountdetail>(request, response), pbsNcountdetail); 
		model.addAttribute("page", page);
		return "pbs/ncount/pbsNcountdetailList";
	}

	@RequiresPermissions("ncount:pbsNcountdetail:view")
	@RequestMapping(value = "form")
	public String form(PbsNcountdetail pbsNcountdetail, Model model) {
		model.addAttribute("pbsNcountdetail", pbsNcountdetail);
		return "pbs/ncount/pbsNcountdetailForm";
	}

	@RequiresPermissions("ncount:pbsNcountdetail:edit")
	@RequestMapping(value = "save")
	public String save(PbsNcountdetail pbsNcountdetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsNcountdetail)){
			return form(pbsNcountdetail, model);
		}
		pbsNcountdetailService.save(pbsNcountdetail);
		addMessage(redirectAttributes, "保存统计计数明细信息成功");
		return "redirect:"+Global.getAdminPath()+"/ncount/pbsNcountdetail/?repage";
	}
	
	@RequiresPermissions("ncount:pbsNcountdetail:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsNcountdetail pbsNcountdetail, RedirectAttributes redirectAttributes) {
		pbsNcountdetailService.delete(pbsNcountdetail);
		addMessage(redirectAttributes, "删除统计计数明细信息成功");
		return "redirect:"+Global.getAdminPath()+"/ncount/pbsNcountdetail/?repage";
	}

}
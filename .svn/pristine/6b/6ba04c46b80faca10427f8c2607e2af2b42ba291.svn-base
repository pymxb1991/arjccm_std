/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.ncount.web;

import java.util.List;

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
import com.arjjs.ccm.modules.pbs.ncount.entity.PbsNcount;
import com.arjjs.ccm.modules.pbs.ncount.service.PbsNcountService;

/**
 * 统计计数Controller
 * @author lc
 * @version 2018-07-12
 */
@Controller
@RequestMapping(value = "${adminPath}/ncount/pbsNcount")
public class PbsNcountController extends BaseController {

	@Autowired
	private PbsNcountService pbsNcountService;
	
	@ModelAttribute
	public PbsNcount get(@RequestParam(required=false) String id) {
		PbsNcount entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pbsNcountService.get(id);
		}
		if (entity == null){
			entity = new PbsNcount();
		}
		return entity;
	}
	
	@RequiresPermissions("ncount:pbsNcount:view")
	@RequestMapping(value = {"list", ""})
	public String list(PbsNcount pbsNcount, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsNcount> page = pbsNcountService.findPage(new Page<PbsNcount>(request, response), pbsNcount); 
		model.addAttribute("page", page);
		return "pbs/ncount/pbsNcountList";
	}

	@RequiresPermissions("ncount:pbsNcount:view")
	@RequestMapping(value = "form")
	public String form(PbsNcount pbsNcount, Model model) {
		model.addAttribute("pbsNcount", pbsNcount);
		return "pbs/ncount/pbsNcountForm";
	}

	@RequiresPermissions("ncount:pbsNcount:edit")
	@RequestMapping(value = "save")
	public String save(PbsNcount pbsNcount, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsNcount)){
			return form(pbsNcount, model);
		}
		pbsNcountService.save(pbsNcount);
		addMessage(redirectAttributes, "保存统计计数成功");
		return "redirect:"+Global.getAdminPath()+"/ncount/pbsNcount/?repage";
	}
	
	@RequiresPermissions("ncount:pbsNcount:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsNcount pbsNcount, RedirectAttributes redirectAttributes) {
		pbsNcountService.delete(pbsNcount);
		addMessage(redirectAttributes, "删除统计计数成功");
		return "redirect:"+Global.getAdminPath()+"/ncount/pbsNcount/?repage";
	}

	@RequestMapping(value = "namelist")
	public String namelist(PbsNcount pbsNcount, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<PbsNcount> list = pbsNcountService.findList(pbsNcount);
		model.addAttribute("list", list);
		return "mapping/NcountList";
	}
}
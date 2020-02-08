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
import com.arjjs.ccm.modules.pbs.ncount.entity.PbsNcountHis;
import com.arjjs.ccm.modules.pbs.ncount.service.PbsNcountHisService;

/**
 * 统计类别历史Controller
 * @author lc
 * @version 2018-07-19
 */
@Controller
@RequestMapping(value = "${adminPath}/ncount/pbsNcountHis")
public class PbsNcountHisController extends BaseController {

	@Autowired
	private PbsNcountHisService pbsNcountHisService;
	
	@ModelAttribute
	public PbsNcountHis get(@RequestParam(required=false) String id) {
		PbsNcountHis entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pbsNcountHisService.get(id);
		}
		if (entity == null){
			entity = new PbsNcountHis();
		}
		return entity;
	}
	
	@RequiresPermissions("ncount:pbsNcountHis:view")
	@RequestMapping(value = {"list", ""})
	public String list(PbsNcountHis pbsNcountHis, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsNcountHis> page = pbsNcountHisService.findPage(new Page<PbsNcountHis>(request, response), pbsNcountHis); 
		model.addAttribute("page", page);
		return "pbs/ncount/pbsNcountHisList";
	}

	@RequiresPermissions("ncount:pbsNcountHis:view")
	@RequestMapping(value = "form")
	public String form(PbsNcountHis pbsNcountHis, Model model) {
		model.addAttribute("pbsNcountHis", pbsNcountHis);
		return "pbs/ncount/pbsNcountHisForm";
	}

	@RequiresPermissions("ncount:pbsNcountHis:edit")
	@RequestMapping(value = "save")
	public String save(PbsNcountHis pbsNcountHis, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsNcountHis)){
			return form(pbsNcountHis, model);
		}
		pbsNcountHisService.save(pbsNcountHis);
		addMessage(redirectAttributes, "保存统计类别历史成功");
		return "redirect:"+Global.getAdminPath()+"/ncount/pbsNcountHis/?repage";
	}
	
	@RequiresPermissions("ncount:pbsNcountHis:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsNcountHis pbsNcountHis, RedirectAttributes redirectAttributes) {
		pbsNcountHisService.delete(pbsNcountHis);
		addMessage(redirectAttributes, "删除统计类别历史成功");
		return "redirect:"+Global.getAdminPath()+"/ncount/pbsNcountHis/?repage";
	}

}
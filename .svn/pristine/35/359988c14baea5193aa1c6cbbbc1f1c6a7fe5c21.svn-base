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
import com.arjjs.ccm.modules.pbs.ncount.entity.PbsNcountdetailHis;
import com.arjjs.ccm.modules.pbs.ncount.service.PbsNcountdetailHisService;

/**
 * 统计信息历史Controller
 * @author lc
 * @version 2018-07-19
 */
@Controller
@RequestMapping(value = "${adminPath}/ncount/pbsNcountdetailHis")
public class PbsNcountdetailHisController extends BaseController {

	@Autowired
	private PbsNcountdetailHisService pbsNcountdetailHisService;
	
	@ModelAttribute
	public PbsNcountdetailHis get(@RequestParam(required=false) String id) {
		PbsNcountdetailHis entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pbsNcountdetailHisService.get(id);
		}
		if (entity == null){
			entity = new PbsNcountdetailHis();
		}
		return entity;
	}
	
	@RequiresPermissions("ncount:pbsNcountdetailHis:view")
	@RequestMapping(value = {"list", ""})
	public String list(PbsNcountdetailHis pbsNcountdetailHis, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsNcountdetailHis> page = pbsNcountdetailHisService.findPage(new Page<PbsNcountdetailHis>(request, response), pbsNcountdetailHis); 
		model.addAttribute("page", page);
		return "pbs/ncount/pbsNcountdetailHisList";
	}

	@RequiresPermissions("ncount:pbsNcountdetailHis:view")
	@RequestMapping(value = "form")
	public String form(PbsNcountdetailHis pbsNcountdetailHis, Model model) {
		model.addAttribute("pbsNcountdetailHis", pbsNcountdetailHis);
		return "pbs/ncount/pbsNcountdetailHisForm";
	}

	@RequiresPermissions("ncount:pbsNcountdetailHis:edit")
	@RequestMapping(value = "save")
	public String save(PbsNcountdetailHis pbsNcountdetailHis, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsNcountdetailHis)){
			return form(pbsNcountdetailHis, model);
		}
		pbsNcountdetailHisService.save(pbsNcountdetailHis);
		addMessage(redirectAttributes, "保存统计信息历史成功");
		return "redirect:"+Global.getAdminPath()+"/ncount/pbsNcountdetailHis/?repage";
	}
	
	@RequiresPermissions("ncount:pbsNcountdetailHis:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsNcountdetailHis pbsNcountdetailHis, RedirectAttributes redirectAttributes) {
		pbsNcountdetailHisService.delete(pbsNcountdetailHis);
		addMessage(redirectAttributes, "删除统计信息历史成功");
		return "redirect:"+Global.getAdminPath()+"/ncount/pbsNcountdetailHis/?repage";
	}

}
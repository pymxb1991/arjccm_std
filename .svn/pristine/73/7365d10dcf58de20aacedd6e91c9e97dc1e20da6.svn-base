/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.person.web;

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
import com.arjjs.ccm.modules.pbs.person.entity.PbsLabelinfo;
import com.arjjs.ccm.modules.pbs.person.entity.PbsMemlabel;
import com.arjjs.ccm.modules.pbs.person.service.PbsLabelinfoService;
import com.arjjs.ccm.modules.pbs.person.service.PbsMemlabelService;

/**
 * 人物标签Controller
 * @author lc
 * @version 2018-08-03
 */
@Controller
@RequestMapping(value = "${adminPath}/person/pbsMemlabel")
public class PbsMemlabelController extends BaseController {

	@Autowired
	private PbsMemlabelService pbsMemlabelService;
	@Autowired
	private PbsLabelinfoService pbsLabelinfoService;
	
	@ModelAttribute
	public PbsMemlabel get(@RequestParam(required=false) String id) {
		PbsMemlabel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pbsMemlabelService.get(id);
		}
		if (entity == null){
			entity = new PbsMemlabel();
		}
		return entity;
	}
	
	@RequiresPermissions("person:pbsMemlabel:view")
	@RequestMapping(value = {"list", ""})
	public String list(PbsMemlabel pbsMemlabel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsMemlabel> page = pbsMemlabelService.findPage(new Page<PbsMemlabel>(request, response), pbsMemlabel); 
		model.addAttribute("page", page);
		List<PbsLabelinfo> pbsLabelinfos =  pbsLabelinfoService.findList(new PbsLabelinfo());
		model.addAttribute("pbsLabelinfos", pbsLabelinfos);
		return "pbs/person/label/pbsMemlabelList";
	}

	@RequiresPermissions("person:pbsMemlabel:view")
	@RequestMapping(value = "form")
	public String form(PbsMemlabel pbsMemlabel, Model model) {
		model.addAttribute("pbsMemlabel", pbsMemlabel);
		// 获取的标签信息
		List<PbsLabelinfo> pbsLabelinfos =  pbsLabelinfoService.findList(new PbsLabelinfo());
		model.addAttribute("pbsLabelinfos", pbsLabelinfos);
		return "pbs/person/label/pbsMemlabelForm";
	}

	@RequiresPermissions("person:pbsMemlabel:edit")
	@RequestMapping(value = "save")
	public String save(PbsMemlabel pbsMemlabel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsMemlabel)){
			return form(pbsMemlabel, model);
		}
		pbsMemlabelService.save(pbsMemlabel);
		addMessage(redirectAttributes, "保存人物标签成功");
		return "redirect:"+Global.getAdminPath()+"/person/pbsMemlabel/?repage";
	}
	
	@RequiresPermissions("person:pbsMemlabel:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsMemlabel pbsMemlabel, RedirectAttributes redirectAttributes) {
		pbsMemlabelService.delete(pbsMemlabel);
		addMessage(redirectAttributes, "删除人物标签成功");
		return "redirect:"+Global.getAdminPath()+"/person/pbsMemlabel/?repage";
	}

}
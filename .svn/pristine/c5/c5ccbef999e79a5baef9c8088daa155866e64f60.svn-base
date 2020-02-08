/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.service.web;

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
import com.arjjs.ccm.modules.ccm.service.entity.PbsCourseinfo;
import com.arjjs.ccm.modules.ccm.service.service.PbsCourseinfoService;

/**
 * 业务学习Controller
 * @author liujindan
 * @version 2019-02-25
 */
@Controller
@RequestMapping(value = "${adminPath}/service/pbsCourseinfo")
public class PbsCourseinfoController extends BaseController {

	@Autowired
	private PbsCourseinfoService pbsCourseinfoService;
	
	@ModelAttribute
	public PbsCourseinfo get(@RequestParam(required=false) String id) {
		PbsCourseinfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pbsCourseinfoService.get(id);
		}
		if (entity == null){
			entity = new PbsCourseinfo();
		}
		return entity;
	}
	
	@RequiresPermissions("service:pbsCourseinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(PbsCourseinfo pbsCourseinfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsCourseinfo> page = pbsCourseinfoService.findPage(new Page<PbsCourseinfo>(request, response), pbsCourseinfo); 
		model.addAttribute("page", page);
		return "ccm/service/pbsCourseinfoList";
	}

	@RequiresPermissions("service:pbsCourseinfo:view")
	@RequestMapping(value = "form")
	public String form(PbsCourseinfo pbsCourseinfo, Model model) {
		model.addAttribute("pbsCourseinfo", pbsCourseinfo);
		return "ccm/service/pbsCourseinfoForm";
	}

	@RequiresPermissions("service:pbsCourseinfo:edit")
	@RequestMapping(value = "save")
	public String save(PbsCourseinfo pbsCourseinfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsCourseinfo)){
			return form(pbsCourseinfo, model);
		}
		if (!StringUtils.isBlank(pbsCourseinfo.getSFileurl())) {
			String url = pbsCourseinfo.getSFileurl();
			int one = url.lastIndexOf(".");
			// 赋予文件类型
			pbsCourseinfo.setSFiletype(url.substring((one + 1), url.length()));
		}
		if (StringUtils.isBlank(pbsCourseinfo.getISort())) {
			pbsCourseinfo.setISort("1");
		}
		pbsCourseinfoService.save(pbsCourseinfo);
		addMessage(redirectAttributes, "保存业务学习成功");
		return "redirect:"+Global.getAdminPath()+"/service/pbsCourseinfo/?repage";
	}
	
	@RequiresPermissions("service:pbsCourseinfo:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsCourseinfo pbsCourseinfo, RedirectAttributes redirectAttributes) {
		pbsCourseinfoService.delete(pbsCourseinfo);
		addMessage(redirectAttributes, "删除业务学习成功");
		return "redirect:"+Global.getAdminPath()+"/service/pbsCourseinfo/?repage";
	}

}
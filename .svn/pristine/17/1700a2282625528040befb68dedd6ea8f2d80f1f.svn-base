/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.resource.web;

import java.io.IOException;

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
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.plm.resource.entity.PlmResource;
import com.arjjs.ccm.modules.plm.resource.entity.PlmResourceUser;
import com.arjjs.ccm.modules.plm.resource.service.PlmResourceService;
import com.arjjs.ccm.modules.plm.resource.service.PlmResourceUserService;
import com.arjjs.ccm.modules.plm.storage.entity.AjaxResultEntity;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.PlmTypes;

/**
 * 我的资源Controller
 * @author liucong
 * @version 2018-07-20
 */
@Controller
@RequestMapping(value = "${adminPath}/resource/plmPersonalResource")
public class PlmPersonalResourceController extends BaseController {

	@Autowired
	private PlmResourceService plmResourceService;
	@Autowired
	private PlmResourceUserService plmResourceUserService;
	PlmResource plmResource=null;
	PlmTypes plmTypes;
	@ModelAttribute
	public PlmResource get(@RequestParam(required=false) String id) {
		PlmResource entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmResourceService.get(id);
		}
		if (entity == null){
			entity = new PlmResource();
		}
		return entity;
	}
	
	@RequiresPermissions("resource:plmPersonalResource:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmResource plmResource, HttpServletRequest request, HttpServletResponse response, Model model) {
		plmResource.setUpdateBy(UserUtils.getUser());
		Page<PlmResource> page = plmResourceService.findPage(new Page<PlmResource>(request, response), plmResource); 
		model.addAttribute("page", page);
		return "plm/resource/plmPersonalResourceList";
	}

	@RequiresPermissions("resource:plmPersonalResource:view")
	@RequestMapping(value = "form")
	public String form(PlmResource plmResource, Model model) {
		model.addAttribute("plmResource", plmResource);
		return "plm/resource/plmPersonalResourceForm";
	}

	@RequiresPermissions("resource:plmPersonalResource:edit")
	@RequestMapping(value = "save")
	public String save(PlmResource plmResource, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmResource)){
			return form(plmResource, model);
		}
		plmResource.setType("02");
		plmResourceService.save(plmResource);
		addMessage(redirectAttributes, "保存资源共享成功");
		return "redirect:"+Global.getAdminPath()+"/resource/plmPersonalResource/?repage&type=02";
	}
	
	@RequiresPermissions("resource:plmPersonalResource:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmResource plmResource, RedirectAttributes redirectAttributes) {
		plmResourceService.delete(plmResource);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/resource/plmPersonalResource/?repage&type=02";
	}
	@SuppressWarnings("unused")
	@RequiresPermissions("resource:plmPersonalResource:edit")
	@RequestMapping(value = "saveId")
	@ResponseBody
	public AjaxResultEntity saveId( PlmResourceUser plmResourceUser, HttpServletRequest request, HttpServletResponse response,
			Model model, RedirectAttributes redirectAttributes) throws IOException {
		AjaxResultEntity resultEntity = new AjaxResultEntity();
		String[] ids = request.getParameterValues("ids");
		String[] rId = request.getParameterValues("rid");
		if (ids.length<=0||rId.length<=0) {
			resultEntity.setRet(AjaxResultEntity.ERROR_PARAM);
			resultEntity.setMessage("分享失败");
			return resultEntity;
		}
		for (int i = 0; i < ids.length; i++) {
			plmResourceUser.setrId(rId[i]);
			String id=ids[i];
			if(plmResourceUserService.findCount(id.substring(2), rId[i])>0) {
				resultEntity.setRet(AjaxResultEntity.ERROR_PARAM);
				resultEntity.setMessage("已分享过");
				return resultEntity;
			}else {
			plmResourceUser.setuId(id.substring(2));
			plmResourceUserService.save(plmResourceUser);
			plmResourceService.updateType(rId[i]);
			resultEntity.setRet(AjaxResultEntity.ERROR_OK);
			resultEntity.setResult(id.substring(2));
			resultEntity.setMessage("分享成功");
			return resultEntity;
			}
		}
		return resultEntity;
	}
}
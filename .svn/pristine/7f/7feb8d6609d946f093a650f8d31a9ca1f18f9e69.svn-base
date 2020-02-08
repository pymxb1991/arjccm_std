/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.appupload.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.flat.appupload.entity.SysAppUpload;
import com.arjjs.ccm.modules.flat.appupload.service.SysAppUploadService;

/**
 * App 上传记录表Controller
 * @author maoxb
 * @version 2019-05-16
 */
@Controller
@RequestMapping(value = "${adminPath}/appupload/sysAppUpload")
public class SysAppUploadController extends BaseController {

	@Autowired
	private SysAppUploadService sysAppUploadService;
	
	@ModelAttribute
	public SysAppUpload get(@RequestParam(required=false) String id) {
		SysAppUpload entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysAppUploadService.get(id);
		}
		if (entity == null){
			entity = new SysAppUpload();
		}
		return entity;
	}
	
	@RequiresPermissions("appupload:sysAppUpload:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysAppUpload sysAppUpload, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysAppUpload> page = sysAppUploadService.findPage(new Page<SysAppUpload>(request, response), sysAppUpload); 
		model.addAttribute("page", page);
		return "flat/appupload/sysAppUploadList";
	}

	@RequiresPermissions("appupload:sysAppUpload:view")
	@RequestMapping(value = "form")
	public String form(SysAppUpload sysAppUpload, Model model) {
		model.addAttribute("sysAppUpload", sysAppUpload);
		return "flat/appupload/sysAppUploadForm";
	}

	@RequiresPermissions("appupload:sysAppUpload:edit")
	@RequestMapping(value = "save")
	public String save(SysAppUpload sysAppUpload, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysAppUpload)){
			return form(sysAppUpload, model);
		}
		sysAppUploadService.save(sysAppUpload);
		addMessage(redirectAttributes, "保存App 上传记录表成功");
		return "redirect:"+Global.getAdminPath()+"/appupload/sysAppUpload/?repage";
	}
	
	@RequiresPermissions("appupload:sysAppUpload:edit")
	@RequestMapping(value = "delete")
	public String delete(SysAppUpload sysAppUpload, RedirectAttributes redirectAttributes) {
		sysAppUploadService.delete(sysAppUpload);
		addMessage(redirectAttributes, "删除App 上传记录表成功");
		return "redirect:"+Global.getAdminPath()+"/appupload/sysAppUpload/?repage";
	}
	@ResponseBody
	@RequestMapping(value = "uploadApp", method = RequestMethod.POST)
	public SysAppUpload uploadApp(MultipartFile file, HttpServletRequest request) throws IOException {
		SysAppUpload attachFile = sysAppUploadService.uploadFile(file, request);
		return attachFile;
	}
	@ResponseBody
	@RequestMapping(value = "d/{id}", method = RequestMethod.GET)
	public void downloadApp(@PathVariable String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		sysAppUploadService.downloadAppFile(id,request,response);
	}
	@ResponseBody
	@RequestMapping(value = "getAppInfo", method = RequestMethod.GET)
	public String getAppInfo(){
		return sysAppUploadService.getAppInfo().getUrl();
	}

}
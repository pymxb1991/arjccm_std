/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.face.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.list.entity.CcmList;
import com.arjjs.ccm.modules.ccm.list.service.CcmListService;
import com.arjjs.ccm.modules.iot.face.entity.CcmFaceControl;
import com.arjjs.ccm.modules.iot.face.service.CcmFaceControlService;
import com.arjjs.ccm.modules.iot.grabber.entity.CcmGrabberManage;
import com.arjjs.ccm.modules.iot.grabber.service.CcmGrabberManageService;
import com.arjjs.ccm.tool.CommUtil;
import com.google.common.base.Joiner;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 人脸布控实体类Controller
 * @author lgh
 * @version 2019-06-05
 */
@Controller
@RequestMapping(value = "${adminPath}/face/ccmFaceControl")
public class CcmFaceControlController extends BaseController {

	@Autowired
	private CcmFaceControlService ccmFaceControlService;

	//布控抓拍机
	@Autowired
	private CcmGrabberManageService ccmGrabberManageService;

	//名单库
	@Autowired
	private CcmListService ccmListService;

	@ModelAttribute
	public CcmFaceControl get(@RequestParam(required=false) String id) {
		CcmFaceControl entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmFaceControlService.get(id);
		}
		if (entity == null){
			entity = new CcmFaceControl();
		}
		return entity;
	}
	
	@RequiresPermissions("face:ccmFaceControl:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmFaceControl ccmFaceControl, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmFaceControl> page = ccmFaceControlService.findPage(new Page<CcmFaceControl>(request, response), ccmFaceControl); 
		model.addAttribute("page", page);
		return "iot/face/ccmFaceControlList";
	}

	@RequiresPermissions("face:ccmFaceControl:view")
	@RequestMapping(value = "form")
	public String form(CcmFaceControl ccmFaceControl,CcmList ccmList, Model model) {
		// 名单库列表
		List<CcmList> list = ccmListService.getList(ccmList);

		CcmGrabberManage ccmGrabberManage = new CcmGrabberManage();
		List<CcmGrabberManage> grabber = ccmGrabberManageService.findList(ccmGrabberManage);
		model.addAttribute("ccmFaceControl", ccmFaceControl);
		model.addAttribute("libraryList",list);
		model.addAttribute("grabberList",grabber);
		return "iot/face/ccmFaceControlForm";
	}

	@RequiresPermissions("face:ccmFaceControl:edit")
	@RequestMapping(value = "save")
	public void save(CcmFaceControl ccmFaceControl, Model model, HttpServletResponse response) {
		if (!beanValidator(model, ccmFaceControl)){
			// return form(ccmFaceControl, model);
		}

		if(ccmFaceControl.getLibrarySelectList()!=null&&ccmFaceControl.getLibrarySelectList().size()>0){
			String librarySelect = Joiner.on(",").join(ccmFaceControl.getLibrarySelectList());
			ccmFaceControl.setListId(librarySelect);
		}
		if(ccmFaceControl.getCaptureMachineList()!=null&&ccmFaceControl.getCaptureMachineList().size()>0){
			String captureMachine = Joiner.on(",").join(ccmFaceControl.getCaptureMachineList());
			ccmFaceControl.setMachine(captureMachine);
		}
		ccmFaceControlService.save(ccmFaceControl);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CommUtil.openWinExpDiv(out, "保存人脸布控记录成功");
	}
	
	@RequiresPermissions("face:ccmFaceControl:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmFaceControl ccmFaceControl, RedirectAttributes redirectAttributes) {
		ccmFaceControlService.delete(ccmFaceControl);
		addMessage(redirectAttributes, "删除人脸布控记录成功");
		return "redirect:"+Global.getAdminPath()+"/face/ccmFaceControl/?repage";
	}

}
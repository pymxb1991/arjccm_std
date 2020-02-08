package com.arjjs.ccm.modules.flat.userBindingDevice.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.flat.userBindingDevice.entity.UserBindingDevice;
import com.arjjs.ccm.modules.flat.userBindingDevice.service.UserBindingDeviceService;

@Controller
@RequestMapping(value = "${adminPath}/userBindingDevice/userBindingDevice")
public class UserBindingDeviceController extends BaseController {

	@Autowired
	private UserBindingDeviceService userBindingDeviceService;
	
	@ModelAttribute
	public UserBindingDevice get(@RequestParam(required=false) String id) {
		UserBindingDevice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = userBindingDeviceService.get(id);
		}
		if (entity == null){
			entity = new UserBindingDevice();
		}
		return entity;
	}
	
	@RequestMapping(value = "form")
	public String form(UserBindingDevice userBindingDevice, Model model) {
		model.addAttribute("userBindingDevice", userBindingDevice);
		return "flat/userBindingDevice/userBindingDeviceForm";
	}

	@RequestMapping(value = "save")
	public String save(UserBindingDevice userBindingDevice, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, userBindingDevice)){
			return form(userBindingDevice, model);
		}
		userBindingDeviceService.save(userBindingDevice);
		addMessage(redirectAttributes, "保存用户绑定设备成功");
		return "redirect:"+Global.getAdminPath()+"/userBindingDevice/userBindingDevice/finduserBindingDeviceList?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(UserBindingDevice userBindingDevice, RedirectAttributes redirectAttributes) {
		userBindingDeviceService.delete(userBindingDevice);
		addMessage(redirectAttributes, "删除用户绑定设备成功");
		return "redirect:"+Global.getAdminPath()+"/userBindingDevice/userBindingDevice/finduserBindingDeviceList?repage";
	}
	
	@RequestMapping(value = {"index"})
	public String index(UserBindingDevice userBindingDevice, Model model) {
		return "flat/userBindingDevice/userBindingDeviceIndex";
	}
	
	@RequestMapping(value = "finduserBindingDeviceList")
	public String finduserBindingDeviceList(UserBindingDevice userBindingDevice, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<UserBindingDevice> page = userBindingDeviceService.finduserBindingDeviceList(new Page<UserBindingDevice>(request, response), userBindingDevice);
		model.addAttribute("page", page);
		return "flat/userBindingDevice/userBindingDeviceList";
	}
	
}

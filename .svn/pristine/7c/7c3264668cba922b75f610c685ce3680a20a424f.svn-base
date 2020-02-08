package com.arjjs.ccm.modules.pbs.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymembind;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymemreg;
import com.arjjs.ccm.modules.pbs.person.service.PbsPartymemService;
import com.arjjs.ccm.modules.pbs.person.service.PbsPartymembindService;
import com.arjjs.ccm.modules.pbs.person.service.PbsPartymemregService;
import com.arjjs.ccm.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/sys/PbsCertification")
public class PbsCertificationController extends BaseController {
	
	@Autowired
	private PbsPartymemregService pbsPartymemregService;
	@Autowired
	private PbsPartymembindService pbsPartymembindService;
	@Autowired
	private PbsPartymemService pbsPartymemService;

	@RequestMapping("form")
	public String form(Model model,HttpServletRequest request, HttpServletResponse response) {
		// 检查当前用户是否已经是 党员 ，如果是 则 不能进行 登记
		 String userid = UserUtils.getUser().getId();
		// 党员注册信息
		PbsPartymemreg partymemregDto = new PbsPartymemreg();
		// 赋予党员信息
		partymemregDto.setUserid(userid);
		// 返回党员信息列表
		List<PbsPartymemreg> pbsPartymemregList = pbsPartymemregService.findList(partymemregDto);
		// 党员登记信息
		if (pbsPartymemregList.size() > 0) {
			// 返回的 登记信息
			PbsPartymemreg pbsPartymemreg = pbsPartymemregList.get(0);
			model.addAttribute("pbsPartymemreg", pbsPartymemreg);
			// 登记是否存在
			model.addAttribute("flagReg", true);
		} else {
			// 登记是否存在
			model.addAttribute("flagReg", false);
		}
		// 党员关系表信息
		PbsPartymembind pbsPartymembindDto = new PbsPartymembind();
		pbsPartymembindDto.setSPrimarykey01(userid);
		// 党员 关系
		List<PbsPartymembind> pbsPartymembindList = pbsPartymembindService.findList(pbsPartymembindDto);
		// 如果党员关系 存在
		if (pbsPartymembindList.size() > 0) {
			// 党员信息
			PbsPartymem partymem = pbsPartymemService.get(pbsPartymembindList.get(0).getSPartymemid());
			model.addAttribute("partymem", partymem);
			// 党员是否存在
			model.addAttribute("flagMem", true);
		}
		// 返回用户信息
		model.addAttribute("user", UserUtils.getUser());
		return "pbs/sys/pbsMemregisterPC";
	}
	
	// 页面登记功能
	@ResponseBody
	@RequestMapping(value = "register")
	public String register(PbsPartymemreg pbsPartymemreg, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsPartymemreg)) {
			// 失败 后 返回页面
			Map<String, Object> maps = model.asMap();
			return maps.get("message").toString();
		}
		// 添加 用户登录ip
		pbsPartymemreg.setSRegip(UserUtils.getUser().getLoginIp());
		// 添加 用户id
		pbsPartymemreg.setUserid(UserUtils.getUser().getId());
		// 验证数据是否重复
		if (pbsPartymemregService.checkExist(pbsPartymemreg)) {
			addMessage(model, "数据验证失败： 身份证信息数据重复");
			return "数据验证失败： 身份证信息数据重复";
		}
		// 保存党员
		pbsPartymemregService.save(pbsPartymemreg);
		addMessage(redirectAttributes, "保存党员登记信息成功");
		// 返回 个人信息页面
		return "sucess";
	}
	// 取消登记申请
	@RequestMapping(value = "regcancel")
	public String regcancel(PbsPartymemreg pbsPartymemreg, RedirectAttributes redirectAttributes) {
		pbsPartymemregService.delete(pbsPartymemreg);
		addMessage(redirectAttributes, "删除党员登记信息成功");
		return "redirect:" + Global.getAdminPath() + "/sys/user/info/";
	}
}
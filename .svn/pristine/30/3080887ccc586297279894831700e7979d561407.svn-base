/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.sys.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.common.web.BaseController;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActinotifications;
import com.arjjs.ccm.modules.pbs.activity.service.PbsActinotificationsService;
import com.arjjs.ccm.modules.pbs.person.entity.PbsDepartmentbind;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymembind;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymemreg;
import com.arjjs.ccm.modules.pbs.person.service.PbsDepartmentbindService;
import com.arjjs.ccm.modules.pbs.person.service.PbsPartymemService;
import com.arjjs.ccm.modules.pbs.person.service.PbsPartymembindService;
import com.arjjs.ccm.modules.pbs.person.service.PbsPartymemregService;
import com.arjjs.ccm.modules.pbs.sys.entity.Member;
import com.arjjs.ccm.modules.pbs.sys.entity.MemberJsonMap;
import com.arjjs.ccm.modules.pbs.sys.entity.PbsRemindMsg;
import com.arjjs.ccm.modules.pbs.sys.service.PbsDepartmentetcService;
import com.arjjs.ccm.modules.pbs.sys.service.PbsRemindMsgService;
import com.arjjs.ccm.modules.pbs.sys.entity.PbsOffice;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.pbs.sys.service.SystemServiceEx;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;
import com.google.common.collect.Lists;

/**
 * 登录Controller
 * 
 * @author lc
 * @version 2018-03-27
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/Mobile")
public class AdminMobileController extends BaseController {

	private static final String LIMIT = "member";
	// 党员关系信息
	@Autowired
	private PbsPartymembindService pbsPartymembindService;
	// 党员信息
	@Autowired
	private PbsPartymemService pbsPartymemService;
	// 党员登记信息
	@Autowired
	private PbsPartymemregService pbsPartymemregService;
	// 部门扩展信息
	@Autowired
	private PbsDepartmentetcService pbsDepartmentetcService;
	// 党员-部门关系
	@Autowired
	private PbsDepartmentbindService pbsDepartmentbindService;
	@Autowired
	private SystemServiceEx systemService;
	// 活动通知
	@Autowired
	private PbsActinotificationsService pbsActinotificationsService;
	// 通用通知
	@Autowired
	private PbsRemindMsgService pbsRemindMsgService;
	// @Autowired
	// private SessionDAO sessionDAO;

	// 显示 主页内容
	@RequestMapping(value = { "index", "" })
	public String pbsvotelist(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 进入网址首页
		return "/index";
	}

	// 跳转网页 -无数据加载
	@RequestMapping(value = "pageTurn")
	public String pageTurn(@RequestParam(required = false) String pageTo,
			@RequestParam(required = false) String pageLimit, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		PbsPartymem pbsPartymemCur = UserUtils.getPartymem();
		// 如果当前 为 非党员
		if (LIMIT.equals(pageLimit)) {
			if (StringUtils.isBlank(pbsPartymemCur.getId())) {
				return personInfo(request, response, model);
			}
		}
		// 当前的 党务工作
		if (StringUtils.isNoneBlank(pageTo) && (pageTo).contains("information")) {
			// 活动数据
			PbsActinotifications pbsActinotificationsCurDto = new PbsActinotifications();
			pbsActinotificationsCurDto.setsAcceptorid(pbsPartymemCur);
			// 未查看的信息状态
			pbsActinotificationsCurDto.setSStat("0");
			// 获取
			List<PbsActinotifications> pbsActinotificationMes = pbsActinotificationsService
					.findList(pbsActinotificationsCurDto);
			model.addAttribute("huodongcount", pbsActinotificationMes.size());
			// 关系转移
			PbsRemindMsg msgDto = new PbsRemindMsg();
			msgDto.setsAcceptorid(pbsPartymemCur);
			// 申请类型
			msgDto.setSFuncionid("0");
			model.addAttribute("zhuanyi", pbsRemindMsgService.getCountNotice(msgDto));
		}

		// 当前的 审批请示
		if (StringUtils.isNoneBlank(pageTo) && (pageTo).contains("Nav-personal/feedback/feedback")) {

		}
		// 进入跳转网页
		return pageTo;
	}

	// 跳转个人信息页面
	@RequestMapping(value = "personInfo")
	public String personInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 党员注册信息
		PbsPartymemreg partymemregDto = new PbsPartymemreg();
		// 赋予党员信息
		partymemregDto.setUserid(UserUtils.getUser().getId());
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
		pbsPartymembindDto.setSPrimarykey01(UserUtils.getUser().getId());
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
		// 跳转个人信息页面
		return "modules/sys/personalInfo";
	}

	// 党员登记页面
	@RequestMapping(value = "registerpage")
	public String registerpage(PbsPartymemreg pbsPartymemreg, Model model) {
		return "modules/sys/memregister";
	}

	// 页面登记功能
	@ResponseBody
	@RequestMapping(value = "register")
	public String register(PbsPartymemreg pbsPartymemreg, Model model, RedirectAttributes redirectAttributes) {
		// 当前用户
		User CurUser = UserUtils.getUser();
		if (!beanValidator(model, pbsPartymemreg)) {
			// 失败 后 返回页面
			Map<String, Object> maps = model.asMap();
			return maps.get("message").toString();
		}
		// 添加 用户登录ip
		pbsPartymemreg.setSRegip(CurUser.getLoginIp());
		// 添加 用户id
		pbsPartymemreg.setUserid(CurUser.getId());
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

	// 党员登记页面
	@RequestMapping(value = "applypage")
	public String applypage(PbsPartymemreg pbsPartymemreg, Model model) {
		return "modules/sys/memregister";
	}

	//
	@ResponseBody
	@RequestMapping(value = "uploadfile")
	public String uploadfile(Model model) {
		return "modules/sys/memregister";
	}

	// 获取指定对象的 党员列表
	@ResponseBody
	@RequestMapping(value = "getMemberList")
	public List<MemberJsonMap> getMemberList(String type) {
		PbsDepartmentbind pbsDepartmentbindDto = new PbsDepartmentbind();
		pbsDepartmentbindDto.setSPartymemid(UserUtils.getPartymem().getId());
		List<PbsDepartmentbind> pbsDepartmentbinds = pbsDepartmentbindService.findList(pbsDepartmentbindDto);
		List<PbsOffice> listOffice = new ArrayList<>();
		//
		for (PbsDepartmentbind bind : pbsDepartmentbinds) {
			PbsOffice officeNew = new PbsOffice();
			officeNew.setType("2");
			officeNew.setId(bind.getSDepartmentid());
			listOffice.add(officeNew);
		}
		// 当前的的 用户所能获得的所有部门信息
		List<PbsOffice> list = pbsDepartmentetcService.getAllOfficeWithMem(listOffice);
		// 当前用户 所能获得
		PbsDepartmentbind PbsDepartmentbindDto = new PbsDepartmentbind();
		PbsDepartmentbindDto.setPartymemtype(type);
		List<PbsDepartmentbind> pbsDepartmentbindAll = pbsDepartmentbindService.findList(PbsDepartmentbindDto);

		// 开始填装 返回的 memberJson
		List<MemberJsonMap> memberjsonList = new ArrayList<>();

		// 循环 添加 office
		for (PbsOffice office : list) {
			MemberJsonMap memberjsonmap = new MemberJsonMap();
			memberjsonmap.setOffice(office.getParentName());
			List<Member> mems = Lists.newArrayList();
			memberjsonmap.setMember(mems);
			for (PbsDepartmentbind bind : pbsDepartmentbindAll) {
				// 如果当前的 用户
				if (bind.getSDepartmentid().equals(office.getId())) {
					Member memberObject = new Member();
					memberObject.setMemberNumber(bind.getSPartymemid());
					memberObject.setMemberName(bind.getPartymemname());
					mems.add(memberObject);
				}
			}
			if (mems.size() > 0) {
				memberjsonList.add(memberjsonmap);
			}
		}
		return memberjsonList;
	}

	// 修改密码
	@ResponseBody
	@RequestMapping(value = "modifyPwd")
	public String modifyPwd(String oldPassword, String newPassword, Model model) {
		User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(oldPassword) && StringUtils.isNotBlank(newPassword)) {
			if (SystemServiceEx.validatePassword(oldPassword, user.getPassword())) {
				systemService.updatePasswordById(user.getId(), user.getLoginName(), newPassword);
				model.addAttribute("message", "修改密码成功");
			} else {
				model.addAttribute("message", "修改密码失败，旧密码错误");
				return "wrong";
			}
		}
		model.addAttribute("user", user);
		return SUCCESS;
	}

	// 修改电话
	@ResponseBody
	@RequestMapping(value = "modifyTel")
	public String modifyTel(String telphone, Model model) {
		User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(telphone)) {
			user.setPhone(telphone);
			systemService.updateUserInfo(user);
		}
		model.addAttribute("user", user);
		return SUCCESS;
	}

}
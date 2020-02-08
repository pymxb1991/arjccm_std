/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.thinkingreport.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arjjs.ccm.modules.pbs.common.web.BaseController;
import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.thinkingreport.entity.PbsThinkingreport;
import com.arjjs.ccm.modules.pbs.thinkingreport.entity.PbsThinkingreportopt;
import com.arjjs.ccm.modules.pbs.thinkingreport.service.PbsThinkingreportService;
import com.arjjs.ccm.modules.pbs.thinkingreport.service.PbsThinkingreportoptService;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;

/**
 * 思想汇报信息Controller
 * 
 * @author lc
 * @version 2018-05-28
 */
@Controller
@RequestMapping(value = "${adminPath}/thinkingreport/Pc")
public class ThinkingreportPcController extends BaseController {

	// 思想汇报
	@Autowired
	private PbsThinkingreportService pbsThinkingreportService;
	// 思想汇报操作
	@Autowired
	private PbsThinkingreportoptService pbsThinkingreportoptService;

	private static final String OPTTYPEVAL = "0";
	private static final String OPTTYPEFORWARD = "1";

	// 思想汇报列表-自己发送或接受的
	@RequestMapping(value = "thinkRepValueList")
	public String thinkRepValueList(PbsThinkingreport pbsThinkingreport, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		PbsPartymem curPartymem = UserUtils.getPartymem();
		// 当前如果没有党员关系
		if (StringUtils.isNotBlank(curPartymem.getId())) {
			// 当前的发送者+ 接收者
			pbsThinkingreport.setsAcceptermem(curPartymem);
			pbsThinkingreport.setsReportermem(curPartymem);
			Page<PbsThinkingreport> page1 = pbsThinkingreportService
					.findPage(new Page<PbsThinkingreport>(request, response), pbsThinkingreport);
			// 被转送的接受者
			model.addAttribute("page", page1);
		}
		return "pbs/thinkingreport/pc/ThinkingreportValueList";
	}

	// 思想汇报列表-转发给我的
	@RequestMapping(value = "thinkRepForwardList")
	public String thinkRepForwardList(PbsThinkingreport pbsThinkingreport, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		PbsPartymem curPartymem = UserUtils.getPartymem();
		// 当前如果没有党员关系
		if (StringUtils.isNotBlank(curPartymem.getId())) {
			pbsThinkingreport.setsTransmem(curPartymem);
			Page<PbsThinkingreport> page2 = pbsThinkingreportService
					.findPageByTransmem(new Page<PbsThinkingreport>(request, response), pbsThinkingreport);
			model.addAttribute("page", page2);
		}
		return "/pbs/thinkingreport/pc/ThinkingreportForwardList";

	}

	// 思想汇报 具体任务
	@RequestMapping(value = "thinkRepInfo")
	public String thinkRepInfo(PbsThinkingreport pbsThinkingreportDto, Model model) {
		// 页面的思想汇报
		PbsThinkingreport pbsThinkingreport = pbsThinkingreportService.get(pbsThinkingreportDto.getId());
		model.addAttribute("pbsThinkingreport", pbsThinkingreport);
		// 当前的 为评价
		PbsThinkingreportopt pbsThinkingreportoptDto = new PbsThinkingreportopt();
		pbsThinkingreportoptDto.setSType(OPTTYPEVAL);
		pbsThinkingreportoptDto.setsReportid(pbsThinkingreportDto);
		List<PbsThinkingreportopt> pbsThinkingreportopts = pbsThinkingreportoptService
				.findList(pbsThinkingreportoptDto);
		// 评价的内容
		model.addAttribute("ThinkingreportoptValue", pbsThinkingreportopts);
		// 当前的 为转发
		PbsThinkingreportopt pbsThinkingreportoptDto2 = new PbsThinkingreportopt();
		pbsThinkingreportoptDto2.setSType(OPTTYPEFORWARD);
		pbsThinkingreportoptDto2.setsReportid(pbsThinkingreportDto);
		List<PbsThinkingreportopt> pbsThinkingreportopts2 = pbsThinkingreportoptService
				.findList(pbsThinkingreportoptDto2);
		// 转发的情况
		model.addAttribute("ThinkingreportoptForward", pbsThinkingreportopts2);
		// 当前的 处理的的人不能是本人
		if (UserUtils.getPartymem().getId().equals(pbsThinkingreport.getsReportermem().getId())) {
			model.addAttribute("selfflag", false);
		} else {
			model.addAttribute("selfflag", true);
		}
		return "pbs/thinkingreport/pc/ThinkingreportInfoForm";
	}

	// 思想汇报评价页面与反馈页面
	@RequestMapping(value = "thinknewform")
	public String thinknewform(PbsThinkingreport pbsThinkingreport, Model model) {
		model.addAttribute("pbsThinkingreport", pbsThinkingreport);
		return "pbs/thinkingreport/pc/ThinkingreportNewForm";
	}

	// 思想汇报评价页面与反馈页面
	@RequestMapping(value = "thinkRepDealPage")
	public String thinkRepDealPage(String id, String type, Model model) {
		// 返回信息
		PbsThinkingreport pbsThinkingreport = pbsThinkingreportService.get(id);
		model.addAttribute("pbsThinkingreport", pbsThinkingreport);
		model.addAttribute("pbsThinkingreportopt", new PbsThinkingreportopt());
		if ("0".equals(type)) {
			return "pbs/thinkingreport/pc/ThinkingreportDealValueForm";
		} else {
			return "pbs/thinkingreport/pc/ThinkingreportDealForwardForm";
		}
	}

	// 提交思想汇报
	@RequestMapping(value = "thinkRepSubmit")
	public String thinkRepSubmit(PbsThinkingreport pbsThinkingreport, Model model) {
		// 思想汇报 校验
		if (!beanValidator(model, pbsThinkingreport)) {
			return thinknewform(pbsThinkingreport, model);
		}

		// if () {
		// return thinknewform(pbsThinkingreport, model);
		// }

		// 党员信息
		PbsPartymem curMem = UserUtils.getPartymem();
		// 当前如果有党员关系
		if (StringUtils.isBlank(curMem.getId())) {
			addMessage(model, "数据验证失败： 您还不是党员");
			return thinknewform(pbsThinkingreport, model);
		}

		if (StringUtils.isBlank(pbsThinkingreport.getsAcceptermem().getId())) {
			addMessage(model, "数据验证失败： 您尚未选择接收人");
			return thinknewform(pbsThinkingreport, model);
		}
		pbsThinkingreport.setsReporteruser(UserUtils.getUser());
		pbsThinkingreport.setsReportermem(curMem);
		pbsThinkingreportService.save(pbsThinkingreport);
		return "redirect:" + Global.getAdminPath() + "/thinkingreport/Pc/thinkRepValueList?repage";

	}

	// 回复||反馈 思想汇报
	@RequestMapping(value = "thinkRepAction")
	public String thinkRepAction(PbsThinkingreportopt pbsThinkingreportopt, Model model) {
		// 查询该操作是否已经存在
		if (StringUtils.isNotBlank(UserUtils.getPartymem().getId())) {
			// 添加操作人信息
			pbsThinkingreportopt.setsOperatemem(UserUtils.getPartymem());
			pbsThinkingreportopt.setsOperateuser(UserUtils.getUser());
			// 更新
			pbsThinkingreportoptService.save(pbsThinkingreportopt);
		}
		return "redirect:" + Global.getAdminPath() + "/thinkingreport/Pc/thinkRepInfo?id="
				+ pbsThinkingreportopt.getsReportid().getId();
	}

}
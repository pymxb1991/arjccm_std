/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.thinkingreport.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.modules.pbs.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.thinkingreport.entity.PbsThinkingreport;
import com.arjjs.ccm.modules.pbs.thinkingreport.entity.PbsThinkingreportopt;
import com.arjjs.ccm.modules.pbs.thinkingreport.service.PbsThinkingreportService;
import com.arjjs.ccm.modules.pbs.thinkingreport.service.PbsThinkingreportoptService;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;

/**
 * 微信端思想汇报信息Controller
 * 
 * @author lc
 * @version 2018-05-28
 */
@Controller
@RequestMapping(value = "${adminPath}/thinkingreport/pbsThinkingreport")
public class MobileThinkingreportController extends BaseController {

	// 思想汇报
	@Autowired
	private PbsThinkingreportService pbsThinkingreportService;
	// 思想汇报操作
	@Autowired
	private PbsThinkingreportoptService pbsThinkingreportoptService;

	private static final String OPTTYPEVAL = "0";
	private static final String OPTTYPEFORWARD = "1";

	// 思想汇报任务列表
	@RequestMapping(value = "thinkRepList")
	public String dealtMyList(Model model) {

		PbsPartymem curPartymem = UserUtils.getPartymem();
		// 当前如果没有党员关系
		if (StringUtils.isNotBlank(curPartymem.getId())) {
			// 当前的发送者+ 接收者
			PbsThinkingreport pbsThinkingreportDto = new PbsThinkingreport();
			pbsThinkingreportDto.setsAcceptermem(curPartymem);
			pbsThinkingreportDto.setsReportermem(curPartymem);
			List<PbsThinkingreport> list1 = pbsThinkingreportService.findList(pbsThinkingreportDto);
			// 被转送的接受者
			PbsThinkingreport pbsThinkingreportDto2 = new PbsThinkingreport();
			pbsThinkingreportDto2.setsTransmem(curPartymem);
			List<PbsThinkingreport> list2 = pbsThinkingreportService.findListByTransmem(pbsThinkingreportDto2);
			Set<PbsThinkingreport> ts = new HashSet<PbsThinkingreport>();
			ts.addAll(list1);
			ts.addAll(list2);
			model.addAttribute("list", ts);
		}
		return "Nav-activity/proposal/thought/thoughtList";
	}

	// 思想汇报具体任务
	@RequestMapping(value = "thinkRepInfo")
	public String thinkRepInfo(String id, Model model) {
		PbsThinkingreport pbsThinkingreport = pbsThinkingreportService.get(id);
		model.addAttribute("pbsThinkingreport", pbsThinkingreport);
		// 当前的 为评价
		PbsThinkingreportopt pbsThinkingreportoptDto = new PbsThinkingreportopt();
		pbsThinkingreportoptDto.setSType(OPTTYPEVAL);
		pbsThinkingreportoptDto.setsReportid(new PbsThinkingreport(id));
		List<PbsThinkingreportopt> pbsThinkingreportopts = pbsThinkingreportoptService
				.findList(pbsThinkingreportoptDto);
		if (pbsThinkingreportopts.size() > 0) {
			model.addAttribute("ThinkingreportoptValue", pbsThinkingreportopts.get(0));
			model.addAttribute("valueFlag", true);
		} else {
			model.addAttribute("valueFlag", false);
		}
		// 当前的 为转发
		PbsThinkingreportopt pbsThinkingreportoptDto2 = new PbsThinkingreportopt();
		pbsThinkingreportoptDto2.setSType(OPTTYPEFORWARD);
		pbsThinkingreportoptDto2.setsReportid(new PbsThinkingreport(id));
		List<PbsThinkingreportopt> pbsThinkingreportopts2 = pbsThinkingreportoptService
				.findList(pbsThinkingreportoptDto2);
		if (pbsThinkingreportopts2.size() > 0) {
			model.addAttribute("ThinkingreportoptForward", pbsThinkingreportopts2.get(0));
			model.addAttribute("forwardFlag", true);
		} else {
			model.addAttribute("forwardFlag", false);
		}
		// 当前的 处理的的人不能是本人
		if (UserUtils.getPartymem().getId().equals(pbsThinkingreport.getsReportermem().getId())) {
			model.addAttribute("selfflag", false);
		} else {
			model.addAttribute("selfflag", true);
		}
		return "/Nav-activity/proposal/thought/thoughtInfo";
	}

	// 思想汇报具体任务
	@RequestMapping(value = "thinkRepDealPage")
	public String thinkRepDealPage(String id, String type, Model model) {
		model.addAttribute("id", id);
		if ("0".equals(type)) {
			return "/Nav-activity/proposal/thought/thoughtForward";
		} else {
			return "/Nav-activity/proposal/thought/thoughtValue";
		}
	}

	// 提交思想汇报
	@ResponseBody
	@RequestMapping(value = "thinkRepSubmit")
	public String thinkRepSubmit(PbsThinkingreport pbsThinkingreport, Model model) {
		// 当前如果有党员关系
		if (StringUtils.isNotBlank(UserUtils.getPartymem().getId())) {
			pbsThinkingreport.setsReporteruser(UserUtils.getUser());
			pbsThinkingreport.setsReportermem(UserUtils.getPartymem());
			pbsThinkingreportService.save(pbsThinkingreport);
			return SUCCESS;
		}
		return FAIL;
	}

	// 回复||反馈 思想汇报
	@ResponseBody
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
		return SUCCESS;
	}

	//
	@RequestMapping(value = "thinkRepData")
	public String thinkRepData(String id, Model model) {
		PbsThinkingreport pbsThinkingreport = pbsThinkingreportService.get(id);
		model.addAttribute("article", pbsThinkingreport);
		return "Nav-activity/proposal/thought/thoughtArtcleDate";
	}
}
package com.arjjs.ccm.modules.pbs.proposal.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.modules.pbs.common.web.BaseController;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.proposal.entity.PbsProposal;
import com.arjjs.ccm.modules.pbs.proposal.entity.PbsProposalarea;
import com.arjjs.ccm.modules.pbs.proposal.entity.PbsProposalopt;
import com.arjjs.ccm.modules.pbs.proposal.service.PbsProposalService;
import com.arjjs.ccm.modules.pbs.proposal.service.PbsProposalareaService;
import com.arjjs.ccm.modules.pbs.proposal.service.PbsProposaloptService;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/proposal/pbsProposalPC")
public class PbsProposalPCController extends BaseController {
	@Autowired
	private PbsProposalService pbsProposalService;
	@Autowired
	private PbsProposalareaService pbsProposalareaService;
	@Autowired
	private PbsProposaloptService pbsProposaloptService;

	@RequestMapping(value = "proposalList")
	public String proposalList(PbsProposal pbsProposal, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		PbsPartymem curmem = UserUtils.getPartymem();
		// 当前如果没有党员关系
		if (StringUtils.isNotBlank(curmem.getId())) {
			if (!"1".equals(pbsProposal.getSPublish())) {
				pbsProposal.setSPublish("0");
				pbsProposal.setsAcceptermem(curmem);
				pbsProposal.setsReportermem(curmem);
			}else{
				pbsProposal.setSPublish("1");
			}
			Page<PbsProposal> page = pbsProposalService.findPage(new Page<PbsProposal>(request, response), pbsProposal);
			model.addAttribute("page", page);
			// 获取 当前的 党员信息
			model.addAttribute("partymem", UserUtils.getPartymem());
		}
		return "pbs/proposal/PC/pbsProposalPCList";
	}

	// 跳转提交建言页面
	@RequestMapping(value = "form")
	public String form(Model model) {
		return "pbs/proposal/PC/pbsProposalPCForm";
	}

	// 提交 建言献策
	@ResponseBody
	@RequestMapping(value = "proposalSubmit")
	public String proposalSubmit(PbsProposal pbsProposal, Model model) {
		// 当前如果有党员关系
		if (StringUtils.isNotBlank(UserUtils.getPartymem().getId())) {
			// 获取 当前的 评价分区 的 接收人
			PbsProposalarea pbsProposalarea = pbsProposalareaService.get(pbsProposal.getsAreaid().getId());
			// 当前的 接收的党员
			pbsProposal.setsReportermem(UserUtils.getPartymem());
			pbsProposal.setsReporteruser(UserUtils.getUser());
			// 当前的 建议分区 接收的党员
			pbsProposal.setsAcceptermem(pbsProposalarea.getsMastermem());
			// 已经提交了
			pbsProposal.setSStat("0");
			pbsProposal.setSPublish("0");
			pbsProposalService.save(pbsProposal);
			return SUCCESS;
		}
		return FAIL;
	}

	// 获取 所有的 类型
	@RequestMapping(value = "namelist")
	public String namelist(PbsProposalarea pbsProposalarea, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<PbsProposalarea> list = pbsProposalareaService.findList(pbsProposalarea);
		model.addAttribute("list", list);
		return "mapping/PC/PbsProposalareaList";
	}

	// 建议详细信息
	@RequestMapping(value = "proposalInfo")
	public String proposalInfo(String id, Model model, HttpServletRequest request, HttpServletResponse response) {
		// 获取 当前的 建言献策
		PbsProposal pbsProposal = pbsProposalService.get(id);
		// 获取当前的 建言分区
		PbsProposalarea pbsProposalarea = pbsProposalareaService.get(pbsProposal.getsAreaid().getId());
		// 获取 当前的 建议
		model.addAttribute("pbsProposal", pbsProposal);
		// 获取 当前的 建议分区
		model.addAttribute("pbsProposalarea", pbsProposalarea);
		// 如果当前的审核人
		if (pbsProposalarea.getsMastermem().getId().equals(UserUtils.getPartymem().getId())) {
			model.addAttribute("handleFlag", true);
		}
		// 获取当前的 操作信息 当前的 信息 为
		PbsProposalopt pbsProposaloptDto = new PbsProposalopt();
		pbsProposaloptDto.setSOpttype("0");
		pbsProposaloptDto.setsProposalid(new PbsProposal(id));
		Page<PbsProposalopt> page = pbsProposaloptService.findPage(new Page<PbsProposalopt>(request, response),
				pbsProposaloptDto);
		model.addAttribute("page", page);
		// 返回 建议 id
		model.addAttribute("proposalId", id);
		return "pbs/proposal/PC/pbsProposalInfo";
	}

	// 审核当前的 建议
	@RequestMapping(value = "proposalHandlePage")
	public String proposalHandlePage(String id, Model model) {
		// 获取当前的建议
		PbsProposal pbsProposal = pbsProposalService.get(id);
		// 返回的内容
		model.addAttribute("proposal", pbsProposal);
		return "pbs/proposal/PC/pbsProposalHandle";
	}
}
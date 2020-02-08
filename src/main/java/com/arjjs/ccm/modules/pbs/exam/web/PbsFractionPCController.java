package com.arjjs.ccm.modules.pbs.exam.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arjjs.ccm.modules.pbs.common.web.BaseController;
import com.arjjs.ccm.modules.pbs.exam.entity.PbsExamaction;
import com.arjjs.ccm.modules.pbs.exam.service.PbsExamactionService;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;

//积分榜
@Controller
@RequestMapping(value = "${adminPath}/exam/pbsFractionPCController")
public class PbsFractionPCController extends BaseController {
	@Autowired
	private PbsExamactionService pbsExamactionService;
	
	// 积分排行榜
	@RequestMapping(value = "integralSelfList")
	public String integralSelfList(HttpSession httpSession, HttpServletRequest request, Model model, String roomid) {
		// 获取当前的考试人
		PbsPartymem partymemcur = UserUtils.getPartymem();
		PbsExamaction pbsExamactionDto = new PbsExamaction();
		// 个人
		List<PbsExamaction> examSelfList = pbsExamactionService.findIntegrallist(pbsExamactionDto);
		// 查询个人
		PbsExamaction pbsExamactionCurDto = new PbsExamaction();
		pbsExamactionCurDto.setsExaminee(partymemcur);
		pbsExamactionCurDto.setsStat("1");
		List<PbsExamaction> examactionList = pbsExamactionService.findList(pbsExamactionCurDto);
		model.addAttribute("user", partymemcur);
		model.addAttribute("usertime", examactionList.size()*5);
		model.addAttribute("examSelfList", examSelfList);
		return "/pbs/exam/scoreboard/pbsIntegralSelfList";
	}
	
	// 积分排行榜
	@RequestMapping(value = "integralOfcList")
	public String integralOfcList(HttpSession httpSession, HttpServletRequest request, Model model, String roomid) {
		// 获取当前的考试人
		PbsPartymem partymemcur = UserUtils.getPartymem();
		PbsExamaction pbsExamactionDto = new PbsExamaction();
		// 部门
		List<PbsExamaction> examOfcList = pbsExamactionService.findIntegralByOfc(pbsExamactionDto);
		// 查询个人
		PbsExamaction pbsExamactionCurDto = new PbsExamaction();
		pbsExamactionCurDto.setsExaminee(partymemcur);
		pbsExamactionCurDto.setsStat("1");
		List<PbsExamaction> examactionList = pbsExamactionService.findList(pbsExamactionCurDto);
		model.addAttribute("user", partymemcur);
		model.addAttribute("usertime", examactionList.size()*5);
		model.addAttribute("examOfcList", examOfcList);
		return "/pbs/exam/scoreboard/pbsIntegralOfcList";
	}
}
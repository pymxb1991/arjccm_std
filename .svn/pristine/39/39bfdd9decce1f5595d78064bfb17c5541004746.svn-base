package com.arjjs.ccm.modules.pbs.exam.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.modules.pbs.common.web.BaseController;
import com.arjjs.ccm.modules.pbs.exam.entity.PbsExamaction;
import com.arjjs.ccm.modules.pbs.exam.entity.PbsExampaper;
import com.arjjs.ccm.modules.pbs.exam.service.PbsExamactionService;
import com.arjjs.ccm.modules.pbs.exam.service.PbsExampaperService;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;

//积分榜
@Controller
@RequestMapping(value = "${adminPath}/fraction/Pc")
public class FractionPcController extends BaseController {

	// 试卷信息
	@Autowired
	private PbsExampaperService pbsExampaperService;
	// 答题
	@Autowired
	private PbsExamactionService pbsExamactionService;

	// 学习测评-分数排行
	@RequestMapping(value = "officelist")
	public String officelist(PbsExamaction pbsExamactionOfcDto ,HttpSession httpSession, HttpServletRequest request, HttpServletResponse response,
			Model model, String roomid) {
		PbsPartymem partymemcur = UserUtils.getPartymem();
		// 返回的试卷信息
		PbsExampaper pbsExampaper = pbsExampaperService.findListByLast();
		// 查询部门考试成绩
		pbsExamactionOfcDto.setsExampaper(pbsExampaper);
		Page<PbsExamaction> pbsExamactionOfcList = pbsExamactionService.findPageIReportByOfc(new Page<PbsExamaction>(request, response),
				pbsExamactionOfcDto);
		// 返回试卷 分数排行榜信息
		model.addAttribute("pbsExampaper", pbsExampaper);
		model.addAttribute("page", pbsExamactionOfcList);
		model.addAttribute("user", partymemcur);
		// 查询个人的成绩
		return "/pbs/exam/scoreboard/fractionOfficeList";
	}

	@RequestMapping(value = "persionlist")
	public String persionlist(PbsExamaction pbsExamactionDto, HttpSession httpSession, HttpServletRequest request,
			HttpServletResponse response, Model model, String roomid) {
		PbsPartymem partymemcur = UserUtils.getPartymem();
		// 返回的试卷信息
		PbsExampaper pbsExampaper = pbsExampaperService.findListByLast();
		// 查询此次考试成绩情况
		pbsExamactionDto.setsExampaper(pbsExampaper);
		Page<PbsExamaction> Page = pbsExamactionService.findPageByIReport(new Page<PbsExamaction>(request, response),
				pbsExamactionDto);
		// 查询个人的成绩
		PbsExamaction pbsExamactionCurDto = new PbsExamaction();
		pbsExamactionCurDto.setsExaminee(partymemcur);
		pbsExamactionCurDto.setsExampaper(pbsExampaper);
		List<PbsExamaction> pbsExamactionCur = pbsExamactionService.findList(pbsExamactionCurDto);
		// 如果当前的 考试结果存在
		if (pbsExamactionCur.size() > 0) {
			model.addAttribute("pbsExamactionCur", pbsExamactionCur.get(0));
		}
		// 返回试卷 分数排行榜信息
		model.addAttribute("pbsExampaper", pbsExampaper);
		model.addAttribute("page", Page);
		model.addAttribute("user", partymemcur);
		
		// 查询个人的成绩
		return "/pbs/exam/scoreboard/fractionPersionList";
	}

}
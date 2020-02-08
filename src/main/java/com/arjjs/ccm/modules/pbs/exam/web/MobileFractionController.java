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
import com.arjjs.ccm.modules.pbs.exam.entity.PbsExampaper;
import com.arjjs.ccm.modules.pbs.exam.service.PbsExamactionService;
import com.arjjs.ccm.modules.pbs.exam.service.PbsExampaperService;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;

//积分榜
@Controller
@RequestMapping(value = "${adminPath}/fraction")
public class MobileFractionController extends BaseController {

	// 试卷信息
	@Autowired
	private PbsExampaperService pbsExampaperService;
	// 答题
	@Autowired
	private PbsExamactionService pbsExamactionService;

	// 学习测评-分数排行
	@RequestMapping(value = "list")
	public String list(HttpSession httpSession, HttpServletRequest request, Model model, String roomid) {
		PbsPartymem partymemcur = UserUtils.getPartymem();
		// 返回的试卷信息
		PbsExampaper pbsExampaper = pbsExampaperService.findListByLast();
		// 查询此次考试成绩情况
		PbsExamaction pbsExamactionDto = new PbsExamaction();
		pbsExamactionDto.setsExampaper(pbsExampaper);
		List<PbsExamaction> pbsExamactionList = pbsExamactionService.findListByIReport(pbsExamactionDto);
		// 查询部门考试成绩
		PbsExamaction pbsExamactionOfcDto = new PbsExamaction();
		pbsExamactionOfcDto.setsExampaper(pbsExampaper);
		List<PbsExamaction> pbsExamactionOfcList = pbsExamactionService.findIReportByOfc(pbsExamactionOfcDto);
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
		model.addAttribute("pbsExamactionOfcList", pbsExamactionOfcList);
		model.addAttribute("pbsExamactionList", pbsExamactionList);
		model.addAttribute("user", partymemcur);
		// 查询个人的成绩
		return "/Nav-Study/fraction/fraction";
	}

	// 积分排行榜
	@RequestMapping(value = "integrallist")
	public String integrallist(HttpSession httpSession, HttpServletRequest request, Model model, String roomid) {
		// 获取当前的考试人
		PbsPartymem partymemcur = UserUtils.getPartymem();
		// 获取当前的时间
		// Calendar cal1 = Calendar.getInstance();
		// Calendar cal2 = Calendar.getInstance();
		// Date beginDay = new Date(DateUtils.getDate());
		// cal1.setTime(beginDay);
		// cal1.set(Calendar.DAY_OF_MONTH, 1);
		// Date endDay = new Date();
		// cal2.setTime(endDay);
		// cal2.set(Calendar.DAY_OF_MONTH, 1);
		// cal2.roll(Calendar.DAY_OF_MONTH, -1);
		//
		PbsExamaction pbsExamactionDto = new PbsExamaction();
		// pbsExamactionDto.setBeginHappenDate(cal1.getTime());
		// pbsExamactionDto.setEndHappenDate(cal2.getTime());
		
		// 个人
		List<PbsExamaction> examSelfList = pbsExamactionService.findIntegrallist(pbsExamactionDto);
		// 部门
		List<PbsExamaction> examOfcList = pbsExamactionService.findIntegralByOfc(pbsExamactionDto);
		// 查询个人
		PbsExamaction pbsExamactionCurDto = new PbsExamaction();
		pbsExamactionCurDto.setsExaminee(partymemcur);
		// 自测数
		pbsExamactionCurDto.setsStat("1");
		List<PbsExamaction> examactionList = pbsExamactionService.findList(pbsExamactionCurDto);
	
		model.addAttribute("user", partymemcur);
		model.addAttribute("usertime", examactionList.size()*5);
		model.addAttribute("examSelfList", examSelfList);
		model.addAttribute("examOfcList", examOfcList);
		return "Nav-personal/integral/integral";
	}
	
	
	
}
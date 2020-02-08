/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.exam.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.Collections3;
import com.arjjs.ccm.common.utils.DateUtils;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.common.web.BaseController;
import com.arjjs.ccm.modules.pbs.exam.entity.PbsExamaction;
import com.arjjs.ccm.modules.pbs.exam.entity.PbsExamactionitem;
import com.arjjs.ccm.modules.pbs.exam.entity.PbsExampaper;
import com.arjjs.ccm.modules.pbs.exam.entity.PbsExampaperitem;
import com.arjjs.ccm.modules.pbs.exam.service.PbsExamactionService;
import com.arjjs.ccm.modules.pbs.exam.service.PbsExamactionitemService;
import com.arjjs.ccm.modules.pbs.exam.service.PbsExampaperService;
import com.arjjs.ccm.modules.pbs.exam.service.PbsExampaperitemService;
import com.arjjs.ccm.modules.pbs.person.entity.PbsDepartmentbind;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.person.service.PbsDepartmentbindService;
import com.arjjs.ccm.modules.pbs.question.entity.PbsChoiceItem;
import com.arjjs.ccm.modules.pbs.question.entity.PbsQuestionMajor;
import com.arjjs.ccm.modules.pbs.question.entity.PbsQuestionObjective;
import com.arjjs.ccm.modules.pbs.question.entity.PbsQuestionProject;
import com.arjjs.ccm.modules.pbs.question.service.PbsChoiceItemService;
import com.arjjs.ccm.modules.pbs.question.service.PbsQuestionObjectiveService;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 考试Controller
 * 
 * @author lc
 * @version 2018-04-25
 */
@Controller
@RequestMapping(value = "${adminPath}/exam/testPC")
public class ExamactionPcController extends BaseController {
	// 试卷信息
	@Autowired
	private PbsExampaperService pbsExampaperService;
	// 试卷的题目详细信息
	@Autowired
	private PbsExampaperitemService pbsExampaperitemService;
	// 答题
	@Autowired
	private PbsExamactionService pbsExamactionService;
	// 答题的详细信息
	@Autowired
	private PbsExamactionitemService pbsExamactionitemService;
	// 客观题
	@Autowired
	private PbsQuestionObjectiveService pbsQuestionObjectiveService;
	// 选项信息
	@Autowired
	private PbsChoiceItemService pbsChoiceItemService;
	// 查询部门
	@Autowired
	private PbsDepartmentbindService pbsDepartmentbindService;

	private static final String PROJECTTYPE = "af58025cad0c4430be6c7273268e77ae";

	// 获取当前用户的自测结果列表
	@RequestMapping(value = "selftestList")
	public String selftestList(PbsExamaction pbsExamactionDto, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 获取当前的党员
		PbsPartymem PbsPartymemCur = UserUtils.getPartymem();
		// 当前是用户的情况下
		if (StringUtils.isNotBlank(PbsPartymemCur.getId())) {
			// 提交状态
			pbsExamactionDto.setsExaminee(PbsPartymemCur);
			// 自动为自测 系统出卷
			PbsExampaper pbsExampaperDto = new PbsExampaper();
			pbsExampaperDto.setSWay("1");
			// 已经完成的试卷
			pbsExamactionDto.setsStat("1");
			if(pbsExamactionDto.getsExampaper() != null && !pbsExamactionDto.getsExampaper().equals("")) {
				pbsExampaperDto.setSName(pbsExamactionDto.getsExampaper().getSName());
			}
			pbsExamactionDto.setsExampaper(pbsExampaperDto);
			// 获取当前的信息
			Page<PbsExamaction> list = pbsExamactionService.findPage(new Page<PbsExamaction>(request, response),
					pbsExamactionDto);
			model.addAttribute("page", list);
		}
		return "pbs/exam/pcself/pbsExamactionList";
	}

	@RequestMapping(value = "officeSelfList")
	public String officeSelfList(PbsDepartmentbind pbsDepartmentbindDto, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 获取当前的党员
		PbsPartymem PbsPartymemCur = UserUtils.getPartymem();
		// 当前是用户的情况下
		if (StringUtils.isNotBlank(PbsPartymemCur.getId())) {
			String id = PbsPartymemCur.getId();
			// 查询当前的党支部关系信息
			pbsDepartmentbindDto.setSPartymemid(id);
			Page<PbsDepartmentbind> departmentbindList = pbsDepartmentbindService
					.findPage(new Page<PbsDepartmentbind>(request, response), pbsDepartmentbindDto);
			model.addAttribute("page", departmentbindList);
		}

		return "pbs/exam/pcself/pbsDepartmentbindList";
	}

	// 生成试卷内容
	@RequestMapping(value = "examtest")
	public String examtest(String sDepartment, HttpServletRequest request, HttpServletResponse response, Model model) {
		// 获取当前的党员
		PbsPartymem PbsPartymemCur = UserUtils.getPartymem();
		// 考题数量
		int Questioncount = 5;
		/** 1. 生成试卷 */
		// 生成的试卷
		PbsExampaper pbsExampaperCur = new PbsExampaper();
		pbsExampaperCur.setSName(PbsPartymemCur.getSName() + "_在线自测" + "_" + DateUtils.getDateTime());
		// 当前的考试类别只有党建
		pbsExampaperCur.setsMajor(new PbsQuestionMajor("000001"));
		// 默认一个
		pbsExampaperCur.setsProject(new PbsQuestionProject(PROJECTTYPE));
		pbsExampaperCur.setSTitle("党建自测");
		pbsExampaperCur.setSWay("1");
		// 默认40分钟
		pbsExampaperCur.setIExamtime(40);
		pbsExampaperCur.setDtStart(new Date());
		// 进行添加当前的 考试试卷信息
		pbsExampaperService.save(pbsExampaperCur);
		/** 2.答题情况的一个默认信息 */
		PbsExamaction pbsExamactionCur = new PbsExamaction();
		// 赋值当前的党员信息
		pbsExamactionCur.setsExaminee(PbsPartymemCur);
		pbsExamactionCur.setsExampaper(pbsExampaperCur);
		pbsExamactionCur.setIReport(0);
		pbsExamactionCur.setsStat("0");
		pbsExamactionCur.setDtStart(new Date());
		pbsExamactionCur.setsDepartment(new Office(sDepartment));
		// sDepartment
		pbsExamactionService.save(pbsExamactionCur);
		/** 3. 添加试卷中题目 */
		// 获取题库中所有题目
		PbsQuestionObjective pbsQuestionObjective  = new PbsQuestionObjective();
		//获取单选题
		pbsQuestionObjective.setSType("0");
		List<PbsQuestionObjective> objectiveList = pbsQuestionObjectiveService.findList(pbsQuestionObjective);
		//获取多选题
		pbsQuestionObjective.setSType("1");
		List<PbsQuestionObjective> objectiveList1 = pbsQuestionObjectiveService.findList(pbsQuestionObjective);
		//获取判断题
		pbsQuestionObjective.setSType("2");
		List<PbsQuestionObjective> objectiveList2 = pbsQuestionObjectiveService.findList(pbsQuestionObjective);
		// 收集当前的题目
		List<PbsExampaperitem> itemList = Lists.newArrayList();
		// 目前选取5道题
		// 随机数据获取
		List<Integer> randomList = randomListElement(objectiveList.size(), Questioncount);
		List<Integer> randomList1 = randomListElement(objectiveList1.size(), Questioncount);
		List<Integer> randomList2 = randomListElement(objectiveList2.size(), Questioncount);
		// 题号
		int count = 1;
		// 遍历当前的选择题情况
		for (int index : randomList) {
			PbsExampaperitem itemDto = new PbsExampaperitem();
			// 他的试卷信息
			itemDto.setsExampaper(pbsExampaperCur);
			itemDto.setISort(count);
			itemDto.setsItem(objectiveList.get(index));
			// 题目的类型
			itemDto.setSBelongtype(objectiveList.get(index).getSType());
			itemList.add(itemDto);
			count++;
		}
		// 遍历当前的选择题情况
		for (int index : randomList1) {
			PbsExampaperitem itemDto = new PbsExampaperitem();
			// 他的试卷信息
			itemDto.setsExampaper(pbsExampaperCur);
			itemDto.setISort(count);
			itemDto.setsItem(objectiveList1.get(index));
			// 题目的类型
			itemDto.setSBelongtype(objectiveList1.get(index).getSType());
			itemList.add(itemDto);
			count++;
		}
		for (int index : randomList2) {
			PbsExampaperitem itemDto = new PbsExampaperitem();
			// 他的试卷信息
			itemDto.setsExampaper(pbsExampaperCur);
			itemDto.setISort(count);
			itemDto.setsItem(objectiveList2.get(index));
			// 题目的类型
			itemDto.setSBelongtype(objectiveList2.get(index).getSType());
			itemList.add(itemDto);
			count++;
		}
		// 新增当前试卷中的所有考试题目
		pbsExampaperitemService.insertAll(itemList);
		/** 4. 获取一个试卷所有的题目以及选项内容. */
		// 获取所有的选项信息
		PbsChoiceItem itemDto = new PbsChoiceItem();
		itemDto.setPaperid(pbsExampaperCur.getId());
		List<PbsChoiceItem> choiceList = pbsChoiceItemService.findListByPaper(itemDto);
		// 遍历所有的题目 填充选项
		for (PbsExampaperitem Examitem : itemList) {
			// 所有的选项信息
			List<PbsChoiceItem> papaerChoices = Lists.newArrayList();
			// 遍历
			for (PbsChoiceItem Qustentchoice : choiceList) {
				// 当前的题库中选项的客观题 id = 试卷中的客观题的id
				if (Qustentchoice.getsParentid().getId().equals(Examitem.getsItem().getId())) {
					papaerChoices.add(Qustentchoice);
				}
			}
			// 添加 选项
			Examitem.setChoiceList(papaerChoices);
		}
		// 所有题目以及答案
		model.addAttribute("itemList", itemList);
		model.addAttribute("sDepartment", sDepartment);
		model.addAttribute("pbsExamactionCur", pbsExamactionCur);
		return "pbs/exam/pcself/pbsExamSelfForm";
	}

	// 提交自测答案
	@ResponseBody
	@RequestMapping(value = "examAction")
	public String examAction(PbsExamaction pbsExamaction, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 获取当前的党员
		PbsPartymem PbsPartymemCur = UserUtils.getPartymem();
		// 获取考试信息
		PbsExamaction pbsExamactionDto = pbsExamactionService.get(pbsExamaction.getId());
		// 接收自测的结果
		String resultCols = pbsExamaction.getResults();
		String[] choiceItem = resultCols.split(";");
		// 考题数量
		int questionCount = choiceItem.length;
		// 每一道题的分数
		int questionScore = 100 / questionCount;
		// 获取所有的
		PbsQuestionObjective ObjectiveDto = new PbsQuestionObjective();
		// ObjectiveDto.setPaperid(pbsExamaction.getId());
		// 题库中选项
		List<PbsQuestionObjective> TchoiceList = pbsQuestionObjectiveService.findList(ObjectiveDto);
		// list 转map
		Map<String, PbsQuestionObjective> choiceMap = ChoiceList_Map(TchoiceList);
		// 需要插入的结果list
		List<PbsExamactionitem> pbsExamactionitems = new ArrayList<>();
		int score = 0;
		// 遍历 选项
		for (String Pchoices : choiceItem) {
			String[] items = Pchoices.split(",");
			// 选择题id
			String choiceid = items[0];
			// 如果正常的为两个结果
			String choiceres = items.length == 2 ? items[1] : "";
			PbsQuestionObjective Objective = choiceMap.get(choiceid);
			/** 生成 结果list */
			PbsExamactionitem pbsExamactionitem = new PbsExamactionitem();
			// 使之变成两个正确的答案
			String choicereComp = choiceres.replaceAll("、", "");
			String rightComp = Objective.getSAnswer().replaceAll("、", "");
			// 相同就是正确答案
			String scorcFlag = choicereComp.equalsIgnoreCase(rightComp) ? "1" : "0";
			pbsExamactionitem.setsExampaper(pbsExamactionDto.getsExampaper());
			pbsExamactionitem.setsExaminee(PbsPartymemCur);
			pbsExamactionitem.setsItem(Objective);
			pbsExamactionitem.setsDoanswer(choiceres);
			pbsExamactionitem.setsActionid(pbsExamactionDto);
			pbsExamactionitem.setSJudge(scorcFlag);
			// 结果
			pbsExamactionitem.setIScore(questionScore);
			// 插入前的赋值基础
			pbsExamactionitem.preInsert();
			// 正确的结果数据结果的累加
			if ("1".equals(scorcFlag)) {
				score += questionScore;
			}
			pbsExamactionitems.add(pbsExamactionitem);
		}
		// 插入数据
		pbsExamactionitemService.insertAll(pbsExamactionitems);
		/**
		 * 更新考试分数以及考试结束时间
		 */
		pbsExamactionDto.setsStat("1");
		pbsExamactionDto.setDtSubmit(new Date());
		pbsExamactionDto.setIReport(score);
		pbsExamactionService.save(pbsExamactionDto);
		return SUCCESS;
	}

	// 返回试卷自测内容
	@RequestMapping(value = "examcheck")
	public String examcheck(String id, HttpServletRequest request, HttpServletResponse response, Model model) {
		// 获取当前的 考试信息
		PbsExamaction pbsExamaction = pbsExamactionService.get(id);
		// 获取当前的 试卷信息
		PbsExampaper pbsExampaper = pbsExampaperService.get(pbsExamaction.getsExampaper().getId());
		// 获取试卷所有选择题信息
		PbsExampaperitem pbsExampaperitemDto = new PbsExampaperitem();
		pbsExampaperitemDto.setsExampaper(pbsExampaper);
		List<PbsExampaperitem> pbsExampaperitems = pbsExampaperitemService.findList(pbsExampaperitemDto);
		// 获取题库的信息
		PbsChoiceItem itemDto = new PbsChoiceItem();
		itemDto.setPaperid(pbsExampaper.getId());
		List<PbsChoiceItem> choiceList = pbsChoiceItemService.findListByPaper(itemDto);

		// 客观题选择的结果
		PbsExamactionitem pbsExamactionitemDto = new PbsExamactionitem();
		pbsExamactionitemDto.setsActionid(pbsExamaction);
		List<PbsExamactionitem> pbsExamactionitems = pbsExamactionitemService.findList(pbsExamactionitemDto);

		// 添加选项信息
		for (PbsExampaperitem paperItem : pbsExampaperitems) {
			// 接收的对象列表
			List<PbsChoiceItem> choices = Lists.newArrayList();
			for (PbsChoiceItem TItem : choiceList) {
				// 试题的item 是客观题 ,选项的父id 是客观题
				if (paperItem.getsItem().getId().equals(TItem.getsParentid().getId())) {
					choices.add(TItem);
				}
				// 为当前的试题 添加选项
				paperItem.setChoiceList(choices);
			}
			// 考试信息 用户选择信息
			for (PbsExamactionitem actionItem : pbsExamactionitems) {
				if (actionItem.getsItem().getId().equals(paperItem.getsItem().getId())) {
					// 用户选择的内容
					paperItem.setPbsExamactionitem(actionItem);
				}
			}
		}

		model.addAttribute("pbsExamaction", pbsExamaction);
		// 试卷题目信息
		model.addAttribute("pbsExampaperitems", pbsExampaperitems);
		return "pbs/exam/pcself/pbsExamSelfCheckForm";
	}
	
	//返回错题库的信息
	@RequestMapping(value = "errorExam")
	public String errorExam(String id, HttpServletRequest request, HttpServletResponse response, Model model) {
		// 错题题目以及结果信息
		PbsExampaper pbsExampaper = new PbsExampaper();
		PbsExamactionitem pbsExamactionitemDto = new PbsExamactionitem();
		pbsExamactionitemDto.setSJudge("0");
		pbsExamactionitemDto.setsExaminee(UserUtils.getPartymem());
		pbsExampaper.setSWay("1");
		pbsExamactionitemDto.setsExampaper(pbsExampaper);
		List<PbsExamactionitem> pbsExamactionitems = pbsExamactionitemService.findErrorList(pbsExamactionitemDto);
		int errorNum = pbsExamactionitems.size();
		model.addAttribute("errorNum", errorNum);
		List<PbsExampaperitem> pbsExampaperitems = new ArrayList<>();
		List<String> itemList = new ArrayList<>();
		for(PbsExamactionitem items:pbsExamactionitems) {
			PbsExampaperitem paperItem = new PbsExampaperitem();
			itemList.add(items.getsItem().getId());
			paperItem.setPbsExamactionitem(items);
			paperItem.setsItem(items.getsItem());
			pbsExampaperitems.add(paperItem);
		}
		//获取错题的所有选项信息
		if(itemList.size() > 0) {
			List<PbsChoiceItem> choiceList = pbsChoiceItemService.findListByParentId(itemList,"0");
			// 为错题添加选项信息
			for (PbsExampaperitem paperItem : pbsExampaperitems) {
				// 接收的对象列表
				List<PbsChoiceItem> choices = Lists.newArrayList();
				for (PbsChoiceItem TItem : choiceList) {
					// 试题的item 是客观题 ,选项的父id 是客观题
					if (paperItem.getsItem().getId().equals(TItem.getsParentid().getId())) {
						choices.add(TItem);
					}
					// 为当前的试题 添加选项
					paperItem.setChoiceList(choices);
				}
				// 考试信息 用户选择信息
				for (PbsExamactionitem actionItem : pbsExamactionitems) {
					if (actionItem.getsItem().getId().equals(paperItem.getsItem().getId())) {
						// 用户选择的内容
						paperItem.setPbsExamactionitem(actionItem);
					}
				}
			}
		}
		// 试卷题目信息
		model.addAttribute("pbsExampaperitems", pbsExampaperitems);
		return "pbs/exam/pcself/pbsErrorExamSelfForm";
	}
	
	// 获取当前用户的在线考试列表列表
	@RequestMapping(value = "onlineList")
	public String onlineList(PbsExampaper pbsExampaperDto, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 获取当前的党员
		PbsPartymem PbsPartymemCur = UserUtils.getPartymem();
		// 当前是用户的情况下
		if (StringUtils.isNotBlank(PbsPartymemCur.getId())) {
			// 获取当前的值
			// PbsExampaper pbsExampaperDto = new PbsExampaper();
			// 当前已经发布
			pbsExampaperDto.setsStat("1");
			// 当前是在线
			pbsExampaperDto.setSWay("0");
			Page<PbsExampaper> ExampaperPage = pbsExampaperService.findPage(new Page<PbsExampaper>(request, response),
					pbsExampaperDto);
			// 获取已经考试过了的试卷
			PbsExamaction pbsExamactionDto = new PbsExamaction();
			pbsExamactionDto.setsExampaper(pbsExampaperDto);
			pbsExamactionDto.setsExaminee(PbsPartymemCur);
			List<PbsExamaction> pbsExamactionList = pbsExamactionService.findList(pbsExamactionDto);
			String examactions = Collections3.extractToString(pbsExamactionList, "sExampaper.id", ",");
			// Examactionmaps
			Map<String, PbsExamaction> examactionMaps = Examaction_Map(pbsExamactionList);
			// 获取当前的信息
			model.addAttribute("page", ExampaperPage);
			for (PbsExampaper paper : ExampaperPage.getList()) {
				// 当前的 已考的考试 不包含与当前的试卷
				if (examactions.contains(paper.getId())) {
					paper.setWriteFlag("1");
					paper.setiReport(examactionMaps.get(paper.getId()).getIReport());
					paper.setExamActionId(examactionMaps.get(paper.getId()).getId());
				} else {
					paper.setWriteFlag("0");
					// -1 为 未参加考试
					paper.setiReport(-1);
				}
			}
			// model.addAttribute("examactions", examactions);
			// model.addAttribute("exammap", Examaction_Map(pbsExamactionList));
		}
		// model.addAttribute("date", new Date());
		// model.addAttribute("sDepartment", sDepartment);
		return "pbs/exam/pconline/pbsExampaperListOnline";
	}

	
	// 获取当前用户的综合考试列表
	@RequestMapping(value = "synthesizeList")
	public String synthesizeList(PbsExampaper pbsExampaperDto, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 获取当前的党员
		PbsPartymem PbsPartymemCur = UserUtils.getPartymem();
		// 当前是用户的情况下
		if (StringUtils.isNotBlank(PbsPartymemCur.getId())) {
			// 获取当前的值
			// PbsExampaper pbsExampaperDto = new PbsExampaper();
			// 当前已经发布
			pbsExampaperDto.setsStat("1");
			// 当前是在线
			pbsExampaperDto.setSWay("2");
			Page<PbsExampaper> ExampaperPage = pbsExampaperService.findPage(new Page<PbsExampaper>(request, response),
					pbsExampaperDto);
			// 获取已经考试过了的试卷
			PbsExamaction pbsExamactionDto = new PbsExamaction();
			pbsExamactionDto.setsExampaper(pbsExampaperDto);
			pbsExamactionDto.setsExaminee(PbsPartymemCur);
			List<PbsExamaction> pbsExamactionList = pbsExamactionService.findList(pbsExamactionDto);
			String examactions = Collections3.extractToString(pbsExamactionList, "sExampaper.id", ",");
			// Examactionmaps
			Map<String, PbsExamaction> examactionMaps = Examaction_Map(pbsExamactionList);
			// 获取当前的信息
			model.addAttribute("page", ExampaperPage);
			for (PbsExampaper paper : ExampaperPage.getList()) {
				// 当前的 已考的考试 不包含与当前的试卷
				if (examactions.contains(paper.getId())) {
					paper.setWriteFlag("1");
					paper.setiReport(examactionMaps.get(paper.getId()).getIReport());
					paper.setExamActionId(examactionMaps.get(paper.getId()).getId());
				} else {
					paper.setWriteFlag("0");
					// -1 为 未参加考试
					paper.setiReport(-1);
				}
			}
			// model.addAttribute("examactions", examactions);
			// model.addAttribute("exammap", Examaction_Map(pbsExamactionList));
		}
		// model.addAttribute("date", new Date());
		// model.addAttribute("sDepartment", sDepartment);
		return "pbs/exam/pconline/pbsExampaperListSynthesize";
	}
	
	
	
	// 显示试卷
	@RequestMapping(value = "onlinetest")
	public String onlinetest(String id, HttpServletRequest request, HttpServletResponse response, Model model) {
		// String sDepartment,
		/** 获取试卷的信息 */
		// 试卷信息
		PbsExampaper pbsExampaper = pbsExampaperService.get(id);
		model.addAttribute("pbsExampaper", pbsExampaper);
		// 获取所有试题
		PbsExampaperitem pbsExampaperitemto = new PbsExampaperitem();
		pbsExampaperitemto.setsExampaper(pbsExampaper);
		List<PbsExampaperitem> pbsExampaperitemList = pbsExampaperitemService.findList(pbsExampaperitemto);
		// 获取所有试题的选项
		PbsChoiceItem pbsChoiceItemDto = new PbsChoiceItem();
		pbsChoiceItemDto.setPaperid(id);
		List<PbsChoiceItem> pbsChoiceItemList = pbsChoiceItemService.findListByPaper(pbsChoiceItemDto);
		// 为所有的题目添加选项
		for (PbsExampaperitem Examitem : pbsExampaperitemList) {
			// 所有的选项信息
			List<PbsChoiceItem> papaerChoices = Lists.newArrayList();
			// 遍历
			for (PbsChoiceItem Qustentchoice : pbsChoiceItemList) {
				// 当前的题库中选项的客观题 id = 试卷中的客观题的id
				if (Qustentchoice.getsParentid().getId().equals(Examitem.getsItem().getId())) {
					papaerChoices.add(Qustentchoice);
				}
			}
			// 添加 选项
			Examitem.setChoiceList(papaerChoices);
		}
		/** 获取已经考试过的信息 */
		PbsExamaction pbsExamactionDto = new PbsExamaction();
		pbsExamactionDto.setsExaminee(UserUtils.getPartymem());
		List<PbsExamaction> pbsExamactionList = pbsExamactionService.findList(pbsExamactionDto);

		model.addAttribute("paperid", id);
		// model.addAttribute("sDepartment", sDepartment);
		// 所有题目以及答案
		model.addAttribute("itemList", pbsExampaperitemList);
		model.addAttribute("examactionList", pbsExamactionList);

		return "pbs/exam/pconline/pbsExamOnlineForm";
	}

	// 提交在线考试答案
	@ResponseBody
	@RequestMapping(value = "onlineAction")
	public String onlineAction(PbsExamaction pbsExamaction, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 信息的添加
		pbsExamaction.preInsert();
		// 获取当前的党员
		PbsPartymem PbsPartymemCur = UserUtils.getPartymem();
		// results
		String resultCols = pbsExamaction.getResults();
		String[] choiceItem = resultCols.split(";");
		// 考题数量
		int questionCount = choiceItem.length;
		// 每一道题的分数
		int questionScore = 100 / questionCount;
		// 获取所有的
		PbsQuestionObjective ObjectiveDto = new PbsQuestionObjective();
		// ObjectiveDto.setPaperid(pbsExamaction.getId());
		// 题库中选项
		List<PbsQuestionObjective> TchoiceList = pbsQuestionObjectiveService.findList(ObjectiveDto);
		// list 转map
		Map<String, PbsQuestionObjective> choiceMap = ChoiceList_Map(TchoiceList);
		// 需要插入的结果list
		List<PbsExamactionitem> pbsExamactionitems = new ArrayList<>();
		int score = 0;
		// 遍历 选项
		for (String Pchoices : choiceItem) {
			String[] items = Pchoices.split(",");
			// 选择题id
			String choiceid = items[0];
			// 如果正常的为两个结果
			String choiceres = items.length == 2 ? items[1] : "";
			PbsQuestionObjective Objective = choiceMap.get(choiceid);
			/** 生成 结果list */
			PbsExamactionitem pbsExamactionitem = new PbsExamactionitem();
			// 使之变成两个正确的答案
			String choicereComp = choiceres.replaceAll("、", "");
			String rightComp = Objective.getSAnswer().replaceAll("、", "");
			// 相同就是正确答案
			String scorcFlag = choicereComp.equalsIgnoreCase(rightComp) ? "1" : "0";
			pbsExamactionitem.setsExampaper(pbsExamaction.getsExampaper());
			pbsExamactionitem.setsExaminee(PbsPartymemCur);
			pbsExamactionitem.setsItem(Objective);
			pbsExamactionitem.setsDoanswer(choiceres);
			pbsExamactionitem.setsActionid(pbsExamaction);
			pbsExamactionitem.setSJudge(scorcFlag);
			// 结果
			pbsExamactionitem.setIScore(questionScore);
			// 插入前的赋值基础
			pbsExamactionitem.preInsert();
			// 正确的结果数据结果的累加
			if ("1".equals(scorcFlag)) {
				score += questionScore;
			}
			pbsExamactionitems.add(pbsExamactionitem);
		}
		// 插入数据
		pbsExamactionitemService.insertAll(pbsExamactionitems);
		/**
		 * 更新考试分数以及考试结束时间
		 */
		pbsExamaction.setsExaminee(PbsPartymemCur);
		pbsExamaction.setsStat("1");
		pbsExamaction.setIReport(score);
		pbsExamactionService.insert(pbsExamaction);

		return SUCCESS;
	}

	// 查看试卷信息
	@RequestMapping(value = "onlinecheck")
	public String onlinecheck(String id,String sWay, HttpServletRequest request, HttpServletResponse response, Model model) {
		// id 为试卷id
		PbsExamaction pbsExamaction = pbsExamactionService.get(id);
		model.addAttribute("pbsExamaction", pbsExamaction);
		// 获取当前的 试卷信息
		PbsExampaper pbsExampaper = pbsExampaperService.get(pbsExamaction.getsExampaper().getId());
		// 获取试卷所有选择题信息
		PbsExampaperitem pbsExampaperitemDto = new PbsExampaperitem();
		pbsExampaperitemDto.setsExampaper(pbsExampaper);
		List<PbsExampaperitem> pbsExampaperitems = pbsExampaperitemService.findList(pbsExampaperitemDto);
		// 获取题库的信息
		PbsChoiceItem itemDto = new PbsChoiceItem();
		itemDto.setPaperid(pbsExampaper.getId());
		List<PbsChoiceItem> choiceList = pbsChoiceItemService.findListByPaper(itemDto);

		// 客观题选择的结果
		PbsExamactionitem pbsExamactionitemDto = new PbsExamactionitem();
		pbsExamactionitemDto.setsActionid(pbsExamaction);
		List<PbsExamactionitem> pbsExamactionitems = pbsExamactionitemService.findList(pbsExamactionitemDto);
		// 添加选项信息
		for (PbsExampaperitem paperItem : pbsExampaperitems) {
			// 接收的对象列表
			List<PbsChoiceItem> choices = Lists.newArrayList();
			for (PbsChoiceItem TItem : choiceList) {
				// 试题的item 是客观题 ,选项的父id 是客观题
				if (paperItem.getsItem().getId().equals(TItem.getsParentid().getId())) {
					choices.add(TItem);
				}
				// 为当前的试题 添加选项
				paperItem.setChoiceList(choices);
			}
			// 考试信息 用户选择信息
			for (PbsExamactionitem actionItem : pbsExamactionitems) {
				if (actionItem.getsItem().getId().equals(paperItem.getsItem().getId())) {
					// 用户选择的内容
					paperItem.setPbsExamactionitem(actionItem);
				}
			}
		}
		// 试卷题目信息
		model.addAttribute("pbsExampaperitems", pbsExampaperitems);
		// model.addAttribute("sDepartment", sDepartment);
		//
		if(("2").equals(sWay)) {
			return "pbs/exam/pconline/pbsExamSynthesizeCheckForm";
		}else {
			return "pbs/exam/pconline/pbsExamOnlineCheckForm";
		}
	}

	// 获取指定数量的最大数list
	public static List<Integer> randomListElement(int sum, int maxlength) {
		// 最大值应该小于长度的数值
		if (maxlength >= sum) {
			maxlength = sum - 1;
		}
		List<Integer> countList = new ArrayList<Integer>();
		for (int i = 0; i < sum; i++) {
			countList.add(i);
		}
		List<Integer> randomList = new ArrayList<Integer>();
		// 随机打乱list
		Collections.shuffle(countList);
		for (int j = 0; j < maxlength; j++) {
			randomList.add(countList.get(j));
		}
		return randomList;
	}

	// List返回Map
	public static Map<String, PbsQuestionObjective> ChoiceList_Map(List<PbsQuestionObjective> list) {
		Map<String, PbsQuestionObjective> choiceMap = Maps.newHashMap();
		for (PbsQuestionObjective choice : list) {
			choiceMap.put(choice.getId(), choice);
		}
		return choiceMap;
	}

	// 返回 map<paperid,examactionid>
	public static Map<String, PbsExamaction> Examaction_Map(List<PbsExamaction> list) {
		// 返回的map
		Map<String, PbsExamaction> map = Maps.newHashMap();
		for (PbsExamaction exam : list) {
			map.put(exam.getsExampaper().getId(), exam);
		}
		return map;
	}

}
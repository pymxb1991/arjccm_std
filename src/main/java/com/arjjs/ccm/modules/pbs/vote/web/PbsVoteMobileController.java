/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.vote.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.common.utils.Collections3;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.pbs.vote.entity.PbsVoteItem;
import com.arjjs.ccm.modules.pbs.vote.entity.PbsVoteOpdetail;
import com.arjjs.ccm.modules.pbs.vote.entity.PbsVoteSubject;
import com.arjjs.ccm.modules.pbs.vote.entity.PbsVoteTopic;
import com.arjjs.ccm.modules.pbs.vote.entity.PbsVoteUser;
import com.arjjs.ccm.modules.pbs.vote.service.PbsVoteItemService;
import com.arjjs.ccm.modules.pbs.vote.service.PbsVoteOpdetailService;
import com.arjjs.ccm.modules.pbs.vote.service.PbsVoteSubjectService;
import com.arjjs.ccm.modules.pbs.vote.service.PbsVoteTopicService;
import com.arjjs.ccm.modules.pbs.vote.service.PbsVoteUserService;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;

/**
 * 投票题目选项信息Controller
 * 
 * @author lc
 * @version 2018-03-27
 */
@Controller
@RequestMapping(value = "${adminPath}/vote/pbsVoteMobile")
public class PbsVoteMobileController extends BaseController {

	@Autowired
	private PbsVoteItemService pbsVoteItemService;
	@Autowired
	private PbsVoteOpdetailService pbsVoteOpdetailService;
	@Autowired
	private PbsVoteSubjectService pbsVoteSubjectService;
	@Autowired
	private PbsVoteTopicService pbsVoteTopicService;
	@Autowired
	private PbsVoteUserService pbsVoteUserService;

	// 显示 投票list
	@RequestMapping(value = { "pbsvotelist", "" })
	public String pbsvotelist(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 投票列表显示
		PbsVoteTopic pbsVoteTopicDto = new PbsVoteTopic();
		// 0 ：为投票 1：问卷调查
		pbsVoteTopicDto.setSBelongfunc("0");
		pbsVoteTopicDto.setsStat("1");
		List<PbsVoteTopic> list = pbsVoteTopicService.findList(pbsVoteTopicDto);
		// 用户已经投票的内容
		PbsVoteUser pbsVoteUserDto = new PbsVoteUser();
		pbsVoteUserDto.setUser(UserUtils.getUser());
		List<PbsVoteUser> VoteIDs = pbsVoteUserService.findList(pbsVoteUserDto);
		// 循环 当前的 标题
		for (PbsVoteTopic topic : list) {
			// 用户id
			for (PbsVoteUser user : VoteIDs) {
				// 投票id 为用户的投票id
				if (topic.getId().equals(user.getTopid())) {
					topic.setVoteflag(true);
					break;
				}
				topic.setVoteflag(false);
			}
		}
		// 向页面 添加对象信息
		model.addAttribute("TopicList", list);
		return "Nav-activity/vote/votelist";
	}

	// 显示 调查问卷list
	@RequestMapping(value = "investigationlist")
	public String investigationlist(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 投票列表显示
		PbsVoteTopic pbsVoteTopicDto = new PbsVoteTopic();
		// 0 ：为投票 1：问卷调查
		pbsVoteTopicDto.setSBelongfunc("1");
		pbsVoteTopicDto.setsStat("1");
		List<PbsVoteTopic> list = pbsVoteTopicService.findList(pbsVoteTopicDto);
		// 用户已经投票的内容
		PbsVoteUser pbsVoteUserDto = new PbsVoteUser();
		pbsVoteUserDto.setUser(UserUtils.getUser());
		List<PbsVoteUser> VoteIDs = pbsVoteUserService.findList(pbsVoteUserDto);
		// 循环 当前的 标题
		for (PbsVoteTopic topic : list) {
			// 用户id
			for (PbsVoteUser user : VoteIDs) {
				// 投票id 为用户的投票id
				if (topic.getId().equals(user.getTopid())) {
					topic.setVoteflag(true);
					break;
				}
				topic.setVoteflag(false);
			}
		}
		// 向页面 添加对象信息
		model.addAttribute("TopicList", list);
		// 添加当前的时间
		model.addAttribute("date", new Date());
		return "Nav-activity/vote/InvestigationList";
	}

	/**
	 * 投票页面 信息 一个主题下 只有一个 题目完成 一次投票内容
	 */
	@RequestMapping(value = "voteaction")
	public String voteaction(@RequestParam(required = false) String id, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		User CurUser = UserUtils.getUser();
		// 首先获取当前的 所有题目
		PbsVoteSubject pbsVoteSubjectDto = new PbsVoteSubject();
		PbsVoteTopic pbsVoteTopicDto = new PbsVoteTopic();
		pbsVoteTopicDto.setId(id);
		pbsVoteSubjectDto.setsParentid(pbsVoteTopicDto);
		List<PbsVoteSubject> subjectlist = pbsVoteSubjectService.findList(pbsVoteSubjectDto);
		// 默认当前只有 一个 标题
		if (subjectlist.size() == 0) {
			return "Nav-activity/vote/voteaction";
		}
		// 默认获取一个 投票选项内容
		PbsVoteSubject pbsVoteSubject = subjectlist.get(0);
		PbsVoteItem pbsVoteItemDto = new PbsVoteItem();
		pbsVoteItemDto.setsParentid(pbsVoteSubject);
		List<PbsVoteItem> itemList = pbsVoteItemService.findList(pbsVoteItemDto);
		// 获取当前 主题内容
		PbsVoteTopic pbsVoteTopic = pbsVoteTopicService.get(id);
		// 获取当前主题是否有人添加
		PbsVoteUser pbsVoteUserDto = new PbsVoteUser();
		pbsVoteUserDto.setTopid(id);
		pbsVoteUserDto.setUser(CurUser);
		List<PbsVoteUser> userList = pbsVoteUserService.findList(pbsVoteUserDto);
		// 获取当前主题 所有 人数
		int SumUser = pbsVoteUserService.getsum(pbsVoteUserDto);
		// 获取用户所选的内容
		PbsVoteOpdetail pbsVoteOpdetailDto = new PbsVoteOpdetail();
		User userDto = new User();
		userDto.setId(CurUser.getId());
		pbsVoteOpdetailDto.setUser(userDto);
		pbsVoteOpdetailDto.setSSubject(pbsVoteSubject.getId());
		List<PbsVoteOpdetail> pbsVoteOpdetailList = pbsVoteOpdetailService.findList(pbsVoteOpdetailDto);
		// 向前台传入数据
		model.addAttribute("itemList", getVoteItem(itemList, SumUser));
		model.addAttribute("pbsVoteTopic", pbsVoteTopic);
		model.addAttribute("pbsVoteSubject", pbsVoteSubject);
		model.addAttribute("pbsVoteOpdetailList", pbsVoteOpdetailList);
		// 判断用户是否添加过
		if (userList.size() > 0) {
			model.addAttribute("userCheck", true);
		} else {
			model.addAttribute("userCheck", false);
		}

		Date dt1 = new Date();
		Date dt2 = pbsVoteTopic.getDtEnd();
		if (dt1.getTime() > dt2.getTime()) {
			model.addAttribute("deadlineflag", true);
		} else {
			model.addAttribute("deadlineflag", false);
		}

		return "Nav-activity/vote/voteaction";
	}

	/**
	 * 调查问卷页面 信息
	 */
	@RequestMapping(value = "investigationInfo")
	public String investigationInfo(@RequestParam(required = false) String id, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 首先获取当前的 所有题目
		PbsVoteSubject pbsVoteSubjectDto = new PbsVoteSubject();
		PbsVoteTopic pbsVoteTopicDto = new PbsVoteTopic();
		pbsVoteTopicDto.setId(id);
		pbsVoteSubjectDto.setsParentid(pbsVoteTopicDto);
		List<PbsVoteSubject> subjectlist = pbsVoteSubjectService.findList(pbsVoteSubjectDto);
		// 默认获取一个 投票选项内容
		PbsVoteItem pbsVoteItemDto = new PbsVoteItem();
		// 主题id 获取主题下所有 选内容
		pbsVoteItemDto.setTopid(new PbsVoteTopic(id));
		List<PbsVoteItem> itemList = pbsVoteItemService.findList(pbsVoteItemDto);
		// 获取当前主题是否有人添加
		PbsVoteUser pbsVoteUserDto = new PbsVoteUser();
		pbsVoteUserDto.setTopid(id);
		pbsVoteUserDto.setUser(UserUtils.getUser());
		List<PbsVoteUser> userList = pbsVoteUserService.findList(pbsVoteUserDto);
		// 获取总人数
		int Usersum = pbsVoteUserService.getsum(pbsVoteUserDto);
		/** 获取用户所选的内容，并为每一个选项 添加是否选中的标识 */
		PbsVoteOpdetail pbsVoteOpdetailDto = new PbsVoteOpdetail();
		pbsVoteOpdetailDto.setSTopic(id);
		pbsVoteOpdetailDto.setUser(UserUtils.getUser());
		pbsVoteOpdetailDto.setSTopic(id);
		List<PbsVoteOpdetail> pbsVoteOpdetailList = pbsVoteOpdetailService.findList(pbsVoteOpdetailDto);
		// 已经选中 的list 集合
		String OpdetailIDs = Collections3.extractToString(pbsVoteOpdetailList, "SItem", ",");
		//
		for (PbsVoteItem item : itemList) {
			// 如果这个 字符串包含 选择信息
			if (OpdetailIDs.contains(item.getId())) {
				item.setVoteFlag(true);
			}
		}
		// 赋予百分比
		getVoteItem(itemList, Usersum);
		/** 为所有的题目填充 所有的选项 */
		for (PbsVoteSubject subject : subjectlist) {
			List<PbsVoteItem> itemForSub = new ArrayList<>();
			// 遍历所有选项
			for (PbsVoteItem item : itemList) {
				if (subject.getId().equals(item.getsParentid().getId())) {
					// 添加所有的对象
					itemForSub.add(item);
				}
			}
			// 获取所有
			subject.setPbsVoteItemList(itemForSub);
		}
		// 获取当前 主题内容
		PbsVoteTopic pbsVoteTopic = pbsVoteTopicService.get(id);

		// 向前台传入数据
		// model.addAttribute("itemList",
		// getVoteItem(itemList,userList.size()));
		model.addAttribute("pbsVoteTopic", pbsVoteTopic);
		model.addAttribute("pbsVoteSubjectList", subjectlist);
		// 判断用户是否添加过
		if (userList.size() > 0) {
			model.addAttribute("userCheck", true);
		} else {
			model.addAttribute("userCheck", false);
		}
		Date dt1 = new Date();
		Date dt2 = pbsVoteTopic.getDtEnd();
		if (dt1.getTime() > dt2.getTime()) {
			model.addAttribute("deadlineflag", true);
		} else {
			model.addAttribute("deadlineflag", false);
		}

		return "/Nav-activity/vote/InvestigationInfo";
	}

	// 保存投票信息
	@ResponseBody
	@RequestMapping(value = "savevote")
	public String saveVote(@RequestParam(required = false) String topicid,
			@RequestParam(required = false) String subjectid, @RequestParam(required = false) String itemid,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		// 用户操作详细 录入
		PbsVoteOpdetail pbsVoteOpdetail = new PbsVoteOpdetail();
		pbsVoteOpdetail.setSItem(itemid);
		pbsVoteOpdetail.setSSubject(subjectid);
		pbsVoteOpdetail.setSTopic(topicid);
		pbsVoteOpdetail.setUser(UserUtils.getUser());
		pbsVoteOpdetail.setSIp(UserUtils.getUser().getLoginIp());
		pbsVoteOpdetailService.save(pbsVoteOpdetail);
		// 用户 主题填写标记
		PbsVoteUser pbsVoteUser = new PbsVoteUser();
		pbsVoteUser.setTopid(topicid);
		pbsVoteUser.setUser(UserUtils.getUser());
		pbsVoteUser.setSIp(UserUtils.getUser().getLoginIp());
		pbsVoteUserService.save(pbsVoteUser);

		return "yes";
	}

	// 保存调查信息
	@ResponseBody
	@RequestMapping(value = "saveinvestigation")
	public String saveinvestigation(@RequestParam(required = false) String topicid, String results, Model model) {
		List<PbsVoteOpdetail> pbsVoteOpdetailList = new ArrayList<>();
		// 检测 数据是否正常
		if (StringUtils.isBlank(results)) {
			return "false";
		}
		// 所有 list
		String[] sublist = results.split(";");
		// 循环
		for (String sub : sublist) {
			String[] result = sub.split(":");
			// 获取 题目id
			String subId = result[0];
			String[] items = result[1].split(",");
			// 正式填充 数据
			for (String itemId : items) {
				PbsVoteOpdetail pbsVoteOpdetail = new PbsVoteOpdetail();
				pbsVoteOpdetail.setSTopic(topicid);
				pbsVoteOpdetail.setSSubject(subId);
				pbsVoteOpdetail.setSItem(itemId);
				pbsVoteOpdetail.setUser(UserUtils.getUser());
				pbsVoteOpdetail.setSIp(UserUtils.getUser().getLoginIp());
				pbsVoteOpdetailList.add(pbsVoteOpdetail);
			}
		}

		// 用户操作详细 录入
		pbsVoteOpdetailService.insertAll(pbsVoteOpdetailList);
		// 用户 主题填写标记
		PbsVoteUser pbsVoteUser = new PbsVoteUser();
		pbsVoteUser.setTopid(topicid);
		pbsVoteUser.setUser(UserUtils.getUser());
		pbsVoteUser.setSIp(UserUtils.getUser().getLoginIp());
		pbsVoteUserService.save(pbsVoteUser);
		return "true";
	}

	// 计算百分比函数
	public static List<PbsVoteItem> getVoteItem(List<PbsVoteItem> list, int sum) {
		// 如果总和大于0
		if (sum > 0) {
			// 后赋值
			for (PbsVoteItem item : list) {
				String cntCent = (Float.parseFloat(StringUtils.isBlank(item.getICnt()) ? "0" : item.getICnt()) * 100
						/ sum) + "";
				// 赋予百分比值
				item.setiCntCent(cntCent);
			}
		}
		return list;
	}
}
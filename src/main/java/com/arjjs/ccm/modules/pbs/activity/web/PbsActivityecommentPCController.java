package com.arjjs.ccm.modules.pbs.activity.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.common.web.BaseController;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActinotifications;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivityecomment;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivityevaluate;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivityleave;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivityrec;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivitysignin;
import com.arjjs.ccm.modules.pbs.activity.service.PbsActinotificationsService;
import com.arjjs.ccm.modules.pbs.activity.service.PbsActivityecommentService;
import com.arjjs.ccm.modules.pbs.activity.service.PbsActivityevaluateService;
import com.arjjs.ccm.modules.pbs.activity.service.PbsActivityleaveService;
import com.arjjs.ccm.modules.pbs.activity.service.PbsActivityrecService;
import com.arjjs.ccm.modules.pbs.activity.service.PbsActivitysigninService;
import com.arjjs.ccm.modules.pbs.activity.service.PbsActivitytypeService;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
@RequestMapping("${adminPath}/work/pbsComment")
public class PbsActivityecommentPCController extends BaseController {
	@Autowired
	private PbsActivityecommentService pbsActivityecommentService;
	@Autowired 
	private PbsActivityrecService pbsActivityrecService;
	@Autowired 
	private PbsActivityleaveService pbsActivityleaveService;
	@Autowired 
	private PbsActivitysigninService pbsActivitysigninService;
	@Autowired 
	private PbsActinotificationsService pbsActinotificationsService;
	@Autowired
	private PbsActivitytypeService pbsActivitytypeService;
	@Autowired
	private PbsActivityevaluateService pbsActivityevaluateService;
	
	private static final String SFLAGEYES = "1";
	
	// 支部活动详细信息
	@RequestMapping(value = "ActivityNewsInfo")
	public String ActivityNewsInfo(String id, Model model) {
		// 当前的党员
		PbsPartymem pbsPartymemCur = UserUtils.getPartymem();
		boolean operateflag = false;
		// 获取所需要的 活动
		PbsActivityrec pbsActivityrec = pbsActivityrecService.get(id);
		String group = pbsActivitytypeService.findGroupById(pbsActivityrec.getsType().getId());
		//自定义分组信息
		model.addAttribute("group",group);
		model.addAttribute("pbsActivityrec", pbsActivityrec);
		//判断当前活动是否是当前用户发起的活动
		if((pbsActivityrec.getSMastermem().getId()).equals(pbsPartymemCur.getId())) {
			model.addAttribute("userFlag", true);
		}
		// 获取了当前的 请假数量
		PbsActivityleave pbsActivityleaveDtoA = new PbsActivityleave();
		pbsActivityleaveDtoA.setsActivityid(pbsActivityrec);
		model.addAttribute("activityflag", pbsActivityrec.getsType().getSGroup());
		pbsActivityleaveDtoA.setsBindmember(pbsPartymemCur);
		List<PbsActivityleave> pbsActivityleaveAs = pbsActivityleaveService.findList(pbsActivityleaveDtoA);
		// 获取了当前的 签到数量
		PbsActivitysignin pbsActivitysigninDtoA = new PbsActivitysignin();
		pbsActivitysigninDtoA.setsActivityid(pbsActivityrec);
		pbsActivitysigninDtoA.setsBindmember(pbsPartymemCur);
		List<PbsActivitysignin> PbsActivitysignins = pbsActivitysigninService.findList(pbsActivitysigninDtoA);
		// partInFlag 当前如果有数据 则证明已经 进行过 添加 、否则 就是未进行 操作
		model.addAttribute("partInFlag", (pbsActivityleaveAs.size() + PbsActivitysignins.size()) > 0 ? true : false);
		// 获取人数
		PbsActinotifications pbsActinotificationsDto = new PbsActinotifications();
		pbsActinotificationsDto.setsActivityid(new PbsActivityrec(id));
		// 当前所获取的 内容
		List<PbsActinotifications> pbsActinotifications = pbsActinotificationsService.findList(pbsActinotificationsDto);
		Map<String, PbsPartymem> Needmap = Maps.newConcurrentMap();
		for (PbsActinotifications item : pbsActinotifications) {
			Needmap.put(item.getsAcceptorid().getId(), item.getsAcceptorid());
		}
		// 获取当前的 活动 请假 人员
		PbsActivityleave pbsActivityleaveDto = new PbsActivityleave();
		pbsActivityleaveDto.setsActivityid(new PbsActivityrec(id));
		List<PbsActivityleave> pbsActivityleaves = pbsActivityleaveService.findList(pbsActivityleaveDto);
		Map<String, PbsPartymem> LeavesMap = Maps.newHashMap();
		List<PbsPartymem> LeavesList = Lists.newArrayList();
		for (PbsActivityleave item : pbsActivityleaves) {
			LeavesList.add(item.getsBindmember());
			LeavesMap.put(item.getsBindmember().getId(), item.getsBindmember());
			// 自己是否已经请假过
			if (pbsPartymemCur.equals(item.getsBindmember())) {
				operateflag = true;
			}
		}
		// 已经签到的
		PbsActivitysignin pbsActivitysigninDto = new PbsActivitysignin();
		pbsActivitysigninDto.setsActivityid(new PbsActivityrec(id));
		List<PbsActivitysignin> pbsActivitysignins = pbsActivitysigninService.findList(pbsActivitysigninDto);
		List<PbsPartymem> signinIdList = Lists.newArrayList();
		Map<String, PbsPartymem> signinsMap = Maps.newHashMap();
		for (PbsActivitysignin item : pbsActivitysignins) {
			signinsMap.put(item.getsSignbindmember().getId(), item.getsSignbindmember());
			signinIdList.add(item.getsSignbindmember());
			// 自己是否已经签到过
			if (pbsPartymemCur.equals(item.getsBindmember())) {
				operateflag = true;
			}
		}
		// 获取 未签到的 计划中的人
		Set<Map.Entry<String, PbsPartymem>> entries = Needmap.entrySet();
		for (Map.Entry<String, PbsPartymem> entry : entries) {
			if (signinsMap.containsKey(entry.getKey())) {
				Needmap.remove(entry.getKey());// ConcurrentModificationException
			}
			if (LeavesMap.containsKey(entry.getKey())) {
				Needmap.remove(entry.getKey());// ConcurrentModificationException
			}
		}
		Collection<PbsPartymem> valueCollection = Needmap.values();
		List<PbsPartymem> NeedList = new ArrayList<PbsPartymem>(valueCollection);
		// 已经签到
		model.addAttribute("signinIdList", signinIdList);
		// 已经请假
		model.addAttribute("LeavesList", LeavesList);
		// 未回复的
		model.addAttribute("NeedList", NeedList);
		Date dtnow = new Date();
		Date dtend = pbsActivityrec.getDtEndtime();
		Date dtstart = pbsActivityrec.getDtStarttime();
		// 当前时间 大于 开始时间 小于结束时间
		if ((dtnow.getTime() < dtstart.getTime())) {
			model.addAttribute("Dealflag", 0);
		} else if ((dtnow.getTime() > dtend.getTime())) {
			model.addAttribute("Dealflag", 2);
			model.addAttribute("valueflag", true);
		} else {
			model.addAttribute("Dealflag", 1);
			model.addAttribute("valueflag", false);
		}
		// 当前的类别为 评价
		if (("2").equals(pbsActivityrec.getsType().getSGroup())) {
			model.addAttribute("valueflag", true);
			model.addAttribute("evaluateFlag", true);
		} else {
			model.addAttribute("evaluateFlag", false);
		}
		// 自己是否参与过
		model.addAttribute("operateflag", operateflag);

		// 查询 评论数
		PbsActivityecomment pbsActivityecommentDto = new PbsActivityecomment();
		pbsActivityecommentDto.setsActivityid(pbsActivityrec);
		// 获取评论的数量
		List<PbsActivityecomment> pbsActivityecommentList = pbsActivityecommentService.findList(pbsActivityecommentDto);
		// 评论数量
		model.addAttribute("comments", pbsActivityecommentList.size());
		/** 修改查看通知的状态 */
		// 修改已查看的状态
		PbsActinotifications pbsActinotificationsCurDto = new PbsActinotifications();
		pbsActinotificationsCurDto.setsActivityid(pbsActivityrec);
		pbsActinotificationsCurDto.setsAcceptorid(pbsPartymemCur);
		// 未查看的信息状态
		pbsActinotificationsCurDto.setSStat("0");
		// 获取
		List<PbsActinotifications> pbsActinotificationMes = pbsActinotificationsService
				.findList(pbsActinotificationsCurDto);
		if (pbsActinotificationMes.size() > 0) {
			PbsActinotifications pbsActinotificationViewed = pbsActinotificationMes.get(0);
			// 1是已查看
			pbsActinotificationViewed.setSStat("1");
			pbsActinotificationsService.updatesStat(pbsActinotificationViewed);
		}
		return "/pbs/work/pbsMeetingDetail";
	}

	/**
	 * 请假
	 * 
	 * @param id
	 * @param values
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "leaveAction")
	public String leaveAction(String id, String reason, Model model) {
		// 要插入的 请假申请
		PbsActivityleave pbsActivityleave = new PbsActivityleave();
		pbsActivityleave.setsActivityid(new PbsActivityrec(id));
		pbsActivityleave.setSAllpyreason(reason);
		pbsActivityleave.setsBindmember(UserUtils.getPartymem());
		pbsActivityleave.setsOperator(UserUtils.getUser());
		pbsActivityleaveService.save(pbsActivityleave);
		return SUCCESS;
	}

	/**
	 * 签到
	 * 
	 * @param id
	 * @param values
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "signinAction")
	public String signinAction(String id, String values, Model model) {
		PbsActivitysignin pbsActivitysignin = new PbsActivitysignin();
		pbsActivitysignin.setsActivityid(new PbsActivityrec(id));
		pbsActivitysignin.setsBindmember(UserUtils.getPartymem());
		pbsActivitysignin.setsSignbindmember(UserUtils.getPartymem());//签到绑定人员
		pbsActivitysignin.setsOperator(UserUtils.getUser());
		pbsActivitysigninService.save(pbsActivitysignin);
		return SUCCESS;
	}
	// 跳转 评论
	@RequestMapping(value = "comment")
	public String courseInfo(String id, Model model) {
		// 查询全部评论
		PbsActivityecomment pbsActivityecommentDto = new PbsActivityecomment();
		pbsActivityecommentDto.setsActivityid(new PbsActivityrec(id));
		List<PbsActivityecomment> list = pbsActivityecommentService.findList(pbsActivityecommentDto);
		// 查看 全部课程评论
		model.addAttribute("commentList", list);
		model.addAttribute("id", id);
		// 查询用户的评论
		pbsActivityecommentDto.setsOperator(UserUtils.getUser());
		List<PbsActivityecomment> Userlist = pbsActivityecommentService.findList(pbsActivityecommentDto);
		model.addAttribute("userComList", Userlist);
		return "/pbs/work/pbsCommentList";
	}
	
	@ResponseBody
	@RequestMapping(value = "commentSave")
	public String commentSave(PbsActivityecomment PbsActivityecomment, Model model) {
		// 验证
		if (!beanValidator(model, PbsActivityecomment)) {
			return "false";
		}
		if (StringUtils.isBlank(PbsActivityecomment.getSContent())) {
			return "false";
		}
		// 添加 用户 id
		PbsActivityecomment.setsBindmember(UserUtils.getPartymem());
		PbsActivityecomment.setsOperator(UserUtils.getUser());
		
		// 添加评论
		pbsActivityecommentService.save(PbsActivityecomment);
		return "sucess";
	}
	
	// 我发起的活动评价表现
	@RequestMapping(value = "SendActivityValue")
	public String SendActivityValues(String id, Model model) {
		// 获取所需要的 活动
		PbsActivityrec pbsActivityrec = pbsActivityrecService.get(id);
		model.addAttribute("pbsActivityrec", pbsActivityrec);
		if ("1".equals(pbsActivityrec.getSFlag())) {
			PbsActivityevaluate pbsActivityevaluateDto = new PbsActivityevaluate();
			pbsActivityevaluateDto.setsActivityid(new PbsActivityrec(pbsActivityrec.getId()));
			List<PbsActivityevaluate> list = pbsActivityevaluateService.findList(pbsActivityevaluateDto);
			model.addAttribute("valueList", list);
			return "/pbs/work/pbsMeetingValueed";
		} else {
			// 当前未 添加评价
			PbsActinotifications pbsActinotificationsDto = new PbsActinotifications();
			pbsActinotificationsDto.setsActivityid(new PbsActivityrec(id));
			// 当前所获取的 内容
			List<PbsActinotifications> pbsActinotifications = pbsActinotificationsService
					.findList(pbsActinotificationsDto);
			List<PbsPartymem> NeedList = Lists.newArrayList();
			for (PbsActinotifications item : pbsActinotifications) {
				NeedList.add(item.getsAcceptorid());
			}
			model.addAttribute("NeedList", NeedList);
			return "/pbs/work/pbsMeetingValue";
		}
	}
	
	/**
	 * 评价
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "valueAction")
	public String valueAction(String id, String values, Model model) {
		if (StringUtils.isEmpty(values)) {
			return FAIL;
		}
		List<PbsActivityevaluate> list = new ArrayList<>();
		String[] Arrays = values.split(";");
		for (String value : Arrays) {
			if (StringUtils.isEmpty(value)) {
				continue;
			}
			String[] valueMem = value.split(",");
			if (valueMem.length > 1) {
				PbsActivityevaluate pbsActivityevaluateDto = new PbsActivityevaluate();
				pbsActivityevaluateDto.setsActivityid(new PbsActivityrec(id));
				pbsActivityevaluateDto.setsOperator(UserUtils.getUser());
				pbsActivityevaluateDto.setsBindmember(UserUtils.getPartymem());
				pbsActivityevaluateDto.setSValue(valueMem[1]);
				pbsActivityevaluateDto.setsPartmember(new PbsPartymem(valueMem[0]));
				pbsActivityevaluateDto.preInsert();
				list.add(pbsActivityevaluateDto);
			}
		}
		// 更新全部的内容
		pbsActivityevaluateService.inserAll(list);
		// 同步更新主表的 添加状态内容
		PbsActivityrec pbsActivityrec = pbsActivityrecService.get(id);
		pbsActivityrec.setSFlag(SFLAGEYES);
		pbsActivityrecService.save(pbsActivityrec);
		return SUCCESS;
	}
}

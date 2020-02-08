package com.arjjs.ccm.modules.pbs.activity.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActinotifications;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivityevaluate;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivityleave;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivityrec;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivitysignin;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivitytype;
import com.arjjs.ccm.modules.pbs.activity.service.PbsActinotificationsService;
import com.arjjs.ccm.modules.pbs.activity.service.PbsActivityevaluateService;
import com.arjjs.ccm.modules.pbs.activity.service.PbsActivityleaveService;
import com.arjjs.ccm.modules.pbs.activity.service.PbsActivityrecService;
import com.arjjs.ccm.modules.pbs.activity.service.PbsActivitysigninService;
import com.arjjs.ccm.modules.pbs.activity.service.PbsActivitytypeService;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.sys.entity.GeneralMethod;
import com.arjjs.ccm.modules.pbs.sys.service.PbsGeneralService;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
@RequestMapping("${adminPath}/work/pbsMeeting")
public class PbsActivityPCController extends BaseController {
	
	@Autowired
	private PbsActivityrecService pbsActivityrecService;
	@Autowired
	private PbsActivityevaluateService pbsActivityevaluateService;
	@Autowired 
	private PbsActivitytypeService pbsActivitytypeService;
	@Autowired
	private PbsGeneralService pbsGeneralService;
	@Autowired
	private PbsActinotificationsService pbsActinotificationsService;
	@Autowired
	private PbsActivityleaveService pbsActivityleaveService;
	@Autowired
	private PbsActivitysigninService pbsActivitysigninService;
	
	
	private static final String TABLEKEY ="pbs_activityrec";
	private static final String COLNAME ="s_title";
	
	
	@ModelAttribute
	public PbsActivityrec get(@RequestParam(required=false)String id) {
		PbsActivityrec entity =null;
		if(StringUtils.isNotBlank(id)) {
			entity = pbsActivityrecService.get(id);
		} else {
			entity = new PbsActivityrec();
		}
		return entity;
	}
	
	// 查询表格内容
	@RequestMapping({"list",""})
	public String list(HttpServletRequest request, HttpServletResponse response , PbsActivityrec pbsActivityrec, Model model) {
		// 当前是党员才能查询
		if (StringUtils.isNoneBlank(UserUtils.getPartymem().getId())) {
			Page<PbsActivityrec> page = pbsActivityrecService.findPage(new Page<PbsActivityrec>(request, response), pbsActivityrec);
			model.addAttribute("page",page);
		}
		return "/pbs/work/pbsMeetingList";
	}
	
	//查询表单中的内容
	@RequestMapping("form")
	public String form(PbsActivityrec pbsActivityrec, Model model ) {
		model.addAttribute("pbsActivityrec",pbsActivityrec);
		return "/pbs/work/pbsMeetingForm";
	}
	
	@RequestMapping("checkActivityValue")
	public String checkActivityValue(String id, Model model) {
		// 获取所需要的 活动
		PbsActivityrec pbsActivityrec = pbsActivityrecService.get(id);
		pbsActivityrec.setSFlag("1");
		model.addAttribute("pbsActivityrec", pbsActivityrec);
		PbsActivityevaluate pbsActivityevaluateDto = new PbsActivityevaluate();
		pbsActivityevaluateDto.setsActivityid(new PbsActivityrec(pbsActivityrec.getId()));
		List<PbsActivityevaluate> list = pbsActivityevaluateService.findList(pbsActivityevaluateDto);
		model.addAttribute("valueList", list);
		return "/pbs/work/pbsGradeList";
	}
	
	@RequestMapping(value = "namelist")
	public String namelist(PbsActivitytype pbsActivitytype,String group, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		pbsActivitytype.setSGroup(group);
		List<PbsActivitytype> list = pbsActivitytypeService.findList(pbsActivitytype);
		model.addAttribute("list", list);
		return "mapping/ActivitytypeList";
	}
	
	@RequestMapping(value = "save")
	public String save(PbsActivityrec pbsActivityrec, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsActivityrec)) {
			return addForm(pbsActivityrec, model);
		}
		// 判断当前的内容是否存在
		GeneralMethod generalMethod = new GeneralMethod();
		generalMethod.setTabletype(TABLEKEY);
		generalMethod.setId(pbsActivityrec.getId());
		generalMethod.setColumntype(COLNAME);
		generalMethod.setKey(pbsActivityrec.getSTitle());
		// 验证数据是否重复
		if (pbsGeneralService.checkExist(generalMethod)) {
			addMessage(model, "数据验证失败：该信息已经存在");
			return addForm(pbsActivityrec, model);
		}
		pbsActivityrec.setSMastermem(UserUtils.getPartymem());
		pbsActivityrecService.save(pbsActivityrec);
		if (pbsActivityrec.getSStat().equals("2")) {
			// 如果是补录，则把所有的参与人员改为已签到
			String pbsPartymemids = pbsActivityrec.getPbsActinotificationIds();
			String[] listIds = pbsPartymemids.split(",");
			List<PbsActivitysignin> signList = new ArrayList<PbsActivitysignin>();
			for (int i = 0; i < listIds.length; i++) {
				PbsActivitysignin pbsActivitysignin = new PbsActivitysignin();
				pbsActivitysignin.setsActivityid(pbsActivityrec);
				pbsActivitysignin.setsBindmember(new PbsPartymem(listIds[i]));
				pbsActivitysignin.setsOperator(UserUtils.getUser());
				pbsActivitysignin.preInsert();
				signList.add(pbsActivitysignin);
			}
			pbsActivityrecService.saveRecord(signList);
		}
		addMessage(redirectAttributes, "保存活动信息成功");
		return "redirect:" + Global.getAdminPath() + "/work/pbsMeeting/?repage";
	}
	
	@RequestMapping(value = "addForm")
	public String addForm(PbsActivityrec pbsActivityrec, Model model) {
		PbsActinotifications pbsActinotificationsDto = new PbsActinotifications();
		pbsActinotificationsDto.setsActivityid(pbsActivityrec);
		// 赋值 人员
		List<PbsActinotifications> lists = pbsActinotificationsService.findList(pbsActinotificationsDto);
		pbsActivityrec.setPbsActinotificationList(lists);
		model.addAttribute("pbsActivityrec", pbsActivityrec);
		return "pbs/work/pbsMeetingForm";
	}
	
	@RequestMapping(value = "SendActivitySign")
	public String SendActivitySign(String id, Model model) {
		// 获取 当前的 活动已经签到的人
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
		}

		// 已经签到的
		PbsActivitysignin pbsActivitysigninDto = new PbsActivitysignin();
		pbsActivitysigninDto.setsActivityid(new PbsActivityrec(id));
		List<PbsActivitysignin> pbsActivitysignins = pbsActivitysigninService.findList(pbsActivitysigninDto);
		List<PbsPartymem> signinIdList = Lists.newArrayList();
		Map<String, PbsPartymem> signinsMap = Maps.newHashMap();
		for (PbsActivitysignin item : pbsActivitysignins) {
			signinsMap.put(item.getsBindmember().getId(), item.getsBindmember());
			signinIdList.add(item.getsBindmember());
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

		// 获取活动
		PbsActivityrec pbsActivityrec = pbsActivityrecService.get(id);

		// 已经签到
		model.addAttribute("type", pbsActivityrec.getsType().getSGroup());
		// 已经签到
		model.addAttribute("signinIdList", signinIdList);
		// 已经请假
		model.addAttribute("LeavesList", LeavesList);
		// 未回复的
		model.addAttribute("NeedList", NeedList);

		return "/pbs/work/pbsMeetingSign";
	}
	
}

/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.carpass.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.bayonet.entity.CcmCarBayonet;
import com.arjjs.ccm.modules.ccm.bayonet.service.CcmCarBayonetService;
import com.arjjs.ccm.modules.ccm.carpass.entity.CcmCarPass;
import com.arjjs.ccm.modules.ccm.carpass.service.CcmCarPassService;
import com.arjjs.ccm.modules.ccm.lane.entity.CcmLane;
import com.arjjs.ccm.modules.ccm.lane.service.CcmLaneService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 过车查询Controller
 * @author liuyongjian
 * @version 2019-07-24
 */
@Controller
@RequestMapping(value = "${adminPath}/carpass/ccmCarPass")
public class CcmCarPassController extends BaseController {

	@Autowired
	private CcmCarPassService ccmCarPassService;

	@Autowired
	private CcmCarBayonetService ccmCarBayonetService;

	@Autowired
	private CcmLaneService ccmLaneService;

	@ModelAttribute
	public CcmCarPass get(@RequestParam(required=false) String id) {
		CcmCarPass entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmCarPassService.get(id);
		}
		if (entity == null){
			entity = new CcmCarPass();
		}
		return entity;
	}
	
	@RequiresPermissions("carpass:ccmCarPass:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmCarPass ccmCarPass, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmCarPass> page = ccmCarPassService.findPage(new Page<CcmCarPass>(request, response), ccmCarPass);
		List<CcmCarBayonet> bayonetList = ccmCarBayonetService.findList(new CcmCarBayonet());
		List<CcmLane> laneList = ccmLaneService.findList(new CcmLane());
//		List<CcmCarPass> reslist = page.getList();
		
/*		String str = "[{\n" + 
				"	\"bayonet\": \"0\",\n" + 
				"	\"brand\": \"0\",\n" + 
				"	\"carcolor\": \"0\",\n" + 
				"	\"carphotos\": \"0\",\n" + 
				"}]";
		
		
		String str1 = "[{\"bayonet\": \"0\",\"brand\": \"0\",\"carcolor\": \"0\",\"carphotos\": \"0\",\"cartype\": \"0\",\"chronology\": \"0\",\"condition\": \"0\",\"dangerous\": \"0\",\"direction\": \"0\",\"headdirection\": \"0\",\"id\": \"1\",\"illegaltype\": \"0\",\"isNewRecord\": false,\"iscall\": \"0\",\"issafety\": \"0\",\"lane\": \"0\",\"lanenumber\": \"0\",\"latitude\": \"0\",\"longitude\": \"0\",\"number\": \"0\",\"outincity\": \"0\",\"passtime\": \"\",\"pendant\": \"0\",\"plate\": \"0\",\"platecolor\": \"0\",\"platetype\": \"0\",\"speed\": \"10\",\"subbrand\": \"0\",\"sunlouver\": \"0\",\"type\": \"0\",\"yellowcar\": \"0\",\"beginPasstime\":\"\",\"endPasstime\":\"\"}]";
		List<CcmCarPass> list = JSONObject.parseArray(str,CcmCarPass.class);
		page.setList(list);
		page.setPageNo(10);
		page.setPageSize(20);
		page.setCount(list.size());*/
		model.addAttribute("page", page);
		model.addAttribute("bayonetList",bayonetList);
		model.addAttribute("laneList",laneList);
		return "ccm/carpass/ccmCarPassList";
	}

	@RequiresPermissions("carpass:ccmCarPass:view")
	@RequestMapping(value = "form")
	public String form(CcmCarPass ccmCarPass, Model model) {
		model.addAttribute("ccmCarPass", ccmCarPass);
		return "ccm/carpass/ccmCarPassForm";
	}

	@RequiresPermissions("carpass:ccmCarPass:edit")
	@RequestMapping(value = "save")
	public String save(CcmCarPass ccmCarPass, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmCarPass)){
			return form(ccmCarPass, model);
		}
		ccmCarPassService.save(ccmCarPass);
		addMessage(redirectAttributes, "保存过车信息成功");
		return "redirect:"+Global.getAdminPath()+"/carpass/ccmCarPass/?repage";
	}
	
	@RequiresPermissions("carpass:ccmCarPass:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmCarPass ccmCarPass, RedirectAttributes redirectAttributes) {
		ccmCarPassService.delete(ccmCarPass);
		addMessage(redirectAttributes, "删除过车信息成功");
		return "redirect:"+Global.getAdminPath()+"/carpass/ccmCarPass/?repage";
	}

}
/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.car.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.arjjs.ccm.modules.plm.car.entity.PlmCarUseSpend;
import com.arjjs.ccm.modules.plm.car.service.PlmCarUseSpendService;
import com.arjjs.ccm.modules.plm.statistics.entity.PlmStatisticsDict;
import com.arjjs.ccm.modules.plm.statistics.service.PlmStatisticsDictService;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.utils.DictUtils;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.EchartType;

import net.sf.json.JSONArray;

/**
 * 用车费用记录Controller
 * 
 * @author fu
 * @version 2018-07-04
 */
@Controller
@RequestMapping(value = "${adminPath}/car/plmCarUseSpend")
public class PlmCarUseSpendController extends BaseController {

	@Autowired
	private PlmCarUseSpendService plmCarUseSpendService;
	@Autowired
	private PlmStatisticsDictService plmStatisticsDictService;

	@ModelAttribute
	public PlmCarUseSpend get(@RequestParam(required = false) String id) {
		PlmCarUseSpend entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = plmCarUseSpendService.get(id);
		}
		if (entity == null) {
			entity = new PlmCarUseSpend();
		}
		return entity;
	}

	@RequiresPermissions("car:plmCarUseSpend:view")
	@RequestMapping(value = { "list", "" })
	public String list(PlmCarUseSpend plmCarUseSpend, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<PlmCarUseSpend> page = plmCarUseSpendService.findPage(new Page<PlmCarUseSpend>(request, response),
				plmCarUseSpend);
		model.addAttribute("page", page);
		return "plm/car/plmCarUseSpendList";
	}

	@RequiresPermissions("car:plmCarUseSpend:view")
	@RequestMapping(value = "form")
	public String form(PlmCarUseSpend plmCarUseSpend, Model model) {
		model.addAttribute("plmCarUseSpend", plmCarUseSpend);
		return "plm/car/plmCarUseSpendForm";
	}

	@RequiresPermissions("car:plmCarUseSpend:edit")
	@RequestMapping(value = "save")
	public String save(PlmCarUseSpend plmCarUseSpend, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmCarUseSpend)) {
			return form(plmCarUseSpend, model);
		}
		plmCarUseSpendService.save(plmCarUseSpend);
		addMessage(redirectAttributes, "保存用车费用记录成功");
		return "redirect:" + Global.getAdminPath() + "/car/plmCarUseSpend/?repage";
	}

	@RequiresPermissions("car:plmCarUseSpend:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmCarUseSpend plmCarUseSpend, RedirectAttributes redirectAttributes) {
		plmCarUseSpendService.delete(plmCarUseSpend);
		addMessage(redirectAttributes, "删除用车费用记录成功");
		return "redirect:" + Global.getAdminPath() + "/car/plmCarUseSpend/?repage";
	}

	/**
	 * 统计首页-用车费用统计(根据类型)
	 * 
	 * @param request
	 * @param response
	 * @param height
	 * @param width
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "spendByType")
	public String spendByType(HttpServletRequest request, HttpServletResponse response, String isOffice, String height, String width, String content, String sid) {
		PlmStatisticsDict PlmStatisticsDict = plmStatisticsDictService.typeAndLine(content);
		request.setAttribute("porline", PlmStatisticsDict.getLine());
		request.setAttribute("portype", PlmStatisticsDict.getType());
		Office office = new Office();
		if (StringUtils.isNotBlank(isOffice) && isOffice.equals("0")) {
			office = UserUtils.getUser().getOffice();
		}
		List<EchartType> list = plmCarUseSpendService.selectSpendByType(office);
		for (EchartType echartType : list) {
			echartType.setType(DictUtils.getDictLabel(echartType.getType(), "plm_car_use_type", ""));
		}
		JSONArray jsondata = JSONArray.fromObject(list);
		request.setAttribute("porid", sid);
		request.setAttribute("jsondata", jsondata);
		request.setAttribute("porheigh", height);
		request.setAttribute("porwidth", width);
		return "plm/statistics/car/spendByType";
	}
}
/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.traffic.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.place.entity.CcmBasePlace;
import com.arjjs.ccm.modules.ccm.place.service.CcmBasePlaceService;
import com.arjjs.ccm.modules.ccm.traffic.entity.CcmPlaceTraffic;
import com.arjjs.ccm.modules.ccm.traffic.service.CcmPlaceTrafficService;
import com.arjjs.ccm.tool.CommUtil;

/**
 * 交通出行场所Controller
 * 
 * @author ljd
 * @version 2019-04-29
 */
@Controller
@RequestMapping(value = "${adminPath}/traffic/ccmPlaceTraffic")
public class CcmPlaceTrafficController extends BaseController {

	@Autowired
	private CcmPlaceTrafficService ccmPlaceTrafficService;
	@Autowired
	private CcmBasePlaceService ccmBasePlaceService;

	@ModelAttribute
	public CcmPlaceTraffic get(@RequestParam(required = false) String id) {
		CcmPlaceTraffic entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmPlaceTrafficService.get(id);
		}
		if (entity == null) {
			entity = new CcmPlaceTraffic();
		}
		return entity;
	}

	@RequiresPermissions("traffic:ccmPlaceTraffic:view")
	@RequestMapping(value = { "list", "", "{type}" })
	public String list(CcmPlaceTraffic ccmPlaceTraffic, HttpServletRequest request, HttpServletResponse response,
			Model model, @PathVariable("type") String type) {
		Page<CcmPlaceTraffic> page = ccmPlaceTrafficService.findPage(new Page<CcmPlaceTraffic>(request, response),
				ccmPlaceTraffic);
		List<CcmPlaceTraffic> findList = ccmPlaceTrafficService.findList(ccmPlaceTraffic);
		for (CcmPlaceTraffic traffic : findList) {
			traffic.setCcmBasePlace(ccmBasePlaceService.get(traffic.getBasePlaceId()));
		}
		page.setList(findList);
		model.addAttribute("page", page);

		if (StringUtils.isNoneBlank(ccmPlaceTraffic.getType()) && "01".equals(ccmPlaceTraffic.getType())) {
			// 车站
			return "ccm/traffic/ccmPlaceTrafficList";
		} else if ((StringUtils.isNoneBlank(ccmPlaceTraffic.getType())) && ("02".equals(ccmPlaceTraffic.getType()))) {
			// 停车场
			return "ccm/traffic/ccmPlaceParkList";
		} else if (StringUtils.isNoneBlank(ccmPlaceTraffic.getType()) && ("03".equals(ccmPlaceTraffic.getType()))) {
			// 加油或加气站
			return "ccm/traffic/ccmPlaceGasStationList";
		} else {
			return "ccm/traffic/ccmPlaceTrafficList";
		}
	}

	@RequiresPermissions("traffic:ccmPlaceTraffic:view")
	@RequestMapping(value = "form")
	public String form(CcmPlaceTraffic ccmPlaceTraffic, Model model) {
		CcmBasePlace ccmBasePlace = new CcmBasePlace();
		ccmBasePlace.setId(ccmPlaceTraffic.getBasePlaceId());
		CcmBasePlace ccmBasePlace2 = ccmBasePlaceService.get(ccmBasePlace);
		ccmPlaceTraffic.setCcmBasePlace(ccmBasePlace2);
		model.addAttribute("ccmPlaceTraffic", ccmPlaceTraffic);

		if (StringUtils.isNoneBlank(ccmPlaceTraffic.getType()) && "01".equals(ccmPlaceTraffic.getType())) {
			// 车站
			return "ccm/traffic/ccmPlaceTrafficForm";
		} else if ((StringUtils.isNoneBlank(ccmPlaceTraffic.getType())) && ("02".equals(ccmPlaceTraffic.getType()))) {
			// 停车场
			return "ccm/traffic/ccmPlaceParkForm";
		} else if (StringUtils.isNoneBlank(ccmPlaceTraffic.getType()) && ("03".equals(ccmPlaceTraffic.getType()))) {
			// 加油或加气站
			return "ccm/traffic/ccmPlaceccmGasStationForm";
		} else {
			return "ccm/traffic/ccmPlaceTrafficForm";
		}
	}

	@RequiresPermissions("traffic:ccmPlaceTraffic:edit")
	@RequestMapping(value = "save/{type}")
	public void save(CcmPlaceTraffic ccmPlaceTraffic, Model model, RedirectAttributes redirectAttributes,
			HttpServletResponse response, @PathVariable("type") String type) {
		if (!beanValidator(model, ccmPlaceTraffic)) {
		}
		// 处理场所基本信息
		if (null == ccmPlaceTraffic.getBasePlaceId() || "".equals(ccmPlaceTraffic.getBasePlaceId())) {
			CcmBasePlace ccmBasePlace = ccmPlaceTraffic.getCcmBasePlace();
			String id = UUID.randomUUID().toString();
			ccmBasePlace.setId(id);
			ccmBasePlace.setIsNewRecord(true);
			ccmBasePlace.setPlaceType("ccm_place_traffic");
			ccmBasePlaceService.save(ccmBasePlace);
			ccmPlaceTraffic.setCcmBasePlace(ccmBasePlace);
			ccmPlaceTraffic.setBasePlaceId(id);
		} else {
			CcmBasePlace ccmBasePlace = ccmPlaceTraffic.getCcmBasePlace();
			ccmBasePlace.setId(ccmPlaceTraffic.getBasePlaceId());
			ccmBasePlace.setPlaceType("ccm_place_traffic");
			ccmBasePlaceService.save(ccmBasePlace);
			ccmPlaceTraffic.setCcmBasePlace(ccmBasePlace);
		}

		ccmPlaceTraffic.setType(type);
		// TODO 首先保存基础场所表数据，之后把生成id存到houseId里
		ccmPlaceTrafficService.save(ccmPlaceTraffic);
		addMessage(redirectAttributes, "保存交通出行场所成功");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if ("01".equals(type)) {
			CommUtil.openWinExpDiv(out, "保存车站成功");
		}
		if ("02".equals(type)) {
			CommUtil.openWinExpDiv(out, "保存停车站成功");
		}
		if ("03".equals(type)) {
			CommUtil.openWinExpDiv(out, "保存加油或加气站成功");
		}
	}

	@RequiresPermissions("traffic:ccmPlaceTraffic:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmPlaceTraffic ccmPlaceTraffic, RedirectAttributes redirectAttributes) {
		if (null != ccmPlaceTraffic.getBasePlaceId() && !("".equals(ccmPlaceTraffic.getBasePlaceId()))) {
			CcmBasePlace ccmBasePlace = ccmBasePlaceService.get(ccmPlaceTraffic.getBasePlaceId());
			ccmBasePlaceService.delete(ccmBasePlace);
		}
		ccmPlaceTrafficService.delete(ccmPlaceTraffic);
		addMessage(redirectAttributes, "删除交通出行场所成功");
		return "redirect:" + Global.getAdminPath() + "/traffic/ccmPlaceTraffic/" + ccmPlaceTraffic.getType()
				+ "?repage";
	}

}
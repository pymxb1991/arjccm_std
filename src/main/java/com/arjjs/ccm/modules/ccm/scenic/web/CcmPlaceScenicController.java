/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.scenic.web;

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
import com.arjjs.ccm.modules.ccm.scenic.entity.CcmPlaceScenic;
import com.arjjs.ccm.modules.ccm.scenic.service.CcmPlaceScenicService;
import com.arjjs.ccm.tool.CommUtil;

/**
 * 旅游景点Controller
 * 
 * @author ljd
 * @version 2019-04-29
 */
@Controller
@RequestMapping(value = "${adminPath}/scenic/ccmPlaceScenic")
public class CcmPlaceScenicController extends BaseController {

	@Autowired
	private CcmPlaceScenicService ccmPlaceScenicService;
	@Autowired
	private CcmBasePlaceService ccmBasePlaceService;

	@ModelAttribute
	public CcmPlaceScenic get(@RequestParam(required = false) String id) {
		CcmPlaceScenic entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmPlaceScenicService.get(id);
		}
		if (entity == null) {
			entity = new CcmPlaceScenic();
		}
		return entity;
	}

	@RequiresPermissions("scenic:ccmPlaceScenic:view")
	@RequestMapping(value = { "list", "", "{type}" })
	public String list(CcmPlaceScenic ccmPlaceScenic, HttpServletRequest request, HttpServletResponse response,
			Model model, @PathVariable("type") String type) {
		ccmPlaceScenic.setType(type);
		Page<CcmPlaceScenic> page = ccmPlaceScenicService.findPage(new Page<CcmPlaceScenic>(request, response),
				ccmPlaceScenic);
		model.addAttribute("page", page);
		List<CcmPlaceScenic> findList = ccmPlaceScenicService.findList(ccmPlaceScenic);
		for (CcmPlaceScenic scenic : findList) {
			scenic.setCcmBasePlace(ccmBasePlaceService.get(scenic.getBasePlaceId()));
		}
		page.setList(findList);
		if ("01".equals(type)) {
			return "ccm/scenic/ccmPlaceScenicList";
		}
		return "ccm/scenic/ccmPlaceScenicList";
	}

	@RequiresPermissions("scenic:ccmPlaceScenic:view")
	@RequestMapping(value = "form")
	public String form(CcmPlaceScenic ccmPlaceScenic, Model model) {
		CcmBasePlace ccmBasePlace = new CcmBasePlace();
		ccmBasePlace.setId(ccmPlaceScenic.getBasePlaceId());
		CcmBasePlace ccmBasePlace2 = ccmBasePlaceService.get(ccmBasePlace);
		ccmPlaceScenic.setCcmBasePlace(ccmBasePlace2);
		model.addAttribute("ccmPlaceScenic", ccmPlaceScenic);
		if ("01".equals(ccmPlaceScenic.getType())) {
			return "ccm/scenic/ccmPlaceScenicForm";
		}
		return "ccm/scenic/ccmPlaceScenicForm";
	}

	@RequiresPermissions("scenic:ccmPlaceScenic:edit")
	@RequestMapping(value = "save/{type}")
	public void save(CcmPlaceScenic ccmPlaceScenic, Model model, RedirectAttributes redirectAttributes,
			HttpServletResponse response, @PathVariable("type") String type) {
		if (!beanValidator(model, ccmPlaceScenic)) {
		}
		// 处理场所基本信息
		if (null == ccmPlaceScenic.getBasePlaceId() || "".equals(ccmPlaceScenic.getBasePlaceId())) {
			CcmBasePlace ccmBasePlace = ccmPlaceScenic.getCcmBasePlace();
			String id = UUID.randomUUID().toString();
			ccmBasePlace.setId(id);
			ccmBasePlace.setIsNewRecord(true);
			ccmBasePlace.setPlaceType("ccm_place_scenic");
			ccmBasePlaceService.save(ccmBasePlace);
			ccmPlaceScenic.setCcmBasePlace(ccmBasePlace);
			ccmPlaceScenic.setBasePlaceId(id);
		} else {
			CcmBasePlace ccmBasePlace = ccmPlaceScenic.getCcmBasePlace();
			ccmBasePlace.setId(ccmPlaceScenic.getBasePlaceId());
			ccmBasePlace.setPlaceType("ccm_place_scenic");
			ccmBasePlaceService.save(ccmBasePlace);
			ccmPlaceScenic.setCcmBasePlace(ccmBasePlace);
		}

		ccmPlaceScenic.setType(type);
		// TODO 首先保存基础场所表数据，之后把生成id存到houseId里
		ccmPlaceScenicService.save(ccmPlaceScenic);
		addMessage(redirectAttributes, "保存旅游场所成功");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if ("01".equals(type)) {
			CommUtil.openWinExpDiv(out, "保存风景名胜成功");
		}
	}

	@RequiresPermissions("scenic:ccmPlaceScenic:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmPlaceScenic ccmPlaceScenic, RedirectAttributes redirectAttributes) {
		if (null != ccmPlaceScenic.getBasePlaceId() && !("".equals(ccmPlaceScenic.getBasePlaceId()))) {
			CcmBasePlace ccmBasePlace = ccmBasePlaceService.get(ccmPlaceScenic.getBasePlaceId());
			ccmBasePlaceService.delete(ccmBasePlace);
		}
		ccmPlaceScenicService.delete(ccmPlaceScenic);
		addMessage(redirectAttributes, "删除旅游景点成功");
		return "redirect:" + Global.getAdminPath() + "/scenic/ccmPlaceScenic/" + ccmPlaceScenic.getType() + "?repage";
	}

}
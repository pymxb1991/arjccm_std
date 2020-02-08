/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.chemical.web;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.chemical.entity.CcmPlaceChemical;
import com.arjjs.ccm.modules.ccm.chemical.service.CcmPlaceChemicalService;
import com.arjjs.ccm.modules.ccm.place.entity.CcmBasePlace;
import com.arjjs.ccm.modules.ccm.place.service.CcmBasePlaceService;
import com.arjjs.ccm.tool.CommUtil;

/**
 * 危化品经营Controller
 * 
 * @author ljd
 * @version 2019-04-29
 */
@Controller
@RequestMapping(value = "${adminPath}/chemical/ccmPlaceChemical")
public class CcmPlaceChemicalController extends BaseController {

	@Autowired
	private CcmPlaceChemicalService ccmPlaceChemicalService;
	@Autowired
	private CcmBasePlaceService ccmBasePlaceService;

	@ModelAttribute
	public CcmPlaceChemical get(@RequestParam(required = false) String id) {
		CcmPlaceChemical entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmPlaceChemicalService.get(id);
		}
		if (entity == null) {
			entity = new CcmPlaceChemical();
		}
		return entity;
	}

	@RequiresPermissions("chemical:ccmPlaceChemical:view")
	@RequestMapping(value = { "list", "" })
	public String list(CcmPlaceChemical ccmPlaceChemical, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<CcmPlaceChemical> page = ccmPlaceChemicalService.findPage(new Page<CcmPlaceChemical>(request, response),
				ccmPlaceChemical);
		List<CcmPlaceChemical> findList = ccmPlaceChemicalService.findList(ccmPlaceChemical);
		for (CcmPlaceChemical chemical : findList) {
			chemical.setCcmBasePlace(ccmBasePlaceService.get(chemical.getBasePlaceId()));
		}
		page.setList(findList);
		model.addAttribute("page", page);
		return "ccm/chemical/ccmPlaceChemicalList";
	}

	@RequiresPermissions("chemical:ccmPlaceChemical:view")
	@RequestMapping(value = "form")
	public String form(CcmPlaceChemical ccmPlaceChemical, Model model) {
		CcmBasePlace ccmBasePlace = new CcmBasePlace();
		ccmBasePlace.setId(ccmPlaceChemical.getBasePlaceId());
		CcmBasePlace ccmBasePlace2 = ccmBasePlaceService.get(ccmBasePlace);
		ccmPlaceChemical.setCcmBasePlace(ccmBasePlace2);
		model.addAttribute("ccmPlaceChemical", ccmPlaceChemical);
		return "ccm/chemical/ccmPlaceChemicalForm";
	}

	@RequiresPermissions("chemical:ccmPlaceChemical:edit")
	@RequestMapping(value = "save")
	public void save(CcmPlaceChemical ccmPlaceChemical, Model model, RedirectAttributes redirectAttributes,
			HttpServletResponse response) {
		if (!beanValidator(model, ccmPlaceChemical)) {
		}
		// 处理场所基本信息
		if (null == ccmPlaceChemical.getBasePlaceId() || "".equals(ccmPlaceChemical.getBasePlaceId())) {
			CcmBasePlace ccmBasePlace = ccmPlaceChemical.getCcmBasePlace();
			String id = UUID.randomUUID().toString();
			ccmBasePlace.setId(id);
			ccmBasePlace.setIsNewRecord(true);
			ccmBasePlace.setPlaceType("ccm_place_chemical");
			ccmBasePlaceService.save(ccmBasePlace);
			ccmPlaceChemical.setCcmBasePlace(ccmBasePlace);
			ccmPlaceChemical.setBasePlaceId(id);
		} else {
			CcmBasePlace ccmBasePlace = ccmPlaceChemical.getCcmBasePlace();
			ccmBasePlace.setId(ccmPlaceChemical.getBasePlaceId());
			ccmBasePlace.setPlaceType("ccm_place_chemical");
			ccmBasePlaceService.save(ccmBasePlace);
			ccmPlaceChemical.setCcmBasePlace(ccmBasePlace);
		}

		// 公园和体育场新增
		ccmPlaceChemical.setType("01");
		ccmPlaceChemicalService.save(ccmPlaceChemical);
		addMessage(redirectAttributes, "保存危化品经营场所成功");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CommUtil.openWinExpDiv(out, "保存危化品经营场所成功");
	}

	@RequiresPermissions("chemical:ccmPlaceChemical:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmPlaceChemical ccmPlaceChemical, RedirectAttributes redirectAttributes) {
		ccmPlaceChemicalService.delete(ccmPlaceChemical);
		if (null != ccmPlaceChemical.getBasePlaceId() && !("".equals(ccmPlaceChemical.getBasePlaceId()))) {
			CcmBasePlace ccmBasePlace = ccmBasePlaceService.get(ccmPlaceChemical.getBasePlaceId());
			ccmBasePlaceService.delete(ccmBasePlace);
		}
		addMessage(redirectAttributes, "删除危化品经营成功");
		return "redirect:" + Global.getAdminPath() + "/chemical/ccmPlaceChemical/?repage";
	}

}
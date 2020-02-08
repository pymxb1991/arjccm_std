/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.financial.web;

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
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.financial.entity.CcmPlaceFinancial;
import com.arjjs.ccm.modules.ccm.financial.service.CcmPlaceFinancialService;
import com.arjjs.ccm.modules.ccm.place.entity.CcmBasePlace;
import com.arjjs.ccm.modules.ccm.place.service.CcmBasePlaceService;
import com.arjjs.ccm.tool.CommUtil;

/**
 * 金融机构Controller
 * 
 * @author lgh
 * @version 2019-04-29
 */
@Controller
@RequestMapping(value = "${adminPath}/financial/ccmPlaceFinancial")
public class CcmPlaceFinancialController extends BaseController {

	@Autowired
	private CcmPlaceFinancialService ccmPlaceFinancialService;
	@Autowired
	private CcmBasePlaceService ccmBasePlaceService;

	@ModelAttribute
	public CcmPlaceFinancial get(@RequestParam(required = false) String id) {
		CcmPlaceFinancial entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmPlaceFinancialService.get(id);
		}
		if (entity == null) {
			entity = new CcmPlaceFinancial();
		}
		return entity;
	}

	@RequiresPermissions("financial:ccmPlaceFinancial:view")
	@RequestMapping(value = { "list", "" })
	public String list(CcmPlaceFinancial ccmPlaceFinancial, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<CcmPlaceFinancial> page = ccmPlaceFinancialService.findPage(new Page<CcmPlaceFinancial>(request, response),
				ccmPlaceFinancial);
		List<CcmPlaceFinancial> findList = ccmPlaceFinancialService.findList(ccmPlaceFinancial);
		for (CcmPlaceFinancial financial : findList) {
			financial.setCcmBasePlace(ccmBasePlaceService.get(financial.getBasePlaceId()));
		}
		page.setList(findList);
		model.addAttribute("page", page);
		return "ccm/financial/ccmPlaceFinancialList";
	}

	@RequiresPermissions("financial:ccmPlaceFinancial:view")
	@RequestMapping(value = "form")
	public String form(CcmPlaceFinancial ccmPlaceFinancial, Model model) {

		CcmBasePlace ccmBasePlace = ccmBasePlaceService.get(ccmPlaceFinancial.getBasePlaceId());
		ccmPlaceFinancial.setCcmBasePlace(ccmBasePlace);
		model.addAttribute("ccmPlaceFinancial", ccmPlaceFinancial);
		return "ccm/financial/ccmPlaceFinancialForm";
	}

	@RequiresPermissions("financial:ccmPlaceFinancial:edit")
	@RequestMapping(value = "save")
	public void save(CcmPlaceFinancial ccmPlaceFinancial, Model model, RedirectAttributes redirectAttributes,
			HttpServletResponse response) {
		if (!beanValidator(model, ccmPlaceFinancial)) {
			// return form(ccmPlaceFinancial, model);
		}

		// 处理场所基本信息
		if (null == ccmPlaceFinancial.getBasePlaceId() || "".equals(ccmPlaceFinancial.getBasePlaceId())) {
			CcmBasePlace ccmBasePlace = ccmPlaceFinancial.getCcmBasePlace();
			String id = UUID.randomUUID().toString();
			ccmBasePlace.setId(id);
			ccmBasePlace.setIsNewRecord(true);
			ccmBasePlace.setPlaceType("ccm_place_financial");
			ccmBasePlaceService.save(ccmBasePlace);
			ccmPlaceFinancial.setCcmBasePlace(ccmBasePlace);
			ccmPlaceFinancial.setBasePlaceId(id);
		} else {
			CcmBasePlace ccmBasePlace = ccmPlaceFinancial.getCcmBasePlace();
			ccmBasePlace.setId(ccmPlaceFinancial.getBasePlaceId());
			ccmBasePlace.setPlaceType("ccm_place_financial");
			ccmBasePlaceService.save(ccmBasePlace);
			ccmPlaceFinancial.setCcmBasePlace(ccmBasePlace);
		}

		ccmPlaceFinancialService.save(ccmPlaceFinancial);

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CommUtil.openWinExpDiv(out, "保存银行场所成功");
	}

	@RequiresPermissions("financial:ccmPlaceFinancial:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmPlaceFinancial ccmPlaceFinancial, RedirectAttributes redirectAttributes) {
		if (null != ccmPlaceFinancial.getBasePlaceId() && !("".equals(ccmPlaceFinancial.getBasePlaceId()))) {
			CcmBasePlace ccmBasePlace = ccmBasePlaceService.get(ccmPlaceFinancial.getBasePlaceId());
			ccmBasePlaceService.delete(ccmBasePlace);
		}
		ccmPlaceFinancialService.delete(ccmPlaceFinancial);
		addMessage(redirectAttributes, "删除金融机构场所成功");
		return "redirect:" + Global.getAdminPath() + "/financial/ccmPlaceFinancial/?repage";
	}

}
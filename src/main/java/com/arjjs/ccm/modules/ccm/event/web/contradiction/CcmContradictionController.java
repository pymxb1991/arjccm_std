/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.web.contradiction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventAmbi;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventIncident;
import com.arjjs.ccm.modules.ccm.event.entity.preview.CcmEventIncidentPreview;
import com.arjjs.ccm.modules.ccm.event.entity.preview.CcmEventIncidentSimilarty;
import com.arjjs.ccm.modules.ccm.event.service.CcmEventAmbiService;
import com.arjjs.ccm.modules.ccm.event.service.CcmEventIncidentService;
import com.arjjs.ccm.modules.ccm.event.service.preview.CcmEventIncidentPreviewService;
import com.arjjs.ccm.modules.ccm.sys.entity.SysConfig;
import com.arjjs.ccm.modules.ccm.sys.service.SysConfigService;
import com.arjjs.ccm.modules.sys.entity.Area;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.CommUtil;
import com.arjjs.ccm.tool.SimilarityUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.arjjs.ccm.common.utils.StringUtils;

/**
 * 矛盾纠纷排查化解Controller
 * @author wwh
 * @version 2018-01-30
 */
@Controller
@RequestMapping(value = "${adminPath}/contradiction/ccmContradiction")
public class CcmContradictionController extends BaseController {

	@Autowired
	private CcmEventIncidentPreviewService ccmEventIncidentPreviewService;
	@Autowired
	private CcmEventIncidentService ccmEventIncidentService;
	@Autowired
	private SysConfigService sysConfigService;
	@Autowired
	private CcmEventAmbiService ccmEventAmbiService;

	@ModelAttribute
	public CcmEventIncidentPreview get(@RequestParam(required = false) String id) {
		CcmEventIncidentPreview entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmEventIncidentPreviewService.get(id);
		}
		if (entity == null) {
			entity = new CcmEventIncidentPreview();
		}
		return entity;
	}

	@RequiresPermissions("contradiction:ccmContradiction:view")
	@RequestMapping(value = { "{reportType}" })
	public String list(CcmEventIncidentPreview ccmEventIncidentPreview, HttpServletRequest request,
			HttpServletResponse response, Model model, @PathVariable("reportType") String reportType,
			@RequestParam(required = false) String status) {
		ccmEventIncidentPreview.setCreateBy(UserUtils.getUser());
		ccmEventIncidentPreview.setStatus(status);
		Page<CcmEventIncidentPreview> page = ccmEventIncidentPreviewService
				.findPage(new Page<CcmEventIncidentPreview>(request, response), ccmEventIncidentPreview);
		model.addAttribute("page", page);
		// App上报页面
		
		// 网站预处理
		if ("4".equals(reportType)) {
			return "ccm/event/contradiction/ccmContradictionList";
		}
		
		return "ccm/event/contradiction/ccmContradictionList";
	}

	@RequiresPermissions("contradiction:ccmContradiction:view")
	@RequestMapping(value = "form/{reportType}")
	public String form(CcmEventIncidentPreview ccmEventIncidentPreview, Model model,
			@PathVariable("reportType") String reportType) {
		model.addAttribute("ccmEventIncidentPreview", ccmEventIncidentPreview);
		
		if ("4".equals(reportType)) {
			return "ccm/event/contradiction/ccmContradictionForm";
		}
		
		return "ccm/event/contradiction/ccmContradictionForm";
	}

	@RequiresPermissions("contradiction:ccmContradiction:edit")
	@RequestMapping(value = "save/{reportType}")
	public void save(CcmEventIncidentPreview ccmEventIncidentPreview, HttpServletResponse response, Model model,
			RedirectAttributes redirectAttributes, @PathVariable("reportType") String reportType) {
		ccmEventIncidentPreview.setReportType(reportType);

		if (!beanValidator(model, ccmEventIncidentPreview)) {

		}

		// 判断是添加还是修改
		if (StringUtils.isEmpty(ccmEventIncidentPreview.getId())) { // 添加
			String id = UUID.randomUUID().toString();

			// 判断预处理系统是否开启
			SysConfig sysConfig = sysConfigService.get("preview_system_config");
			if (sysConfig.getParamInt() == 1) { // 开启

				// 判断事件类型是否是矛盾纠纷
				if (!"99".equals(ccmEventIncidentPreview.getEventSort())) { // 是
					CcmEventAmbi ccmEventAmbi = new CcmEventAmbi();
					if (StringUtils.isNotEmpty(ccmEventIncidentPreview.getCaseName())) {
						ccmEventAmbi.setName(ccmEventIncidentPreview.getCaseName());
					}
					if (null != ccmEventIncidentPreview.getHappenDate()) {
						ccmEventAmbi.setSendDate(ccmEventIncidentPreview.getHappenDate());
					}
					if (StringUtils.isNoneEmpty(ccmEventIncidentPreview.getCaseCondition())) {
						ccmEventAmbi.setEventSket(ccmEventIncidentPreview.getCaseCondition());
					}
					if (StringUtils.isNoneEmpty(ccmEventIncidentPreview.getCasePlace())) {
						Area area = new Area();
						area.setId(ccmEventIncidentPreview.getCasePlace());
						ccmEventAmbi.setArea(area);
					}
					if (StringUtils.isNotEmpty(ccmEventIncidentPreview.getHappenPlace())) {
						ccmEventAmbi.setSendAdd(ccmEventIncidentPreview.getHappenPlace());
					}
					if (StringUtils.isNotEmpty(ccmEventIncidentPreview.getCaseScope())) {
						ccmEventAmbi.setEventScale(ccmEventIncidentPreview.getCaseScope());
					}
					if (StringUtils.isNotEmpty(ccmEventIncidentPreview.getEventSort())) {
						ccmEventAmbi.setEventType(ccmEventIncidentPreview.getEventSort());
					}
					if (StringUtils.isNotEmpty(ccmEventIncidentPreview.getFile1())) {
						ccmEventAmbi.setFile(ccmEventIncidentPreview.getFile1());
					}
					ccmEventAmbi.setStatus("01");
					ccmEventAmbi.setCaseId(id);
					ccmEventAmbi.setId(UUID.randomUUID().toString());
					ccmEventAmbi.setIsNewRecord(true);
					ccmEventAmbiService.save(ccmEventAmbi);
				}

				ccmEventIncidentPreview.setStatus("01");
				ccmEventIncidentPreview.setId(id);
				ccmEventIncidentPreview.setIsNewRecord(true);
				ccmEventIncidentPreviewService.save(ccmEventIncidentPreview);
				PrintWriter out = null;
				try {
					out = response.getWriter();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				if ("4".equals(reportType)) {
					CommUtil.openWinExpDiv(out, "保存上报事件成功");
				}
				
			} else { // 关闭
				CcmEventIncident ccmEventIncident = new CcmEventIncident();
				try {
					PropertyUtils.copyProperties(ccmEventIncident, ccmEventIncidentPreview);
				} catch (Exception e) {
					e.printStackTrace();
				}
				ccmEventIncident.setId(id);
				ccmEventIncidentService.save(ccmEventIncident,UserUtils.getUser());
				PrintWriter out = null;
				try {
					out = response.getWriter();
				} catch (IOException e) {
					e.printStackTrace();
				}
				CommUtil.openWinExpDiv(out, "该事件已成功记录至事件管理系统");
			}
		} else { // 修改
			ccmEventIncidentPreviewService.save(ccmEventIncidentPreview);
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if ("4".equals(reportType)) {
				CommUtil.openWinExpDiv(out, "保存上报事件成功");
			}
			
		}

	}

	@RequiresPermissions("contradiction:ccmContradiction:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmEventIncidentPreview ccmEventIncidentPreview, RedirectAttributes redirectAttributes) {
		ccmEventIncidentPreviewService.delete(ccmEventIncidentPreview);
		addMessage(redirectAttributes, "删除预处理事件成功");
		return "redirect:" + Global.getAdminPath() + "/contradiction/ccmContradiction/"
				+ ccmEventIncidentPreview.getReportType() + "?repage";
	}


}
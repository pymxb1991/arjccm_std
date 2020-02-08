/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.score.web;

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
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.kpi.scheme.entity.KpiScheme;
import com.arjjs.ccm.modules.kpi.scheme.entity.KpiSchemeKpi;
import com.arjjs.ccm.modules.kpi.scheme.service.KpiSchemeKpiService;
import com.arjjs.ccm.modules.kpi.scheme.service.KpiSchemeService;
import com.arjjs.ccm.modules.kpi.score.entity.KpiFinalScore;
import com.arjjs.ccm.modules.kpi.score.entity.KpiSchemeScore;
import com.arjjs.ccm.modules.kpi.score.service.KpiSchemeScoreService;
import com.arjjs.ccm.modules.sys.utils.UserUtils;

/**
 * 绩效客观KPI得分Controller
 * @author pengjianqiang
 * @version 2018-04-11
 */
@Controller
@RequestMapping(value = "${adminPath}/score/kpiSchemeScore")
public class KpiSchemeScoreController extends BaseController {

	@Autowired
	private KpiSchemeScoreService kpiSchemeScoreService;
	
	@Autowired
	private KpiSchemeService kpiSchemeService;

	@Autowired
	private KpiSchemeKpiService kpiSchemeKpiService;
	
	@ModelAttribute
	public KpiSchemeScore get(@RequestParam(required=false) String id) {
		KpiSchemeScore entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = kpiSchemeScoreService.get(id);
		}
		if (entity == null){
			entity = new KpiSchemeScore();
		}
		return entity;
	}
	
	@RequiresPermissions("score:kpiSchemeScore:view")
	@RequestMapping(value = {"list", ""})
	public String list(KpiScheme kpiScheme, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<KpiScheme> page = kpiSchemeService.findPage(new Page<KpiScheme>(request, response), kpiScheme); 
		model.addAttribute("page", page);
		return "kpi/score/kpiSchemeScoreList";
	}


	@RequiresPermissions("score:kpiSchemeScore:view")
	@RequestMapping(value = "form")
	public String form(KpiScheme kpiScheme, Model model) {

		// 根据编号查询方案 生成表头
		String id = kpiScheme.getId();
		kpiScheme = kpiSchemeService.get(id);

		// 根据方案表id中找kpi， 生成表头用
		KpiSchemeKpi kpiSchemeKpi = new KpiSchemeKpi();
		kpiSchemeKpi.setSchemeId(id);
		kpiSchemeKpi.setType("01");//客观KPI项
		List<KpiSchemeKpi> kpiList = kpiSchemeKpiService.findList(kpiSchemeKpi);
		
		//根据方案查询方案中考核对象的信息
		List<KpiFinalScore> schemeUserLst = kpiSchemeScoreService.getSchemeUserBySchemeID(kpiScheme);
		//循环获取每个对象绩效得分
		for (KpiFinalScore schemeUser : schemeUserLst) {
			KpiSchemeScore kpiSchemeScore = new KpiSchemeScore();
			kpiSchemeScore.setUserId(schemeUser.getUser());
			kpiSchemeScore.setSchemeId(id);
			List<KpiSchemeScore> lstScores = kpiSchemeScoreService.getSchemeScore(kpiSchemeScore);
			schemeUser.setKpiScoreList(lstScores);
		}
		
		model.addAttribute("kpirow", kpiList);
		model.addAttribute("lstScore", schemeUserLst);
		model.addAttribute("kpiScheme", kpiScheme);
		return "kpi/score/kpiSchemeScoreForm";
	}

	@RequiresPermissions("score:kpiSchemeScore:edit")
	@RequestMapping(value = "save")
	public String save(KpiSchemeScore kpiSchemeScore, Model model, RedirectAttributes redirectAttributes) {
		//通过kpiSchemeScore中的schemeId、userId、kpiId，去得分表中查询，若是存在数据，则更新得分值，若是没有数据则新增数据。
		List<KpiSchemeScore> lstScores = kpiSchemeScoreService.findList(kpiSchemeScore);
		if (lstScores != null && lstScores.size() > 0) {
			kpiSchemeScore.setId(lstScores.get(0).getId());
			kpiSchemeScore.setScorerId(lstScores.get(0).getScorerId());
		} else {
			kpiSchemeScore.setScorerId(UserUtils.getUser());
		}
		
		kpiSchemeScoreService.save(kpiSchemeScore);
		
		addMessage(redirectAttributes, "保存绩效KPI得分成功");
		return "redirect:"+Global.getAdminPath()+"/score/kpiSchemeScore/?repage";
	}
	
	@RequiresPermissions("score:kpiSchemeScore:edit")
	@RequestMapping(value = "delete")
	public String delete(KpiSchemeScore kpiSchemeScore, RedirectAttributes redirectAttributes) {
		kpiSchemeScoreService.delete(kpiSchemeScore);
		addMessage(redirectAttributes, "删除绩效KPI得分成功");
		return "redirect:"+Global.getAdminPath()+"/score/kpiSchemeScore/?repage";
	}

}
/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.score.web;

import java.math.BigDecimal;
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
import com.arjjs.ccm.modules.kpi.scheme.entity.KpiSchemeKpi;
import com.arjjs.ccm.modules.kpi.scheme.entity.KpiSchemeSubjectivity;
import com.arjjs.ccm.modules.kpi.scheme.service.KpiSchemeKpiService;
import com.arjjs.ccm.modules.kpi.scheme.service.KpiSchemeService;
import com.arjjs.ccm.modules.kpi.scheme.service.KpiSchemeSubjectivityService;
import com.arjjs.ccm.modules.kpi.score.entity.KpiSchemeScore;
import com.arjjs.ccm.modules.kpi.score.service.KpiSchemeScoreService;
import com.arjjs.ccm.modules.sys.utils.UserUtils;

/**
 * 绩效主观KPI得分Controller
 * @author pengjianqiang
 * @version 2018-04-13
 */
@Controller
@RequestMapping(value = "${adminPath}/score/kpiSchemeScoreSubjective")
public class KpiSchemeScoreSubjectiveController extends BaseController {

	@Autowired
	private KpiSchemeScoreService kpiSchemeScoreService;
	
	@Autowired
	private KpiSchemeService kpiSchemeService;

	@Autowired
	private KpiSchemeKpiService kpiSchemeKpiService;

	@Autowired
	private KpiSchemeSubjectivityService kpiSchemeSubjectivityService;
	
	
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
	
	@RequiresPermissions("score:kpiSchemeScoreSubjective:view")
	@RequestMapping(value = {"list", ""})
	public String list(KpiSchemeKpi kpiSchemeKpi, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		Page<KpiSchemeKpi> page = kpiSchemeKpiService.findPageSubjective(new Page<KpiSchemeKpi>(request, response), kpiSchemeKpi); 
		model.addAttribute("page", page);
		return "kpi/score/kpiSchemeScoreSubjectiveList";
	}


	@RequiresPermissions("score:kpiSchemeScoreSubjective:view")
	@RequestMapping(value = "form")
	public String form(KpiSchemeKpi kpiSchemeKpi, Model model) {
		kpiSchemeKpi = kpiSchemeKpiService.getAll(kpiSchemeKpi);//查找状态
		//根据KPI和当前用户，获取对应的评价对象及对应主观评分（若是有评分）
		KpiSchemeSubjectivity kpiSchemeSubjectivity = new KpiSchemeSubjectivity();
		kpiSchemeSubjectivity.setKpiId(kpiSchemeKpi.getId());
		kpiSchemeSubjectivity.setScorerId(UserUtils.getUser());
		List<KpiSchemeSubjectivity> subjectiveUserLst = kpiSchemeSubjectivityService.findListWithScore(kpiSchemeSubjectivity);
		
		model.addAttribute("lstScore", subjectiveUserLst);
		model.addAttribute("kpiSchemeKpi", kpiSchemeKpi);
		return "kpi/score/kpiSchemeScoreSubjectiveForm";
	}

	@RequiresPermissions("score:kpiSchemeScoreSubjective:edit")
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
		//根据KPI，评分对象和当前用户，获取对应的主观评分规则，根据规则计算该条评价的实际得分
		KpiSchemeSubjectivity kpiSchemeSubjectivity = new KpiSchemeSubjectivity();
		kpiSchemeSubjectivity.setKpiId(kpiSchemeScore.getKpiId());
		kpiSchemeSubjectivity.setUserId(kpiSchemeScore.getUserId());
		kpiSchemeSubjectivity.setScorerId(UserUtils.getUser());
		List<KpiSchemeSubjectivity> subjectiveUserLst = kpiSchemeSubjectivityService.findList(kpiSchemeSubjectivity);
		
		KpiSchemeKpi kpiSchemeKpi = kpiSchemeKpiService.get(kpiSchemeScore.getKpiId());
		
		if (subjectiveUserLst != null && subjectiveUserLst.size() > 0) {
			Double scoreRemark = Double.parseDouble(kpiSchemeScore.getRemarks());
			Double weight = subjectiveUserLst.get(0).getWeight() ;
			Double kpiScore = kpiSchemeKpi.getScore();
			Double score = scoreRemark * weight * kpiScore / 100;
			BigDecimal b = new BigDecimal(score);
			Double score2 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			kpiSchemeScore.setScore(score2);
		}
		
		kpiSchemeScoreService.save(kpiSchemeScore);
		
		addMessage(redirectAttributes, "保存绩效KPI得分成功");
		return "redirect:"+Global.getAdminPath()+"/score/kpiSchemeScoreSubjective/?repage";
	}
	
	@RequiresPermissions("score:kpiSchemeScoreSubjective:edit")
	@RequestMapping(value = "delete")
	public String delete(KpiSchemeScore kpiSchemeScore, RedirectAttributes redirectAttributes) {
		kpiSchemeScoreService.delete(kpiSchemeScore);
		addMessage(redirectAttributes, "删除绩效KPI得分成功");
		return "redirect:"+Global.getAdminPath()+"/score/kpiSchemeScoreSubjective/?repage";
	}

}
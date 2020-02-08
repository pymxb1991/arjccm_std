/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.special.web;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringEscapeUtils;
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
import com.arjjs.ccm.modules.ccm.sys.entity.SysDict;
import com.arjjs.ccm.modules.kpi.score.entity.DateRange;
import com.arjjs.ccm.modules.kpi.special.entity.KpiSpecialScore;
import com.arjjs.ccm.modules.kpi.special.service.KpiSpecialScoreService;
import com.arjjs.ccm.modules.sys.entity.Dict;
import com.arjjs.ccm.modules.sys.service.DictService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 考核细则Controller
 * @author yiqingxuan
 * @version 2019-07-12
 */
@Controller
@RequestMapping(value = "${adminPath}/special/kpiSpecialScore")
public class KpiSpecialScoreController extends BaseController {

	@Autowired
	private KpiSpecialScoreService kpiSpecialScoreService;
	@Autowired
	private DictService dictService;
	
	@ModelAttribute
	public KpiSpecialScore get(@RequestParam(required=false) String id) {
		KpiSpecialScore entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = kpiSpecialScoreService.get(id);
		}
		if (entity == null){
			entity = new KpiSpecialScore();
		}
		return entity;
	}
	
	@RequiresPermissions("special:kpiSpecialScore:view")
	@RequestMapping(value = {"list", ""})
	public String list(KpiSpecialScore kpiSpecialScore, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<KpiSpecialScore> page = kpiSpecialScoreService.findPage(new Page<KpiSpecialScore>(request, response), kpiSpecialScore); 
		model.addAttribute("page", page);
		return "kpi/special/kpiSpecialScoreList";
	}

	@RequiresPermissions("special:kpiSpecialScore:view")
	@RequestMapping(value = "form")
	public String form(KpiSpecialScore kpiSpecialScore, Model model) {
		model.addAttribute("kpiSpecialScore", kpiSpecialScore);
		return "kpi/special/kpiSpecialScoreForm";
	}

	@RequiresPermissions("special:kpiSpecialScore:edit")
	@RequestMapping(value = "save")
	public String save(KpiSpecialScore kpiSpecialScore, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, kpiSpecialScore)){
			return form(kpiSpecialScore, model);
		}
		kpiSpecialScoreService.save(kpiSpecialScore);
		addMessage(redirectAttributes, "保存专项考核成功");
		return "redirect:"+Global.getAdminPath()+"/special/kpiSpecialScore/?repage";
	}
	
	@RequiresPermissions("special:kpiSpecialScore:edit")
	@RequestMapping(value = "delete")
	public String delete(KpiSpecialScore kpiSpecialScore, RedirectAttributes redirectAttributes) {
		kpiSpecialScoreService.delete(kpiSpecialScore);
		addMessage(redirectAttributes, "删除专项考核成功");
		return "redirect:"+Global.getAdminPath()+"/special/kpiSpecialScore/?repage";
	}

	
	@RequiresPermissions("special:kpiSpecialScore:edit")
	@RequestMapping(value = "saveList")
	public String saveList(String param, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		if(param!=null) {
			String newJson = StringEscapeUtils.unescapeHtml4(param);
			param = java.net.URLDecoder.decode(newJson, "UTF-8");
			JSONArray arr = JSONArray.fromObject(param);
			for(int i=0;i<arr.size();i++) {
				JSONObject object = (JSONObject) arr.get(i);
				KpiSpecialScore kpiSpecialScore = new KpiSpecialScore();
				kpiSpecialScore.setId((String) object.get("id"));
				kpiSpecialScore.setWeights(object.getString("weights"));
				kpiSpecialScore.setRemarks(object.getString("remarks"));
				kpiSpecialScoreService.save(kpiSpecialScore);
			}
		}
		return "redirect:"+Global.getAdminPath()+"/special/kpiSpecialScore/?repage";
	}
	
	
	@RequiresPermissions("special:kpiSpecialScore:view")
	@RequestMapping(value = "getLeaderBoard")
	public String getLeaderBoard(KpiSpecialScore kpiSpecialScore,String year,Model model) {
		try {
			String month = "";
			Date backYear = null;
			Dict dict = new Dict();
			dict.setType("kpi_score_quarter");
			List<Dict> list = dictService.findList(dict);
			//根据查询类型进行判断
			if(StringUtils.isNotEmpty(kpiSpecialScore.getType())) {
				if("02".equals(kpiSpecialScore.getType())) {                    //月度查询
					//判断是否选择月份，没有选择默认查询上个月份的数据
					if(kpiSpecialScore.getMonth()!=null) {
						DateRange Month = getMonth(kpiSpecialScore.getMonth());
						kpiSpecialScore.setBeginDate(Month.getStart());
						kpiSpecialScore.setEndDate(Month.getEnd());
					}else {
						DateRange lastMonth = getLastMonth();
						kpiSpecialScore.setBeginDate(lastMonth.getStart());
						kpiSpecialScore.setEndDate(lastMonth.getEnd());
					}
					//给月份赋值回显
					kpiSpecialScore.setMonth(kpiSpecialScore.getBeginDate());
				}else if("04".equals(kpiSpecialScore.getType())) {               //年度查询
					//判断是否选择年份，没有选择默认查询上个年度的数据
					if(StringUtils.isNotEmpty(year)) {
						DateRange Year = getYear(year);
						kpiSpecialScore.setBeginDate(Year.getStart());
						kpiSpecialScore.setEndDate(Year.getEnd());
					}else {
						DateRange lastYear = getLastYear();
						kpiSpecialScore.setBeginDate(lastYear.getStart());
						kpiSpecialScore.setEndDate(lastYear.getEnd());
					}
					//给年份赋值回显
					backYear = kpiSpecialScore.getBeginDate();
				}else{                                                          //季度查询
					//判断非空情况，1.年份为空时，默认查询当前年份选中季度的数据；2.季度为空时，默认查询选中年份的第一季度数据；3.都为空时，默认查询上一季度的数据
					if(StringUtils.isEmpty(year) && StringUtils.isEmpty(kpiSpecialScore.getQuarter())) {
						kpiSpecialScore.setType("03");
						DateRange lastQuarter = getLastQuarter();
						kpiSpecialScore.setBeginDate(lastQuarter.getStart());
						kpiSpecialScore.setEndDate(lastQuarter.getEnd());
					}else if(StringUtils.isNotEmpty(year) && StringUtils.isEmpty(kpiSpecialScore.getQuarter())) {
						kpiSpecialScore.setType("03");
						month = "02";
						DateRange Quarter = getQuarter(year,month);
						kpiSpecialScore.setBeginDate(Quarter.getStart());
						kpiSpecialScore.setEndDate(Quarter.getEnd());
					}else {
						kpiSpecialScore.setType("03");
						for (Dict dict2 : list) {
							if(dict2.getValue().equals(kpiSpecialScore.getQuarter())) {
								month = dict2.getRemarks();
							}
						}
						DateRange Quarter = getQuarter(year,month);
						kpiSpecialScore.setBeginDate(Quarter.getStart());
						kpiSpecialScore.setEndDate(Quarter.getEnd());
					}
					//给年份赋值回显
					backYear = kpiSpecialScore.getBeginDate();
				}
			}else {
				kpiSpecialScore.setType("03");
				DateRange lastQuarter = getLastQuarter();
				kpiSpecialScore.setBeginDate(lastQuarter.getStart());
				kpiSpecialScore.setEndDate(lastQuarter.getEnd());
			}
			
			//获取网格得分排行
			List<KpiSpecialScore> listWG = kpiSpecialScoreService.getForWG(kpiSpecialScore);
			int wgsize = listWG.size();
			//获取社区得分排行
			List<KpiSpecialScore> listSQ = kpiSpecialScoreService.getForSQ(kpiSpecialScore);
			int sqsize = listSQ.size();
			//获取区县得分排行
			List<KpiSpecialScore> listQX = kpiSpecialScoreService.getForQX(kpiSpecialScore);
			int qxsize = listQX.size();
			
			model.addAttribute("listWG", listWG);
			model.addAttribute("wgsize", wgsize);
			model.addAttribute("listSQ", listSQ);
			model.addAttribute("sqsize", sqsize);
			model.addAttribute("listQX", listQX);
			model.addAttribute("qxsize", qxsize);
			model.addAttribute("year", backYear);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "kpi/special/LeaderBoardList";
	}
	
	//获取上个季度时间范围
	public static DateRange getLastQuarter() {
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.set(Calendar.MONTH, ((int) startCalendar.get(Calendar.MONTH) / 3 - 1) * 3);
		startCalendar.set(Calendar.DAY_OF_MONTH, 1);
		setMinTime(startCalendar);
		
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.set(Calendar.MONTH, ((int) endCalendar.get(Calendar.MONTH) / 3 - 1) * 3 + 2);
		endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		setMaxTime(endCalendar);
		
		return new DateRange(startCalendar.getTime(), endCalendar.getTime());
	}
	
	//获取选中季度时间范围
	public static DateRange getQuarter(String year,String month) {
		Calendar startCalendar = Calendar.getInstance();
		if(StringUtils.isNotEmpty(year)) {
			startCalendar.set(Calendar.YEAR, (Integer.parseInt(year)));
		}
		startCalendar.set(Calendar.MONTH, (Integer.parseInt(month) / 3) * 3);
		startCalendar.set(Calendar.DAY_OF_MONTH, 1);
		setMinTime(startCalendar);
		
		Calendar endCalendar = Calendar.getInstance();
		if(StringUtils.isNotEmpty(year)) {
			endCalendar.set(Calendar.YEAR, (Integer.parseInt(year)));
		}
		endCalendar.set(Calendar.MONTH, (Integer.parseInt(month) / 3) * 3 + 2);
		endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		setMaxTime(endCalendar);
		
		return new DateRange(startCalendar.getTime(), endCalendar.getTime());
	}
	
	//获取上个月度时间范围
	public static DateRange getLastMonth() {
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.set(Calendar.MONTH, ((int) startCalendar.get(Calendar.MONTH) - 1));
		startCalendar.set(Calendar.DAY_OF_MONTH, 1);
		setMinTime(startCalendar);
		
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.set(Calendar.MONTH, ((int) endCalendar.get(Calendar.MONTH) - 1));
		endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		setMaxTime(endCalendar);
			
		return new DateRange(startCalendar.getTime(), endCalendar.getTime());
	}
	
	//获取选择月度时间范围
	public static DateRange getMonth(Date month) {
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(month);
		startCalendar.set(Calendar.DAY_OF_MONTH, 1);
		setMinTime(startCalendar);
		
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(month);
		endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		setMaxTime(endCalendar);
			
		return new DateRange(startCalendar.getTime(), endCalendar.getTime());
	}
	
	//获取上个年度时间范围
	public static DateRange getLastYear() {
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.set(Calendar.YEAR, (startCalendar.get(Calendar.YEAR) - 1));
		startCalendar.set(Calendar.MONTH, 0);
		startCalendar.set(Calendar.DAY_OF_MONTH, 1);
		setMinTime(startCalendar);
		
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.set(Calendar.YEAR, (endCalendar.get(Calendar.YEAR) - 1));
		endCalendar.set(Calendar.MONTH, 11);
		endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		setMaxTime(endCalendar);
			
		return new DateRange(startCalendar.getTime(), endCalendar.getTime());
	}
	
	//获取选择年度时间范围
	public static DateRange getYear(String year) {
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.set(Calendar.YEAR, (Integer.parseInt(year)));
		startCalendar.set(Calendar.MONTH, 0);
		startCalendar.set(Calendar.DAY_OF_MONTH, 1);
		setMinTime(startCalendar);
		
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.set(Calendar.YEAR, (Integer.parseInt(year)));
		endCalendar.set(Calendar.MONTH, 11);
		endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		setMaxTime(endCalendar);
			
		return new DateRange(startCalendar.getTime(), endCalendar.getTime());
	}
	
	private static void setMinTime(Calendar calendar){
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}
	
	private static void setMaxTime(Calendar calendar){
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
	}
	
}
package com.arjjs.ccm.modules.dma.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.modules.dma.service.DmaThreeRealDataAnalysisService;
import com.arjjs.ccm.tool.EchartType;

/**
 * 三实数据分析表现层
 * @author jiaopanyu
 * @version 2019-09-04
 */
@Controller
@RequestMapping(value = "${adminPath}/dma/threeRealDataAnalysis")
public class DmaThreeRealDataAnalysisController {
	@Autowired
	private DmaThreeRealDataAnalysisService threeRealDataAnalysisService;
	/**
	 * 实有居民组合统计页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "residentStatisticsList")
	public String residentStatisticsList(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "dma/threerealdataanalysis/residentStatistics";
	}
	/**
	 * 各区域人口类型统计
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "residentStatisticsData")
	public Map<String, Object> residentStatisticsData( Model model) {
		return threeRealDataAnalysisService.findResidentStatisticsData();
	}
	/**
	 * 各区域人口性别统计
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "sexStatisticsData")
	public Map<String, Object> sexStatisticsData( Model model) {
		return threeRealDataAnalysisService.findSexStatisticsData();
	}
	/**
	 * 实有房屋统计页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "houseStatisticsList")
	public String houseStatisticsList(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "dma/threerealdataanalysis/houseStatistics";
	}
	/**
	 * 房屋产权类别分布统计
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "housePropertyStatisticsData")
	public List<EchartType> housePropertyStatisticsData( Model model) {
		return threeRealDataAnalysisService.findHousePropertyData();
	}
	/**
	 * 房屋建筑用途分布统计
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "housePrupStatisticsData")
	public List<EchartType> housePrupStatisticsData( Model model) {
		return threeRealDataAnalysisService.findHousePrupData();
	}
	/**
	 * 房屋建成年限统计
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "houseYearStatisticsData")
	public List<EchartType> houseYearStatisticsData( Model model) {
		return threeRealDataAnalysisService.findHouseYearData();
	}
	/**
	 * 房屋区域分布统计
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "houseAreaStatisticsData")
	public List<EchartType> houseAreaStatisticsData( Model model) {
		return threeRealDataAnalysisService.findHouseAreaData();
	}
	/**
	 * 房屋结构类型统计
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "houseStructureStatisticsData")
	public List<EchartType> houseStructureStatisticsData( Model model) {
		return threeRealDataAnalysisService.findHouseStructureData();
	}
}

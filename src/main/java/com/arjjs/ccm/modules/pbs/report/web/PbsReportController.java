/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.report.web;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.modules.pbs.common.utils.EchartType;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.pbs.course.entity.PbsCourseoperate;
import com.arjjs.ccm.modules.pbs.course.service.PbsCourseoperateService;
import com.arjjs.ccm.modules.pbs.ncount.entity.PbsNcount;
import com.arjjs.ccm.modules.pbs.ncount.entity.PbsNcountdetail;
import com.arjjs.ccm.modules.pbs.ncount.service.PbsNcountdetailService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 首页统计项Controller
 * 
 * @author lc
 * @version 2018-06-09
 */
@Controller
@RequestMapping(value = "${adminPath}/report/")
public class PbsReportController extends BaseController {

//	@Autowired
//	private PbsNcountService pbsNcountService;
	@Autowired
	private PbsNcountdetailService pbsNcountdetailService;
	@Autowired
	private PbsCourseoperateService pbsCourseoperateService;
	
	
	private static int ten = 10;
//	private static int five = 5;
	
	// 跳转首页
	@RequestMapping(value = { "stat", "" })
	public String list(HttpServletRequest request, HttpServletResponse response, Model model) {

		return "pbs/sys/index/stat";
	}

	//
	@RequestMapping(value = "count")
	public String count(HttpServletRequest request, HttpServletResponse response, Model model) {

		return "pbs/report/reportEchartTest";
	}
	
	@RequestMapping("struct")
	public String struct (HttpServletRequest request, HttpServletResponse response, Model model) {
		return "pbs/report/reportEchartsStruct";
	}
	
	@RequestMapping("num")
	public String num (HttpServletRequest request, HttpServletResponse response, Model model) {
		return "pbs/report/reportEchartsNum";
	}

	@RequestMapping(value = "departmentPeriod")
	public String departmentPeriod(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "pbs/report/reportEchartsDepartmentPeriod";
	}
	
	@RequestMapping(value = "getMap")
	@ResponseBody
	public Map<String, Object> getMap() {
		PbsNcountdetail pbsNcountdetailDto = new PbsNcountdetail();
		List<PbsNcountdetail> pbsNcountdetails = pbsNcountdetailService.findList(pbsNcountdetailDto);
		// 返回的结果集
		Map<String, Object> resultMap = Maps.newConcurrentMap();

		// 党员性别
		List<EchartType> echart0 = Lists.newArrayList();
		// 党员民族
		List<EchartType> echart1 = Lists.newArrayList();
		// 党员部门人数
		List<EchartType> echart2 = Lists.newArrayList();
		// 党员类型人数
		List<EchartType> echart3 = Lists.newArrayList();
		// 党员数量变化
		List<EchartType> echart4 = Lists.newArrayList();
		// 党员活动次数
		List<EchartType> echart5 = Lists.newArrayList();
		// 支部活动次数
		List<EchartType> echart8 = Lists.newArrayList();
		//支部党课次数
		List<EchartType> echart12 = Lists.newArrayList();
		//支部会议次数
		List<EchartType> echart13 = Lists.newArrayList();
		//支部评论次数
		List<EchartType> echart14 = Lists.newArrayList();
		//党员组织类型
		List<EchartType> echart9 = Lists.newArrayList();
		
		// 循环生成统计信息
		for (PbsNcountdetail detail : pbsNcountdetails) {
			String type = detail.getsParentid().getSType();
			EchartType et = new EchartType();
			et.setName(detail.getSName());
			et.setValue(detail.getINumber());
			// et.setsTitle(type);
			// type 空 赋空值
			type=StringUtils.isEmpty(type)?"":type;
			switch (type) {
			// 党员性别人数
			case "0":
				echart0.add(et);
				break;
			// 民族类别人数
			case "1":
				echart1.add(et);
				break;
			// 部门人数
			case "2":
				echart2.add(et);
				break;
			// 党员性别人数
			case "3":
				echart3.add(et);
				break;
			// 党员类型人数
			case "4":
				echart4.add(et);
				break;
			// 党员数量变化
			case "5":
				echart5.add(et);
				break;
			case "8":
				echart8.add(et);
				break;
			case "12":
				echart12.add(et);
				break;
			case "13":
				echart13.add(et);
				break;
			case "14":
				echart14.add(et);
				break;
			case "9":
				echart9.add(et);
				break;
			}

		}
		//
		resultMap.put("type0", echart0);

		resultMap.put("type1", echart1);
		// 部门人数按倒序排名
		Collections.sort(echart2, new Comparator<EchartType>() {
			@Override
			public int compare(EchartType o1, EchartType o2) {
				return compareEchartType(o1, o2, true);
			}
		});
//		List<EchartType> echart2Ex = Lists.newArrayList();
		/*if(echart2.size() > five) {
			for(int i=0;i<five;i++) {
				echart2Ex.add(echart2.get(i));
			}
			resultMap.put("type2", echart2Ex);
		} else {*/
			resultMap.put("type2", echart2);
		/*}*/

		resultMap.put("type3", echart3);

		resultMap.put("type4", echart4);

		resultMap.put("type5", echart5);
		// 成绩按倒序排名
		Collections.sort(echart8, new Comparator<EchartType>() {
			@Override
			public int compare(EchartType o1, EchartType o2) {
				return compareEchartType(o1, o2, false);
			}
		});
//		List<EchartType> echart8Ex = Lists.newArrayList();
		/*if(echart8.size() > ten) {
			for(int i=0;i<ten;i++) {
				echart8Ex.add(echart8.get(i));
			}
			resultMap.put("type8", echart8Ex);
		} else {*/
		resultMap.put("type8", echart8);
		/*}*/
		resultMap.put("type12", echart12);
		resultMap.put("type13", echart13);
		resultMap.put("type14", echart14);
		resultMap.put("type9",echart9);
		return resultMap;
	}

	//
	public int compareEchartType(EchartType arg0, EchartType arg1, boolean flag) {
		int hits0 = arg0.getValue();
		int hits1 = arg1.getValue();
		if (hits1 < hits0) {
			return flag?1:-1;
		} else if (hits1 == hits0) {
			return 0;
		} else {
			return flag?-1:1;		
		}
	}

	//
	@ResponseBody
	@RequestMapping(value = "personStat")
	public Map<String, Object> personStat(Model model) {
		// 返回对象结果
		Map<String, Object> map = Maps.newHashMap();
		List<EchartType> list1 = Lists.newArrayList();
		// 详细信息
		PbsNcountdetail pbsNcountdetailDto = new PbsNcountdetail();
		PbsNcount pbsNcountDto = new PbsNcount();
		pbsNcountDto.setSType("8");
		pbsNcountdetailDto.setsParentid(pbsNcountDto);
		//
		List<PbsNcountdetail> pbsNcountdetails = pbsNcountdetailService.findList(pbsNcountdetailDto);
		if(pbsNcountdetails.size() > 10) {
			for (int i=0;i<10;i++) {
				EchartType e1 = new EchartType();
				e1.setName(pbsNcountdetails.get(i).getSName());
				e1.setValue(pbsNcountdetails.get(i).getINumber());
				// 返回 数据1
				list1.add(e1);
			}
		} else {
			for (PbsNcountdetail detail : pbsNcountdetails) {
				EchartType e1 = new EchartType();
				e1.setName(detail.getSName());
				e1.setValue(detail.getINumber());
				// 返回 数据1
				list1.add(e1);
			}
		}
		List<EchartType> list2 = Lists.newArrayList();
		// 详细信息
		PbsNcountdetail pbsNcountdetail2Dto = new PbsNcountdetail();
		PbsNcount pbsNcount2Dto = new PbsNcount();
		pbsNcount2Dto.setSType("6");
		pbsNcountdetail2Dto.setsParentid(pbsNcount2Dto);
		//
		List<PbsNcountdetail> pbsNcountdetails2 = pbsNcountdetailService.findList(pbsNcountdetail2Dto);
		if(pbsNcountdetails2.size()>ten) {
			for (int i=0;i<ten;i++) {
				EchartType e1 = new EchartType();
				e1.setName(pbsNcountdetails2.get(i).getSName());
				e1.setValue(pbsNcountdetails2.get(i).getINumber());
				// 返回 数据1
				list2.add(e1);
			}
		} else {
			for (PbsNcountdetail detail : pbsNcountdetails2) {
				EchartType e1 = new EchartType();
				e1.setName(detail.getSName());
				e1.setValue(detail.getINumber());
				// 返回 数据1
				list2.add(e1);
			}
		}
		Collections.sort(list1, new Comparator<EchartType>() {
			@Override
			public int compare(EchartType o1, EchartType o2) {
				return compareEchartType(o1, o2, true);
			}
		});
		
		Collections.sort(list2, new Comparator<EchartType>() {
			@Override
			public int compare(EchartType o1, EchartType o2) {
				return compareEchartType(o1, o2, true);
			}
		});
		
		map.put("部门考试成绩", list1);
		map.put("个人考试成绩", list2);
		return map;
	}
	
	//党员组织构成查询
	@ResponseBody
	@RequestMapping(value = "personsStruct")
	public Map<String, Object> personsStruct( Model model) {
		// 返回对象结果
		Map<String, Object> map = Maps.newHashMap();
		List<EchartType> list1 = Lists.newArrayList();
		// 详细信息
		PbsNcountdetail pbsNcountdetailDto = new PbsNcountdetail();
		PbsNcount pbsNcountDto = new PbsNcount();
		pbsNcountDto.setSType("0");
		pbsNcountdetailDto.setsParentid(pbsNcountDto);
		//
		List<PbsNcountdetail> pbsNcountdetails = pbsNcountdetailService.findList(pbsNcountdetailDto);
		for (PbsNcountdetail detail : pbsNcountdetails) {
			EchartType e1 = new EchartType();
			e1.setName(detail.getSName());
			e1.setValue(detail.getINumber());
			// 返回 数据1
			list1.add(e1);
		}
		List<EchartType> list2 = Lists.newArrayList();
		// 详细信息
		PbsNcountdetail pbsNcountdetail2Dto = new PbsNcountdetail();
		PbsNcount pbsNcount2Dto = new PbsNcount();
		pbsNcount2Dto.setSType("9");
		pbsNcountdetail2Dto.setsParentid(pbsNcount2Dto);

		List<PbsNcountdetail> pbsNcountdetails2 = pbsNcountdetailService.findList(pbsNcountdetail2Dto);
		for (PbsNcountdetail detail : pbsNcountdetails2) {
			EchartType e1 = new EchartType();
			e1.setName(detail.getSName());
			e1.setValue(detail.getINumber());
			// 返回 数据1
			list2.add(e1);
		}
		map.put("学员性别占比", list1);
		map.put("学员组织构成", list2);
		return map;
	}
	
	//党员组织构成查询
		@ResponseBody
		@RequestMapping(value = "getNum")
		public Map<String, Object> getNum( Model model) {
			// 返回对象结果
			Map<String, Object> map = Maps.newHashMap();
			List<EchartType> list1 = Lists.newArrayList();
			// 详细信息
			PbsNcountdetail pbsNcountdetailDto = new PbsNcountdetail();
			PbsNcount pbsNcountDto = new PbsNcount();
			pbsNcountDto.setSType("5");
			pbsNcountdetailDto.setsParentid(pbsNcountDto);
			//
			List<PbsNcountdetail> pbsNcountdetails = pbsNcountdetailService.findList(pbsNcountdetailDto);
			for (PbsNcountdetail detail : pbsNcountdetails) {
				EchartType e1 = new EchartType();
				e1.setName(detail.getSName());
				e1.setValue(detail.getINumber());
				// 返回 数据1
				list1.add(e1);
			}
			List<EchartType> list2 = Lists.newArrayList();
			// 详细信息
			PbsNcountdetail pbsNcountdetail2Dto = new PbsNcountdetail();
			PbsNcount pbsNcount2Dto = new PbsNcount();
			pbsNcount2Dto.setSType("4");
			pbsNcountdetail2Dto.setsParentid(pbsNcount2Dto);

			List<PbsNcountdetail> pbsNcountdetails2 = pbsNcountdetailService.findList(pbsNcountdetail2Dto);
			for (PbsNcountdetail detail : pbsNcountdetails2) {
				EchartType e1 = new EchartType();
				e1.setName(detail.getSName());
				e1.setValue(detail.getINumber());
				// 返回 数据1
				list2.add(e1);
			}
			map.put("学员活动变化曲线", list1);
			map.put("学员数量变化曲线", list2);
			return map;
		}
		
		/**
		 * 党支部学习统计(按时段统计党支部全体学员的学习时长)
		 * @param pbsCourseoperate
		 * @param request
		 * @param response
		 * @param model
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value = "getDepartmentPeriod")
		public Map<String,Object> getDepartmentPeriod(PbsCourseoperate pbsCourseoperate,HttpServletRequest request, HttpServletResponse response,Model model){
			Map<String, Object> map = Maps.newHashMap();
			List<EchartType> list = Lists.newArrayList();
			List<PbsCourseoperate> pbsCourseoperateList = pbsCourseoperateService.getDepartmentPeriod(pbsCourseoperate);
			for (PbsCourseoperate pbsCourseoperate2 : pbsCourseoperateList) {
				EchartType e1 = new EchartType();
				e1.setId(pbsCourseoperate2.getId());
				e1.setName(pbsCourseoperate2.getoName());
				e1.setValue(Integer.valueOf(pbsCourseoperate2.getITime()));
				list.add(e1);
			}
			map.put("list", list);
			return map;
		}
		
		/**
		 * 人员学时统计
		 * @param pbsCourseoperate
		 * @param request
		 * @param response
		 * @param model
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value = "getPersonnelPeriod")
		public Map<String,Object> getPersonnelPeriod(PbsCourseoperate pbsCourseoperate,HttpServletRequest request, HttpServletResponse response,Model model) {
			Map<String, Object> map = Maps.newHashMap();
			List<PbsCourseoperate> list = pbsCourseoperateService.getPersonnelPeriod(pbsCourseoperate);
			map.put("list", list);
			return map;
		}
		
}
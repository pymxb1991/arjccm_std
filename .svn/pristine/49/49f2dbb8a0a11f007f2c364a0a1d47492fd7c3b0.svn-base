/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.car.web;

import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.plm.car.entity.PlmCar;
import com.arjjs.ccm.modules.plm.car.entity.PlmCarUse;
import com.arjjs.ccm.modules.plm.car.service.PlmCarService;
import com.arjjs.ccm.modules.plm.car.service.PlmCarUseService;
import com.arjjs.ccm.modules.plm.car.service.PlmCarUseSpendService;
import com.arjjs.ccm.modules.plm.car.service.apply.PlmCarApplyRepairService;
import com.arjjs.ccm.modules.plm.car.service.apply.PlmCarApplyScrapService;
import com.arjjs.ccm.modules.plm.car.service.apply.PlmCarApplyUseService;
import com.arjjs.ccm.modules.plm.statistics.entity.PlmStatisticsDict;
import com.arjjs.ccm.modules.plm.statistics.service.PlmStatisticsDictService;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.EchartType;
import com.arjjs.ccm.tool.Select2Type;

import net.sf.json.JSONArray;

/**
 * 领用记录Controller
 * @author fu
 * @version 2018-07-02
 */
@Controller
@RequestMapping(value = "${adminPath}/car/plmCarUse")
public class PlmCarUseController extends BaseController {

	@Autowired
	private PlmCarUseService plmCarUseService;
	@Autowired
	private PlmCarService plmCarService;
	@Autowired
	private PlmCarApplyRepairService plmCarApplyRepairService;	
	@Autowired
	private PlmCarApplyScrapService plmCarApplyScrapService;
	@Autowired
	private PlmCarApplyUseService plmCarApplyUseService;
	@Autowired
	private PlmStatisticsDictService plmStatisticsDictService;
	@Autowired
	private PlmCarUseSpendService plmCarUseSpendService;
	
	@ModelAttribute
	public PlmCarUse get(@RequestParam(required=false) String id) {
		PlmCarUse entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmCarUseService.get(id);
		}
		if (entity == null){
			entity = new PlmCarUse();
		}
		return entity;
	}
	
	@RequiresPermissions("car:plmCarUse:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmCarUse plmCarUse, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmCarUse> page = plmCarUseService.findPage(new Page<PlmCarUse>(request, response), plmCarUse); 
		model.addAttribute("page", page);
		return "plm/car/plmCarUseList";
	}
	@RequiresPermissions("car:plmCarUse:view")
	@RequestMapping(value = {"carList"})
	public String carList(PlmCarUse plmCarUse, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmCarUse> page = plmCarUseService.findPage(new Page<PlmCarUse>(request, response), plmCarUse); 
		model.addAttribute("page", page);
		PlmCar plmCar = plmCarService.get(plmCarUse.getCar().getId());
		model.addAttribute("plmCar",plmCar);
		return "plm/car/plmOneCarUseList";
	}

	@RequiresPermissions("car:plmCarUse:view")
	@RequestMapping(value = "form")
	public String form(PlmCarUse plmCarUse, Model model) {
		model.addAttribute("plmCarUse", plmCarUse);
		return "plm/car/plmCarUseForm";
	}
	@RequiresPermissions("car:plmCarUse:view")
	@RequestMapping(value = "carForm")
	public String carForm(PlmCarUse plmCarUse, Model model) {
		model.addAttribute("plmCarUse", plmCarUse);
		return "plm/car/plmOneCarUseForm";
	}

	@RequiresPermissions("car:plmCarUse:edit")
	@RequestMapping(value = "save")
	public String save(PlmCarUse plmCarUse, Model model, RedirectAttributes redirectAttributes,String oneList) {
		if (!beanValidator(model, plmCarUse)){
			return form(plmCarUse, model);
		}
		plmCarUseService.save(plmCarUse);
		
		addMessage(redirectAttributes, "保存领用记录成功");
		if(oneList!=null&&"1".equals(oneList)) {
	
		return "redirect:"+Global.getAdminPath()+"/car/plmCarUse/carList?car.id="+plmCarUse.getCar().getId();
		}
		
		return "redirect:"+Global.getAdminPath()+"/car/plmCarUse/?repage";
	}
	
	@RequiresPermissions("car:plmCarUse:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmCarUse plmCarUse, RedirectAttributes redirectAttributes) {
		plmCarUseService.delete(plmCarUse);
		addMessage(redirectAttributes, "删除领用记录成功");
		return "redirect:"+Global.getAdminPath()+"/car/plmCarUse/?repage";
	}
	
	@ResponseBody
	@RequestMapping(value = {"applyList"})
	public List<Select2Type> applyList(Model model,PlmCarUse plmCarUse) {
		List<Select2Type> list = new ArrayList<>();
		if("01".equals(plmCarUse.getType())){
			list = plmCarApplyUseService.findSelect2Type();
		}else if("02".equals(plmCarUse.getType())){
			list = plmCarApplyRepairService.findSelect2Type();
		}else if("03".equals(plmCarUse.getType())){
			list = plmCarApplyScrapService.findSelect2Type();
		}else{
			
		}
		 
		return list;
	}
	
	/**
	 * 统计首页-车辆领用次数top10统计
	 * @param request
	 * @param response
	 * @param height
	 * @param width
	 * @param content
	 * @return
	 */
	@RequestMapping(value = {"useNumByCar"})
	public String useNumByCar( String isOffice,HttpServletRequest request, HttpServletResponse response ,String height,String width , String content,String sid) {
		PlmStatisticsDict PlmStatisticsDict= plmStatisticsDictService.typeAndLine(content);
		request.setAttribute("porline", PlmStatisticsDict.getLine()); 
		request.setAttribute("portype", PlmStatisticsDict.getType()); 
		Office office = new Office();
		if(StringUtils.isNotBlank(isOffice)&&isOffice.equals("0")){
			office = UserUtils.getUser().getOffice();
		}
		List<EchartType> list = plmCarUseService.selectUseNumByCar(office);
		
		JSONArray jsondata = JSONArray.fromObject(list); 
		request.setAttribute("porid", sid);
		request.setAttribute("jsondata", jsondata);
		request.setAttribute("porheigh", height);
	  
		request.setAttribute("porwidth", width);
		return "plm/statistics/car/useNumByCar";
	}
	
	/**
	 * 统计首页-本年度部门用车统计（次数、里程、费用）
	 * @param request
	 * @param response
	 * @param height
	 * @param width
	 * @param content
	 * @return
	 */
	@RequestMapping(value = {"allNumByOffice"})
	public String allNumByOffice(HttpServletRequest request, HttpServletResponse response ,String height,String width , String content,String sid) {
		PlmStatisticsDict PlmStatisticsDict= plmStatisticsDictService.typeAndLine(content);
		request.setAttribute("porline", PlmStatisticsDict.getLine()); 
		request.setAttribute("portype", PlmStatisticsDict.getType()); 
		//用车次数
		List<EchartType> useList = plmCarUseService.selectUseNumAllByOffice();
		//用车费用
		List<EchartType> spendList = plmCarUseSpendService.selectSpendNumAllByOffice();
		//用车里程
		List<EchartType> distanceList = plmCarUseSpendService.selectDistanceNumAllByOffice();
		JSONArray jsondataUse = JSONArray.fromObject(useList); 
		JSONArray jsondataSpend = JSONArray.fromObject(spendList); 
		JSONArray jsondataDistance = JSONArray.fromObject(distanceList); 
		request.setAttribute("porid", sid);
		request.setAttribute("jsondataUse", jsondataUse);
		request.setAttribute("jsondataSpend", jsondataSpend);
		request.setAttribute("jsondataDistance", jsondataDistance);
		request.setAttribute("porheigh", height);
	  
		request.setAttribute("porwidth", width);
		return "plm/statistics/car/allNumByOffice";
	}
	
	/**
	 * 统计首页-本年度司机出车/违章/损坏次数统计
	 * @param request
	 * @param response
	 * @param height
	 * @param width
	 * @param content
	 * @return
	 */
	@RequestMapping(value = {"numByDriver"})
	public String numByDriver(HttpServletRequest request, HttpServletResponse response ,String height,String width , String content,String sid) {
		PlmStatisticsDict PlmStatisticsDict= plmStatisticsDictService.typeAndLine(content);
		request.setAttribute("porline", PlmStatisticsDict.getLine()); 
		request.setAttribute("portype", PlmStatisticsDict.getType()); 
		List<EchartType> list = plmCarUseService.selectNumByDriver();
		
		JSONArray jsondata = JSONArray.fromObject(list); 
		request.setAttribute("porid", sid);
		request.setAttribute("jsondata", jsondata);
		request.setAttribute("porheigh", height);
	  
		request.setAttribute("porwidth", width);
		return "plm/statistics/car/numByDriver";
	}
	/**
	 * 统计首页-近六个月用车统计（费用、次数、违章、事故）
	 * @param request
	 * @param response
	 * @param height
	 * @param width
	 * @param content
	 * @return
	 */
	@RequestMapping(value = {"allNumByMonth"})
	public String allNumByMonth(HttpServletRequest request, HttpServletResponse response ,String height,String width , String content,String sid) {
		PlmStatisticsDict PlmStatisticsDict= plmStatisticsDictService.typeAndLine(content);
		request.setAttribute("porline", PlmStatisticsDict.getLine()); 
		request.setAttribute("portype", PlmStatisticsDict.getType()); 
		//用车次数/违章
		List<EchartType> useAndVioList = plmCarUseService.selectUseAndVioNumByMonth();

		//用车费用/里程
		List<EchartType> damagedAndDisList = plmCarUseSpendService.selectDamagedAndDisNumByMonth();
		//获取当前月份
		Calendar cale= Calendar.getInstance();
	    Integer month = cale.get(Calendar.MONTH) + 1;
	    if(useAndVioList.size()>0) {
	    EchartType echartType =useAndVioList.get(useAndVioList.size()-1);
			if(month>StringUtils.toInteger(echartType.getType())){
				EchartType echartType2 = new EchartType();
				echartType2.setType(month.toString());
				echartType2.setValue(null);
				echartType2.setValue2(null);
				useAndVioList.add(echartType2);
			}
	    }
		JSONArray jsondataUseAndVio = JSONArray.fromObject(useAndVioList); 
		JSONArray jsondataDamagedAndDis = JSONArray.fromObject(damagedAndDisList); 
		request.setAttribute("porid", sid);
		request.setAttribute("jsondataUseAndVio", jsondataUseAndVio);
		request.setAttribute("jsondataDamagedAndDis", jsondataDamagedAndDis);
		request.setAttribute("porheigh", height);
	  
		request.setAttribute("porwidth", width);
		return "plm/statistics/car/allNumByMonth";
	}	
	/**
	 * 统计首页-部门用车统计Top10
	 * @param request
	 * @param response
	 * @param height
	 * @param width
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "useNumByOffice")
	public String useNumByOffice(HttpServletRequest request, HttpServletResponse response ,String height,String width , String content,String sid) {
		PlmStatisticsDict PlmStatisticsDict= plmStatisticsDictService.typeAndLine(content);
		request.setAttribute("porline", PlmStatisticsDict.getLine()); 
		request.setAttribute("portype", PlmStatisticsDict.getType()); 
		List<EchartType> list = plmCarUseService.selectUseNumByOffice();
		
		JSONArray jsondata = JSONArray.fromObject(list); 
		request.setAttribute("porid", sid);
		request.setAttribute("jsondata", jsondata);
		request.setAttribute("porheigh", height);
	  
		request.setAttribute("porwidth", width);
		return "plm/statistics/car/useNumByOffice";
	}
	/**
	 * 统计首页-部门用车统计Ajax
	 * @param request
	 * @param response	
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"useNumAllByOfficeAjax"})
	public JSONArray useNumAllByOfficeAjax(HttpServletRequest request, HttpServletResponse response ,String height,String width , String content,String sid) {
		//top10
		//List<EchartType> list = plmCarUseService.selectUseNumByOffice();
		//用车次数
		List<EchartType> useList = plmCarUseService.selectUseNumAllByOffice();
		
		
		
		JSONArray jsondata = JSONArray.fromObject(useList); 
		
		return jsondata;
	}
	
	
	
	/**
	 * 统计首页-近5个月用车统计（费用、次数）Ajax
	 * @param request
	 * @param response 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"allNumByMonthAjax"})
	public JSONArray allNumByMonthAjax(HttpServletRequest request, HttpServletResponse response ) {
		
		//用车次数
		List<EchartType> useAndVioList = plmCarUseService.selectUseAndVioNumByMonth();

		//用车费用
		//List<EchartType> damagedAndDisList = plmCarUseSpendService.selectDamagedAndDisNumByMonth();
		//获取当前月份
		Calendar cale= Calendar.getInstance();
	    Integer month = cale.get(Calendar.MONTH) + 1;
	    List<Object> listmonth=new ArrayList<>();
		
		
		 List<Object> listValue=new ArrayList<>();
	    for (int i = 1; i <=5; i++) {	
     	   int monthfor=month-5+i;
     	   if(monthfor<=0) {
     		   monthfor+=12;
     	   }
     	  listmonth.add(monthfor);
    	  
     	  boolean not=true;
	     	 
     	 
    	  for (EchartType echart : useAndVioList) {
			  if(Integer.valueOf(echart.getType())==monthfor) {
				  listValue.add(echart.getValue()); 
				  not=false;
			  }					   					 
		   } 
    	  if(not==true) {
    		  listValue.add(0); 
    	  }
	    
	    }
	    List<Object> list=new ArrayList<>();
	    list.add(listmonth);
	    list.add(listValue);
		JSONArray jsondata = JSONArray.fromObject(list); 
		//JSONArray jsondataDamagedAndDis = JSONArray.fromObject(damagedAndDisList); 
		
		return jsondata;
	}	
}
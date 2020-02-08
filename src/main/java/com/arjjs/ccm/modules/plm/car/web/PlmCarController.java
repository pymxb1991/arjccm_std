/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.car.web;

import java.util.ArrayList;
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
import com.arjjs.ccm.modules.plm.car.service.PlmCarService;
import com.arjjs.ccm.modules.plm.home.service.PlmPortalDictService;
import com.arjjs.ccm.modules.plm.statistics.entity.PlmStatisticsDict;
import com.arjjs.ccm.modules.plm.statistics.service.PlmStatisticsDictService;
import com.arjjs.ccm.modules.plm.storage.entity.PlmStorage;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.utils.DictUtils;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.EchartType;
import com.arjjs.ccm.tool.Select2Type;

import net.sf.json.JSONArray;

/**
 * 车辆Controller
 * @author fu
 * @version 2018-06-30
 */
@Controller
@RequestMapping(value = "${adminPath}/car/plmCar")
public class PlmCarController extends BaseController {

	@Autowired
	private PlmCarService plmCarService;
	@Autowired
	private PlmStatisticsDictService plmStatisticsDictService;
	@Autowired
	private PlmPortalDictService plmPortalDictService;
	
	@ModelAttribute
	public PlmCar get(@RequestParam(required=false) String id) {
		PlmCar entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmCarService.get(id);
		}
		if (entity == null){
			entity = new PlmCar();
		}
		return entity;
	}
	
	@RequiresPermissions("car:plmCar:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmCar plmCar, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmCar> page = plmCarService.findPage(new Page<PlmCar>(request, response), plmCar); 
		model.addAttribute("page", page);
		return "plm/car/plmCarList";
	}

	@RequiresPermissions("car:plmCar:view")
	@RequestMapping(value = "form")
	public String form(PlmCar plmCar, Model model) {
		model.addAttribute("plmCar", plmCar);
		return "plm/car/plmCarForm";
	}

	@RequiresPermissions("car:plmCar:edit")
	@RequestMapping(value = "save")
	public String save(PlmCar plmCar, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmCar)){
			return form(plmCar, model);
		}
		plmCarService.save(plmCar);
		addMessage(redirectAttributes, "保存车辆成功");
		return "redirect:"+Global.getAdminPath()+"/car/plmCar/?repage";
	}
	
	@RequiresPermissions("car:plmCar:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmCar plmCar, RedirectAttributes redirectAttributes) {
		plmCarService.delete(plmCar);
		addMessage(redirectAttributes, "删除车辆成功");
		return "redirect:"+Global.getAdminPath()+"/car/plmCar/?repage";
	}
	
	@ResponseBody
	@RequestMapping(value = {"selectList"})
	public List<Select2Type> selectList(Model model,PlmCar plmCar) {
		List<Select2Type> list = plmCarService.findSelect2Type(plmCar); 
		return list;
	}
	@ResponseBody
	@RequestMapping(value = {"getById"})
	public PlmCar getById(@RequestParam(required=false) String id) {
		PlmCar entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmCarService.get(id);
		}
		if (entity == null){
			entity = new PlmCar();
		}
		return entity;
	}
	
	/**
	 * 统计首页-车辆类型统计(根据车辆类型)
	 * @param request
	 * @param response
	 * @param height
	 * @param width
	 * @param content
	 * @return
	 */
	@RequestMapping(value = {"numByVtype"})
	public String numByVtype( String isOffice,HttpServletRequest request, HttpServletResponse response ,String height,String width , String content,String sid) {
		PlmStatisticsDict PlmStatisticsDict= plmStatisticsDictService.typeAndLine(content);
		request.setAttribute("porline", PlmStatisticsDict.getLine()); 
		request.setAttribute("portype", PlmStatisticsDict.getType()); 
		Office office = new Office();
		if(StringUtils.isNotBlank(isOffice)&&isOffice.equals("0")){
			office = UserUtils.getUser().getOffice();
		}
		List<EchartType> list = plmCarService.selectNumByVtype(office);
		
		for (EchartType echartType : list) {
			echartType.setType(DictUtils.getDictLabel(echartType.getType(), "plm_car_vtype", ""));
		}
		JSONArray jsondata = JSONArray.fromObject(list); 
		
		request.setAttribute("porid", sid);
		request.setAttribute("jsondata", jsondata);
		request.setAttribute("porheigh", height);
	  
		request.setAttribute("porwidth", width);
		return "plm/statistics/car/numByVtype";
	}
	/**
	 * 个人门户定制首页-车辆
	 * @param request
	 * @param response
	 * @param height
	 * @param width
	 * @param content
	 * @return
	 */
	@RequestMapping(value = {"carforHome"})
	public String actforHomeSup(PlmCar plmCar, HttpServletRequest request, HttpServletResponse response ,String height,String width , String content,String divId) {
	    
	    // 最大显示行数
		int line=plmPortalDictService.line(content);
	

		Page<PlmCar> page = plmCarService.findPage(new Page<PlmCar>(request, response), plmCar); 
		request.setAttribute("porheigh", height);
		request.setAttribute("line", line);
		request.setAttribute("porwidth", width);
		request.setAttribute("porcontent", content);
		request.setAttribute("divId", divId);
		request.setAttribute("page", page);
		return "plm/home/car/plmCarList";
	}
	
	@ResponseBody
	@RequestMapping(value = "countCar")
	public JSONArray countCar(PlmStorage plmStorage, HttpServletResponse response) {
		Integer couot =plmCarService.countCar();
		 List<Integer>  arrlist=new ArrayList<Integer>();
		
			arrlist.add(couot);
		
		JSONArray jsondata = JSONArray.fromObject(arrlist); 		 
		return jsondata;
	}
	/**
	 * 统计首页-车辆类型统计(根据车辆类型)Ajax	
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"numByVtypeAjax"})
	public JSONArray numByVtypeAjax(HttpServletRequest request, HttpServletResponse response ) {
		
		Office office = new Office();
		
		List<EchartType> list = plmCarService.selectNumByVtype(office);
		
		for (EchartType echartType : list) {
			echartType.setType(DictUtils.getDictLabel(echartType.getType(), "plm_car_vtype", ""));
		}
		JSONArray jsondata = JSONArray.fromObject(list); 
		
		
			
		return jsondata;
	}
	/**
	 * 统计首页-车辆状态统计(根据车辆状态)Ajax	
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"countByStatusAjax"})
	public JSONArray countByStatusAjax(HttpServletRequest request, HttpServletResponse response ) {
		
		
		
		List<EchartType> list = plmCarService.countByStatusAjax();
		
		
		JSONArray jsondata = JSONArray.fromObject(list); 
		
		
			
		return jsondata;
	}
}
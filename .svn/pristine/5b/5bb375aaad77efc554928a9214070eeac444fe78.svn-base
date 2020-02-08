/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.storage.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arjjs.ccm.modules.flat.alarm.entity.BphAlarmInfo;
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
import com.arjjs.ccm.modules.plm.statistics.entity.PlmStatisticsDict;
import com.arjjs.ccm.modules.plm.statistics.service.PlmStatisticsDictService;
import com.arjjs.ccm.modules.plm.storage.entity.PlmEquipment;
import com.arjjs.ccm.modules.plm.storage.entity.PlmStorage;
import com.arjjs.ccm.modules.plm.storage.service.PlmEquipmentService;
import com.arjjs.ccm.modules.plm.storage.service.PlmMinusandAddDetailService;
import com.arjjs.ccm.modules.plm.storage.util.QREncodeUtils;
import com.arjjs.ccm.modules.sys.utils.DictUtils;
import com.arjjs.ccm.tool.EchartType;
import com.google.zxing.common.BitMatrix;

import net.sf.json.JSONArray;

/**
 * 装备物资Controller
 * @author dongqikai
 * @version 2018-06-27
 */
@Controller
@RequestMapping(value = "${adminPath}/storage/plmEquipment")
public class PlmEquipmentController extends BaseController {

	@Autowired
	private PlmEquipmentService plmEquipmentService;
	
	@Autowired
	private PlmStatisticsDictService plmStatisticsDictService;
	@Autowired
	private PlmMinusandAddDetailService plmMinusandAddDetailService;
	@ModelAttribute
	public PlmEquipment get(@RequestParam(required=false) String id) {
		PlmEquipment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmEquipmentService.get(id);
		}
		if (entity == null){
			entity = new PlmEquipment();
		}
		return entity;
	}
	
	@RequiresPermissions("storage:plmEquipment:view")
	@RequestMapping(value = {"index"})
	public String index(PlmEquipment plmEquipment, Model model) {
		return "plm/storage/plmEquipmentIndex";
	}
	
	@RequiresPermissions("storage:plmEquipment:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmEquipment plmEquipment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmEquipment> page = plmEquipmentService.findPage(new Page<PlmEquipment>(request, response), plmEquipment); 
		model.addAttribute("page", page);
		model.addAttribute("provideList", plmEquipmentService.getProvidList());
		model.addAttribute("officeFlag", plmEquipment.getOfficeFlag());
		return "plm/storage/plmEquipmentList";
	}

	@RequiresPermissions("storage:plmEquipment:view")
	@RequestMapping(value = "form")
	public String form(PlmEquipment plmEquipment, Model model) {
		model.addAttribute("plmEquipment", plmEquipment);
		model.addAttribute("provideList", plmEquipmentService.getProvidList());
		return "plm/storage/plmEquipmentForm";
	}

	@RequiresPermissions("storage:plmEquipment:edit")
	@RequestMapping(value = "save")
	public String save(PlmEquipment plmEquipment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmEquipment)){
			return form(plmEquipment, model);
		}
		plmEquipmentService.save(plmEquipment);
		addMessage(redirectAttributes, "保存装备物资成功");
		return "redirect:"+Global.getAdminPath()+"/storage/plmEquipment/?repage";
	}
	
	@RequiresPermissions("storage:plmEquipment:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmEquipment plmEquipment, RedirectAttributes redirectAttributes) {
		plmEquipmentService.delete(plmEquipment);
		addMessage(redirectAttributes, "删除装备物资成功");
		return "redirect:"+Global.getAdminPath()+"/storage/plmEquipment/?repage";
	}
	
	/**
	 * 各倉庫物资统计
	 * @author liuxue
	 * @return list
	 */
	@RequestMapping(value = "countEquipmentByStorage")
	public String countEquipmentByStorage(String isOffice, HttpServletRequest request, HttpServletResponse response ,String height,String width , String content,String sid) {
		 PlmStatisticsDict PlmStatisticsDict= plmStatisticsDictService.typeAndLine(content);
		 request.setAttribute("porline", PlmStatisticsDict.getLine()); 
		 request.setAttribute("portype", PlmStatisticsDict.getType());
		 request.setAttribute("porheigh", height);	  
		 request.setAttribute("porwidth", width);
		 request.setAttribute("porid", sid);
		 
		 
		 
		 List<Object> listY=new ArrayList<>();
			
		 List<Object> listValue2=new ArrayList<>();
		 List<Object> listValue3=new ArrayList<>();
		
		 PlmEquipment plmEquipment =new PlmEquipment();
		 PlmEquipment plmEquipmentk =new PlmEquipment();
		 plmEquipment.setType("");
		List<PlmEquipment> equipmentListAll = plmEquipmentService.countEquipmentByStorage(plmEquipment);
		plmEquipmentk.setType("1");
		List<PlmEquipment> equipmentListKong = plmEquipmentService.countEquipmentByStorage(plmEquipmentk);
		for (PlmEquipment plmEquipment2 : equipmentListAll) {
			listY.add(plmEquipment2.getStorage().getName());
			listValue2.add(plmEquipment2.getCounts());
			
		}
		for (PlmEquipment plmEquipment3 : equipmentListKong) {
			
			listValue3.add(plmEquipment3.getCounts());
			
		} 
		  	        		      				
		//将list 转化为json格式
		JSONArray jsonY = JSONArray.fromObject(listY); 		
		JSONArray jsondata2 = JSONArray.fromObject(listValue2); 
		JSONArray jsondata3 = JSONArray.fromObject(listValue3); 
		
		  request.setAttribute("jsonY", jsonY);	 
		  request.setAttribute("jsondata2", jsondata2);
		  request.setAttribute("jsondata3", jsondata3);		
		return "plm/statistics/storage/countEquipmentByStorage";
	}
	

	/**
	 * 各倉庫物资统计Ajax
	 * @author liuxue
	 * @return list
	 */
	@ResponseBody
	@RequestMapping(value = "countEquipmentByStorageAjax")
	public JSONArray countEquipmentByStorageAjax(HttpServletRequest request, HttpServletResponse response ) {
		 
		 
		 
		 
		
			
		
		
		 PlmEquipment plmEquipment =new PlmEquipment();
		 PlmEquipment plmEquipmentk =new PlmEquipment();
		 plmEquipment.setType("");
		List<PlmEquipment> equipmentListAll = plmEquipmentService.countEquipmentByStorage(plmEquipment);
		plmEquipmentk.setType("1");//闲置
		List<PlmEquipment> equipmentListKong = plmEquipmentService.countEquipmentByStorage(plmEquipmentk);
		
		 List<Object> listValue=new ArrayList<>(); 
	
		for (PlmEquipment plmEquipment2 : equipmentListAll) {
			Map<String, Object> map=new HashMap<String, Object>();
			String name=plmEquipment2.getStorage().getName();
			map.put("name",name );
			Integer valueAll=plmEquipment2.getCounts();
			map.put("valueAll",valueAll );
			
			

			for (PlmEquipment plmEquipment3 : equipmentListKong) {
				Integer value1=plmEquipment3.getCounts();
				if(name.equals(plmEquipment3.getStorage().getName())) {
				map.put("value1", value1);
				map.put("value2", valueAll-value1);
				
				}
			}
			listValue.add(map);
		}
		 
		
		 
		//将list 转化为json格式
				
		JSONArray jsondata = JSONArray.fromObject(listValue); 
		
		
		 	
		return jsondata;
	}
	
	/**
	 *  仓库物资统状态计图
	 */
	@RequestMapping(value = {"countEquipmentByStats"})
	public String countEquipmentByType( HttpServletRequest request, HttpServletResponse response ,String height,String width , String content,String sid) {
		 PlmStatisticsDict PlmStatisticsDict= plmStatisticsDictService.typeAndLine(content);
		 request.setAttribute("porline", PlmStatisticsDict.getLine()); 
		 request.setAttribute("portype", PlmStatisticsDict.getType());
		 request.setAttribute("porheigh", height);	  
		 request.setAttribute("porwidth", width);
		 request.setAttribute("porid", sid);
	
	
		 //	 统计各个物资统状的数量   以EchartType类返回
		 List<EchartType> equipmentList = plmEquipmentService.countEquipmentByStats();
		 			
		List<Object> listValue=new ArrayList<>();
		
		for (EchartType echartType : equipmentList) {
			echartType.setType(DictUtils.getDictLabel(echartType.getType(),"plm_equipment_status",""));
	
			listValue.add(echartType);
		}		
		//将list 转化为json格式
		JSONArray jsondata = JSONArray.fromObject(listValue); 		
	  request.setAttribute("jsondata", jsondata);	 	  
		return "plm/statistics/storage/countEquipmentByStats";
	}
	
	/**
	 * 各个物资统计数量统计
	 * @author liuxue
	 * @return list
	 */
	@RequestMapping(value = "countEquipmentByType")
	public String countEquipmentByType(String isOffice, HttpServletRequest request, HttpServletResponse response ,String height,String width , String content,String sid) {
		 PlmStatisticsDict PlmStatisticsDict= plmStatisticsDictService.typeAndLine(content);
		 request.setAttribute("porline", PlmStatisticsDict.getLine()); 
		 request.setAttribute("portype", PlmStatisticsDict.getType());
		 request.setAttribute("porheigh", height);	  
		 request.setAttribute("porwidth", width);
		 request.setAttribute("porid", sid);
		 
		 
		 
		 List<Object> listY=new ArrayList<>();
			
		
		 List<Object> listValue0=new ArrayList<>();
		 List<Object> listValue1=new ArrayList<>();
		 List<Object> listValue2=new ArrayList<>();
		 List<Object> listValue3=new ArrayList<>();
		 List<Object> listValue4=new ArrayList<>();
		

		
		 //占用，闲置，维修保养，使用中，报废
		
		

		 List<Map<String, Object>> equipmentList = plmEquipmentService.countEquipmentByName();
		
		for (Map<String, Object> map : equipmentList) {
			listY.add(map.get("label"));
			listValue1.add(map.get("value0"));
			listValue1.add(map.get("value1"));
			listValue2.add(map.get("value2"));
			listValue3.add(map.get("value3"));
			listValue4.add(map.get("value4"));
		}
		
		
		
		//将list 转化为json格式
		JSONArray jsonY = JSONArray.fromObject(listY); 		
		JSONArray jsondata0 = JSONArray.fromObject(listValue0); 
		JSONArray jsondata1 = JSONArray.fromObject(listValue1); 
		JSONArray jsondata2 = JSONArray.fromObject(listValue2); 
		JSONArray jsondata3 = JSONArray.fromObject(listValue3); 
		JSONArray jsondata4 = JSONArray.fromObject(listValue4); 
	
		
		  request.setAttribute("jsonY", jsonY);	 
		  request.setAttribute("jsondata0", jsondata0);
		  request.setAttribute("jsondata1", jsondata1);
		  request.setAttribute("jsondata2", jsondata2);
		  request.setAttribute("jsondata3", jsondata3);	
		  request.setAttribute("jsondata4", jsondata4);
	
		return "plm/statistics/storage/countEquipmentByType";
	}
	
	/**
	 * 物资数  以月为时间轴
	 */
	@RequestMapping(value = {"countEquipmentByTypeDate"})
	public String countEquipmentByTypeDate( HttpServletRequest request, HttpServletResponse response ,String height,String width , String content,String sid) {
		 PlmStatisticsDict PlmStatisticsDict= plmStatisticsDictService.typeAndLine(content);
		 request.setAttribute("porline", PlmStatisticsDict.getLine()); 
		 request.setAttribute("portype", PlmStatisticsDict.getType());
		 request.setAttribute("porheigh", height);	  
		 request.setAttribute("porwidth", width);
		 request.setAttribute("porid", sid);
		 
		 int qmonth=6;//前几个月
		//map : type(01 接待  02会议)  qmonth（前几个月）
		 Map<String, Object> mapechart =new HashMap<String, Object>();
		 mapechart.put("qmonth", qmonth);	 
		 List<Map<String, Object>>  echartTypeList= plmEquipmentService.countEquipmentByTypeDate(mapechart);
		 
		 List<Object> listmonth=new ArrayList<>();
		
		
		 List<Object> listValue=new ArrayList<>();

		
		 Calendar cale= Calendar.getInstance();
		   //获取当前月份
	        int month = cale.get(Calendar.MONTH) + 1;
	        for (int i = 1; i <=qmonth; i++) {	
	        	   int monthfor=month-qmonth+i;
	        	   if(monthfor<=0) {
	        		   monthfor+=12;
	        	   }
	        	  listmonth.add(monthfor+"月");
	        	  
	        	  boolean not=true;
	        	 
	        	  for (Map<String, Object> echartTypeMap : echartTypeList) {
					  if((int)echartTypeMap.get("month")==monthfor) {
						  listValue.add(echartTypeMap.get("count")); 
						  not=false;
					  }					   					 
				   } 
	        	  if(not==true) {
	        		  listValue.add(0); 
	        	  }
	        	  
			}
	      				
		//将list 转化为json格式
		JSONArray jsonmonth = JSONArray.fromObject(listmonth); 
		
		
		JSONArray jsondata = JSONArray.fromObject(listValue); 
	  request.setAttribute("jsonmonth", jsonmonth);
	  
	  request.setAttribute("jsondata", jsondata);
	 
	  
		return "plm/statistics/storage/countEquipmentByTypeDate" ;
	}
	
	/**
	 * 预警物资 
	 * @author xue
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "warningEquipment")
	public JSONArray warningEquipment(PlmStorage plmStorage, HttpServletResponse response) {
		
		Map<String, Object> map=new HashMap<String, Object>();
		
		map.put("qmonth", 0);//0 当前日期
		//到期时间
		 List<Map<String, Object>>  dList =plmEquipmentService.warningEquipment(map);
		
		 map.put("qmonth",null);//n  提前即将到期    
		 //即将到期时间
		 List<Map<String, Object>>  jList =plmEquipmentService.warningEquipment(map);
		 List<Object> dlistType=new ArrayList<>();
		 List<Object> jlistType=new ArrayList<>();
		 List<Object> dlistLengage=new ArrayList<>();	
		 List<Object> jlistLengage=new ArrayList<>();	
			
		 for (Map<String, Object> map2 : dList) {
			 dlistType.add(map2.get("count"));
			 dlistLengage.add(map2.get("name"));
		}
		 for (Map<String, Object> map2 : jList) {
			 jlistType.add(map2.get("count"));
			 jlistLengage.add(map2.get("name"));
		} 
		 
		 
			 List<Object> listValue=new ArrayList<>();
			 listValue.add(dlistLengage);
			 listValue.add(dlistType);
			 listValue.add(jlistLengage);		
			 listValue.add(jlistType);
			 
		JSONArray jsondata = JSONArray.fromObject(listValue); 		 
		return jsondata;
	}
	
	/**
	 * 到期预警物资 (表格)
	 * @author xue
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "warningEquipmentTable")
	public JSONArray warningEquipmentTable(PlmStorage plmStorage, HttpServletResponse response) {
		
		Map<String, Object> map=new HashMap<String, Object>();
		
		map.put("qmonth", 0);//0 当前日期  
		//到期时间
		 List<Map<String, Object>>  dList =plmEquipmentService.warningEquipment(map);
		
		 map.put("qmonth",null );//n  提前n个月即将到期   null所有
		 //即将到期时间
		 List<Map<String, Object>>  jList =plmEquipmentService.warningEquipment(map);
		 List<Object> listMap=new ArrayList<>();
		 //借用超期装备
		 List<Map<String, Object>> cList= plmMinusandAddDetailService.findDeadLineCount();
		 
		 
		 listMap.add(dList);
		 listMap.add(jList);
		 listMap.add(cList);
			
			 
		JSONArray jsondata = JSONArray.fromObject(listMap); 		 
		return jsondata;
	}
	/**
	 * 根据类型统计   typechild=1 时 根据子类型统计
	 * @author xue
	 * @return list
	 */
	@ResponseBody
	@RequestMapping(value = "ratioEquipmentByType")
	public JSONArray ratioEquipmentByType(PlmStorage plmStorage, HttpServletResponse response) {
		//typechild=1
		Map<String, Object> map=new HashMap<String, Object>();
		
		List<EchartType> list =plmEquipmentService.ratioEquipmentByType(map);
		map.put("typechild", 1);
		List<EchartType> zlist =plmEquipmentService.ratioEquipmentByType(map);
		 List<Object> listType=new ArrayList<>();
		 List<Object> zlistType=new ArrayList<>();
		 List<Object> listLengage=new ArrayList<>();	
			for (EchartType echartType : list) {
//				if("0".equals(echartType.getType())) {
//					echartType.setValue(echartType.getValue2());
//				}
				
				echartType.setType(DictUtils.getDictLabel(echartType.getType(),"plm_equipment_type",""));
		
				listType.add(echartType);
				listLengage.add(echartType.getType());
			}		
			for (EchartType echartType : zlist) {
//				if("0".equals(echartType.getType())) {
//					echartType.setValue(echartType.getValue2());
//				}
				echartType.setTypeO(DictUtils.getDictLabel(echartType.getTypeO(),"plm_equipment_type_child",""));
				
				zlistType.add(echartType);
				listLengage.add(echartType.getTypeO());
			}	
			
			 List<Object> listValue=new ArrayList<>();
			 listValue.add(listType);
			 listValue.add(zlistType);
			 listValue.add(listLengage);
		JSONArray jsondata = JSONArray.fromObject(listValue); 		 
		return jsondata;
	}
	
	
	/**
	 * 物资使用情况统计
	 * @author liuxue
	 * @return list
	 */
	@ResponseBody
	@RequestMapping(value = "countEquipmentByStatsAjax")
	public JSONArray countEquipmentByStatsAjax(PlmStorage plmStorage, HttpServletRequest request, HttpServletResponse response ) {
		
		 
		 
		 
		 
		 
		 List<Object> listY=new ArrayList<>();
		 List<Object> listValue1=new ArrayList<>();
		 List<Object> listValue2=new ArrayList<>();
		 List<Object> listValue3=new ArrayList<>();
		 List<Object> listValue4=new ArrayList<>();
		
		
		
		 //闲置，维修保养，使用中，报废

		 List<Map<String, Object>> equipmentList = plmEquipmentService.countEquipmentByName();
		
		for (Map<String, Object> map : equipmentList) {
			Object label=map.get("label");
			if(label==null) {
				label="";
			}
			listY.add(label);
			listValue1.add(map.get("value1"));
			listValue2.add(map.get("value2"));
			listValue3.add(map.get("value3"));
			listValue4.add(map.get("value4"));
		}
		
		
		
		 List<Object> listValue=new ArrayList<>();
		
		 listValue.add(listValue1);
		 listValue.add(listValue2);
		 listValue.add(listValue3);
		 listValue.add(listValue4);
		
		 listValue.add(listY);
		//将list 转化为json格式
			
		
			
		JSONArray jsondata = JSONArray.fromObject(listValue); 
		return jsondata;
	}
	
	/**
	 * 库存统计
	 * @param plmEquipment
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("storage:plmEquipment:view")
	@RequestMapping(value="countByEquType")
	public String countByEquType(PlmEquipment plmEquipment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmEquipment> page = plmEquipmentService.countByEquType(new Page<PlmEquipment>(request, response), plmEquipment); 
		model.addAttribute("page", page);
		return "plm/storage/plmEquipmentCountsList";
	}
	
	/**
	 * 显示二维码
	 * @param code
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="getQrCode")
	public void getQrCode(String code, HttpServletRequest request, HttpServletResponse response) {
		BitMatrix bm = QREncodeUtils.createBitMatrixWithQR(code, 70);
		try {
			QREncodeUtils.writeToStream(bm, "jpg", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 打印二维码
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="printQrCode")
	public String printQrCode(String id, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<PlmEquipment> callbackList = new ArrayList<>();
		PlmEquipment equ = plmEquipmentService.get(id);
		BitMatrix bm = QREncodeUtils.createBitMatrixWithQR(equ.getCode(), 70);
		equ.setQrCode(QREncodeUtils.writeToBase64(bm, "jpg"));
		callbackList.add(equ);
		model.addAttribute("callbackList", callbackList);
		return "plm/storage/printQrCode";
	}


	@RequestMapping(value = "bphNewestplmStatisticsShow")
	public String bphNewestplmStatisticsShow(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "flat/home/bphNewestplmStatisticsShow";
	}
}
/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.act.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.arjjs.ccm.modules.act.entity.Act;
import com.arjjs.ccm.modules.plm.act.entity.PlmAct;
import com.arjjs.ccm.modules.plm.act.service.PlmActService;
import com.arjjs.ccm.modules.plm.home.service.PlmPortalDictService;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.EchartType;

import net.sf.json.JSONArray;

/**
 * 业务申请单主表Controller
 * @author fu
 * @version 2018-07-20
 */
@Controller
@RequestMapping(value = "${adminPath}/act/plmAct")
public class PlmActController extends BaseController {

	@Autowired
	private PlmActService plmActService;
	@Autowired
	private PlmPortalDictService plmPortalDictService;
	@Autowired
	private ActTaskSelfController actTaskSelfController;
	private  String ProcInsIds;
	@ModelAttribute
	public PlmAct get(@RequestParam(required=false) String id) {
		PlmAct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmActService.get(id);
		}
		if (entity == null){
			entity = new PlmAct();
		}
		return entity;
	}
	//全部事项	
	@RequiresPermissions("act:plmAct:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmAct plmAct, HttpServletRequest request, HttpServletResponse response, Model model) {
		/*plmAct.setIsSup("");
		plmAct.setCreateBy(UserUtils.getUser());*/
		Page<PlmAct> page = plmActService.findPage(new Page<PlmAct>(request, response), plmAct); 
		model.addAttribute("page", page);
		return "plm/act/plmActList";
	}
	
	
	//我的督办任务(进行中)
	@RequestMapping(value = {"mySupTodolist"})
	public String mySupTodolist(PlmAct plmAct, HttpServletRequest request, HttpServletResponse response, Model model) {
		String procInsId=request.getParameter("procInsIds");
		ProcInsIds=procInsId;
		plmAct.setProcInsId(procInsId);
		plmAct.setIsSup(null);
		plmAct.setSupExe(UserUtils.getUser());
		Page<PlmAct> page = plmActService.findPage(new Page<PlmAct>(request, response), plmAct); 
		model.addAttribute("page", page);
		
		return "plm/act/plmActSupTodoList";
	}
	//我的督办任务(已完成)
	@RequestMapping(value = {"mySupFinishlist"})
	public String mySupFinishlist(PlmAct plmAct, HttpServletRequest request, HttpServletResponse response, Model model) {
		String procInsId=ProcInsIds;
		plmAct.setProcInsId(procInsId);
		plmAct.setSupExe(UserUtils.getUser());
		Page<PlmAct> page = plmActService.findSupFinishPage(new Page<PlmAct>(request, response), plmAct); 
		model.addAttribute("page", page);
		return "plm/act/plmActSupFinishList";
	}	
	//我的全部事项
	@RequiresPermissions("act:plmAct:edit")
	@RequestMapping(value = {"mylist"})
	public String myEndlist(PlmAct plmAct, HttpServletRequest request, HttpServletResponse response, Model model) {
		plmAct.setIsSup(null);
		String procInsId=request.getParameter("procInsIds");
		plmAct.setProcInsId(procInsId);
		plmAct.setCreateBy(UserUtils.getUser());
		Page<PlmAct> page = plmActService.findPage(new Page<PlmAct>(request, response), plmAct); 
		model.addAttribute("page", page);
		return "plm/act/plmActList";
	}	
	@RequestMapping(value = "form")
	public String form(PlmAct plmAct, Model model) {

		return "redirect:" + plmActService.getFormUrl(plmAct);
	}

	@RequiresPermissions("act:plmAct:edit")
	@RequestMapping(value = "save")
	public String save(PlmAct plmAct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmAct)){
			return form(plmAct, model);
		}
		plmActService.save(plmAct);
		addMessage(redirectAttributes, "保存业务申请单主表成功");
		return "redirect:"+Global.getAdminPath()+"/act/plmAct/?repage";
	}
	
	@RequiresPermissions("act:plmAct:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(PlmAct plmAct, RedirectAttributes redirectAttributes) {
		plmActService.delete(plmAct);
		
		addMessage(redirectAttributes, "删除业务申请单主表成功");
		return "200";
	}
	/**
	 * 个人门户定制首页-所有流程
	 * @param request
	 * @param response
	 * @param height
	 * @param width
	 * @param content
	 * @return
	 */
	@RequestMapping(value = {"actforHome"})
	public String actforHome(Act act,PlmAct plmAct, HttpServletRequest request, HttpServletResponse response ,String height,String width , String content,String divId) {

	    //查所有我发起的流程
		plmAct.setCreateBy(UserUtils.getUser());
		Page<PlmAct> page = plmActService.findPage(new Page<PlmAct>(request, response), plmAct); 
		//查所有我的督办流程
		plmAct.setSupExe(UserUtils.getUser());
		plmAct.setCreateBy(null);
		plmAct.setIsSup("1");
		Page<PlmAct> page2 = plmActService.findPage(new Page<PlmAct>(request, response), plmAct); 
		
		//查已办数量
		long fNum = actTaskSelfController.actforHomeFinishNum(act, request, response);
		//查待办数量
		int tNum = actTaskSelfController.actforHomeTodoNum(act);
	    
	    // 最大显示行数
	    int line=plmPortalDictService.line(content);
		request.setAttribute("allNum", page.getCount());
		request.setAttribute("fNum", fNum);
		request.setAttribute("tNum", tNum);
		request.setAttribute("supNum", page2.getCount());
		request.setAttribute("porheigh", height);
		request.setAttribute("line", line);
		request.setAttribute("porwidth", width);
		request.setAttribute("porcontent", content);
		request.setAttribute("divId", divId);
		request.setAttribute("page", page);
		return "plm/home/act/plmActList";
	}
	/**
	 * 个人门户定制首页-我的督办
	 * @param request
	 * @param response
	 * @param height
	 * @param width
	 * @param content
	 * @return
	 */
	@RequestMapping(value = {"actforHomeSup"})
	public String actforHomeSup(PlmAct plmAct, HttpServletRequest request, HttpServletResponse response ,String height,String width , String content,String divId) {
	    
	    // 最大显示行数
		  int line=plmPortalDictService.line(content);
	

		plmAct.setSupExe(UserUtils.getUser());
		plmAct.setIsSup("1");
		Page<PlmAct> page = plmActService.findPage(new Page<PlmAct>(request, response), plmAct); 
		request.setAttribute("porheigh", height);
		request.setAttribute("line", line);
		request.setAttribute("porwidth", width);
		request.setAttribute("porcontent", content);
		request.setAttribute("divId", divId);
		request.setAttribute("page", page);
		return "plm/home/act/plmActSupList";
	}
	
	/**流程趋势  以天为时间轴
	 * @author xue
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"countActByDate"})
	public JSONArray countActByDate( HttpServletRequest request, HttpServletResponse response) {
		
		 
		 int day=15;//前几天
		//map : type  day（前几天）
		 Map<String, Object> mapechart =new HashMap<String, Object>();
		 mapechart.put("day", day);	 
			List<EchartType>  echartTypeList= plmActService.countActByDate(mapechart);
		 
		 List<Object> listday=new ArrayList<>();
		 //总数
		 List<Object> listValueAll=new ArrayList<>();
		 //审核中
		 List<Object> listValue1=new ArrayList<>();
		 //已完成
		 List<Object> listValue2=new ArrayList<>();
		
		 Calendar cale= Calendar.getInstance();
		
	        SimpleDateFormat df=new SimpleDateFormat("MM月dd");	        	       		    		   	
		    cale.add(Calendar.DATE, -day);
			 
			 
		    for (int i = 1; i <=day; i++) {	
	     	 
	     	  cale.add(Calendar.DATE, 1);
	     	   
	     	   
	     	 Integer daymonth = cale.get(Calendar.DAY_OF_MONTH) ;
	     	  listday.add(df.format(cale.getTime()));
	    	
	    	  boolean not=true;
	     	 
	    	  for (EchartType echart : echartTypeList) {
				  if(Integer.valueOf(echart.getType())==daymonth ) {
					  listValueAll.add(echart.getValue());
					  listValue1.add(echart.getValue2()); 
					  listValue2.add(echart.getValue3()); 
					  not=false;
				  }					   					 
			   } 
        	  if(not==true) {
        		  listValueAll.add(0); 
        		  listValue1.add(0); 
        		  listValue2.add(0); 
        	  }
		    
		    }
		    List<Object> list=new ArrayList<>();
		    list.add(listday);
		    list.add(listValueAll);
		    list.add(listValue1);
		    list.add(listValue2);
			JSONArray jsondata = JSONArray.fromObject(list); 
	
			return jsondata;
	}	
	/**
	 * 流程各状态数量
	 * @author xue
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"countActByStatus"})
	public JSONArray countActByStatus( HttpServletRequest request, HttpServletResponse response) {
		      
		
		     Map<String, Object> map=new  HashMap<String, Object>();
			List<EchartType>  echartTypeList= plmActService.countActByStatus(map);
			
			 EchartType echartType =new EchartType();
			 EchartType echartType2 =new EchartType();
			 EchartType echartTypeo1 =new EchartType();
			 echartTypeo1.setType("未提交");
			 echartTypeo1.setValue("0");
			 EchartType echartTypeo2 =new EchartType();
			 echartTypeo2.setType("审核中");
			 echartTypeo2.setValue("0");
			 EchartType echartTypeo3 =new EchartType();
			 echartTypeo3.setType("已销毁");
			 echartTypeo3.setValue("0");
			 EchartType echartTypeo4 =new EchartType();
			 echartTypeo4.setType("已完成");
			 echartTypeo4.setValue("0");
			
			// 未提交 审核中   已销毁 已完成 督办 总数
			 
			 List<Object> listValue=new ArrayList<>();
			 listValue.add(0,echartTypeo1);
			 listValue.add(1,echartTypeo2);
			 listValue.add(2,echartTypeo3);
			 listValue.add(3,echartTypeo4);
			 
			 for (EchartType echartType3 : echartTypeList) {
				    if(echartType3.getType().equals("未提交")) {
				    	listValue.set(0,echartType3);
				    }else if (echartType3.getType().equals("审核中")) {
				    	listValue.set(1,echartType3);
					}
				    else if (echartType3.getType().equals("已销毁")) {
				    	listValue.set(2,echartType3);
					}
				    else if (echartType3.getType().equals("已完成")) {
				    	listValue.set(3,echartType3);
					}
			}		 
			 echartType.setType("督办");
			 Integer supcount=plmActService.countActBySup(map);
			 if(supcount==null) {
				 supcount=0;
			 }
			 echartType.setValue(String.valueOf(supcount));
			 listValue.add(4,echartType);
			 
			 echartType2.setType("总数");
			 Integer zcount=plmActService.countAct();
             if(zcount==null) {
           	  zcount=0;
			 }
			 echartType2.setValue(String.valueOf(zcount));
			 listValue.add(5,echartType2);
			    
			JSONArray jsondata = JSONArray.fromObject(listValue); 
			
	
			return jsondata;
	}	
	/**
	 * 各流程各状态数量
	 * @author xue
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"countActByStatusType"})
	public JSONArray countActByStatusType( HttpServletRequest request, HttpServletResponse response) {
		    List<Object> listValue=new ArrayList<>();
		    List<Object> listName=new ArrayList<>();
		  
		  
				
		   Map<String, Object> map=new  HashMap<String, Object>();
		   List<EchartType>  echartTypeListAll= plmActService.countActByType(map);	
		    map.put("status", "01"); 
			List<EchartType>  echartTypeList1= plmActService.countActByType(map);	
			 map.put("status", "02"); 
			List<EchartType>  echartTypeList2= plmActService.countActByType(map);	
			map.put("status", "03"); 
			List<EchartType>  echartTypeList3= plmActService.countActByType(map);	
			map.put("status", "04"); 
			List<EchartType>  echartTypeList4= plmActService.countActByType(map);	
			
			 
			/* Integer supcount=plmActService.countActBySup(map);	
			 if(supcount==null) {
				 supcount=0;
			 }*/
			 
			
			 for (EchartType echartType : echartTypeListAll) {
				 listName.add(echartType.getType());
			}
			 List<Object> listValue1=new ArrayList<>();
			 List<Object> listValue2=new ArrayList<>();
			 List<Object> listValue3=new ArrayList<>();
			 List<Object> listValue4=new ArrayList<>();
    
			 for (EchartType echartType : echartTypeList1) {
				 listValue1.add(echartType.getValue());
			}
			 for (EchartType echartType : echartTypeList2) {
				 listValue2.add(echartType.getValue());
			}
			 for (EchartType echartType : echartTypeList3) {
				 listValue3.add(echartType.getValue());
			}
			 for (EchartType echartType : echartTypeList4) {
				 listValue4.add(echartType.getValue());
			}
			 listValue.add(listValue1);
			 listValue.add(listValue2);
			 listValue.add(listValue3);
			 listValue.add(listValue4);
			 List<Object> list=new ArrayList<>();
		    
		    
			 list.add(listName);
			 list.add(listValue);
		  
			 
			JSONArray jsondata = JSONArray.fromObject(list); 
			
	
			return jsondata;
	}	
	
	/**
	 * 各流程数量(一年内)
	 * @author xue
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = {"countActByType"})
	public JSONArray countActByType( HttpServletRequest request, HttpServletResponse response) {
		  
				
		   Map<String, Object> map=new  HashMap<String, Object>();
		   List<EchartType>  echartTypeList= plmActService.countActByType(map);	
		   			 
			JSONArray jsondata = JSONArray.fromObject(echartTypeList); 
			
	
			return jsondata;
	}	
	
	/**
	 * 撤销申请
	 * @param plmApplicationForLeave
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "cancelApply")
	public String cancelApply(HttpServletRequest request, HttpServletResponse response) {
		String procInsId = request.getParameter("procInsId");
		if (StringUtils.isNotBlank(procInsId)) {
			plmActService.cancelApply(procInsId);
		}
		return "redirect:" + adminPath + "/act/task/todo/";
	}
	
}
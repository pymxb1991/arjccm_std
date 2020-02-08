/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.statistics.web;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.plm.statistics.entity.PlmStatisticsDetail;
import com.arjjs.ccm.modules.plm.statistics.entity.PlmStatisticsDict;
import com.arjjs.ccm.modules.plm.statistics.entity.PlmStatisticsPlan;
import com.arjjs.ccm.modules.plm.statistics.service.PlmStatisticsDetailService;
import com.arjjs.ccm.modules.plm.statistics.service.PlmStatisticsDictService;
import com.arjjs.ccm.modules.plm.statistics.service.PlmStatisticsPlanService;
import com.arjjs.ccm.modules.sys.utils.DictUtils;

/**
 * 统计首页明细Controller
 * @author liuxue
 * @version 2018-07-24
 */
@Controller
@RequestMapping(value = "${adminPath}/statistics/plmStatisticsDetail")
public class PlmStatisticsDetailController extends BaseController {

	@Autowired
	private PlmStatisticsDetailService plmStatisticsDetailService;
	@Autowired
	private PlmStatisticsDictService plmStatisticsDictService;
	@Autowired
	private PlmStatisticsPlanService plmStatisticsPlanService;
	
	// 父类id plmStatisticsPlan ip
		private String parentId;
	
	@ModelAttribute
	public PlmStatisticsDetail get(@RequestParam(required=false) String id) {
		PlmStatisticsDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmStatisticsDetailService.get(id);
		}
		if (entity == null){
			entity = new PlmStatisticsDetail();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(PlmStatisticsDetail plmStatisticsDetail, HttpServletRequest request, HttpServletResponse response, Model model,String pid) {
		if(pid==null||"".equals(pid)) {
			
			}else {
				parentId=pid;
			}
		
		
		PlmStatisticsPlan plmStatisticsPlan =plmStatisticsPlanService.get(parentId);
		model.addAttribute("PlmStatisticsPlan", plmStatisticsPlan);			
			plmStatisticsDetail.setParent(plmStatisticsPlan);			
			List<PlmStatisticsDetail>  portletDetaillist=plmStatisticsDetailService.findList(plmStatisticsDetail);	
		  
		    model.addAttribute("portletlist", portletDetaillist);
		  
		return "plm/statistics/plmStatisticsPlanCustom";
	}

	/**
	 * 预修改使用方案form
	 * @param plmStatisticsDetail
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "form")
	public String form(PlmStatisticsDetail plmStatisticsDetail, Model model) {
		model.addAttribute("plmStatisticsDetail", plmStatisticsDetail);	
		     //Content 下拉列表
				PlmStatisticsDict plmStatisticsDict =new PlmStatisticsDict();
				List<PlmStatisticsDict> plmStatisticsDictList=plmStatisticsDictService.findList(plmStatisticsDict);
				model.addAttribute("plmStatisticsDictList", plmStatisticsDictList);
		
		return "plm/statistics/plmStatisticsFormFan";
		
		
	}
	
	/**
	 * 明细编辑form
	 * @param plmStatisticsDetail
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value = "formDetail")
	public String formDetail(PlmStatisticsDetail plmStatisticsDetail, Model model) {
		  PlmStatisticsPlan plmStatisticsPlan=plmStatisticsPlanService.get(parentId);
		   model.addAttribute("plmStatisticsPlan", plmStatisticsPlan);	
		   plmStatisticsDetail.setParent(plmStatisticsPlan);
		   String type=plmStatisticsPlan.getType();
		   plmStatisticsDetail.setType(type);
		   model.addAttribute("plmStatisticsDetail", plmStatisticsDetail);	
		     
		
		   //Content 下拉列表
			PlmStatisticsDict plmStatisticsDict =new PlmStatisticsDict();
			List<PlmStatisticsDict> plmStatisticsDictList=plmStatisticsDictService.findList(plmStatisticsDict);
			model.addAttribute("plmStatisticsDictList", plmStatisticsDictList);
			
			//数据字典列位置列表 根据type的改变而改变
			model.addAttribute("statistics_latItudelist", DictUtils.getDictList("home_latItude"+type));
			
			
			
		
		return "plm/statistics/plmStatisticsDetailForm";
		
		
	}
	
	  
	/**
	 * 编辑门户明细信息
	 * @param plmStatisticsDetail
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */

	@RequestMapping(value = "save")
	public String save(PlmStatisticsDetail plmStatisticsDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmStatisticsDetail)){
			return form(plmStatisticsDetail, model);
		}
		
		
		
		plmStatisticsDetailService.save(plmStatisticsDetail);
		
		addMessage(redirectAttributes, "保存门户明细成功");
		return "redirect:"+Global.getAdminPath()+"/statistics/plmStatisticsDetail/?repage";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "itudeValidate")
	public String itudeValidate(String longItude,String latItude,String ylongItude,String ylatItude) {
		//得到该方案的集合		
		PlmStatisticsDetail plmStatisticsDetail=new PlmStatisticsDetail();
		PlmStatisticsPlan plmStatisticsPlan =plmStatisticsPlanService.get(parentId);
		plmStatisticsDetail.setParent(plmStatisticsPlan);			
		List<PlmStatisticsDetail>  portletDetaillist=plmStatisticsDetailService.findList(plmStatisticsDetail);
		//判断 该位置是否有窗口存在		
		String ok="1";
		if(portletDetaillist!=null&&portletDetaillist.size()>=1) {
			
			char LongItude[]=longItude.toCharArray();
			for (char c : LongItude) {
				
				Iterator<PlmStatisticsDetail> it = portletDetaillist.iterator();  
		        while(it.hasNext()){
		        	PlmStatisticsDetail plmStatisticsDetail2 = it.next();
		            if(StringUtils.isNotBlank(ylongItude)&&plmStatisticsDetail2.getLongItude().equals(ylongItude)&&plmStatisticsDetail2.getLatItude().equals(ylatItude)) {
		            	//在编辑时 遍历集合时跳过  要修改的PlmStatisticsDetail对象
					}else {
						if(plmStatisticsDetail2.getLongItude().indexOf(String.valueOf(c))!=-1||String.valueOf(c).equals(plmStatisticsDetail2.getLongItude())) {												
						String LatItude2= plmStatisticsDetail2.getLatItude();						
						if(latItude.indexOf(LatItude2)!=-1||LatItude2.indexOf(latItude)!=-1||LatItude2.equals(latItude)) {
							
							ok="0";
						}
					}
					}
																           
		        }
				
			}
	
		}
		
		return ok;
		}
	
	@ResponseBody
	@RequestMapping(value = "delete")
	public String delete(PlmStatisticsDetail plmStatisticsDetail, RedirectAttributes redirectAttributes) {
		plmStatisticsDetailService.delete(plmStatisticsDetail);
		
		return "删除成功";
	}

}
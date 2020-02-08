/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.place.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.casino.service.CcmPlaceCasinoService;
import com.arjjs.ccm.modules.ccm.catering.service.CcmPlaceCateringService;
import com.arjjs.ccm.modules.ccm.chemical.service.CcmPlaceChemicalService;
import com.arjjs.ccm.modules.ccm.education.service.CcmPlaceEducationService;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventIncident;
import com.arjjs.ccm.modules.ccm.event.service.CcmEventIncidentService;
import com.arjjs.ccm.modules.ccm.financial.service.CcmPlaceFinancialService;
import com.arjjs.ccm.modules.ccm.hospital.service.CcmPlaceHospitalService;
import com.arjjs.ccm.modules.ccm.hotel.service.CcmPlaceHotelService;
import com.arjjs.ccm.modules.ccm.live.service.CcmPlaceLiveService;
import com.arjjs.ccm.modules.ccm.place.entity.CcmBasePlace;
import com.arjjs.ccm.modules.ccm.place.service.CcmBasePlaceService;
import com.arjjs.ccm.modules.ccm.religion.service.CcmPlaceReligionService;
import com.arjjs.ccm.modules.ccm.scenic.service.CcmPlaceScenicService;
import com.arjjs.ccm.modules.ccm.traffic.service.CcmPlaceTrafficService;
import com.arjjs.ccm.modules.sys.entity.Area;
import com.arjjs.ccm.modules.sys.entity.Dict;
import com.arjjs.ccm.modules.sys.utils.DictUtils;
import com.arjjs.ccm.tool.CommUtil;
import com.arjjs.ccm.tool.EchartType;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * 场所基本信息表Controller
 * 
 * @author lgh
 * @version 2019-04-23
 */
@Controller
@RequestMapping(value = "${adminPath}/place/ccmBasePlace")
public class CcmBasePlaceController extends BaseController {

	@Autowired
	private CcmBasePlaceService ccmBasePlaceService;
	@Autowired
	private CcmPlaceLiveService ccmPlaceLiveService;
	@Autowired
	private CcmPlaceCasinoService ccmPlaceCasinoService;
	@Autowired
	private CcmPlaceEducationService ccmPlaceEducationService;
	@Autowired
	private CcmPlaceHospitalService ccmPlaceHospitalService;
	@Autowired
	private CcmPlaceCateringService ccmPlaceCateringService;
	@Autowired
	private CcmPlaceTrafficService ccmPlaceTrafficService;
	@Autowired
	private CcmPlaceHotelService ccmPlaceHotelService;
	@Autowired
	private CcmPlaceScenicService ccmPlaceScenicService;
	@Autowired
	private CcmPlaceFinancialService ccmPlaceFinancialService;
	@Autowired
	private CcmPlaceReligionService ccmPlaceReligionService;
	@Autowired
	private CcmPlaceChemicalService ccmPlaceChemicalService;
	@Autowired
	private CcmEventIncidentService ccmEventIncidentService;
	@ModelAttribute
	public CcmBasePlace get(@RequestParam(required = false) String id) {
		CcmBasePlace entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmBasePlaceService.get(id);
		}
		if (entity == null) {
			entity = new CcmBasePlace();
		}
		return entity;
	}

	@RequiresPermissions("place:ccmBasePlace:view")
	@RequestMapping(value = { "list", "" })
	public String list(CcmBasePlace ccmBasePlace, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<CcmBasePlace> page = ccmBasePlaceService.findPage(new Page<CcmBasePlace>(request, response), ccmBasePlace);
		model.addAttribute("page", page);
		return "ccm/place/ccmBasePlaceList";
	}

	@RequestMapping(value = "emphasisList")
	public String emphasisList(CcmBasePlace ccmBasePlace, HttpServletRequest request, HttpServletResponse response,
			Model model) {

		Page<CcmBasePlace> page = ccmBasePlaceService.findPage(new Page<CcmBasePlace>(request, response), ccmBasePlace);

		model.addAttribute("page", page);
		return "ccm/place/emphasis/ccmBasePlaceList";
	}

	@RequiresPermissions("place:ccmBasePlace:view")
	@RequestMapping(value = "form")
	public String form(CcmBasePlace ccmBasePlace, Model model) {
		model.addAttribute("ccmBasePlace", ccmBasePlace);
		return "ccm/place/ccmBasePlaceForm";
	}

	@RequestMapping(value = "emphasisForm")
	public String emphasisForm(CcmBasePlace ccmBasePlace, Model model) {
		/*
		 * if("ccm_place_live".equals(ccmBasePlace.getPlaceType())){//1.生活配套 url =
		 * "redirect:" + Global.getAdminPath() +
		 * "/place/ccmBasePlace/?id="+ccmBasePlace.getJuniorId(); }else
		 * if("ccm_place_casino".equals(ccmBasePlace.getPlaceType())) {//2.娱乐场所 url =
		 * "redirect:" + Global.getAdminPath() +
		 * "/place/ccmBasePlace/?id="+ccmBasePlace.getJuniorId(); }else
		 * if("ccm_place_education".equals(ccmBasePlace.getPlaceType())) {//3.文化教育 url =
		 * "redirect:" + Global.getAdminPath() +
		 * "/place/ccmBasePlace/?id="+ccmBasePlace.getJuniorId(); }else
		 * if("ccm_place_hospital".equals(ccmBasePlace.getPlaceType())) {//4.医疗卫生 url =
		 * "redirect:" + Global.getAdminPath() +
		 * "/place/ccmBasePlace/?id="+ccmBasePlace.getJuniorId(); }else
		 * if("ccm_place_catering".equals(ccmBasePlace.getPlaceType())) {//5.餐饮单位 url =
		 * "redirect:" + Global.getAdminPath() +
		 * "/place/ccmBasePlace/?id="+ccmBasePlace.getJuniorId(); }else
		 * if("ccm_place_traffic".equals(ccmBasePlace.getPlaceType())) {//6.交通出行 url =
		 * "redirect:" + Global.getAdminPath() +
		 * "/place/ccmBasePlace/?id="+ccmBasePlace.getJuniorId(); }else
		 * if("ccm_place_hotel".equals(ccmBasePlace.getPlaceType())) {//7.酒店住宿 url =
		 * "redirect:" + Global.getAdminPath() +
		 * "/place/ccmBasePlace/?id="+ccmBasePlace.getJuniorId(); }else
		 * if("ccm_place_scenic".equals(ccmBasePlace.getPlaceType())) {//8.旅游景点 url =
		 * "redirect:" + Global.getAdminPath() +
		 * "/place/ccmBasePlace/?id="+ccmBasePlace.getJuniorId(); }else
		 * if("ccm_place_financial".equals(ccmBasePlace.getPlaceType())) {//9.金融机构 url =
		 * "redirect:" + Global.getAdminPath() +
		 * "/place/ccmBasePlace/?id="+ccmBasePlace.getJuniorId(); }else
		 * if("10".equals(ccmBasePlace.getPlaceType())) {//10.安全机构 url = "redirect:" +
		 * Global.getAdminPath() +
		 * "/place/ccmBasePlace/?id="+ccmBasePlace.getJuniorId(); }else
		 * if("ccm_place_chemical".equals(ccmBasePlace.getPlaceType())) {//11.其他 url =
		 * "redirect:" + Global.getAdminPath() +
		 * "/place/ccmBasePlace/?id="+ccmBasePlace.getJuniorId(); }
		 */
		return "ccm/place/emphasis/ccmBasePlaceForm";
	}

	@RequiresPermissions("place:ccmBasePlace:view")
	@RequestMapping(value = "form/common")
	public String formCommon(CcmBasePlace ccmBasePlace, Model model) {
		model.addAttribute("ccmBasePlace", ccmBasePlace);
		return "ccm/place/ccmBasePlaceFormCommon";
	}

	@RequiresPermissions("place:ccmBasePlace:edit")
	@RequestMapping(value = "save")
	public void save(CcmBasePlace ccmBasePlace, Model model, RedirectAttributes redirectAttributes,HttpServletResponse response) throws IOException {
		if (!beanValidator(model, ccmBasePlace)) {
			//return form(ccmBasePlace, model);
		}
		/*
		 * // 获取场所类型 String placeType = ccmBasePlace.getPlaceType(); String superUuid =
		 * ""; String juniorUuid = ""; // 新增 if (null == ccmBasePlace.getId() ||
		 * "".equals(ccmBasePlace.getId())) { superUuid = UUID.randomUUID().toString();
		 * ccmBasePlace.setId(superUuid); juniorUuid = UUID.randomUUID().toString(); //
		 * 生活配套 if (placeType.equals("ccm_place_live")) { CcmPlaceLive ccmPlaceLive =
		 * new CcmPlaceLive(); ccmPlaceLive.setId(juniorUuid);
		 * ccmPlaceLive.setType(ccmBasePlace.getChildType());
		 * ccmPlaceLive.setBasePlaceId(superUuid); ccmPlaceLive.setIsNewRecord(true);
		 * ccmPlaceLiveService.save(ccmPlaceLive); }
		 * 
		 * // 娱乐场所 if (placeType.equals("ccm_place_casino")) { CcmPlaceCasino
		 * ccmPlaceCasino = new CcmPlaceCasino(); ccmPlaceCasino.setId(juniorUuid);
		 * ccmPlaceCasino.setType(ccmBasePlace.getChildType());
		 * ccmPlaceCasino.setBasePlaceId(superUuid);
		 * ccmPlaceCasino.setIsNewRecord(true);
		 * ccmPlaceCasinoService.save(ccmPlaceCasino); }
		 * 
		 * // 文化教育 if (placeType.equals("ccm_place_education")) { CcmPlaceEducation
		 * ccmPlaceEducation = new CcmPlaceEducation();
		 * ccmPlaceEducation.setId(juniorUuid);
		 * ccmPlaceEducation.setType(ccmBasePlace.getChildType());
		 * ccmPlaceEducation.setBasePlaceId(superUuid);
		 * ccmPlaceEducation.setIsNewRecord(true);
		 * ccmPlaceEducationService.save(ccmPlaceEducation); }
		 * 
		 * // 医疗中心 if (placeType.equals("ccm_place_hospital")) { CcmPlaceHospital
		 * ccmPlaceHospital = new CcmPlaceHospital();
		 * ccmPlaceHospital.setId(juniorUuid);
		 * ccmPlaceHospital.setType(ccmBasePlace.getChildType());
		 * ccmPlaceHospital.setBasePlaceId(superUuid);
		 * ccmPlaceHospital.setIsNewRecord(true);
		 * ccmPlaceHospitalService.save(ccmPlaceHospital); }
		 * 
		 * // 餐饮单位 if (placeType.equals("ccm_place_catering")) { CcmPlaceCatering
		 * ccmPlaceCatering = new CcmPlaceCatering();
		 * ccmPlaceCatering.setId(juniorUuid);
		 * ccmPlaceCatering.setType(ccmBasePlace.getChildType());
		 * ccmPlaceCatering.setBasePlaceId(superUuid);
		 * ccmPlaceCatering.setIsNewRecord(true);
		 * ccmPlaceCateringService.save(ccmPlaceCatering); }
		 * 
		 * // 出行交通 if (placeType.equals("ccm_place_traffic")) { CcmPlaceTraffic
		 * ccmPlaceTraffic = new CcmPlaceTraffic(); ccmPlaceTraffic.setId(juniorUuid);
		 * ccmPlaceTraffic.setType(ccmBasePlace.getChildType());
		 * ccmPlaceTraffic.setBasePlaceId(superUuid);
		 * ccmPlaceTraffic.setIsNewRecord(true);
		 * ccmPlaceTrafficService.save(ccmPlaceTraffic); }
		 * 
		 * // 酒店住宿 if (placeType.equals("ccm_place_hotel")) { CcmPlaceHotel
		 * ccmPlaceHotel = new CcmPlaceHotel(); ccmPlaceHotel.setId(juniorUuid);
		 * ccmPlaceHotel.setType(ccmBasePlace.getChildType());
		 * ccmPlaceHotel.setBasePlaceId(superUuid); ccmPlaceHotel.setIsNewRecord(true);
		 * ccmPlaceHotelService.save(ccmPlaceHotel); } // 旅游景点 if
		 * (placeType.equals("ccm_place_scenic")) { CcmPlaceScenic ccmPlaceScenic = new
		 * CcmPlaceScenic(); ccmPlaceScenic.setId(juniorUuid);
		 * ccmPlaceScenic.setType(ccmBasePlace.getChildType());
		 * ccmPlaceScenic.setBasePlaceId(superUuid);
		 * ccmPlaceScenic.setIsNewRecord(true);
		 * ccmPlaceScenicService.save(ccmPlaceScenic); }
		 * 
		 * // 金融机构 if (placeType.equals("ccm_place_financial")) { CcmPlaceFinancial
		 * ccmPlaceFinancial = new CcmPlaceFinancial();
		 * ccmPlaceFinancial.setId(juniorUuid);
		 * ccmPlaceFinancial.setType(ccmBasePlace.getChildType());
		 * ccmPlaceFinancial.setBasePlaceId(superUuid);
		 * ccmPlaceFinancial.setIsNewRecord(true);
		 * ccmPlaceFinancialService.save(ccmPlaceFinancial); }
		 * 
		 * // 宗教组织 if (placeType.equals("ccm_place_religion")) { CcmPlaceReligion
		 * ccmPlaceReligion = new CcmPlaceReligion();
		 * ccmPlaceReligion.setId(juniorUuid);
		 * ccmPlaceReligion.setType(ccmBasePlace.getChildType());
		 * ccmPlaceReligion.setBasePlaceId(superUuid);
		 * ccmPlaceReligion.setIsNewRecord(true);
		 * ccmPlaceReligionService.save(ccmPlaceReligion); }
		 * 
		 * // 其他 if (placeType.equals("ccm_place_chemical")) { CcmPlaceChemical
		 * ccmPlaceChemical = new CcmPlaceChemical();
		 * ccmPlaceChemical.setId(juniorUuid);
		 * ccmPlaceChemical.setType(ccmBasePlace.getChildType());
		 * ccmPlaceChemical.setBasePlaceId(superUuid);
		 * ccmPlaceChemical.setIsNewRecord(true);
		 * ccmPlaceChemicalService.save(ccmPlaceChemical); }
		 * 
		 * ccmBasePlace.setJuniorId(juniorUuid); ccmBasePlace.setIsNewRecord(true); }
		 */
		ccmBasePlaceService.save(ccmBasePlace);
		PrintWriter out = response.getWriter(); 
		CommUtil.openWinExpDiv(out, "保存场所信息成功");
		//return "redirect:" + Global.getAdminPath() + "/place/ccmBasePlace/?repage";
	}

	@RequiresPermissions("place:ccmBasePlace:edit")
	@RequestMapping(value = "save/common")
	public String saveCommon(CcmBasePlace ccmBasePlace, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmBasePlace)) {
			return form(ccmBasePlace, model);
		}
		if (ccmBasePlace.getId() == null && ccmBasePlace.getId().equals("")) {
			String id = UUID.randomUUID().toString();
			ccmBasePlace.setId(id);
			ccmBasePlace.setIsNewRecord(true);
			redirectAttributes.addFlashAttribute("placeId", id);
		}
		ccmBasePlaceService.save(ccmBasePlace);
		addMessage(redirectAttributes, "保存场所信息成功");
		return "redirect:" + Global.getAdminPath() + "/live/ccmPlaceLive/form/?repage";
	}

	@RequiresPermissions("place:ccmBasePlace:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmBasePlace ccmBasePlace, RedirectAttributes redirectAttributes) {
		ccmBasePlaceService.delete(ccmBasePlace);
		addMessage(redirectAttributes, "删除场所信息成功");
		return "redirect:" + Global.getAdminPath() + "/place/ccmBasePlace/?repage";
	}

	@RequestMapping(value = "emphasisDelete")
	public String emphasisDelete(CcmBasePlace ccmBasePlace, RedirectAttributes redirectAttributes) {
		ccmBasePlaceService.delete(ccmBasePlace);
		addMessage(redirectAttributes, "删除场所信息成功");
		return "redirect:" + Global.getAdminPath() + "/place/ccmBasePlace/emphasisList?repage";
	}
	

	@RequestMapping(value = "geteventList")
	@ResponseBody
    public Object geteventList(String areaid) {
		CcmBasePlace entity = null;
		if (StringUtils.isNotBlank(areaid)) {
			entity = ccmBasePlaceService.get(areaid);
		}
		if (entity != null && entity.getAdministrativeDivision()!=null) {
			CcmEventIncident CcmEventIncident = new CcmEventIncident();
			Area area = new Area();
			area.setId(entity.getAdministrativeDivision());
			CcmEventIncident.setArea(area);
			List<CcmEventIncident> listccmEventIncident = ccmEventIncidentService.findList(CcmEventIncident);
			return listccmEventIncident;
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "getImportByKeyPoint")
	public Map<String, Object> getImportByKeyPoint(CcmBasePlace ccmBasePlace, HttpServletRequest request, HttpServletResponse response,
							   Model model) {
		// 返回对象结果
		List<EchartType> list = ccmBasePlaceService.getImportByKeyPoint();

		String[] name = null;
		String[] value = null;
		List<Map<String, Object>> listData = new ArrayList<>();
		if(list.size()==0){
			List<Dict> dictList = DictUtils.getDictList("ccm_place_important");
			name = new String[dictList.size()];
			value = new String[dictList.size()];
			for (int i = 0; i < dictList.size(); i++) {
				name[i] = dictList.get(i).getLabel();
				value[i] = "0";
				Map<String, Object> tempMap = new HashMap<>();
				//查询并添加进去数量以及title
				tempMap.put("value", value[i]);
				tempMap.put("name", name[i]);
				listData.add(tempMap);
			}
		}else{
			name = new String[list.size()];
			value = new String[list.size()];
		}


		for (int i = 0; i < list.size(); i++) {
			//赋值
			list.get(i).setType(DictUtils.getDictLabel(list.get(i).getType(), "ccm_place_important", ""));

			name[i] = list.get(i).getType();
			value[i] = list.get(i).getValue();
			Map<String, Object> tempMap = new HashMap<>();
			//查询并添加进去数量以及title
			tempMap.put("value", value[i]);
			tempMap.put("name", name[i]);
			listData.add(tempMap);
		}
		Map<String, Object> data = new HashMap<>();
		data.put("name", name);
		data.put("value", listData);
		return data;
	}




}
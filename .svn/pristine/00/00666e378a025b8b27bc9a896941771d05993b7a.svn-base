/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.overallControl.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.car.entity.CcmCarControl;
import com.arjjs.ccm.modules.ccm.car.service.CcmCarControlService;
import com.arjjs.ccm.modules.ccm.cpp.entity.CppPopVehile;
import com.arjjs.ccm.modules.ccm.cpp.service.CppPopVehileService;
import com.arjjs.ccm.modules.ccm.list.entity.CcmList;
import com.arjjs.ccm.modules.ccm.list.service.CcmListService;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.modules.ccm.pop.service.CcmPeopleService;
import com.arjjs.ccm.modules.iot.device.entity.CcmDeviceControl;
import com.arjjs.ccm.modules.iot.device.service.CcmDeviceControlService;
import com.arjjs.ccm.modules.iot.face.entity.CcmFaceControl;
import com.arjjs.ccm.modules.iot.face.service.CcmFaceControlService;
import com.arjjs.ccm.modules.iot.grabber.entity.CcmGrabberManage;
import com.arjjs.ccm.modules.iot.grabber.service.CcmGrabberManageService;
import com.arjjs.ccm.modules.iot.overallControl.entity.CcmOverallControl;
import com.arjjs.ccm.modules.sys.utils.DictUtils;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.CommUtil;
import com.google.common.base.Joiner;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 突发事件Controller
 * @author lhf
 * @version 2019-07-25
 */
@Controller
@RequestMapping(value = "${adminPath}/overallControl/ccmOverallControl")
public class CcmOverallControlController extends BaseController {

	// 人脸布控
	@Autowired
	private CcmFaceControlService ccmFaceControlService;

	// 设备布控
	@Autowired
	private CcmDeviceControlService ccmDeviceControlService;

	// 车辆布控
	@Autowired
	private CcmCarControlService ccmCarControlService;

	@Autowired
	private CcmPeopleService ccmPeopleService;

	//名单库
	@Autowired
	private CcmListService ccmListService;

	//车牌
    @Autowired
    private CppPopVehileService cppPopVehileService;

    //布控抓拍机
	@Autowired
    private CcmGrabberManageService ccmGrabberManageService;

	@ModelAttribute
	public CcmPeople get(@RequestParam(required = false) String id) {
		CcmPeople entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmPeopleService.get(id);
		}
		if (entity == null) {
			entity = new CcmPeople();
		}
		return entity;
	}

//	@ModelAttribute
//	public CcmEarlyWarning get(@RequestParam(required=false) String id) {
//		CcmEarlyWarning entity = null;
//		if (StringUtils.isNotBlank(id)){
//			entity = ccmEarlyWarningService.get(id);
//		}
//		if (entity == null){
//			entity = new CcmEarlyWarning();
//		}
//		return entity;
//	}

//	@RequiresPermissions("overallControl:ccmOverallControl:view")
//	@RequestMapping(value = {"list", ""})
//	public String list(CcmPeople ccmPeople, HttpServletRequest request, HttpServletResponse response, Model model) {
//		Page<CcmPeople> page = ccmPeopleService.findPage(new Page<CcmPeople>(request, response), ccmPeople);
//		model.addAttribute("page", page);
//		return "iot/emergencies/ccmEmergenciesList";
//	}

	@RequiresPermissions("overallControl:ccmOverallControl:edit")
	@RequestMapping(value = "form")
	public String form(CcmPeople ccmPeople,CcmList ccmList, Model model) {
		model.addAttribute("ccmPeople", ccmPeople);
		// 名单库列表
		List<CcmList> list = ccmListService.getList(ccmList);

        CcmGrabberManage ccmGrabberManage = new CcmGrabberManage();
        List<CcmGrabberManage> grabber = ccmGrabberManageService.findList(ccmGrabberManage);

        String idcard = ccmPeople.getIdent();
		List <CppPopVehile> phoneList = null;
		List <CppPopVehile> vehicleList = null;
        if(idcard!=null && idcard.length()!=0) {
			CppPopVehile cppPopVehile = new CppPopVehile();
			cppPopVehile.setIdCard(idcard);
			cppPopVehile.setType("02");//手机号
			phoneList = cppPopVehileService.findList(cppPopVehile);
			for (CppPopVehile cppPopVehile2 : phoneList) {
				cppPopVehile2.setSubType(DictUtils.getDictLabel(cppPopVehile2.getSubType(), "cpp_phone_type", ""));
			}
			cppPopVehile.setType("01");//车牌号
			vehicleList = cppPopVehileService.findList(cppPopVehile);
			for (CppPopVehile cppPopVehile2 : vehicleList) {
				cppPopVehile2.setSubType(DictUtils.getDictLabel(cppPopVehile2.getSubType(), "cpp_vehile_type", ""));
			}
        }
		model.addAttribute("phoneList",phoneList);
		model.addAttribute("vehicleList",vehicleList);
		model.addAttribute("libraryList",list);
        model.addAttribute("grabberList",grabber);
		return "iot/overallControl/ccmOverallControlForm";
	}




	private String getAgeByBirthday(java.util.Date brithday){
		if (brithday == null) {
			return "";
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(brithday);
		int year = cal.get(Calendar.YEAR);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new java.util.Date());
		int year2 = cal2.get(Calendar.YEAR);
		year2 = year2 - year;
		return String.valueOf(year2);
	}


	@RequiresPermissions("overallControl:ccmOverallControl:edit")
	@RequestMapping(value = "save")
	public void save(String id ,CcmOverallControl ccmOverallControl, Model model, RedirectAttributes redirectAttributes,HttpServletResponse response) {
//		if (!beanValidator(model, ccmOverallControl)){
//			return form(ccmOverallControl, model);
//		}

		if(ccmOverallControl.isFace()){
			CcmFaceControl ccmFaceControl = new CcmFaceControl();
			List<String> librarySelectList = ccmOverallControl.getLibrarySelect();
			if(librarySelectList!=null&&librarySelectList.size()>0){
				String librarySelect = Joiner.on(",").join(librarySelectList);
				ccmFaceControl.setListId(librarySelect);
			}
			ccmFaceControl.setName(ccmOverallControl.getControlName());
			ccmFaceControl.setStartTime(ccmOverallControl.getStartDate());
			ccmFaceControl.setEndTime(ccmOverallControl.getEndDate());
			ccmFaceControl.setControllerLevel(ccmOverallControl.getControlGrade());
			List<String> captureMachineList = ccmOverallControl.getCaptureMachine();
			if(captureMachineList!=null&&captureMachineList.size()>0){
				String captureMachine = Joiner.on(",").join(captureMachineList);
				ccmFaceControl.setMachine(captureMachine);
			}
			ccmFaceControl.setControllerReason(ccmOverallControl.getReason());
			ccmFaceControl.setIdent(ccmOverallControl.getIdent());
			//保存人脸信息
			ccmFaceControlService.save(ccmFaceControl);
		}

		if(ccmOverallControl.isDevice()){
			//保存手机信息
			CcmDeviceControl ccmDeviceControl = new CcmDeviceControl();
			ccmDeviceControl.setAge(ccmOverallControl.getAge());
			ccmDeviceControl.setControlRange(ccmOverallControl.getPhoneRange());
			ccmDeviceControl.setSex(ccmOverallControl.getSex());
			ccmDeviceControl.setControlBy("4");
			ccmDeviceControl.setStartDate(ccmOverallControl.getStartDate());
			ccmDeviceControl.setEndDate(ccmOverallControl.getEndDate());
			ccmDeviceControl.setIdCard(ccmOverallControl.getIdent());
			ccmDeviceControl.setName(ccmOverallControl.getName());
			ccmDeviceControl.setGrade(ccmOverallControl.getControlGrade());
			ccmDeviceControl.setReason(ccmOverallControl.getReason());
			ccmDeviceControl.setControlType(ccmOverallControl.getPhoneType());
			List<String> telephoneList = ccmOverallControl.getTelephone();
			if(telephoneList!=null&&telephoneList.size()>0){
				String telephone = Joiner.on(",").join(telephoneList);
				ccmDeviceControl.setPhones(telephone);
			}
			ccmDeviceControlService.save(ccmDeviceControl);
		}

		if(ccmOverallControl.isCar()){
			//保存车辆信息
			CcmCarControl ccmCarControl = new CcmCarControl();
			ccmCarControl.setStartTime(ccmOverallControl.getStartDate());
			ccmCarControl.setEndTime(ccmOverallControl.getEndDate());
			ccmCarControl.setIdent(ccmOverallControl.getIdent());
			ccmCarControl.setControlReason(ccmOverallControl.getReason());
			ccmCarControl.setControlType(ccmOverallControl.getVehicleType());
			List<String> vehicleNumberList = ccmOverallControl.getVehicleNumber();
			if(vehicleNumberList!=null&&vehicleNumberList.size()>0){
				String vehicleNumber = Joiner.on(",").join(vehicleNumberList);
				ccmCarControl.setPlateNumber(vehicleNumber);
			}
			ccmCarControlService.save(ccmCarControl);
		}

		//设置人员为已布控
		CcmPeople ccmPeople = ccmPeopleService.get(id);
		ccmPeople.setUpdateBy(UserUtils.getUser());
		ccmPeople.setUpdateDate(new Date());
		ccmPeople.setIsControl("1");
		ccmPeopleService.updatePeople(ccmPeople);

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CommUtil.openWinExpDiv(out, "保存布控信息成功");
	}

	@RequiresPermissions("overallControl:ccmOverallControl:edit")
	@RequestMapping(value = "deleteAllControl")
	public String deleteAllControl(String id , String ident,CcmFaceControl ccmFaceControl,CcmDeviceControl ccmDeviceControl,CcmCarControl ccmCarControl, RedirectAttributes redirectAttributes) {
		ccmFaceControlService.deleteByIdent(ccmFaceControl);
		ccmDeviceControl.setIdCard(ident);
		ccmDeviceControlService.deleteByIdent(ccmDeviceControl);
		ccmCarControlService.deleteByIdent(ccmCarControl);
		CcmPeople ccmPeople = ccmPeopleService.get(id);
		ccmPeople.setUpdateBy(UserUtils.getUser());
		ccmPeople.setUpdateDate(new Date());
		ccmPeople.setIsControl("0");
		ccmPeopleService.updatePeople(ccmPeople);
		addMessage(redirectAttributes, "删除该人员所有布控成功");
		return "redirect:"+Global.getAdminPath()+"/pop/ccmPeople/?repage";
	}

}
/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.ccmsys.web;

import com.arjjs.ccm.common.beanvalidator.BeanValidators;
import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.DateUtils;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.utils.excel.ExportExcel;
import com.arjjs.ccm.common.utils.excel.ImportExcel;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmDevice;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmDeviceArea;
import com.arjjs.ccm.modules.ccm.ccmsys.service.CcmDeviceService;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgArea;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgAreaService;
import com.arjjs.ccm.modules.sys.entity.Area;
import com.arjjs.ccm.tool.EchartType;
import com.arjjs.ccm.tool.Tool;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.*;

/**
 * 设备管理Controller
 * @author arj
 * @version 2018-01-25
 */
@Controller
@RequestMapping(value = "${adminPath}/ccmsys/ccmDevice")
public class CcmDeviceController extends BaseController {

	@Autowired
	private CcmDeviceService ccmDeviceService;
	
	@Autowired
	private CcmOrgAreaService ccmOrgAreaService;
	
	@ModelAttribute
	public CcmDevice get(@RequestParam(required=false) String id) {
		CcmDevice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmDeviceService.get(id);
		}
		if (entity == null){
			entity = new CcmDevice();
		}
		return entity;
	}
	
	@RequiresPermissions("ccmsys:ccmDevice:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmDevice ccmDevice, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmDevice> page = ccmDeviceService.findPage(new Page<CcmDevice>(request, response), ccmDevice); 
		model.addAttribute("page", page);
		return "ccm/ccmsys/ccmDeviceList";
	}

	@RequiresPermissions("ccmsys:ccmDevice:view")
	@RequestMapping(value = "form")
	public String form(CcmDevice ccmDevice, Model model) {
		model.addAttribute("ccmDevice", ccmDevice);
		return "ccm/ccmsys/ccmDeviceForm";
	}

	@RequiresPermissions("ccmsys:ccmDevice:edit")
	@RequestMapping(value = "save")
	public String save(CcmDevice ccmDevice, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmDevice)){
			return form(ccmDevice, model);
		}
		ccmDeviceService.save(ccmDevice);
		addMessage(redirectAttributes, "保存设备成功");
		return "redirect:"+Global.getAdminPath()+"/ccmsys/ccmDevice/?repage";
	}
	
	@RequiresPermissions("ccmsys:ccmDevice:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmDevice ccmDevice, RedirectAttributes redirectAttributes) {
		ccmDeviceService.delete(ccmDevice);
		addMessage(redirectAttributes, "删除设备成功");
		return "redirect:"+Global.getAdminPath()+"/ccmsys/ccmDevice/?repage";
	}
	
	//首页地图弹页面显示摄像机
	@RequiresPermissions("ccmsys:ccmDevice:view")
	@RequestMapping(value = "getDeviceMap")
	public String getDeviceMap(CcmDevice ccmDevice, Model model) {
		model.addAttribute("ccmDevice", ccmDevice);
		return "ccm/ccmsys/ccmDeviceFormMap";
	}
	/**
	 * 导出网络设备数据
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ccmsys:ccmDevice:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(CcmDevice ccmDevice, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "网络设备数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			List<CcmDevice> list = ccmDeviceService.findList(ccmDevice);
			System.out.println(list.size());
			new ExportExcel("网络设备数据", CcmDevice.class).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出网络设备数据失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/ccmsys/ccmDevice/?repage";
	}

	/**
	 * 导入网络设备数据
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ccmsys:ccmDevice:view")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/ccmsys/ccmDevice/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<CcmDevice> list = ei.getDataList(CcmDevice.class);
			for (CcmDevice ccmDevice : list) {
				try {

					BeanValidators.validateWithException(validator, ccmDevice);
					ccmDeviceService.save(ccmDevice);
					successNum++;
				} catch (ConstraintViolationException ex) {
					failureMsg.append("<br/>网络设备类型 " + ccmDevice.getName() + " 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg.append(message + "; ");
						failureNum++;
					}
				} catch (Exception ex) {
					failureMsg.append("<br/>登录名 " + ccmDevice.getName() + " 导入失败：" + ex.getMessage());
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条网络设备，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条网络设备" + failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入网络设备数据失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/ccmsys/ccmDevice/?repage";
	}
	
	@RequiresPermissions("ccmsys:ccmDevice:view")
	@RequestMapping(value = "updateDeviceArea", method = RequestMethod.POST)
	public String updateDeviceArea(CcmDevice ccmDevice, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		List<CcmOrgArea> orgAreaList = ccmOrgAreaService.getAreaMap(new CcmOrgArea());
		List<CcmDeviceArea> resultList = Lists.newArrayList();
		this.sortList(resultList, orgAreaList, "0", true);
		List<String> pointList = Lists.newArrayList();
		Page<CcmDevice> page = ccmDeviceService.findPage(new Page<CcmDevice>(request, response), ccmDevice);
		List<CcmDevice> deviceList = page.getList();
		for (CcmDevice ccmDeviceInfo : deviceList) {
			String coordinate = ccmDeviceInfo.getCoordinate();
			if(StringUtils.isNotBlank(coordinate)) {
				String[] pointInfo = coordinate.split(",");
				double lat = Double.valueOf(pointInfo[0]);
				double lon = Double.valueOf(pointInfo[1]);
				String areaId = this.getDeviceAreaId(resultList, pointList, lat, lon);
				if(StringUtils.isNotBlank(areaId)) {
					Area area = new Area();
					area.setId(areaId);
					ccmDeviceInfo.setArea(area);
					ccmDeviceService.updateDeviceArea(ccmDeviceInfo);
				}
			}
		}
		return "redirect:" + Global.getAdminPath() + "/ccmsys/ccmDevice/?repage";
	}
	
	public void sortList(List<CcmDeviceArea> list, List<CcmOrgArea> sourcelist, String parentId, boolean cascade) {
		for(int i = 0; i < sourcelist.size(); ++i) {
			CcmOrgArea e = (CcmOrgArea)sourcelist.get(i);
			if (e.getAreaParentId() != null && e.getAreaParentId().equals(parentId)) {
				CcmDeviceArea ccmDeviceArea = new CcmDeviceArea();
				ccmDeviceArea.setId(e.getAreaId());
				ccmDeviceArea.setAreaMap(e.getAreaMap());
				List<CcmDeviceArea> childrenList = Lists.newArrayList();
				ccmDeviceArea.setChildrenList(childrenList);
				list.add(ccmDeviceArea);
				if (cascade) {
					for(int j = 0; j < sourcelist.size(); ++j) {
						CcmOrgArea child = (CcmOrgArea)sourcelist.get(j);
						if (child.getAreaParentId() != null && child.getAreaParentId().equals(e.getAreaId())) {
							sortList(childrenList, sourcelist, e.getAreaId(), true);
							break;
						}
					}
				}
			}
		}
	}
	
	public String getDeviceAreaId(List<CcmDeviceArea> resultList, List<String> pointList, double lat, double lon) {
		String areaId = null;
		for (int i = 0; i < resultList.size(); i++) {
			CcmDeviceArea ccmDeviceArea = resultList.get(i);
			if(ccmDeviceArea != null) {
				List<CcmDeviceArea> childrenList = ccmDeviceArea.getChildrenList();
				if(childrenList.size() > 0) {
					areaId = getDeviceAreaId(childrenList,pointList,lat,lon);
					if(StringUtils.isBlank(areaId)) {
						areaId = isInPolygon(pointList, ccmDeviceArea, lat, lon);
					}
				}else {
					areaId = isInPolygon(pointList, ccmDeviceArea, lat, lon);
				}
				if(StringUtils.isNotBlank(areaId)) {
					break;
				}
			}
		}
		return areaId;
	}
	
	public String isInPolygon(List<String> pointList,CcmDeviceArea ccmDeviceArea, double lat, double lon) {
		boolean flag = false;
		String areaId = null;
		String areaMap = ccmDeviceArea.getAreaMap();
		if(StringUtils.isNotBlank(areaMap)) {
			String[] point = areaMap.split(";");
			pointList.addAll(Arrays.asList(point));
			double[] latList = new double[pointList.size()];
			double[] lonList = new double[pointList.size()];
			for(int i = 0; i < pointList.size(); i++) {
				String[] pointInfo = pointList.get(i).split(",");
				if(pointInfo.length==2){
					latList[i] = Double.valueOf(pointInfo[0]);
					lonList[i] = Double.valueOf(pointInfo[1]);
				}
			}
			flag = Tool.isInPolygon(lon, lat, lonList, latList);
			if(flag) {
				areaId = ccmDeviceArea.getId();
			}
		}
		return areaId;
	}
	//	//<!-- 视频安装范围分类 -->
	@ResponseBody
	@RequestMapping(value = "selectByInstallType")
	public List<EchartType> selectByInstallType() {
		return ccmDeviceService.selectByInstallType();
	}


	//<!-- 视频每年布控趋势 -->
	@ResponseBody
	@RequestMapping(value = "selectByCreateDate")
	public Map<String,Object> selectByCreateDate() {
		Map map = Maps.newHashMap();
		List<EchartType> list = ccmDeviceService.selectByCreateDate();
		List<String> listdata = Lists.newArrayList();
		for (EchartType echartType : list) {
			listdata.add(echartType.getType());
		}
		map.put("data",listdata);
		map.put("value",list);
		return map;
	}
	//<!-- 监控设备类型 -->
	@ResponseBody
	@RequestMapping(value = "selectByType")
	public List<EchartType> selectByType() {
		return ccmDeviceService.selectByType();
	}
	// <!-- 视频区域分布-->
	@ResponseBody
	@RequestMapping(value = "selectDeviceByArea")
	public Map<String,Object> selectDeviceByArea() {
		int num = 4;
		Map map = Maps.newHashMap();
		List<EchartType> list = ccmDeviceService.selectDeviceByArea();
		List<String> listdata = Lists.newArrayList();
		List<String> listtype = Lists.newArrayList();
		List<Integer> listMaxnum = Lists.newArrayList();
		Collections.sort(list, Comparator.comparing(EchartType::getValue).reversed());
//		List<EchartType> reslist = list.stream().sorted((s1, s2) -> s1.getValue().compareTo(s2.getValue())).collect(Collectors.toList());
		list.sort(Comparator.comparing(EchartType::getValue).reversed());
//		String Maxnum = list.get(0).getValue();
		int Maxnum = getMaxNumber(list.get(0).getValue());
		for (int i = 0; i < list.size(); i++) {
			if(i > num){
				break;
			}
			listdata.add(list.get(i).getValue());
			listtype.add(list.get(i).getType());
			listMaxnum.add(Maxnum);
		}
		map.put("type",listtype);
		map.put("data",listdata);
		map.put("Maxnum",listMaxnum);
		return map;
	}

	@RequestMapping(value = "mapvForm")
	public String mapvForm(CcmDevice ccmDevice, Model model) {
		model.addAttribute("ccmDevice", ccmDevice);
		return "ccm/ccmsys/ccmDeviceMapvForm";
	}

    public static int getMaxNumber(String a){
        if(a == null){
            return 0;
        }
        int len = a.length();
        if(len <= 2){
            return Integer.parseInt(a) + 10;
        } else if(len <= 4){
            return Integer.parseInt(a) + 100;
        } else {
            return Integer.parseInt(a) + 1000;
        }
    }
}
/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.pop.web;

import com.arjjs.ccm.common.beanvalidator.BeanValidators;
import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.DateUtils;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.utils.excel.ExportExcel;
import com.arjjs.ccm.common.utils.excel.ImportExcel;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseBuildmanage;
import com.arjjs.ccm.modules.ccm.house.service.CcmHouseBuildmanageService;
import com.arjjs.ccm.modules.ccm.log.entity.CcmLogTail;
import com.arjjs.ccm.modules.ccm.log.service.CcmLogTailService;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPopTenant;
import com.arjjs.ccm.modules.ccm.pop.service.CcmPeopleService;
import com.arjjs.ccm.modules.ccm.pop.service.CcmPopTenantService;
import com.arjjs.ccm.modules.sys.entity.Area;
import com.arjjs.ccm.tool.CommUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 房屋Controller
 * 
 * @author wwh
 * @version 2018-01-10
 */
@Controller
@RequestMapping(value = "${adminPath}/pop/ccmPopTenant")
public class CcmPopTenantController extends BaseController {

	@Autowired
	private CcmPopTenantService ccmPopTenantService;

	@Autowired
	private CcmLogTailService ccmLogTailService;
	@Autowired
	private CcmPeopleService ccmPeopleService;
	@Autowired
	private CcmHouseBuildmanageService ccmHouseBuildmanageService;

	@ModelAttribute
	public CcmPopTenant get(@RequestParam(required = false) String id) {
		CcmPopTenant entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmPopTenantService.get(id);
		}
		if (entity == null) {
			entity = new CcmPopTenant();
		}
		return entity;
	}

	// 楼栋-房屋-住户信息弹框
	@ResponseBody
	@RequestMapping(value = "getHousePopList")
	public List<CcmPeople> getHousePopList(CcmPopTenant ccmPopTenant, Model model) {
		// 返回对象结果
		CcmPeople ccmPeople = new CcmPeople();
		ccmPeople.setRoomId(ccmPopTenant);
		List<CcmPeople> list = ccmPeopleService.getHousePopList(ccmPeople);
		String relation = null;
		for (CcmPeople li : list) {
			relation = ccmPeopleService.getRelation(li);
			if (relation == null || relation == "") {
				li.setAccountrelation("");
			} else {
				li.setAccountrelation(relation);
			}

		}
		return list;
	}

	//
	@RequiresPermissions("pop:ccmPopTenant:view")
	@RequestMapping(value = { "list", "" })
	public String list(CcmPopTenant ccmPopTenant, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<CcmPopTenant> respage = ccmPopTenantService.findListId(new Page<CcmPopTenant>(request, response), ccmPopTenant);
		List<String> idlist = Lists.newArrayList();
		respage.getList().forEach(item->{
			idlist.add(item.getId());
		});
		ccmPopTenant.setListLimite(idlist);
		Page<CcmPopTenant> pagelist = ccmPopTenantService.findList_V2(new Page<CcmPopTenant>(request, response), ccmPopTenant);
		respage.setList(pagelist.getList());
//		Page<CcmPopTenant> page = ccmPopTenantService.findPage(new Page<CcmPopTenant>(request, response), ccmPopTenant);
		model.addAttribute("page", respage);
		model.addAttribute("ccmPopTenant", ccmPopTenant);
		return "ccm/pop/ccmPopTenantList";
	}
	
	// 出租屋列表
	@RequiresPermissions("pop:ccmPopTenant:view")
	@RequestMapping(value = { "list/rent"})
	public String rentList(CcmPopTenant ccmPopTenant, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		ccmPopTenant.setHouseType("02");
		Page<CcmPopTenant> page = ccmPopTenantService.findPage(new Page<CcmPopTenant>(request, response), ccmPopTenant);
		model.addAttribute("page", page);
		model.addAttribute("ccmPopTenant", ccmPopTenant);
		return "ccm/pop/ccmPopTenantRentList";
	}
	
	
	//房屋批量添加
	@RequiresPermissions("pop:ccmPopTenant:view")
	@RequestMapping(value = "listBuildAdd")
	public String listBuildAdd(CcmPopTenant ccmPopTenant,String bId, String aId, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<CcmPopTenant> page = ccmPopTenantService.findPage(new Page<CcmPopTenant>(request, response), ccmPopTenant);
		model.addAttribute("page", page);
		model.addAttribute("ccmPopTenant", ccmPopTenant);
		model.addAttribute("bId", bId);
		model.addAttribute("aId", aId);
		return "ccm/pop/ccmPopTenantListBuildAdd";
	}

	//房屋批量添加保存
	@ResponseBody
	@RequestMapping(value = "saveBuildAdd")
	public List<String> saveBuildAdd(CcmPopTenant ccmPopTenant,String bId, String aId, Model model, RedirectAttributes redirectAttributes) {
		ccmPopTenant = ccmPopTenantService.get(ccmPopTenant.getId());
		
		CcmHouseBuildmanage ccmHouseBuildmanage = new CcmHouseBuildmanage();
		ccmHouseBuildmanage.setId(bId);
		ccmPopTenant.setBuildingId(ccmHouseBuildmanage);//楼栋
		
		Area area = new Area();
		area.setId(aId);
		ccmPopTenant.setArea(area);//网格
		
		ccmPopTenantService.save(ccmPopTenant);
		addMessage(redirectAttributes, "房屋绑定成功");
		
		List<String> list = new ArrayList<>();
		list.add(ccmPopTenant.getId());
		list.add("房屋绑定成功");
		return list;
	}
	
	
	
	// 楼栋调用
	@RequiresPermissions("pop:ccmPopTenant:view")
	@RequestMapping(value = { "listBuild" })
	public String listBuild(CcmPopTenant ccmPopTenant, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<CcmPopTenant> page = ccmPopTenantService.findPage(new Page<CcmPopTenant>(request, response), ccmPopTenant);
		model.addAttribute("page", page);
		model.addAttribute("ccmPopTenant", ccmPopTenant);
		model.addAttribute("buildingId", ccmPopTenant.getBuildingId().getId());
		model.addAttribute("buildingAreaId", ccmPopTenant.getArea().getId());
		return "ccm/pop/ccmPopTenantListBuild";
	}

	@ResponseBody
	@RequestMapping(value = "findListBuildData")
	public Object findListBuildData(CcmPopTenant ccmPopTenant) {
		return ccmPopTenantService.findListBuildData(ccmPopTenant);
	}
	// 楼栋调用
	@RequiresPermissions("pop:ccmPopTenant:view")
	@RequestMapping(value = "formBuild")
	public String formBuild(CcmPopTenant ccmPopTenant, Model model,String buildingIdId) {
		CcmHouseBuildmanage ccmHouseBuildmanage = new CcmHouseBuildmanage();
		ccmHouseBuildmanage = ccmHouseBuildmanageService.get(buildingIdId);
		ccmPopTenant.setBuildingId(ccmHouseBuildmanage);
		//model.addAttribute("ccmPopTenant", ccmPopTenant);
		// 创建 查询对象 建立查询条件
		CcmLogTail ccmLogTailDto = new CcmLogTail();
		ccmLogTailDto.setRelevanceId(ccmPopTenant.getId());
		ccmLogTailDto.setRelevanceTable("ccm_pop_tenant");
		List<CcmLogTail> ccmLogTailList = ccmLogTailService.findListByObject(ccmLogTailDto);
		// 返回查询结果
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"createBy","updateBy","currentUser","dbName","global","page","createDate","updateDate","sqlMap"});
		config.setIgnoreDefaultExcludes(false);  //设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String jsonDocumentList = JSONArray.fromObject(ccmLogTailList,config).toString(); 
		model.addAttribute("documentList", jsonDocumentList);
		model.addAttribute("documentNumber", ccmLogTailList.size());
		model.addAttribute("ccmLogTailList", ccmLogTailList);
		model.addAttribute("ccmPopTenant", ccmPopTenant);
				
		return "ccm/pop/ccmPopTenantFormBuild";
	}

	@RequiresPermissions("pop:ccmPopTenant:view")
	@RequestMapping(value = "form")
	public String form(CcmPopTenant ccmPopTenant, Model model) {
		// 创建 查询对象 建立查询条件
		CcmLogTail ccmLogTailDto = new CcmLogTail();
		ccmLogTailDto.setRelevanceId(ccmPopTenant.getId());
		ccmLogTailDto.setRelevanceTable("ccm_pop_tenant");
		List<CcmLogTail> ccmLogTailList = ccmLogTailService.findListByObject(ccmLogTailDto);
		// 返回查询结果
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"createBy","updateBy","currentUser","dbName","global","page","createDate","updateDate","sqlMap"});
		config.setIgnoreDefaultExcludes(false);  //设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String jsonDocumentList = JSONArray.fromObject(ccmLogTailList,config).toString(); 
		model.addAttribute("documentList", jsonDocumentList);
		model.addAttribute("documentNumber", ccmLogTailList.size());
		
		model.addAttribute("ccmLogTailList", ccmLogTailList);
		model.addAttribute("ccmPopTenant", ccmPopTenant);
		model.addAttribute("type", "hire");
		return "ccm/pop/ccmPopTenantForm";
	}
	
	@RequiresPermissions("pop:ccmPopTenant:view")
	@RequestMapping(value = "formByHouse")
	public String formByHouse(CcmPopTenant ccmPopTenant, Model model) {
		// 创建 查询对象 建立查询条件
		CcmLogTail ccmLogTailDto = new CcmLogTail();
		ccmLogTailDto.setRelevanceId(ccmPopTenant.getId());
		ccmLogTailDto.setRelevanceTable("ccm_pop_tenant");
		List<CcmLogTail> ccmLogTailList = ccmLogTailService.findListByObject(ccmLogTailDto);
		// 返回查询结果
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"createBy","updateBy","currentUser","dbName","global","page","createDate","updateDate","sqlMap"});
		config.setIgnoreDefaultExcludes(false);  //设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String jsonDocumentList = JSONArray.fromObject(ccmLogTailList,config).toString(); 
		model.addAttribute("documentList", jsonDocumentList);
		model.addAttribute("documentNumber", ccmLogTailList.size());
		
		model.addAttribute("ccmLogTailList", ccmLogTailList);
		model.addAttribute("ccmPopTenant", ccmPopTenant);
		model.addAttribute("type", "house");
		return "ccm/pop/ccmPopTenantForm";
	}
	
	@RequiresPermissions("pop:ccmPopTenant:view")
	@RequestMapping(value = "form/rent")
	public String rentForm(CcmPopTenant ccmPopTenant, Model model) {
		// 创建 查询对象 建立查询条件
		CcmLogTail ccmLogTailDto = new CcmLogTail();
		ccmLogTailDto.setRelevanceId(ccmPopTenant.getId());
		ccmLogTailDto.setRelevanceTable("ccm_pop_tenant");
		List<CcmLogTail> ccmLogTailList = ccmLogTailService.findListByObject(ccmLogTailDto);
		// 返回查询结果
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"createBy","updateBy","currentUser","dbName","global","page","createDate","updateDate","sqlMap"});
		config.setIgnoreDefaultExcludes(false);  //设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String jsonDocumentList = JSONArray.fromObject(ccmLogTailList,config).toString(); 
		model.addAttribute("documentList", jsonDocumentList);
		model.addAttribute("documentNumber", ccmLogTailList.size());
		
		model.addAttribute("ccmLogTailList", ccmLogTailList);
		model.addAttribute("ccmPopTenant", ccmPopTenant);
		return "ccm/pop/ccmPopTenantRentForm";
	}

	// 楼栋调用
	@RequiresPermissions("pop:ccmPopTenant:edit")
	@RequestMapping(value = "saveBuild")
	public String saveBuild(CcmPopTenant ccmPopTenant, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmPopTenant)) {
			return form(ccmPopTenant, model);
		}
		CcmHouseBuildmanage ccmHouseBuildmanage = ccmHouseBuildmanageService.get(ccmPopTenant.getBuildingId());
		ccmPopTenant.setAreaMap(ccmHouseBuildmanage.getAreaMap());
		ccmPopTenant.setAreaPoint(ccmHouseBuildmanage.getAreaPoint());
		ccmPopTenantService.saveBuild(ccmPopTenant);
		addMessage(redirectAttributes, "保存房屋成功");
		return "redirect:" + Global.getAdminPath() + "/pop/ccmPopTenant/listBuild?buildingId.id="
				+ ccmPopTenant.getBuildingId().getId() + "&area.id=" + ccmPopTenant.getArea().getId() + "&repage";
	}

	@RequiresPermissions("pop:ccmPopTenant:edit")
	@RequestMapping(value = "save")
	public String save(CcmPopTenant ccmPopTenant, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmPopTenant)) {
			return form(ccmPopTenant, model);
		}
		ccmPopTenantService.save(ccmPopTenant);
		addMessage(redirectAttributes, "保存房屋成功");
		return "redirect:" + Global.getAdminPath() + "/pop/ccmPopTenant/?repage";
	}
	
	// 出租屋保存
	@RequiresPermissions("pop:ccmPopTenant:edit")
	@RequestMapping(value = "save/rent")
	public String saveRent(CcmPopTenant ccmPopTenant, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmPopTenant)) {
			return form(ccmPopTenant, model);
		}
		ccmPopTenantService.save(ccmPopTenant);
		addMessage(redirectAttributes, "保存房屋成功");
		return "redirect:" + Global.getAdminPath() + "/pop/ccmPopTenant/list/rent?repage";
	}

	// 楼栋调用
	@RequiresPermissions("pop:ccmPopTenant:edit")
	@RequestMapping(value = "deleteBuild")
	public String deleteBuild(CcmPopTenant ccmPopTenant, RedirectAttributes redirectAttributes) {
		ccmPopTenantService.delete(ccmPopTenant);
		addMessage(redirectAttributes, "删除房屋成功");
		return "redirect:" + Global.getAdminPath() + "/pop/ccmPopTenant/listBuild?buildingId.id="
		+ ccmPopTenant.getBuildingId().getId() + "&area.id=" + ccmPopTenant.getArea().getId() + "&repage";
	}
	//批量添加的移除房屋
	@RequiresPermissions("pop:ccmPopTenant:edit")
	@RequestMapping(value = "deleteHouse")
	public String deleteHouse(String id, String buildingId, String buildingAreaId, RedirectAttributes redirectAttributes) {
		//ccmPopTenantService.delete(ccmPopTenant);
		CcmPopTenant ccmPopTenant = new CcmPopTenant();
		ccmPopTenant = ccmPopTenantService.get(id);
		
		CcmHouseBuildmanage ccmHouseBuildmanage = new CcmHouseBuildmanage();//移除楼栋ID
		ccmPopTenant.setBuildingId(ccmHouseBuildmanage);
		ccmPopTenantService.save(ccmPopTenant);
		
		addMessage(redirectAttributes, "解除房屋绑定成功");
		return "redirect:" + Global.getAdminPath() + "/pop/ccmPopTenant/listBuild?buildingId.id="+buildingId+"&area.id="+buildingAreaId+"&repage";
	}
	
	
	
	@RequiresPermissions("pop:ccmPopTenant:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmPopTenant ccmPopTenant, RedirectAttributes redirectAttributes) {
		ccmPopTenantService.delete(ccmPopTenant);
		addMessage(redirectAttributes, "删除房屋成功");
		return "redirect:" + Global.getAdminPath() + "/pop/ccmPopTenant/?repage";
	}
	
	// 出租屋删除
	@RequiresPermissions("pop:ccmPopTenant:edit")
	@RequestMapping(value = "delete/rent")
	public String deleteRent(CcmPopTenant ccmPopTenant, RedirectAttributes redirectAttributes) {
		ccmPopTenantService.delete(ccmPopTenant);
		addMessage(redirectAttributes, "删除房屋成功");
		return "redirect:" + Global.getAdminPath() + "/pop/ccmPopTenant/list/rent?repage";
	}

	/**
	 * 导出出租房屋数据
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pop:ccmPopTenant:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(CcmPopTenant ccmPopTenant, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			ccmPopTenant.setHouseType("02");
			String fileName = "房屋数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			List<CcmPopTenant> list = ccmPopTenantService.findList(ccmPopTenant);
			new ExportExcel("房屋数据", CcmPopTenant.class).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出房屋失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/pop/ccmPopTenant/list/rent?repage";
	}
	
	/**
	 * 导出全部房屋数据
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pop:ccmPopTenant:view")
	@RequestMapping(value = "exportAll", method = RequestMethod.POST)
	public String exportAllFile(CcmPopTenant ccmPopTenant, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "房屋数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			List<CcmPopTenant> list = ccmPopTenantService.findList(ccmPopTenant);
			new ExportExcel("房屋数据", CcmPopTenant.class).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出房屋失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/pop/ccmPopTenant/?repage";
	}

	/**
	 * 导入房屋数据
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("pop:ccmPopTenant:view")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/pop/ccmPopTenant/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<CcmPopTenant> list = ei.getDataList(CcmPopTenant.class);
			for (CcmPopTenant PopTenant : list) {
				try {


					BeanValidators.validateWithException(validator, PopTenant);
					ccmPopTenantService.save(PopTenant);
					successNum++;
				} catch (ConstraintViolationException ex) {
					failureMsg.append("<br/>房屋名 " + PopTenant.getHouseBuild() + " 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg.append(message + "; ");
						failureNum++;
					}
				} catch (Exception ex) {
					failureMsg.append("<br/>登录名 " + PopTenant.getHouseBuild() + " 导入失败：" + ex.getMessage());
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条房屋，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条房屋" + failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入房屋失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/pop/ccmPopTenant/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId,
			@RequestParam(required = false) String type, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<CcmPopTenant> list = ccmPopTenantService.findAreaBuildTenantList(new CcmPopTenant(), type);
		for (int i = 0; i < list.size(); i++) {
			CcmPopTenant ccmPopTenant = list.get(i);
			if ((StringUtils.isBlank(extId) || (extId != null && !extId.equals(ccmPopTenant.getId())))
					&& (type.equals("7"))) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", ccmPopTenant.getId());
				map.put("pId", ccmPopTenant.getBuildingId().getId());
				map.put("name", ccmPopTenant.getHouseBuild());
				mapList.add(map);
			}
		}
		return mapList;
	}

	/**
	 * @see 楼栋-房屋-住户信息弹框
	 * @param pilesNum
	 *            层数
	 * @param elemNum
	 *            单元数
	 * @param buildName
	 *            楼栋长姓名
	 * @param ccmPopTenant
	 *            房屋对象
	 * @param model
	 *            返回对象
	 * @return
	 */
	@RequestMapping(value = "getHouseMapPopList")
	public String getHouseMapPopList(@RequestParam(required = false) int pilesNum,
			@RequestParam(required = false) int elemNum, @RequestParam(required = false) String buildName,
			CcmPopTenant ccmPopTenant, Model model) {
		// 返回对象结果
		CcmPeople ccmPeopleDto = new CcmPeople();
		ccmPeopleDto.setRoomId(ccmPopTenant);
		// 返回查询结果
		List<CcmPeople> list = ccmPeopleService.getHousePopList(ccmPeopleDto);
		// 人口信息
		for (CcmPeople ccmPeople : list) {
			String SpePeople = CommUtil.ReturnSpecialString(ccmPeople);
			// 对more1字段 进行 重点人员赋值
			ccmPeople.setMore1(SpePeople);
		}
		//楼栋信息
		CcmHouseBuildmanage ccmHouseBuildmanage = new CcmHouseBuildmanage();
		if(ccmPopTenant.getBuildingId()!=null && !"".equals(ccmPopTenant.getBuildingId()) && ccmPopTenant.getBuildingId().getId()!=null && !"".equals(ccmPopTenant.getBuildingId().getId())){
			ccmHouseBuildmanage.setId(ccmPopTenant.getBuildingId().getId());
			ccmHouseBuildmanage = ccmHouseBuildmanageService.get(ccmHouseBuildmanage);
		}else{
			ccmHouseBuildmanage.setImages("nulls");
		}
		
		
		model.addAttribute("ccmHouseBuildmanage", ccmHouseBuildmanage);
		model.addAttribute("ccmPopTenant", ccmPopTenant);
		model.addAttribute("pilesNum", pilesNum);
		model.addAttribute("elemNum", elemNum);
		model.addAttribute("buildName", buildName);
		model.addAttribute("list", list);
		return "/modules/mapping/houseBuild/mapHouse";
	}

	@ResponseBody
	@RequestMapping(value = "findCount")
	public String findCount(CcmPopTenant ccmPopTenant,HttpServletResponse response) {
		int count = ccmPopTenantService.findListNum(ccmPopTenant);
		return String.valueOf(count);
	}

	@RequestMapping(value = "mapvForm")
	public String mapvForm(CcmPopTenant ccmPopTenant, Model model) {
		// 创建 查询对象 建立查询条件
		CcmLogTail ccmLogTailDto = new CcmLogTail();
		ccmLogTailDto.setRelevanceId(ccmPopTenant.getId());
		ccmLogTailDto.setRelevanceTable("ccm_pop_tenant");
		List<CcmLogTail> ccmLogTailList = ccmLogTailService.findListByObject(ccmLogTailDto);
		// 返回查询结果
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"createBy","updateBy","currentUser","dbName","global","page","createDate","updateDate","sqlMap"});
		config.setIgnoreDefaultExcludes(false);  //设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String jsonDocumentList = JSONArray.fromObject(ccmLogTailList,config).toString();
		model.addAttribute("documentList", jsonDocumentList);
		model.addAttribute("documentNumber", ccmLogTailList.size());

		model.addAttribute("ccmLogTailList", ccmLogTailList);
		model.addAttribute("ccmPopTenant", ccmPopTenant);
		model.addAttribute("type", "hire");
		return "ccm/pop/ccmPopTenantMapvForm";
	}
}
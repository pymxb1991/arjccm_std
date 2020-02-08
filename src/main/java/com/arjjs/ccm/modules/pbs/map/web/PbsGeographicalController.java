/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.map.web;

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
import com.arjjs.ccm.modules.pbs.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.map.entity.BindCount;
import com.arjjs.ccm.modules.pbs.map.entity.PbsGeographical;
import com.arjjs.ccm.modules.pbs.map.service.PbsGeographicalService;
import com.arjjs.ccm.modules.pbs.person.entity.PbsDepartmentbind;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.person.service.PbsDepartmentbindService;
import com.arjjs.ccm.modules.pbs.person.service.PbsPartymemService;
import com.arjjs.ccm.modules.pbs.sys.entity.PbsDepartmentetc;
import com.arjjs.ccm.modules.pbs.sys.service.PbsDepartmentetcService;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.service.OfficeService;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;
import com.google.common.collect.Maps;

/**
 * 地图信息表Controller
 * 
 * @author lc
 * @version 2018-04-02
 */
@Controller
@RequestMapping(value = "${adminPath}/map/pbsGeographical")
public class PbsGeographicalController extends BaseController {

	// 地理信息services
	@Autowired
	private PbsGeographicalService pbsGeographicalService;
	// 办公室services
	@Autowired
	private OfficeService officeService;
	// 党员services
	@Autowired
	private PbsPartymemService pbsPartymemService;
	// 部门扩展信息
	@Autowired
	private PbsDepartmentetcService pbsDepartmentetcService;
	// 用户部门关系信息
	@Autowired
	private PbsDepartmentbindService pbsDepartmentbindService;

	@ModelAttribute
	public PbsGeographical get(@RequestParam(required = false) String id) {
		PbsGeographical entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsGeographicalService.get(id);
		}
		if (entity == null) {
			entity = new PbsGeographical();
		}
		return entity;
	}

	@RequiresPermissions("map:pbsGeographical:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsGeographical pbsGeographical, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<PbsGeographical> page = pbsGeographicalService.findPage(new Page<PbsGeographical>(request, response),
				pbsGeographical);
		model.addAttribute("page", page);
		return "pbs/map/pbsGeographicalList";
	}

	@RequiresPermissions("map:pbsGeographical:view")
	@RequestMapping(value = "form")
	public String form(PbsGeographical pbsGeographical, Model model) {
		model.addAttribute("pbsGeographical", pbsGeographical);
		return "pbs/map/pbsGeographicalForm";
	}

	@RequiresPermissions("map:pbsGeographical:edit")
	@RequestMapping(value = "save")
	public String save(PbsGeographical pbsGeographical, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsGeographical)) {
			return form(pbsGeographical, model);
		}
		if(pbsGeographical !=null && "1".equals(pbsGeographical.getSType())) {
			pbsGeographical.setSPrimarykey01(pbsGeographical.getsDepartmentid().getId());
		} else if(pbsGeographical !=null && "2".equals(pbsGeographical.getSType())) {
			pbsGeographical.setSPrimarykey01(pbsGeographical.getPbsPartymem().getId());
		} else if(pbsGeographical !=null && "3".equals(pbsGeographical.getSType())){
			pbsGeographical.setSPrimarykey01(pbsGeographical.getsActivityid().getId());
		} else if(pbsGeographical !=null && "4".equals(pbsGeographical.getSType())){
			pbsGeographical.setSPrimarykey01(pbsGeographical.getsSuperiorid().getId());
		}
		//根据类型和第一个主键id查询是否被标注过
		int count = pbsGeographicalService.findByTypeAndKey(pbsGeographical);
		if(count > 0) {
			addMessage(model, "数据验证失败：已存在的标注");
			return form(pbsGeographical, model);
		}
		
		pbsGeographicalService.save(pbsGeographical);
		addMessage(redirectAttributes, "保存地图信息表成功");
		return "redirect:" + Global.getAdminPath() + "/map/pbsGeographical/?repage";
	}

	@RequiresPermissions("map:pbsGeographical:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsGeographical pbsGeographical, RedirectAttributes redirectAttributes) {
		pbsGeographicalService.delete(pbsGeographical);
		addMessage(redirectAttributes, "删除地图信息表成功");
		return "redirect:" + Global.getAdminPath() + "/map/pbsGeographical/?repage";
	}

	// 今日 地图跳转页面
	@RequestMapping(value = "toMap")
	public String ToMap(PbsGeographical pbsGeographical, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		if (StringUtils.isBlank(UserUtils.getPartymem().getId())) {
			return "redirect:" + Global.getAdminPath() + "/sys/Mobile/personInfo/?repage";
		}
		pbsGeographical.setSType("1");
		// 获取list内容
		List<PbsGeographical> List = pbsGeographicalService.findList(pbsGeographical);
		List<BindCount> countList = pbsGeographicalService.getPartymembindCount();
		Map<String, String> CountMap = Maps.newHashMap();
		// 获取值
		for (BindCount bind : countList) {
			CountMap.put(bind.getId(), bind.getSum1());
		}
		// 填充數據
		for (PbsGeographical geo : List) {
			String a = CountMap.get(geo.getSPrimarykey01());
			geo.setPeoplesum(StringUtils.isBlank(a) ? "0" : a);
		}
		model.addAttribute("List", List);
		return "/Nav-activity/maps/mapsPro";
	}

	@RequestMapping(value = "toMapOffice")
	public String ToMapOffice(String id, HttpServletRequest request, HttpServletResponse response, Model model) {
		// 如果为空 则直接返回
		if (StringUtils.isBlank(id)) {
			return "/Nav-activity/maps/mapsOffice";
		}
		// 1 查询 部门详细
		// 设置部门id 并返回部门对象信息
		Office office = officeService.get(id);
		model.addAttribute("office", office);
		// 2 查询部门 扩展信息
		PbsDepartmentetc pbsDepartmentetcDto = new PbsDepartmentetc();
		pbsDepartmentetcDto.setOffice(office);
		List<PbsDepartmentetc> pbsDepartmentetcs = pbsDepartmentetcService.findList(pbsDepartmentetcDto);
		if (pbsDepartmentetcs.size() > 0) {
			// 返回 部门扩展信息
			model.addAttribute("pbsDepartmentetcs", pbsDepartmentetcs.get(0));
		} else {
			model.addAttribute("pbsDepartmentetcs", new PbsDepartmentetc());
		}
		// 3查询部门下的所有党员信息
		PbsPartymem pbsPartymemDto = new PbsPartymem();
		pbsPartymemDto.setOfficeid(id);
		List<PbsPartymem> memList = pbsPartymemService.findList(pbsPartymemDto);
		model.addAttribute("memList", memList);
		return "/Nav-activity/maps/mapsOffice";
	}

	@RequestMapping(value = "toMapUser")
	public String ToMapUser(String id, String officeid, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 查询该用户
		PbsPartymem PbsPartymem = pbsPartymemService.get(id);
		model.addAttribute("userEnt", PbsPartymem);

		// 查询部门关系
		PbsDepartmentbind pbsDepartmentbindDto = new PbsDepartmentbind();
		pbsDepartmentbindDto.setSDepartmentid(officeid);
		pbsDepartmentbindDto.setSPartymemid(id);
		List<PbsDepartmentbind> list = pbsDepartmentbindService.findList(pbsDepartmentbindDto);
		//
		if (list.size() > 0) {
			model.addAttribute("departmentbind", list.get(0));
		} else {
			model.addAttribute("departmentbind", new PbsDepartmentbind());
		}

		return "/Nav-activity/maps/mapsUser";
	}

	// 返回json 主体
	@ResponseBody
	@RequestMapping(value = "toMapBody")
	public List<PbsGeographical> toMapBody(PbsGeographical pbsGeographical, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 获取list内容
		List<PbsGeographical> List = pbsGeographicalService.findList(pbsGeographical);
		List<BindCount> countList = pbsGeographicalService.getPartymembindCount();
		Map<String, String> CountMap = Maps.newHashMap();
		// 获取值
		for (BindCount bind : countList) {
			CountMap.put(bind.getId(), bind.getSum1());
		}
		// 填充數據
		for (PbsGeographical geo : List) {
			String a = CountMap.get(geo.getSPrimarykey01());
			geo.setPeoplesum(StringUtils.isBlank(a) ? "0" : a);
		}
		return List;
	}

	@ResponseBody
	@RequestMapping(value = "toMapOfficeBody")
	public Map<String, Object> toMapOfficeBody(String id, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// office Map
		Map<String, Object> OfficeMap = new HashMap<String, Object>();
		// 1 查询 部门详细
		// 设置部门id 并返回部门对象信息
		Office office = officeService.get(id);
		model.addAttribute("office", office);
		// 2 查询部门 扩展信息
		PbsDepartmentetc pbsDepartmentetcDto = new PbsDepartmentetc();
		pbsDepartmentetcDto.setOffice(office);
		List<PbsDepartmentetc> pbsDepartmentetcs = pbsDepartmentetcService.findList(pbsDepartmentetcDto);
		if (pbsDepartmentetcs.size() > 0) {
			// 返回 部门扩展信息
			OfficeMap.put("pbsDepartmentetcs", pbsDepartmentetcs.get(0));
		}
		// 3查询部门下的所有党员信息
		PbsPartymem pbsPartymemDto = new PbsPartymem();
		pbsPartymemDto.setOfficeid(id);
		List<PbsPartymem> memList = pbsPartymemService.findList(pbsPartymemDto);
		OfficeMap.put("memList", memList);
		return OfficeMap;
	}

	// 地图详细信息
	@RequestMapping(value = "mapInfo")
	public String mapInfo(PbsGeographical pbsGeographical, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		if (StringUtils.isBlank(UserUtils.getPartymem().getId())) {
			return "redirect:" + Global.getAdminPath() + "/sys/Mobile/personInfo/?repage";
		}
		// 获取list内容
		List<PbsGeographical> List = pbsGeographicalService.findList(pbsGeographical);
		List<BindCount> countList = pbsGeographicalService.getPartymembindCount();
		Map<String, String> CountMap = Maps.newHashMap();
		// 获取值
		for (BindCount bind : countList) {
			CountMap.put(bind.getId(), bind.getSum1());
		}
		// 填充數據
		for (PbsGeographical geo : List) {
			String a = CountMap.get(geo.getSPrimarykey01());
			geo.setPeoplesum(StringUtils.isBlank(a) ? "0" : a);
		}
		model.addAttribute("List", List);
		return "pbs/map/GeographicalMap";
	}
	@ResponseBody
	@RequestMapping(value = "getPoint")
	public List<PbsGeographical> getPoint(PbsGeographical pbsGeographical, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<PbsGeographical> page = pbsGeographicalService.findPage(new Page<PbsGeographical>(request, response),
				pbsGeographical);
		return page.getList();
	}
	
	@ResponseBody
	@RequestMapping("getNumByType")
	public List<Integer> getNumByType(){
		List<Integer> num = pbsGeographicalService.getNumByType();
		return num;
	}
}	
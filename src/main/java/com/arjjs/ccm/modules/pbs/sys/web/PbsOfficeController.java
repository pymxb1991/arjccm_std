/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.sys.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.codehaus.groovy.classgen.genArrayAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.beanvalidator.BeanValidators;
import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.DateUtils;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.utils.excel.ExportExcel;
import com.arjjs.ccm.common.utils.excel.ImportExcel;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.pbs.person.entity.PbsDepartmentbind;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.person.service.PbsDepartmentbindService;
import com.arjjs.ccm.modules.pbs.sys.entity.GeneralMethod;
import com.arjjs.ccm.modules.pbs.sys.service.PbsDepartmentetcService;
import com.arjjs.ccm.modules.pbs.sys.service.PbsGeneralService;
import com.arjjs.ccm.modules.pbs.sys.entity.PbsOffice;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.pbs.sys.service.PbsOfficeService;
import com.arjjs.ccm.modules.sys.service.SystemService;
import com.arjjs.ccm.modules.sys.utils.DictUtils;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import sun.net.www.content.text.plain;

/**
 * 机构Controller
 * 
 * @author admin001
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/pbsOffice")
public class PbsOfficeController extends BaseController {

	@Autowired
	private PbsOfficeService officeService;
	@Autowired
	private PbsDepartmentbindService pbsDepartmentbindService;
	@Autowired
	private PbsDepartmentetcService pbsDepartmentetcService;

	@ModelAttribute("office")
	public PbsOffice get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return officeService.get(id);
		} else {
			return new PbsOffice();
		}
	}

	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = { "" })
	public String index(PbsOffice office, Model model) {
		// model.addAttribute("list", officeService.findAll());
		return "modules/sys/officeIndex";
	}

	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = { "list" })
	public String list(PbsOffice office, Model model) {
		model.addAttribute("list", officeService.findList(office));
		return "modules/sys/officeList";
	}

	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = "form")
	public String form(PbsOffice office, Model model) {
		User user = UserUtils.getUser();
		if (office.getParent() == null || office.getParent().getId() == null) {
			//office.setParent(user.getOffice());
		}
		office.setParent(officeService.get(office.getParent().getId()));
		if (office.getArea() == null) {
			//office.setArea(user.getOffice().getArea());
		}
		// 自动获取排序号
		if (StringUtils.isBlank(office.getId()) && office.getParent() != null) {
			int size = 0;
			List<PbsOffice> list = officeService.findAll();
			for (int i = 0; i < list.size(); i++) {
				PbsOffice e = list.get(i);
				if (e.getParent() != null && e.getParent().getId() != null
						&& e.getParent().getId().equals(office.getParent().getId())) {
					size++;
				}
			}
			office.setCode(office.getParent().getCode()
					+ StringUtils.leftPad(String.valueOf(size > 0 ? size + 1 : 1), 3, "0"));
		}
		model.addAttribute("office", office);
		return "modules/sys/officeForm";
	}

	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "save")
	public String save(PbsOffice office, Model model, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/office/";
		}
		if (!beanValidator(model, office)) {
			return form(office, model);
		}
		officeService.save(office);

		if (office.getChildDeptList() != null) {
			PbsOffice childOffice = null;
			for (String id : office.getChildDeptList()) {
				childOffice = new PbsOffice();
				childOffice.setName(DictUtils.getDictLabel(id, "sys_office_common", "未知"));
				childOffice.setParent(office);
				childOffice.setArea(office.getArea());
				childOffice.setType("2");
				childOffice.setGrade(String.valueOf(Integer.valueOf(office.getGrade()) + 1));
				childOffice.setUseable(Global.YES);
				officeService.save(childOffice);
			}
		}

		addMessage(redirectAttributes, "保存机构'" + office.getName() + "'成功");
		String id = "0".equals(office.getParentId()) ? "" : office.getParentId();
		return "redirect:" + adminPath + "/sys/office/list?id=" + id + "&parentIds=" + office.getParentIds();
	}

	@RequiresPermissions("sys:office:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsOffice office, Model model, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/office/list";
		}
		// if (Office.isRoot(office.getId())){
		// addMessage(redirectAttributes, "删除机构失败, 不允许删除顶级机构或编号空");
		// }else{
		/**
		 * 1.如果当前的部门有下级部门 不能删除 2.如果当前的部门有相关的党员关系 不能删除
		 */
		PbsOffice officeDto = new PbsOffice();
		// 当前的部门为 id
		officeDto = office;
		officeDto.setParentIds(office.getParentIds()+office.getId());
		List<PbsOffice> officelist = officeService.findList(officeDto);
		// 如果当前的部门 有下级部门 则无法删除相关的部门
		if (officelist.size() > 1) {
			addMessage(redirectAttributes, "如果要删除该部门，当前部门不能有子部门。");
			return "redirect:" + adminPath + "/sys/office/list?id=" + office.getParentId() + "&parentIds="
					+ office.getParentIds();
		}
		// 当前的党员部门的关系如果存在 则不能删除该部门
		PbsDepartmentbind pbsDepartmentbindDto = new PbsDepartmentbind();
		// 当前的部门信息 设置
		pbsDepartmentbindDto.setSDepartmentid(office.getId());
		pbsDepartmentbindDto.setOfficeparentid(officeDto.getParentIds());
		List<PbsDepartmentbind> departmentList = pbsDepartmentbindService.findList(pbsDepartmentbindDto);
		if (departmentList.size() > 0) {
			addMessage(model, "如果要删除该部门，当前部门不能有党员部门关系。");
			return "redirect:" + adminPath + "/sys/office/list?id=" + office.getParentId() + "&parentIds="
					+ office.getParentIds();
		}

		officeService.delete(office);
		addMessage(redirectAttributes, "删除机构成功");
		// }
		return "redirect:" + adminPath + "/sys/office/list?id=" + office.getParentId() + "&parentIds="
				+ office.getParentIds();
	}

	/**
	 * 获取机构JSON数据。
	 * 
	 * @param extId
	 *            排除的ID
	 * @param type
	 *            类型（1：公司；2：部门/小组/其它：3：用户）
	 * @param grade
	 *            显示级别
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId,
			@RequestParam(required = false) String type, @RequestParam(required = false) Long grade,
			@RequestParam(required = false) Boolean isAll, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		isAll = (isAll==null)?true:isAll;
		// 默认全能搜索
		List<PbsOffice> list = officeService.findList(isAll);
		for (int i = 0; i < list.size(); i++) {
			PbsOffice e = list.get(i);
			if ((StringUtils.isBlank(extId)
					|| (extId != null && !extId.equals(e.getId()) && e.getParentIds().indexOf("," + extId + ",") == -1))
					&& (type == null || (type != null && (type.equals("1") ? type.equals(e.getType()) : true)))
					&& (grade == null || (grade != null && Integer.parseInt(e.getGrade()) <= grade.intValue()))
					&& Global.YES.equals(e.getUseable())) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("pIds", e.getParentIds());
				map.put("name", e.getName());
				if (type != null && "3".equals(type)) {
					map.put("isParent", true);
				}
				if (type != null && "4".equals(type)) {
					map.put("isParent", true);
				}
				mapList.add(map);
			}
		}
		return mapList;
	}

	@ResponseBody
	@RequestMapping(value = "partmemtreeData")
	public List<Map<String, Object>> partmemtreeData(@RequestParam(required = false) String extId,
			@RequestParam(required = false) String type, @RequestParam(required = false) Long grade,
			@RequestParam(required = false) Boolean isAll, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		// 党员
		PbsPartymem CurPartymem = UserUtils.getPartymem();
		List<PbsOffice> listOffice = new ArrayList<>();
		if (StringUtils.isNotBlank(CurPartymem.getId())) {
			// 当前的 用户 为党员
			PbsDepartmentbind pbsDepartmentbindDto = new PbsDepartmentbind();
			pbsDepartmentbindDto.setSPartymemid(CurPartymem.getId());
			List<PbsDepartmentbind> pbsDepartmentbinds = pbsDepartmentbindService.findList(pbsDepartmentbindDto);
			//
			for (PbsDepartmentbind bind : pbsDepartmentbinds) {
				PbsOffice officeNew = new PbsOffice();
				officeNew.setType("2");
				officeNew.setId(bind.getSDepartmentid());
				listOffice.add(officeNew);
			}
		}
		List<PbsOffice> list = pbsDepartmentetcService.getAllOfficeWithMem(listOffice);
		
		for (int i = 0; i < list.size(); i++) {
			PbsOffice e = list.get(i);
			if ((StringUtils.isBlank(extId)
					|| (extId != null && !extId.equals(e.getId()) && e.getParentIds().indexOf("," + extId + ",") == -1))
					&& (type == null || (type != null && (type.equals("1") ? type.equals(e.getType()) : true)))
					&& (grade == null || (grade != null && Integer.parseInt(e.getGrade()) <= grade.intValue()))
					&& Global.YES.equals(e.getUseable())) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("pIds", e.getParentIds());
				map.put("name", e.getName());
				if (type != null && "3".equals(type)) {
					map.put("isParent", true);
				}
				if (type != null && "4".equals(type)) {
					map.put("isParent", true);
				}
				mapList.add(map);
			}
		}
		return mapList;
	}

	/**
	 * 导出部门数据
	 * 
	 * @param office
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(PbsOffice office, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			String fileName = "部门数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			List<PbsOffice> officelist = Lists.newArrayList();
			officelist = officeService.findList(office);
			// Page<User> page = systemService.findUser(new Page<User>(request,
			// response,
			// -1), user);
			// new ExportExcel("用户数据",
			// User.class).setDataList(page.getList()).write(response,
			// fileName).dispose();
			new ExportExcel("部门数据", PbsOffice.class).setDataList(officelist).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出数据失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/office/list?id=&parentIds=";
	}

	/**
	 * 导入部门数据
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/office/list?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<PbsOffice> list = ei.getDataList(PbsOffice.class);
			for (PbsOffice office : list) {
				try {
					// 检测当前的部门是否为空
					// if
					// (("false").equals(pbsGeneralService.checkExistForString(generalMethod)))
					// {
					// BeanValidators.validateWithException(validator, office);
					PbsOffice officeDto = officeService.get(office.getId());
					if (null == officeDto || null == officeDto.getId()) {
						System.out.println(office.getId());
						officeService.insert(office);
					} else {
						officeService.update(office);
					}
					successNum++;
				} catch (ConstraintViolationException ex) {
					failureMsg.append("<br/>部门名 " + office.getName() + " 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg.append(message + "; ");
						failureNum++;
					}
				} catch (Exception ex) {
					failureMsg.append("<br/>部门名 " + office.getName() + " 导入失败：" + ex.getMessage());
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条部门，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条部门" + failureMsg);
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导入部门失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/office/list?id=&parentIds=";
	}

	/**
	 * 下载导入部门数据模板
	 * 
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "部门数据导入模板.xlsx";
			List<PbsOffice> list = Lists.newArrayList();
			list.addAll(UserUtils.getOfficeList());
			new ExportExcel("部门数据", PbsOffice.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/office/list?repage";
	}
}

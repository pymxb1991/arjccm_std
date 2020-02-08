/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.view.web;

import com.arjjs.ccm.common.beanvalidator.BeanValidators;
import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.utils.DateUtils;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.utils.excel.ExportExcel;
import com.arjjs.ccm.common.utils.excel.ImportExcel;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgTeam;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgTeamService;
import com.arjjs.ccm.modules.ccm.view.entity.ImportUserVCcmTeam;
import com.arjjs.ccm.modules.ccm.view.entity.UserVCcmTeam;
import com.arjjs.ccm.modules.ccm.view.entity.VCcmOrg;
import com.arjjs.ccm.modules.ccm.view.entity.VCcmTeam;
import com.arjjs.ccm.modules.ccm.view.entity.VCcmTeamExport;
import com.arjjs.ccm.modules.ccm.view.service.VCcmOrgService;
import com.arjjs.ccm.modules.ccm.view.service.VCcmTeamService;
import com.arjjs.ccm.modules.pbs.person.service.UserService;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.Role;
import com.arjjs.ccm.modules.sys.entity.SysUserRole;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.service.SystemService;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.DateTools;
import com.arjjs.ccm.tool.EntityTools;
import com.arjjs.ccm.tool.NumberTools;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 综治队伍Controller
 * @author liang
 * @version 2018-01-12
 */
@Controller
@RequestMapping(value = "${adminPath}/view/vCcmTeam")
public class VCcmTeamController extends BaseController {

	@Autowired
	private VCcmTeamService vCcmTeamService;
	@Autowired
	private VCcmOrgService vCcmOrgService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private CcmOrgTeamService ccmOrgTeamService;
	@Autowired
	private UserService userService;

	@ModelAttribute
	public VCcmTeam get(@RequestParam(required=false) String id) {
		VCcmTeam entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = vCcmTeamService.get(id);
		}
		if (entity == null){
			entity = new VCcmTeam();
		}
		return entity;
	}
	
	@RequiresPermissions("view:vCcmTeam:view")
	@RequestMapping(value = {"index", ""})
	public String index(VCcmTeam vCcmTeam,Model model) {
		Office office = UserUtils.getUser().getOffice();
		model.addAttribute("office", office);
		return "ccm/view/vCcmTeamIndex";
	}
	
	@RequiresPermissions("view:vCcmTeam:view")
	@RequestMapping(value = {"list", ""})
	public String list(VCcmTeam vCcmTeam, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<VCcmTeam> list = vCcmTeamService.findList(vCcmTeam); 
		model.addAttribute("office", UserUtils.getUser().getOffice());
		model.addAttribute("list", list);
		model.addAttribute("vCcmTeam", vCcmTeam);
		return "ccm/view/vCcmTeamList";
	}

	//新填findform查询
	@RequiresPermissions("view:vCcmTeam:view")
	@RequestMapping(value = {"findform", ""})
	public String findform(VCcmTeam vCcmTeam, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<VCcmTeam> list = vCcmTeamService.findform(vCcmTeam); 
		model.addAttribute("office", UserUtils.getUser().getOffice());
		model.addAttribute("list", list);
		model.addAttribute("vCcmTeam", vCcmTeam);
		return "ccm/view/vCcmTeamList";
	}
	
	@RequiresPermissions("view:vCcmTeam:view")
	@RequestMapping(value = "form")
	public String form(VCcmTeam vCcmTeam, Model model) {
		if (vCcmTeam.getParent()!=null && StringUtils.isNotBlank(vCcmTeam.getParent().getId())){
			vCcmTeam.setParent(vCcmTeamService.get(vCcmTeam.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(vCcmTeam.getId())){
				VCcmTeam vCcmTeamChild = new VCcmTeam();
				vCcmTeamChild.setParent(new VCcmTeam(vCcmTeam.getParent().getId()));
				List<VCcmTeam> list = vCcmTeamService.findList(vCcmTeam); 
				if (list.size() > 0){
					vCcmTeam.setSort(list.get(list.size()-1).getSort());
					if (vCcmTeam.getSort() != null){
						vCcmTeam.setSort(vCcmTeam.getSort() + 30);
					}
				}
			}
		}
		if (vCcmTeam.getSort() == null){
			vCcmTeam.setSort(30);
		}
		model.addAttribute("office", UserUtils.getUser().getOffice());
		model.addAttribute("vCcmTeam", vCcmTeam);
		return "ccm/view/vCcmTeamForm";
	}

	@RequiresPermissions("view:vCcmTeam:edit")
	@RequestMapping(value = "save")
	public String save(VCcmTeam vCcmTeam, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, vCcmTeam)){
			return form(vCcmTeam, model);
		}
		vCcmTeamService.save(vCcmTeam);
		addMessage(redirectAttributes, "保存综治队伍成功");
		return "redirect:"+Global.getAdminPath()+"/view/vCcmTeam/?repage";
	}
	
	@RequiresPermissions("view:vCcmTeam:edit")
	@RequestMapping(value = "delete")
	public String delete(VCcmTeam vCcmTeam, RedirectAttributes redirectAttributes) {
		vCcmTeamService.delete(vCcmTeam);
		addMessage(redirectAttributes, "删除综治队伍成功");
		return "redirect:"+Global.getAdminPath()+"/view/vCcmTeam/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		//List<VCcmTeam> list = vCcmTeamService.findList(new VCcmTeam());
		List<VCcmOrg> list = vCcmOrgService.findList(new VCcmOrg());
		for (int i=0; i<list.size(); i++){
			//VCcmTeam e = list.get(i);
			VCcmOrg e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	/**
	 * 导入实有人口数据
	 *
	 * @param file
	 * @param redirectAttributes
	 * 修改：彭建强 20190522 导入数据时，能够自动添加楼栋和房屋，各类关联数据放入到内存中，减少频繁操作数据库带来的压力
	 * @return
	 */
	public static final List<ImportUserVCcmTeam> reslist = new ArrayList<ImportUserVCcmTeam>();
	@RequiresPermissions("view:vCcmTeam:view")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:"+Global.getAdminPath()+"/view/vCcmTeam/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<ImportUserVCcmTeam> list = ei.getDataList(ImportUserVCcmTeam.class);
			
			//导入过程中失败的数据，进行汇总并导出
			List<ImportUserVCcmTeam> listFailure = new ArrayList<ImportUserVCcmTeam>();
			//用于存放工号的集合
			List<String> workNolist = new ArrayList<String>();
			//用于存放登录名的集合
			List<String> loginNamelist = new ArrayList<String>();
			//获取已经存在的用户集合
			List<User> userlist = userService.findList(new User());
			for (User user : userlist) {
				workNolist.add(user.getNo());
				loginNamelist.add(user.getLoginName());
			}
			
			for (ImportUserVCcmTeam importUserVCcmTeam : list) {
				
				if(importUserVCcmTeam.getCompany() == null) {
					Office company = new Office();
					company.setId("-1");
					importUserVCcmTeam.setCompany(company);
				}

				if(EntityTools.isEmpty(importUserVCcmTeam)){
					continue;
				}
				
				//根据工号去重
				if(workNolist.contains(importUserVCcmTeam.getNo())) {
					failureMsg.append("<br/>社工姓名" + importUserVCcmTeam.getName() + " 导入失败：" + "工号已存在！");
					importUserVCcmTeam.setName(importUserVCcmTeam.getName() + "，失败原因：工号已存在！");
					listFailure.add(importUserVCcmTeam);
					failureNum++;
					continue;
				}else {
					workNolist.add(importUserVCcmTeam.getNo());
				}
				
				//根据登录名去重
				if(loginNamelist.contains(importUserVCcmTeam.getLoginName())) {
					failureMsg.append("<br/>社工姓名" + importUserVCcmTeam.getName() + " 导入失败：" + "登录名已存在！");
					importUserVCcmTeam.setName(importUserVCcmTeam.getName() + "，失败原因：登录名已存在！");
					listFailure.add(importUserVCcmTeam);
					failureNum++;
					continue;
				}else {
					loginNamelist.add(importUserVCcmTeam.getLoginName());
				}
				
//					People.setIsPermanent("01");

				//出生日期，从身份证中去获取
//					String birthStr = userVCcmTeam.getvCcmTeam().getIdenNum().substring(6, 14);
//					People.setBirthday(sdf.parse(birthStr));

				// 验证必填项
				if ( StringUtils.isBlank(importUserVCcmTeam.getNo()) || 
						StringUtils.isBlank(importUserVCcmTeam.getName()) || 
						StringUtils.isBlank(importUserVCcmTeam.getLoginName()) || 
						StringUtils.isBlank(importUserVCcmTeam.getTeamType()) || 
						StringUtils.isBlank(importUserVCcmTeam.getSex()) || 
						StringUtils.isBlank(importUserVCcmTeam.getGrade()) || 
						StringUtils.isBlank(importUserVCcmTeam.getNation()) || 
						StringUtils.isBlank(importUserVCcmTeam.getService()) || 
						StringUtils.isBlank(importUserVCcmTeam.getPolitics()) || 
						StringUtils.isBlank(importUserVCcmTeam.getEducation())) {
					failureMsg.append("<br/>社工姓名" + importUserVCcmTeam.getName() + " 导入失败：" + "必填项为空或输入值有误。");
					importUserVCcmTeam.setName(importUserVCcmTeam.getName() + "，失败原因：必填项为空或输入值有误。");
					listFailure.add(importUserVCcmTeam);
					failureNum++;
					continue;
				}

				//出生年月日是否合法
				if ( DateTools.getIntYearByDate(importUserVCcmTeam.getBirthday())  <  DateTools.getIntYear() -150 || DateTools.getIntYearByDate(importUserVCcmTeam.getBirthday()) >  DateTools.getIntYear()) {
					failureMsg.append("<br/>社工姓名" + importUserVCcmTeam.getName() + " 导入失败：" + "出生年月数据不合法");
					importUserVCcmTeam.setName(importUserVCcmTeam.getName() + "，失败原因：出生年月数据不合法");
					listFailure.add(importUserVCcmTeam);
					failureNum++;
					continue;
				}

				// 如果当前用户的身份填写且身份证号码位数不够18位则应该进行 剔除
				if (StringUtils.isNotBlank(importUserVCcmTeam.getIdenNum()) && importUserVCcmTeam.getIdenNum().length() != 18) {
					failureMsg.append("<br/>社工姓名" + importUserVCcmTeam.getName() + " 导入失败：" + "当前的用户的身份证材料尚未存在。");
					importUserVCcmTeam.setName(importUserVCcmTeam.getName() + "，失败原因：身份证信息错误");
					listFailure.add(importUserVCcmTeam);
					failureNum++;
					continue;
				}

				//电话验证
				if( NumberTools.isPhone(importUserVCcmTeam.getMobile())==false) {
					failureMsg.append("<br/>社工姓名" + importUserVCcmTeam.getName() + " 导入失败：" + "必填项数据不合法。");
					importUserVCcmTeam.setName(importUserVCcmTeam.getName() + "，失败原因：必填项数据不合法。");
					listFailure.add(importUserVCcmTeam);
					failureNum++;
					continue;
				}

				User user = new User();
				user.setCompany(importUserVCcmTeam.getCompany());
				user.setOffice(importUserVCcmTeam.getOffice());
				user.setLoginName(importUserVCcmTeam.getLoginName());
				user.setName(importUserVCcmTeam.getName());
				user.setPassword(importUserVCcmTeam.getPassword());
				user.setNo(importUserVCcmTeam.getNo());
				user.setEmail(importUserVCcmTeam.getEmail());
				user.setPhone(importUserVCcmTeam.getPhone());
				user.setMobile(importUserVCcmTeam.getMobile());
				user.setUserType(importUserVCcmTeam.getUserType());
				user.setCreateDate(importUserVCcmTeam.getCreateDate());
				user.setRoleList(importUserVCcmTeam.getRoleList());
				user.setRemarks(importUserVCcmTeam.getRemarks());
				try {
					String checkLoginName = null;
					if ("" != null && "".equals(user.getLoginName())) {
						checkLoginName = "true";
					} else {
						checkLoginName =  "" != null && this.systemService.getUserByLoginName("") == null ? "true" : "false";
					}
					if ("true".equals(checkLoginName)) {
						user.setPassword(SystemService.entryptPassword("123456"));
						BeanValidators.validateWithException(this.validator, user, new Class[0]);
						this.systemService.saveUser(user);
						CcmOrgTeam ccmOrgTeam = new CcmOrgTeam();
						ccmOrgTeam.setTeamType(importUserVCcmTeam.getTeamType());
						ccmOrgTeam.setSex(importUserVCcmTeam.getSex());
						ccmOrgTeam.setGrade(importUserVCcmTeam.getGrade());
						ccmOrgTeam.setNation(importUserVCcmTeam.getNation());
						ccmOrgTeam.setService(importUserVCcmTeam.getService());
						ccmOrgTeam.setPolitics(importUserVCcmTeam.getPolitics());
						ccmOrgTeam.setBirthday(importUserVCcmTeam.getBirthday());
						ccmOrgTeam.setIdenNum(importUserVCcmTeam.getIdenNum());
						ccmOrgTeam.setEducation(importUserVCcmTeam.getEducation());
						ccmOrgTeam.setFixTel(importUserVCcmTeam.getFixTel());
						ccmOrgTeam.setUser(user);

						ccmOrgTeamService.save(ccmOrgTeam);
						++successNum;
					} else {
						failureMsg.append("<br/>登录名 " + user.getLoginName() + " 已存在; ");
						++failureNum;
					}
				} catch (ConstraintViolationException var14) {
					failureMsg.append("<br/>登录名 " + user.getLoginName() + " 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(var14, ": ");

					for(Iterator var13 = messageList.iterator(); var13.hasNext(); ++failureNum) {
						String message = (String)var13.next();
						failureMsg.append(message + "; ");
					}
				} catch (Exception var15) {
					failureMsg.append("<br/>登录名 " + user.getLoginName() + " 导入失败：" + var15.getMessage());
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条社工信息，导入信息如下：");
				reslist.addAll(listFailure);
//				String fileName = "PeopleImportFailure-" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
//				new ExportExcel("实有人口导入失败数据", CcmPeople.class).setDataList(listFailure).write(response, fileName).dispose();
//				return null;
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条社工信息" + failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入社工信息失败！失败信息：" + e.getMessage());
		}
		Office office = UserUtils.getUser().getOffice();
		return "redirect:" + adminPath + "/view/vCcmTeam/list?office.id="+office.getId()+"&office.parentIds=,"+office.getId()+",";
	}

	/**
	 *
	 * 导出事件处理列表
	 */
	@RequiresPermissions("sys:ccmWorkReport:view")
	@RequestMapping(value = { "export" })
	public void export(VCcmTeam vCcmTeam, HttpServletRequest request, HttpServletResponse response,
							Model model) {
		try {
			String fileName = "ComprehensiveManagementTeam"  + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			List<ImportUserVCcmTeam> result = new ArrayList<ImportUserVCcmTeam>();
			List<VCcmTeam> list = vCcmTeamService.findList(vCcmTeam);
			List<User> userlist = systemService.findUser(new User());
			List<Role> role = systemService.findAllRole();
			ImportUserVCcmTeam imUser = null;
			for (VCcmTeam vCcmTeam2 : list) {
				imUser = new ImportUserVCcmTeam();
				for (User user : userlist) {
					if(user.getId().equals(vCcmTeam2.getUser().getId())) {
						vCcmTeam2.setUser(user);
					}
				}
				if(StringUtils.isEmpty(vCcmTeam2.getUser().getId())) {
					imUser.setCompany(vCcmTeam2.getCompanyId());
					imUser.setOffice(vCcmTeam2.getOffice());
					imUser.setLoginName(vCcmTeam2.getLoginName());
					imUser.setName(vCcmTeam2.getName());
					imUser.setPassword(vCcmTeam2.getPassword());
					imUser.setNo(vCcmTeam2.getNo());
					imUser.setEmail(vCcmTeam2.getEmail());
					imUser.setPhone(vCcmTeam2.getPhone());
					imUser.setUserType(vCcmTeam2.getUserType());
					imUser.setMobile(vCcmTeam2.getMobile());
					imUser.setCreateDate(vCcmTeam2.getCreateDate());
					imUser.setRemarks(vCcmTeam2.getRemarks());
				}else {
					imUser.setCompany(vCcmTeam2.getUser().getCompany());
					imUser.setOffice(vCcmTeam2.getUser().getOffice());
					imUser.setLoginName(vCcmTeam2.getUser().getLoginName());
					imUser.setName(vCcmTeam2.getUser().getName());
					imUser.setPassword(vCcmTeam2.getUser().getPassword());
					imUser.setNo(vCcmTeam2.getUser().getNo());
					imUser.setEmail(vCcmTeam2.getUser().getNo());
					imUser.setPhone(vCcmTeam2.getUser().getPhone());
					imUser.setUserType(vCcmTeam2.getUser().getUserType());
					imUser.setMobile(vCcmTeam2.getUser().getMobile());
					imUser.setCreateDate(vCcmTeam2.getUser().getCreateDate());
					List<Role> roleList = new ArrayList<Role>();
					//根据用户ID查询中间表
					SysUserRole uandr = new SysUserRole();
					uandr.setUserId(vCcmTeam2.getUser().getId());
					List<SysUserRole> userrole = userService.findUandRList(uandr);
					for (SysUserRole sysUserRole : userrole) {
						for (Role r : role) {
							if(r.getId().equals(sysUserRole.getRoleId())) {
								roleList.add(r);
							}
						}
					}
					imUser.setRoleList(roleList);
					imUser.setRemarks(vCcmTeam2.getUser().getRemarks());
				}
				imUser.setTeamType(vCcmTeam2.getTeamType());
				imUser.setSex(vCcmTeam2.getSex());
				imUser.setGrade(vCcmTeam2.getGrade());
				imUser.setNation(vCcmTeam2.getNation());
				imUser.setService(vCcmTeam2.getService());
				imUser.setPolitics(vCcmTeam2.getPolitics());
				imUser.setBirthday(vCcmTeam2.getBirthday());
				imUser.setEducation(vCcmTeam2.getEducation());
				imUser.setIdenNum(vCcmTeam2.getIdenNum());
				imUser.setFixTel(vCcmTeam2.getFixTel());
				result.add(imUser);
			}
			new ExportExcel("综治队伍", ImportUserVCcmTeam.class).setDataList(result).write(response, fileName).dispose();
		} catch (Exception e) {
			System.out.println("导出综治队伍数据失败！失败信息：" + e.getMessage());
		}
	}
}
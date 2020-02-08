package com.arjjs.ccm.modules.sys.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgTeam;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgTeamService;
import com.arjjs.ccm.modules.ccm.view.entity.ImportUserVCcmTeam;
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

/**
 * 编码方案管理Controller
 * @author chenboyuan
 * @version 2018-07-03
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/sysUser")
public class SysUserController extends BaseController {
	@Autowired
	private UserService userService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private CcmOrgTeamService ccmOrgTeamService;
	
	/**
	 *
	 * 导出用户列表
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = { "/export" })
	public void export(User user, HttpServletRequest request, HttpServletResponse response,
							Model model) {
		try {
			String fileName = "User"  + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			//查询出所有用户
			List<User> list = systemService.findUser(user);
			//查询出所有角色
			List<Role> role = systemService.findAllRole();
			for (int i=0;i<list.size();i++) {
				List<Role> roleList = new ArrayList<Role>();
				//根据用户ID查询中间表
				SysUserRole uandr = new SysUserRole();
				uandr.setUserId(list.get(i).getId());
				List<SysUserRole> userrole = userService.findUandRList(uandr);
				for (SysUserRole sysUserRole : userrole) {
					for (Role r : role) {
						if(r.getId().equals(sysUserRole.getRoleId())) {
							roleList.add(r);
						}
					}
				}
				list.get(i).setRoleList(roleList);
			}
			new ExportExcel("用户信息", User.class).setDataList(list).write(response, fileName).dispose();
		} catch (Exception e) {
			System.out.println("导出用户信息数据失败！失败信息：" + e.getMessage());
		}
	}
	
	/**
	 * 导入实有人口数据
	 *
	 */
	public static final List<ImportUserVCcmTeam> reslist = new ArrayList<ImportUserVCcmTeam>();
	@RequiresPermissions("sys:user:view")
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

				if(EntityTools.isEmpty(importUserVCcmTeam)){
					continue;
				}
				
				//根据工号去重
				if(workNolist.contains(importUserVCcmTeam.getNo())) {
					failureMsg.append("<br/>用户姓名" + importUserVCcmTeam.getName() + " 导入失败：" + "工号已存在！");
					importUserVCcmTeam.setName(importUserVCcmTeam.getName() + "，失败原因：工号已存在！");
					listFailure.add(importUserVCcmTeam);
					failureNum++;
					continue;
				}else {
					workNolist.add(importUserVCcmTeam.getNo());
				}
				
				//根据登录名去重
				if(loginNamelist.contains(importUserVCcmTeam.getLoginName())) {
					failureMsg.append("<br/>用户姓名" + importUserVCcmTeam.getName() + " 导入失败：" + "登录名已存在！");
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
					failureMsg.append("<br/>用户姓名" + importUserVCcmTeam.getName() + " 导入失败：" + "必填项为空或输入值有误。");
					importUserVCcmTeam.setName(importUserVCcmTeam.getName() + "，失败原因：必填项为空或输入值有误。");
					listFailure.add(importUserVCcmTeam);
					failureNum++;
					continue;
				}

				//出生年月日是否合法
				if ( DateTools.getIntYearByDate(importUserVCcmTeam.getBirthday())  <  DateTools.getIntYear() -150 || DateTools.getIntYearByDate(importUserVCcmTeam.getBirthday()) >  DateTools.getIntYear()) {
					failureMsg.append("<br/>用户姓名" + importUserVCcmTeam.getName() + " 导入失败：" + "出生年月数据不合法");
					importUserVCcmTeam.setName(importUserVCcmTeam.getName() + "，失败原因：出生年月数据不合法");
					listFailure.add(importUserVCcmTeam);
					failureNum++;
					continue;
				}

				// 如果当前用户的身份填写且身份证号码位数不够18位则应该进行 剔除
				if (StringUtils.isNotBlank(importUserVCcmTeam.getIdenNum()) && importUserVCcmTeam.getIdenNum().length() != 18) {
					failureMsg.append("<br/>用户姓名" + importUserVCcmTeam.getName() + " 导入失败：" + "当前的用户的身份证材料尚未存在。");
					importUserVCcmTeam.setName(importUserVCcmTeam.getName() + "，失败原因：身份证信息错误");
					listFailure.add(importUserVCcmTeam);
					failureNum++;
					continue;
				}

				//电话验证
				if( NumberTools.isPhone(importUserVCcmTeam.getMobile())==false) {
					failureMsg.append("<br/>用户姓名" + importUserVCcmTeam.getName() + " 导入失败：" + "必填项数据不合法。");
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
				failureMsg.insert(0, "，失败 " + failureNum + " 条用户信息，导入信息如下：");
				reslist.addAll(listFailure);
//				String fileName = "PeopleImportFailure-" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
//				new ExportExcel("实有人口导入失败数据", CcmPeople.class).setDataList(listFailure).write(response, fileName).dispose();
//				return null;
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条用户信息" + failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入用户信息失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/sys/user/list";
	}
}

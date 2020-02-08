/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.beanvalidator.BeanValidators;
import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.DateUtils;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.tool.EntityTools;
import com.arjjs.ccm.tool.ExportExcel;
import com.arjjs.ccm.common.utils.excel.ImportExcel;
import com.arjjs.ccm.modules.ccm.log.entity.CcmLogTail;
import com.arjjs.ccm.modules.ccm.log.service.CcmLogTailService;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgSocialorg;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgSocialorgService;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * 社会组织Controller
 * @author fuxinshuang
 * @version 2018-01-26
 */
@Controller
@RequestMapping(value = "${adminPath}/org/ccmOrgSocialorg")
public class CcmOrgSocialorgController extends BaseController {

	@Autowired
	private CcmOrgSocialorgService ccmOrgSocialorgService;
	
	@Autowired
	private CcmLogTailService ccmLogTailService;
	@ModelAttribute
	public CcmOrgSocialorg get(@RequestParam(required=false) String id) {
		CcmOrgSocialorg entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmOrgSocialorgService.get(id);
		}
		if (entity == null){
			entity = new CcmOrgSocialorg();
		}
		return entity;
	}
	
	@RequiresPermissions("org:ccmOrgSocialorg:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmOrgSocialorg ccmOrgSocialorg, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmOrgSocialorg> page = ccmOrgSocialorgService.findPage(new Page<CcmOrgSocialorg>(request, response), ccmOrgSocialorg); 
		model.addAttribute("page", page);
		return "ccm/org/ccmOrgSocialorgList";
	}

	@RequiresPermissions("org:ccmOrgSocialorg:view")
	@RequestMapping(value = "form")
	public String form(CcmOrgSocialorg ccmOrgSocialorg, Model model) {
		// 创建 查询对象 建立查询条件
		CcmLogTail ccmLogTailDto = new CcmLogTail();
		ccmLogTailDto.setRelevanceId(ccmOrgSocialorg.getId());
		ccmLogTailDto.setRelevanceTable("ccm_org_socialorg");
		List<CcmLogTail > ccmLogTailList = ccmLogTailService.findListByObject(ccmLogTailDto);
		// 返回查询结果
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"createBy","updateBy","currentUser","dbName","global","page","createDate","updateDate","sqlMap"});
		config.setIgnoreDefaultExcludes(false);  //设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String jsonDocumentList = JSONArray.fromObject(ccmLogTailList,config).toString(); 
		model.addAttribute("documentList", jsonDocumentList);
		model.addAttribute("documentNumber", ccmLogTailList.size());
		
		model.addAttribute("ccmLogTailList", ccmLogTailList);				
		model.addAttribute("ccmOrgSocialorg", ccmOrgSocialorg);
		return "ccm/org/ccmOrgSocialorgForm";
	}

	@RequiresPermissions("org:ccmOrgSocialorg:edit")
	@RequestMapping(value = "save")
	public String save(CcmOrgSocialorg ccmOrgSocialorg, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmOrgSocialorg)){
			return form(ccmOrgSocialorg, model);
		}
		ccmOrgSocialorgService.save(ccmOrgSocialorg);
		addMessage(redirectAttributes, "保存社会组织成功");
		return "redirect:"+Global.getAdminPath()+"/org/ccmOrgSocialorg/?repage";
	}
	
	@RequiresPermissions("org:ccmOrgSocialorg:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmOrgSocialorg ccmOrgSocialorg, RedirectAttributes redirectAttributes) {
		ccmOrgSocialorgService.delete(ccmOrgSocialorg);
		addMessage(redirectAttributes, "删除社会组织成功");
		return "redirect:"+Global.getAdminPath()+"/org/ccmOrgSocialorg/?repage";
	}
	
	/**
	 * 导出社会组织数据
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("org:ccmOrgSocialorg:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(CcmOrgSocialorg ccmOrgSocialorg, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		String [] strArr={"统一社会信用代码","登记证号","社会组织名称","登记管理机关代码","法定代表人姓名","住所","批准日期","社会组织类型","负责人证件代码","负责人证件号码","负责人姓名","负责人联系方式","办公地址","治保负责人姓名","治保负责人联系方式"
				,"关注程度","是否具备建立中共党组织条件","是否有中共党组织","中共党员数量","是否有工会","工会会员数量","是否有共青团组织","共青团团员数量","是否有妇联组织","妇女数量","是否有境外背景"};
		try {
			String fileName = "social" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			List<CcmOrgSocialorg> list = ccmOrgSocialorgService.findList(ccmOrgSocialorg);
			new ExportExcel("社会组织数据", CcmOrgSocialorg.class,strArr).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出社会组织失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/org/ccmOrgSocialorg/?repage";
	}

	/**
	 * 导入社会组织数据
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("org:ccmOrgSocialorg:view")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/org/ccmOrgSocialorg/?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<CcmOrgSocialorg> list = ei.getDataList(CcmOrgSocialorg.class);
			for (CcmOrgSocialorg OrgSocialorg : list) {
				try {
					
					if(EntityTools.isEmpty(OrgSocialorg)){
						failureNum++;
						continue;
					}
					
					if(StringUtils.isBlank(OrgSocialorg.getCreditCode()) ||
							StringUtils.isBlank(OrgSocialorg.getRegiNum()) ||
							StringUtils.isBlank(OrgSocialorg.getOrgName()) ||
							StringUtils.isBlank(OrgSocialorg.getRegiPlaceNum()) ||
							StringUtils.isBlank(OrgSocialorg.getLegalReprName()) ||
							StringUtils.isBlank(OrgSocialorg.getPlace()) ||
							StringUtils.isBlank(OrgSocialorg.getOrgType()) ||
							StringUtils.isBlank(OrgSocialorg.getPrinCode()) ||
							StringUtils.isBlank(OrgSocialorg.getPrinId()) ||
							StringUtils.isBlank(OrgSocialorg.getPrinName()) ||
							StringUtils.isBlank(OrgSocialorg.getPrinTel()) ||
							StringUtils.isBlank(OrgSocialorg.getWorkAdd()) ||
							StringUtils.isBlank(OrgSocialorg.getSecuName()) ||
							StringUtils.isBlank(OrgSocialorg.getSecuTel()) ||
							StringUtils.isBlank(OrgSocialorg.getConcernExtent()) ||
							StringUtils.isBlank(OrgSocialorg.getEstaOrgCond()) ||
							StringUtils.isBlank(OrgSocialorg.getEstaOrg()) ||
							StringUtils.isBlank(OrgSocialorg.getLaborUnion()) ||
							StringUtils.isBlank(OrgSocialorg.getYouthLeagOrg()) ||
							StringUtils.isBlank(OrgSocialorg.getWomenOrg()) ||
							StringUtils.isBlank(OrgSocialorg.getOverBack()) ||
							OrgSocialorg.getApprDate()==null ||
							OrgSocialorg.getPartyMem()==null ||
							OrgSocialorg.getYouthLeagOrgNum()==null ||
							OrgSocialorg.getLaborUnionNum()==null ||
							OrgSocialorg.getWomenNum()==null
					){
						StringBuilder str = new StringBuilder();
						str.append("(");
						if(StringUtils.isBlank(OrgSocialorg.getCreditCode())) {
							str.append("统一社会信用代码信息错误;");
						}
						if(StringUtils.isBlank(OrgSocialorg.getRegiNum())) {
							str.append("登记证号信息错误;");
						}
						if(StringUtils.isBlank(OrgSocialorg.getOrgName())) {
							str.append("社会组织名称信息错误;");
						}
						if(StringUtils.isBlank(OrgSocialorg.getRegiPlaceNum())) {
							str.append("登记管理机关代码信息错误;");
						}
						if(StringUtils.isBlank(OrgSocialorg.getLegalReprName())) {
							str.append("法定代表人姓名信息错误;");
						}
						if(StringUtils.isBlank(OrgSocialorg.getPlace())) {
							str.append("住所信息错误;");
						}
						if(StringUtils.isBlank(OrgSocialorg.getOrgType())) {
							str.append("社会组织类型信息错误;");
						}
						if(StringUtils.isBlank(OrgSocialorg.getPrinCode())) {
							str.append("负责人证件代码信息错误;");
						}
						if(StringUtils.isBlank(OrgSocialorg.getPrinId())) {
							str.append("负责人证件号码信息错误;");
						}
						if(StringUtils.isBlank(OrgSocialorg.getPrinName())) {
							str.append("负责人姓名信息错误;");
						}
						if(StringUtils.isBlank(OrgSocialorg.getPrinTel())) {
							str.append("负责人联系方式信息错误;");
						}
						if(StringUtils.isBlank(OrgSocialorg.getWorkAdd())) {
							str.append("办公地址信息错误;");
						}
						if(StringUtils.isBlank(OrgSocialorg.getSecuName())) {
							str.append("治保负责人姓名信息错误;");
						}
						if(StringUtils.isBlank(OrgSocialorg.getSecuTel())) {
							str.append("治保负责人联系方式信息错误;");
						}
						if(StringUtils.isBlank(OrgSocialorg.getConcernExtent())) {
							str.append("关注程度信息错误;");
						}
						if(StringUtils.isBlank(OrgSocialorg.getEstaOrgCond())) {
							str.append("是否具备建立中共党组织条件信息错误;");
						}
						if(StringUtils.isBlank(OrgSocialorg.getEstaOrg())) {
							str.append("是否有中共党组织信息错误;");
						}
						if(StringUtils.isBlank(OrgSocialorg.getLaborUnion())) {
							str.append("是否有工会信息错误;");
						}
						if(StringUtils.isBlank(OrgSocialorg.getYouthLeagOrg())) {
							str.append("是否有共青团组织信息错误;");
						}
						if(StringUtils.isBlank(OrgSocialorg.getWomenOrg())) {
							str.append("是否有妇联组织信息错误;");
						}
						if(StringUtils.isBlank(OrgSocialorg.getOverBack())) {
							str.append("是否有境外背景信息错误;");
						}
						if(OrgSocialorg.getApprDate()==null) {
							str.append("批准日期信息错误;");
						}
						if(OrgSocialorg.getPartyMem()==null) {
							str.append("中共党员数量信息错误;");
						}
						if(OrgSocialorg.getYouthLeagOrgNum()==null) {
							str.append("共青团团员数量信息错误;");
						}
						if(OrgSocialorg.getLaborUnionNum()==null) {
							str.append("工会会员数量信息错误;");
						}
						if(OrgSocialorg.getWomenNum()==null) {
							str.append("妇女数量信息错误;");
						}
						str.append(")");
						failureMsg.append("<br/>社会组织名 " + OrgSocialorg.getOrgName() + " 导入失败：必填项为空。"+str.toString());
						failureNum++;
						continue;
					}
					
					CcmOrgSocialorg social = new CcmOrgSocialorg();
					social.setRegiNum(OrgSocialorg.getRegiNum());
					List<CcmOrgSocialorg> result = ccmOrgSocialorgService.findList(social);
					if(result.isEmpty()) {
						BeanValidators.validateWithException(validator, OrgSocialorg);
						ccmOrgSocialorgService.save(OrgSocialorg);
						successNum++;
					}else {
						failureMsg.append("<br/>社会组织名 " + OrgSocialorg.getOrgName() + " 导入失败：登记证号已存在。");
						failureNum++;
						continue;
					}
				} catch (ConstraintViolationException ex) {
					failureMsg.append("<br/>社会组织名 " + OrgSocialorg.getOrgName() + " 导入失败：");
					List<String> messageList = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
					for (String message : messageList) {
						failureMsg.append(message + "; ");
						failureNum++;
					}
				} catch (Exception ex) {
					failureMsg.append("<br/>社会组织名 " + OrgSocialorg.getOrgName()+ " 导入失败：" + ex.getMessage());
				}
			}
			if (failureNum > 0) {
				failureMsg.insert(0, "，失败 " + failureNum + " 条社会组织，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 " + successNum + " 条社会组织" + failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入社会组织失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/org/ccmOrgSocialorg/?repage";
	}


}
/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.partybuild.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.partybuild.entity.CcmPartyOrganiz;
import com.arjjs.ccm.modules.ccm.partybuild.service.CcmPartyOrganizService;
import com.arjjs.ccm.modules.sys.entity.Dict;
import com.arjjs.ccm.modules.sys.utils.DictUtils;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 党组织管理Controller
 * @author maoxb
 *
 * @version 2019-08-12
 */
@Controller
@RequestMapping(value = "${adminPath}/partybuild/ccmPartyOrganiz")
public class CcmPartyOrganizController extends BaseController {

	@Autowired
	private CcmPartyOrganizService ccmPartyOrganizService;
	
	@ModelAttribute
	public CcmPartyOrganiz get(@RequestParam(required=false) String id) {
		CcmPartyOrganiz entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmPartyOrganizService.get(id);
		}
		if (entity == null){
			entity = new CcmPartyOrganiz();
		}
		return entity;
	}
	

	@RequestMapping(value = {"list", ""})
	public String list(CcmPartyOrganiz ccmPartyOrganiz, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmPartyOrganiz> page = ccmPartyOrganizService.findPage(new Page<CcmPartyOrganiz>(request, response), ccmPartyOrganiz); 
		model.addAttribute("page", page);
		String type = ccmPartyOrganiz.getType();
		return  "1".equals(type) ?  "ccm/partybuild/ccmPartyOrganizList" : "ccm/partybuild/ccmPartyDoubleOrganizList";
	}

	@RequiresPermissions("partybuild:ccmPartyOrganiz:view")
	@RequestMapping(value = {"ccmPartyDoubleOrganizIndex"})
	public String ccmPartyDoubleOrganizIndex() {
		return "ccm/partybuild/ccmPartyDoubleOrganizIndex";
	}

	@RequiresPermissions("partybuild:ccmPartyOrganiz:view")
	@RequestMapping(value = {"ccmPartyOrganizIndex"})
	public String ccmPartyOrganizIndex() {
		return "ccm/partybuild/ccmPartyOrganizIndex";
	}

	/**
	 * @since  社区-1/两新党组织-2 返回页面 1/2
	 * @param ccmPartyOrganiz
	 * @param model
	 * @author maoxb
	 * @return
	 */
	@RequiresPermissions("partybuild:ccmPartyOrganiz:view")
	@RequestMapping(value = "form")
	public String form(CcmPartyOrganiz ccmPartyOrganiz, Model model) {
		model.addAttribute("ccmPartyOrganiz", ccmPartyOrganiz);
		CcmPartyOrganiz ccmPartyOrganiz1 = new CcmPartyOrganiz();
		ccmPartyOrganiz1.setType(ccmPartyOrganiz.getType());
		List<CcmPartyOrganiz> list = ccmPartyOrganizService.findList(ccmPartyOrganiz1);
		model.addAttribute("list", list);
		String type = ccmPartyOrganiz.getType();		//return "redirect:"+Global.getAdminPath()+"/partybuild/ccmPartyOrganiz/?repage";
		model.addAttribute("type", type);
		return "ccm/partybuild/ccmPartyOrganizForm";
//		return  "1".equals(type) ?  "ccm/partybuild/ccmPartyOrganizForm" : "ccm/partybuild/ccmPartyDoubleOrganizForm";
	}

	@RequiresPermissions("partybuild:ccmPartyOrganiz:edit")
	@RequestMapping(value = "save")
	public void save(CcmPartyOrganiz ccmPartyOrganiz, Model model, RedirectAttributes redirectAttributes,
					 HttpServletRequest request,HttpServletResponse response) throws IOException {
		ccmPartyOrganizService.save(ccmPartyOrganiz);
		addMessage(redirectAttributes, "保存党组织管理成功");

		PrintWriter out = response.getWriter();
		out.println("<script language='javascript'>	var index = parent.layer.getFrameIndex(window.name);parent.layer.close(index);</script>");
		out.println("<script language='javascript'>parent.parent.parent.alertInfo('" + "保存成功"
				+ "');</script>");
		out.println("<script language='javascript'>parent.parent.parent.document.getElementById('mainFrame').contentWindow.location.reload(true);</script>");
	}
	
	@RequiresPermissions("partybuild:ccmPartyOrganiz:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmPartyOrganiz ccmPartyOrganiz, RedirectAttributes redirectAttributes) {
		ccmPartyOrganizService.delete(ccmPartyOrganiz);
		addMessage(redirectAttributes, "删除党组织管理成功");
		String type = ccmPartyOrganiz.getType();
		return "redirect:"+Global.getAdminPath()+"/partybuild/ccmPartyOrganiz/?type=" + type;
	}


	@ResponseBody
	@RequestMapping(value = "getOrgByBussCate")
	public Map<String, Object> getOrgByBussCate(Model model) {
		// 返回对象结果
		List<EchartType> list = ccmPartyOrganizService.getOrgByBussCate();
		String[] type = null;
		String[] value = null;
		if(list.size()==0){
			List<Dict> dictList = DictUtils.getDictList("ccm_buss_cate");
			type = new String[dictList.size()];
			value = new String[dictList.size()];
			for (int i = 0; i < dictList.size(); i++) {
				type[i] = dictList.get(i).getLabel();
				value[i] = "0";
			}
		}else {
			type = new String[list.size()];
			value = new String[list.size()];
		}



		for (int i = 0; i < list.size(); i++) {
//			CcmOrgArea ccmOrgArea = new CcmOrgArea();
//			sysArea = list.get(i);
//			ccmOrgArea.setAreaId(sysArea.getId());
//
//			CcmOrgArea ccmOrgArea1 = ccmOrgAreaService.findList(ccmOrgArea).get(0);

//            //赋值
			list.get(i).setType(DictUtils.getDictLabel(list.get(i).getType(), "ccm_buss_cate", "无类型信息"));
			type[i] = list.get(i).getType();
			value[i] = list.get(i).getValue();
		}
		Map<String, Object> data = new HashMap<>();
		data.put("type", type);
		data.put("value", value);
		return data;
	}
}
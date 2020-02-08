/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.sys.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.Collections3;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.person.service.PbsPartymemService;
import com.arjjs.ccm.modules.pbs.sys.entity.PbsDepartmentetc;
import com.arjjs.ccm.modules.pbs.sys.service.PbsDepartmentetcService;
import com.arjjs.ccm.modules.pbs.sys.entity.PbsOffice;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;
import com.google.common.collect.Lists;

/**
 * 党员部门扩展信息Controller
 * 
 * @author lc
 * @version 2018-04-10
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/pbsDepartmentetc")
public class PbsDepartmentetcController extends BaseController {

	@Autowired
	private PbsDepartmentetcService pbsDepartmentetcService;

	@Autowired
	private PbsPartymemService pbsPartymemService;
	
	@ModelAttribute
	public PbsDepartmentetc get(@RequestParam(required = false) String id) {
		PbsDepartmentetc entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsDepartmentetcService.get(id);
		}
		if (entity == null) {
			entity = new PbsDepartmentetc();
		}
		return entity;
	}

	@RequiresPermissions("sys:pbsDepartmentetc:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsDepartmentetc pbsDepartmentetc, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<PbsDepartmentetc> page = pbsDepartmentetcService.findPage(new Page<PbsDepartmentetc>(request, response),pbsDepartmentetc);
		Page<PbsDepartmentetc> pages = new Page<PbsDepartmentetc>();
		pages = page;
		for(int i = 0;i < page.getList().size();i++) {
			if(page.getList().get(i).getSSpare01() != null && page.getList().get(i).getSSpare01() != "") {
				String[] userIdArray = page.getList().get(i).getSSpare01().split(",");
				String userNames = "";
				if(userIdArray.length>0) {
					for(int j = 0;j<userIdArray.length;j++) {
						PbsPartymem user = new PbsPartymem();
						user = pbsPartymemService.get(userIdArray[j]);
						if(user != null) {
							userNames += user.getSName() + ",";
						}
					}
				}
				pages.getList().get(i).setMasternames(userNames);
			}
		}
		
		model.addAttribute("page", pages);
		return "pbs/sys/pbsDepartmentetcList";
	}

	@RequiresPermissions("sys:pbsDepartmentetc:view")
	@RequestMapping(value = "form")
	public String form(PbsDepartmentetc pbsDepartmentetc, Model model) {
		if(pbsDepartmentetc.getSSpare01() != null && pbsDepartmentetc.getSSpare01() != "") {
			String[] userIdArray = pbsDepartmentetc.getSSpare01().split(",");
			String userNames = "";
			if(userIdArray.length>0) {
				for(int j = 0;j<userIdArray.length;j++) {
					PbsPartymem user = new PbsPartymem();
					user = pbsPartymemService.get(userIdArray[j]);
					if(user != null) {
						userNames += user.getSName() + ",";
					}
				}
			}
			pbsDepartmentetc.setMasternames(userNames);
		}
		model.addAttribute("pbsDepartmentetc", pbsDepartmentetc);
		return "pbs/sys/pbsDepartmentetcForm";
	}

	@RequiresPermissions("sys:pbsDepartmentetc:edit")
	@RequestMapping(value = "save")
	public String save(PbsDepartmentetc pbsDepartmentetc, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsDepartmentetc)) {
			return form(pbsDepartmentetc, model);
		}
		// 如果已经含有该部门则 返回提示信息
		if (pbsDepartmentetcService.checkExist(pbsDepartmentetc)) {
			addMessage(model, "数据验证失败：该部门已经拥有信息");
			return form(pbsDepartmentetc, model);
		}
		pbsDepartmentetcService.save(pbsDepartmentetc);
		addMessage(redirectAttributes, "保存党干部管理信息成功");
		return "redirect:" + Global.getAdminPath() + "/sys/pbsDepartmentetc/?repage";
	}

	@RequiresPermissions("sys:pbsDepartmentetc:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsDepartmentetc pbsDepartmentetc, RedirectAttributes redirectAttributes) {
		pbsDepartmentetcService.delete(pbsDepartmentetc);
		addMessage(redirectAttributes, "删除党干部管理信息成功");
		return "redirect:" + Global.getAdminPath() + "/sys/pbsDepartmentetc/?repage";
	}

	// 获取 所有的 类型
	@RequestMapping(value = "namelist")
	public String namelist(PbsOffice office, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<PbsOffice> list = pbsDepartmentetcService.getAllOfficeWithName(office);
		// 1 为删除自己所在的部门
		if (("1").equals(office.getRemoveme())) {
			List<PbsOffice> curList = Lists.newArrayList();
			List<PbsOffice> curoffices = UserUtils.getOfficeList();
			String CurofcIds = Collections3.extractToString(curoffices, "id", ",");
			// 如果当前的用户已经存在于该部门
			for (PbsOffice ofc : list) {
				if (!CurofcIds.contains(ofc.getId())) {
					curList.add(ofc);
				}
			}
			model.addAttribute("list", curList);
		} else {
			model.addAttribute("list", list);
		}

		return "mapping/OfficeList";
	}
}
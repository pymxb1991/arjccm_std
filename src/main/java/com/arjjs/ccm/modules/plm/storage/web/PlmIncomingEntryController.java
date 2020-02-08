/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.storage.web;

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
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.plm.storage.entity.PlmIncomingEntry;
import com.arjjs.ccm.modules.plm.storage.entity.PlmMinusandAddDetail;
import com.arjjs.ccm.modules.plm.storage.service.PlmEquipmentService;
import com.arjjs.ccm.modules.plm.storage.service.PlmIncomingEntryService;
import com.arjjs.ccm.modules.plm.storage.service.PlmMinusandAddDetailService;

/**
 * 入库单Controller
 * @author dongqikai
 * @version 2018-06-30
 */
@Controller
@RequestMapping(value = "${adminPath}/storage/plmIncomingEntry")
public class PlmIncomingEntryController extends BaseController {

	@Autowired
	private PlmIncomingEntryService plmIncomingEntryService;
	
	@Autowired
	private PlmEquipmentService plmEquipmentService;
	
	@Autowired
	private PlmMinusandAddDetailService plmMinusandAddDetailService;
	
	@ModelAttribute
	public PlmIncomingEntry get(@RequestParam(required=false) String id) {
		PlmIncomingEntry entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmIncomingEntryService.get(id);
		}
		if (entity == null){
			entity = new PlmIncomingEntry();
		}
		return entity;
	}
	
	@RequiresPermissions("storage:plmIncomingEntry:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmIncomingEntry plmIncomingEntry, HttpServletRequest request, HttpServletResponse response, Model model) {
		plmIncomingEntry.setFlag("0");
		Page<PlmIncomingEntry> page = plmIncomingEntryService.findPage(new Page<PlmIncomingEntry>(request, response), plmIncomingEntry); 
		model.addAttribute("page", page);
		model.addAttribute("provideList", plmEquipmentService.getProvidList());
		return "plm/storage/plmIncomingEntryList";
	}

	@RequiresPermissions("storage:plmIncomingEntry:view")
	@RequestMapping(value = "form")
	public String form(PlmIncomingEntry plmIncomingEntry, Model model) {
		model.addAttribute("provideList", plmEquipmentService.getProvidList());//供应商下拉列表
		PlmMinusandAddDetail plmMinusandAddDetail = new PlmMinusandAddDetail();
		plmMinusandAddDetail.setParent(plmIncomingEntry.getId());
		model.addAttribute("detailList", plmMinusandAddDetailService.findList(plmMinusandAddDetail));//入库明细列表
		String flag = PlmIncomingEntry.COMPLETE_IN;  //获取出库状态，用于判断前端显示
		if (StringUtils.isNotBlank(plmIncomingEntry.getId())) {
			if (StringUtils.isNotBlank(plmIncomingEntry.getType())) {
				flag = plmIncomingEntry.getType();
			}
		} else {
			flag = PlmIncomingEntry.READY_IN;
			plmIncomingEntry.setType(PlmIncomingEntry.READY_IN);
		}
		if (PlmIncomingEntry.INCOMMING_TYPE.equals(plmIncomingEntry.getType())) {//如果状态为正在进行，则判断是否全部入库
			plmMinusandAddDetail.setIsConditionFlag(PlmMinusandAddDetail.CONDITION_FLAG);
			List<PlmMinusandAddDetail> notIncommingDetailList = plmMinusandAddDetailService.findList(plmMinusandAddDetail);
			if (notIncommingDetailList == null || notIncommingDetailList.isEmpty()) {
				plmIncomingEntry.setType(PlmIncomingEntry.COMPLETE_IN);
				plmIncomingEntryService.save(plmIncomingEntry);
				flag = PlmIncomingEntry.COMPLETE_IN;
			}
		}
		model.addAttribute("plmIncomingEntry", plmIncomingEntry);
		model.addAttribute("flag", flag);
		return "plm/storage/plmIncomingEntryForm";
	}

	@RequiresPermissions("storage:plmIncomingEntry:edit")
	@RequestMapping(value = "save")
	public String save(PlmIncomingEntry plmIncomingEntry, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmIncomingEntry)){
			return form(plmIncomingEntry, model);
		}
		if (!PlmIncomingEntry.READY_IN.equals(plmIncomingEntry.getType()) && !plmMinusandAddDetailService.checkIsHasDetail(plmIncomingEntry.getId())) {
			model.addAttribute("message", "保存失败：物资详情为空，请添加物资详情！");
			return form(plmIncomingEntry, model);
		}
		plmIncomingEntryService.save(plmIncomingEntry);
		addMessage(redirectAttributes, "保存入库单成功");
		/*return form(plmIncomingEntry, model);*/
		return "redirect:"+Global.getAdminPath()+"/storage/plmIncomingEntry/?repage";
	}
	
	@RequiresPermissions("storage:plmIncomingEntry:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmIncomingEntry plmIncomingEntry, RedirectAttributes redirectAttributes) {
		plmIncomingEntryService.delete(plmIncomingEntry);
		addMessage(redirectAttributes, "删除入库单成功");
		return "redirect:"+Global.getAdminPath()+"/storage/plmIncomingEntry/?repage";
	}

}
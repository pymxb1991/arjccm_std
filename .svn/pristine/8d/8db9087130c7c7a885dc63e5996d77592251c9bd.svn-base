/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.storage.web;

import java.io.IOException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.DateUtils;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.plm.storage.entity.AjaxResultEntity;
import com.arjjs.ccm.modules.plm.storage.entity.PlmEquipment;
import com.arjjs.ccm.modules.plm.storage.entity.PlmIncomingEntry;
import com.arjjs.ccm.modules.plm.storage.entity.PlmMinusandAddDetail;
import com.arjjs.ccm.modules.plm.storage.service.PlmEquipmentService;
import com.arjjs.ccm.modules.plm.storage.service.PlmIncomingEntryService;
import com.arjjs.ccm.modules.plm.storage.service.PlmMinusandAddDetailService;
import com.arjjs.ccm.modules.sys.utils.DictUtils;
import com.arjjs.ccm.modules.sys.utils.UserUtils;

import net.sf.json.JSONObject;

/**
 * 入库单Controller
 * @author dongqikai
 * @version 2018-06-30
 */
@Controller
@RequestMapping(value = "${adminPath}/storage/plmOut")
public class PlmOutController extends BaseController {

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
	
	@RequiresPermissions("storage:plmOut:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmIncomingEntry plmIncomingEntry, HttpServletRequest request, HttpServletResponse response, Model model) {
		plmIncomingEntry.setFlag("1");
		Page<PlmIncomingEntry> page = plmIncomingEntryService.findPage(new Page<PlmIncomingEntry>(request, response), plmIncomingEntry); 
		model.addAttribute("page", page);
		return "plm/storage/plmOutList";
	}

	@RequiresPermissions("storage:plmOut:view")
	@RequestMapping(value = "form")
	public String form(PlmIncomingEntry plmIncomingEntry, Model model) {
		plmEquipmentService.updateOccupyStatus(new PlmEquipment());//进入表单刷新物资占用
		if (plmIncomingEntry.getUser() == null || StringUtils.isBlank(plmIncomingEntry.getUser().getId())) {
			plmIncomingEntry.setUser(UserUtils.getUser());
		}
		if (plmIncomingEntry.getUserJob() == null || StringUtils.isBlank(plmIncomingEntry.getUserJob().getId())) {
			plmIncomingEntry.setUserJob(UserUtils.getUser().getOffice());
		}
		if (StringUtils.isBlank(plmIncomingEntry.getId())) {
			plmIncomingEntry.setType(PlmIncomingEntry.READY_OUT);
		}
		PlmMinusandAddDetail plmMinusandAddDetail = new PlmMinusandAddDetail();
		plmMinusandAddDetail.setParent(plmIncomingEntry.getId());
		List<PlmMinusandAddDetail> detailList = plmMinusandAddDetailService.findList(plmMinusandAddDetail);
		String flag = PlmIncomingEntry.COMPLETE_OUT;  //获取出库状态，用于判断前端显示
		if (StringUtils.isNotBlank(plmIncomingEntry.getId())) {
			if (StringUtils.isNotBlank(plmIncomingEntry.getType())) {
				flag = plmIncomingEntry.getType();
			}
		} else {
			flag = PlmIncomingEntry.READY_OUT;
		}
		model.addAttribute("flag", flag);
		model.addAttribute("plmout", plmIncomingEntry);
		model.addAttribute("detailList", detailList);//入库明细列表
		return "plm/storage/plmOutForm";
	}

	@RequiresPermissions("storage:plmOut:edit")
	@RequestMapping(value = "save")
	public String save(PlmIncomingEntry plmIncomingEntry, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmIncomingEntry)){
			return form(plmIncomingEntry, model);
		}
		if (!PlmIncomingEntry.READY_OUT.equals(plmIncomingEntry.getType()) && !plmMinusandAddDetailService.checkIsHasDetail(plmIncomingEntry.getId())) {
			model.addAttribute("message", "保存失败：物资详情为空，请添加物资详情！");
			return form(plmIncomingEntry, model);
		}
		plmIncomingEntryService.save(plmIncomingEntry);
		saveDetail(plmIncomingEntry);
		plmMinusandAddDetailService.updateDeadlineDate(plmIncomingEntry.getId(), DateUtils.formatDate(plmIncomingEntry.getDeadLineDate(), "yyyy-MM-dd"));
		addMessage(redirectAttributes, "保存出库单成功");
		/*return form(plmIncomingEntry, model);*/
		return "redirect:"+Global.getAdminPath()+"/storage/plmOut/?repage";
	}
	
	@RequiresPermissions("storage:plmOut:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmIncomingEntry plmIncomingEntry, RedirectAttributes redirectAttributes) {
		plmIncomingEntryService.delete(plmIncomingEntry);
		addMessage(redirectAttributes, "删除出库单成功");
		return "redirect:"+Global.getAdminPath()+"/storage/plmOut/?repage";
	}
	
	/**
	 * 根据编号获取资产信息
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequiresPermissions("storage:plmOut:view")
	@RequestMapping(value = "getEquipmentById")	
	public void getEquipmentById(@RequestParam(required = true) String code, HttpServletRequest request, HttpServletResponse response) throws IOException {
		AjaxResultEntity resultEntity = new AjaxResultEntity();
		if (StringUtils.isBlank(code)) {
			resultEntity.setRet(AjaxResultEntity.ERROR_PARAM);
			resultEntity.setMessage("物资编号为空！");
			response.getWriter().print(JSONObject.fromObject(resultEntity).toString());
			return;
		}
		PlmEquipment plmEquipment = new PlmEquipment();
		plmEquipment.setCode(code);
		List<PlmEquipment> equList = plmEquipmentService.findList(plmEquipment);
		if (equList == null || !(equList.size() > 0) || equList.get(0) == null) {
			resultEntity.setRet(AjaxResultEntity.ERROR_PARAM);
			resultEntity.setMessage("该物资信息不存在！");
			response.getWriter().print(JSONObject.fromObject(resultEntity).toString());
			return;
		}
		plmEquipment = equList.get(0);
		if (!PlmEquipment.TYPE_CONSUMABLE.equals(plmEquipment.getTypeId())) {
			plmEquipment.setType(PlmEquipment.OCCUPY_STATUS);
			plmEquipmentService.save(plmEquipment);
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("<tr>");
		buffer.append("<td style=\"display: none;\"><input name=\"equIds\" type=\"text\" value=\"" + plmEquipment.getId() + "\"/></td>");
		buffer.append(getTd(plmEquipment.getCode()));
		buffer.append(getTd(plmEquipment.getName()));
		buffer.append(getTd(plmEquipment.getSpec()));
		buffer.append(getTd(DictUtils.getDictLabel(plmEquipment.getTypeId(), "plm_equipment_type", "未知")));
		buffer.append(getTd(DictUtils.getDictLabel(plmEquipment.getTypeChild(), "plm_equipment_type_child", "未知")));
		if(plmEquipment.getProductionDate() == null) {
			buffer.append(getTd(""));
		}else {			
			buffer.append(getTd(DateUtils.formatDate(plmEquipment.getProductionDate())));
		}
		buffer.append(getTd(PlmIncomingEntry.DEFAULT_NUM));
		buffer.append(getTd(plmEquipment.getUnit()));
		if (PlmIncomingEntry.CONSUMABLE_TYPE.equals(plmEquipment.getTypeId())) {
			buffer.append(getTd("<a title='delete'>删除</a>&nbsp;<a title='num'>耗材数量</a><input name=\"number\" type=\"hidden\" value=\"" + plmEquipment.getId() + "\"/>"));
		} else {
			buffer.append(getTd("<a title='delete'>删除</a>"));
		}
		buffer.append("</tr>");
		resultEntity.setRet(AjaxResultEntity.ERROR_OK);
		resultEntity.setResult(buffer.toString());
		response.getWriter().print(JSONObject.fromObject(resultEntity).toString());
	}
	
	/**
	 * 拼接td节点
	 * @param value
	 * @return
	 */
	public static String getTd(String value) {
		return "<td>" + value + "</td>";
	}
	
	/**
	 * 保存出库详单
	 * @param plmIncomingEntry
	 * @return
	 */
	@RequiresPermissions("storage:plmOut:edit")
	public int saveDetail(PlmIncomingEntry plmIncomingEntry) {
		int num = 0;
		String[] equIds = plmIncomingEntry.getEquIds();
		List<PlmEquipment> equipments= plmEquipmentService.findByIds(equIds);
		if (equipments == null || !(equipments.size() > 0)) {
			return num;
		}
		Map<String, String> consumableNum = new HashMap<String, String>();
		if (plmIncomingEntry.getNumber() != null && plmIncomingEntry.getNumber().length > 0) {
			for (String conNum : plmIncomingEntry.getNumber()) {
				if (StringUtils.isBlank(conNum)) continue;
				String[] idNum = conNum.split("@@");
				if (idNum.length < 2) continue;
				consumableNum.put(idNum[0], idNum[1]);
			}
		}
		for (PlmEquipment plmEquipment : equipments) {
			PlmMinusandAddDetail detail = new PlmMinusandAddDetail();
			detail.setParent(plmIncomingEntry.getId());
			detail.setStorage(plmEquipment.getStorage());
			detail.setEquipmentCode(plmEquipment.getCode());
			detail.setName(plmEquipment.getName());
			detail.setSpec(plmEquipment.getSpec());
			detail.setTypeId(plmEquipment.getTypeId());
			detail.setTypeChild(plmEquipment.getTypeChild());
			if (PlmIncomingEntry.CONSUMABLE_TYPE.equals(plmEquipment.getTypeId())) {
				if (StringUtils.isBlank(consumableNum.get(plmEquipment.getId()))) {
					detail.setErialNumber(1);
				} else {
					detail.setErialNumber(Integer.valueOf(consumableNum.get(plmEquipment.getId())));
				}
			} else {
				detail.setErialNumber(1);
			}
			detail.setImgUrl(plmEquipment.getImgUrl());
			detail.setProductionBatch(plmEquipment.getProductionBatch());
			detail.setProductionDate(plmEquipment.getProductionDate());
			detail.setGuaranteePeriod(plmEquipment.getGuaranteePeriod());
			detail.setExpirationDate(plmEquipment.getExpirationDate());
			detail.setPrice(plmEquipment.getPrice());
			detail.setUnit(plmEquipment.getUnit());
			detail.setDurableYears(plmEquipment.getDurableYears());
			plmMinusandAddDetailService.save(detail);
			num++;
		}
		return num;
	}
	
	/**
	 * 出库详细列表
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("storage:plmOut:view")
	@RequestMapping(value="detailForm")
	public String detailForm(@RequestParam(required = true) String id, Model model) {
		PlmMinusandAddDetail plmMinusandAddDetail = plmMinusandAddDetailService.get(id);
		plmMinusandAddDetail.setFlag(PlmIncomingEntry.NOT_EDIT);
		model.addAttribute("plmMinusandAddDetail", plmMinusandAddDetail);
		return "plm/storage/plmMinusandAddDetailForm";
	}
	
	@RequiresPermissions("storage:plmOut:edit")
	@RequestMapping(value = "deleteDetail")
	public void deleteDetail(PlmMinusandAddDetail plmMinusandAddDetail, HttpServletRequest request, HttpServletResponse response) throws IOException {
		AjaxResultEntity resultEntity = new AjaxResultEntity();
		if (StringUtils.isBlank(plmMinusandAddDetail.getId())) {
			resultEntity.setRet(AjaxResultEntity.ERROR_PARAM);
			resultEntity.setMessage("id为空！");
			response.getWriter().print(JSONObject.fromObject(resultEntity).toString());
			return;
		}
		plmMinusandAddDetailService.delete(plmMinusandAddDetail);
		resultEntity.setRet(AjaxResultEntity.ERROR_OK);
		response.getWriter().print(JSONObject.fromObject(resultEntity).toString());
	}
	
	/**
	 * 更新物资使用人（确认出库完成时调用）
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequiresPermissions("storage:plmOut:edit")
	@RequestMapping(value="updateEquipmentUser")
	public void updateEquipmentUser(@RequestParam(required=true) String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		AjaxResultEntity resultEntity = new AjaxResultEntity();
		if (StringUtils.isBlank(id)) {
			resultEntity.setRet(AjaxResultEntity.ERROR_PARAM);
			resultEntity.setMessage("id为空！");
			response.getWriter().print(JSONObject.fromObject(resultEntity).toString());
			return;
		}
		PlmIncomingEntry incomingEntry = new PlmIncomingEntry();
		incomingEntry.setId(id);
		incomingEntry.setType(PlmIncomingEntry.COMPLETE_OUT);
		plmIncomingEntryService.updateIncomingStatus(incomingEntry);
		PlmEquipment equipment = new PlmEquipment();
		PlmIncomingEntry entry = plmIncomingEntryService.get(id);
		equipment.setUser(entry.getUser());
		equipment.setUserJob(entry.getUserJob());
		equipment.setOutId(id);
		plmEquipmentService.updateUser(equipment);
		PlmMinusandAddDetail detail = new PlmMinusandAddDetail();
		detail.setParent(id);
		detail.setTypeId(PlmEquipment.TYPE_CONSUMABLE);
		List<PlmMinusandAddDetail> detialList = plmMinusandAddDetailService.findList(detail);
		for (PlmMinusandAddDetail plmMinusandAddDetail : detialList) {
			PlmEquipment condition = new PlmEquipment();
			condition.setCode(plmMinusandAddDetail.getEquipmentCode());
			condition.setUseNumber(plmMinusandAddDetail.getErialNumber());
			plmEquipmentService.updateUseNum(condition);
		}
		resultEntity.setRet(AjaxResultEntity.ERROR_OK);
		response.getWriter().print(JSONObject.fromObject(resultEntity).toString());
	}
	
	/**
	 * 刷新物资占用
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="refreshOccupyStatus")
	public void refreshOccupyStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		AjaxResultEntity resultEntity = new AjaxResultEntity();
		plmEquipmentService.updateOccupyStatus(new PlmEquipment());
		resultEntity.setRet(AjaxResultEntity.ERROR_OK);
		response.getWriter().print(JSONObject.fromObject(resultEntity).toString());
	}

}
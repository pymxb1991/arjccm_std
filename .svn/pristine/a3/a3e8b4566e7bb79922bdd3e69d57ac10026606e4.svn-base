/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.storage.web;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.plm.storage.entity.AjaxResultEntity;
import com.arjjs.ccm.modules.plm.storage.entity.PlmEquipment;
import com.arjjs.ccm.modules.plm.storage.entity.PlmIncomingEntry;
import com.arjjs.ccm.modules.plm.storage.entity.PlmMinusandAddDetail;
import com.arjjs.ccm.modules.plm.storage.service.PlmEquipmentService;
import com.arjjs.ccm.modules.plm.storage.service.PlmIncomingEntryService;
import com.arjjs.ccm.modules.plm.storage.service.PlmMinusandAddDetailService;
import com.arjjs.ccm.modules.sys.service.SysCodesService;
import com.arjjs.ccm.modules.sys.utils.DictUtils;

import net.sf.json.JSONObject;

/**
 * 出库、入库进出明细Controller
 * @author dongqikai
 * @version 2018-06-30
 */
@Controller
@RequestMapping(value = "${adminPath}/storage/plmMinusandAddDetail")
public class PlmMinusandAddDetailController extends BaseController {

	@Autowired
	private PlmMinusandAddDetailService plmMinusandAddDetailService;
	
	@Autowired
	private PlmEquipmentService plmEquipmentService;
	
	@Autowired
	private PlmIncomingEntryService plmIncomingEntryService;
	
	@Autowired
	private SysCodesService sysCodesService;
	
	private static String incomingId = "";  //入库单id
	
	private static List<PlmEquipment> printList = new ArrayList<>();  //用于打印的二维码信息列表
	
	@ModelAttribute
	public PlmMinusandAddDetail get(@RequestParam(required=false) String id) {
		PlmMinusandAddDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmMinusandAddDetailService.get(id);
		}
		if (entity == null){
			entity = new PlmMinusandAddDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("storage:plmMinusandAddDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmMinusandAddDetail plmMinusandAddDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmMinusandAddDetail> page = plmMinusandAddDetailService.findPage(new Page<PlmMinusandAddDetail>(request, response), plmMinusandAddDetail); 
		model.addAttribute("page", page);
		return "plm/storage/plmMinusandAddDetailList";
	}

	@RequiresPermissions("storage:plmMinusandAddDetail:view")
	@RequestMapping(value = "form")
	public String form(PlmMinusandAddDetail plmMinusandAddDetail, Model model) {
		model.addAttribute("plmMinusandAddDetail", plmMinusandAddDetail);
		return "plm/storage/plmMinusandAddDetailForm";
	}

	@RequiresPermissions("storage:plmMinusandAddDetail:edit")
	@ResponseBody
	@RequestMapping(value = "save")
	public void save(PlmMinusandAddDetail plmMinusandAddDetail, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		AjaxResultEntity resultEntity = new AjaxResultEntity();
		if (!beanValidator(model, plmMinusandAddDetail)){
			resultEntity.setMessage(model.asMap().get("message").toString());
			response.getWriter().print(JSONObject.fromObject(resultEntity).toString());
			return;
		}
		plmMinusandAddDetailService.save(plmMinusandAddDetail);
		resultEntity.setRet(0);
		response.getWriter().print(JSONObject.fromObject(resultEntity).toString());
	}
	
	@RequiresPermissions("storage:plmMinusandAddDetail:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public void delete(PlmMinusandAddDetail plmMinusandAddDetail, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		plmMinusandAddDetailService.delete(plmMinusandAddDetail);
		AjaxResultEntity resultEntity = new AjaxResultEntity();
		
		resultEntity.setRet(0);
		resultEntity.setMessage("删除成功");
		response.getWriter().print(JSONObject.fromObject(resultEntity).toString());
	}
	
	/**
	 * 修改物资数量
	 * @param plmMinusandAddDetail
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "updateNum")
	@ResponseBody
	public void updateNum(PlmMinusandAddDetail plmMinusandAddDetail, HttpServletRequest request, HttpServletResponse response) throws IOException {
		AjaxResultEntity resultEntity = new AjaxResultEntity();
		if (StringUtils.isBlank(plmMinusandAddDetail.getId()) ||
				plmMinusandAddDetail.getErialNumber() == null || plmMinusandAddDetail.getErialNumber() == 0) {
			resultEntity.setRet(AjaxResultEntity.ERROR_PARAM);
			resultEntity.setMessage("id或耗材数量为空！");
			response.getWriter().print(JSONObject.fromObject(resultEntity).toString());
			return;
		}
		plmMinusandAddDetailService.updateNum(plmMinusandAddDetail);
		resultEntity.setRet(AjaxResultEntity.ERROR_OK);
		response.getWriter().print(JSONObject.fromObject(resultEntity).toString());
	}
	
	/**
	 * 同类型物资统计列表
	 * @author dongqikai
	 * @return list
	 */
	@RequestMapping(value = "countEquipmentByType")
	public String countEquipmentByType(PlmEquipment plmEquipment, Model model) {
		if (StringUtils.isNotBlank(plmEquipment.getIncomingId())) {
			incomingId = plmEquipment.getIncomingId();
		}
		List<PlmEquipment> returnList = new ArrayList<>();
		if (StringUtils.isBlank(plmEquipment.getTypeId())) {
			List<PlmEquipment> equipmentList = plmEquipmentService.countEquipmentByType(plmEquipment);//除耗材外的模板list
			returnList.addAll(equipmentList);
			plmEquipment.setTypeId(PlmEquipment.TYPE_CONSUMABLE);
			List<PlmEquipment> equList = plmEquipmentService.findList(plmEquipment);//耗材模板list
			returnList.addAll(equList);
			plmEquipment.setTypeId(null);
		} else if (PlmEquipment.TYPE_CONSUMABLE.equals(plmEquipment.getTypeId())) {
			List<PlmEquipment> equList = plmEquipmentService.findList(plmEquipment);//耗材模板list
			returnList.addAll(equList);
		} else {
			List<PlmEquipment> equipmentList = plmEquipmentService.countEquipmentByType(plmEquipment);//除耗材外的模板list
			returnList.addAll(equipmentList);
		}
		model.addAttribute("equipmentList", returnList);
		return "plm/storage/plmMinusandAddDetailList";
	}
	
	
	
	
	
	/**
	 * 获取添加模板表单
	 * @param plmEquipment
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getEquipmentDemo")
	public String getEquipmentDemo(PlmEquipment plmEquipment, Model model) {
		PlmEquipment equipment = null;
		if (StringUtils.isNotBlank(plmEquipment.getId())) {
			equipment = plmEquipmentService.get(plmEquipment.getId());
		} else {
			equipment = plmEquipmentService.getDemoForm(plmEquipment);
		}
		PlmMinusandAddDetail plmMinusandAddDetail = new PlmMinusandAddDetail();
		if (equipment != null) {
			if (PlmEquipment.TYPE_CONSUMABLE.equals(equipment.getTypeId())) {
				plmMinusandAddDetail.setEquId(equipment.getId());
			}
			plmMinusandAddDetail.setParent(incomingId);
			plmMinusandAddDetail.setStorage(equipment.getStorage());
			plmMinusandAddDetail.setName(equipment.getName());
			plmMinusandAddDetail.setSpec(equipment.getSpec());
			plmMinusandAddDetail.setTypeId(equipment.getTypeId());
			plmMinusandAddDetail.setTypeChild(equipment.getTypeChild());
			plmMinusandAddDetail.setCategory(equipment.getCategory());
			plmMinusandAddDetail.setImgUrl(equipment.getImgUrl());
			plmMinusandAddDetail.setProductionBatch(equipment.getProductionBatch());
			plmMinusandAddDetail.setProductionDate(equipment.getProductionDate());
			plmMinusandAddDetail.setGuaranteePeriod(equipment.getGuaranteePeriod());
			plmMinusandAddDetail.setExpirationDate(equipment.getExpirationDate());
			plmMinusandAddDetail.setPrice(equipment.getPrice());
			plmMinusandAddDetail.setUnit(equipment.getUnit());
			plmMinusandAddDetail.setDurableYears(equipment.getDurableYears());
		}
		model.addAttribute("plmMinusandAddDetail", plmMinusandAddDetail);
		return "plm/storage/plmMinusandAddDetailForm";
	}
	
	/**
	 * 将单条详细单的物资入库保存
	 * @param id
	 * @throws IOException
	 */
	@RequestMapping(value = "incomingEquipment")
	public String incomingEquipment(@RequestParam(required = false) String id, Model model) {
		PlmMinusandAddDetail plmMinusandAddDetail = plmMinusandAddDetailService.get(id);
		List<PlmEquipment> returnList = saveEquipment(plmMinusandAddDetail);
		if (returnList == null || !(returnList.size() > 0)) {
			model.addAttribute("message", "未能正确获取编号方案信息，请检查是否配置正确!");
			return "plm/storage/qrCodeShowList";
		}
		printList.clear();
		printList.addAll(returnList);
		model.addAttribute("callbackList", returnList);
		return "plm/storage/qrCodeShowList";
	}
	
	/**
	 * 将物资进行入库保存
	 * @param plmMinusandAddDetail
	 * @return
	 */
	public List<PlmEquipment> saveEquipment(PlmMinusandAddDetail plmMinusandAddDetail) {
		PlmEquipment plmEquipment = new PlmEquipment();
		List<PlmEquipment> callbackList = new ArrayList<PlmEquipment>();
		if (StringUtils.isBlank(plmMinusandAddDetail.getEquId())) {//判断是否为模板耗材
		List<String> codes = null;
		plmEquipment.setMinusandId(plmMinusandAddDetail.getId());
		plmEquipment.setStorage(plmMinusandAddDetail.getStorage());
		plmEquipment.setName(plmMinusandAddDetail.getName());
		plmEquipment.setSpec(plmMinusandAddDetail.getSpec());
		plmEquipment.setTypeId(plmMinusandAddDetail.getTypeId());
		plmEquipment.setTypeChild(plmMinusandAddDetail.getTypeChild());
		plmEquipment.setCategory(plmMinusandAddDetail.getCategory());
		plmEquipment.setImgUrl(plmMinusandAddDetail.getImgUrl());
		plmEquipment.setProductionBatch(plmMinusandAddDetail.getProductionBatch());
		plmEquipment.setProductionDate(plmMinusandAddDetail.getProductionDate());
		plmEquipment.setGuaranteePeriod(plmMinusandAddDetail.getGuaranteePeriod());
		plmEquipment.setExpirationDate(plmMinusandAddDetail.getExpirationDate());
		plmEquipment.setPrice(plmMinusandAddDetail.getPrice());
		plmEquipment.setUnit(plmMinusandAddDetail.getUnit());
		plmEquipment.setDurableYears(plmMinusandAddDetail.getDurableYears());
			if ("0".equals(plmEquipment.getTypeId())) {
				plmEquipment.setErialNumber(plmMinusandAddDetail.getErialNumber());
				codes = sysCodesService.getCode(PlmEquipment.class.getName(), 1);
			} else {
				codes = sysCodesService.getCode(PlmEquipment.class.getName(), plmMinusandAddDetail.getErialNumber());
			}
			if (codes == null || !(codes.size() > 0)) return callbackList;
			for (int i = 0; i < codes.size(); i++) {
				PlmEquipment equipment = plmEquipment.clone();
				equipment.setCode(codes.get(i));
				plmEquipmentService.save(equipment);
				try {
					equipment.setQrCode(plmMinusandAddDetailService.qrCodeWithBase64(codes.get(i)));
				} catch (IOException e) {
					e.printStackTrace();
				}
				callbackList.add(equipment);
			}
		} else {
			PlmEquipment equConsumable = plmEquipmentService.get(plmMinusandAddDetail.getEquId());
			equConsumable.setErialNumber(equConsumable.getErialNumber() + plmMinusandAddDetail.getErialNumber());
			plmEquipmentService.save(equConsumable);
			try {
				equConsumable.setQrCode(plmMinusandAddDetailService.qrCodeWithBase64(equConsumable.getCode()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			callbackList.add(equConsumable);
		}
		plmMinusandAddDetailService.updateFlag(plmMinusandAddDetail.getId());
		return callbackList;
	}
	
	/**
	 * 将所有详细单的物资入库保存
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "incomingEquipmentAll")
	public String incomingEquipmentAll(@RequestParam(required = false) String id, Model model) {
		PlmMinusandAddDetail plmMinusandAddDetail = new PlmMinusandAddDetail();
		plmMinusandAddDetail.setParent(id);
		plmMinusandAddDetail.setIsConditionFlag(PlmMinusandAddDetail.CONDITION_FLAG);
		List<PlmMinusandAddDetail> detailList = plmMinusandAddDetailService.findList(plmMinusandAddDetail);
		List<PlmEquipment> allIncomingList = new ArrayList<PlmEquipment>();
		for (PlmMinusandAddDetail detail : detailList) {
			List<PlmEquipment> equList = saveEquipment(detail);
			allIncomingList.addAll(equList);
		}
		printList.clear();
		printList.addAll(allIncomingList);
		PlmIncomingEntry plmIncomingEntry = new PlmIncomingEntry();
		plmIncomingEntry.setId(id);
		plmIncomingEntry.setType(PlmIncomingEntry.COMPLETE_IN);
		plmIncomingEntryService.updateIncomingStatus(plmIncomingEntry);
		model.addAttribute("callbackList", allIncomingList);
		return "plm/storage/qrCodeShowList";
	}
	
	/**
	 * 打印页面跳转
	 * @param code
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "print")
	public String qrCode(Model model) throws IOException {
		model.addAttribute("callbackList", printList);
		return "plm/storage/printQrCode";
	}

	@RequestMapping(value = "index")
	public String index(PlmMinusandAddDetail plmMinusandAddDetail,HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmMinusandAddDetail> page=new Page<PlmMinusandAddDetail>(request, response);
		plmMinusandAddDetail.setPage(page);
		List<PlmMinusandAddDetail> detailList = plmMinusandAddDetailService.findIndexList(plmMinusandAddDetail);
		page.setList(detailList);
		model.addAttribute("page", page);
		model.addAttribute("detailList", detailList);//入库明细列表
		return "plm/storage/plmMinusandAddDetailIndex";
	}
	
	@RequestMapping(value = "giveBack")
	public String giveBack(PlmMinusandAddDetail plmMinusandAddDetail,HttpServletRequest request, HttpServletResponse response, Model model) {
		List<PlmMinusandAddDetail> list=plmMinusandAddDetailService.findGiveBack(plmMinusandAddDetail);
		model.addAttribute("list",list);
		return "plm/storage/plmEquipmentGiveBackList";
	}
	
	/**
	 * 根据编号获取资产信息
	 * @param id
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "getEquipmentGiveBackCode")
	@ResponseBody
	public void getEquipmentById(@RequestParam(required = true) String equipmentCode, HttpServletRequest request, HttpServletResponse response) throws IOException {
		AjaxResultEntity resultEntity = new AjaxResultEntity();
		if (StringUtils.isBlank(equipmentCode)) {
			resultEntity.setRet(AjaxResultEntity.ERROR_PARAM);
			resultEntity.setMessage("物资编号为空！");
			response.getWriter().print(JSONObject.fromObject(resultEntity).toString());
			return;
		}
		PlmMinusandAddDetail plmMinusandAddDetail = new PlmMinusandAddDetail();
		plmMinusandAddDetail.setEquipmentCode(equipmentCode);;
		List<PlmMinusandAddDetail> equList=plmMinusandAddDetailService.findGiveBack(plmMinusandAddDetail);
		if (equList == null || !(equList.size() > 0) || equList.get(0) == null) {
			resultEntity.setRet(AjaxResultEntity.ERROR_PARAM);
			resultEntity.setMessage("该物资信息不存在出库信息！");
			response.getWriter().print(JSONObject.fromObject(resultEntity).toString());
			return;
		}
		plmMinusandAddDetail=equList.get(0);
		plmMinusandAddDetailService.updateId(plmMinusandAddDetail.getId());
		plmEquipmentService.updateGiveBack(plmMinusandAddDetail.getEquipmentCode());
		StringBuffer buffer = new StringBuffer();
		buffer.append("<tr>");
		buffer.append("<td style=\"display: none;\"><input name=\"equIds\" type=\"text\" value=\"" + plmMinusandAddDetail.getId() + "\"/></td>");
		buffer.append(getTd(plmMinusandAddDetail.getEquipmentCode()));
		buffer.append(getTd(plmMinusandAddDetail.getName()));
		buffer.append(getTd(plmMinusandAddDetail.getSpec()));
		buffer.append(getTd(DictUtils.getDictLabel(plmMinusandAddDetail.getTypeId(), "plm_equipment_type", "未知")));
		buffer.append(getTd(DictUtils.getDictLabel(plmMinusandAddDetail.getTypeChild(), "plm_equipment_type_child", "未知")));
		buffer.append(getTd(PlmIncomingEntry.DEFAULT_NUM));
		buffer.append(getTd("已归还"));
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
	
}
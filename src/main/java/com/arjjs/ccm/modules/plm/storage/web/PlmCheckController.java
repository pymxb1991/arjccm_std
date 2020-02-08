/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.storage.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.utils.excel.ImportExcel;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.plm.storage.entity.AjaxResultEntity;
import com.arjjs.ccm.modules.plm.storage.entity.PlmCheck;
import com.arjjs.ccm.modules.plm.storage.entity.PlmCheckDetial;
import com.arjjs.ccm.modules.plm.storage.entity.PlmEquipment;
import com.arjjs.ccm.modules.plm.storage.entity.PlmIncomingEntry;
import com.arjjs.ccm.modules.plm.storage.service.PlmCheckDetialService;
import com.arjjs.ccm.modules.plm.storage.service.PlmCheckService;
import com.arjjs.ccm.modules.plm.storage.service.PlmEquipmentService;

import net.sf.json.JSONObject;

/**
 * 物资盘点Controller
 * @author dongqikai
 * @version 2018-07-10
 */
@Controller
@RequestMapping(value = "${adminPath}/storage/plmCheck")
public class PlmCheckController extends BaseController {

	@Autowired
	private PlmCheckService plmCheckService;
	
	@Autowired
	private PlmCheckDetialService plmCheckDetialService;
	
	@Autowired
	private PlmEquipmentService plmEquipmentService;
	
	@ModelAttribute
	public PlmCheck get(@RequestParam(required=false) String id) {
		PlmCheck entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmCheckService.get(id);
		}
		if (entity == null){
			entity = new PlmCheck();
		}
		return entity;
	}
	
	@RequiresPermissions("storage:plmCheck:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmCheck plmCheck, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmCheck> page = plmCheckService.findPage(new Page<PlmCheck>(request, response), plmCheck); 
		model.addAttribute("page", page);
		return "plm/storage/plmCheckList";
	}

	@RequiresPermissions("storage:plmCheck:view")
	@RequestMapping(value = "form")
	public String form(PlmCheck plmCheck, Model model) {
		if (StringUtils.isBlank(plmCheck.getId())) {
			plmCheck.setStatus(PlmCheck.READY_CHECK);
		}
		model.addAttribute("plmCheck", plmCheck);
		return "plm/storage/plmCheckForm";
	}

	@RequiresPermissions("storage:plmCheck:edit")
	@RequestMapping(value = "save")
	public String save(PlmCheck plmCheck, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmCheck)){
			return form(plmCheck, model);
		}
		plmCheckDetialService.saveCheckEquipment(plmCheck);
		plmCheckService.save(plmCheck);
		addMessage(redirectAttributes, "保存盘点单成功");
		return form(plmCheck, model);
	}
	
	@RequiresPermissions("storage:plmCheck:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmCheck plmCheck, RedirectAttributes redirectAttributes) {
		plmCheckService.delete(plmCheck);
		addMessage(redirectAttributes, "删除盘点单成功");
		return "redirect:"+Global.getAdminPath()+"/storage/plmCheck/?repage";
	}
	
	/**
	 * 盘点页面跳转
	 * @return
	 */
	@RequiresPermissions("storage:plmCheck:view")
	@RequestMapping(value="checkInfo")
	public String checkInfo(@RequestParam(required=true) String id, Model model) {
		PlmCheck plmCheck = plmCheckService.get(id);
		PlmCheckDetial condition = new PlmCheckDetial();
		condition.setParent(id);
		condition.setFlag(PlmCheckDetial.COMPLETE_CHECK);
		List<PlmCheckDetial> detailList = plmCheckDetialService.findList(condition);
		model.addAttribute("detailList", detailList);
		model.addAttribute("plmCheck", plmCheck);
		return "plm/storage/plmCheckDetialList";
	}
	
	/**
	 * 盘点信息比对结果
	 * @param code
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="getCheckInfo")
	@ResponseBody
	public AjaxResultEntity getCheckInfo(PlmCheckDetial plmCheckDetail, HttpServletRequest request, HttpServletResponse response) throws IOException {
		AjaxResultEntity resultEntity = new AjaxResultEntity();
		if (StringUtils.isBlank(plmCheckDetail.getCode())) {
			resultEntity.setRet(AjaxResultEntity.ERROR_PARAM);
			resultEntity.setMessage("物资编号为空！");
			return resultEntity;
		}
		PlmCheckDetial detail = new PlmCheckDetial();
		detail.setCode(plmCheckDetail.getCode());
		detail.setFlag(null);
		List<PlmCheckDetial> detailList = plmCheckDetialService.findList(detail);
		//物品如果不在盘点物资范围内，则将该物资存入并标记为多余状态
		if (detailList == null || !(detailList.size() > 0) || detailList.get(0) == null) {
			PlmEquipment equipment = new PlmEquipment();
			equipment.setCode(plmCheckDetail.getCode());
			equipment.setType(null);
			List<PlmEquipment> equList = plmEquipmentService.findList(equipment);
			if (equList == null || !(equList.size() > 0) || equList.get(0) == null) {
				resultEntity.setRet(AjaxResultEntity.ERROR_PARAM);
				resultEntity.setMessage("该物资信息不存在！");
				return resultEntity;
			}
			equipment = equList.get(0);
			PlmCheckDetial plmCheckDetial = new PlmCheckDetial();
			plmCheckDetial.setStorage(equipment.getStorage());
			plmCheckDetial.setParent(plmCheckDetail.getParent());
			plmCheckDetial.setCode(equipment.getCode());
			plmCheckDetial.setName(equipment.getName());
			plmCheckDetial.setSpec(equipment.getSpec());
			plmCheckDetial.setTypeId(equipment.getTypeId());
			plmCheckDetial.setTypeChild(equipment.getTypeChild());
			if (PlmIncomingEntry.CONSUMABLE_TYPE.equals(equipment.getTypeId())) {
				plmCheckDetial.setRemainingQuantity(equipment.getErialNumber() - equipment.getUseNumber());
			} else {
				plmCheckDetial.setRemainingQuantity(equipment.getErialNumber());
			}
			plmCheckDetial.setUnit(equipment.getUnit());
			plmCheckDetial.setStatus(PlmCheckDetial.SURPLUS);
			plmCheckDetial.setFlag(PlmCheckDetial.COMPLETE_CHECK);
			plmCheckDetialService.save(plmCheckDetial);
			resultEntity.setRet(AjaxResultEntity.ERROR_OK);
			resultEntity.setResult(plmCheckDetialService.getHtml(null, equipment));
			return resultEntity;
		}
		PlmCheckDetial checkDetial = detailList.get(0);
		if (PlmCheckDetial.COMPLETE_CHECK.equals(checkDetial.getFlag())) {
			resultEntity.setRet(AjaxResultEntity.ERROR_PARAM);
			resultEntity.setMessage("该物资已经经过盘点！");
			return resultEntity;
		}
		resultEntity.setRet(AjaxResultEntity.ERROR_OK);
		checkDetial.setStatus(PlmCheckDetial.NORMAL);
		checkDetial.setFlag(PlmCheckDetial.COMPLETE_CHECK);
		plmCheckDetialService.save(checkDetial);
		resultEntity.setResult(plmCheckDetialService.getHtml(checkDetial, null));
		return resultEntity;
	}
	
	/**
	 * 导入盘点
	 * @param file
	 * @param request
	 * @param response
	 */
	@RequiresPermissions("storage:plmCheck:edit")
	@RequestMapping(value="importCheckCode")
	@ResponseBody
	public void importCheckCode(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultEntity entity = new AjaxResultEntity();
		if (!file.isEmpty()) {
			BufferedReader reader = null;
			InputStream inputStream = null;
			ArrayList<String> codes = new ArrayList<String>();
			try {
				ImportExcel ei = new ImportExcel(file, 1, 0);
				List<PlmCheckDetial> list = ei.getDataList(PlmCheckDetial.class);
				for (PlmCheckDetial plmCheckDetial : list) {
					codes.add(plmCheckDetial.getCode());
				}
				List<String> resultList = plmCheckDetialService.checkAll(codes);
				if (resultList == null || resultList.isEmpty()) {
					entity.setMessage("比对完成，无正常记录！");
					response.getWriter().print(JSONObject.fromObject(entity).toString());
					return;
				}
				entity.setRet(AjaxResultEntity.ERROR_OK);
				entity.setResult(resultList);
				response.getWriter().print(JSONObject.fromObject(entity).toString());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * 确认盘点完成
	 * @param parent
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequiresPermissions("storage:plmCheck:edit")
	@RequestMapping(value="checkComplete")
	@ResponseBody
	public void checkComplete(@RequestParam("parent") String parent, HttpServletRequest request, HttpServletResponse response) throws IOException {
		AjaxResultEntity entity = new AjaxResultEntity();
		if (StringUtils.isBlank(parent)) {
			entity.setRet(AjaxResultEntity.ERROR_PARAM);
			entity.setMessage("盘点单号为空！");
			response.getWriter().print(JSONObject.fromObject(entity).toString());
			return;
		}
		List<String> htmls = plmCheckDetialService.checkComplete(parent);
		entity.setRet(AjaxResultEntity.ERROR_OK);
		entity.setResult(htmls);
		response.getWriter().print(JSONObject.fromObject(entity).toString());
	}

}
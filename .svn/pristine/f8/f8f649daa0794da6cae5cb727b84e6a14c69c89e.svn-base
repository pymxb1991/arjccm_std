/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.storage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.plm.storage.dao.PlmCheckDao;
import com.arjjs.ccm.modules.plm.storage.dao.PlmCheckDetialDao;
import com.arjjs.ccm.modules.plm.storage.dao.PlmEquipmentDao;
import com.arjjs.ccm.modules.plm.storage.entity.PlmCheck;
import com.arjjs.ccm.modules.plm.storage.entity.PlmCheckDetial;
import com.arjjs.ccm.modules.plm.storage.entity.PlmEquipment;
import com.arjjs.ccm.modules.plm.storage.entity.PlmIncomingEntry;
import com.arjjs.ccm.modules.plm.storage.web.PlmOutController;
import com.arjjs.ccm.modules.sys.utils.DictUtils;

/**
 * 盘点详细Service
 * @author dongqikai
 * @version 2018-07-10
 */
@Service
@Transactional(readOnly = true)
public class PlmCheckDetialService extends CrudService<PlmCheckDetialDao, PlmCheckDetial> {
	
	@Autowired
	private PlmCheckDao plmCheckDao;
	
	@Autowired
	private PlmEquipmentDao plmEquipmentDao;
	
	@Autowired
	private PlmCheckDetialDao plmCheckDetialDao;
	
	public PlmCheckDetial get(String id) {
		return super.get(id);
	}
	
	public List<PlmCheckDetial> findList(PlmCheckDetial plmCheckDetial) {
		return super.findList(plmCheckDetial);
	}
	
	public Page<PlmCheckDetial> findPage(Page<PlmCheckDetial> page, PlmCheckDetial plmCheckDetial) {
		return super.findPage(page, plmCheckDetial);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmCheckDetial plmCheckDetial) {
		super.save(plmCheckDetial);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmCheckDetial plmCheckDetial) {
		super.delete(plmCheckDetial);
	}
	
	@Transactional(readOnly = false)
	public void saveCheckEquipment(PlmCheck plmCheck) {
		if (StringUtils.isNotBlank(plmCheck.getId())) {
			if (PlmCheck.CHECKING.equals(plmCheck.getStatus())) {
				PlmEquipment plmEquipment = new PlmEquipment();
				plmEquipment.setStorage(plmCheck.getStorage());
				plmEquipment.setTypeId(plmCheck.getType());
				plmEquipment.setTypeChild(plmCheck.getTypeChild());
				List<PlmEquipment> equList = plmEquipmentDao.findList(plmEquipment);
				if (equList == null || equList.isEmpty()) return;
				for (PlmEquipment equipment : equList) {
					PlmCheckDetial checkDetial = new PlmCheckDetial();
					checkDetial.setStorage(equipment.getStorage());
					checkDetial.setParent(plmCheck.getId());
					checkDetial.setCode(equipment.getCode());
					checkDetial.setName(equipment.getName());
					checkDetial.setSpec(equipment.getSpec());
					checkDetial.setTypeId(equipment.getTypeId());
					checkDetial.setTypeChild(equipment.getTypeChild());
					if (PlmIncomingEntry.CONSUMABLE_TYPE.equals(equipment.getTypeId())) {
						checkDetial.setRemainingQuantity(equipment.getErialNumber() - equipment.getUseNumber());
					} else {
						checkDetial.setRemainingQuantity(equipment.getErialNumber());
					}
					checkDetial.setUnit(equipment.getUnit());
					checkDetial.setStatus(PlmCheckDetial.MISSING);
					checkDetial.setFlag(PlmCheckDetial.NOT_CHECK);
					save(checkDetial);
				}
			}
		}
		
	}
	
	/**
	 * 拼接html
	 * @param buffer
	 * @param detial
	 * @param equipment
	 * @return
	 */
	public String getHtml(PlmCheckDetial detial, PlmEquipment equipment) {
		StringBuffer buffer = new StringBuffer();
		if (detial == null) {
			buffer.append("<tr>");
			buffer.append(PlmOutController.getTd(equipment.getCode()));
			buffer.append(PlmOutController.getTd(equipment.getName()));
			buffer.append(PlmOutController.getTd(equipment.getSpec()));
			buffer.append(PlmOutController.getTd(DictUtils.getDictLabel(equipment.getTypeId(), "plm_equipment_type", "未知")));
			buffer.append(PlmOutController.getTd(DictUtils.getDictLabel(equipment.getTypeChild(), "plm_equipment_type_child", "未知")));
			buffer.append(PlmOutController.getTd(equipment.getErialNumber().toString()));
			buffer.append(PlmOutController.getTd(equipment.getUnit()));
			buffer.append(PlmOutController.getTd(PlmCheckDetial.SURPLUS));
			buffer.append("</tr>");
		}
		if (equipment == null) {
			buffer.append("<tr>");
			buffer.append(PlmOutController.getTd(detial.getCode()));
			buffer.append(PlmOutController.getTd(detial.getName()));
			buffer.append(PlmOutController.getTd(detial.getSpec()));
			buffer.append(PlmOutController.getTd(DictUtils.getDictLabel(detial.getTypeId(), "plm_equipment_type", "未知")));
			buffer.append(PlmOutController.getTd(DictUtils.getDictLabel(detial.getTypeChild(), "plm_equipment_type_child", "未知")));
			buffer.append(PlmOutController.getTd(detial.getRemainingQuantity().toString()));
			buffer.append(PlmOutController.getTd(detial.getUnit()));
			if (PlmCheckDetial.MISSING.equals(detial.getStatus())) {
				buffer.append(PlmOutController.getTd(detial.getStatus()));
			} else {
				buffer.append(PlmOutController.getTd(PlmCheckDetial.NORMAL));
			}
			buffer.append("</tr>");
		}
		return buffer.toString();
	}
	
	/**
	 * 比对所有记录
	 * @param codes
	 * @return
	 */
	@Transactional(readOnly = false)
	public List<String> checkAll(ArrayList<String> codes) {
		if (codes == null || codes.isEmpty()) {
			return null;
		}
		ArrayList<String> htmls = new ArrayList<String>();
		List<PlmCheckDetial> detailList = plmCheckDetialDao.getDetails(codes.toArray(new String[codes.size()]));
		Map<String, PlmCheckDetial> mapDetail = new HashMap<>();
		for (String code : codes) {
			mapDetail.put(code, null);
		}
		for (PlmCheckDetial plmCheckDetial : detailList) {
			mapDetail.put(plmCheckDetial.getCode(), plmCheckDetial);
		}
		ArrayList<String> nullList = new ArrayList<String>();
		ArrayList<String> normalList = new ArrayList<String>();
		ArrayList<PlmCheckDetial> returnDetailList = new ArrayList<PlmCheckDetial>();
		String parentId = "";
		for (String code : mapDetail.keySet()) {
			PlmCheckDetial plmCheckDetial = mapDetail.get(code);
			if (plmCheckDetial == null) {
				nullList.add(code);
				continue;
			}
			normalList.add(code);
			returnDetailList.add(plmCheckDetial);
			parentId = plmCheckDetial.getParent();
		}
		if (nullList != null && nullList.size() > 0) {
			List<PlmEquipment> eqList = plmEquipmentDao.findByCodes(nullList.toArray(new String[nullList.size()]));
			for (PlmEquipment plmEquipment : eqList) {
				PlmCheckDetial detail = new PlmCheckDetial();
				detail.setStorage(plmEquipment.getStorage());
				detail.setParent(parentId);
				detail.setCode(plmEquipment.getCode());
				detail.setName(plmEquipment.getName());
				detail.setSpec(plmEquipment.getSpec());
				detail.setTypeId(plmEquipment.getTypeId());
				detail.setTypeChild(plmEquipment.getTypeChild());
				if (PlmIncomingEntry.CONSUMABLE_TYPE.equals(plmEquipment.getTypeId())) {
					detail.setRemainingQuantity(plmEquipment.getErialNumber() - plmEquipment.getUseNumber());
				} else {
					detail.setRemainingQuantity(plmEquipment.getErialNumber());
				}
				detail.setUnit(plmEquipment.getUnit());
				detail.setStatus(PlmCheckDetial.SURPLUS);
				detail.setFlag(PlmCheckDetial.COMPLETE_CHECK);
				save(detail);
				htmls.add(getHtml(null, plmEquipment));
			}
		}
		for (PlmCheckDetial plmCheckDetial : returnDetailList) {
			plmCheckDetial.setStatus(PlmCheckDetial.NORMAL);
			htmls.add(getHtml(plmCheckDetial, null));
		}
		if (normalList != null && normalList.size() > 0) {
			plmCheckDetialDao.updateAllStatus(normalList.toArray(new String[normalList.size()]));
		}
		return htmls;
	}
	
	/**
	 * 确认盘点完成
	 * @param parent
	 * @return
	 */
	@Transactional(readOnly = false)
	public List<String> checkComplete (String parent) {
		List<String> htmls = new ArrayList<String>();
		PlmCheckDetial selectCondition = new PlmCheckDetial();
		selectCondition.setParent(parent);
		selectCondition.setStatus(PlmCheckDetial.MISSING);
		List<PlmCheckDetial> checkDetials = findList(selectCondition);
		if (checkDetials != null && checkDetials.size() > 0) {
			for (PlmCheckDetial plmCheckDetial : checkDetials) {
				htmls.add(getHtml(plmCheckDetial, null));
			}
		}
		PlmCheck check = plmCheckDao.get(parent);
		if (check != null) {
			check.setStatus(PlmCheck.COMPLETE);
			plmCheckDao.update(check);
		}
		return htmls;
	}
	
}
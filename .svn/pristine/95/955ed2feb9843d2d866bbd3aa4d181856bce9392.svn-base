/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.storage.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.plm.storage.dao.PlmEquipmentDao;
import com.arjjs.ccm.modules.plm.storage.dao.PlmProvideInfoDao;
import com.arjjs.ccm.modules.plm.storage.entity.PlmEquipment;
import com.arjjs.ccm.modules.plm.storage.entity.PlmProvideInfo;
import com.arjjs.ccm.tool.EchartType;

/**
 * 装备物资Service
 * @author dongqikai
 * @version 2018-06-27
 */
@Service
@Transactional(readOnly = true)
public class PlmEquipmentService extends CrudService<PlmEquipmentDao, PlmEquipment> {
	
	@Autowired
	private PlmEquipmentDao plmEquipmentDao;
	
	@Autowired
	private PlmProvideInfoDao plmProvideInfoDao;
	
	public PlmEquipment get(String id) {
		return super.get(id);
	}
	
	public List<PlmEquipment> findList(PlmEquipment plmEquipment) {
		return super.findList(plmEquipment);
	}
	
	public Page<PlmEquipment> findPage(Page<PlmEquipment> page, PlmEquipment plmEquipment) {
		return super.findPage(page, plmEquipment);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmEquipment plmEquipment) {
		super.save(plmEquipment);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmEquipment plmEquipment) {
		super.delete(plmEquipment);
	}
	
	/**
	 * 获取供应商列表
	 * @author dongqikai
	 * @return
	 */
	public List<PlmProvideInfo> getProvidList() {
		PlmProvideInfo plmProvideInfo = new PlmProvideInfo();
		return plmProvideInfoDao.findList(plmProvideInfo);
	}
	
	/**
	 * 各倉庫物资统计列表
	 * @author xue
	 * @return list
	 */
	public List<PlmEquipment> countEquipmentByStorage(PlmEquipment plmEquipment) {
		return plmEquipmentDao.countEquipmentByStorage(plmEquipment);
	}
	/**
	 * 物资状态统计
	 * @author xue
	 * @return list
	 */
	public List<EchartType> countEquipmentByStats(){
		
		return plmEquipmentDao.countEquipmentByStats();
	};
	/**
	 * 根据类型统计   typechild=1 时 根据 子类型统计
	 * @author xue
	 * @return list
	 */
	public List<EchartType> ratioEquipmentByType(Map<String, Object> map){
		      //map:typechild=1 时 根据 子类型统计
		return dao.ratioEquipmentByType(map);
	}
	/**
	 * 同类型物资统计列表
	 * @author dongqikai
	 * @return list
	 */
	public List<PlmEquipment> countEquipmentByType(PlmEquipment plmEquipment) {
		return plmEquipmentDao.countEquipmentByType(plmEquipment);
	}
	
	/**
	 * 物资统计列表
	 * @author liuxue
	 * @return list
	 */
	public List<Map<String, Object>> countEquipmentByName(){
		return plmEquipmentDao.countEquipmentByName();
	}
	
	/**
	 * 总物资数量
	 * @return
	 */
	public  Integer countEquipment(){
		return plmEquipmentDao.countEquipment();
	};
	
	/**
	 * 物资类型数量（有多少种类型）
	 * @return
	 */
	public  Integer countByType(){
		return plmEquipmentDao.countByType();
	};
	/**
	 * 物资数  以月为时间轴
	 * @author xue
	 * @return Map
	 */

	public List<Map<String, Object>> countEquipmentByTypeDate(Map<String, Object> map){
		return plmEquipmentDao.countEquipmentByTypeDate(map);
		
	};
	/**
	 * 到期预警物资 
	 * @author xue
	 * @return
	 */
	public List<Map<String, Object>> warningEquipment(Map<String, Object> map){
		
		return plmEquipmentDao.warningEquipment(map);
	};
	/**
	 * 获取批量添加模板表单
	 * @param plmEquipment
	 * @return
	 */
	public PlmEquipment getDemoForm(PlmEquipment plmEquipment) {
		PlmEquipment demoForm = plmEquipmentDao.showCountDetailList(plmEquipment).get(0);
		return demoForm;
	}
	
	/**
	 * 根据id获取记录
	 * @param ids
	 * @return
	 */
	public List<PlmEquipment> findByIds(String[] ids) {
		if (ids == null || !(ids.length > 0)) {
			return null;
		}
		return plmEquipmentDao.findByIds(ids);
	}
	
	/**
	 * 修改物资表物资使用人和使用部门
	 * @param plmEquipment
	 * @return
	 */
	@Transactional(readOnly = false)
	public int updateUser(PlmEquipment plmEquipment) {
		return plmEquipmentDao.updateUser(plmEquipment);
	}
	/**
	 * 根据code修改物资表物资使用人和使用部门
	 * @param plmEquipment
	 * @return
	 */
	@Transactional(readOnly = false)
	public int updateUserAndUserJob(PlmEquipment plmEquipment) {
		
		return plmEquipmentDao.updateUserAndUserJob(plmEquipment);
	};
	/**
	 * 刷新占用状态
	 * @return
	 */
	@Transactional(readOnly = false)
	public int updateOccupyStatus(PlmEquipment plmEquipment) {
		return plmEquipmentDao.updateOccupyStatus(plmEquipment);
	}
	/**
	 * 后勤物资获取
	 * @return
	 */	
	public Page<PlmEquipment> findPageForLogi(Page<PlmEquipment> page, PlmEquipment plmEquipment) {
		plmEquipment.setPage(page);
		page.setList(plmEquipmentDao.findListForLogi(plmEquipment));
		return page;
	}	
	
	/**
	 * 根据物资类型统计数量
	 * @param plmEquipment
	 * @return
	 */
	public Page<PlmEquipment> countByEquType(Page<PlmEquipment> page, PlmEquipment plmEquipment) {
		List<PlmEquipment> equList = new ArrayList<>();
		plmEquipment.setPage(page);
		if (PlmEquipment.TYPE_CONSUMABLE.equals(plmEquipment.getTypeId())) {
			List<PlmEquipment> consumableCounts = dao.findList(plmEquipment);
			equList.addAll(consumableCounts);
		} else {
			List<PlmEquipment> allCounts = dao.countByEquType(plmEquipment);
			if (allCounts == null || allCounts.isEmpty()) return page;
			plmEquipment.setFlag(PlmEquipment.DETAIL_COUNT);
			plmEquipment.setPage(null);
			List<PlmEquipment> detailCounts = dao.countByEquType(plmEquipment);
			if (detailCounts == null || detailCounts.isEmpty()) return page;
			Map<String, PlmEquipment> detailCountsMap = new LinkedHashMap<>();
			for (PlmEquipment allCount : allCounts) {
				detailCountsMap.put(allCount.getOnlyFlag(), allCount);
			}
			for (PlmEquipment detailCount : detailCounts) {
				PlmEquipment temp = detailCountsMap.get(detailCount.getOnlyFlag());
				if (temp == null) {
					continue;
				}
				switch(detailCount.getType()) {
				case PlmEquipment.OCCUPY_STATUS : temp.setOccupyCounts(detailCount.getCounts()); break;
				case PlmEquipment.UNOCCUPIED_STATUS : temp.setUnoccupiedCounts(detailCount.getCounts()); break;
				case PlmEquipment.REPAIR_STATUS : temp.setRepairCounts(detailCount.getCounts()); break;
				case PlmEquipment.USING_STATUS : temp.setUsingCounts(detailCount.getCounts()); break;
				case PlmEquipment.SCRAP_STATUS : temp.setScrapCounts(detailCount.getCounts()); break;
				default : break;
				}
			}
			Iterator<String> iterator = detailCountsMap.keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				equList.add(detailCountsMap.get(key));
			}
		}
		page.setList(equList);
		return page;
	}
	
	/**
	 * 更新耗材使用数量
	 * @param plmEquipment
	 */
	@Transactional(readOnly = false)
	public void updateUseNum(PlmEquipment plmEquipment) {
		dao.updateUseNum(plmEquipment);
	}
	/**
	 * 查询物资
	 * @param plmEquipment
	 */
	public List<PlmEquipment> findListBySpec(PlmEquipment plmEquipment){
		return dao.findListBySpec(plmEquipment);
	}
	//修改物资状态
	@Transactional(readOnly = false)
	public int updateGiveBack(String code){
		return plmEquipmentDao.updateGiveBack(code);
	}
	public PlmEquipment findCode(String id){
		return plmEquipmentDao.findCode(id);
	}
}
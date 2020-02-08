/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.storage.dao;

import java.util.List;
import java.util.Map;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.storage.entity.PlmEquipment;
import com.arjjs.ccm.tool.EchartType;

/**
 * 装备物资DAO接口
 * @author dongqikai
 * @version 2018-06-27
 */
@MyBatisDao
public interface PlmEquipmentDao extends CrudDao<PlmEquipment> {
	
	
	  
	
	/**
	 * 各类型物资数  以月为时间轴
	 * @author xue
	 * @return Map
	 */

	List<Map<String, Object>> countEquipmentByTypeDate(Map<String, Object> map);
	
	/**
	 * 物资状态统计
	 * @author xue
	 * @return list
	 */
	public List<EchartType> countEquipmentByStats();
	/**
	 * 根据类型统计   typechild=1 时 根据 子类型统计
	 * @author xue
	 * @return list
	 */
	public List<EchartType> ratioEquipmentByType(Map<String, Object> map);
	  
	
	/**
	 * 到期预警物资 
	 * @author xue
	 * @return
	 */
	List<Map<String, Object>> warningEquipment(Map<String, Object> map);
	
	/**
	 * 各倉庫物资统计
	 * @author xue
	 * @return list
	 */
	public List<PlmEquipment> countEquipmentByStorage(PlmEquipment plmEquipment);
	
	/**
	 * 同类型物资统计列表
	 * @author dongqikai
	 * @return list
	 */
	public List<PlmEquipment> countEquipmentByType(PlmEquipment plmEquipment);
	/**
	 * 总物资数量
	 * @return
	 */
	public  Integer countEquipment();
	/**
	 * 物资类型数量（有多少种类型）
	 * @return
	 */
	Integer countByType();

	/**
	 * 物资统计列表
	 * @author liuxue
	 * @return list
	 */
	public List<Map<String, Object>> countEquipmentByName();
	
	/**
	 * 显示统计详细记录
	 * @author dongqikai
	 * @return list
	 */
	public List<PlmEquipment> showCountDetailList(PlmEquipment plmEquipment);
	
	/**
	 * 根据id获取记录
	 * @param ids
	 * @return
	 */
	public List<PlmEquipment> findByIds(String[] ids);
	
	/**
	 * 根据code获取记录
	 * @param ids
	 * @return
	 */
	public List<PlmEquipment> findByCodes(String[] codes);
	
	/**
	 * 修改物资表物资使用人和使用部门
	 * @param plmEquipment
	 * @return
	 */
	public int updateUser(PlmEquipment plmEquipment);
	/**
	 * 根据code修改物资表物资使用人和使用部门
	 * @param plmEquipment
	 * @return
	 */
	public int updateUserAndUserJob(PlmEquipment plmEquipment);
	/**
	 * 刷新占用状态
	 * @return
	 */
	public int updateOccupyStatus(PlmEquipment plmEquipment);
	/**
	 * 获取后勤管理装备
	 * @param ids
	 * @return
	 */
	public List<PlmEquipment> findListForLogi(PlmEquipment plmEquipment);	
	
	/**
	 * 根据物资类型统计数量
	 * @param plmEquipment
	 * @return
	 */
	public List<PlmEquipment> countByEquType(PlmEquipment plmEquipment);
	
	/**
	 * 更新耗材使用数量
	 * @param plmEquipment
	 */
	public void updateUseNum(PlmEquipment plmEquipment);
	/**
	 * 查询物资
	 * @param plmEquipment
	 */
	public List<PlmEquipment> findListBySpec(PlmEquipment plmEquipment);
	//修改物资状态
	public int updateGiveBack(String code);
	
	public PlmEquipment findCode(String id);
	
}
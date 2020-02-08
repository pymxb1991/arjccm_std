/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.act.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.act.dao.ActUtConfigDao;
import com.arjjs.ccm.modules.act.entity.ActUtConfig;
import com.arjjs.ccm.modules.act.service.ActTaskService;
import com.arjjs.ccm.modules.plm.act.dao.PlmActDao;
import com.arjjs.ccm.modules.plm.act.entity.PlmAct;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.EchartType;
import com.google.common.collect.Maps;

/**
 * 业务申请单主表Service
 * @author fu
 * @version 2018-07-20
 */
@Service
@Transactional(readOnly = true)
public class PlmActService extends CrudService<PlmActDao, PlmAct> {
	@Autowired
	private ActUtConfigDao actUtConfigDao;
	@Autowired
	private ActTaskService actTaskService;
	
	public PlmAct get(String id) {
		return super.get(id);
	}
	
	public List<PlmAct> findList(PlmAct plmAct) {
		return super.findList(plmAct);
	}
	
	public Page<PlmAct> findPage(Page<PlmAct> page, PlmAct plmAct) {
		return super.findPage(page, plmAct);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmAct plmAct) {
		super.save(plmAct);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmAct plmAct) {
		super.delete(plmAct);
	}
	@Transactional(readOnly = false)
	public void save(PlmAct plmAct, String configId,String tableId) {
		save(plmAct, configId,tableId,"");
	}
	@Transactional(readOnly = false)
	public void save(PlmAct plmAct, String configId,String tableId,String procInsId) {
		ActUtConfig actUtConfig = actUtConfigDao.get(configId);
		//如果首次创建流程业务表单
		if(StringUtils.isBlank(plmAct.getId())){
			//如果无标题，则添加默认标题
			if(StringUtils.isBlank(plmAct.getTitle())){
				plmAct.setTitle(actUtConfig.getTitle());
			}
			plmAct.setType(actUtConfig.getProcessId());
			plmAct.setTableName(actUtConfig.getTable());
			plmAct.setTableId(tableId);
			plmAct.setStatus("01");
			plmAct.setFormUrl(actUtConfig.getFormKeyName());
		}
		//如果督办（即已指定督办人），添加督办发起人
		if(StringUtils.isNotBlank(plmAct.getSupExe().getId())){
			plmAct.setSupIni(UserUtils.getUser());
		}
		//如果已提交，修改状态 并 添加流程实例编号 和 流程定义编号
		if(StringUtils.isNotBlank(procInsId)){
			plmAct.setProcInsId(procInsId);
			plmAct.setStatus("02");
			String defID = findProcDefIdByKey(plmAct.getType());
			plmAct.setProcDefId(defID);
		}
		super.save(plmAct);
	}

	public String findProcDefIdByKey(String procDefKey){
		return dao.findProcDefIdByKey(procDefKey);
	}
	
	public PlmAct getByTable(String tableId, String configId) {
		PlmAct plmAct = new PlmAct();
		ActUtConfig actUtConfig = actUtConfigDao.get(configId);
		plmAct.setTableId(tableId);
		plmAct.setTableName(actUtConfig.getTable());
		return dao.getByTable(plmAct);
	}

	public void updateStatus(PlmAct plmAct) {
		dao.updateStatus(plmAct);
		
	}
	@Transactional(readOnly = false)
	public void updateStatusToDestory(PlmAct plmAct) {
		plmAct.setStatus("03");
		dao.updateStatus(plmAct);
	}
	@Transactional(readOnly = false)
	public void updateStatusToEnd(PlmAct plmAct) {
		plmAct.setStatus("04");
		dao.updateStatus(plmAct);
	}

	public String getFormUrl(PlmAct plmAct) {
		StringBuilder formUrl = new StringBuilder();
		formUrl.append(Global.getAdminPath());
		formUrl.append(plmAct.getFormUrl()).append(formUrl.indexOf("?") == -1 ? "?" : "&");
		formUrl.append("&id=").append(plmAct.getTableId() != null ? plmAct.getTableId() : "");
		formUrl.append("&cancelFlag=").append(plmAct.getStatus() != null ? plmAct.getStatus() : "");
		return formUrl.toString();
	}

	public Page<PlmAct> findSupFinishPage(Page<PlmAct> page, PlmAct plmAct) {
		plmAct.setPage(page);
		page.setList(dao.findSupFinishList(plmAct));
		return page;
	}
	@Transactional(readOnly = false)
	public void updateSup(PlmAct plmAct) {
		plmAct.setSupIni(UserUtils.getUser());
		dao.updateSup(plmAct);
		
	}
	   /**
	    * 流程趋势  以天为时间轴
	    * @param map
	    * @return
	    */
	public  List<EchartType> countActByDate(Map<String, Object> map){
		return dao.countActByDate(map);
	};
		
		   /**
		    * 各个流程申请数量
		    * @param map
		    * @return
		    */
	public  Integer countAct(){
		return dao.countAct();
	};
	
	/**
	 * 流程各状态数量
	 * @param map(map: type)
	 * @return
	 */
	public List<EchartType> countActByStatus(Map<String, Object> map){
	   
		return dao.countActByStatus(map);
	};
	
	public List<EchartType> countActByType(Map<String, Object> map){
		   
		return dao.countActByType(map);
	};
	/**
	 * 流程督办数量
	 * @param map(map: type)
	 * @return
	 */
	public Integer countActBySup(Map<String, Object> map){
		
		return dao.countActBySup(map);
	};
	
	/**
	 * 流程撤销
	 * @param procInsId
	 */
	@Transactional(readOnly = false)
	public void cancelApply(String procInsId) {
		Map<String, Object> vars = Maps.newHashMap();
		actTaskService.jumpTask(procInsId, "modify", vars);
	}
	
}
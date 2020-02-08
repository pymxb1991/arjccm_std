/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.allot.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.act.service.ActTaskService;
import com.arjjs.ccm.modules.plm.allot.dao.PlmAllotDao;
import com.arjjs.ccm.modules.plm.allot.dao.PlmAllotDetailDao;
import com.arjjs.ccm.modules.plm.allot.entity.PlmAllot;
import com.arjjs.ccm.modules.plm.allot.entity.PlmAllotDetail;
import com.arjjs.ccm.modules.sys.service.SysCodesService;
import com.google.common.collect.Maps;

/**
 * 内部调拨Service
 * @author dongqikai
 * @version 2018-08-16
 */
@Service
@Transactional(readOnly = true)
public class PlmAllotService extends CrudService<PlmAllotDao, PlmAllot> {
	
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private SysCodesService sysCodesService;
	@Autowired
	private PlmAllotDetailDao plmAllotDetailDao;
	@Autowired
	private PlmAllotDetailService plmAllotDetailService;

	public PlmAllot get(String id) {
		return super.get(id);
	}
	
	public List<PlmAllot> findList(PlmAllot plmAllot) {
		return super.findList(plmAllot);
	}
	
	public Page<PlmAllot> findPage(Page<PlmAllot> page, PlmAllot plmAllot) {
		return super.findPage(page, plmAllot);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmAllot plmAllot) {
		if (StringUtils.isBlank(plmAllot.getCode())) {
			String code = sysCodesService.getCode(PlmAllot.class.getName(), 1).get(0);
			plmAllot.setCode(code);
		}
		super.save(plmAllot);
		plmAllotDetailDao.deleteByAllotId(plmAllot.getId());
		String[] selectRemaks = plmAllot.getSelectRemarks().split("@");
		for (String remakInfo : selectRemaks) {
			String[] infos = remakInfo.split(":");
			PlmAllotDetail allotDetail = new PlmAllotDetail();
			allotDetail.setAllotId(plmAllot.getId());
			allotDetail.setEquCode(infos[0]);
			if(infos.length>1) {
				allotDetail.setRemarks(infos[1]);
			}
			
			plmAllotDetailService.save(allotDetail);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmAllot plmAllot) {
		super.delete(plmAllot);
	}
	
	@Transactional(readOnly = false)
	public void auditSave(PlmAllot plmAllot) {
		// 设置意见
		plmAllot.getAct().setComment(("yes".equals(plmAllot.getAct().getFlag())?"[同意] ":"[驳回] ") + plmAllot.getAct().getComment());
		plmAllot.preUpdate();
		
		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("pass", "yes".equals(plmAllot.getAct().getFlag())? "1" : "0");
		actTaskService.complete(plmAllot.getAct().getTaskId(), plmAllot.getAct().getProcInsId(), plmAllot.getAct().getComment(), vars);
	}
	
}
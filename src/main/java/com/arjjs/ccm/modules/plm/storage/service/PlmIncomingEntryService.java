/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.storage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.plm.storage.dao.PlmIncomingEntryDao;
import com.arjjs.ccm.modules.plm.storage.entity.PlmIncomingEntry;
import com.arjjs.ccm.modules.sys.service.SysCodesService;

/**
 * 入库单Service
 * @author dongqikai
 * @version 2018-06-30
 */
@Service
@Transactional(readOnly = true)
public class PlmIncomingEntryService extends CrudService<PlmIncomingEntryDao, PlmIncomingEntry> {
	
	@Autowired
	private SysCodesService sysCodesService;

	public PlmIncomingEntry get(String id) {
		return super.get(id);
	}
	
	public List<PlmIncomingEntry> findList(PlmIncomingEntry plmIncomingEntry) {
		return super.findList(plmIncomingEntry);
	}
	
	public Page<PlmIncomingEntry> findPage(Page<PlmIncomingEntry> page, PlmIncomingEntry plmIncomingEntry) {
		return super.findPage(page, plmIncomingEntry);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmIncomingEntry plmIncomingEntry) {
		if (StringUtils.isBlank(plmIncomingEntry.getIncomingCode())) {
			String code = sysCodesService.getCode(PlmIncomingEntry.class.getName(), 1).get(0);
			plmIncomingEntry.setIncomingCode(code);
		}
		super.save(plmIncomingEntry);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmIncomingEntry plmIncomingEntry) {
		super.delete(plmIncomingEntry);
	}
	
	/**
	 * 修改入库状态
	 * @param plmIncomingEntry
	 */
	@Transactional(readOnly = false)
	public void updateIncomingStatus(PlmIncomingEntry plmIncomingEntry) {
		dao.updateIncomingStatus(plmIncomingEntry);
	}
	
}
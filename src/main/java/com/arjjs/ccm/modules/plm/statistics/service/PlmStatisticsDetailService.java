/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.statistics.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.plm.statistics.dao.PlmStatisticsDetailDao;
import com.arjjs.ccm.modules.plm.statistics.entity.PlmStatisticsDetail;

/**
 * 统计首页明细Service
 * @author liuxue
 * @version 2018-07-24
 */
@Service
@Transactional(readOnly = true)
public class PlmStatisticsDetailService extends CrudService<PlmStatisticsDetailDao, PlmStatisticsDetail> {

	public PlmStatisticsDetail get(String id) {
		return super.get(id);
	}
	
	public List<PlmStatisticsDetail> findList(PlmStatisticsDetail plmStatisticsDetail) {
		return super.findList(plmStatisticsDetail);
	}
	
	public Page<PlmStatisticsDetail> findPage(Page<PlmStatisticsDetail> page, PlmStatisticsDetail plmStatisticsDetail) {
		return super.findPage(page, plmStatisticsDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmStatisticsDetail plmStatisticsDetail) {
		super.save(plmStatisticsDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmStatisticsDetail plmStatisticsDetail) {
		super.delete(plmStatisticsDetail);
	}
	
}
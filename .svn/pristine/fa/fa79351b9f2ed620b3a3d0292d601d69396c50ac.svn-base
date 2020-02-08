/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.statistics.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.plm.statistics.dao.PlmStatisticsPlanDao;
import com.arjjs.ccm.modules.plm.statistics.entity.PlmStatisticsPlan;

/**
 * 统计首页方案Service
 * @author liuxue
 * @version 2018-07-24
 */
@Service
@Transactional(readOnly = true)
public class PlmStatisticsPlanService extends CrudService<PlmStatisticsPlanDao, PlmStatisticsPlan> {

	public PlmStatisticsPlan get(String id) {
		return super.get(id);
	}
	
	public List<PlmStatisticsPlan> findList(PlmStatisticsPlan plmStatisticsPlan) {
		return super.findList(plmStatisticsPlan);
	}
	
	public Page<PlmStatisticsPlan> findPage(Page<PlmStatisticsPlan> page, PlmStatisticsPlan plmStatisticsPlan) {
		return super.findPage(page, plmStatisticsPlan);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmStatisticsPlan plmStatisticsPlan) {
		super.save(plmStatisticsPlan);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmStatisticsPlan plmStatisticsPlan) {
		super.delete(plmStatisticsPlan);
	}
	
}
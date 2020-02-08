/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.plm.statistics.dao.PlmStatisticsDao;
import com.arjjs.ccm.modules.plm.statistics.entity.PlmStatistics;

/**
 * 统计首页Service
 * @author liuxue
 * @version 2018-07-24
 */
@Service
@Transactional(readOnly = true)
public class PlmStatisticsService extends CrudService<PlmStatisticsDao, PlmStatistics> {
   
	@Autowired
	PlmStatisticsDao plmStatisticsdao ;
	
	public PlmStatistics get(String id) {
		return super.get(id);
	}
	
	public List<PlmStatistics> findList(PlmStatistics plmStatistics) {
		return super.findList(plmStatistics);
	}
	
	public Page<PlmStatistics> findPage(Page<PlmStatistics> page, PlmStatistics plmStatistics) {
		return super.findPage(page, plmStatistics);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmStatistics plmStatistics) {
		super.save(plmStatistics);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmStatistics plmStatistics) {
		super.delete(plmStatistics);
	}
	@Transactional(readOnly = false)
	public void updatePageTitle(PlmStatistics plmStatistics) {
		plmStatisticsdao.updatePageTitle(plmStatistics);
		
	};
	
	@Transactional(readOnly = false)
	public void updatePages(PlmStatistics plmStatistics) {
		plmStatisticsdao.updatePages(plmStatistics);
		
	};
	@Transactional(readOnly = false)
	public int pagesMax() {
		return plmStatisticsdao.pagesMax();
		
	};
}
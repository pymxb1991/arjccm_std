/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.count.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.flat.analyst.entity.Count;
import com.arjjs.ccm.modules.kpi.count.dao.CcmKpiCountDao;
import com.arjjs.ccm.modules.kpi.count.entity.CcmKpiCount;

/**
 * 绩效统计实体类Service
 * @author lgh
 * @version 2019-07-17
 */
@Service
@Transactional(readOnly = true)
public class CcmKpiCountService extends CrudService<CcmKpiCountDao, CcmKpiCount> {
	
	@Autowired
	private CcmKpiCountDao ccmKpiCountDao;
	
	public List<Count> getRank() {
		return ccmKpiCountDao.getRank();
	}

	public CcmKpiCount get(String id) {
		return super.get(id);
	}
	
	public List<CcmKpiCount> findList(CcmKpiCount ccmKpiCount) {
		return super.findList(ccmKpiCount);
	}
	
	public Page<CcmKpiCount> findPage(Page<CcmKpiCount> page, CcmKpiCount ccmKpiCount) {
		return super.findPage(page, ccmKpiCount);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmKpiCount ccmKpiCount) {
		super.save(ccmKpiCount);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmKpiCount ccmKpiCount) {
		super.delete(ccmKpiCount);
	}
	
}
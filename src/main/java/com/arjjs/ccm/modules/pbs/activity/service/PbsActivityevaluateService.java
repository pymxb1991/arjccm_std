/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.activity.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivityevaluate;
import com.arjjs.ccm.modules.pbs.activity.dao.PbsActivityevaluateDao;

/**
 * 活动评分Service
 * @author lc
 * @version 2018-05-15
 */
@Service
@Transactional(readOnly = true)
public class PbsActivityevaluateService extends CrudService<PbsActivityevaluateDao, PbsActivityevaluate> {

	public PbsActivityevaluate get(String id) {
		return super.get(id);
	}
	
	public List<PbsActivityevaluate> findList(PbsActivityevaluate pbsActivityevaluate) {
		return super.findList(pbsActivityevaluate);
	}
	
	public Page<PbsActivityevaluate> findPage(Page<PbsActivityevaluate> page, PbsActivityevaluate pbsActivityevaluate) {
		return super.findPage(page, pbsActivityevaluate);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsActivityevaluate pbsActivityevaluate) {
		super.save(pbsActivityevaluate);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsActivityevaluate pbsActivityevaluate) {
		super.delete(pbsActivityevaluate);
	}
	
	@Transactional(readOnly = false)
	public void inserAll(List<PbsActivityevaluate> pbsActivityevaluates) {
		dao.insertAll(pbsActivityevaluates);
	}
	
}
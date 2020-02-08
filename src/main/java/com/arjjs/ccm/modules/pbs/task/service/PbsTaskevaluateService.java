/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.task.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.task.entity.PbsTaskevaluate;
import com.arjjs.ccm.modules.pbs.task.dao.PbsTaskevaluateDao;

/**
 * 工作安排反馈信息Service
 * @author lc
 * @version 2018-05-03
 */
@Service
@Transactional(readOnly = true)
public class PbsTaskevaluateService extends CrudService<PbsTaskevaluateDao, PbsTaskevaluate> {

	public PbsTaskevaluate get(String id) {
		return super.get(id);
	}
	
	public List<PbsTaskevaluate> findList(PbsTaskevaluate pbsTaskevaluate) {
		return super.findList(pbsTaskevaluate);
	}
	
	public Page<PbsTaskevaluate> findPage(Page<PbsTaskevaluate> page, PbsTaskevaluate pbsTaskevaluate) {
		return super.findPage(page, pbsTaskevaluate);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsTaskevaluate pbsTaskevaluate) {
		super.save(pbsTaskevaluate);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsTaskevaluate pbsTaskevaluate) {
		super.delete(pbsTaskevaluate);
	}
	
}
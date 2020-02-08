/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.exam.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.exam.entity.PbsExampaper;
import com.arjjs.ccm.modules.pbs.exam.dao.PbsExampaperDao;

/**
 * 试卷信息Service
 * @author lc
 * @version 2018-06-11
 */
@Service
@Transactional(readOnly = true)
public class PbsExampaperService extends CrudService<PbsExampaperDao, PbsExampaper> {

	public PbsExampaper get(String id) {
		return super.get(id);
	}
	
	public List<PbsExampaper> findList(PbsExampaper pbsExampaper) {
		return super.findList(pbsExampaper);
	}
	
	public Page<PbsExampaper> findPage(Page<PbsExampaper> page, PbsExampaper pbsExampaper) {
		return super.findPage(page, pbsExampaper);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsExampaper pbsExampaper) {
		super.save(pbsExampaper);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsExampaper pbsExampaper) {
		super.delete(pbsExampaper);
	}
	
	@Transactional(readOnly = false)
	public void updateStat(PbsExampaper pbsExampaper) {
		dao.updateStat(pbsExampaper);
	}
	
	
	public PbsExampaper findListByLast(){
		return dao.findListByLast(new PbsExampaper());
	}
}
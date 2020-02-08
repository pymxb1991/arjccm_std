/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.question.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.question.entity.PbsQuestionMajor;
import com.arjjs.ccm.modules.pbs.question.dao.PbsQuestionMajorDao;

/**
 * 专业信息Service
 * @author lc
 * @version 2018-04-11
 */
@Service
@Transactional(readOnly = true)
public class PbsQuestionMajorService extends CrudService<PbsQuestionMajorDao, PbsQuestionMajor> {

	public PbsQuestionMajor get(String id) {
		return super.get(id);
	}
	
	public List<PbsQuestionMajor> findList(PbsQuestionMajor pbsQuestionMajor) {
		return super.findList(pbsQuestionMajor);
	}
	
	public Page<PbsQuestionMajor> findPage(Page<PbsQuestionMajor> page, PbsQuestionMajor pbsQuestionMajor) {
		return super.findPage(page, pbsQuestionMajor);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsQuestionMajor pbsQuestionMajor) {
		super.save(pbsQuestionMajor);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsQuestionMajor pbsQuestionMajor) {
		super.delete(pbsQuestionMajor);
	}
	
}
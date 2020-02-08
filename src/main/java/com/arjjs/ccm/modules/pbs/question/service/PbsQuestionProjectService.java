/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.question.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.question.entity.PbsQuestionProject;
import com.arjjs.ccm.modules.pbs.question.dao.PbsQuestionProjectDao;

/**
 * 科目信息Service
 * @author lc
 * @version 2018-04-11
 */
@Service
@Transactional(readOnly = true)
public class PbsQuestionProjectService extends CrudService<PbsQuestionProjectDao, PbsQuestionProject> {

	public PbsQuestionProject get(String id) {
		return super.get(id);
	}
	
	public List<PbsQuestionProject> findList(PbsQuestionProject pbsQuestionProject) {
		return super.findList(pbsQuestionProject);
	}
	
	public Page<PbsQuestionProject> findPage(Page<PbsQuestionProject> page, PbsQuestionProject pbsQuestionProject) {
		return super.findPage(page, pbsQuestionProject);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsQuestionProject pbsQuestionProject) {
		super.save(pbsQuestionProject);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsQuestionProject pbsQuestionProject) {
		super.delete(pbsQuestionProject);
	}
	
}
/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.question.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.question.entity.PbsQuestionObjective;
import com.arjjs.ccm.modules.pbs.question.dao.PbsQuestionObjectiveDao;

/**
 * 客观题信息Service
 * @author lc
 * @version 2018-06-09
 */
@Service
@Transactional(readOnly = true)
public class PbsQuestionObjectiveService extends CrudService<PbsQuestionObjectiveDao, PbsQuestionObjective> {

	public PbsQuestionObjective get(String id) {
		return super.get(id);
	}
	
	public List<PbsQuestionObjective> findList(PbsQuestionObjective pbsQuestionObjective) {
		return super.findList(pbsQuestionObjective);
	}
	
	public Page<PbsQuestionObjective> findPage(Page<PbsQuestionObjective> page, PbsQuestionObjective pbsQuestionObjective) {
		return super.findPage(page, pbsQuestionObjective);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsQuestionObjective pbsQuestionObjective) {
		super.save(pbsQuestionObjective);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsQuestionObjective pbsQuestionObjective) {
		super.delete(pbsQuestionObjective);
	}
	
	List<PbsQuestionObjective> findListByExam(PbsQuestionObjective pbsQuestionObjective){
		
		return  dao.findListByExam(pbsQuestionObjective);
		
	}
}
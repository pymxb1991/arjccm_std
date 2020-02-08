/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.question.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.question.entity.PbsQuestionLevel;
import com.arjjs.ccm.modules.pbs.question.dao.PbsQuestionLevelDao;

/**
 * 考试难度级别Service
 * @author lc
 * @version 2018-06-06
 */
@Service
@Transactional(readOnly = true)
public class PbsQuestionLevelService extends CrudService<PbsQuestionLevelDao, PbsQuestionLevel> {

	public PbsQuestionLevel get(String id) {
		return super.get(id);
	}
	
	public List<PbsQuestionLevel> findList(PbsQuestionLevel pbsQuestionLevel) {
		return super.findList(pbsQuestionLevel);
	}
	
	public Page<PbsQuestionLevel> findPage(Page<PbsQuestionLevel> page, PbsQuestionLevel pbsQuestionLevel) {
		return super.findPage(page, pbsQuestionLevel);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsQuestionLevel pbsQuestionLevel) {
		super.save(pbsQuestionLevel);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsQuestionLevel pbsQuestionLevel) {
		super.delete(pbsQuestionLevel);
	}
	
}
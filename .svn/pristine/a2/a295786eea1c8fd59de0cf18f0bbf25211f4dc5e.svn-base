/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.question.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.question.entity.PbsQuestionRule;
import com.arjjs.ccm.modules.pbs.question.dao.PbsQuestionRuleDao;

/**
 * 评分规则定义Service
 * @author lc
 * @version 2018-06-06
 */
@Service
@Transactional(readOnly = true)
public class PbsQuestionRuleService extends CrudService<PbsQuestionRuleDao, PbsQuestionRule> {

	public PbsQuestionRule get(String id) {
		return super.get(id);
	}
	
	public List<PbsQuestionRule> findList(PbsQuestionRule pbsQuestionRule) {
		return super.findList(pbsQuestionRule);
	}
	
	public Page<PbsQuestionRule> findPage(Page<PbsQuestionRule> page, PbsQuestionRule pbsQuestionRule) {
		return super.findPage(page, pbsQuestionRule);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsQuestionRule pbsQuestionRule) {
		super.save(pbsQuestionRule);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsQuestionRule pbsQuestionRule) {
		super.delete(pbsQuestionRule);
	}
	
}
/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.question.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.question.entity.PbsQuestionRuleitem;
import com.arjjs.ccm.modules.pbs.question.dao.PbsQuestionRuleitemDao;

/**
 * 评分规则定义Service
 * @author lc
 * @version 2018-06-06
 */
@Service
@Transactional(readOnly = true)
public class PbsQuestionRuleitemService extends CrudService<PbsQuestionRuleitemDao, PbsQuestionRuleitem> {

	public PbsQuestionRuleitem get(String id) {
		return super.get(id);
	}
	
	public List<PbsQuestionRuleitem> findList(PbsQuestionRuleitem pbsQuestionRuleitem) {
		return super.findList(pbsQuestionRuleitem);
	}
	
	public Page<PbsQuestionRuleitem> findPage(Page<PbsQuestionRuleitem> page, PbsQuestionRuleitem pbsQuestionRuleitem) {
		return super.findPage(page, pbsQuestionRuleitem);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsQuestionRuleitem pbsQuestionRuleitem) {
		super.save(pbsQuestionRuleitem);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsQuestionRuleitem pbsQuestionRuleitem) {
		super.delete(pbsQuestionRuleitem);
	}
	
}
/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.risk.manage.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.risk.manage.entity.RiskHelp;
import com.arjjs.ccm.modules.risk.manage.dao.RiskHelpDao;

/**
 * 帮助信息Service
 * @author liang
 * @version 2018-03-31
 */
@Service
@Transactional(readOnly = true)
public class RiskHelpService extends CrudService<RiskHelpDao, RiskHelp> {

	public RiskHelp get(String id) {
		return super.get(id);
	}
	
	public List<RiskHelp> findList(RiskHelp riskHelp) {
		return super.findList(riskHelp);
	}
	
	public Page<RiskHelp> findPage(Page<RiskHelp> page, RiskHelp riskHelp) {
		return super.findPage(page, riskHelp);
	}
	
	@Transactional(readOnly = false)
	public void save(RiskHelp riskHelp) {
		super.save(riskHelp);
	}
	
	@Transactional(readOnly = false)
	public void delete(RiskHelp riskHelp) {
		super.delete(riskHelp);
	}
	
}
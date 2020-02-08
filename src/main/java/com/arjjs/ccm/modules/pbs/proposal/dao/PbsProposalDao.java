/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.proposal.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.pbs.proposal.entity.PbsProposal;

/**
 * 建议信息DAO接口
 * 
 * @author lc
 * @version 2018-05-31
 */
@MyBatisDao
public interface PbsProposalDao extends CrudDao<PbsProposal> {

	public void updateiCntById(String pbsProposal);
	
}
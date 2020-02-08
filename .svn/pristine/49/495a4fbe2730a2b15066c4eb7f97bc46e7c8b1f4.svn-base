/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.sys.entity.PbsRemindMsg;
import com.arjjs.ccm.modules.pbs.sys.dao.PbsRemindMsgDao;

/**
 * 消息提醒信息Service
 * 
 * @author lc
 * @version 2018-08-02
 */
@Service
@Transactional(readOnly = true)
public class PbsRemindMsgService extends CrudService<PbsRemindMsgDao, PbsRemindMsg> {

	public PbsRemindMsg get(String id) {
		return super.get(id);
	}

	public List<PbsRemindMsg> findList(PbsRemindMsg pbsRemindMsg) {
		return super.findList(pbsRemindMsg);
	}

	public Page<PbsRemindMsg> findPage(Page<PbsRemindMsg> page, PbsRemindMsg pbsRemindMsg) {
		return super.findPage(page, pbsRemindMsg);
	}

	@Transactional(readOnly = false)
	public void save(PbsRemindMsg pbsRemindMsg) {
		super.save(pbsRemindMsg);
	}

	@Transactional(readOnly = false)
	public void delete(PbsRemindMsg pbsRemindMsg) {
		super.delete(pbsRemindMsg);
	}

	@Transactional(readOnly = false)
	public void updateMsgStat(PbsRemindMsg pbsRemindMsg) {
		dao.updateStat(pbsRemindMsg);
	}

	// 返回通知数
	public int getCountNotice(PbsRemindMsg pbsRemindMsg) {
		return dao.getCountNotice(pbsRemindMsg);
	}
}
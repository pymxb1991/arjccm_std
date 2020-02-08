/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.wechat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.wechat.entity.PbsWebchatsendmsg;
import com.arjjs.ccm.modules.pbs.wechat.dao.PbsWebchatsendmsgDao;

/**
 * 微信消息Service
 * 
 * @author lc
 * @version 2018-06-29
 */
@Service
@Transactional(readOnly = true)
public class PbsWebchatsendmsgService extends CrudService<PbsWebchatsendmsgDao, PbsWebchatsendmsg> {

	public PbsWebchatsendmsg get(String id) {
		return super.get(id);
	}

	public List<PbsWebchatsendmsg> findList(PbsWebchatsendmsg pbsWebchatsendmsg) {
		return super.findList(pbsWebchatsendmsg);
	}

	public Page<PbsWebchatsendmsg> findPage(Page<PbsWebchatsendmsg> page, PbsWebchatsendmsg pbsWebchatsendmsg) {
		return super.findPage(page, pbsWebchatsendmsg);
	}

	@Transactional(readOnly = false)
	public void save(PbsWebchatsendmsg pbsWebchatsendmsg) {
		super.save(pbsWebchatsendmsg);
	}

	@Transactional(readOnly = false)
	public void delete(PbsWebchatsendmsg pbsWebchatsendmsg) {
		super.delete(pbsWebchatsendmsg);
	}

	// 返回发送的次数
	public int findSum() {
		return dao.findSum();
	}

}
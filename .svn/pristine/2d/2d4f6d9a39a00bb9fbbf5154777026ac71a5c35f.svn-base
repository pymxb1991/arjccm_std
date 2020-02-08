/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.activity.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActinotifications;
import com.arjjs.ccm.modules.pbs.activity.dao.PbsActinotificationsDao;

/**
 * 活动通知Service
 * 
 * @author lc
 * @version 2018-05-14
 */
@Service
@Transactional(readOnly = true)
public class PbsActinotificationsService extends CrudService<PbsActinotificationsDao, PbsActinotifications> {

	public PbsActinotifications get(String id) {
		return super.get(id);
	}

	public List<PbsActinotifications> findList(PbsActinotifications pbsActinotifications) {
		return super.findList(pbsActinotifications);
	}

	public Page<PbsActinotifications> findPage(Page<PbsActinotifications> page,
			PbsActinotifications pbsActinotifications) {
		return super.findPage(page, pbsActinotifications);
	}

	@Transactional(readOnly = false)
	public void save(PbsActinotifications pbsActinotifications) {
		super.save(pbsActinotifications);
	}

	@Transactional(readOnly = false)
	public void delete(PbsActinotifications pbsActinotifications) {
		super.delete(pbsActinotifications);
	}

	@Transactional(readOnly = false)
	public void updatesStat(PbsActinotifications pbsActinotifications) {
		dao.updatesStat(pbsActinotifications);
	}

}
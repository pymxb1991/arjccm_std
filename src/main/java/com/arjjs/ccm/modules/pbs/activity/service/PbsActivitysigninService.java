/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.activity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivityrec;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivitysignin;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivitytype;
import com.arjjs.ccm.modules.pbs.activity.dao.PbsActivitysigninDao;

/**
 * 活动签到Service
 * 
 * @author lc
 * @version 2018-05-15
 */
@Service
@Transactional(readOnly = true)
public class PbsActivitysigninService extends CrudService<PbsActivitysigninDao, PbsActivitysignin> {

	@Autowired
	private PbsActivityrecService pbsActivityrecService;
	
	public PbsActivitysignin get(String id) {
		return super.get(id);
	}

	public List<PbsActivitysignin> findList(PbsActivitysignin pbsActivitysignin) {
		return super.findList(pbsActivitysignin);
	}

	public Page<PbsActivitysignin> findPage(Page<PbsActivitysignin> page, PbsActivitysignin pbsActivitysignin) {
		return super.findPage(page, pbsActivitysignin);
	}

	@Transactional(readOnly = false)
	public void save(PbsActivitysignin pbsActivitysignin) {
		// 获取 活动 并加入 活动的类型ID
		PbsActivityrec pbsActivityrec = pbsActivityrecService.get(pbsActivitysignin.getsActivityid());
		pbsActivitysignin.setsType(new PbsActivitytype(pbsActivityrec.getsType().getId()));
		super.save(pbsActivitysignin);
		pbsActivityrecService.updateNum(pbsActivityrec );
	}

	@Transactional(readOnly = false)
	public void delete(PbsActivitysignin pbsActivitysignin) {
		super.delete(pbsActivitysignin);
	}
	
	public int getActivitySumByMemberId(String sSignbindmember) {
		return dao.getActivitySumByMemberId(sSignbindmember);
	}
}
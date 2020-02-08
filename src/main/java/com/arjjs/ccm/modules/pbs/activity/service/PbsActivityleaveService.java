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
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivityleave;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivityrec;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivitytype;
import com.arjjs.ccm.modules.pbs.activity.dao.PbsActivityleaveDao;

/**
 * 活动请假Service
 * @author lc
 * @version 2018-05-15
 */
@Service
@Transactional(readOnly = true)
public class PbsActivityleaveService extends CrudService<PbsActivityleaveDao, PbsActivityleave> {

	@Autowired
	private PbsActivityrecService pbsActivityrecService;
	
	public PbsActivityleave get(String id) {
		return super.get(id);
	}
	
	public List<PbsActivityleave> findList(PbsActivityleave pbsActivityleave) {
		return super.findList(pbsActivityleave);
	}
	
	public Page<PbsActivityleave> findPage(Page<PbsActivityleave> page, PbsActivityleave pbsActivityleave) {
		return super.findPage(page, pbsActivityleave);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsActivityleave pbsActivityleave) {
		// 获取 活动  并加入 活动的类型ID
		PbsActivityrec pbsActivityrec = pbsActivityrecService.get(pbsActivityleave.getsActivityid());
		pbsActivityleave.setsType(new PbsActivitytype(pbsActivityrec.getsType().getId()));
		super.save(pbsActivityleave);
		pbsActivityrecService.updateNum(pbsActivityrec);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsActivityleave pbsActivityleave) {
		super.delete(pbsActivityleave);
	}
	
}
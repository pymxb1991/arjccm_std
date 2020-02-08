/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.activity.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivityecomment;
import com.arjjs.ccm.modules.pbs.activity.dao.PbsActivityecommentDao;

/**
 * 活动评论信息Service
 * @author lc
 * @version 2018-07-10
 */
@Service
@Transactional(readOnly = true)
public class PbsActivityecommentService extends CrudService<PbsActivityecommentDao, PbsActivityecomment> {

	public PbsActivityecomment get(String id) {
		return super.get(id);
	}
	
	public List<PbsActivityecomment> findList(PbsActivityecomment pbsActivityecomment) {
		return super.findList(pbsActivityecomment);
	}
	
	public Page<PbsActivityecomment> findPage(Page<PbsActivityecomment> page, PbsActivityecomment pbsActivityecomment) {
		return super.findPage(page, pbsActivityecomment);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsActivityecomment pbsActivityecomment) {
		super.save(pbsActivityecomment);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsActivityecomment pbsActivityecomment) {
		super.delete(pbsActivityecomment);
	}
	
}
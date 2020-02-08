/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.course.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.course.entity.PbsCourseinfoEx;
import com.arjjs.ccm.modules.pbs.course.dao.PbsCourseinfoExDao;

/**
 * 课程信息Service
 * @author lc
 * @version 2018-04-11
 */
@Service
@Transactional(readOnly = true)
public class PbsCourseinfoServiceEx extends CrudService<PbsCourseinfoExDao, PbsCourseinfoEx> {

	public PbsCourseinfoEx get(String id) {
		return super.get(id);
	}
	
	public List<PbsCourseinfoEx> findList(PbsCourseinfoEx pbsCourseinfoEx) {
		return super.findList(pbsCourseinfoEx);
	}
	
	public Page<PbsCourseinfoEx> findPage(Page<PbsCourseinfoEx> page, PbsCourseinfoEx pbsCourseinfoEx) {
		return super.findPage(page, pbsCourseinfoEx);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsCourseinfoEx pbsCourseinfoEx) {
		super.save(pbsCourseinfoEx);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsCourseinfoEx pbsCourseinfoEx) {
		super.delete(pbsCourseinfoEx);
	}
	
}
/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.service.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.service.entity.PbsCourseinfo;
import com.arjjs.ccm.modules.ccm.service.dao.PbsCourseinfoDao;

/**
 * 业务学习Service
 * @author liujindan
 * @version 2019-02-25
 */
@Service
@Transactional(readOnly = true)
public class PbsCourseinfoService extends CrudService<PbsCourseinfoDao, PbsCourseinfo> {

	public PbsCourseinfo get(String id) {
		return super.get(id);
	}
	
	public List<PbsCourseinfo> findList(PbsCourseinfo pbsCourseinfo) {
		return super.findList(pbsCourseinfo);
	}
	
	public Page<PbsCourseinfo> findPage(Page<PbsCourseinfo> page, PbsCourseinfo pbsCourseinfo) {
		return super.findPage(page, pbsCourseinfo);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsCourseinfo pbsCourseinfo) {
		super.save(pbsCourseinfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsCourseinfo pbsCourseinfo) {
		super.delete(pbsCourseinfo);
	}
	
}
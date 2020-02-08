/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.person.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.person.entity.PbsLabelinfo;
import com.arjjs.ccm.modules.pbs.person.dao.PbsLabelinfoDao;

/**
 * 标签信息表Service
 * @author lc
 * @version 2018-08-03
 */
@Service
@Transactional(readOnly = true)
public class PbsLabelinfoService extends CrudService<PbsLabelinfoDao, PbsLabelinfo> {

	public PbsLabelinfo get(String id) {
		return super.get(id);
	}
	
	public List<PbsLabelinfo> findList(PbsLabelinfo pbsLabelinfo) {
		return super.findList(pbsLabelinfo);
	}
	
	public Page<PbsLabelinfo> findPage(Page<PbsLabelinfo> page, PbsLabelinfo pbsLabelinfo) {
		return super.findPage(page, pbsLabelinfo);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsLabelinfo pbsLabelinfo) {
		super.save(pbsLabelinfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsLabelinfo pbsLabelinfo) {
		super.delete(pbsLabelinfo);
	}
	
}
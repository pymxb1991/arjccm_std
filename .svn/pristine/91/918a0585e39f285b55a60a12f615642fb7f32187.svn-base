/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.person.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPositionlevel;
import com.arjjs.ccm.modules.pbs.person.dao.PbsPositionlevelDao;

/**
 * 职位信息Service
 * @author lc
 * @version 2018-06-15
 */
@Service
@Transactional(readOnly = true)
public class PbsPositionlevelService extends CrudService<PbsPositionlevelDao, PbsPositionlevel> {

	public PbsPositionlevel get(String id) {
		return super.get(id);
	}
	
	public List<PbsPositionlevel> findList(PbsPositionlevel pbsPositionlevel) {
		return super.findList(pbsPositionlevel);
	}
	
	public Page<PbsPositionlevel> findPage(Page<PbsPositionlevel> page, PbsPositionlevel pbsPositionlevel) {
		return super.findPage(page, pbsPositionlevel);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsPositionlevel pbsPositionlevel) {
		super.save(pbsPositionlevel);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsPositionlevel pbsPositionlevel) {
		super.delete(pbsPositionlevel);
	}
	
}
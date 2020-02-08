/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.exam.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.exam.entity.PbsExamactionitem;
import com.arjjs.ccm.modules.pbs.exam.dao.PbsExamactionitemDao;

/**
 * 考试题目信息Service
 * @author lc
 * @version 2018-06-11
 */
@Service
@Transactional(readOnly = true)
public class PbsExamactionitemService extends CrudService<PbsExamactionitemDao, PbsExamactionitem> {

	public PbsExamactionitem get(String id) {
		return super.get(id);
	}
	
	public List<PbsExamactionitem> findList(PbsExamactionitem pbsExamactionitem) {
		return super.findList(pbsExamactionitem);
	}
	
	public Page<PbsExamactionitem> findPage(Page<PbsExamactionitem> page, PbsExamactionitem pbsExamactionitem) {
		return super.findPage(page, pbsExamactionitem);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsExamactionitem pbsExamactionitem) {
		super.save(pbsExamactionitem);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsExamactionitem pbsExamactionitem) {
		super.delete(pbsExamactionitem);
	}
	
	@Transactional(readOnly = false)
	public void insertAll(List<PbsExamactionitem> list) {
		dao.insertAll(list);
	}
	
	public List<PbsExamactionitem> findErrorList(PbsExamactionitem pbsExamactionitem) {
		return dao.findErrorList(pbsExamactionitem);
	}
}
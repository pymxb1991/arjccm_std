/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.exam.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.exam.entity.PbsExamaction;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.modules.pbs.exam.dao.PbsExamactionDao;

/**
 * 考试信息Service
 * 
 * @author lx
 * @version 2018-06-11
 */
@Service
@Transactional(readOnly = true)
public class PbsExamactionService extends CrudService<PbsExamactionDao, PbsExamaction> {

	public PbsExamaction get(String id) {
		return super.get(id);
	}

	public List<PbsExamaction> findList(PbsExamaction pbsExamaction) {
		return super.findList(pbsExamaction);
	}

	public Page<PbsExamaction> findPage(Page<PbsExamaction> page, PbsExamaction pbsExamaction) {
		pbsExamaction.setsPrimarykey01(UserUtils.getUser().getId());
		return super.findPage(page, pbsExamaction);
	}

	@Transactional(readOnly = false)
	public void save(PbsExamaction pbsExamaction) {
		super.save(pbsExamaction);
	}

	@Transactional(readOnly = false)
	public void delete(PbsExamaction pbsExamaction) {
		super.delete(pbsExamaction);
	}

	@Transactional(readOnly = false)
	public void insert(PbsExamaction pbsExamaction) {
		dao.insert(pbsExamaction);
	}

	// 按考试成绩 排行
	public List<PbsExamaction> findListByIReport(PbsExamaction pbsExamaction) {
		return dao.findListByIReport(pbsExamaction);
	}

	public Page<PbsExamaction> findPageByIReport(Page<PbsExamaction> page, PbsExamaction pbsExamaction) {
		pbsExamaction.setPage(page);
		page.setList(dao.findListByIReport(pbsExamaction));
		return page;
	}

	// 按部门成绩平均分列表
	public Page<PbsExamaction> findPageIReportByOfc(Page<PbsExamaction> page, PbsExamaction pbsExamaction) {
		pbsExamaction.setPage(page);
		page.setList(dao.findIReportByOfc(pbsExamaction));
		return page;
	}

	public List<PbsExamaction> findIReportByOfc(PbsExamaction pbsExamaction) {
		return dao.findIReportByOfc(pbsExamaction);
	}

	public List<PbsExamaction> findIntegrallist(PbsExamaction pbsExamaction) {
		return dao.findIntegrallist(pbsExamaction);
	}

	public List<PbsExamaction> findIntegralByOfc(PbsExamaction pbsExamaction) {
		return dao.findIntegralByOfc(pbsExamaction);
	}
	
	public List<PbsExamaction> paperDetail(String paperId) {
		return dao.paperDetail(paperId);
	}
	
}
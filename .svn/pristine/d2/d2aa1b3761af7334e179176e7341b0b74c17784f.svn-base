/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.ncount.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.ncount.entity.PbsNcountdetail;
import com.arjjs.ccm.modules.pbs.ncount.dao.PbsNcountdetailDao;

/**
 * 统计计数明细信息Service
 * @author lc
 * @version 2018-07-12
 */
@Service
@Transactional(readOnly = true)
public class PbsNcountdetailService extends CrudService<PbsNcountdetailDao, PbsNcountdetail> {

	public PbsNcountdetail get(String id) {
		return super.get(id);
	}
	
	public List<PbsNcountdetail> findList(PbsNcountdetail pbsNcountdetail) {
		return super.findList(pbsNcountdetail);
	}
	
	public Page<PbsNcountdetail> findPage(Page<PbsNcountdetail> page, PbsNcountdetail pbsNcountdetail) {
		return super.findPage(page, pbsNcountdetail);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsNcountdetail pbsNcountdetail) {
		super.save(pbsNcountdetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsNcountdetail pbsNcountdetail) {
		super.delete(pbsNcountdetail);
	}
	
	@Transactional(readOnly = false)
	public void clearData(PbsNcountdetail pbsNcountdetail) {
		dao.clearData(pbsNcountdetail);
	}
	
	@Transactional(readOnly = false)
	public void insertAll(List<PbsNcountdetail> PbsNcountdetails){
		dao.insertAll(PbsNcountdetails);
	}
	
	
}
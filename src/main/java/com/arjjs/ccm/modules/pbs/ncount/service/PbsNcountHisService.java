/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.ncount.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.ncount.entity.PbsNcount;
import com.arjjs.ccm.modules.pbs.ncount.entity.PbsNcountHis;
import com.arjjs.ccm.modules.pbs.ncount.dao.PbsNcountHisDao;

/**
 * 统计类别历史Service
 * 
 * @author lc
 * @version 2018-07-19
 */
@Service
@Transactional(readOnly = true)
public class PbsNcountHisService extends CrudService<PbsNcountHisDao, PbsNcountHis> {

	public PbsNcountHis get(String id) {
		return super.get(id);
	}

	public List<PbsNcountHis> findList(PbsNcountHis pbsNcountHis) {
		return super.findList(pbsNcountHis);
	}

	public Page<PbsNcountHis> findPage(Page<PbsNcountHis> page, PbsNcountHis pbsNcountHis) {
		return super.findPage(page, pbsNcountHis);
	}

	@Transactional(readOnly = false)
	public void save(PbsNcountHis pbsNcountHis) {
		super.save(pbsNcountHis);
	}

	@Transactional(readOnly = false)
	public void delete(PbsNcountHis pbsNcountHis) {
		super.delete(pbsNcountHis);
	}

	@Transactional(readOnly = false)
	public void copyData(PbsNcount pbsNcount) {
		dao.copyData(pbsNcount);
	}

}
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
import com.arjjs.ccm.modules.pbs.ncount.entity.PbsNcountdetail;
import com.arjjs.ccm.modules.pbs.ncount.dao.PbsNcountDao;

/**
 * 统计计数Service
 * 
 * @author lc
 * @version 2018-07-12
 */
@Service
@Transactional(readOnly = true)
public class PbsNcountService extends CrudService<PbsNcountDao, PbsNcount> {

	public PbsNcount get(String id) {
		return super.get(id);
	}

	public List<PbsNcount> findList(PbsNcount pbsNcount) {
		return super.findList(pbsNcount);
	}

	public Page<PbsNcount> findPage(Page<PbsNcount> page, PbsNcount pbsNcount) {
		return super.findPage(page, pbsNcount);
	}

	@Transactional(readOnly = false)
	public void save(PbsNcount pbsNcount) {
		super.save(pbsNcount);
	}

	@Transactional(readOnly = false)
	public void delete(PbsNcount pbsNcount) {
		super.delete(pbsNcount);
	}

	@Transactional(readOnly = false)
	public void clearData(PbsNcount pbsNcount) {
		dao.clearData(pbsNcount);
	}

	// 转移数据
	@Transactional(readOnly = false)
	public void insertData(PbsNcount pbsNcount) {
		// 获取所有的统计信息
		PbsNcountdetail pbsNcountdetailDto = new PbsNcountdetail();
		pbsNcountdetailDto.setsParentid(pbsNcount);
		// 复刻信息到历史表
		// 复刻详细到历史表
		dao.clearData(pbsNcount);
	}

}
/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.thinkingreport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.thinkingreport.entity.PbsThinkingreport;
import com.arjjs.ccm.modules.pbs.thinkingreport.entity.PbsThinkingreportopt;
import com.arjjs.ccm.modules.pbs.thinkingreport.dao.PbsThinkingreportoptDao;

/**
 * 思想汇报操作Service
 * 
 * @author lc
 * @version 2018-05-28
 */
@Service
@Transactional(readOnly = true)
public class PbsThinkingreportoptService extends CrudService<PbsThinkingreportoptDao, PbsThinkingreportopt> {

	@Autowired
	private PbsThinkingreportService pbsThinkingreportService;

	public PbsThinkingreportopt get(String id) {
		return super.get(id);
	}

	public List<PbsThinkingreportopt> findList(PbsThinkingreportopt pbsThinkingreportopt) {
		return super.findList(pbsThinkingreportopt);
	}

	public Page<PbsThinkingreportopt> findPage(Page<PbsThinkingreportopt> page,
			PbsThinkingreportopt pbsThinkingreportopt) {
		return super.findPage(page, pbsThinkingreportopt);
	}

	@Transactional(readOnly = false)
	public void save(PbsThinkingreportopt pbsThinkingreportopt) {
		// 评价同时 更改 汇报的评价
		if ("0".equals(pbsThinkingreportopt.getSType())) {
			PbsThinkingreport pbsThinkingreport = pbsThinkingreportService
					.get(pbsThinkingreportopt.getsReportid().getId());
			pbsThinkingreport.setSLevel(pbsThinkingreportopt.getSLevel());
			pbsThinkingreport.setSStat("1");
			pbsThinkingreportService.save(pbsThinkingreport);
		}
		if ("1".equals(pbsThinkingreportopt.getSType())) {
			PbsThinkingreport pbsThinkingreport = pbsThinkingreportService
					.get(pbsThinkingreportopt.getsReportid().getId());
			pbsThinkingreport.setSStat("2");
			pbsThinkingreportService.save(pbsThinkingreport);
		}
		super.save(pbsThinkingreportopt);
	}

	@Transactional(readOnly = false)
	public void delete(PbsThinkingreportopt pbsThinkingreportopt) {
		super.delete(pbsThinkingreportopt);
	}

}
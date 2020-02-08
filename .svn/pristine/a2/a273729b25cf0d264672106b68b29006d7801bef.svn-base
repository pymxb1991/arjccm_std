/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.person.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymemreg;
import com.arjjs.ccm.modules.pbs.person.dao.PbsPartymemregDao;

/**
 * 党员登记信息Service
 * 
 * @author lc
 * @version 2018-04-08
 */
@Service
@Transactional(readOnly = true)
public class PbsPartymemregService extends CrudService<PbsPartymemregDao, PbsPartymemreg> {

	@Autowired
	private PbsPartymemregDao pbsPartymemregDao;

	public PbsPartymemreg get(String id) {
		return super.get(id);
	}

	public List<PbsPartymemreg> findList(PbsPartymemreg pbsPartymemreg) {
		return super.findList(pbsPartymemreg);
	}

	public Page<PbsPartymemreg> findPage(Page<PbsPartymemreg> page, PbsPartymemreg pbsPartymemreg) {
		return super.findPage(page, pbsPartymemreg);
	}

	@Transactional(readOnly = false)
	public void save(PbsPartymemreg pbsPartymemreg) {
		super.save(pbsPartymemreg);
	}

	@Transactional(readOnly = false)
	public void delete(PbsPartymemreg pbsPartymemreg) {
		super.delete(pbsPartymemreg);
	}

	// 检测 当前的登记业务是否存在
	public boolean checkExist(PbsPartymemreg pbsPartymemreg) {
		return pbsPartymemregDao.checkExist(pbsPartymemreg) == 0 ? false : true;
	}

	// 更新登记结果
	@Transactional(readOnly = false)
	public boolean updatepartymemstat(PbsPartymemreg pbsPartymemreg) {
		return pbsPartymemregDao.updatepartymemstat(pbsPartymemreg) == 0 ? false : true;
	}
}
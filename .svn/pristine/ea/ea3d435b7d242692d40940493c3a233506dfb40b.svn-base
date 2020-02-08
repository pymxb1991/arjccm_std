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
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymembind;
import com.arjjs.ccm.modules.pbs.person.dao.PbsPartymembindDao;

/**
 * 党员表关系Service
 * 
 * @author lc
 * @version 2018-04-03
 */
@Service
@Transactional(readOnly = true)
public class PbsPartymembindService extends CrudService<PbsPartymembindDao, PbsPartymembind> {

	@Autowired
	private PbsPartymembindDao pbsPartymembindDao;

	public PbsPartymembind get(String id) {
		return super.get(id);
	}

	public List<PbsPartymembind> findList(PbsPartymembind pbsPartymembind) {
		return super.findList(pbsPartymembind);
	}

	public Page<PbsPartymembind> findPage(Page<PbsPartymembind> page, PbsPartymembind pbsPartymembind) {
		return super.findPage(page, pbsPartymembind);
	}

	@Transactional(readOnly = false)
	public void save(PbsPartymembind pbsPartymembind) {
		super.save(pbsPartymembind);
	}

	@Transactional(readOnly = false)
	public void delete(PbsPartymembind pbsPartymembind) {
		super.delete(pbsPartymembind);
	}

	public boolean checkExist(PbsPartymembind pbsPartymembind) {
		return pbsPartymembindDao.checkExist(pbsPartymembind) == 0 ? false : true;
	}

	//
	public boolean deleteByUser(PbsPartymembind pbsPartymembind) {
		return pbsPartymembindDao.deleteByUser(pbsPartymembind) == 0 ? false : true;
	}

	// 获取 指定 用户的党员
	public PbsPartymem getItemByUser(String id) {
		PbsPartymembind pbsPartymembind = new  PbsPartymembind();
		pbsPartymembind.setSPrimarykey01(id);
		PbsPartymem pbsPartymem =pbsPartymembindDao.getItemByUser(pbsPartymembind) ;
		return pbsPartymem==null?new PbsPartymem() :pbsPartymem;
	}
}
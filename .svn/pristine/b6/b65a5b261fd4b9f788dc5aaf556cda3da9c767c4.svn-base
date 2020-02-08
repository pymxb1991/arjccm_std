/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.partyprojectpost.service;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.partyprojectpost.dao.CcmPartyProjectPostDao;
import com.arjjs.ccm.modules.ccm.partyprojectpost.entity.CcmPartyProjectPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 认领功能Service
 * @author cby
 * @version 2019-08-15
 */
@Service
@Transactional(readOnly = true)
public class CcmPartyProjectPostService extends CrudService<CcmPartyProjectPostDao, CcmPartyProjectPost> {

	@Autowired
	private CcmPartyProjectPostDao ccmPartyProjectPostDao;

	public CcmPartyProjectPost get(String id) {
		return super.get(id);
	}
	
	public List<CcmPartyProjectPost> findList(CcmPartyProjectPost ccmPartyProjectPost) {
		return super.findList(ccmPartyProjectPost);
	}
	
	public Page<CcmPartyProjectPost> findPage(Page<CcmPartyProjectPost> page, CcmPartyProjectPost ccmPartyProjectPost) {
		return super.findPage(page, ccmPartyProjectPost);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPartyProjectPost ccmPartyProjectPost) {
		super.save(ccmPartyProjectPost);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPartyProjectPost ccmPartyProjectPost) {
		super.delete(ccmPartyProjectPost);
	}

	/**
	 *  查询 服务项目，岗位认领数量
	 * @param id
	 * @return
	 */
	public int countProPostNum(String id) {
		return dao.countProPostNum(id);
	}

	@Transactional(readOnly = false)
	public void batchSave(List<CcmPartyProjectPost> ccmPartyProjectPostList) {
		ccmPartyProjectPostDao.batchSave(ccmPartyProjectPostList);
	}
}
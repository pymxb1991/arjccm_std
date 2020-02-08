/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.partyvolutterpost.service;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.partyprojectpost.service.CcmPartyProjectPostService;
import com.arjjs.ccm.modules.ccm.partyvolutterpost.dao.CcmPartyVolutterPostDao;
import com.arjjs.ccm.modules.ccm.partyvolutterpost.entity.CcmPartyVolutterPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 志愿者岗位管理Service
 * @author cby
 * @version 2019-08-15
 */
@Service
@Transactional(readOnly = true)
public class CcmPartyVolutterPostService extends CrudService<CcmPartyVolutterPostDao, CcmPartyVolutterPost> {

	@Autowired
	private CcmPartyProjectPostService ccmPartyProjectPostService;

	public CcmPartyVolutterPost get(String id) {
		return super.get(id);
	}
	
	public List<CcmPartyVolutterPost> findList(CcmPartyVolutterPost ccmPartyVolutterPost) {
		return super.findList(ccmPartyVolutterPost);
	}
	
	public Page<CcmPartyVolutterPost> findPage(Page<CcmPartyVolutterPost> page, CcmPartyVolutterPost ccmPartyVolutterPost) {
		page = super.findPage(page, ccmPartyVolutterPost);
		List<CcmPartyVolutterPost> list = new ArrayList<>();
		for (CcmPartyVolutterPost volutter: page.getList()) {
			int count = ccmPartyProjectPostService.countProPostNum(volutter.getId());
			volutter.setRelclailNum( count +"");
			list.add(volutter);
		}
		ccmPartyVolutterPost.setPage(page);
		page.setList(list);
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPartyVolutterPost ccmPartyVolutterPost) {
		super.save(ccmPartyVolutterPost);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPartyVolutterPost ccmPartyVolutterPost) {
		super.delete(ccmPartyVolutterPost);
	}
	
}
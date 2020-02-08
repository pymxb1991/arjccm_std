/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.cms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.cms.entity.CmsBbsCommentMulti;
import com.arjjs.ccm.modules.cms.dao.CmsBbsCommentMultiDao;

/**
 * 网上论坛多级评论Service
 * @author maoxb
 * @version 2019-08-01
 */
@Service
@Transactional(readOnly = true)
public class CmsBbsCommentMultiService extends CrudService<CmsBbsCommentMultiDao, CmsBbsCommentMulti> {

	public CmsBbsCommentMulti get(String id) {
		return super.get(id);
	}
	
	public List<CmsBbsCommentMulti> findList(CmsBbsCommentMulti cmsBbsCommentMulti) {
		return super.findList(cmsBbsCommentMulti);
	}
	
	public Page<CmsBbsCommentMulti> findPage(Page<CmsBbsCommentMulti> page, CmsBbsCommentMulti cmsBbsCommentMulti) {
		return super.findPage(page, cmsBbsCommentMulti);
	}
	
	@Transactional(readOnly = false)
	public void save(CmsBbsCommentMulti cmsBbsCommentMulti) {
		super.save(cmsBbsCommentMulti);
	}
	
	@Transactional(readOnly = false)
	public void delete(CmsBbsCommentMulti cmsBbsCommentMulti) {
		super.delete(cmsBbsCommentMulti);
	}
	
}
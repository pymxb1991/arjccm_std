/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.cms.service;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.cms.dao.CmsBbsCommentDao;
import com.arjjs.ccm.modules.cms.entity.CmsBbsComment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 网上论坛一级评论Service
 * @author maoxb
 * @version 2019-08-01
 */
@Service
@Transactional(readOnly = true)
public class CmsBbsCommentService extends CrudService<CmsBbsCommentDao, CmsBbsComment> {

	public CmsBbsComment get(String id) {
		return super.get(id);
	}
	
	public List<CmsBbsComment> findList(CmsBbsComment cmsBbsComment) {
		return super.findList(cmsBbsComment);
	}
	
	public Page<CmsBbsComment> findPage(Page<CmsBbsComment> page, CmsBbsComment cmsBbsComment) {
		return super.findPage(page, cmsBbsComment);
	}
	
	@Transactional(readOnly = false)
	public void save(CmsBbsComment cmsBbsComment) {
		super.save(cmsBbsComment);
	}
	
	@Transactional(readOnly = false)
	public void delete(CmsBbsComment cmsBbsComment) {
		super.delete(cmsBbsComment);
	}

}
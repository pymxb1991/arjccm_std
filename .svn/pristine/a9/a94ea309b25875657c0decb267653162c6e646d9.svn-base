/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.work.service;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.work.dao.CcmWorkNoticeDao;
import com.arjjs.ccm.modules.ccm.work.entity.CcmWorkNotice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 公告Service
 * @author liang
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class CcmWorkNoticeService extends CrudService<CcmWorkNoticeDao, CcmWorkNotice> {

	public CcmWorkNotice get(String id) {
		return super.get(id);
	}
	
	public List<CcmWorkNotice> findList(CcmWorkNotice ccmWorkNotice) {
		return super.findList(ccmWorkNotice);
	}
	
	public Page<CcmWorkNotice> findPage(Page<CcmWorkNotice> page, CcmWorkNotice ccmWorkNotice) {
		return super.findPage(page, ccmWorkNotice);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmWorkNotice ccmWorkNotice) {
		super.save(ccmWorkNotice);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmWorkNotice ccmWorkNotice) {
		super.delete(ccmWorkNotice);
	}

	public List<CcmWorkNotice> queryWorkNotice(String id) {

		List<CcmWorkNotice> ccmWorkNotices = dao.queryWorkNotice(id);
		return ccmWorkNotices;
	}

	public int selectWorkNoticeTotal() {
		return dao.selectWorkNoticeTotal();
	}

	public int selectWorkNoticeTodoCount() {
		return dao.selectWorkNoticeTodoCount();
	}


	public int updateSatausById(String id) {
		return dao.updateSatausById(id);
	}
}
/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.book.service;

import java.util.Iterator;
import java.util.List;

import com.arjjs.ccm.common.utils.CacheUtils;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.book.entity.CcmDatabaseBook;
import com.arjjs.ccm.modules.ccm.book.dao.CcmDatabaseBookDao;

/**
 * 资料库录入管理Service
 * @author cdz
 * @version 2019-09-16
 */
@Service
@Transactional(readOnly = true)
public class CcmDatabaseBookService extends CrudService<CcmDatabaseBookDao, CcmDatabaseBook> {

	@Autowired
	private CcmDatabaseBookDao ccmDatabaseBookDao;
	
	public List<CcmDatabaseBook> findList(CcmDatabaseBook ccmDatabaseBook) {
		return super.findList(ccmDatabaseBook);
	}

	public List<CcmDatabaseBook> findListById(String id) {
		return dao.findListById(id);
	}

	public CcmDatabaseBook getCcmDatabaseBook(String id) {
		return this.ccmDatabaseBookDao.get(id);
	}
	
	public CcmDatabaseBook getArticleInfo(CcmDatabaseBook CcmDatabaseBook) {
		return dao.getArticleInfo(CcmDatabaseBook);
	}

	public List<CcmDatabaseBook> findAllList() {
		return dao.findAllList();
	}

	public Page<CcmDatabaseBook> findPage(Page<CcmDatabaseBook> page, CcmDatabaseBook ccmDatabaseBook) {
		return super.findPage(page, ccmDatabaseBook);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmDatabaseBook ccmDatabaseBook) {
		super.save(ccmDatabaseBook);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmDatabaseBook ccmDatabaseBook) {
		super.delete(ccmDatabaseBook);
	}


	@Transactional(readOnly = false)
	public void saveMenu(CcmDatabaseBook menu) {
		menu.setParent(this.getCcmDatabaseBook(menu.getParent().getId()));
		String oldParentIds = menu.getParentIds();
		menu.setParentIds(menu.getParent().getParentIds() + menu.getParent().getId() + ",");
		if (StringUtils.isBlank(menu.getId())) {
			menu.preInsert();
			this.ccmDatabaseBookDao.insert(menu);
		} else {
			menu.preUpdate();
			this.ccmDatabaseBookDao.update(menu);
		}

		CcmDatabaseBook m = new CcmDatabaseBook();
		m.setParentIds("%," + menu.getId() + ",%");
		List<CcmDatabaseBook> list = this.ccmDatabaseBookDao.findByParentIdsLike(m);
		Iterator var6 = list.iterator();

		while(var6.hasNext()) {
			CcmDatabaseBook e = (CcmDatabaseBook)var6.next();
			e.setParentIds(e.getParentIds().replace(oldParentIds, menu.getParentIds()));
			this.ccmDatabaseBookDao.updateParentIds(e);
		}

		UserUtils.removeCache("menuList");
		CacheUtils.remove("menuNamePathMap");
	}

	@Transactional(
			readOnly = false
	)
	public void deleteMenu(CcmDatabaseBook menu) {
		this.ccmDatabaseBookDao.delete(menu);
		UserUtils.removeCache("menuList");
		CacheUtils.remove("menuNamePathMap");
	}
	
}
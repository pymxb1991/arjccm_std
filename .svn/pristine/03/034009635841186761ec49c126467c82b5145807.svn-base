/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.tree.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.tree.entity.CcmMapCollectZtree;
import com.arjjs.ccm.modules.ccm.tree.dao.CcmMapCollectZtreeDao;

/**
 * 首页地图收藏树Service
 * @author liuxue
 * @version 2018-09-14
 */
@Service
@Transactional(readOnly = true)
public class CcmMapCollectZtreeService extends CrudService<CcmMapCollectZtreeDao, CcmMapCollectZtree> {

	public CcmMapCollectZtree get(String id) {
		return super.get(id);
	}
	
	public List<CcmMapCollectZtree> findList(CcmMapCollectZtree ccmMapCollectZtree) {
		return super.findList(ccmMapCollectZtree);
	}
	
	public CcmMapCollectZtree	getZId (CcmMapCollectZtree ccmMapCollectZtree) {
		
		return dao.getZId(ccmMapCollectZtree);
	};
	
	public Page<CcmMapCollectZtree> findPage(Page<CcmMapCollectZtree> page, CcmMapCollectZtree ccmMapCollectZtree) {
		return super.findPage(page, ccmMapCollectZtree);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmMapCollectZtree ccmMapCollectZtree) {
		super.save(ccmMapCollectZtree);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmMapCollectZtree ccmMapCollectZtree) {
		super.delete(ccmMapCollectZtree);
	}
	
}
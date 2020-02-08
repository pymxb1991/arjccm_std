/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.collection.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.collection.entity.CcmDatabaseCollection;
import com.arjjs.ccm.modules.ccm.collection.entity.CcmRestCollection;
import com.arjjs.ccm.modules.ccm.collection.dao.CcmDatabaseCollectionDao;
import com.arjjs.ccm.modules.ccm.book.entity.dataCollectionEntity;

/**
 * 收藏夹管理Service
 * @author cdz
 * @version 2019-09-16
 */
@Service
@Transactional(readOnly = true)
public class CcmDatabaseCollectionService extends CrudService<CcmDatabaseCollectionDao, CcmDatabaseCollection> {

	public CcmDatabaseCollection get(String id) {
		return super.get(id);
	}
	
	public List<CcmDatabaseCollection> findList(CcmDatabaseCollection ccmDatabaseCollection) {
		return super.findList(ccmDatabaseCollection);
	}
	
	public Page<CcmDatabaseCollection> findPage(Page<CcmDatabaseCollection> page, CcmDatabaseCollection ccmDatabaseCollection) {
		return super.findPage(page, ccmDatabaseCollection);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmDatabaseCollection ccmDatabaseCollection) {
		super.save(ccmDatabaseCollection);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmDatabaseCollection ccmDatabaseCollection) {
		super.delete(ccmDatabaseCollection);
	}

	public List<dataCollectionEntity> findListCollection(String id){
		return dao.findListCollection(id);
	}
	
	public List<CcmRestCollection> getCollectionInfo(CcmDatabaseCollection ccmDatabaseCollection){
		return dao.getCollectionInfo(ccmDatabaseCollection);
	}
	
	@Transactional(readOnly = false)
	public void addCollectionInfo(CcmDatabaseCollection ccmDatabaseCollection){
		dao.insert(ccmDatabaseCollection);
	}
	
	@Transactional(readOnly = false)
	public void updateCollectionInfo(CcmDatabaseCollection ccmDatabaseCollection){
		dao.updateCollectionInfo(ccmDatabaseCollection);
	}
	
	@Transactional(readOnly = false)
	public void cancelCollectionInfo(CcmDatabaseCollection ccmDatabaseCollection){
		dao.cancelCollectionInfo(ccmDatabaseCollection);
	}
	
	public CcmDatabaseCollection getCollectionData(CcmDatabaseCollection ccmDatabaseCollection) {
		return dao.getCollectionData(ccmDatabaseCollection);
	}
}
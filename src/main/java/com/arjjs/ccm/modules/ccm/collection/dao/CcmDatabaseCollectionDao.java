/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.collection.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.collection.entity.CcmDatabaseCollection;
import com.arjjs.ccm.modules.ccm.collection.entity.CcmRestCollection;
import com.arjjs.ccm.modules.ccm.book.entity.dataCollectionEntity;

import java.util.List;

/**
 * 收藏夹管理DAO接口
 * @author cdz
 * @version 2019-09-16
 */
@MyBatisDao
public interface CcmDatabaseCollectionDao extends CrudDao<CcmDatabaseCollection> {

    List<dataCollectionEntity> findListCollection(String id);
    
    List<CcmRestCollection> getCollectionInfo(CcmDatabaseCollection ccmDatabaseCollection);
    
    void updateCollectionInfo(CcmDatabaseCollection ccmDatabaseCollection);
    
    void cancelCollectionInfo(CcmDatabaseCollection ccmDatabaseCollection);
    
    CcmDatabaseCollection getCollectionData(CcmDatabaseCollection ccmDatabaseCollection);
}
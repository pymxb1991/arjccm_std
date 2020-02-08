/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.oa.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.oa.entity.CcmOfficialDocument;
import com.arjjs.ccm.modules.oa.entity.TestAudit;

/**
 * 公文DAO接口
 * @author pengjianqiang
 * @version 2018-03-19
 */
@MyBatisDao
public interface CcmOfficialDocumentDao extends CrudDao<CcmOfficialDocument> {

	public int updateProcInsId(CcmOfficialDocument ccmOfficialDocument);

	public CcmOfficialDocument getByProcInsId(String procInsId);
}
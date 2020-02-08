/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgOrgprevent;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgSyncentre;
import com.arjjs.ccm.tool.EchartType;

/**
 * 群防群治组织DAO接口
 * @author liang
 * @version 2018-01-13
 */
@MyBatisDao
public interface CcmOrgOrgpreventDao extends CrudDao<CcmOrgOrgprevent> {

	//群防群治组织结构-大屏-滨海新区社会网格化管理信息平台
	List<EchartType> findOrgpreventComTypeType(CcmOrgSyncentre ccmOrgSyncentre);

	List<EchartType> getByOrgpreventComType();
	
}
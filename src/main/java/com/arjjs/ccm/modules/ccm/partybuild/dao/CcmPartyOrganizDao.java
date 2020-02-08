/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.partybuild.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.partybuild.entity.CcmPartyOrganiz;
import com.arjjs.ccm.tool.EchartType;

import java.util.List;

/**
 * 党组织管理DAO接口
 * @author maoxb
 * @version 2019-08-12
 */
@MyBatisDao
public interface CcmPartyOrganizDao extends CrudDao<CcmPartyOrganiz> {


    List<EchartType> getOrgByBussCate();
}
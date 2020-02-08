/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.rest.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmUserGroup;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmUserRelationship;
import com.arjjs.ccm.modules.ccm.view.entity.VCcmTeam;

/**
 * 用户关系DAO接口
 * @author fu
 * @version 2018-03-08
 */
@MyBatisDao
public interface CcmUserRelationshipDao extends CrudDao<CcmUserRelationship> {

	List<VCcmTeam> findTeamListByGroup(CcmUserGroup ccmUserGroup);

	void deleteByGroupAndUser(CcmUserRelationship ccmUserRelationship);
	int insertUserRelationship(List<CcmUserRelationship> ccmUserRelationship);
	
}
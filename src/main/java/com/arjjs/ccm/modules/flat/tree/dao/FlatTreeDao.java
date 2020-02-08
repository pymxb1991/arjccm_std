package com.arjjs.ccm.modules.flat.tree.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgTeam;

@MyBatisDao
public interface FlatTreeDao {
	List<CcmOrgTeam> findAllUser();
	CcmOrgTeam findUserById(CcmOrgTeam ccmOrgTeam);
}

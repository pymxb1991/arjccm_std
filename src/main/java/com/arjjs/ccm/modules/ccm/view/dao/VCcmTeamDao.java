/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.view.dao;

import com.arjjs.ccm.common.persistence.TreeDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.view.entity.VCcmTeam;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.tool.EchartType;
import com.arjjs.ccm.tool.SearchTab;

import java.util.List;

/**
 * 综治队伍DAO接口
 * @author liang
 * @version 2018-01-12
 */
@MyBatisDao
public interface VCcmTeamDao extends TreeDao<VCcmTeam> {
	/**
	 * 新填findform查询
	 * @param  
	 * @return
	 */
	public List<VCcmTeam> findform(VCcmTeam vCcmTeam);
	
	public List<VCcmTeam> GetUserByArea(String id);

	public VCcmTeam getOneByUserId(String userId);

	public List<VCcmTeam> findByOfficeParentIdsLike(Office office);

	public List<VCcmTeam> findMyFriendAvailablePage(VCcmTeam vCcmTeam);

	//区域查用户
	public List<VCcmTeam> findAreaList(VCcmTeam vCcmTeam);

	public List<SearchTab> findByTypes();

	public List<VCcmTeam> findUserByOffice(VCcmTeam vCcmTeam2);
	
	public List<VCcmTeam> findTeamCount();

	public List<EchartType> getBySex();

	public List<EchartType> getByNation();
}
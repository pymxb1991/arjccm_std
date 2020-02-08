/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.view.service;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.TreeService;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.view.dao.VCcmTeamDao;
import com.arjjs.ccm.modules.ccm.view.entity.VCcmTeam;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.tool.EchartType;
import com.arjjs.ccm.tool.SearchTab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 综治队伍Service
 * @author liang
 * @version 2018-01-12
 */
@Service
@Transactional(readOnly = true)
public class VCcmTeamService extends TreeService<VCcmTeamDao, VCcmTeam> {

	//新填findform查询
	@Autowired
	private VCcmTeamDao vCcmTeamDao;
	
	public VCcmTeam get(String id) {
		return super.get(id);
	}
	
	public List<VCcmTeam> findList(VCcmTeam vCcmTeam) {
		if (StringUtils.isNotBlank(vCcmTeam.getParentIds())){
			vCcmTeam.setParentIds(","+vCcmTeam.getParentIds()+",");
		}
		return super.findList(vCcmTeam);
	}
	
	//新填findform查询
	public List<VCcmTeam> findform(VCcmTeam vCcmTeam) {
		if (StringUtils.isNotBlank(vCcmTeam.getParentIds())){
			vCcmTeam.setParentIds(","+vCcmTeam.getParentIds()+",");
		}
		return vCcmTeamDao.findform(vCcmTeam);
	}
	
	@Transactional(readOnly = false)
	public void save(VCcmTeam vCcmTeam) {
		super.save(vCcmTeam);
	}
	
	@Transactional(readOnly = false)
	public void delete(VCcmTeam vCcmTeam) {
		super.delete(vCcmTeam);
	}
	
	/**
	 * @see 获取所有当前的用户所在管理者信息 
	 * @param id
	 * @return
	 */
	public List<VCcmTeam> GetUserByArea(String id){
		return vCcmTeamDao.GetUserByArea(id);
	}
	/**
	 * @see 获取当前部门下所有用户信息
	 * @param id
	 * @return
	 */
	public List<VCcmTeam> findByOfficeParentIdsLike(Office office){
		return vCcmTeamDao.findByOfficeParentIdsLike(office);
	}

	public Page<VCcmTeam> findMyFriendAvailablePage(Page<VCcmTeam> page, VCcmTeam vCcmTeam) {
		vCcmTeam.setPage(page);
		page.setList(vCcmTeamDao.findMyFriendAvailablePage(vCcmTeam));
		return page;
	}

	//区域查用户
	public List<VCcmTeam> findAreaList(VCcmTeam vCcmTeam) {
		return vCcmTeamDao.findAreaList(vCcmTeam);
	}

	public List<SearchTab> findByTypes() {
		return vCcmTeamDao.findByTypes();
	}

	public List<VCcmTeam> findUserByOffice(VCcmTeam vCcmTeam2) {
		return vCcmTeamDao.findUserByOffice(vCcmTeam2);
	}

	/**
	 * @see 根据综治组织队伍性别统计情况。
	 * @return
	 */
	public List<EchartType> getBySex() {
		return dao.getBySex();
	}

	/**
	 * @see 根据综治组织队伍民族统计情况。
	 * @return
	 */
	public List<EchartType> getByNation() {
		return dao.getByNation();
	}


	public VCcmTeam getOneByUserId(String userId) {
		return dao.getOneByUserId(userId);
	}
}
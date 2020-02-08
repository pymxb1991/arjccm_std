/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.service;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.org.dao.SysAreaDao;
import com.arjjs.ccm.modules.ccm.org.entity.SysArea;
import com.arjjs.ccm.plugins.InterceptorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 区域扩展表（区域查询）Service
 * @author pengjianqiang
 * @version 2018-01-29
 */
@Service
@Transactional(readOnly = true)
public class SysAreaService extends CrudService<SysAreaDao, SysArea> {

	@Autowired
	private SysAreaDao sysAreaDao;
	
	public SysArea get(String id) {
		return super.get(id);
	}
	
	public List<SysArea> findList(SysArea sysArea) {
		return super.findList(sysArea);
	}
	
	public Page<SysArea> findPage(Page<SysArea> page, SysArea sysArea) {
		return super.findPage(page, sysArea);
	}
	
	@Transactional(readOnly = false)
	public void save(SysArea sysArea) {
		super.save(sysArea);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysArea sysArea) {
		super.delete(sysArea);
	}

	public List<SysArea> queryBuildCollectStat(SysArea sysArea) {
		return sysAreaDao.queryBuildCollectStat(sysArea);
	}
	public List<SysArea> queryRoomCollectStat(SysArea sysArea) {
		return sysAreaDao.queryRoomCollectStat(sysArea);
	}
	public List<SysArea> queryPeopleCollectStat(SysArea sysArea) {
		return sysAreaDao.queryPeopleCollectStat(sysArea);
	}
	public List<SysArea> queryNpseCollectStat(SysArea sysArea) {
		return sysAreaDao.queryNpseCollectStat(sysArea);
	}
	public List<SysArea> queryPlaceCollectStat(SysArea sysArea) {
		return sysAreaDao.queryPlaceCollectStat(sysArea);
	}

	public List<String> selectAreaByType(String type){
		List<String> areaNameList = new ArrayList<>();
		List<SysArea> sysAreas = sysAreaDao.selectAreaByType(type);
		for (SysArea area:sysAreas) {
			areaNameList.add(area.getName());
		}
		return areaNameList;
	}
	
	public List<SysArea> findByPid(SysArea area) {
		return sysAreaDao.findByPid(area);
	}

	public List<String> selectAreaIdByParentIdAndId(InterceptorEntity interceptorEntity) {
		return sysAreaDao.selectAreaIdByParentIdAndId(interceptorEntity);
	}
}
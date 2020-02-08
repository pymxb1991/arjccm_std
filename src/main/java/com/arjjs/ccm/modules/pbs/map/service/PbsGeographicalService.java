/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.map.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.map.entity.BindCount;
import com.arjjs.ccm.modules.pbs.map.entity.PbsGeographical;
import com.arjjs.ccm.modules.pbs.map.dao.PbsGeographicalDao;

/**
 * 地图信息表Service
 * @author lc
 * @version 2018-04-02
 */
@Service
@Transactional(readOnly = true)
public class PbsGeographicalService extends CrudService<PbsGeographicalDao, PbsGeographical> {
	
	@Autowired
	private PbsGeographicalDao pbsGeographicalDao;

	public PbsGeographical get(String id) {
		return super.get(id);
	}
	
	public List<PbsGeographical> findList(PbsGeographical pbsGeographical) {
		return super.findList(pbsGeographical);
	}
	
	public Page<PbsGeographical> findPage(Page<PbsGeographical> page, PbsGeographical pbsGeographical) {
		return super.findPage(page, pbsGeographical);
	}
	
	@Transactional(readOnly = false)
	public void save(PbsGeographical pbsGeographical) {
		super.save(pbsGeographical);
	}
	
	@Transactional(readOnly = false)
	public void delete(PbsGeographical pbsGeographical) {
		super.delete(pbsGeographical);
	}
	
	// 获取党员部门
	public List<BindCount> getPartymembindCount(){
		return pbsGeographicalDao.getPartymembindCount();
	}
	
	//根据类型和关联id 判断是否被标记过
	public int findByTypeAndKey(PbsGeographical pbsGeographical){
		return dao.findByTypeAndKey(pbsGeographical);
	}
	
	//查询每种类型在地图上面的数量
	public List<Integer> getNumByType(){
		return dao.getNumByType();
	}
}
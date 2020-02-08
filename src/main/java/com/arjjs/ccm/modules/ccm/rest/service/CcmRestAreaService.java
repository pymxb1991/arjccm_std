/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.service.TreeService;
import com.arjjs.ccm.modules.sys.dao.AreaDao;
import com.arjjs.ccm.modules.sys.entity.Area;
import com.arjjs.ccm.modules.sys.utils.UserUtils;

/**
 * 区域Service
 * @author admin001
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class CcmRestAreaService extends TreeService<AreaDao, Area> {

	@Autowired
	private AreaDao areaDao;
	
	public List<Area> findAll(){
		List<Area> areaList = areaDao.findAllList(new Area());
		
		return areaList;
	}

}

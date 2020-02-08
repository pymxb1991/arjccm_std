/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.cpp.service;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.cpp.dao.CppPopVehileDao;
import com.arjjs.ccm.modules.ccm.cpp.entity.CppPopVehile;
import com.arjjs.ccm.tool.EchartForce;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 人车/联系/网络关系表Service
 * @author liuxue
 * @version 2018-10-30
 */
@Service
@Transactional(readOnly = true)
public class CppPopVehileService extends CrudService<CppPopVehileDao, CppPopVehile> {

	public CppPopVehile get(String id) {
		return super.get(id);
	}
	
	public List<CppPopVehile> findList(CppPopVehile cppPopVehile) {
		return super.findList(cppPopVehile);
	}
	
	public Page<CppPopVehile> findPage(Page<CppPopVehile> page, CppPopVehile cppPopVehile) {
		return super.findPage(page, cppPopVehile);
	}
	
	@Transactional(readOnly = false)
	public void save(CppPopVehile cppPopVehile) {
		super.save(cppPopVehile);
	}
	
	@Transactional(readOnly = false)
	public void delete(CppPopVehile cppPopVehile) {
		super.delete(cppPopVehile);
	}

	public EchartForce getVehileForForce(CppPopVehile cppPopVehile){
		return dao.getVehileForForce(cppPopVehile);
	}
}
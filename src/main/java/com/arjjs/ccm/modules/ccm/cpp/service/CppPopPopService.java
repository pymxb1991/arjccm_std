/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.cpp.service;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.cpp.dao.CppPopPopDao;
import com.arjjs.ccm.modules.ccm.cpp.entity.CppPopPop;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.tool.EchartForce;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 人际关系Service
 * @author liuxue
 * @version 2018-11-03
 */
@Service
@Transactional(readOnly = true)
public class CppPopPopService extends CrudService<CppPopPopDao, CppPopPop> {
    
	public CcmPeople getIdCard(CcmPeople ccmPeople){
		List<CcmPeople> popList = dao.getIdCard(ccmPeople);
		if (popList != null && popList.size() >= 1) {
			return popList.get(0);
		} else {
			return new CcmPeople();
		}
	};
	public CppPopPop get(String id) {
		return super.get(id);
	}
	
	public List<CppPopPop> findList(CppPopPop cppPopPop) {
		return super.findList(cppPopPop);
	}
	
	public List<CppPopPop> findListAddTime(CppPopPop cppPopPop) {
		return dao.findListAddTime(cppPopPop);
	}
	
	public Page<CppPopPop> findPage(Page<CppPopPop> page, CppPopPop cppPopPop) {
		return super.findPage(page, cppPopPop);
	}
	
	@Transactional(readOnly = false)
	public void save(CppPopPop cppPopPop) {
		super.save(cppPopPop);
	}
	
	@Transactional(readOnly = false)
	public void delete(CppPopPop cppPopPop) {
		super.delete(cppPopPop);
	}



	public EchartForce findListForForce(CppPopPop cppPopPop){
		return dao.findListForForce(cppPopPop);
	}
	
}
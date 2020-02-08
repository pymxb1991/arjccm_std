/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.person.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.person.entity.PbsMemlabel;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.person.dao.PbsPartymemDao;

/**
 * 党员信息Service
 * 
 * @author lc
 * @version 2018-04-03
 */
@Service
@Transactional(readOnly = true)
public class PbsPartymemService extends CrudService<PbsPartymemDao, PbsPartymem> {

	@Autowired
	private PbsMemlabelService pbsMemlabelService;

	public PbsPartymem get(String id) {
		return super.get(id);
	}

	public List<PbsPartymem> findList(PbsPartymem pbsPartymem) {
		return super.findList(pbsPartymem);
	}

	public Page<PbsPartymem> findPage(Page<PbsPartymem> page, PbsPartymem pbsPartymem) {
		return super.findPage(page, pbsPartymem);
	}

	@Transactional(readOnly = false)
	public void save(PbsPartymem pbsPartymem) {
		super.save(pbsPartymem);
		// 插入或更新
		PbsMemlabel Memlabel = pbsPartymem.getPbsMemlabel();
		// 防止逻辑的递归自调用
		Memlabel.setsMemberid(new PbsPartymem(pbsPartymem.getId()));
		pbsMemlabelService.save(Memlabel);
	}

	@Transactional(readOnly = false)
	public void delete(PbsPartymem pbsPartymem) {
		super.delete(pbsPartymem);
	}

	public boolean checkExist(PbsPartymem pbsPartymem) {
		return dao.checkExist(pbsPartymem) == 0 ? false : true;
	}

	// 获取当前部门的所有党员信息
	public List<PbsPartymem> findListByOffice(PbsPartymem pbsPartymem) {
		return dao.findListByOffice(pbsPartymem);
	}
	
	public List<PbsPartymem> findListByOfficeList(List<String> ids){
		return dao.findListByOfficeList(ids);
	}
	
	public List<PbsPartymem> getListByName(String name){
		return dao.getListByName(name);
	}
	public List<String> findDepartName(String departId){
	    return dao.findDepartName(departId);
	}
	
}
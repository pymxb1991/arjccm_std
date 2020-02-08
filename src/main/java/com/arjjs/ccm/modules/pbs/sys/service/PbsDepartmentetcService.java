/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.sys.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.pbs.sys.entity.PbsDepartmentetc;
import com.arjjs.ccm.modules.pbs.sys.entity.PbsOffice;
import com.google.common.collect.Lists;
import com.arjjs.ccm.modules.pbs.sys.dao.PbsDepartmentetcDao;

/**
 * 党员部门扩展信息Service
 * 
 * @author lc
 * @version 2018-04-10
 */
@Service
@Transactional(readOnly = true)
public class PbsDepartmentetcService extends CrudService<PbsDepartmentetcDao, PbsDepartmentetc> {

	@Autowired
	private PbsDepartmentetcDao pbsDepartmentetcDao;

	public PbsDepartmentetc get(String id) {
		return super.get(id);
	}

	public List<PbsDepartmentetc> findList(PbsDepartmentetc pbsDepartmentetc) {
		return super.findList(pbsDepartmentetc);
	}

	public Page<PbsDepartmentetc> findPage(Page<PbsDepartmentetc> page, PbsDepartmentetc pbsDepartmentetc) {
		return super.findPage(page, pbsDepartmentetc);
	}

	@Transactional(readOnly = false)
	public void save(PbsDepartmentetc pbsDepartmentetc) {
		super.save(pbsDepartmentetc);
	}

	@Transactional(readOnly = false)
	public void delete(PbsDepartmentetc pbsDepartmentetc) {
		super.delete(pbsDepartmentetc);
	}

	// 检测该部门是否已经添加
	public boolean checkExist(PbsDepartmentetc pbsDepartmentetc) {
		return pbsDepartmentetcDao.checkExist(pbsDepartmentetc) == 0 ? false : true;
	}

	// 获取所有的部门名称
	public List<PbsOffice> getAllOfficeWithName(PbsOffice Office) {
		return dao.getAllOfficeWithName(Office);
	}

	// 获取所有的 部门 该用户所拥有 党员 所在的部门
	public List<PbsOffice> getAllOfficeWithMem(@Param("list") List<PbsOffice> list) {
		if(list.size()>0){
			return dao.getAllOfficeWithMem(list);
		}else{
			return Lists.newArrayList();
		}
		
	}

}